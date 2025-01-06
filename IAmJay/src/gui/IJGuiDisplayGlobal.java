package gui;

public class IJGuiDisplayGlobal {

	public IJGuiDisplayGlobal(IJGuiManager sIJGuiManager, String sTitle) {
		pIJGuiManager = sIJGuiManager;
		pTitle = sTitle;
		/*
		 * 
		 */
		pValue = "-";
		pIJGuiManager.getmGUIManager().addNewRowInGUIFrame(this);
	}
	
	
	/*
	 * Data
	 */
	private IJGuiManager pIJGuiManager;
	private String pTitle;
	private String pValue;
	
	
	/*
	 * Display
	 */
	public final String pDisplayWMain_DisplayGlobal_Title() {
		return pTitle;
	}
	public final String pDisplayWMain_DisplayGlobal_Value() {
		return pValue;
	}
	
	
	/*
	 * Getters & Setters
	 */
	public final void setpValue(String pValue) {
		this.pValue = pValue;
	}
}
