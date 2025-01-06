package manoid;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import deity.IJDeity;
import gui.IJGridCell;
import gui.IJGuiManager;
import manoid.IJManoidManager.type_manoid;
import turn.IJDilemma.type_interact;

public class IJManoid {

	protected IJManoid(IJManoidManager sIJManoidManager, IJDeity sIJDeity, type_manoid  sTypeManoid) {
		pIJManoidManager = sIJManoidManager;
		pIJDeity = sIJDeity;	
		pTypeManoid = sTypeManoid;
		/*
		 * 
		 */
		pIJGuiManager = pIJManoidManager.getpIJManager().getpIJGuiManager();
	}

	/*
	 * Data
	 */
	private IJManoidManager pIJManoidManager;
	private IJGuiManager pIJGuiManager;
	private IJDeity pIJDeity;
	private type_manoid pTypeManoid;
	private int pLife;
	private IJGridCell pIJGridCell;
	private int pGainInRound;
	/*
	 * Technology
	 */
	private Map<IJManoid, Integer> pMapIJManoidRancuneToCountDownRancune;
	private Set<IJManoid> pSetIJManoidRancuneToAddBuffer;

	/*
	 * 
	 */
	public final void initiate() {
		pLife = pIJGuiManager.getpInitialManoidLife();
		pMapIJManoidRancuneToCountDownRancune = new HashMap<>();
		pSetIJManoidRancuneToAddBuffer = new HashSet<>();
	}

	/**
	 * 
	 */
	public final void placeManoidInGrid() {
		pIJGridCell = pIJGuiManager.getpIJManager().getpIJFight().getpIJArena().allocatePlaceInGrid(this);
	}

	/**
	 * 
	 * @param sX
	 * @param sY
	 */	
	public final void placeManoidInGrid(int sX, int sY) {
		pIJGridCell = pIJGuiManager.getpIJManager().getpIJFight().getpIJArena().allocatePlaceInGrid(this, sX, sY);
	}
	
	/**
	 * 
	 * @param sIJManoid
	 */
	public final type_interact interact(IJManoid sIJManoidNeighbour) {
		if (pTypeManoid == type_manoid.GULLIBLE) {
			/*
			 * Technology -> case of RANCUNE
			 */
			if (sIJManoidNeighbour.getpTypeManoid() == type_manoid.ASSHOLE
					&& pIJDeity.getpIsTechnoG01Rancune()) {
				if (pMapIJManoidRancuneToCountDownRancune.containsKey(sIJManoidNeighbour)) {
					return type_interact.CROOK;
				}
				/*
				 * Memory
				 */
				else {
					pSetIJManoidRancuneToAddBuffer.add(sIJManoidNeighbour);
				}
			}
			/*
			 * Technology LANCEUR D'ALERTE
			 */
			if (sIJManoidNeighbour.getpTypeManoid() == type_manoid.ASSHOLE
					&& pIJDeity.getpIsTechnoG02LanceurAlerte()) {
				if (pIJDeity.getpMapIJManoidToCountDown().containsKey(sIJManoidNeighbour)) {
					return type_interact.CROOK;
				}
				/*
				 * Memory
				 */
				else {
					pIJDeity.addpSetIJManoidRancuneBuffer(sIJManoidNeighbour);
				}
			}
			return type_interact.COOPERATE;
		} else {
			return type_interact.CROOK;
		}
	}

	/**
	 * 
	 */
	public final void afterRound() {
		/*
		 * Count down one round
		 */
		Set<IJManoid> lSetToRemove = new HashSet<>();
		for (IJManoid lIJManoid : pMapIJManoidRancuneToCountDownRancune.keySet()) {
			int lCountDown = pMapIJManoidRancuneToCountDownRancune.get(lIJManoid) - 1;
			if (lCountDown <= 0) {
				lSetToRemove.add(lIJManoid);
			} else {
				pMapIJManoidRancuneToCountDownRancune.put(lIJManoid, lCountDown);
			}
		}
		for (IJManoid lIJManoid : lSetToRemove) {
			pMapIJManoidRancuneToCountDownRancune.remove(lIJManoid);
		}
		/*
		 * Add new MANOIDS
		 */
		int lMemoryRancune;
		if (pIJDeity.getpIsTechnoG02MemoireDelephant()) {
			lMemoryRancune = pIJGuiManager.getpNumberOfRounds() * 2;
		} else {
			lMemoryRancune = 1;
		}		
		for (IJManoid lIJManoid : pSetIJManoidRancuneToAddBuffer) {
			pMapIJManoidRancuneToCountDownRancune.put(lIJManoid, lMemoryRancune);
		}
		pSetIJManoidRancuneToAddBuffer.clear();
	}
	
	/**
	 * 
	 */
	public final String toString() {
		return "" + pTypeManoid;
	}

	/*
	 * Getters & Setters
	 */
	public final IJManoidManager getpIJManoidManager() {
		return pIJManoidManager;
	}
	public final IJDeity getpIJDeity() {
		return pIJDeity;
	}
	public final type_manoid getpTypeManoid() {
		return pTypeManoid;
	}
	public final int getpLife() {
		return pLife;
	}
	public final IJGridCell getpIJGridCell() {
		return pIJGridCell;
	}
	public final void addpLife(int sToAdd) {
		pLife += sToAdd;
	}
	public final void setpIJGridCell(IJGridCell pIJGridCell) {
		this.pIJGridCell = pIJGridCell;
	}
	public final int getpGainInRound() {
		return pGainInRound;
	}
	public final void setpGainInRound(int pGainInRound) {
		this.pGainInRound = pGainInRound;
	}
	
	
}
