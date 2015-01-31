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
	
	public ArrowSpawner(World world, Player player) {
		this.world = world;
		this.player = player;
	}
	
	public void spawn () {
		angle = Math.random() * MathUtils.PI2;
		radius = Math.sqrt(Math.pow(Gdx.graphics.getHeight() / 16, 2) + Math.pow(Gdx.graphics.getWidth() / 16, 2));
		arrow = new Arrow(world, new Vector2((float)(radius * Math.cos(angle) + player.getBody().getPosition().x), (float)(radius * Math.sin(angle)) + player.getBody().getPosition().y), (float) angle);
	}

}
