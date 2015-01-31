package com.mcprog.ragnar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.lib.Assets;

public class KillScreen implements Screen {

	private Ragnar game;
	private SpriteBatch batch;
	public String deathMsg;
	private OrthographicCamera camera;
	
	public KillScreen(Ragnar gameInstance) {
		game = gameInstance;
		camera = new OrthographicCamera();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);//Red
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		if (Gdx.app.getType().equals(ApplicationType.Android) || Gdx.app.getType().equals(ApplicationType.iOS)) {
			Assets.ragnarFont.drawMultiLine(batch, deathMsg + "\nTap screen to retry\nYou lasted " + (int)(game.gameScreen.timeInGame) + " seconds", -camera.viewportWidth * .375f, camera.viewportHeight * .25f);
		} else {
			Assets.ragnarFont.drawMultiLine(batch, deathMsg + "\nHit \"R\" to retry\nYou lasted " + (int)(game.gameScreen.timeInGame) + " seconds", -camera.viewportWidth * .375f, camera.viewportHeight * .25f);
		}
		
		batch.end();
		
		if (Gdx.input.isKeyJustPressed(Keys.R)) {
			game.gameScreen.timeInGame = 0;
			game.setScreen(game.gameScreen);
		}
		if (Gdx.app.getType().equals(ApplicationType.Android) || Gdx.app.getType().equals(ApplicationType.Android)) {
			if (Gdx.input.justTouched()) {
				game.gameScreen.timeInGame = 0;
				game.setScreen(game.gameScreen);
			}
		}
		
		
		
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.update();
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
