package com.mcprog.ragnar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.gui.SettingsTable;

public class SettingsScreen extends ScreenDrawable {

	private Stage stage;
	private ShapeRenderer debugLines;
	private SettingsTable settingsTable;
	
	public SettingsScreen(Ragnar game) {
		super(game);
		settingsTable = new SettingsTable();
		settingsTable.setFillParent(true);
		debugLines = new ShapeRenderer();
		stage = new Stage();
		
		stage.addActor(settingsTable);
//		stage.setDebugAll(true);
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		settingsTable.show();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.5f, .5f, .5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
		
	}
	
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}
	
	@Override
	public void dispose() {
		stage.dispose();
		
	}

}
