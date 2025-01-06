package backtest.param;

public class IJParamRatioGlouton extends IJParamAbstract {

	protected IJParamRatioGlouton(String sNameParam, IJParamManager sIJParamManager) {
		super(sNameParam, sIJParamManager);
		// TODO Auto-generated constructor stub
	}

	@Override public void setParameter(int sIdxScenario) {
		pIJGuiManager.getpListIJGuiManoids().get(5).pParamWMain_Manoids_ValueChosen = pListValues.get(sIdxScenario);		
	}
	
	

}
