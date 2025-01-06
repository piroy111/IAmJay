package gui;

import java.awt.Color;

import background.guistatic.GUIStaticColor;
import basicmethods.BasicPrintMsg;
import deity.IJDeity;

public class IJGuiDiplomacy {

	public IJGuiDiplomacy(IJGuiManager _sIJGuiManager, IJDeity sIJDeity) {
		pIJDeity = sIJDeity;
		pIJGuiManager = _sIJGuiManager;
		pIJGuiManager.getmGUIManager().addNewRowInGUIFrame(this);
	}
	
	/*
	 * Parameter
	 */
	public int pParamWMain_Diplomacy_Ismene;
	public int pParamWMain_Diplomacy_Xetra;
	public int pParamWMain_Diplomacy_Netrenide;
	public int pParamWMain_Diplomacy_Antigone;
	public int pParamWMain_Diplomacy_Gamer;
	public int pParamWMain_Diplomacy_Thalys;
	/*
	 * Data
	 */
	private IJGuiManager pIJGuiManager;
	private IJDeity pIJDeity;
	
	/**
	 * 
	 * @param sIJDeityWith
	 * @return
	 */
	public final int getpIsDiplomacy(IJDeity sIJDeityWith) {
		switch (sIJDeityWith.getpName()) {
		case "Thalys": return pParamWMain_Diplomacy_Thalys;
		case "Netrenide": return pParamWMain_Diplomacy_Netrenide;
		case "Antigone": return pParamWMain_Diplomacy_Antigone;
		case "Ismene": return pParamWMain_Diplomacy_Ismene;
		case "Xetra": return pParamWMain_Diplomacy_Xetra;
		case "Gamer": return pParamWMain_Diplomacy_Gamer;
		default: BasicPrintMsg.errorCodeLogic(); return 0;
		}
	}
	
	
	/**
	 * 
	 * @param sNameDeityVersus
	 * @param sValue
	 */
	public final void setValue(String sNameDeityVersus, int sValue) {
		switch(sNameDeityVersus) {
		case "Gamer": 		pParamWMain_Diplomacy_Gamer = sValue; return;
		case "Antigone": 	pParamWMain_Diplomacy_Antigone = sValue; return;
		case "Ismene": 		pParamWMain_Diplomacy_Ismene = sValue; return;
		case "Thalys": 		pParamWMain_Diplomacy_Thalys = sValue; return;
		case "Netrenide": 	pParamWMain_Diplomacy_Netrenide = sValue; return;
		case "Xetra": 		pParamWMain_Diplomacy_Xetra = sValue; return;
		default: BasicPrintMsg.error("sNameDeityVersus= " + sNameDeityVersus);
		}
	}
	

	
	/*
	 * Display
	 */
	public final String pDisplayWMain_Diplomacy_Name() {
		return pIJDeity.getpName();
	}
	public final int pDisplayWMain_Diplomacy_Max_Diplomacy() {
		return pIJDeity.getpMaxLevelDipolomacy();
	}
	
	
	/*
	 * Colour
	 */
	public final Color pDisplayColorWMain_Diplomacy_Thalys() {
		return pParamWMain_Diplomacy_Thalys == 0 ? GUIStaticColor.getDARK_GREY() : GUIStaticColor.getTURQUOISE();
	}
	public final Color pDisplayColorWMain_Diplomacy_Netrenide() {
		return pParamWMain_Diplomacy_Netrenide == 0 ? GUIStaticColor.getDARK_GREY() : GUIStaticColor.getTURQUOISE();
	}
	public final Color pDisplayColorWMain_Diplomacy_Antigone() {
		return pParamWMain_Diplomacy_Antigone == 0 ? GUIStaticColor.getDARK_GREY() : GUIStaticColor.getTURQUOISE();
	}
	public final Color pDisplayColorWMain_Diplomacy_Ismene() {
		return pParamWMain_Diplomacy_Ismene == 0 ? GUIStaticColor.getDARK_GREY() : GUIStaticColor.getTURQUOISE();
	}
	public final Color pDisplayColorWMain_Diplomacy_Xetra() {
		return pParamWMain_Diplomacy_Xetra == 0 ? GUIStaticColor.getDARK_GREY() : GUIStaticColor.getTURQUOISE();
	}
	public final Color pDisplayColorWMain_Diplomacy_Gamer() {
		return pParamWMain_Diplomacy_Gamer == 0 ? GUIStaticColor.getDARK_GREY() : GUIStaticColor.getTURQUOISE();
	}
}
