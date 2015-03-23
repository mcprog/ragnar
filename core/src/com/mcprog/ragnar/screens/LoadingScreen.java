package com.mcprog.ragnar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.lib.Assets;
import com.mcprog.ragnar.lib.RagnarConfig;

public class LoadingScreen extends ScreenDrawable {
	
	private boolean setup = false;
	
	public LoadingScreen(Ragnar game) {
		super(game);

	}

    @Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (Assets.assetManager.update()) {

			/*
			 * Indicated Loading is done
			 */
			fontBatch.setProjectionMatrix(fontCamera.combined);
			fontBatch.begin();
			Assets.ragnarFont.draw(fontBatch, "Assets loaded", -fontCamera.viewportWidth / 6,  Assets.ragnarFont.getCapHeight() / 2);
			//Assets.ragnarFont.draw(fontBatch, "Continue", -fontCamera.viewportWidth / 8,  -Assets.ragnarFont.getCapHeight() * 2);
            fontBatch.end();
			setupAfterLoadOnce();
			//if (Gdx.input.justTouched()) {
				game.setScreen(game.menuScreen);
			//}
		} 
		else if (Assets.assetManager.isLoaded(Assets.VIKING_FONT_PATH)) {
			Assets.ragnarFont = Assets.getLoadedFont(Assets.VIKING_FONT_PATH);
			fontBatch.setProjectionMatrix(fontCamera.combined);
			fontBatch.begin();
			Assets.ragnarFont.draw(fontBatch, "Assets loading ... " + (int)Assets.getLoadingProgress() + "%", -fontCamera.viewportWidth / 4, Assets.ragnarFont.getCapHeight() / 2);
			fontBatch.end();
		}
	}
	
	private void setupAfterLoadOnce () {
		if (setup) {
			return;
		}
		/*
		 * Load mutations and assign vars
		 */
		Assets.playerAnimations = Assets.loadAnimation(Assets.PLAYER_PATH);
		Assets.playerGirlAnimations = Assets.loadAnimation(Assets.PLAYER_GIRL_PATH);
		Assets.assignLoadedAssets();
		/*
		 * instantiate screens
		 */
		RagnarConfig.init();
		Assets.testLoadI18N();
		game.gameScreen = new GameScreen(game);
		game.killScreen = new KillScreen(game);
		game.winScreen = new WinScreen(game);
		game.settingsScreen = new SettingsScreen(game);
		game.menuScreen = new MenuScreen(game);
		game.highscoreScreen = new HighscoreScreen(game);
		game.creditsScreen = new CreditsScreen(game);
        game.optionsScreen = new OptionsScreen(game);
		
		setup = true;
	}

}
