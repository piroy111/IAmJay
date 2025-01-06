package deity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import basicmethods.BasicPrintMsg;
import manoid.IJManoid;
import manoid.IJManoidManager.type_manoid;
import zz_launch_me.IJManager;

public class IJDeityManager {

	public IJDeityManager(IJManager _sIJManager) {
		pIJManager = _sIJManager;
	}

	/*
	 * Data
	 */
	private IJManager pIJManager;
	private List<IJDeity> pListDeity;
	private Map<String, IJDeity> pMapNameToIJDeity;

	/**
	 * 
	 */
	public final void instantiate() {
		pListDeity = new ArrayList<>();
		pMapNameToIJDeity = new HashMap<>();
		/*
		 * 
		 */
		getpOrCreateIJDeity("Thalys");
		getpOrCreateIJDeity("Netrenide");
		getpOrCreateIJDeity("Antigone");
		getpOrCreateIJDeity("Ismene");
		getpOrCreateIJDeity("Xetra");
		getpOrCreateIJDeity("Gamer");
	}

	/**
	 * 
	 */
	public final void initiate() {
		/*
		 * Reset Deity
		 */
		for (int lIdx = 0; lIdx < pListDeity.size(); lIdx++) {
			IJDeity lIJDeity = pListDeity.get(lIdx);
			lIJDeity.initiate();
		}
		/*
		 * Initiate DeitiesThalys
		 */
		for (int lIdx = 0; lIdx < pListDeity.size(); lIdx++) {
			IJDeity lIJDeity = pListDeity.get(lIdx);
			/*
			 * Initiate level diplomacy: Diagonal
			 */
			lIJDeity.setpMaxLevelDipolomacy(3);
			lIJDeity.getpMapIJDeityToLevelDiplomacy().put(lIJDeity, lIJDeity.getpMaxLevelDipolomacy());
			/*
			 * Initiate level diplomacy: Interactions
			 */
			for (int lKdx = lIdx + 1; lKdx < pListDeity.size(); lKdx++) {
				IJDeity lIJDeity2 = pListDeity.get(lKdx);
				lIJDeity.getpMapIJDeityToLevelDiplomacy().put(lIJDeity2, lIJDeity.getpDiplomacyWith(lIJDeity2));
				lIJDeity2.getpMapIJDeityToLevelDiplomacy().put(lIJDeity, lIJDeity2.getpDiplomacyWith(lIJDeity));
			}
			/*
			 * Create new MANOIDS
			 */
			if (!pIJManager.getpIJUTManager().getpIsUnitTestRunning()) {
				for (int lKdx = 0; lKdx < lIJDeity.getpIJGuiDeity().pParamWMain_Deities_Starting_Number_Guillibles; lKdx++) {
					IJManoid lIJManoid = lIJDeity.createIJManoid(type_manoid.GULLIBLE);
					lIJManoid.placeManoidInGrid();
				}
				for (int lKdx = 0; lKdx < lIJDeity.getpIJGuiDeity().pParamWMain_Deities_Starting_Number_Assholes; lKdx++) {
					IJManoid lIJManoid = lIJDeity.createIJManoid(type_manoid.ASSHOLE);
					lIJManoid.placeManoidInGrid();
				}
			}
		}
		/*
		 * Case of unit tests --> we create the MANOIDS not randomly but according to the grid given byt the CSV unit test file
		 */
		if (pIJManager.getpIJUTManager().getpIsUnitTestRunning()) {
			pIJManager.getpIJUTManager().createManoids();
		}
	}

	/**
	 * 
	 * @param sName
	 * @return
	 */
	public final IJDeity getpOrCreateIJDeity(String sName) {
		IJDeity lIJDeity = pMapNameToIJDeity.get(sName);
		if (lIJDeity == null) {
			lIJDeity = new IJDeity(this, sName);
			pMapNameToIJDeity.put(sName, lIJDeity);
			pListDeity.add(lIJDeity);
		}
		return lIJDeity;
	}

	/**
	 * 
	 * @param sSender
	 * @param sName
	 * @return
	 */
	public final IJDeity getpOrErrorIJDeity(Object sSender, String sName) {
		IJDeity lIJDeity = pMapNameToIJDeity.get(sName);
		if (lIJDeity == null) {
			String lMsg = "The name of the deity does not exist"
					+ "\nsName= " + sName
					+ "\nSender= " + sSender.getClass().getSimpleName()
					+ "\nList of existing IJDeity names= " + pMapNameToIJDeity.keySet();
			BasicPrintMsg.error(lMsg);
			return null;
		}
		return lIJDeity;
	}

	/**
	 * 
	 */
	public final void resetScores() {
		for (IJDeity lIJDeity : pListDeity) {
			lIJDeity.setpScore(0);
		}
	}

	/*
	 * Getters & Setters
	 */
	public final IJManager getpIJManager() {
		return pIJManager;
	}
	public final List<IJDeity> getpListDeity() {
		return pListDeity;
	}
	public final Map<String, IJDeity> getpMapNameToIJDeity() {
		return pMapNameToIJDeity;
	}


}
