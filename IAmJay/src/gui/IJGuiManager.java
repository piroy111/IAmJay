package gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import background.strategy.Automaton;
import basicmethods.BasicPrintMsg;
import basicmethods.BasicTime;
import deity.IJDeity;
import turn.IJDilemma.type_arena;
import turn.IJDilemma.type_interact;
import zz_launch_me.IJManager;

public class IJGuiManager extends Automaton {

	public IJGuiManager() {
		super();
	}

	/*
	 * Data GUI
	 */
	private List<IJGridLine> pListIJGridLine;
	private List<IJGuiManoids> pListIJGuiManoids;
	private IJGuiRestart pIJGuiRestart;
	private Map<String, IJGuiDilemma> pMapKeyToIJGuiDilemma;
	private IJGuiDisplayGlobal pIJGuiDisplayGlobalIdxRound;
	private IJGuiBacktest pIJGuiBacktest;
	private IJGuiDisplayGlobal pIJGuiDisplayGlobalTypeArena;
	private IJGuiDisplayGlobal pIJGuiDisplayGlobalMemoryRancune;
	private IJGuiDisplayGlobal pIJGuiDisplayGlobalMemoryLanceurDAlerte;
	/*
	 * Data
	 */
	private IJManager pIJManager;


	public final void start() {
		/*
		 * Instantiate
		 */
		pIJManager = new IJManager(this);
		pIJManager.instantiate();
		instantiateBackground();
		/*
		 * Initiate
		 */		
		initiateBackground();
		pIJManager.initiate();
	}

	public final void refresh() {
		while (true) {
			/*
			 * Case we launch the BACKTESTER
			 */
			if (pIJGuiBacktest.pParamWMain_Backtest_start) {
				getmGUIManager().forceParam("pParamWMain_Backtest_start", pIJGuiBacktest, false);
				pIJManager.getpIJBacktestManager().initiate();
			}
			/*
			 * Case the BACKTESTER is running
			 */
			else if (pIJManager.getpIJBacktestManager().getpIsRunning()) {
				pIJManager.getpIJBacktestManager().refresh();
			}		
			/*
			 * Case of the GUI mode --> we have sleep time and displays
			 */
			else {
				pIJManager.refresh();
				/*
				 * Refresh GUI
				 */
				refreshBackground();
				pIJGuiDisplayGlobalIdxRound.setpValue("" + pIJManager.getpIJFight().getpIdxRound());
				pIJGuiDisplayGlobalTypeArena.setpValue("" + pIJManager.getpIJFight().getpIJArena().getpTypeArena());
				pIJGuiDisplayGlobalMemoryRancune.setpValue("" + getpMemoryRancune());
				pIJGuiDisplayGlobalMemoryLanceurDAlerte.setpValue("" + getpMemoryLanceurDAlerte());
				for (IJDeity lIJDeity : pIJManager.getpIJDeityManager().getpListDeity()) {
					lIJDeity.getpIJGuiDeity().refresh();
				}
				/*
				 * Sleep
				 */
				BasicTime.sleep(50);
			}
		}
	}


	@Override public void instantiateAutomaton() {
		/*
		 * Arena
		 */
		pListIJGridLine = new ArrayList<>();
		for (int lIdx = 0; lIdx < 50; lIdx++) {
			IJGridLine lIJGridLine = new IJGridLine(this, lIdx);
			lIJGridLine.instantiate();
			pListIJGridLine.add(lIJGridLine);
		}
		/*
		 * User restart
		 */
		pIJGuiRestart = new IJGuiRestart(this);
		/*
		 * MANOIDS
		 */
		pListIJGuiManoids = new ArrayList<>();
		createIJGuiManoids("Initial Manoid Life");
		createIJGuiManoids("Number of rounds");
		createIJGuiManoids("aride decay");
		createIJGuiManoids("fertile decay");
		createIJGuiManoids("type Arena (0= aride, 1= fertile, 2= random)");
		createIJGuiManoids("Ratio Glouton (>100)(in percent)");
		createIJGuiManoids("Memory for rancune");
		createIJGuiManoids("Memory for lanceur d'alerte");
		/*
		 * Deity
		 */
		for (IJDeity lIJDeity : pIJManager.getpIJDeityManager().getpMapNameToIJDeity().values()) {
			createIJGuiDeity(lIJDeity);
		}
		/*
		 * Prisoner Dilemma
		 */
		pMapKeyToIJGuiDilemma = new HashMap<>();
		createIJGuiDilemma(type_arena.ARIDE, type_interact.COOPERATE);
		createIJGuiDilemma(type_arena.ARIDE, type_interact.CROOK);
		createIJGuiDilemma(type_arena.FERTILE, type_interact.COOPERATE);
		createIJGuiDilemma(type_arena.FERTILE, type_interact.CROOK);
		/*
		 * Display global
		 */
		pIJGuiDisplayGlobalIdxRound = new IJGuiDisplayGlobal(this, "Idx Round");
		pIJGuiDisplayGlobalTypeArena = new IJGuiDisplayGlobal(this, "Type arena");
		pIJGuiDisplayGlobalMemoryRancune = new IJGuiDisplayGlobal(this, "Memory rancune");
		pIJGuiDisplayGlobalMemoryLanceurDAlerte = new IJGuiDisplayGlobal(this, "Memory Lanceur d'alerte");
		/*
		 * BACKTESTER
		 */
		pIJGuiBacktest = new IJGuiBacktest(this);
		/*
		 * Diplomacy
		 */
		createIJGuiDiplomacy("Ismene");
		createIJGuiDiplomacy("Xetra");
		createIJGuiDiplomacy("Netrenide");
		createIJGuiDiplomacy("Antigone");
		createIJGuiDiplomacy("Gamer");
		createIJGuiDiplomacy("Thalys");
	}

	/**
	 * 
	 */
	public final void afterRound() {
		for (IJGridLine lIJGridLine : pListIJGridLine) {
			for (IJGridCell lIJGridCell : lIJGridLine.getpListIJGridCell()) {
				lIJGridCell.afterRound();
			}
		}
	}

	/*
	 * CREATION
	 */
	private void createIJGuiManoids(String sTitle) {
		IJGuiManoids lIJGuiManoids = new IJGuiManoids(this, sTitle);
		pListIJGuiManoids.add(lIJGuiManoids);
	}

	private void createIJGuiDeity(IJDeity sIJDeity) {
		IJGuiDeity lIJGuiDeity = new IJGuiDeity(this, sIJDeity);
		sIJDeity.setpIJGuiDeity(lIJGuiDeity);
	}

	private void createIJGuiDilemma(type_arena sTypeArena, type_interact sTypeinteract) {
		IJGuiDilemma lIJGuiDilemma = new IJGuiDilemma(this, sTypeArena, sTypeinteract);
		String lKeyStr = lIJGuiDilemma.getpKeyStr();
		pMapKeyToIJGuiDilemma.put(lKeyStr, lIJGuiDilemma);
	}
	private void createIJGuiDiplomacy(String sNameIJDeity) {
		IJDeity lIJDeity = pIJManager.getpIJDeityManager().getpMapNameToIJDeity().get(sNameIJDeity);
		IJGuiDiplomacy lIjGuiDiplomacy = new IJGuiDiplomacy(this, lIJDeity);
		lIJDeity.setpIJGuiDiplomacy(lIjGuiDiplomacy);
	}

	
	
	@Override public void initiateAutomaton() {
		for (int lIdx = 0; lIdx < 50; lIdx++) {
			IJGridLine lIJGridLine = pListIJGridLine.get(lIdx);
			lIJGridLine.initiate();
		}
	}

	@Override public void refreshAutomaton() {
		/*
		 * User interface
		 */
		pIJGuiRestart.refresh();
		/*
		 * Grid
		 */
		for (int lIdx = 0; lIdx < 50; lIdx++) {
			IJGridLine lIJGridLine = pListIJGridLine.get(lIdx);
			lIJGridLine.refresh();
		}		
	}

	@Override public void actionOnCloseAutomaton() {
		// TODO Auto-generated method stub

	}

	@Override public void stopAllAutomaton() {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 
	 * @param sX
	 * @param sY
	 * @return
	 */
	public final IJGridCell getpIJGridCell(int sX, int sY) {
		if (0 <= sX && sX <= 49 && 0 <= sY && sY <= 49) {
			IJGridLine lIJGridLine = pListIJGridLine.get(sY);
			return lIJGridLine.getpListIJGridCell().get(sX);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param sIJGridCell
	 * @param sDX
	 * @param sDY
	 * @return
	 */
	public final IJGridCell getpIJGridCellNeighbour(IJGridCell sIJGridCell, int sDX, int sDY) {
		if (sDX == 0 && sDY == 0) {
			return null;
		} else {
			int lX = sIJGridCell.getpX() + sDX;
			int lY = sIJGridCell.getpY() + sDY;
			IJGridCell lIJGridCell = getpIJGridCell(lX, lY);
			if (lIJGridCell == null || lIJGridCell.getpIsOff()) {
				return null;
			} else {
				return lIJGridCell;
			}
		}
	}


	/*
	 * Parameters
	 */
	public final IJGuiDilemma getpIJGuiDilemmaParam(type_arena sTypeArena, type_interact sTypeinteract) {
		String lKeyStr = IJGuiDilemma.getKey(sTypeArena, sTypeinteract);
		IJGuiDilemma lIJGuiDilemma= pMapKeyToIJGuiDilemma.get(lKeyStr);
		return lIJGuiDilemma;
	}
	public final int getpInitialManoidLife() {
		return pListIJGuiManoids.get(0).pParamWMain_Manoids_ValueChosen;
	}
	public final int getpNumberOfRounds() {
		return pListIJGuiManoids.get(1).pParamWMain_Manoids_ValueChosen;
	}
	public final int getpArideDecay() {
		return pListIJGuiManoids.get(2).pParamWMain_Manoids_ValueChosen;
	}
	public final int getpFertileDecay() {
		return pListIJGuiManoids.get(3).pParamWMain_Manoids_ValueChosen;
	}
	public final type_arena getpChoiceTypeArena() {
		return type_arena.values()[pListIJGuiManoids.get(4).pParamWMain_Manoids_ValueChosen];
	}
	public final int getpRatioGlouton() {
		return pListIJGuiManoids.get(5).pParamWMain_Manoids_ValueChosen;
	}
	public final int getpMemoryRancune() {
		return pListIJGuiManoids.get(6).pParamWMain_Manoids_ValueChosen;
	}
	public final int getpMemoryLanceurDAlerte() {
		return pListIJGuiManoids.get(7).pParamWMain_Manoids_ValueChosen;
	}

	/*
	 * Set parameters
	 */
	public final void setpParameter(String sNameParam, int sValue) {
		/*
		 * Parameter MANOID
		 */
		switch (sNameParam) {
		case "Aride decay"									:  pListIJGuiManoids.get(2).pParamWMain_Manoids_ValueChosen = sValue; return;
		case "Fertile decay"								:  pListIJGuiManoids.get(3).pParamWMain_Manoids_ValueChosen = sValue; return;
		case "Type Arena (0=arine; 1=fertille; 2= random)"	:  pListIJGuiManoids.get(4).pParamWMain_Manoids_ValueChosen = sValue; return;
		case "Ratio Vorace"									:  pListIJGuiManoids.get(5).pParamWMain_Manoids_ValueChosen = sValue; return;
		case "Memory Rancune"								:  pListIJGuiManoids.get(6).pParamWMain_Manoids_ValueChosen = sValue; return;
		case "Memory Lanceur d'alerte"						:  pListIJGuiManoids.get(7).pParamWMain_Manoids_ValueChosen = sValue; return;
		}
		/*
		 * We split the name with '.'
		 */
		String[] lArrayName = sNameParam.split("\\.");
		/*
		 * Dilemma
		 */
		if (lArrayName[0].equals("Dilemma")) {
			type_arena lTypeArena = type_arena.valueOf(lArrayName[1]);
			type_interact lTypeInteract = type_interact.valueOf(lArrayName[2]);
			type_interact lTypeInteractVersus = type_interact.valueOf(lArrayName[3]);
			IJGuiDilemma lIJGuiDilemma = getpIJGuiDilemmaParam(lTypeArena, lTypeInteract);
			if (lTypeInteractVersus == type_interact.COOPERATE) {
				lIJGuiDilemma.pParamWMain_Dilemma_vs_Cooperate = sValue;
			} else {
				lIJGuiDilemma.pParamWMain_Dilemma_vs_Crook = sValue;
			}
			return;
		}
		/*
		 * Diplomacy
		 */
		if (lArrayName[0].equals("Diplomacy")) {
			/*
			 * Load
			 */
			String lNameDeity = lArrayName[1];
			String lNameDeityVersus = lArrayName[2];
			IJDeity lIJDeity = pIJManager.getpIJDeityManager().getpOrErrorIJDeity(this, lNameDeity);
			IJDeity lIJDeityVersus = pIJManager.getpIJDeityManager().getpOrErrorIJDeity(this, lNameDeityVersus);
			IJGuiDiplomacy lIJGuiDiplomacy = lIJDeity.getpIJGuiDiplomacy();
			IJGuiDiplomacy lIJGuiDiplomacyVersus = lIJDeityVersus.getpIJGuiDiplomacy();
			/*
			 * Set value
			 */
			lIJGuiDiplomacy.setValue(lNameDeityVersus, sValue);
			lIJGuiDiplomacyVersus.setValue(lNameDeity, sValue);
		}
		/*
		 * Technology
		 */
		if (lArrayName[0].equals("Techno")) {
			String lNameIJDeity = lArrayName[1];
			String lNameTechno = lArrayName[2];
			IJDeity lIJDeity = pIJManager.getpIJDeityManager().getpOrErrorIJDeity(this, lNameIJDeity);
			IJGuiDeity lIJGuiDeity = lIJDeity.getpIJGuiDeity();
			/*
			 * 
			 */
			switch (lNameTechno) {
			case "Nb Gullible": lIJGuiDeity.pParamWMain_Deities_Starting_Number_Guillibles = sValue; return;
			case "Nb Assholes": lIJGuiDeity.pParamWMain_Deities_Starting_Number_Assholes = sValue; return;
			case "A01": lIJDeity.setpIsTechnoA01Move(sValue == 1); return;
			case "A02": lIJDeity.setpIsTechnoA02RadarACretins(sValue == 1); return;
			case "A02b": lIJDeity.setpIsTechnoA02Teleportation(sValue == 1); return;
			case "A03": lIJDeity.setpIsTechnoA03Vorace(sValue == 1); return;
			case "H01": lIJDeity.setpIsTechnoG01Rancune(sValue == 1); return;
			case "H02": lIJDeity.setpIsTechnoG02LanceurAlerte(sValue == 1); return;
			case "H02b": lIJDeity.setpIsTechnoG02MemoireDelephant(sValue == 1); return;
			case "H03": lIJDeity.setpIsTechnoG03ImmuniteAlliance(sValue == 1); return;
			default: BasicPrintMsg.error("Unknown parameter= " + sNameParam);
			}
		}
	}
	
	
	/*
	 * Getters & Setters
	 */
	public final List<IJGridLine> getpListIJGridLine() {
		return pListIJGridLine;
	}
	public final IJGuiRestart getpIJGuiRestart() {
		return pIJGuiRestart;
	}
	public final IJManager getpIJManager() {
		return pIJManager;
	}
	public final List<IJGuiManoids> getpListIJGuiManoids() {
		return pListIJGuiManoids;
	}


}
