package backtest.param;

import basicmethods.BasicPrintMsg;
import gui.IJGuiDilemma;
import turn.IJDilemma.type_arena;
import turn.IJDilemma.type_interact;

public class IJPAramDilemma extends IJParamAbstract {

	protected IJPAramDilemma(String sNameParam, IJParamManager sIJParamManager, type_arena sTypeArena, 
			type_interact sTypeInteractGiver, type_interact sTypeInteractReceiver) {
		super(sNameParam, sIJParamManager);
		pTypeArena = sTypeArena;
		pTypeInteractGiver = sTypeInteractGiver;
		pTypeInteractReceiver = sTypeInteractReceiver;
	}

	/*
	 * Data
	 */
	private type_arena pTypeArena;
	private type_interact pTypeInteractGiver;
	private type_interact pTypeInteractReceiver;

	/**
	 * 
	 * @param sTypeArena
	 * @param sTypeInteractGiver
	 * @param sTypeInteractReceiver
	 * @return
	 */
	public static String getName(type_arena sTypeArena, 
			type_interact sTypeInteractGiver, type_interact sTypeInteractReceiver) {
		return "Dilemma"
				+ "." + sTypeArena
				+ "." + sTypeInteractGiver
				+ "." + sTypeInteractReceiver;
	}
	
	/**
	 * 
	 */
	@Override public void setParameter(int _sIdxScenario) {
		IJGuiDilemma lIJGuiDilemma = pIJGuiManager.getpIJGuiDilemmaParam(pTypeArena, pTypeInteractGiver);
		if (pTypeInteractReceiver == type_interact.COOPERATE) {
			lIJGuiDilemma.pParamWMain_Dilemma_vs_Cooperate = pListValues.get(_sIdxScenario);
		} else if (pTypeInteractReceiver == type_interact.CROOK) {
			lIJGuiDilemma.pParamWMain_Dilemma_vs_Crook = pListValues.get(_sIdxScenario);
		} else {
			BasicPrintMsg.errorCodeLogic();
		}
	}

}
