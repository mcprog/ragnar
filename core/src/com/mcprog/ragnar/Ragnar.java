package com.mcprog.ragnar;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mcprog.ragnar.lib.Assets;
import com.mcprog.ragnar.screens.GameScreen;
import com.mcprog.ragnar.screens.KillScreen;
import com.mcprog.ragnar.screens.LoadingScreen;
import com.mcprog.ragnar.screens.PauseScreen;
import com.mcprog.ragnar.screens.WinScreen;
import com.mcprog.ragnar.utility.DebugUtility;

public class Ragnar extends Game {
	
	public GameScreen gameScreen;
	public KillScreen killScreen;
	public PauseScreen pauseScreen;
	public LoadingScreen loadingScreen;
	public WinScreen winScreen;
	public static DebugUtility debugger;
	public static boolean isMobile;
	
	@Override
	public void create () {
		isMobile = Gdx.app.getType().equals(ApplicationType.Android) || Gdx.app.getType().equals(ApplicationType.iOS);
		debugger = new DebugUtility();
		debugger.off();
		Assets.queueFonts();
		Assets.queueTextures();
		loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
	}
	
	public void setToKillScreen (int deathType) {
		killScreen.deathType = deathType;
		setScreen(killScreen);
	}
}
