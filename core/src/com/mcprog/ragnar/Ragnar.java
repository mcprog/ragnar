package com.mcprog.ragnar;

import com.badlogic.gdx.Game;
import com.mcprog.ragnar.lib.Assets;
import com.mcprog.ragnar.screens.GameScreen;
import com.mcprog.ragnar.screens.KillScreen;
import com.mcprog.ragnar.screens.PauseScreen;
import com.mcprog.ragnar.screens.WinScreen;
import com.mcprog.ragnar.utility.DebugUtility;

public class Ragnar extends Game {
	
	public GameScreen gameScreen;
	public KillScreen killScreen;
	public PauseScreen pauseScreen;
	public WinScreen winScreen;
	public static DebugUtility debugger;
	
	@Override
	public void create () {
		debugger = new DebugUtility();
		debugger.on();
		Assets.loadAnimations();
		Assets.loadSprites();
		gameScreen = new GameScreen(this);
		killScreen = new KillScreen(this);
		pauseScreen = new PauseScreen(this);
		winScreen = new WinScreen(this);
		setScreen(gameScreen);
		Assets.loadFonts();
	}
	
	public void setToKillScreen (String deathMsg) {
		killScreen.deathMsg = deathMsg;
		setScreen(killScreen);
	}
}
