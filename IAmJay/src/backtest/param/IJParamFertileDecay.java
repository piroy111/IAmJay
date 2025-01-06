package backtest.param;

public class IJParamFertileDecay extends IJParamAbstract {

	protected IJParamFertileDecay(String sNameParam, IJParamManager sIJParamManager) {
		super(sNameParam, sIJParamManager);
		// TODO Auto-generated constructor stub
	}

	@Override public void setParameter(int sIdxScenario) {
		pIJGuiManager.getpListIJGuiManoids().get(3).pParamWMain_Manoids_ValueChosen = pListValues.get(sIdxScenario);		
	}
}
