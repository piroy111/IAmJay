package deity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import basicmethods.AMDebug;
import basicmethods.AMNumberTools;
import gui.IJGridCell;
import gui.IJGuiDeity;
import gui.IJGuiDiplomacy;
import manoid.IJManoid;
import manoid.IJManoidManager.type_manoid;
import unit_tests.IJUTManager;

public class IJDeity {

	protected IJDeity(IJDeityManager sIJDeityManager, String _sName) {
		pIJDeityManager = sIJDeityManager;
		pName = _sName;
	}

	/*
	 * Data
	 */
	private IJDeityManager pIJDeityManager;
	private String pName;
	private List<IJManoid> pListIJManoid;
	private IJGuiDeity pIJGuiDeity;
	private int pScore;
	private IJGuiDiplomacy pIJGuiDiplomacy;
	/*
	 * Technology
	 */
	private boolean pIsTechnoA01Move;
	private boolean pIsTechnoA02RadarACretins;
	private boolean pIsTechnoA02Teleportation;
	private boolean pIsTechnoA03Vorace;
	private boolean pIsTechnoG01Rancune;
	private boolean pIsTechnoG02LanceurAlerte;
	private boolean pIsTechnoG02MemoireDelephant;
	private boolean pIsTechnoG03ImmuniteAlliance;
	/*
	 * Behaviour
	 */
	private Map<IJManoid, Integer> pMapIJManoidToCountDown;
	private Set<IJManoid> pSetIJManoidRancuneBuffer;
	/*
	 * Diplomacy
	 */
	private int pMaxLevelDipolomacy;
	private Map<IJDeity, Integer> pMapIJDeityToLevelDiplomacy;

	public final void initiate() {
		pListIJManoid = new ArrayList<>();
		pMapIJManoidToCountDown = new HashMap<>();
		pSetIJManoidRancuneBuffer = new HashSet<>();
		pMapIJDeityToLevelDiplomacy = new HashMap<>();
	}

	/**
	 * 
	 * @param sTypeManoid
	 * @return
	 */
	public final IJManoid createIJManoid(type_manoid sTypeManoid) {
		return pIJDeityManager.getpIJManager().getpIJManoidManager().createIJManoid(this, sTypeManoid);
	}

	/**
	 * 
	 * @param sIJManoid
	 */
	public final void removeIJManoid(IJManoid sIJManoid) {
		pIJDeityManager.getpIJManager().getpIJManoidManager().removeIJManoid(sIJManoid);
	}

	/**
	 * 
	 */
	public final void behaviourAfterGain(IJManoid sIJManoid) {
		
		///////////////////////////////////////////
		if (pName.equals("Gamer")) {
			AMDebug.DEBUG();
		}
		///////////////////////////////////////////		
		
		if (pIsTechnoA01Move) {
			/*
			 * Check if we need to move --> if we lose or if Techno03
			 */
			boolean lIsNeedToMove = (pIsTechnoA01Move && sIJManoid.getpGainInRound() < 0) || pIsTechnoA02RadarACretins;
			/*
			 * Move
			 */
			if (lIsNeedToMove) {
				IJGridCell lIJGridCell = sIJManoid.getpIJGridCell();
				/*
				 * Technolog02 - TELEPORTATION
				 */
				int lBoundary = 1;
				if (pIsTechnoA02Teleportation) {
					lBoundary = 2;
				}
				/*
				 * Loop over all neighbours to list all free cells
				 */
				List<IJGridCell> lListIJGridCellEmpty = new ArrayList<>();
				for (int lDY = -lBoundary; lDY <= lBoundary; lDY++) {
					for (int lDX = -lBoundary; lDX <= lBoundary; lDX++) {
					

						////////////////////////////////////////////
//						if (lDX == 1 && lDY == 0) {
//							AMDebug.DEBUG();
//						}
						////////////////////////////////////////////

						if (!(lDX == 0 && lDY == 0)) {
							IJGridCell lIJGridCellNeighbour = lIJGridCell.getpIJGridCellNeighbour(lDX, lDY);
							if (lIJGridCellNeighbour != null 
									&& lIJGridCellNeighbour.getpIJManoid() == null 
									&& lIJGridCellNeighbour.getpIJManoidToMoveHere() == null) {
								lListIJGridCellEmpty.add(lIJGridCellNeighbour);
							}
						}
					}
				}

				//////////////////////////////////////////////
//				System.out.println();
//				System.out.println("lListIJGridCellEmpty= " + lListIJGridCellEmpty);
				//////////////////////////////////////////////				

				/*
				 * We move only if there is a free cell
				 */
				if (lListIJGridCellEmpty.size() > 0) {
					IJGridCell lIJGridCellNew = null;
					/*
					 * Case of RADAR A CRETINS (full technology 03)
					 */
					if (pIsTechnoA02RadarACretins) {
						/*
						 * We add the current cell. Sometimes staying is the best option
						 */
						lListIJGridCellEmpty.add(lIJGridCell);
						/*
						 * We read all the potential cell
						 */
						List<IJGridCell> lListIJGridCellOptimal = new ArrayList<>();
						int lNbGullibleMax = 0;
						for (IJGridCell lIJGridCellPotential : lListIJGridCellEmpty) {
							/*
							 * Count the number of GULLIBLE around lIJGridCellPotential
							 */
							int lNbGullible = 0;
							for (int lDX = -1; lDX <= 1; lDX++) {
								for (int lDY = -1; lDY <= 1; lDY++) {
									IJGridCell lIJGridCellNeighbour = lIJGridCellPotential.getpIJGridCellNeighbour(lDX, lDY);
									if (lIJGridCellNeighbour != null && !lIJGridCellNeighbour.equals(lIJGridCell)) {
										IJManoid lIJManoidNeighbour = lIJGridCellNeighbour.getpIJManoid();
										/*
										 * We count only the GULLIBLE
										 */
										if (lIJManoidNeighbour != null && lIJManoidNeighbour.getpTypeManoid() == type_manoid.GULLIBLE) {
											/*
											 * We avoid sending our ASSHOLE next to our GULLIBLE --> we don't count our GULLIBLE if we are an ASSHOLE
											 */
											if (!(sIJManoid.getpTypeManoid() == type_manoid.ASSHOLE
													&& lIJManoidNeighbour.getpIJDeity().equals(sIJManoid.getpIJDeity()))) {
												lNbGullible++;
											}
										}
									}
								}
							}
							/*
							 * update with the optimum
							 */
							if (lNbGullible > lNbGullibleMax) {
								lNbGullibleMax = lNbGullible;
								lListIJGridCellOptimal.clear();
								lListIJGridCellOptimal.add(lIJGridCellPotential);
							} else if (lNbGullible == lNbGullibleMax) {
								lListIJGridCellOptimal.add(lIJGridCellPotential);
							}
						}
						lListIJGridCellEmpty = lListIJGridCellOptimal;
					}

					//////////////////////////////////////////////
//					System.out.println("lListIJGridCellOptimal= " + lListIJGridCellEmpty);
					//////////////////////////////////////////////	

					/*
					 * If the current is among the best cells, then we don't move
					 */
					if (!lListIJGridCellEmpty.contains(lIJGridCell)) {
						/*
						 * Choose randomly a free space
						 */
						int lIdxNewCell = AMNumberTools.floor(lListIJGridCellEmpty.size() * IJUTManager.random());
						lIJGridCellNew = lListIJGridCellEmpty.get(lIdxNewCell);

						//////////////////////////////////////////////
//						System.out.println("lIJGridCellNew= " + lIJGridCellNew);
//						System.out.println();
						//////////////////////////////////////////////

						/*
						 * Book the place so that another MANOID will not take it. Move will be done after
						 */
						if (!lIJGridCellNew.equals(lIJGridCell)) {
							lIJGridCellNew.setpIJManoidToMoveHere(sIJManoid);
						}
					}
				}
			}
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
		for (IJManoid lIJManoid : pMapIJManoidToCountDown.keySet()) {
			int lCountDown = pMapIJManoidToCountDown.get(lIJManoid) - 1;
			if (lCountDown <= 0) {
				lSetToRemove.add(lIJManoid);
			} else {
				pMapIJManoidToCountDown.put(lIJManoid, lCountDown);
			}
		}
		for (IJManoid lIJManoid : lSetToRemove) {
			pMapIJManoidToCountDown.remove(lIJManoid);
		}
		/*
		 * Add new MANOIDS
		 */
		for (IJManoid lIJManoid : pSetIJManoidRancuneBuffer) {
			pMapIJManoidToCountDown.put(lIJManoid, pIJDeityManager.getpIJManager().getpIJGuiManager().getpMemoryLanceurDAlerte());
		}
		pSetIJManoidRancuneBuffer.clear();
	}

	public final String toString() {
		return pName;
	}

	/**
	 * 
	 * @return
	 */
	public final int getpDiplomacyWith(IJDeity sIJDeityWith) {
		return pIJGuiDiplomacy.getpIsDiplomacy(sIJDeityWith);
	}
	
	/*
	 * Getters & Setters
	 */
	public final String getpName() {
		return pName;
	}
	public final List<IJManoid> getpListIJManoid() {
		return pListIJManoid;
	}
	public final IJDeityManager getpIJDeityManager() {
		return pIJDeityManager;
	}
	public final IJGuiDeity getpIJGuiDeity() {
		return pIJGuiDeity;
	}
	public final void setpIJGuiDeity(IJGuiDeity pIJGuiDeity) {
		this.pIJGuiDeity = pIJGuiDeity;
	}
	public final int getpScore() {
		return pScore;
	}
	public final void addpScore(int sToAdd) {
		pScore += sToAdd;
	}
	public final void setpScore(int pScore) {
		this.pScore = pScore;
	}
	public final boolean getpIsTechnoA01Move() {
		return pIsTechnoA01Move;
	}
	public final boolean getpIsTechnoA02RadarACretins() {
		return pIsTechnoA02RadarACretins;
	}
	public final boolean getpIsTechnoG01Rancune() {
		return pIsTechnoG01Rancune;
	}
	public final boolean getpIsTechnoG02LanceurAlerte() {
		return pIsTechnoG02LanceurAlerte;
	}
	public final boolean getpIsTechnoG03ImmuniteAlliance() {
		return pIsTechnoG03ImmuniteAlliance;
	}
	public final void setpIsTechnoA01Move(boolean pIsTechnoA01Move) {
		this.pIsTechnoA01Move = pIsTechnoA01Move;
	}
	public final void setpIsTechnoA02RadarACretins(boolean pIsTechnoA03RadarACretins) {
		this.pIsTechnoA02RadarACretins = pIsTechnoA03RadarACretins;
	}
	public final void setpIsTechnoG01Rancune(boolean pIsTechnoG01Rancune) {
		this.pIsTechnoG01Rancune = pIsTechnoG01Rancune;
	}
	public final void setpIsTechnoG02LanceurAlerte(boolean pIsTechnoG02LanceurAlerte) {
		this.pIsTechnoG02LanceurAlerte = pIsTechnoG02LanceurAlerte;
	}
	public final void setpIsTechnoG03ImmuniteAlliance(boolean pIsTechnoG03ImmuniteAlliance) {
		this.pIsTechnoG03ImmuniteAlliance = pIsTechnoG03ImmuniteAlliance;
	}
	public final boolean getpIsTechnoA03Vorace() {
		return pIsTechnoA03Vorace;
	}
	public final void setpIsTechnoA03Vorace(boolean pIsTechnoA03Vorace) {
		this.pIsTechnoA03Vorace = pIsTechnoA03Vorace;
	}
	public final void addpSetIJManoidRancuneBuffer(IJManoid sIJManoid) {
		pSetIJManoidRancuneBuffer.add(sIJManoid);
	}
	public final int getpMaxLevelDipolomacy() {
		return pMaxLevelDipolomacy;
	}
	public final void setpMaxLevelDipolomacy(int pMaxLevelDipolomacy) {
		this.pMaxLevelDipolomacy = pMaxLevelDipolomacy;
	}
	public final Map<IJDeity, Integer> getpMapIJDeityToLevelDiplomacy() {
		return pMapIJDeityToLevelDiplomacy;
	}
	public final IJGuiDiplomacy getpIJGuiDiplomacy() {
		return pIJGuiDiplomacy;
	}
	public final void setpIJGuiDiplomacy(IJGuiDiplomacy pIJGuiDiplomacy) {
		this.pIJGuiDiplomacy = pIJGuiDiplomacy;
	}
	public final Map<IJManoid, Integer> getpMapIJManoidToCountDown() {
		return pMapIJManoidToCountDown;
	}
	public final boolean getpIsTechnoA02Teleportation() {
		return pIsTechnoA02Teleportation;
	}
	public final void setpIsTechnoA02Teleportation(boolean pIsTechnoA02Teleportation) {
		this.pIsTechnoA02Teleportation = pIsTechnoA02Teleportation;
	}
	public final boolean getpIsTechnoG02MemoireDelephant() {
		return pIsTechnoG02MemoireDelephant;
	}
	public final void setpIsTechnoG02MemoireDelephant(boolean pIsTechnoG02MemoireDelephant) {
		this.pIsTechnoG02MemoireDelephant = pIsTechnoG02MemoireDelephant;
	}

}
