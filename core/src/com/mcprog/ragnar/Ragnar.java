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
	
	public GameScreen gameScreen;
	public KillScreen killScreen;
	public LoadingScreen loadingScreen;
	public SettingsScreen settingsScreen;
	public WinScreen winScreen;
	public MenuScreen menuScreen;
	public HighscoreScreen highscoreScreen;
	public CreditsScreen creditsScreen;
    public OptionsScreen optionsScreen;
	
	public static DebugUtility debugger;
	public static boolean isMobile;
	
	public IGooglePlayGameServices gpgs;
    public IAdRefresher adRefresher;
	
	public static Ragnar gameInstance;

    public static final int GAME_ID = 0;
    public static final int KILL_ID = 1;
    public static final int LOADING_ID = 2;
    public static final int SETTINGS_ID = 3;
    public static final int WIN_ID = 4;
    public static final int MENU_ID = 5;
    public static final int HIGHSCORE_ID = 6;
    public static final int CREDITS_ID = 7;
    public static final int OPTIONS_ID = 8;
	
	public Ragnar(IGooglePlayGameServices gpgs, IAdRefresher adRefresher) {
		this.gpgs = gpgs;
        this.adRefresher = adRefresher;
	}
	
	public Ragnar() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void create () {
		gameInstance = (Ragnar) (Gdx.app.getApplicationListener());
		isMobile = Gdx.app.getType().equals(ApplicationType.Android) || Gdx.app.getType().equals(ApplicationType.iOS);
		debugger = new DebugUtility();
		debugger.off();
		Assets.queueAll();
		loadingScreen = new LoadingScreen(gameInstance);
		setScreen(loadingScreen);
	}


    @Override
    public void dispose() {
        super.dispose();
        System.out.println("Ragnar disposed");

    }

    public void setToKillScreen (int deathType) {
		killScreen.deathType = deathType;
		setScreen(killScreen);
	}

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
                break;
        }
    }
}
