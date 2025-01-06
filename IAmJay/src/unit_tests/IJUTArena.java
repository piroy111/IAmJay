package unit_tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import basicmethods.BasicPrintMsg;
import basicmethods.BasicString;
import basicmethods.ReadFile;
import constants.IJStatic;
import deity.IJDeity;
import gui.IJGridCell;
import gui.IJGuiManager;
import manoid.IJManoid;
import manoid.IJManoidManager.type_manoid;
import turn.IJArena;

public class IJUTArena {

	
	protected IJUTArena(IJUTOneTest sIJUTOneTest) {
		pIJUTOneTest = sIJUTOneTest;
	}
	
	/*
	 * Data
	 */
	private IJUTOneTest pIJUTOneTest;
	private Map<Integer, Map<Integer, IJUTCell>> pMapGridXtoYtoIJUTCell;
	private int pIdxRound;
	private ReadFile pReadFile;
	
	
	/**
	 * 
	 * @param sReadFile
	 */
	public final void fillMap(ReadFile sReadFile) {
		pReadFile = sReadFile;
		/*
		 * Check the name of the file is correct
		 */
		if (!sReadFile.getmNameFile().startsWith(IJStatic.getUT_ARENA())) {
			BasicPrintMsg.error("The file name is incorrect. FileName= " + sReadFile.getmNameFile());
		}
		String[] lArrayWord = sReadFile.getmNameFile().split("_");
		String lIdxStr = lArrayWord[2].substring(0, 2);
		pIdxRound = BasicString.getInt(lIdxStr);
		if (pIdxRound < 0) {
			BasicPrintMsg.error("The file name is incorrect. FileName= " + sReadFile.getmNameFile());
		}
		/*
		 * Read file and fill the Map
		 */
		pMapGridXtoYtoIJUTCell = new HashMap<>();
		int lSizeArena = sReadFile.getmContentList().size();
		for (int lY = 0; lY < lSizeArena; lY++) {
			for (int lX = 0; lX < lSizeArena; lX++) {
				/*
				 * Get Value
				 */
				List<String> lLineStr = sReadFile.getmContentList().get(lY);
				String lValue = lLineStr.get(lX);
				/*
				 * Get or create Map
				 */
				Map<Integer, IJUTCell> lMapYtoIJUTCell = pMapGridXtoYtoIJUTCell.get(lX);
				if (lMapYtoIJUTCell == null) {
					lMapYtoIJUTCell = new HashMap<>();
					pMapGridXtoYtoIJUTCell.put(lX, lMapYtoIJUTCell);
				}					
				/*
				 * Create IJUTCell and put in map
				 */
				if (!lValue.equals("")) {
					IJUTCell lIJUTCell = new IJUTCell(this, lX, lY, lValue);
					lMapYtoIJUTCell.put(lY, lIJUTCell);
				} else {
					lMapYtoIJUTCell.put(lY,  null);
				}
			}
		}
	}
	
	/**
	 * 
	 */
	public final void check() {
		IJArena lIJArena = pIJUTOneTest.getpIJUTManager().getpIJManager().getpIJFight().getpIJArena();
		int lSizeFromParam = lIJArena.getpXf() - lIJArena.getpX0() + 1;
		if (pMapGridXtoYtoIJUTCell.size() > lSizeFromParam) {
			BasicPrintMsg.error("The size of the arena from the UT file is different than the size of the arena from the parameters"
					+ "\nSize of the arena from file= " + pMapGridXtoYtoIJUTCell.size()
					+ "\nSize of the arena from param= " + lSizeFromParam
					+ "\nFile= " + pReadFile.getmDirPlusNameFile());
		}
	}

	
	/**
	 * 
	 */
	public final void useThisArenaToSetNumberOfManoids() {
		BasicPrintMsg.display(this, "Use the Arena 00 to fill the start position");
		/*
		 * Initiate
		 */
		Map<IJDeity, Integer> lMapIJDeityToNbGullibles = new HashMap<>();
		Map<IJDeity, Integer> lMapIJDeityToNbAssholes = new HashMap<>();
		List<IJDeity> lListIJDeity = pIJUTOneTest.getpIJUTManager().getpIJManager().getpIJDeityManager().getpListDeity();
		for (IJDeity lIJDeity : lListIJDeity) {
			lMapIJDeityToNbGullibles.put(lIJDeity, 0);
			lMapIJDeityToNbAssholes.put(lIJDeity, 0);
		}
		/*
		 * Fill Maps
		 */
		List<IJUTCell> lListIJUTCell = getpListIJUTCell();
		for (IJUTCell lIJUTCell : lListIJUTCell) {
			IJDeity lIJDeity = lIJUTCell.getpIJDeity();
			if (lIJUTCell.getpTypeManoid() == type_manoid.GULLIBLE) {
				lMapIJDeityToNbGullibles.put(lIJDeity, lMapIJDeityToNbGullibles.get(lIJDeity) + 1);
			} else {
				lMapIJDeityToNbAssholes.put(lIJDeity, lMapIJDeityToNbAssholes.get(lIJDeity) + 1);
			}
		}
		/*
		 * Force parameters
		 */
		IJGuiManager lIJGuiManager = pIJUTOneTest.getpIJUTManager().getpIJManager().getpIJGuiManager();
		for (IJDeity lIJDeity : lListIJDeity) {
			String lNameParamGullible = "Techno." + lIJDeity.getpName() + ".Nb Gullible";
			String lNameParamAsshole = "Techno." + lIJDeity.getpName() + ".Nb Assholes";
			lIJGuiManager.setpParameter(lNameParamGullible, lMapIJDeityToNbGullibles.get(lIJDeity));
			lIJGuiManager.setpParameter(lNameParamAsshole, lMapIJDeityToNbAssholes.get(lIJDeity));
		}
		
	}

	/**
	 * Special case where the arena is the starting arena - only for the starting arena
	 */
	public final void useThisArenaToPlaceManoidAtStart() {
		List<IJUTCell> lListIJUTCell = getpListIJUTCell();
		for (IJUTCell lIJUTCell : lListIJUTCell) {
			/*
			 * Create MANOID
			 */
			IJManoid lIJManoid = lIJUTCell.getpIJDeity().createIJManoid(lIJUTCell.getpTypeManoid());
			/*
			 * Place the MANOID in the grid
			 */
			IJArena lIJArena = pIJUTOneTest.getpIJUTManager().getpIJManager().getpIJFight().getpIJArena();
			int lX = lIJUTCell.getpX() + lIJArena.getpX0();
			int lY = lIJUTCell.getpY() + lIJArena.getpY0();
			lIJManoid.placeManoidInGrid(lX, lY);
		}
	}

	/**
	 * Return null if the cell is empty
	 * @param sX
	 * @param sY
	 * @return
	 */
	public final IJUTCell getpIJUTCell(int sX, int sY) {
		Map<Integer, IJUTCell> lMapYtoIJUTCell = pMapGridXtoYtoIJUTCell.get(sX);
		if (lMapYtoIJUTCell == null) {
			return null;
		}
		IJUTCell lIJUTCell = lMapYtoIJUTCell.get(sY);
		return lIJUTCell;
	}
	
	/**
	 * 
	 * @return
	 */
	private List<IJUTCell> getpListIJUTCell() {
		List<IJUTCell> lListIJUTCell = new ArrayList<>();
		for (int lX = 0; lX < pMapGridXtoYtoIJUTCell.size(); lX++) {
			Map<Integer, IJUTCell> lMapYtoIJUTCell = pMapGridXtoYtoIJUTCell.get(lX);
			if (lMapYtoIJUTCell != null) {
				for (int lY = 0; lY < lMapYtoIJUTCell.size(); lY++) {
					IJUTCell lIJUTCell = lMapYtoIJUTCell.get(lY);
					if (lIJUTCell != null) {
						lListIJUTCell.add(lIJUTCell);
					}
				}
			}			
		}
		return lListIJUTCell;
	}

	/**
	 * 
	 */
	public final void checkVersusActualArena() {
		IJArena lIJArena = pIJUTOneTest.getpIJUTManager().getpIJManager().getpIJFight().getpIJArena();
		for (IJGridCell lIJGridCell : lIJArena.getpListIJGridCell()) {
			/*
			 * Find the equivalent IJUTCell
			 */
			int lX = lIJGridCell.getpX() - lIJArena.getpX0();
			int lY = lIJGridCell.getpY() - lIJArena.getpY0();
			IJUTCell lIJUTCell = getpIJUTCell(lX, lY);
			/*
			 * Case of error
			 */
			String lErrorMsg = "";
			if (lIJUTCell != null && lIJGridCell.getpIJManoid() != null) {
				if (!lIJUTCell.getpIJDeity().equals(lIJGridCell.getpIJManoid().getpIJDeity())) {
					lErrorMsg = "Wrong deity";
				} else if (lIJUTCell.getpTypeManoid() != lIJGridCell.getpIJManoid().getpTypeManoid()) {
					lErrorMsg = "Wrong type manoid";
				} else if (lIJUTCell.getpLife() != lIJGridCell.getpIJManoid().getpLife()) {
					lErrorMsg = "Wrong life number";
				}
			} else if (lIJUTCell != null || lIJGridCell.getpIJManoid() != null) {
				lErrorMsg = "One of the cell is empty";
			}
			if (!lErrorMsg.equals("")) {
				exportCSV();
				BasicPrintMsg.error(lErrorMsg);
			}
		}		
	}
	
	/**
	 * 
	 */
	public final void exportCSV() {
//		String lDir = IJStatic.getDIR_UNIT_TESTS_EXPORT_ARENA();
//		String lNameFile = BasicDateInt.getmToday() + IJStatic.getUT_EXPORT_ARENA();
		
		BasicPrintMsg.display(this, "");
		BasicPrintMsg.display(this, "There is a problem. Here is the grid computed by the program:");
		BasicPrintMsg.display(this, "ReadFile= " + pReadFile.getmDirPlusNameFile());
		BasicPrintMsg.display(this, "");
		IJArena lIJArena = pIJUTOneTest.getpIJUTManager().getpIJFight().getpIJArena();
		for (int lY = lIJArena.getpY0(); lY <= lIJArena.getpYf(); lY++) {
			for (int lX = lIJArena.getpX0(); lX <= lIJArena.getpXf(); lX++) {
				IJGridCell lIJGridCell = pIJUTOneTest.getpIJUTManager().getpIJManager().getpIJGuiManager().getpIJGridCell(lX, lY);
				/*
				 * Build Message
				 */
				String lString;
				if (lIJGridCell.getpIJManoid() == null) {
					lString = "....";
				} else {
					IJManoid lIJManoid = lIJGridCell.getpIJManoid();
					lString = lIJManoid.getpIJDeity().getpName().substring(0, 1)
							+ lIJManoid.getpTypeManoid().toString().substring(0, 1).toLowerCase()
							+ BasicString.getIntegerAvecZerosDevant(lIJManoid.getpLife(), 2);
				}
				System.out.print(lString + "  ");
			}
			System.out.println();
		}
	}
	
	/*
	 * Getters & Setters
	 */
	public final IJUTOneTest getpIJUTOneTest() {
		return pIJUTOneTest;
	}
	public final int getpIdxRound() {
		return pIdxRound;
	}
	public final ReadFile getpReadFile() {
		return pReadFile;
	}
	
	
	
	
	
	
	
	
	
}
