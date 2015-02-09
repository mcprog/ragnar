package com.mcprog.ragnar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.lib.Assets;

public class LoadingScreen extends ScreenAdapter {
	
	private Ragnar game;
	private SpriteBatch batch;
	
	public LoadingScreen(Ragnar game) {
		this.game = game;
		batch = new SpriteBatch();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (Assets.assetManager.update()) {
			/*
			 * Indicated Loading is done
			 */
			batch.begin();
			Assets.ragnarFont.draw(batch, "Assets loaded", 10, 30);
			batch.end();
			/*
			 * Load mutations and assign vars
			 */
			Assets.loadAnimations();
			Assets.assignLoadedAssets();
			/*
			 * instantiate screens
			 */
			game.gameScreen = new GameScreen(game);
			game.killScreen = new KillScreen(game);
			game.winScreen = new WinScreen(game);
			if (Gdx.input.justTouched()) {
				
				game.setScreen(game.gameScreen);
			}
		} 
		else if (Assets.assetManager.isLoaded(Assets.VIKING_FONT_PATH)) {
			Assets.ragnarFont = Assets.getLoadedFont(Assets.VIKING_FONT_PATH);
			batch.begin();
			Assets.ragnarFont.draw(batch, "Assets loadeding ..." + (int)Assets.getLoadingProgress() + "%", 10, 30);
			batch.end();
		}
	}

}
