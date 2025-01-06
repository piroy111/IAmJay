package gui;

import java.awt.Color;

import background.guistatic.GUIStaticColor;
import deity.IJDeity;

public class IJGuiDeity {

	public IJGuiDeity(IJGuiManager _sIJGuiManager, IJDeity sIJDeity) {
		pIJDeity = sIJDeity;
		_sIJGuiManager.getmGUIManager().addNewRowInGUIFrame(this);
	}
	
	/*
	 * Parameter
	 */
	public int pParamWMain_Deities_Starting_Number_Guillibles;
	public int pParamWMain_Deities_Starting_Number_Assholes;
	/*
	 * Parameter Technology
	 */
	public boolean pParamWMain_Deities_Techno_Asshole_Move;
	public boolean pParamWMain_Deities_Techno_Asshole_RadarACretins;
	public boolean pParamWMain_Deities_Techno_Asshole_Teleportation;
	public boolean pParamWMain_Deities_Techno_Asshole_Vorace;
	public boolean pParamWMain_Deities_Techno_Gullible_Rancune;
	public boolean pParamWMain_Deities_Techno_Gullible_Lanceur_dAlerte;
	public boolean pParamWMain_Deities_Techno_Gullible_Memoire_dElephant;
	public boolean pParamWMain_Deities_Techno_Gullible_Immunite_dAlliance;
	/*
	 * Data
	 */
	private IJDeity pIJDeity;
	
	
	/**
	 * 
	 */
	public final void refresh() {
		/*
		 * Pass parameters
		 */
		pIJDeity.setpIsTechnoA01Move(pParamWMain_Deities_Techno_Asshole_Move);
		pIJDeity.setpIsTechnoA02RadarACretins(pParamWMain_Deities_Techno_Asshole_RadarACretins);
		pIJDeity.setpIsTechnoA03Vorace(pParamWMain_Deities_Techno_Asshole_Vorace);
		pIJDeity.setpIsTechnoA02Teleportation(pParamWMain_Deities_Techno_Asshole_Teleportation);
		pIJDeity.setpIsTechnoG01Rancune(pParamWMain_Deities_Techno_Gullible_Rancune);
		pIJDeity.setpIsTechnoG02LanceurAlerte(pParamWMain_Deities_Techno_Gullible_Lanceur_dAlerte);
		pIJDeity.setpIsTechnoG02MemoireDelephant(pParamWMain_Deities_Techno_Gullible_Memoire_dElephant);
		pIJDeity.setpIsTechnoG03ImmuniteAlliance(pParamWMain_Deities_Techno_Gullible_Immunite_dAlliance);
	}	
	
	
	/*
	 * Display
	 */
	public final String pDisplayWMain_Deities_Name() {
		return pIJDeity.getpName();
	}
	public final int pDisplayWMain_Deities_Score() {
		return pIJDeity.getpScore();
	}
	public final boolean pDisplayWMain_Deities_Is_Techno_Asshole_Move() {
		return pIJDeity.getpIsTechnoA01Move();
	}
	public final boolean pDisplayWMain_Deities_Is_Techno_Asshole_RadarACretins() {
		return pIJDeity.getpIsTechnoA02RadarACretins();
	}
	public final boolean pDisplayWMain_Deities_Is_Techno_Asshole_Vorace() {
		return pIJDeity.getpIsTechnoA03Vorace();
	}
	public final boolean pDisplayWMain_Deities_Is_Techno_Gullible_Rancune() {
		return pIJDeity.getpIsTechnoG01Rancune();
	}
	public final boolean pDisplayWMain_Deities_Is_Techno_Gullible_Lanceur_dAlerte() {
		return pIJDeity.getpIsTechnoG02LanceurAlerte();
	}
	public final boolean pDisplayWMain_Deities_Is_Techno_Gullible_ImmuniteAlliance() {
		return pIJDeity.getpIsTechnoG03ImmuniteAlliance();
	}
	/*
	 * Colour
	 */
	public final Color pDisplayColorWMain_Deities_Is_Techno_Asshole_Move() {
		return pDisplayWMain_Deities_Is_Techno_Asshole_Move() ? GUIStaticColor.getTURQUOISE() : GUIStaticColor.getDARK_GREY();
	}
	public final Color pDisplayColorWMain_Deities_Is_Techno_Asshole_RadarACretins() {
		return pDisplayWMain_Deities_Is_Techno_Asshole_RadarACretins() ? GUIStaticColor.getTURQUOISE() : GUIStaticColor.getDARK_GREY();
	}
	public final Color pDisplayColorWMain_Deities_Is_Techno_Asshole_Vorace() {
		return pDisplayWMain_Deities_Is_Techno_Asshole_Vorace() ? GUIStaticColor.getTURQUOISE() : GUIStaticColor.getDARK_GREY();
	}
	public final Color pDisplayColorWMain_Deities_Is_Techno_Gullible_Rancune() {
		return pDisplayWMain_Deities_Is_Techno_Gullible_Rancune() ? GUIStaticColor.getTURQUOISE() : GUIStaticColor.getDARK_GREY();
	}
	public final Color pDisplayColorWMain_Deities_Is_Techno_Gullible_Lanceur_dAlerte() {
		return pDisplayWMain_Deities_Is_Techno_Gullible_Lanceur_dAlerte() ? GUIStaticColor.getTURQUOISE() : GUIStaticColor.getDARK_GREY();
	}
	public final Color pDisplayColorWMain_Deities_Is_Techno_Gullible_ImmuniteAlliance() {
		return pDisplayWMain_Deities_Is_Techno_Gullible_ImmuniteAlliance() ? GUIStaticColor.getTURQUOISE() : GUIStaticColor.getDARK_GREY();
	}
	
	
}
