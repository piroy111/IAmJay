package zz_launch_me;

import backtest.IJBacktestManager;
import constants.IJStatic;
import deity.IJDeityManager;
import gui.IJGuiManager;
import manoid.IJManoidManager;
import turn.IJFight;
import unit_tests.IJUTManager;

public class IJManager {

	public IJManager(IJGuiManager sIJGuiManager) {
		pIJGuiManager = sIJGuiManager;
		pIJDeityManager = new IJDeityManager(this);
		pIJManoidManager = new IJManoidManager(this);
		pIJFight = new IJFight(this);
		pIJBacktestManager = new IJBacktestManager(this);
		pIJUTManager = new IJUTManager(this);
	}
	
	/*
	 * Constants
	 */
	public static boolean IS_BACKTEST_MODE = true;
	/*
	 * Data
	 */
	private IJDeityManager pIJDeityManager;
	private IJManoidManager pIJManoidManager;
	private IJGuiManager pIJGuiManager;
	private IJFight pIJFight;
	private IJBacktestManager pIJBacktestManager;
	private IJUTManager pIJUTManager;
	
	/**
	 * 
	 */
	public final void instantiate() {
		IJStatic.createDirs();
		pIJDeityManager.instantiate();
		pIJManoidManager.instantiate();
		
	}
	
	/**
	 * 
	 */
	public final void initiate() {
		pIJUTManager.run();
	}

	/**
	 * 
	 */
	public final void refresh() {
		if (!pIJFight.getpIsLaunchManualOrAutomatic()) {
			pIJFight.refresh();
		}
	}
	
	/*
	 * Getters & Setters
	 */
	public final IJDeityManager getpIJDeityManager() {
		return pIJDeityManager;
	}
	public final IJManoidManager getpIJManoidManager() {
		return pIJManoidManager;
	}
	public final IJGuiManager getpIJGuiManager() {
		return pIJGuiManager;
	}
	public final IJFight getpIJFight() {
		return pIJFight;
	}
	public final IJBacktestManager getpIJBacktestManager() {
		return pIJBacktestManager;
	}
	public final IJUTManager getpIJUTManager() {
		return pIJUTManager;
	}
	
	
	
	
}
