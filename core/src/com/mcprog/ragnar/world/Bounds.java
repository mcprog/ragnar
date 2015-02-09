package com.mcprog.ragnar.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.World;

public class Bounds {
	
	private Body body;
	private BodyDef bodyDef;
	
	public Bounds(World world, float halfSide) {
		this(world, halfSide, halfSide);
//		bodyDef = new BodyDef();
//		bodyDef.type = BodyType.StaticBody;
//		
//		body = world.createBody(bodyDef);
//		ChainShape shape = new ChainShape();
//		Vector2[] vertices = {new Vector2(-halfSide, -halfSide), new Vector2(-halfSide, halfSide), new Vector2(halfSide, halfSide), new Vector2(halfSide, -halfSide)};
//		shape.createLoop(vertices);
//		body.createFixture(shape, 0);
//		
//		shape.dispose();
//		
//		body.setUserData("bounds");
	}
	
	public Bounds (World world, float hWidth, float hHeight) {
		bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		
		body = world.createBody(bodyDef);
		ChainShape shape = new ChainShape();
		Vector2[] vertices = {new Vector2(-hWidth, -hHeight), new Vector2(-hWidth, hHeight), new Vector2(hWidth, hHeight), new Vector2(hWidth, -hHeight)};
		shape.createLoop(vertices);
		body.createFixture(shape, 0);
		
		shape.dispose();
		
		body.setUserData("bounds");
	}
	
	public Body getBody () {
		return body;
	}

}
