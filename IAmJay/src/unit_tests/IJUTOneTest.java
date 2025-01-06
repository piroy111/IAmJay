package unit_tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import basicmethods.BasicString;
import basicmethods.ReadFile;

public class IJUTOneTest {

	protected IJUTOneTest(String sName, IJUTManager sIJUTManager) {
		pName = sName;
		pIJUTManager = sIJUTManager;
	}
	
	/*
	 * Data
	 */
	private String pName;
	private IJUTManager pIJUTManager;
	private ReadFile pReadFileParameters;
	private ReadFile pReadFileArena01;
	private List<ReadFile> pListReadFilesArena;
	private Map<Integer, IJUTArena> pMapIdxRoundToIJUTArena;
	private IJUTArena pIJUTArenaStart;
	private int pMaxRound;
	

	/**
	 * 
	 * @param sReadFileParameters
	 * @param sReadFileArena01
	 * @param sListReadFile
	 */
	public final void initiate(ReadFile sReadFileParameters, ReadFile sReadFileArena01, List<ReadFile> sListReadFile) {
		pReadFileParameters = sReadFileParameters;
		pReadFileArena01 = sReadFileArena01;
		pListReadFilesArena = sListReadFile;		
	}
	
	
	/**
	 * 
	 */
	public final void forceParameters() {
		for (List<String> lLine : pReadFileParameters.getmContentList()) {
			String lName = lLine.get(0);
			int lValue = BasicString.getInt(lLine.get(1));
			pIJUTManager.getpIJManager().getpIJGuiManager().setpParameter(lName, lValue);
		}
	}
	
	/**
	 * 
	 */
	public final void loadArenas() {
		pMapIdxRoundToIJUTArena = new HashMap<>();
		pMaxRound = 0;
		for (ReadFile lReadFileArena : pListReadFilesArena) {
			IJUTArena lIJUTArena = new IJUTArena(this);
			lIJUTArena.fillMap(lReadFileArena);
			if (lIJUTArena.getpIdxRound() == 0) {
				pIJUTArenaStart = lIJUTArena;
			} else {
				pMapIdxRoundToIJUTArena.put(lIJUTArena.getpIdxRound(), lIJUTArena);
			}
			pMaxRound = Math.max(pMaxRound, lIJUTArena.getpIdxRound());
		}
	}
	
	
	
	/**
	 * 
	 */
	public final void checkIJUTArenas() {
		for (IJUTArena lIJUTArena : pMapIdxRoundToIJUTArena.values()) {
			lIJUTArena.check();
		}
	}

	
	/*
	 * Getters & Setters
	 */
	public final String getpName() {
		return pName;
	}
	public final IJUTManager getpIJUTManager() {
		return pIJUTManager;
	}
	public final ReadFile getpReadFileParameters() {
		return pReadFileParameters;
	}
	public final ReadFile getpReadFileArena01() {
		return pReadFileArena01;
	}
	public final List<ReadFile> getpListReadFilesArena() {
		return pListReadFilesArena;
	}
	public final Map<Integer, IJUTArena> getpMapIdxRoundToIJUTArena() {
		return pMapIdxRoundToIJUTArena;
	}
	public final IJUTArena getpIJUTArenaStart() {
		return pIJUTArenaStart;
	}
	public final int getpMaxRound() {
		return pMaxRound;
	}
	
}
