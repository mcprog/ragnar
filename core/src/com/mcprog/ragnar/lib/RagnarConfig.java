package com.mcprog.ragnar.lib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class RagnarConfig {

	
	public static int playerType;
	public static int highScore;
	public static boolean vibrate;
	public static boolean sound;
    public static boolean isPauseOnRight;
    public static boolean gpgsEnabled;
	
	public static void init () {
		Preferences preferences = Gdx.app.getPreferences("ragnar-preferences");
		playerType = preferences.getInteger("player-type", 0);
		highScore = preferences.getInteger("highscore", 0);
		vibrate = preferences.getBoolean("vibrate", true);
		sound = preferences.getBoolean("sound", true);
        isPauseOnRight = preferences.getBoolean("pauseRight", true);
        gpgsEnabled = preferences.getBoolean("gpgs", true);
		//preferences.putInteger("highscore", 0);
        //preferences.clear();
		//preferences.flush();
        //preferences.flush();
        updateFile();
	}
	
	public static void updateFile () {
        Preferences preferences = Gdx.app.getPreferences("ragnar-preferences");
		preferences.putInteger("player-type", playerType).flush();
		preferences.putInteger("highscore", highScore).flush();
		preferences.putBoolean("vibrate", vibrate).flush();
		preferences.putBoolean("sound", sound).flush();
        preferences.putBoolean("pauseRight", isPauseOnRight).flush();
        preferences.putBoolean("gpgs", gpgsEnabled).flush();
		//preferences.flush();
	}
}
