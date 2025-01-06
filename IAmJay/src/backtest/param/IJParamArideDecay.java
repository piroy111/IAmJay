package backtest.param;

public class IJParamArideDecay extends IJParamAbstract {

	protected IJParamArideDecay(String sNameParam, IJParamManager sIJParamManager) {
		super(sNameParam, sIJParamManager);
		// TODO Auto-generated constructor stub
	}

	@Override public void setParameter(int sIdxScenario) {
		pIJGuiManager.getpListIJGuiManoids().get(2).pParamWMain_Manoids_ValueChosen = pListValues.get(sIdxScenario);		
	}



}
