package turn;

import java.util.HashMap;
import java.util.Map;

import deity.IJDeity;
import gui.IJGuiManager;
import manoid.IJManoid;
import manoid.IJManoidManager.type_manoid;

public class IJDilemma {

	public IJDilemma(IJFight sIJFight) {
		pIJFight = sIJFight;
	}
	
	/*
	 * ENUM
	 */
	public enum type_arena {ARIDE, FERTILE, RANDOM}
	public enum type_interact {CROOK, COOPERATE}
	/*
	 * Managers
	 */
	private IJFight pIJFight;
	/*
	 * data
	 */
	private int pDecay;
	private Map<type_interact, Map<type_interact, Integer>> pMapGiveVsReceiveToOutcome;
	
	/**
	 * Initiate the parameters of the dilemma
	 */
	public final void initiate() {
		IJGuiManager lIJGuiManager = pIJFight.getpIJGuiManager();
		pMapGiveVsReceiveToOutcome = new HashMap<>();
		type_arena lTypeArena = pIJFight.getpIJArena().getpTypeArena();
		/*
		 * 
		 */
		int lWW = lIJGuiManager.getpIJGuiDilemmaParam(lTypeArena, type_interact.COOPERATE).pParamWMain_Dilemma_vs_Cooperate;
		int lWL = lIJGuiManager.getpIJGuiDilemmaParam(lTypeArena, type_interact.COOPERATE).pParamWMain_Dilemma_vs_Crook;
		int lLW = lIJGuiManager.getpIJGuiDilemmaParam(lTypeArena, type_interact.CROOK).pParamWMain_Dilemma_vs_Cooperate;
		int lLL = lIJGuiManager.getpIJGuiDilemmaParam(lTypeArena, type_interact.CROOK).pParamWMain_Dilemma_vs_Crook;
		/*
		 * 
		 */
		fillMapInteract(type_interact.COOPERATE, type_interact.COOPERATE, lWW);
		fillMapInteract(type_interact.COOPERATE, type_interact.CROOK, lWL);
		fillMapInteract(type_interact.CROOK, type_interact.COOPERATE, lLW);
		fillMapInteract(type_interact.CROOK, type_interact.CROOK, lLL);
		/*
		 * 
		 */
		if (lTypeArena == type_arena.ARIDE) {
			pDecay = lIJGuiManager.getpArideDecay();
		} else {
			pDecay = lIJGuiManager.getpFertileDecay();
		}
	}
	
	/**
	 * Fill map - get or create
	 */
	private void fillMapInteract(type_interact sGive, type_interact sReceive, int sOutcome) {
		Map<type_interact, Integer> lMapReceiveToOutcome = pMapGiveVsReceiveToOutcome.get(sGive);
		if (lMapReceiveToOutcome == null) {
			lMapReceiveToOutcome = new HashMap<>();
			pMapGiveVsReceiveToOutcome.put(sGive, lMapReceiveToOutcome);
		}
		lMapReceiveToOutcome.put(sReceive, sOutcome);
	}

	/**
	 * 
	 * @param sGive
	 * @param sReceive
	 * @return outcome of an interaction between a giver and a receiver (giver is in the centre, this is the GAMER)
	 */
	public final int getpOutcome(IJManoid sIJManoidGive, IJManoid sIJManoidReceive) {
		IJDeity lIJDeityGive = sIJManoidGive.getpIJDeity();
		IJDeity lIJDeityReceive = sIJManoidReceive.getpIJDeity();
		/*
		 * Immunity of Alliance from the giver
		 */
		if (lIJDeityGive.getpIsTechnoG03ImmuniteAlliance()) {
			if (sIJManoidReceive.getpTypeManoid() == type_manoid.ASSHOLE) {
				if (lIJDeityGive.getpMapIJDeityToLevelDiplomacy().get(lIJDeityReceive) >= lIJDeityReceive.getpMaxLevelDipolomacy()) {
					return 0;
				}
			}
		}
		if (lIJDeityReceive.getpIsTechnoG03ImmuniteAlliance()) {
			if (sIJManoidGive.getpTypeManoid() == type_manoid.ASSHOLE) {
				if (lIJDeityReceive.getpMapIJDeityToLevelDiplomacy().get(lIJDeityGive) >= lIJDeityGive.getpMaxLevelDipolomacy()) {
					return 0;
				}
			}
		}
		/*
		 * Find the behaviour of each MANOID
		 */
		type_interact lTypeInteractGive = sIJManoidGive.interact(sIJManoidReceive);
		type_interact lTypeInteractReceive = sIJManoidReceive.interact(sIJManoidGive);
		/*
		 * Find the outcome as per the dilemma
		 */
		int lOutcome = pMapGiveVsReceiveToOutcome.get(lTypeInteractGive).get(lTypeInteractReceive);
		/*
		 * Impact of technology
		 */
		boolean lIsVoraceAttaque = lIJDeityGive.getpIsTechnoA03Vorace() 
				&& sIJManoidGive.getpTypeManoid() == type_manoid.ASSHOLE
				&& sIJManoidReceive.getpTypeManoid() == type_manoid.GULLIBLE;
		boolean lIsGullibleAttaquedByVorace = lIJDeityReceive.getpIsTechnoA03Vorace() 
				&& sIJManoidGive.getpTypeManoid() == type_manoid.GULLIBLE
				&& sIJManoidReceive.getpTypeManoid() == type_manoid.ASSHOLE;
		if (lIsVoraceAttaque || lIsGullibleAttaquedByVorace) {
			lOutcome = Math.round(lOutcome * pIJFight.getpIJGuiManager().getpRatioGlouton() / 100);
		}
		/*
		 * Answer
		 */
		return lOutcome;
	}

	/**
	 * 
	 */
	public final String toString() {
		return "Decay= " + pDecay
				+ "; MapInteract= " + pMapGiveVsReceiveToOutcome;
	}
	
	
	/*
	 * Getters & Setters
	 */
	public final int getpDecay() {
		return pDecay;
	}
	public final Map<type_interact, Map<type_interact, Integer>> getpMapGiveVsReceiveToOutcome() {
		return pMapGiveVsReceiveToOutcome;
	}

	
}
