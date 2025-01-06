package backtest.param;

public class IJParamTypeArena extends IJParamAbstract {

	protected IJParamTypeArena(String sNameParam, IJParamManager sIJParamManager) {
		super(sNameParam, sIJParamManager);
		// TODO Auto-generated constructor stub
	}

	@Override public void setParameter(int sIdxScenario) {
		pIJGuiManager.getpListIJGuiManoids().get(4).pParamWMain_Manoids_ValueChosen = pListValues.get(sIdxScenario);		
	}
	
}
