package backtest.param;

import deity.IJDeity;

public class IJParamDeityNbGullible extends IJParamAbstract {

	protected IJParamDeityNbGullible(String sNameParam, IJParamManager sIJParamManager, IJDeity sIJDeity) {
		super(sNameParam, sIJParamManager);
		pIJDeity = sIJDeity;
	}

	/*
	 * Data
	 */
	private IJDeity pIJDeity;
	
	
	@Override public void setParameter(int _sIdxScenario) {
		pIJDeity.getpIJGuiDeity().pParamWMain_Deities_Starting_Number_Guillibles = pListValues.get(_sIdxScenario);
		
	}

}
