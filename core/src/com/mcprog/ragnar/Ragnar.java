package com.mcprog.ragnar;

import com.badlogic.gdx.Game;
import com.mcprog.ragnar.lib.Assets;
import com.mcprog.ragnar.screens.GameScreen;
import com.mcprog.ragnar.screens.KillScreen;
import com.mcprog.ragnar.screens.PauseScreen;

public class Ragnar extends Game {
	
	public GameScreen gameScreen;
	public KillScreen killScreen;
	public PauseScreen pauseScreen;
	
	@Override
	public void create () {
		Assets.loadAnimations();
		gameScreen = new GameScreen(this);
		killScreen = new KillScreen(this);
		pauseScreen = new PauseScreen(this);
		setScreen(gameScreen);
		Assets.loadFonts();
	}
	
	public void setToKillScreen (String deathMsg) {
		killScreen.deathMsg = deathMsg;
		setScreen(killScreen);
	}
}
