/*
 * Copyright 2015 Michael Curtis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
import com.mcprog.ragnar.screens.OptionsScreen;
import com.mcprog.ragnar.screens.SettingsScreen;
import com.mcprog.ragnar.screens.WinScreen;
import com.mcprog.ragnar.services.IAdRefresher;
import com.mcprog.ragnar.services.IGooglePlayGameServices;
import com.mcprog.ragnar.utility.DebugUtility;

public class Ragnar extends Game {

    /*
    Screen instances
     */
    public GameScreen gameScreen;
    public KillScreen killScreen;
    public LoadingScreen loadingScreen;
    public SettingsScreen settingsScreen;
    public WinScreen winScreen;
    public MenuScreen menuScreen;
    public HighscoreScreen highscoreScreen;
    public CreditsScreen creditsScreen;
    public OptionsScreen optionsScreen;

    /*
    Debugging
     */
    public static DebugUtility debugger;

    /*
    Mobile components
     */
    public static boolean isMobile;
    public IGooglePlayGameServices gpgs;
    public IAdRefresher adRefresher;

    //Static instance of this object
    public static Ragnar gameInstance;

    /*
    Constant IDs for setToScreen(id) method
     */
    public static final int GAME_ID = 0;
    public static final int KILL_ID = 1;
    public static final int LOADING_ID = 2;
    public static final int SETTINGS_ID = 3;
    public static final int WIN_ID = 4;
    public static final int MENU_ID = 5;
    public static final int HIGHSCORE_ID = 6;
    public static final int CREDITS_ID = 7;
    public static final int OPTIONS_ID = 8;

    /**
     * Constructor called by android launcher
     * @param gpgs: Google Play Game Service (i.e., the launcher)
     * @param adRefresher: AdMob (i.e., the launcher)
     */
	public Ragnar(IGooglePlayGameServices gpgs, IAdRefresher adRefresher) {
        this.gpgs = gpgs;
        this.adRefresher = adRefresher;
	}

    /**
     * Default constructor
     * Desktop calls this
     * HTML calls this
     */
    public Ragnar () {
        //Explicit default constructor
    }
	
	@Override
	public void create () {
        Gdx.input.setCatchBackKey(true);
		gameInstance = (Ragnar) (Gdx.app.getApplicationListener());
		isMobile = Gdx.app.getType().equals(ApplicationType.Android) || Gdx.app.getType().equals(ApplicationType.iOS);
		debugger = new DebugUtility();
		debugger.off();//keep off for releases
		Assets.queueAll();
		loadingScreen = new LoadingScreen(gameInstance);
		setScreen(loadingScreen);
	}


    @Override
    public void dispose() {
        super.dispose();
        System.out.println("Ragnar disposed");

    }

    /**
     * Special method for setting screen to kill screen
     * Uses an id for how the player died
     * @param deathType: id for player death (Stabbed, Shot, or Meteored)
     */
    public void setToKillScreen (int deathType) {
		killScreen.deathType = deathType;
		setScreen(killScreen);
	}

    /**
     * Convenience Method to statically call screens
     * Sets screen to the screen with the id called
     * @param screenID: id that corresponds with screen to switch to
     */
    public void setToScreen (int screenID) {
        switch (screenID) {
            case GAME_ID:
                setScreen(gameScreen);
                break;
            case KILL_ID:
                setScreen(killScreen);
                break;
            case LOADING_ID:
                setScreen(loadingScreen);
                break;
            case SETTINGS_ID:
                setScreen(settingsScreen);
                break;
            case WIN_ID:
                setScreen(winScreen);
                break;
            case MENU_ID:
                setScreen(menuScreen);
                break;
            case HIGHSCORE_ID:
                setScreen(highscoreScreen);
                break;
            case CREDITS_ID:
                setScreen(creditsScreen);
                break;
            case OPTIONS_ID:
                setScreen(optionsScreen);
                break;
            default:
                //no default
                break;
        }
    }
}
