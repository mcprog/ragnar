package com.mcprog.ragnar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.lib.Assets;

public class SettingsScreen extends ScreenDrawable {

	private Stage stage;
	private Table table;
	
	private ShapeRenderer debugRenderer;
	
	public SettingsScreen(Ragnar game) {
		super(game);
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
		
		debugRenderer = new ShapeRenderer();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.5f, .5f, .5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		fontBatch.setProjectionMatrix(fontCamera.combined);
		fontBatch.begin();
		Assets.ragnarFont.draw(fontBatch, "Settings", 0, fontCamera.viewportWidth / 4);
		fontBatch.end();
		stage.act(delta);
	    stage.draw();

	    table.drawDebug(debugRenderer); // This is optional, but enables debug lines for tables.
		
	}

}
