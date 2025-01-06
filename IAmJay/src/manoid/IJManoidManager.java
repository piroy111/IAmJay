package manoid;

import java.util.ArrayList;
import java.util.List;

import deity.IJDeity;
import zz_launch_me.IJManager;

public class IJManoidManager {

	public IJManoidManager(IJManager _sIJManager) {
		pIJManager = _sIJManager;
	}
	
	/*
	 * Constant
	 */
	public enum type_manoid {GULLIBLE, ASSHOLE}
	/*
	 * Data
	 */
	private IJManager pIJManager;
	private List<IJManoid> pListIJManoid;
	
	
	public final void instantiate() {
		pListIJManoid = new ArrayList<>();
	}
	
	public final void initiate() {
		pListIJManoid.clear();
	}
	
	public final IJManoid createIJManoid(IJDeity sIJDeity, type_manoid sTypeManoid) {
		IJManoid lIJManoid = new IJManoid(this, sIJDeity, sTypeManoid);
		pListIJManoid.add(lIJManoid);
		lIJManoid.initiate();
		sIJDeity.getpListIJManoid().add(lIJManoid);
		return lIJManoid;
	}
	
	public final void removeIJManoid(IJManoid sIJManoid) {
		pListIJManoid.remove(sIJManoid);
		sIJManoid.getpIJDeity().getpListIJManoid().remove(sIJManoid);
		sIJManoid.getpIJGridCell().setpIJManoid(null);
	}
	
	/*
	 * Getters & Setters
	 */
	public final IJManager getpIJManager() {
		return pIJManager;
	}
	public final List<IJManoid> getpListIJManoid() {
		return pListIJManoid;
	}
	
	
	
	
}
