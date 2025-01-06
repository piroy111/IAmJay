package unit_tests;

import java.util.HashMap;
import java.util.Map;

import basicmethods.BasicPrintMsg;
import deity.IJDeity;

public class IJUTDeity {

	protected IJUTDeity(IJUTManager sIJUTManager, IJDeity sIJDeity) {
		pIJUTManager = sIJUTManager;
		pIJDeity = sIJDeity;
	}
	
	/*
	 * Data
	 */
	private IJUTManager pIJUTManager;
	private IJDeity pIJDeity;
	private Map<Integer, Integer> pMapIdxRoundToScore;
	
	/**
	 * 
	 */
	public final void initiate() {
		pMapIdxRoundToScore = new HashMap<>();
	}
	
	/**
	 * 
	 * @param sIdxRound
	 * @param sScore
	 */
	public final void addNewScore(int sIdxRound, int sScore) {
		if (pMapIdxRoundToScore.containsKey(sIdxRound)) {
			BasicPrintMsg.errorCodeLogic();
		}
		pMapIdxRoundToScore.put(sIdxRound, sScore);
	}
	
	
	/*
	 * Getters & Setters
	 */
	public final IJUTManager getpIJUTManager() {
		return pIJUTManager;
	}
	public final IJDeity getpIJDeity() {
		return pIJDeity;
	}
	public final Map<Integer, Integer> getpMapIdxRoundToScore() {
		return pMapIdxRoundToScore;
	}
	
	
	
	
	
	
	
}
