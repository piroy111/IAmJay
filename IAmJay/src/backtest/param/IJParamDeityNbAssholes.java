package backtest.param;

import deity.IJDeity;

public class IJParamDeityNbAssholes extends IJParamAbstract {

	protected IJParamDeityNbAssholes(String sNameParam, IJParamManager sIJParamManager, IJDeity sIJDeity) {
		super(sNameParam, sIJParamManager);
		pIJDeity = sIJDeity;
	}

	/*
	 * Data
	 */
	private IJDeity pIJDeity;
	
	
	@Override public void setParameter(int _sIdxScenario) {
		pIJDeity.getpIJGuiDeity().pParamWMain_Deities_Starting_Number_Assholes = pListValues.get(_sIdxScenario);
		
	}

}
