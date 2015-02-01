package com.mcprog.ragnar.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcprog.ragnar.Ragnar;

public class ScreenDrawable extends ScreenAdapter {
	
	protected Ragnar game;
	protected SpriteBatch batch;
	protected OrthographicCamera camera;
	
	
	public ScreenDrawable(Ragnar game) {
		this.game = game;
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
	}
	
	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.update();
	}

}
