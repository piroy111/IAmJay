package gui;

import turn.IJDilemma.type_arena;
import turn.IJDilemma.type_interact;

public class IJGuiDilemma {

	public IJGuiDilemma(IJGuiManager _sIJGuiManager, type_arena sTypeArena, type_interact sTypeinteract) {
		pTypeArena = sTypeArena;
		pTypeInteract = sTypeinteract;
		/*
		 * 
		 */
		pKeyStr = getKey(pTypeArena, pTypeInteract);
		/*
		 * 
		 */
		_sIJGuiManager.getmGUIManager().addNewRowInGUIFrame(this);
	}
	
	/*
	 * Parameter
	 */
	public int pParamWMain_Dilemma_vs_Cooperate;
	public int pParamWMain_Dilemma_vs_Crook;

	
	/*
	 * Data
	 */
	private String pKeyStr;
	private type_arena pTypeArena;
	private type_interact pTypeInteract;

	public static String getKey(type_arena sTypeArena, type_interact sTypeinteract) {
		return sTypeArena + "_" + sTypeinteract;
	}
	
	
	/*
	 * Display
	 */
	public final String pDisplayWMain_Dilemma_Name() {
		return pKeyStr;
	}
	public final String pDisplayWMain_Dilemma_Type_arena() {
		return pTypeArena.toString();
	}
	public final String pDisplayWMain_Dilemma_Gamer() {
		return pTypeInteract.toString();
	}


	/*
	 * getters & Setters
	 */
	public final int getpParamWMain_Dilemma_vs_Cooperate() {
		return pParamWMain_Dilemma_vs_Cooperate;
	}
	public final int getpParamWMain_Dilemma_vs_Crook() {
		return pParamWMain_Dilemma_vs_Crook;
	}
	public final type_arena getpTypeArena() {
		return pTypeArena;
	}
	public final type_interact getpTypeInteract() {
		return pTypeInteract;
	}
	public final String getpKeyStr() {
		return pKeyStr;
	}
	
}
