package gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class IJGridLine {

	public IJGridLine(IJGuiManager _sIJGuiManager, int _sY) {
		pIJGuiManager = _sIJGuiManager;
		pY = _sY;
		/*
		 * Instantiate
		 */
		instantiate();
		/*
		 * GUI 
		 */
		pIJGuiManager.getmGUIManager().addNewRowInGUIFrame(this);
	}
	
	/*
	 * Data
	 */
	private IJGuiManager pIJGuiManager;
	private List<IJGridCell> pListIJGridCell;
	private int pY;

	public final void instantiate() {
		pListIJGridCell = new ArrayList<>();
		for (int lIdx = 0; lIdx < 50; lIdx++) {
			IJGridCell lIJGridCell = new IJGridCell(this, lIdx);
			pListIJGridCell.add(lIJGridCell);
		}
	}
	
	public void initiate() {
		for (int lIdx = 0; lIdx < pListIJGridCell.size(); lIdx++) {
			pListIJGridCell.get(lIdx).initiate();
		}
	}
	
	public void refresh() {
		for (int lIdx = 0; lIdx < pListIJGridCell.size(); lIdx++) {
			pListIJGridCell.get(lIdx).refresh();
		}
	}
	
	/*
	 * Display
	 */
	public final int pDisplayWMain_Grid_Row() {
		return pY + 1;
	}
	
	
	/*
	 * Values
	 */
	public final String pDisplayWMain_Grid_1() {
		return pListIJGridCell.get(0).getpValue();
	}
	public final String pDisplayWMain_Grid_2() {
		return pListIJGridCell.get(1).getpValue();
	}
	public final String pDisplayWMain_Grid_3() {
		return pListIJGridCell.get(2).getpValue();
	}
	public final String pDisplayWMain_Grid_4() {
		return pListIJGridCell.get(3).getpValue();
	}
	public final String pDisplayWMain_Grid_5() {
		return pListIJGridCell.get(4).getpValue();
	}
	public final String pDisplayWMain_Grid_6() {
		return pListIJGridCell.get(5).getpValue();
	}
	public final String pDisplayWMain_Grid_7() {
		return pListIJGridCell.get(6).getpValue();
	}
	public final String pDisplayWMain_Grid_8() {
		return pListIJGridCell.get(7).getpValue();
	}
	public final String pDisplayWMain_Grid_9() {
		return pListIJGridCell.get(8).getpValue();
	}
	public final String pDisplayWMain_Grid_10() {
		return pListIJGridCell.get(9).getpValue();
	}
	public final String pDisplayWMain_Grid_11() {
		return pListIJGridCell.get(10).getpValue();
	}
	public final String pDisplayWMain_Grid_12() {
		return pListIJGridCell.get(11).getpValue();
	}
	public final String pDisplayWMain_Grid_13() {
		return pListIJGridCell.get(12).getpValue();
	}
	public final String pDisplayWMain_Grid_14() {
		return pListIJGridCell.get(13).getpValue();
	}
	public final String pDisplayWMain_Grid_15() {
		return pListIJGridCell.get(14).getpValue();
	}
	public final String pDisplayWMain_Grid_16() {
		return pListIJGridCell.get(15).getpValue();
	}
	public final String pDisplayWMain_Grid_17() {
		return pListIJGridCell.get(16).getpValue();
	}
	public final String pDisplayWMain_Grid_18() {
		return pListIJGridCell.get(17).getpValue();
	}
	public final String pDisplayWMain_Grid_19() {
		return pListIJGridCell.get(18).getpValue();
	}
	public final String pDisplayWMain_Grid_20() {
		return pListIJGridCell.get(19).getpValue();
	}
	public final String pDisplayWMain_Grid_21() {
		return pListIJGridCell.get(20).getpValue();
	}
	public final String pDisplayWMain_Grid_22() {
		return pListIJGridCell.get(21).getpValue();
	}
	public final String pDisplayWMain_Grid_23() {
		return pListIJGridCell.get(22).getpValue();
	}
	public final String pDisplayWMain_Grid_24() {
		return pListIJGridCell.get(23).getpValue();
	}
	public final String pDisplayWMain_Grid_25() {
		return pListIJGridCell.get(24).getpValue();
	}
	public final String pDisplayWMain_Grid_26() {
		return pListIJGridCell.get(25).getpValue();
	}
	public final String pDisplayWMain_Grid_27() {
		return pListIJGridCell.get(26).getpValue();
	}
	public final String pDisplayWMain_Grid_28() {
		return pListIJGridCell.get(27).getpValue();
	}
	public final String pDisplayWMain_Grid_29() {
		return pListIJGridCell.get(28).getpValue();
	}
	public final String pDisplayWMain_Grid_30() {
		return pListIJGridCell.get(29).getpValue();
	}
	public final String pDisplayWMain_Grid_31() {
		return pListIJGridCell.get(30).getpValue();
	}
	public final String pDisplayWMain_Grid_32() {
		return pListIJGridCell.get(31).getpValue();
	}
	public final String pDisplayWMain_Grid_33() {
		return pListIJGridCell.get(32).getpValue();
	}
	public final String pDisplayWMain_Grid_34() {
		return pListIJGridCell.get(33).getpValue();
	}
	public final String pDisplayWMain_Grid_35() {
		return pListIJGridCell.get(34).getpValue();
	}
	public final String pDisplayWMain_Grid_36() {
		return pListIJGridCell.get(35).getpValue();
	}
	public final String pDisplayWMain_Grid_37() {
		return pListIJGridCell.get(36).getpValue();
	}
	public final String pDisplayWMain_Grid_38() {
		return pListIJGridCell.get(37).getpValue();
	}
	public final String pDisplayWMain_Grid_39() {
		return pListIJGridCell.get(38).getpValue();
	}
	public final String pDisplayWMain_Grid_40() {
		return pListIJGridCell.get(39).getpValue();
	}
	public final String pDisplayWMain_Grid_41() {
		return pListIJGridCell.get(40).getpValue();
	}
	public final String pDisplayWMain_Grid_42() {
		return pListIJGridCell.get(41).getpValue();
	}
	public final String pDisplayWMain_Grid_43() {
		return pListIJGridCell.get(42).getpValue();
	}
	public final String pDisplayWMain_Grid_44() {
		return pListIJGridCell.get(43).getpValue();
	}
	public final String pDisplayWMain_Grid_45() {
		return pListIJGridCell.get(44).getpValue();
	}
	public final String pDisplayWMain_Grid_46() {
		return pListIJGridCell.get(45).getpValue();
	}
	public final String pDisplayWMain_Grid_47() {
		return pListIJGridCell.get(46).getpValue();
	}
	public final String pDisplayWMain_Grid_48() {
		return pListIJGridCell.get(47).getpValue();
	}
	public final String pDisplayWMain_Grid_49() {
		return pListIJGridCell.get(48).getpValue();
	}
	public final String pDisplayWMain_Grid_50() {
		return pListIJGridCell.get(49).getpValue();
	}


	
	/*
	 * COLOR
	 */
	public final Color pDisplayColorWMain_Grid_1() {
		return pListIJGridCell.get(0).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_2() {
		return pListIJGridCell.get(1).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_3() {
		return pListIJGridCell.get(2).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_4() {
		return pListIJGridCell.get(3).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_5() {
		return pListIJGridCell.get(4).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_6() {
		return pListIJGridCell.get(5).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_7() {
		return pListIJGridCell.get(6).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_8() {
		return pListIJGridCell.get(7).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_9() {
		return pListIJGridCell.get(8).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_10() {
		return pListIJGridCell.get(9).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_11() {
		return pListIJGridCell.get(10).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_12() {
		return pListIJGridCell.get(11).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_13() {
		return pListIJGridCell.get(12).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_14() {
		return pListIJGridCell.get(13).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_15() {
		return pListIJGridCell.get(14).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_16() {
		return pListIJGridCell.get(15).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_17() {
		return pListIJGridCell.get(16).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_18() {
		return pListIJGridCell.get(17).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_19() {
		return pListIJGridCell.get(18).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_20() {
		return pListIJGridCell.get(19).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_21() {
		return pListIJGridCell.get(20).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_22() {
		return pListIJGridCell.get(21).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_23() {
		return pListIJGridCell.get(22).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_24() {
		return pListIJGridCell.get(23).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_25() {
		return pListIJGridCell.get(24).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_26() {
		return pListIJGridCell.get(25).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_27() {
		return pListIJGridCell.get(26).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_28() {
		return pListIJGridCell.get(27).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_29() {
		return pListIJGridCell.get(28).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_30() {
		return pListIJGridCell.get(29).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_31() {
		return pListIJGridCell.get(30).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_32() {
		return pListIJGridCell.get(31).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_33() {
		return pListIJGridCell.get(32).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_34() {
		return pListIJGridCell.get(33).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_35() {
		return pListIJGridCell.get(34).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_36() {
		return pListIJGridCell.get(35).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_37() {
		return pListIJGridCell.get(36).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_38() {
		return pListIJGridCell.get(37).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_39() {
		return pListIJGridCell.get(38).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_40() {
		return pListIJGridCell.get(39).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_41() {
		return pListIJGridCell.get(40).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_42() {
		return pListIJGridCell.get(41).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_43() {
		return pListIJGridCell.get(42).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_44() {
		return pListIJGridCell.get(43).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_45() {
		return pListIJGridCell.get(44).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_46() {
		return pListIJGridCell.get(45).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_47() {
		return pListIJGridCell.get(46).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_48() {
		return pListIJGridCell.get(47).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_49() {
		return pListIJGridCell.get(48).getpColor();
	}
	public final Color pDisplayColorWMain_Grid_50() {
		return pListIJGridCell.get(49).getpColor();
	}


	
	/*
	 * Getters & Setters
	 */
	public final IJGuiManager getpIJGuiManager() {
		return pIJGuiManager;
	}
	public final int getpY() {
		return pY;
	}
	public final List<IJGridCell> getpListIJGridCell() {
		return pListIJGridCell;
	}
}
