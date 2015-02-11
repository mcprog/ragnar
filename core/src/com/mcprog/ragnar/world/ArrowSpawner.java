package com.mcprog.ragnar.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mcprog.ragnar.Ragnar;

public class ArrowSpawner {
	
	private World world;
	private Arrow arrow;
	private double angle;
	private double radius;
	private Player player;
	private int arrowsLeft;
	private float spawnTimer;
	private float spawnInterval;
	
	public ArrowSpawner(World world, Player player) {
		this.world = world;
		this.player = player;
		arrowsLeft = 300;
		spawnInterval = 1;
		Ragnar.debugger.setTimer(getClass().getSimpleName(), 0);
	}
	
	public void spawn (float delta) {
		spawnTimer += delta;
		Ragnar.debugger.addTimeToTimer(getClass().getSimpleName(), delta);
		if (spawnTimer > spawnInterval) {
			angle = Math.random() * MathUtils.PI2;
			radius = Math.sqrt(Math.pow(Gdx.graphics.getHeight() / 16, 2) + Math.pow(Gdx.graphics.getWidth() / 16, 2));
			arrow = new Arrow(world, new Vector2((float)(radius * Math.cos(angle) + player.getBody().getPosition().x), (float)(radius * Math.sin(angle)) + player.getBody().getPosition().y), (float) angle);
			
			if (arrowsLeft > 0) {
				--arrowsLeft;
			}
			spawnTimer = 0;
			if (spawnInterval > .5f) {
				spawnInterval -= .0025f;
				Ragnar.debugger.logDebug("Spawn Interval Decreasing: " + spawnInterval);
			} else {
				Ragnar.debugger.logTimer("Spawn Interval Decreased for", getClass().getSimpleName(), "seconds");
			}
			
		}
	}
	
	@Deprecated
	public void checkWin (Ragnar game) {
		if (!Ragnar.debugger.on && getWin()) {
			game.setScreen(game.winScreen);
		}
	}
	
	public int getArrowsLeft () {
		return arrowsLeft;
	}
	
	public boolean getWin () {
		return arrowsLeft <= 0;
		
	}

}
