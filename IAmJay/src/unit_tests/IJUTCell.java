package unit_tests;

import java.util.List;

import basicmethods.BasicPrintMsg;
import basicmethods.BasicString;
import deity.IJDeity;
import manoid.IJManoidManager.type_manoid;
import zz_launch_me.IJManager;

public class IJUTCell {

	protected IJUTCell(IJUTArena sIJUTArena, int sX, int sY, String sValue) {
		pIJUTArena = sIJUTArena;
		pValue = sValue;
		pX = sX;
		pY = sY;
		/*
		 * Instantiate
		 */
		instantiate();
	}
	
	/*
	 * Data
	 */
	private IJUTArena pIJUTArena;
	private int pX;
	private int pY;
	private String pValue;
	private IJDeity pIJDeity;
	private type_manoid pTypeManoid;
	private int pLife;
	
	/**
	 * 
	 */
	private final void instantiate() {
		/*
		 * Read Value
		 */
		String lStrDeity = pValue.substring(0, 1);
		String lStrManoid = pValue.substring(1, 2);
		String lStrLife = pValue.substring(2, pValue.length());
		/*
		 * Deity
		 */
		pIJDeity = null;
		IJManager lIJManager = pIJUTArena.getpIJUTOneTest().getpIJUTManager().getpIJManager();
		List<IJDeity> lListIJDeity = lIJManager.getpIJDeityManager().getpListDeity();
		for (IJDeity lIJDeity : lListIJDeity) {
			if (lIJDeity.getpName().startsWith(lStrDeity)) {
				pIJDeity = lIJDeity;
				break;
			}
		}
		if (pIJDeity == null) {
			BasicPrintMsg.error("I cannot find the name of the deity"
					+ "\nLetter of the deity= " + lStrDeity
					+ "\nFile in error= " + pIJUTArena.getpReadFile().getmDirPlusNameFile());
		}
		/*
		 * MANOID
		 */
		if (lStrManoid.equals("a")) {
			pTypeManoid = type_manoid.ASSHOLE;
		} else if (lStrManoid.equals("g")) {
			pTypeManoid = type_manoid.GULLIBLE;
		} else {
			BasicPrintMsg.error("I cannot find the name of the manoid"
					+ "\nLetter of the manoid= " + lStrManoid
					+ "\nFile in error= " + pIJUTArena.getpReadFile().getmDirPlusNameFile());
		}
		/*
		 * Life
		 */
		pLife = BasicString.getInt(lStrLife);
	}

	public String toString() {
		return "IJDeity= " + pIJDeity + "; Manoid= " + pTypeManoid + "; Life= " + pLife;
	}

	
	/*
	 * Getters & Setters
	 */
	public final IJUTArena getpIJUTArena() {
		return pIJUTArena;
	}
	public final String getpValue() {
		return pValue;
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
	public final int getpX() {
		return pX;
	}
	public final int getpY() {
		return pY;
	}

}
