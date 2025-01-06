package turn;

import java.util.ArrayList;
import java.util.List;

import basicmethods.AMNumberTools;
import basicmethods.BasicTime;
import deity.IJDeity;
import deity.IJDeityManager;
import gui.IJGridCell;
import gui.IJGuiManager;
import manoid.IJManoid;
import zz_launch_me.IJManager;

public class IJFight {

	public IJFight(IJManager sIJManager) {
		pIJManager = sIJManager;
		/*
		 * 
		 */
		pIJGuiManager = pIJManager.getpIJGuiManager();
		pIJDeityManager = pIJManager.getpIJDeityManager();
		pIdxRound = Integer.MAX_VALUE;
		pIJDilemma = new IJDilemma(this);
		pIJArena = new IJArena(this);
	}

	/*
	 * Managers
	 */
	private IJManager pIJManager;
	private IJGuiManager pIJGuiManager;
	private IJDilemma pIJDilemma;
	private IJDeityManager pIJDeityManager;
	/*
	 * Data
	 */
	private int pIdxRound;
	private IJArena pIJArena;
	private long pNextTime;
	private boolean pIsLaunchManualOrAutomatic;

	/**
	 * 
	 */
	public final void initiate() {
		pIJArena.initiate();
		/*
		 * initiate the dilemma
		 */
		pIJDilemma.initiate();
		/*
		 * Place the MANOIDS of the deity
		 */
		pIJManager.getpIJManoidManager().initiate();
		pIJDeityManager.initiate();
		/*
		 * 
		 */
		pNextTime = 0;
	}

	public final void start(boolean sIsManualOrAutomatic) {
		pIsLaunchManualOrAutomatic = sIsManualOrAutomatic;
		pIdxRound = 0;
	}

	public final void refresh() {
		if (pIdxRound <= pIJGuiManager.getpNumberOfRounds()) {
			if (pIsLaunchManualOrAutomatic || BasicTime.getmNow() > pNextTime) {
				pNextTime = BasicTime.getmNow() + pIJGuiManager.getpIJGuiRestart().pParamWMain_Restart_Pause_between_rounds_MS;
				/*
				 * Compute the gain and losses for and from each MANOID
				 */
				List<IJManoid> lListIJManoidDead = new ArrayList<>();
				List<IJManoid> lListIJManoid = pIJManager.getpIJManoidManager().getpListIJManoid();
				for (IJManoid lIJManoid : lListIJManoid) {
					IJGridCell lIJGridCell = lIJManoid.getpIJGridCell();
					

					/////////////////////////////////////////////////////////
					if (lIJManoid.getpIJDeity().getpName().startsWith("A")) {
						AMNumberTools.DEBUG();
					}
					/////////////////////////////////////////////////////////					



					
					/*
					 * Look around
					 */
					int lOutcome = pIJDilemma.getpDecay();
					for (int lDX = -1; lDX <= 1; lDX++) {
						for (int lDY = -1; lDY <= 1; lDY++) {
							/*
							 * Get neighbour
							 */
							IJGridCell lIJGridCellNeighbour = lIJGridCell.getpIJGridCellNeighbour(lDX, lDY);
							if (lIJGridCellNeighbour != null) {
								IJManoid lIJManoidNeighbour = lIJGridCellNeighbour.getpIJManoid();
								if (lIJManoidNeighbour != null) {
									/*
									 * Interactions of IJManoids
									 */
									lOutcome += pIJDilemma.getpOutcome(lIJManoid, lIJManoidNeighbour);
								}
							}
						}
					}


					/*
					 * Compute gain
					 */
					if (lOutcome > 0) {
						lIJManoid.getpIJDeity().addpScore(lOutcome);
					} else if (lOutcome < 0) {
						lIJManoid.addpLife(lOutcome);
					}
					lIJManoid.setpGainInRound(lOutcome);
					/*
					 * Case of the dead --> we need to remove
					 */
					if (lIJManoid.getpLife() <= 0) {
						lListIJManoidDead.add(lIJManoid);
					}

					/////////////////////////////////////////////////////////
					if (lIJManoid.getpIJDeity().getpName().startsWith("A")) {
						AMNumberTools.DEBUG();
					}
					/////////////////////////////////////////////////////////		


				}
				/*
				 * Remove all the dead MANOIDS
				 */
				for (IJManoid lIJManoid : lListIJManoidDead) {
					pIJManager.getpIJManoidManager().removeIJManoid(lIJManoid);
				}
				/*
				 * Consequence technologies
				 */
				for (IJManoid lIJManoid : lListIJManoid) {
					lIJManoid.getpIJDeity().behaviourAfterGain(lIJManoid);
				}
				/*
				 * Consequences of the round: after round
				 */
				for (IJManoid lIJManoid : pIJManager.getpIJManoidManager().getpListIJManoid()) {
					lIJManoid.afterRound();
				}
				for (IJDeity lIJDeity : pIJManager.getpIJDeityManager().getpListDeity()) {
					lIJDeity.afterRound();
				}
				/*
				 * Move MANOIDs if technology allows it
				 */
				pIJManager.getpIJGuiManager().afterRound();
				/*
				 * Move on
				 */
				pIdxRound++;
				////////////////////////////////////////

				//				for (IJManoid lIJManoid : pIJManager.getpIJManoidManager().getpListIJManoid()) {
				//					if(lIJManoid.getpTypeManoid() == type_manoid.ASSHOLE) {
				//						
				//						int lX = lIJManoid.getpIJGridCell().getpX();
				//						int lY = lIJManoid.getpIJGridCell().getpY();
				//						
				//						lX = 26;
				//						lY = 25;
				//						
				//						IJGridCell lIJGridCell = pIJManager.getpIJGuiManager().getpIJGridCell(lX, lY);
				//						lIJManoid.setpIJGridCell(lIJGridCell);
				//						lIJGridCell.setpIJManoid(lIJManoid);						
				//					}
				//				}				
				////////////////////////////////////////				
			}			
		}
	}


	/*
	 * Getters & Setters
	 */
	public final IJGuiManager getpIJGuiManager() {
		return pIJGuiManager;
	}
	public final IJDilemma getpIJDilemma() {
		return pIJDilemma;
	}
	public final IJManager getpIJManager() {
		return pIJManager;
	}
	public final IJDeityManager getpIJDeityManager() {
		return pIJDeityManager;
	}
	public final int getpIdxRound() {
		return pIdxRound;
	}
	public final IJArena getpIJArena() {
		return pIJArena;
	}
	public final boolean getpIsLaunchManualOrAutomatic() {
		return pIsLaunchManualOrAutomatic;
	}





}
