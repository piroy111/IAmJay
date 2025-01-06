package backtest.files;

import java.util.ArrayList;
import java.util.List;

import backtest.IJBacktestManager;
import backtest.param.IJParamAbstract;
import basicmethods.AMNumberTools;
import basicmethods.BasicPrintMsg;
import basicmethods.ReadFile;
import basicmethods.ReadFile.comReadFile;
import constants.IJStatic;

public class IJFileReader {

	public IJFileReader(IJBacktestManager sIJBacktestManager) {
		pIJBacktestManager = sIJBacktestManager;
	}
	
	/*
	 * Data
	 */
	private IJBacktestManager pIJBacktestManager;
	private int pNbScenario;
	
	public final void run() {
		/*
		 * Read the file of parameters
		 */
		String lDir = IJStatic.getDIR_BACKTEST_INPUT_PARAM();
		String lNameFile = IJStatic.getNAME_BACKTEST_INPUT_PARAM();
		ReadFile lReadFile = new ReadFile(lDir, lNameFile, comReadFile.FULL_COM);
		/*
		 * Initiate the parameters by reading file
		 */
		int lIJSizeColumn = -1;
		int lIJSizeLine = lReadFile.getmContentList().size();
		for (List<String> lLineList : lReadFile.getmContentList()) {
			/*
			 * Load IJParam
			 */
			int lIdx = -1;
			String lName = lLineList.get(++lIdx);
			IJParamAbstract lIJParamAbstract = pIJBacktestManager.getpIJParamManager().getIJParam(lName);
			/*
			 * Check size
			 */
			if (lIJSizeColumn == -1) {
				lIJSizeColumn = lLineList.size() - 1;
			}
			/*
			 * Load values
			 */
			while (++lIdx < lLineList.size()) {
				String lWord = lLineList.get(lIdx);
				if (!lWord.equals("")) {
					lIJParamAbstract.addNewValueFromCSV(AMNumberTools.getInt(lWord));
				} else {
					lIJParamAbstract.addNewValueFromCSV(null);
				}
			}
		}
		/*
		 * Create columns
		 */
		List<List<Integer>> lListColumn = new ArrayList<>();
		for (int lIdx = 0; lIdx < lIJSizeColumn; lIdx++) {
			List<Integer> lColumn = new ArrayList<>();
			for (IJParamAbstract lIJParamAbstract : pIJBacktestManager.getpIJParamManager().getpListIJParamAbstract()) {
				lColumn.add(lIJParamAbstract.getpListValuesInFileCsv().get(lIdx));
			}
			lListColumn.add(lColumn);
		}		
		/*
		 * Initiate the loop over all possible scenarios
		 */
		BasicPrintMsg.display(this, "Compute scenari");
		List<Integer> lListValueTaken = new ArrayList<>();
		for (int lIdx = 0; lIdx < lIJSizeLine; lIdx++) {
			lListValueTaken.add(null);
		}
		List<Integer> lListIdxColumnTaken = new ArrayList<>();
		int lIdxColumnToAdd = 0;
		pNbScenario = 0;
		/*
		 * Loop over all columns to mix them so that we can create scenarios
		 */
		boolean lIsFinished = false;
		while (!lIsFinished) {
			/*
			 * Add the column differential
			 */
			
			List<Integer> lColumn = lListColumn.get(lIdxColumnToAdd);
			/*
			 * Check if the differential column is compatible --> not twice the same value and all values filled
			 */
			boolean lIsCompatible = true;
			for (int lIdxLine = 0; lIdxLine < lIJSizeLine; lIdxLine++) {
				Integer lValueSoFar = lListValueTaken.get(lIdxLine);
				Integer lValueDifferential = lColumn.get(lIdxLine);
				if (lValueSoFar != null && lValueDifferential != null) {
					lIsCompatible = false;
					break;
				}
			}
			/*
			 * 
			 */
			boolean lIsNeedRemoveLastColumn = false;
			/*
			 * If the column we want to add is not compatible, then we move to the next column
			 */
			if (!lIsCompatible) {
				lIdxColumnToAdd++;
				if (lIdxColumnToAdd >= lIJSizeColumn) {
					lIsNeedRemoveLastColumn = true;
				}
			}
			/*
			 * If it is compatible, then we add the column to the existing set and we check for completion
			 */
			else {
				lListIdxColumnTaken.add(lIdxColumnToAdd);
				boolean lIsCompleted = true;				
				for (int lIdxLine = 0; lIdxLine < lIJSizeLine; lIdxLine++) {
					Integer lValueSoFar = lListValueTaken.get(lIdxLine);
					Integer lValueDifferential = lColumn.get(lIdxLine);
					if (lValueDifferential != null) {
						lListValueTaken.set(lIdxLine, lValueDifferential);
					} else if (lValueSoFar == null) {
						lIsCompleted = false;
					}
				}
				/*
				 * Case we completed a set of columns --> we store the result as a new scenario and we prepare the next search
				 */
				if (lIsCompleted) {
					/*
					 * Store the scenario
					 */
					for (int lIdxLine = 0; lIdxLine < lIJSizeLine; lIdxLine++) {
						IJParamAbstract lIJParamAbstract = pIJBacktestManager.getpIJParamManager().getpListIJParamAbstract().get(lIdxLine);
						lIJParamAbstract.addNewValue(lListValueTaken.get(lIdxLine));
					}
					pNbScenario++;
					/*
					 * Communication
					 */
					String lMsg = "New scenario found= [";
					for (int lIdx = 0; lIdx < lListValueTaken.size(); lIdx++) {
						if (lIdx > 0) {
							lMsg += ", ";
						}
						lMsg += lListValueTaken.get(lIdx);
					}
					lMsg += "]";
					System.out.println(lMsg);
					lMsg = "";
					/*
					 * 
					 */
					lIsNeedRemoveLastColumn = true;
				}
			}
			/*
			 * Withdraw effect of the last column until we can increase to a new one
			 */
			if (lIsNeedRemoveLastColumn) {
				while (true) {
					int lIdxLastColumn = lListIdxColumnTaken.get(lListIdxColumnTaken.size() - 1);
					/*
					 * Withdraw last column from the IDX stored
					 */
					lListIdxColumnTaken.remove(lListIdxColumnTaken.size() - 1);
					/*
					 * Withdraw effect of last column from values stored
					 */
					List<Integer> lColumnToWithdraw = lListColumn.get(lIdxLastColumn);
					for (int lIdxLine = 0; lIdxLine < lIJSizeLine; lIdxLine++) {
						if (lColumnToWithdraw.get(lIdxLine) != null) {
							lListValueTaken.set(lIdxLine, null);
						}
					}
					/*
					 * Case the whole search is finished
					 */
					if (lIdxLastColumn >= lIJSizeColumn - 1 && lListIdxColumnTaken.size() == 0) {
						lIsFinished = true;
						break;
					}
					/*
					 * Case that we move on to the next column
					 */
					else if (lIdxLastColumn < lIJSizeColumn - 1) {
						lIdxColumnToAdd = lIdxLastColumn + 1;							
						break;
					}
				}
			}
		}
		/*
		 * Communication
		 */
		System.out.println();
		System.out.println("Nb scenarios found= " + pNbScenario);
	}

	/*
	 * Getters & Setters
	 */
	public final int getpNbScenario() {
		return pNbScenario;
	}
	
	
}
