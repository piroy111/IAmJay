package zz_launch_me;
import background.guistatic.GUIStatic;
import gui.IJGuiManager;

public class IJLaunchMe {

	/**
	 * Launch me
	 */
	public static void main(String[] _sArgs) {
		if (_sArgs.length > 0) {
			GUIStatic.FORCE_PATH_JAVA(_sArgs[0]);
		}
		IJGuiManager lIJGuiManager = new IJGuiManager();
		lIJGuiManager.start();
		lIJGuiManager.refresh();
	}
	
}
