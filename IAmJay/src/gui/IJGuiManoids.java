package gui;

public class IJGuiManoids {

	public IJGuiManoids(IJGuiManager _sIJGuiManager, String sTitleInstruction) {
		pTitle = sTitleInstruction;
		_sIJGuiManager.getmGUIManager().addNewRowInGUIFrame(this);
	}
	
	/*
	 * Parameter
	 */
	public int pParamWMain_Manoids_ValueChosen;
	/*
	 * Data
	 */
	private String pTitle;
	
	
	
	/*
	 * Display
	 */
	public final String pDisplayWMain_Manoids_Name() {
		return pTitle;
	}
	
}
