package gui;

public class IJGuiRestart {

	public IJGuiRestart(IJGuiManager _sIJGuiManager) {
		pIJGuiManager = _sIJGuiManager;
		/*
		 * 
		 */
		_sIJGuiManager.getmGUIManager().addNewRowInGUIFrame(this);
	}
	
	/*
	 * Parameter
	 */
	public boolean pParamWMain_Restart_Initiate_Fight;
	public boolean pParamWMain_Restart_Run_Fight;
	public boolean pParamWMain_Restart_Run_next_round;
	public int pParamWMain_Restart_Pause_between_rounds_MS;
	public boolean pParamWMain_Restart_Reset_all_Scores;
	public boolean pParamWMain_Restart_Initiate_and_Run_Fight;
	
	/*
	 * Data
	 */
	private IJGuiManager pIJGuiManager;
	
	public final void refresh() {
		/*
		 * Case we restarted the instantiation
		 */
		if (pParamWMain_Restart_Initiate_Fight) {
			pIJGuiManager.getmGUIManager().forceParam("pParamWMain_Restart_Initiate_Fight", this, false);
			pIJGuiManager.getpIJManager().getpIJFight().initiate();
		}
		/*
		 * Case we started the fight
		 */
		if (pParamWMain_Restart_Run_Fight) {
			pIJGuiManager.getmGUIManager().forceParam("pParamWMain_Restart_Run_Fight", this, false);
			pIJGuiManager.getpIJManager().getpIJFight().start(false);
		}
		/*
		 * Case we run the next round
		 */
		if (pParamWMain_Restart_Run_next_round) {
			pIJGuiManager.getmGUIManager().forceParam("pParamWMain_Restart_Run_next_round", this, false);
			if (pIJGuiManager.getpIJManager().getpIJFight().getpIdxRound() > pIJGuiManager.getpNumberOfRounds()) {
				pIJGuiManager.getpIJManager().getpIJFight().initiate();
				pIJGuiManager.getpIJManager().getpIJFight().start(true);
			} else {
				pIJGuiManager.getpIJManager().getpIJFight().refresh();
			}
		}
		/*
		 * Case we initiate and start a fight
		 */
		if (pParamWMain_Restart_Initiate_and_Run_Fight) {
			pIJGuiManager.getmGUIManager().forceParam("pParamWMain_Restart_Initiate_and_Run_Fight", this, false);
			pIJGuiManager.getpIJManager().getpIJFight().initiate();
			pIJGuiManager.getpIJManager().getpIJFight().start(false);			
		}
		/*
		 * Case we reset all scores
		 */
		if (pParamWMain_Restart_Reset_all_Scores) {
			pIJGuiManager.getmGUIManager().forceParam("pParamWMain_Restart_Reset_all_Scores", this, false);
			pIJGuiManager.getpIJManager().getpIJDeityManager().resetScores();
		}
		
	}
	
	
	
}
