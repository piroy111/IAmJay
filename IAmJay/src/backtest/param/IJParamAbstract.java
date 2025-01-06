package backtest.param;

import java.util.ArrayList;
import java.util.List;

import gui.IJGuiManager;

public abstract class IJParamAbstract {

	protected IJParamAbstract(String sNameParam, IJParamManager sIJParamManager) {
		pNameParam = sNameParam;
		pIJParamManager = sIJParamManager;
		/*
		 * 
		 */
		pIJParamManager.declareNewParam(this);
		pIJGuiManager = pIJParamManager.getpIJBacktestManager().getpIJManager().getpIJGuiManager();
	}
	
	/*
	 * Abstract
	 */
	public abstract void setParameter(int _sIdxScenario);
	/*
	 * Data
	 */
	private IJParamManager pIJParamManager;
	protected IJGuiManager pIJGuiManager;
	private String pNameParam;
	protected List<Integer> pListValues;
	/*
	 * BACKTEST
	 */
	private List<Integer> pListValuesInFileCsv;
	
	
	/**
	 * 
	 */
	public final void initiateBeforeBacktest() {
		pListValuesInFileCsv = new ArrayList<>();
		pListValues = new ArrayList<>();
	}
	
	/**
	 * 
	 * @param sValue
	 */
	public final void addNewValueFromCSV(Integer sValue) {
		pListValuesInFileCsv.add(sValue);
	}
	
	public final void addNewValue(int sValue) {
		pListValues.add(sValue);
	}
	
	
	
	/*
	 * Getters & Setters
	 */
	public final String getpNameParam() {
		return pNameParam;
	}
	public final List<Integer> getpListValues() {
		return pListValues;
	}
	public final List<Integer> getpListValuesInFileCsv() {
		return pListValuesInFileCsv;
	}

	public final IJGuiManager getpIJGuiManager() {
		return pIJGuiManager;
	}
	
	
	
	
	
	
	
	
}
