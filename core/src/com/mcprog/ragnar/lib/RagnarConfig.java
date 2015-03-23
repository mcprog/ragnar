package com.mcprog.ragnar.lib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class RagnarConfig {

	public static Preferences preferences;
	
	public static int playerType;
	public static int highScore;
	public static boolean vibrate;
	public static boolean sound;
    public static boolean isPauseOnRight;
    public static boolean gpgsEnabled;
	
	public static void init () {
		preferences = Gdx.app.getPreferences("ragnar-preferences");
		playerType = preferences.getInteger("player-type", 0);
		highScore = preferences.getInteger("highscore", 0);
		vibrate = preferences.getBoolean("vibrate", true);
		sound = preferences.getBoolean("sound", true);
        sound = preferences.getBoolean("pauseRight", true);
        gpgsEnabled = preferences.getBoolean("gpgs", true);
		//preferences.putInteger("highscore", 0);
        //preferences.clear();
		//preferences.flush();
	}
	
	public static void updateFile () {
		preferences.putInteger("player-type", playerType);
		preferences.putInteger("highscore", highScore);
		preferences.putBoolean("vibrate", vibrate);
		preferences.putBoolean("sound", sound);
        preferences.putBoolean("pauseRight", isPauseOnRight);
        preferences.putBoolean("gpgs", gpgsEnabled);
		preferences.flush();
	}
}
