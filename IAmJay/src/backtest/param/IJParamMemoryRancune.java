package backtest.param;

public class IJParamMemoryRancune extends IJParamAbstract {

	protected IJParamMemoryRancune(String sNameParam, IJParamManager sIJParamManager) {
		super(sNameParam, sIJParamManager);
	}

	@Override public void setParameter(int sIdxScenario) {
		pIJGuiManager.getpListIJGuiManoids().get(6).pParamWMain_Manoids_ValueChosen = pListValues.get(sIdxScenario);		
	}

}
