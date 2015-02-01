package com.mcprog.ragnar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.world.Angel;
import com.mcprog.ragnar.world.Player;

public class WinScreen extends ScreenDrawable {
	
	private World world;
	private Angel angel;
	
	public WinScreen(Ragnar game) {
		super(game);
		world = new World(new Vector2(0, -9.81f), true);
		angel = new Angel(world, Vector2.Zero);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, .69f, 1, 1);//Color of Valhalla
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world.step(1/60f, 8, 3);
		
		batch.begin();
		angel.update(delta, null);
	}

}
