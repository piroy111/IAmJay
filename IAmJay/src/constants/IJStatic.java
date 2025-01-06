package constants;

import basicmethods.BasicFichiers;

public class IJStatic {

	
	/*
	 * Directory
	 */
	private static String DIR_ROOT = "C:/I am Jay/";
	private static String DIR_BACKTEST_INPUT_PARAM = DIR_ROOT + "Backtester/Input_params/";
	private static String DIR_BACKTEST_OUTPUT = DIR_ROOT + "Backtester/Output/";
	private static String DIR_UNIT_TESTS = DIR_ROOT + "Unit tests/";
	private static String DIR_UNIT_TESTS_EXPORT_ARENA = DIR_ROOT + "Unit tests - export/";
	/*
	 * File name
	 */
	private static String NAME_BACKTEST_INPUT_PARAM = "input_param.csv";
	private static String NAME_BACKTEST_OUTPUT = "_output.csv";
	private static String UT_ARENA = "UnitTest_Arena_";
	private static String UT_PARAMETERS = "UnitTest_Parameters.csv";
	private static String UT_EXPORT_ARENA = "_Export_Arena.csv";
	private static String UT_SCORES = "UnitTest_Deities_Scores.csv";
	
	/**
	 * 
	 */
	public static void createDirs() {
		BasicFichiers.getOrCreateDirectory(DIR_BACKTEST_INPUT_PARAM);
		BasicFichiers.getOrCreateDirectory(DIR_BACKTEST_OUTPUT);
		BasicFichiers.getOrCreateDirectory(DIR_UNIT_TESTS);
	}
	
	/*
	 * Getters & Setters
	 */
	public static final String getDIR_BACKTEST_INPUT_PARAM() {
		return DIR_BACKTEST_INPUT_PARAM;
	}
	public static final String getDIR_BACKTEST_OUTPUT() {
		return DIR_BACKTEST_OUTPUT;
	}
	public static final String getNAME_BACKTEST_INPUT_PARAM() {
		return NAME_BACKTEST_INPUT_PARAM;
	}
	public static final String getNAME_BACKTEST_OUTPUT() {
		return NAME_BACKTEST_OUTPUT;
	}
	public static final String getDIR_UNIT_TESTS() {
		return DIR_UNIT_TESTS;
	}
	public static final String getUT_ARENA() {
		return UT_ARENA;
	}
	public static final String getUT_PARAMETERS() {
		return UT_PARAMETERS;
	}
	public static final String getDIR_ROOT() {
		return DIR_ROOT;
	}
	public static final String getDIR_UNIT_TESTS_EXPORT_ARENA() {
		return DIR_UNIT_TESTS_EXPORT_ARENA;
	}
	public static final String getUT_EXPORT_ARENA() {
		return UT_EXPORT_ARENA;
	}
	public static final String getUT_SCORES() {
		return UT_SCORES;
	}
	
	
}
