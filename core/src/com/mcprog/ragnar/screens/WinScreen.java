package com.mcprog.ragnar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.world.Angel;
import com.mcprog.ragnar.world.Bounds;
import com.mcprog.ragnar.world.Player;

public class WinScreen extends ScreenDrawable {
	
	private World world;
	private Angel angel;
	private float stateTime;
	private Bounds bounds;
	
	
	public WinScreen(Ragnar game) {
		super(game);
		world = new World(new Vector2(0, -9.81f), true);
		angel = new Angel(world, Vector2.Zero);
		bounds = new Bounds(world, Gdx.graphics.getHeight() / 32);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, .69f, 1, 1);//Color of Valhalla
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stateTime += delta;
		world.step(1/60f, 8, 3);
		batch.setProjectionMatrix(camera.combined);
		angel.update(delta, null);
		batch.begin();
		angel.getDraw(stateTime).draw(batch);
		if (Gdx.input.isKeyPressed(Keys.W)) {
			
			angel.getGust().draw(batch);
		}
		batch.end();
	}
	
	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width / 16;
		camera.viewportHeight = height / 16;
		camera.update();
	}
	
	

}
