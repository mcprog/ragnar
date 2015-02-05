package com.mcprog.ragnar.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class ArrowSpawner {
	
	private World world;
	private Arrow arrow;
	private double angle;
	private double radius;
	private Player player;
	private int arrowsLeft;
	
	public ArrowSpawner(World world, Player player) {
		this.world = world;
		this.player = player;
		arrowsLeft = 40;
	}
	
	public void spawn (int bowmen) {
		if (arrowsLeft > 0) {
			angle = Math.random() * MathUtils.PI2;
			radius = Math.sqrt(Math.pow(Gdx.graphics.getHeight() / 16, 2) + Math.pow(Gdx.graphics.getWidth() / 16, 2));
			arrow = new Arrow(world, new Vector2((float)(radius * Math.cos(angle) + player.getBody().getPosition().x), (float)(radius * Math.sin(angle)) + player.getBody().getPosition().y), (float) angle);
			--arrowsLeft;
		} else {
			System.err.println("You won!");
		}
	}
	
	public int getArrowsLeft () {
		return arrowsLeft;
	}
	
	public boolean getWin () {
		return arrowsLeft <= 0;
		
	}

}
