package gui;

import java.util.ArrayList;
import java.util.List;

import background.generator.GUIDeclarePackageInterface;

public class IJDeclarePackage implements GUIDeclarePackageInterface {

	@Override
	public List<Class<?>> getmListClassToJFrame() {
		/*
		 * Instantiate List
		 */
		List<Class<?>> lListClass = new ArrayList<Class<?>>();
		/*
		 * Fill List
		 */
		lListClass.add(IJGridLine.class);
		lListClass.add(IJGuiManoids.class);
		lListClass.add(IJGuiDeity.class);
		lListClass.add(IJGuiDilemma.class);
		lListClass.add(IJGuiRestart.class);
		lListClass.add(IJGuiDisplayGlobal.class);
		lListClass.add(IJGuiBacktest.class);		
		/*
		 * Return List
		 */
		return lListClass;
	}

}
