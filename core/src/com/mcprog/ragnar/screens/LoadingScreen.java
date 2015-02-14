package com.mcprog.ragnar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.lib.Assets;
import com.mcprog.ragnar.lib.RagnarConfig;

public class LoadingScreen extends ScreenDrawable {
	
	
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
			Assets.ragnarFont.draw(fontBatch, "Assets loaded", -fontCamera.viewportWidth / 4,  Assets.ragnarFont.getCapHeight() / 2);
			fontBatch.end();
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
			game.gameScreen = new GameScreen(game);
			game.killScreen = new KillScreen(game);
			game.winScreen = new WinScreen(game);
			game.settingsScreen = new SettingsScreen(game);
			if (Gdx.input.justTouched()) {
				game.setScreen(game.settingsScreen);
			}
		} 
		else if (Assets.assetManager.isLoaded(Assets.VIKING_FONT_PATH)) {
			Assets.ragnarFont = Assets.getLoadedFont(Assets.VIKING_FONT_PATH);
			fontBatch.setProjectionMatrix(fontCamera.combined);
			fontBatch.begin();
			Assets.ragnarFont.draw(fontBatch, "Assets loadeding ..." + (int)Assets.getLoadingProgress() + "%", -fontCamera.viewportWidth / 4, Assets.ragnarFont.getCapHeight() / 2);
			fontBatch.end();
		}
	}

}
