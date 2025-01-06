package unit_tests;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import basicmethods.AMNumberTools;
import basicmethods.BasicFichiersNio;
import basicmethods.BasicPrintMsg;
import basicmethods.BasicString;
import basicmethods.ReadFile;
import basicmethods.ReadFile.comReadFile;
import constants.IJStatic;
import deity.IJDeity;
import turn.IJDilemma.type_interact;
import turn.IJFight;
import zz_launch_me.IJManager;

public class IJUTManager {

	public IJUTManager(IJManager sIJManager) {
		pIJManager = sIJManager;
	}

	/*
	 * Data
	 */
	private IJManager pIJManager;
	private List<IJUTOneTest> pListIJUTOneTest;
	private static boolean pIsUnitTestRunning;
	private IJFight pIJFight;
	private IJUTOneTest pIJUTOneTestCurrent;
	private Map<IJDeity, IJUTDeity> pMapIJDeityToIJUTDeity;

	/**
	 * 
	 */
	public final void run() {
		BasicPrintMsg.displaySuperTitle(this, "Unit test");
		pIsUnitTestRunning = true;
		/*
		 * Create the test units from the CSV files
		 */
		createUnitTest();
		createIJUTDeities();
		/*
		 * run the tests
		 */
		for (IJUTOneTest lIJUTOneTest : pListIJUTOneTest) {
			BasicPrintMsg.displayTitle(this, "Run unit test " + lIJUTOneTest.getpName());
			pIJUTOneTestCurrent = lIJUTOneTest;
			/*
			 * Reset scores
			 */
			pIJManager.getpIJDeityManager().resetScores();
			/*
			 * Force parameters
			 */
			lIJUTOneTest.forceParameters();
			/*
			 * Load Arenas
			 */
			lIJUTOneTest.loadArenas();
			lIJUTOneTest.getpIJUTArenaStart().useThisArenaToSetNumberOfManoids();
			/*
			 * Load IJUTDeity
			 */
			loadIJUTDeities();
			/*
			 * Initiate fight: type of the arena + size of the arena + dilemma + deities
			 */
			pIJFight = pIJManager.getpIJFight();
			pIJFight.initiate();
			BasicPrintMsg.display(this, "IJDilemma= " + pIJFight.getpIJDilemma());
			/*
			 * Double check parameters + arenas
			 */
//			doubleCheckParameters();
			doubleCheckArenas();
			/*
			 * Run the test
			 */
			pIJFight.start(true);
			BasicPrintMsg.display(this, "");
			for (int lIdxRound = 1; lIdxRound <= pIJUTOneTestCurrent.getpMaxRound(); lIdxRound++) {
				/*
				 * Run the game
				 */
				pIJFight.refresh();
				/*
				 * Check the result versus the expected file
				 */
				IJUTArena lIJUTArena = pIJUTOneTestCurrent.getpMapIdxRoundToIJUTArena().get(lIdxRound);
				if (lIJUTArena != null) {
					BasicPrintMsg.display(this, "Starting round " + lIdxRound);
					lIJUTArena.checkVersusActualArena();
					checkVersusActualScores();
					BasicPrintMsg.display(this, "... All good for round " + lIdxRound);
				}
			}
			/*
			 * Communication
			 */
			BasicPrintMsg.display(this, "Test unit completed with success '" + lIJUTOneTest.getpName() + "'");
		}
		/*
		 * Exit
		 */
		pIsUnitTestRunning = false;
	}

	/**
	 * 
	 */
	private void createUnitTest() {
		BasicPrintMsg.displayTitle(this, "Create unit test");
		/*
		 * Load the unit tests
		 */
		String lDir = IJStatic.getDIR_UNIT_TESTS();
		List<String> lListUnitTest = BasicFichiersNio.getListFilesAndDirectoriesInDirectory(lDir);
		pListIJUTOneTest = new ArrayList<>();
		/*
		 * Loop over the unit test
		 */
		for (String lDirUT : lListUnitTest) {
			BasicPrintMsg.display(this, "New unit test detected= " + lDirUT);
			String lUTDir = lDir + lDirUT + "/";
			/*
			 * Check we have enough file
			 */
			ReadFile lReadFileParameters = new ReadFile(Paths.get(lUTDir + IJStatic.getUT_PARAMETERS()), ReadFile.comReadFile.COM_ONLY_IF_ERROR);
			if (!lReadFileParameters.getmIsFileReadSuccessFully()) {
				BasicPrintMsg.error("The file does not exist. Expected file= " + lUTDir + IJStatic.getUT_PARAMETERS());
			}
			String lNameFileArena00 = IJStatic.getUT_ARENA() + "00.csv";
			ReadFile lReadFileArenaStart = new ReadFile(Paths.get(lUTDir + lNameFileArena00), ReadFile.comReadFile.COM_ONLY_IF_ERROR);
			if (!lReadFileArenaStart.getmIsFileReadSuccessFully()) {
				BasicPrintMsg.error("The file does not exist. Expected file= " + lUTDir + lNameFileArena00);
			}
			/*
			 * Read all files of Arena
			 */
			List<String> lListFiles = BasicFichiersNio.getListFilesInDirectory(lUTDir);
			List<ReadFile> lListReadFilesArena = new ArrayList<>();
			for (String lFileName : lListFiles) {
				if (lFileName.startsWith(IJStatic.getUT_ARENA())) {
					ReadFile lReadFile = new ReadFile(lUTDir + lFileName, comReadFile.COM_ONLY_IF_ERROR);
					lListReadFilesArena.add(lReadFile);
				}
			}
			/*
			 * Create UnitTest
			 */
			IJUTOneTest lIJUTOneTest = new IJUTOneTest(lDirUT, this);
			pListIJUTOneTest.add(lIJUTOneTest);
			lIJUTOneTest.initiate(lReadFileParameters, lReadFileArenaStart, lListReadFilesArena);
		}
		/*
		 * Communication
		 */
		BasicPrintMsg.display(this, null);
		BasicPrintMsg.display(this, "Number of unit tests created: " + pListIJUTOneTest.size());
	}

	/**
	 * 
	 */
	public final void doubleCheckParameters() {
		if (pListIJUTOneTest.size() == 1) {
			BasicPrintMsg.displayTitle(this, "Double check parameters");
			BasicPrintMsg.display(this, "Type arena= " + pIJManager.getpIJGuiManager().getpChoiceTypeArena());
			for (IJDeity lIJDeity : pIJManager.getpIJDeityManager().getpListDeity()) {
				BasicPrintMsg.display(this, lIJDeity.getpName() + ".Nb Gullible= " + lIJDeity.getpIJGuiDeity().pParamWMain_Deities_Starting_Number_Guillibles);
				BasicPrintMsg.display(this, lIJDeity.getpName() + ".Nb Assholes= " + lIJDeity.getpIJGuiDeity().pParamWMain_Deities_Starting_Number_Assholes);
			}
			BasicPrintMsg.display(this, "Decay= " + pIJFight.getpIJDilemma().getpDecay());
			for (type_interact lTypeInteract : type_interact.values()) {
				for (type_interact lTypeInteractVersus : type_interact.values()) {
					BasicPrintMsg.display(this, "Dilemma." + lTypeInteract + "." + lTypeInteractVersus + "= " + pIJFight.getpIJDilemma().getpMapGiveVsReceiveToOutcome().get(lTypeInteract).get(lTypeInteractVersus));
				}
			}
			for (IJDeity lIJDeity : pIJManager.getpIJDeityManager().getpListDeity()) {
				for (IJDeity lIJDeityVersus : pIJManager.getpIJDeityManager().getpListDeity()) {
					BasicPrintMsg.display(this, lIJDeity.getpName() + ".Diplomacy." + lIJDeity.getpName() + "." + lIJDeityVersus.getpName() + "= " + lIJDeity.getpDiplomacyWith(lIJDeityVersus));
				}
			}
			BasicPrintMsg.display(this, "Ratio vorace= " + pIJManager.getpIJGuiManager().getpRatioGlouton());
			BasicPrintMsg.display(this, "Memory Rancune= " + pIJManager.getpIJGuiManager().getpMemoryRancune());
			BasicPrintMsg.display(this, "Memory Lanceur d'alerte= " + pIJManager.getpIJGuiManager().getpMemoryLanceurDAlerte());
			for (IJDeity lIJDeity : pIJManager.getpIJDeityManager().getpListDeity()) {
				BasicPrintMsg.display(this, "Techno." + lIJDeity.getpName() + ".A01= " + lIJDeity.getpIsTechnoA01Move());
				BasicPrintMsg.display(this, "Techno." + lIJDeity.getpName() + ".A02= " + lIJDeity.getpIsTechnoA02RadarACretins());
				BasicPrintMsg.display(this, "Techno." + lIJDeity.getpName() + ".A03= " + lIJDeity.getpIsTechnoA03Vorace());
				BasicPrintMsg.display(this, "Techno." + lIJDeity.getpName() + ".H01= " + lIJDeity.getpIsTechnoG01Rancune());
				BasicPrintMsg.display(this, "Techno." + lIJDeity.getpName() + ".H02= " + lIJDeity.getpIsTechnoG02LanceurAlerte());
				BasicPrintMsg.display(this, "Techno." + lIJDeity.getpName() + ".H03= " + lIJDeity.getpIsTechnoG03ImmuniteAlliance());
			}
		}
	}

	/**
	 * 
	 */
	public final void doubleCheckArenas() {
		for (IJUTArena lIJUTArena : pIJUTOneTestCurrent.getpMapIdxRoundToIJUTArena().values()) {
			lIJUTArena.check();
		}
	}

	/**
	 * 
	 */
	public final void createManoids() {
		pIJUTOneTestCurrent.getpIJUTArenaStart().useThisArenaToPlaceManoidAtStart();
	}
	
	/**
	 * 
	 */
	private final void createIJUTDeities() {
		pMapIJDeityToIJUTDeity = new HashMap<>();
		for (IJDeity lIJDeity : pIJManager.getpIJDeityManager().getpListDeity()) {
			IJUTDeity lIJUTDeity = new IJUTDeity(this, lIJDeity);
			pMapIJDeityToIJUTDeity.put(lIJDeity, lIJUTDeity);
		}
	}
	
	/**
	 * 
	 */
	private void loadIJUTDeities() {
		BasicPrintMsg.displayTitle(this, "create IJUTDeities");
		/*
		 * Initiate
		 */
		String lDir = pIJUTOneTestCurrent.getpReadFileParameters().getmDir();
		String lNameFile = IJStatic.getUT_SCORES();
		ReadFile lReadFile = new ReadFile(lDir, lNameFile, comReadFile.FULL_COM);
		if (!lReadFile.getmIsFileReadSuccessFully()) {
			BasicPrintMsg.error("The file of scores does not exist");
		}
		List<Integer> lListIdxRound = new ArrayList<>();
		/*
		 * Read file
		 */
		for (int lIdx = 0; lIdx < lReadFile.getmContentList().size(); lIdx++) {
			List<String> lLineList = lReadFile.getmContentList().get(lIdx);
			/*
			 * Case of the header --> we take the round
			 */
			if (lIdx == 0) {
				for (int lKdx = 1; lKdx < lLineList.size(); lKdx++) {
					int lIdxRound = BasicString.getInt(lLineList.get(lKdx));
					lListIdxRound.add(lIdxRound);
				}
			}
			/*
			 * Case of a deity
			 */
			else {
				String lNameDeity = lLineList.get(0);
				IJDeity lIJDeity = pIJManager.getpIJDeityManager().getpOrErrorIJDeity(this, lNameDeity);
				IJUTDeity lIJUTDeity = pMapIJDeityToIJUTDeity.get(lIJDeity);
				lIJUTDeity.initiate();
				for (int lKdx = 1; lKdx < lLineList.size(); lKdx++) {
					int lIdxRound = lListIdxRound.get(lKdx - 1);
					int lScore = BasicString.getInt(lLineList.get(lKdx));
					lIJUTDeity.addNewScore(lIdxRound, lScore);
				}
			}
		}
	}
	
	/**
	 * 
	 */
	public final void checkVersusActualScores() {
		String lErrorMsg = "";
		for (IJDeity lIJDeity : pMapIJDeityToIJUTDeity.keySet()) {
			IJUTDeity lIJUTDeity = pMapIJDeityToIJUTDeity.get(lIJDeity);
			if (lIJUTDeity.getpMapIdxRoundToScore().containsKey(pIJFight.getpIdxRound())) {
				int lScoreFromCSV = lIJUTDeity.getpMapIdxRoundToScore().get(pIJFight.getpIdxRound());
				if (lIJDeity.getpScore() != lScoreFromCSV) {
					lErrorMsg += "\nIJDeity= " + lIJDeity.getpName() 
						+ "; score from computer= " + lIJDeity.getpScore() 
						+ "; Score from CSV= " + lScoreFromCSV;
				}
			}
		}
		/*
		 * Error message
		 */
		if (!lErrorMsg.equals("")) {
			lErrorMsg = "\nThe Arena passed" 
					+ "\nBut the scores dont match with the file of the test unit"
					+ "\nTest unit= " + pIJUTOneTestCurrent.getpName()
					+ lErrorMsg;
			BasicPrintMsg.error(lErrorMsg);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public static double random() {
		if (pIsUnitTestRunning) {
			return 0;
		} else {
			return Math.random();
		}
	}
	
	
	/*
	 * Getters & Setters
	 */
	public final IJManager getpIJManager() {
		return pIJManager;
	}
	public final List<IJUTOneTest> getpListIJUTOneTest() {
		return pListIJUTOneTest;
	}
	public final boolean getpIsUnitTestRunning() {
		return pIsUnitTestRunning;
	}

	public final IJUTOneTest getpIJUTOneTestCurrent() {
		return pIJUTOneTestCurrent;
	}

	public final IJFight getpIJFight() {
		return pIJFight;
	}

}
