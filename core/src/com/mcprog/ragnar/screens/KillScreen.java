package com.mcprog.ragnar.screens;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.lib.Assets;

public class KillScreen extends ScreenDrawable {

	private Ragnar game;
	public static final int SHOT = 0;
	public static final int STABBED = 1;
	public static final String SHOT_MSG = "You got shot by the bowmen";
	public static final String STABBED_MSG = "You got too close to the english and they speared you";
	
	public int deathType = -1;
	
	public KillScreen(Ragnar gameInstance) {
		super(gameInstance);
		game = gameInstance;
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		Gdx.gl.glClearColor(.2f, .2f, .2f, 1);//Red
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		if (Gdx.app.getType().equals(ApplicationType.Android) || Gdx.app.getType().equals(ApplicationType.iOS)) {
			Assets.ragnarFont.drawWrapped(batch, assembleMessage() + "\nTap screen to retry\nYou lasted " + (int)(game.gameScreen.timeInGame) + " seconds", -camera.viewportWidth * .4f, camera.viewportHeight * .25f, Gdx.graphics.getWidth()  * .8f, HAlignment.CENTER);
		} else {
			Assets.ragnarFont.drawWrapped(batch, assembleMessage() + "\nHit \"R\" to retry\nYou lasted " + (int)(game.gameScreen.timeInGame) + " seconds", -camera.viewportWidth * .4f, camera.viewportHeight * .25f, Gdx.graphics.getWidth()  * .8f, HAlignment.CENTER);
		}
		Assets.deadPlayerSprite.setScale(3);
		Assets.deadPlayerSprite.draw(batch);
		
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
	
	public String assembleMessage () {
		return deathType == SHOT ? SHOT_MSG : STABBED_MSG;
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
