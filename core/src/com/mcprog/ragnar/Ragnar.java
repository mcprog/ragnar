package com.mcprog.ragnar;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mcprog.ragnar.lib.Assets;
import com.mcprog.ragnar.screens.CreditsScreen;
import com.mcprog.ragnar.screens.GameScreen;
import com.mcprog.ragnar.screens.HighscoreScreen;
import com.mcprog.ragnar.screens.KillScreen;
import com.mcprog.ragnar.screens.LoadingScreen;
import com.mcprog.ragnar.screens.MenuScreen;
import com.mcprog.ragnar.screens.SettingsScreen;
import com.mcprog.ragnar.screens.WinScreen;
import com.mcprog.ragnar.utility.DebugUtility;

public class Ragnar extends Game {
	
	public GameScreen gameScreen;
	public KillScreen killScreen;
	public LoadingScreen loadingScreen;
	public SettingsScreen settingsScreen;
	public WinScreen winScreen;
	public MenuScreen menuScreen;
	public HighscoreScreen highscoreScreen;
	public CreditsScreen creditsScreen;
	public static DebugUtility debugger;
	public static boolean isMobile;
	
	public static Ragnar gameInstance;
	
	@Override
	public void create () {
		gameInstance = (Ragnar) (Gdx.app.getApplicationListener());
		isMobile = Gdx.app.getType().equals(ApplicationType.Android) || Gdx.app.getType().equals(ApplicationType.iOS);
		debugger = new DebugUtility();
		debugger.off();
		Assets.queueAll();
		loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
	}
	
	public void setToKillScreen (int deathType) {
		killScreen.deathType = deathType;
		setScreen(killScreen);
	}
}
