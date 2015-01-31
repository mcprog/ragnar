package com.mcprog.ragnar;

import com.badlogic.gdx.Game;
import com.mcprog.ragnar.lib.Assets;
import com.mcprog.ragnar.screens.GameScreen;
import com.mcprog.ragnar.screens.KillScreen;

public class Ragnar extends Game {
	
	public GameScreen gameScreen;
	public KillScreen killScreen;
	
	@Override
	public void create () {
		Assets.loadAnimations();
		gameScreen = new GameScreen(this);
		killScreen = new KillScreen(this);
		setScreen(gameScreen);
	}
	
	public void setToKillScreen (String deathMsg) {
		killScreen.deathMsg = deathMsg;
		setScreen(killScreen);
	}
}
