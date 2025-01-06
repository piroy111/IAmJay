package backtest.param;

public class IJParamMemoryLanceurDAlerte extends IJParamAbstract {

	protected IJParamMemoryLanceurDAlerte(String sNameParam, IJParamManager sIJParamManager) {
		super(sNameParam, sIJParamManager);
	}

	@Override public void setParameter(int sIdxScenario) {
		pIJGuiManager.getpListIJGuiManoids().get(7).pParamWMain_Manoids_ValueChosen = pListValues.get(sIdxScenario);		
	}

}
