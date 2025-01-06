package backtest.param;

import basicmethods.BasicPrintMsg;
import deity.IJDeity;

public class IJParamDiplomacy extends IJParamAbstract {

	protected IJParamDiplomacy(String sNameParam, IJParamManager sIJParamManager, IJDeity sIJDeityWith) {
		super(sNameParam, sIJParamManager);
		pIJDeityWith = sIJDeityWith;
		/*
		 * 
		 */
		pIJDeityGamer = pIJGuiManager.getpIJManager().getpIJDeityManager().getpMapNameToIJDeity().get("Gamer");
	}

	/*
	 * Data
	 */
	private IJDeity pIJDeityGamer;
	private IJDeity pIJDeityWith;
	
	
	@Override public void setParameter(int _sIdxScenario) {
		setDiplomacy(pIJDeityGamer, pIJDeityWith, pListValues.get(_sIdxScenario));
		setDiplomacy(pIJDeityWith, pIJDeityGamer, pListValues.get(_sIdxScenario));
	}
	
	
	private void setDiplomacy(IJDeity sIJDeityFrom, IJDeity sIJDeityWith, int sDiplomacyValue) {
		switch (sIJDeityWith.getpName()) {
		case "Thalys": sIJDeityFrom.getpIJGuiDiplomacy().pParamWMain_Diplomacy_Thalys = sDiplomacyValue; break;
		case "Netrenide": sIJDeityFrom.getpIJGuiDiplomacy().pParamWMain_Diplomacy_Netrenide = sDiplomacyValue; break;
		case "Antigone": sIJDeityFrom.getpIJGuiDiplomacy().pParamWMain_Diplomacy_Antigone = sDiplomacyValue; break;
		case "Ismene": sIJDeityFrom.getpIJGuiDiplomacy().pParamWMain_Diplomacy_Ismene = sDiplomacyValue; break;
		case "Xetra": sIJDeityFrom.getpIJGuiDiplomacy().pParamWMain_Diplomacy_Xetra = sDiplomacyValue; break;
		case "Gamer": sIJDeityFrom.getpIJGuiDiplomacy().pParamWMain_Diplomacy_Gamer = sDiplomacyValue; break;
		default: BasicPrintMsg.errorCodeLogic();
		}
	}
}
