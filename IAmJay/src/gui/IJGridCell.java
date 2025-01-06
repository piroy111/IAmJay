package gui;

import java.awt.Color;

import background.guistatic.GUIStaticColor;
import basicmethods.BasicPrintMsg;
import manoid.IJManoid;
import manoid.IJManoidManager.type_manoid;

public class IJGridCell {

	public IJGridCell(IJGridLine _sIJGridLine, int _sX) {
		pIJGridLine = _sIJGridLine;
		pX = _sX;
		/*
		 * 
		 */
		pY = pIJGridLine.getpY();
		pIJGuiManager = pIJGridLine.getpIJGuiManager();		
	}

	/*
	 * Data
	 */
	private IJGuiManager pIJGuiManager;
	private IJGridLine pIJGridLine;
	private int pX;
	private int pY;
	private String pValue;
	private Color pColor;
	private IJManoid pIJManoid;
	private boolean pIsOff;
	private IJManoid pIJManoidToMoveHere;

	/**
	 * Reset
	 */
	public final void initiate() {
		pIJManoid = null;
		pValue = "";
		pIJManoidToMoveHere = null;
	}
	
	/**
	 * 
	 */
	public final void refresh() {
		if (pIsOff) {
			pColor = GUIStaticColor.getDARK_GREY();
			pValue = "";
		} else {
			if (pIJManoid == null) {
				pColor = GUIStaticColor.getVERY_LIGHT_GREY();
				pValue = "";			
			} else {
				/*
				 * Decide colour
				 */
				if (pIJManoid.getpLife() <= 0) {
					pColor = GUIStaticColor.getVERY_LIGHT_GREY();
				} else if (pIJManoid.getpTypeManoid() == type_manoid.ASSHOLE) {
					if (pIJManoid.getpIJDeity().getpName().equals("Gamer")) {
						pColor =GUIStaticColor.getRED();
					} else {
						pColor =GUIStaticColor.getLIGHT_RED();
					}
				} else if (pIJManoid.getpTypeManoid() == type_manoid.GULLIBLE) {
					if (pIJManoid.getpIJDeity().getpName().equals("Gamer")) {
						pColor =GUIStaticColor.getGREEN();
					} else {
						pColor =GUIStaticColor.getLIGHT_GREEN();
					}
				}
				/*
				 * Decide value
				 */
				pValue = "" + pIJManoid.getpLife();
			}
		}
	}	

	/**
	 * Neighbours +/- 2 around the cell<br>
	 * Does not contain the cell itself<br>
	 * Does not contain the cell off (outside of borders)<br>
	 * contains cells with MANOIDS and cell empty
	 * @param sDX
	 * @param sDY
	 * @return 
	 */
	public final IJGridCell getpIJGridCellNeighbour(int sDX, int sDY) {
		return pIJGuiManager.getpIJGridCellNeighbour(this, sDX, sDY);
	}	

	/**
	 * 
	 */
	public final void afterRound() {
		if (pIJManoidToMoveHere != null) {
			/*
			 * Case of error
			 */
			if (pIJManoid != null) {
				BasicPrintMsg.error("We try to move a Manoid into a cell already occupied"
						+ "\nManoid to move= " + pIJManoidToMoveHere.getpIJGridCell()
						+ "\nCell where we want to move= " + this);
			} else {
				/*
				 * Reset the previous cell
				 */
				pIJManoidToMoveHere.getpIJGridCell().setpIJManoid(null);
				/*
				 * Move the MANOID here
				 */
				pIJManoid = pIJManoidToMoveHere;
				pIJManoid.setpIJGridCell(this);
				pIJManoidToMoveHere = null;
			}
		}
	}
	
	/**
	 * Classic toString
	 */
	public final String toString() {
		return "pX= " + pX + "; pY= " + pY
				+ "; pIJManoid= " + pIJManoid;
	}

	/*
	 * Getters & Setters
	 */
	public final IJGuiManager getpIJGuiManager() {
		return pIJGuiManager;
	}
	public final IJGridLine getpIJGridLine() {
		return pIJGridLine;
	}
	public final int getpX() {
		return pX;
	}
	public final int getpY() {
		return pY;
	}
	public final String getpValue() {
		return pValue;
	}
	public final Color getpColor() {
		return pColor;
	}
	public final IJManoid getpIJManoid() {
		return pIJManoid;
	}
	public final void setpColor(Color pColor) {
		this.pColor = pColor;
	}
	public final void setpIsOff(boolean pIsOff) {
		this.pIsOff = pIsOff;
	}
	public final void setpIJManoid(IJManoid pIJManoid) {
		this.pIJManoid = pIJManoid;
	}
	public final boolean getpIsOff() {
		return pIsOff;
	}
	public final IJManoid getpIJManoidToMoveHere() {
		return pIJManoidToMoveHere;
	}
	public final void setpIJManoidToMoveHere(IJManoid pIJManoidToMoveHere) {
		this.pIJManoidToMoveHere = pIJManoidToMoveHere;
	}




}
