package com.mcprog.ragnar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.mcprog.ragnar.Ragnar;

public class PauseScreen extends ScreenAdapter {
	
	private Ragnar game;
	
	public PauseScreen(Ragnar game) {
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, .5f);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE) || Gdx.input.justTouched()) {
			game.setScreen(game.gameScreen);
		}
	}

}
