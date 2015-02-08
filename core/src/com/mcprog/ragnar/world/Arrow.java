package com.mcprog.ragnar.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;

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
		shape.setAsBox(1.5f, .1f);
		
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
	
	public static void drawArrows (SpriteBatch batch, Array<Body> bodies, Sprite currentPlayerSprite) {
		batch.begin();
		for (Body b : bodies) {
			if (b.getUserData() instanceof Sprite) {
				
				currentPlayerSprite = (Sprite) b.getUserData();
				if (currentPlayerSprite != null) {
					currentPlayerSprite.setOrigin(currentPlayerSprite.getWidth() / 2, currentPlayerSprite.getHeight() / 2);
					currentPlayerSprite.setBounds(b.getPosition().x - currentPlayerSprite.getWidth() / 2, b.getPosition().y - currentPlayerSprite.getHeight() / 2, 3, .5f);
					currentPlayerSprite.setRotation(b.getAngle() * MathUtils.radiansToDegrees);
					currentPlayerSprite.draw(batch);
				}
			}
			
		}
		batch.end();
	}
	
	public Body getBody () {
		return body;
	}

}
