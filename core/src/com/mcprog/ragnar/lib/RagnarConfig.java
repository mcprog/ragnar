package com.mcprog.ragnar.lib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;

public class RagnarConfig {

	public static Preferences preferences;
	
	public static int playerType;
	
	public static void init () {
		preferences = Gdx.app.getPreferences("ragnar-preferences");
		playerType = preferences.getInteger("player-type", 0);
	}
	
	public static void updateFile () {
		preferences.putInteger("player-type", playerType);
		preferences.flush();
	}
}
