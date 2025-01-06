package turn;

import java.util.ArrayList;
import java.util.List;

import basicmethods.AMNumberTools;
import basicmethods.BasicPrintMsg;
import deity.IJDeity;
import gui.IJGridCell;
import manoid.IJManoid;
import turn.IJDilemma.type_arena;

public class IJArena {

	public IJArena(IJFight sIJFight) {
		pIJFight = sIJFight;
	}
	
	/*
	 * Data
	 */
	private IJFight pIJFight;
	private type_arena pTypeArena;
	private List<IJGridCell> pListIJGridCellFree;
	private List<IJGridCell> pListIJGridCell;
	private int pX0;
	private int pY0;
	private int pXf;
	private int pYf;
	
	
	/**
	 * 
	 */
	public final void initiate() {
		/*
		 * Initiate the type of arena
		 */
		pTypeArena = pIJFight.getpIJGuiManager().getpChoiceTypeArena();
		if (pTypeArena == type_arena.RANDOM) {
			if (Math.random() < 0.5) {
				pTypeArena = type_arena.ARIDE;
			} else {
				pTypeArena = type_arena.FERTILE;
			}
		}
		/*
		 * Count the total number of MANOIDS
		 */
		int lNbManoid = 0;
		for (IJDeity lIJDeity : pIJFight.getpIJDeityManager().getpListDeity()) {
			lNbManoid += lIJDeity.getpIJGuiDeity().pParamWMain_Deities_Starting_Number_Assholes;
			lNbManoid += lIJDeity.getpIJGuiDeity().pParamWMain_Deities_Starting_Number_Guillibles;
		}
		/*
		 * Deduce the size of the arena
		 */
		int lEdgeArena = AMNumberTools.ceil(Math.sqrt(lNbManoid));
		if (lEdgeArena > 50) {
			BasicPrintMsg.error("there are too many Manoids. It exceeds the size of the grid"
					+ "\nNumber of Manoids= " + lNbManoid
					+ "\nSize of Arena= " + lEdgeArena);
		}
		/*
		 * Communication
		 */
		if (!pIJFight.getpIJManager().getpIJBacktestManager().getpIsRunning())  {
			BasicPrintMsg.display(this, "Total number of manoids= " + lNbManoid);
			BasicPrintMsg.display(this, "Size of the arena: " + lEdgeArena);
		}
		/*
		 * Determine the free cells in the GUI
		 */
		pListIJGridCellFree = new ArrayList<>();
		pX0 = (50 - lEdgeArena) / 2;
		pY0 = pX0;
		pXf = pX0 + lEdgeArena - 1;
		pYf = pXf;
		if (pX0 < 0 || pY0 < 0 || pXf >= 50 || pYf >= 50) {
			BasicPrintMsg.error("Problem with grid"
					+ "\nlX0= " + pX0
					+ "\nlY0= " + pY0
					+ "\nlXf= " + pXf
					+ "\nlYf= " + pYf);
		}
		for (int lX = 0; lX < 50; lX++) {
			for (int lY = 0; lY < 50; lY++) {
				IJGridCell lIJGridCell = pIJFight.getpIJGuiManager().getpListIJGridLine().get(lX).getpListIJGridCell().get(lY);
				if (pX0 <= lX && lX <= pXf && pY0 <= lY && lY <= pYf) {
					pListIJGridCellFree.add(lIJGridCell);
					lIJGridCell.setpIsOff(false);
				} else {
					lIJGridCell.setpIsOff(true);
				}
				lIJGridCell.initiate();
			}
		}
		pListIJGridCell = new ArrayList<>(pListIJGridCellFree);
	}

	/**
	 * 
	 * @param sIJManoid
	 */
	public final IJGridCell allocatePlaceInGrid(IJManoid sIJManoid) {
		int lIdx = AMNumberTools.floor(Math.random() * pListIJGridCellFree.size());
		IJGridCell lIJGridCell = pListIJGridCellFree.get(lIdx);
		pListIJGridCellFree.remove(lIdx);
		lIJGridCell.setpIJManoid(sIJManoid);
		return lIJGridCell;
	}
	
	/**
	 * 
	 * @param sIJManois
	 * @param sX
	 * @param sY
	 * @return
	 */
	public final IJGridCell allocatePlaceInGrid(IJManoid sIJManoid, int sX, int sY) {
		IJGridCell lIJGridCell = pIJFight.getpIJGuiManager().getpIJGridCell(sX, sY);
		if (!pListIJGridCellFree.contains(lIJGridCell)) {
			BasicPrintMsg.errorCodeLogic();
		} else {
			pListIJGridCellFree.remove(lIJGridCell);
		}
		lIJGridCell.setpIJManoid(sIJManoid);
		return lIJGridCell;
	}
	
	
	/*
	 * Getters & Setters
	 */
	public final type_arena getpTypeArena() {
		return pTypeArena;
	}
	public final List<IJGridCell> getpListIJGridCell() {
		return pListIJGridCell;
	}
	public final IJFight getpIJFight() {
		return pIJFight;
	}
	public final List<IJGridCell> getpListIJGridCellFree() {
		return pListIJGridCellFree;
	}
	public final int getpX0() {
		return pX0;
	}
	public final int getpY0() {
		return pY0;
	}
	public final int getpXf() {
		return pXf;
	}
	public final int getpYf() {
		return pYf;
	}
	
}
