package backtest.files;

import java.util.ArrayList;
import java.util.List;

import backtest.IJBacktestManager;
import backtest.param.IJParamAbstract;
import basicmethods.BasicDateInt;
import basicmethods.BasicFichiers;
import basicmethods.BasicTime;
import constants.IJStatic;
import deity.IJDeity;
import deity.IJDeityManager;

public class IJFileWriter {

	
	public IJFileWriter(IJBacktestManager sIJBacktestManager) {
		pIJBacktestManager = sIJBacktestManager;
	}
	
	/*
	 * Data
	 */
	private IJBacktestManager pIJBacktestManager;
	private List<String> pListLineToWrite;
	private String pHeader;
	
	/**
	 * reset list line to write + prepare file and write header
	 */
	public final void initiateBeforeBacktest() {
		pListLineToWrite = new ArrayList<>();
		pHeader = "";
		for (IJParamAbstract lIJParamAbstract : pIJBacktestManager.getpIJParamManager().getpListIJParamAbstract()) {
			pHeader += lIJParamAbstract.getpNameParam() + ",";
		}
		pHeader += "Gamer Gain,Opponent Gain,Highest other gain";
	}
	
	/**
	 * 
	 */
	public final void afterOneFight(int sIdxFight) {
		String lLine = "";
		/*
		 * Write parameter inputs
		 */
		for (IJParamAbstract lIJParamAbstract : pIJBacktestManager.getpIJParamManager().getpListIJParamAbstract()) {
			lLine += lIJParamAbstract.getpListValues().get(sIdxFight) + ",";
		}
		/*
		 * Compute output data
		 */
		IJDeityManager lIJDeityManager = pIJBacktestManager.getpIJManager().getpIJDeityManager();
		IJDeity lIJDeityGamer = lIJDeityManager.getpMapNameToIJDeity().get("Gamer");
		IJDeity lIJDeityOpponent = lIJDeityManager.getpMapNameToIJDeity().get("Antigone");
		int lMaxScore = 0;
		for (IJDeity lIJDeity : lIJDeityManager.getpListDeity()) {
			if (!lIJDeity.equals(lIJDeityGamer) && !lIJDeity.equals(lIJDeityOpponent)) {
				lMaxScore = Math.max(lMaxScore, lIJDeity.getpScore());
			}
		}
		/*
		 * Write output data
		 */
		lLine += lIJDeityGamer.getpScore();
		lLine += "," + lIJDeityOpponent.getpScore();
		lLine += "," + lMaxScore;
		pListLineToWrite.add(lLine);
	}
	
	
	/**
	 * 
	 */
	public final void writeFile() {
		String lDir = IJStatic.getDIR_BACKTEST_OUTPUT();
		String lNameFile = BasicDateInt.getmToday()
				+ "_" + BasicTime.getHeureTexteHHMMSSFromLong(BasicTime.getmNow()).replaceAll(":",  "_")
				+ IJStatic.getNAME_BACKTEST_OUTPUT();
		BasicFichiers.writeFile(this, lDir, lNameFile, pHeader, pListLineToWrite);
	}
	
	
	
}
