package backtest.param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import backtest.IJBacktestManager;
import basicmethods.BasicPrintMsg;
import deity.IJDeity;
import turn.IJDilemma.type_arena;
import turn.IJDilemma.type_interact;

public class IJParamManager {

	public IJParamManager(IJBacktestManager sIJBacktestManager) {
		pIJBacktestManager = sIJBacktestManager;
	}

	/*
	 * Data
	 */
	private IJBacktestManager pIJBacktestManager;
	private Map<String, IJParamAbstract> pMapNameToIJParamAbstract;
	private List<IJParamAbstract> pListIJParamAbstract;

	/**
	 * 
	 */
	public final void createIJParam() {
		pListIJParamAbstract = new ArrayList<>();
		pMapNameToIJParamAbstract = new HashMap<>();
		/*
		 * 
		 */
		new IJParamArideDecay("Aride decay", this);
		new IJParamFertileDecay("Fertile decay", this);
		new IJParamTypeArena("Type Arena (0=arine; 1=fertille; 2= random)", this);
		new IJParamRatioGlouton("Ratio Vorace", this);
		new IJParamMemoryRancune("Memory Rancune", this);
		new IJParamMemoryLanceurDAlerte("Memory Lanceur d'alerte", this);
		/*
		 * Dilemma
		 */
		for (type_arena lTypeArena : type_arena.values()) {
			if (lTypeArena != type_arena.RANDOM) {
				for (type_interact lTypeInteractGiver : type_interact.values()) {
					for (type_interact lTypeInteractReceiver : type_interact.values()) {
						String lName = IJPAramDilemma.getName(lTypeArena, lTypeInteractGiver, lTypeInteractReceiver);
						new IJPAramDilemma(lName, this, lTypeArena, lTypeInteractGiver, lTypeInteractReceiver);
					}
				}
			}
		}
		/*
		 * Deities
		 */
		for (IJDeity lIJDeity : pIJBacktestManager.getpIJManager().getpIJDeityManager().getpListDeity()) {
			new IJParamDeityNbGullible(lIJDeity.getpName() + "." + "Nb Gullible", this, lIJDeity);
			new IJParamDeityNbAssholes(lIJDeity.getpName() + "." + "Nb Assholes", this, lIJDeity);
		}
		/*
		 * Deity technology
		 */
		String[] lArray = {"A", "H"};
		for (IJDeity lIJDeity : pIJBacktestManager.getpIJManager().getpIJDeityManager().getpListDeity()) {
			for (String lLetter : lArray) {
				for (int lIdx = 1; lIdx <= 3; lIdx++) {
					String lTechno = lLetter + "0" + lIdx;
					String lName = lIJDeity + ".Techno." + lTechno;
					new IJParamDeityTechno(lName, this, lIJDeity, lTechno);
				}
			}
			new IJParamDeityTechno(lIJDeity + ".Techno.A02b", this, lIJDeity, "A02b");
			new IJParamDeityTechno(lIJDeity + ".Techno.H02b", this, lIJDeity, "H02b");
		}
		/*
		 * Deity diplomacy
		 */
		for (IJDeity lIJDeity : pIJBacktestManager.getpIJManager().getpIJDeityManager().getpListDeity()) {
			String lNameParam = "Diplomacy." + lIJDeity.getpName();
			new IJParamDiplomacy(lNameParam, this, lIJDeity);
		}
	}

	/**
	 * 
	 */
	public final void initiateBeforeBacktest() {
		for (IJParamAbstract lIJParamAbstract : pMapNameToIJParamAbstract.values()) {
			lIJParamAbstract.initiateBeforeBacktest();
		}
	}

	/**
	 * 
	 * @param _sIdxScenario
	 */
	public final void setParameters(int _sIdxScenario) {
		for (IJParamAbstract lIJParamAbstract : pMapNameToIJParamAbstract.values()) {
			lIJParamAbstract.setParameter(_sIdxScenario);
		}
	}


	/**
	 * 
	 * @param sIJParamAbstract
	 */
	public final void declareNewParam(IJParamAbstract sIJParamAbstract) {
		pMapNameToIJParamAbstract.put(sIJParamAbstract.getpNameParam(), sIJParamAbstract);
		pListIJParamAbstract.add(sIJParamAbstract);
	}

	/**
	 * 
	 * @param sName
	 * @return
	 */
	public final IJParamAbstract getIJParam(String sName) {
		IJParamAbstract lIJParamAbstract = pMapNameToIJParamAbstract.get(sName);
		if (lIJParamAbstract == null) {
			BasicPrintMsg.error("The parameter does not exist"
					+ "\nName parameter which is not found= '" + sName
					+ "\nList of existing parameters= " + pMapNameToIJParamAbstract.keySet());
		}
		return lIJParamAbstract;
	}

	/*
	 * Getters & Setters
	 */
	public final IJBacktestManager getpIJBacktestManager() {
		return pIJBacktestManager;
	}
	public final List<IJParamAbstract> getpListIJParamAbstract() {
		return pListIJParamAbstract;
	}	
}
