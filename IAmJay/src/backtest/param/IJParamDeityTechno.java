package backtest.param;

import deity.IJDeity;

public class IJParamDeityTechno extends IJParamAbstract {

	protected IJParamDeityTechno(String sNameParam, IJParamManager sIJParamManager, IJDeity sIJDeity, String sTechno) {
		super(sNameParam, sIJParamManager);
		pIJDeity = sIJDeity;
		pTechno = sTechno;
	}

	/*
	 * Data
	 */
	private IJDeity pIJDeity;
	private String pTechno;
	
	
	@Override public void setParameter(int _sIdxScenario) {
		switch (pTechno) {
		case "A01": pIJDeity.setpIsTechnoA01Move(pListValues.get(_sIdxScenario) == 1);
		case "A02": pIJDeity.setpIsTechnoA02RadarACretins(pListValues.get(_sIdxScenario) == 1);
		case "A02b": pIJDeity.setpIsTechnoA02Teleportation(pListValues.get(_sIdxScenario) == 1);
		case "A03": pIJDeity.setpIsTechnoA03Vorace(pListValues.get(_sIdxScenario) == 1);
		case "H01": pIJDeity.setpIsTechnoG01Rancune(pListValues.get(_sIdxScenario) == 1);
		case "H02": pIJDeity.setpIsTechnoG02LanceurAlerte(pListValues.get(_sIdxScenario) == 1);
		case "H02b": pIJDeity.setpIsTechnoG02MemoireDelephant(pListValues.get(_sIdxScenario) == 1);
		case "H03": pIJDeity.setpIsTechnoG03ImmuniteAlliance(pListValues.get(_sIdxScenario) == 1);
		}
		
	}

}
