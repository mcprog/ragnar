package com.mcprog.ragnar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcprog.ragnar.Ragnar;

public class KillScreen implements Screen {

	private Ragnar game;
	private BitmapFont font;
	private SpriteBatch batch;
	public String deathMsg;
	
	public KillScreen(Ragnar gameInstance) {
		game = gameInstance;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);//Red
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		font.drawMultiLine(batch, deathMsg + "\nHit \"R\" retry\nYou lasted " + (int)(game.gameScreen.timeInGame) + " seconds", Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() * 7 / 8);
		
		batch.end();
		
		if (Gdx.input.isKeyJustPressed(Keys.R)) {
			game.gameScreen.timeInGame = 0;
			game.setScreen(game.gameScreen);
		}
		
		
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		
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
