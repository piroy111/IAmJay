package backtest;

import backtest.files.IJFileReader;
import backtest.files.IJFileWriter;
import backtest.param.IJParamManager;
import basicmethods.BasicPrintMsg;
import turn.IJFight;
import zz_launch_me.IJManager;

public class IJBacktestManager {

	public IJBacktestManager(IJManager sIJManager) {
		pIJManager = sIJManager;
		/*
		 * 
		 */
		instantiate();
	}

	/*
	 * Data
	 */
	private IJManager pIJManager;
	private IJFileReader pIJFileReader;
	private IJParamManager pIJParamManager;
	private IJFileWriter pIJFileWriter;
	private boolean pIsRunning;

	/**
	 * 
	 */
	public final void instantiate() {
		pIJFileReader = new IJFileReader(this);
		pIJParamManager = new IJParamManager(this);
		pIJFileWriter = new IJFileWriter(this);
	}

	/**
	 * 
	 */
	public final void initiate() {
		BasicPrintMsg.displaySuperTitle(this, "Backtester");
		BasicPrintMsg.displayTitle(this, "Initiating backtester");
		pIsRunning = true;
		/*
		 * Reset lists
		 */
		pIJParamManager.createIJParam();
		pIJParamManager.initiateBeforeBacktest();
		/*
		 * Read file input and prepare the scenarios
		 */
		pIJFileReader.run();
		/*
		 * Prepare the file output
		 */
		pIJFileWriter.initiateBeforeBacktest();
	}

	/**
	 * 
	 */
	public final void refresh() {
		BasicPrintMsg.displayTitle(this, "Running backtester");
		/*
		 * Run scenario
		 */
		for (int lIdxScenario = 0; lIdxScenario < pIJFileReader.getpNbScenario(); lIdxScenario++) {
			/*
			 * Communication
			 */
			System.out.println("Scenario " + (lIdxScenario + 1) + " / " + pIJFileReader.getpNbScenario());
			/*
			 * Force values of parameters for this fight following the input file
			 */
			pIJParamManager.setParameters(lIdxScenario);
			/*
			 * Initiate the fight
			 */
			IJFight lIJFight = pIJManager.getpIJFight();
			lIJFight.initiate();
			lIJFight.start(false);
			/*
			 * Reset Scores
			 */
			pIJManager.getpIJDeityManager().resetScores();
			/*
			 * Run Fight
			 */
			while (lIJFight.getpIdxRound() <= pIJManager.getpIJGuiManager().getpNumberOfRounds()) {
				lIJFight.refresh();
			}
			/*
			 * Store result in the output file
			 */
			pIJFileWriter.afterOneFight(lIdxScenario);
		}
		/*
		 * Write output file
		 */
		pIJFileWriter.writeFile();
		/*
		 * Exit
		 */
		pIsRunning = false;
	}

	/*
	 * Getters & Setters
	 */
	public final IJFileReader getpIJFileReader() {
		return pIJFileReader;
	}
	public final IJParamManager getpIJParamManager() {
		return pIJParamManager;
	}
	public final boolean getpIsRunning() {
		return pIsRunning;
	}
	public final IJManager getpIJManager() {
		return pIJManager;
	}
	public final IJFileWriter getpIJFileWriter() {
		return pIJFileWriter;
	}
	
	
}
