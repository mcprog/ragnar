package com.mcprog.ragnar.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Arrow {
	
	private World world;
	private BodyDef bodyDef;
	private Vector2 position;
	private Sprite sprite;
	private Body body;
	private Vector2 movementRaw;
	
	public Arrow(World world, Vector2 position, float rotation) {
		this.world = world;
		bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		this.position = position;
		bodyDef.position.set(position);
		
		
		sprite = new Sprite(new Texture(Gdx.files.internal("arrow.png")));
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(1.5f, .15f);
		
		movementRaw = new Vector2((float)(-Math.cos(rotation)), (float)(-Math.sin(rotation)));
		movementRaw.scl((float) (Math.random() * 5) + 15);
		body = world.createBody(bodyDef);
		body.createFixture(shape, 1);
		shape.dispose();
		body.setTransform(position, rotation);
		body.setFixedRotation(true);// TODO maybe change.
		body.setLinearVelocity(movementRaw);
		body.setUserData(sprite);
		
		
		
	}
	
	public Body getBody () {
		return body;
	}

}
