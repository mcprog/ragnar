package com.mcprog.ragnar.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mcprog.ragnar.lib.Constants;

/**
 * Created by Michael on 3/28/2015.
 */
public class Meteor {

    private World world;
    private BodyDef bodyDef;
    private Vector2 startPosition;
    private Sprite sprite;
    private Body body;
    private float rotation;
    private float rotationSpeed;
    private float radius;

    public Meteor (World world) {
        this.world = world;
        radius = (float)(Math.random() * 4 + 1);
        rotationSpeed = (float)(Math.random() * 150 + 50);

        startPosition =
                new Vector2((float)(Math.random() * Constants.SCALED_WIDTH - Constants.SCALED_WIDTH / 2),
                        (float)(Math.random() * Constants.SCALED_HEIGHT * 2.5f + Constants.SCALED_HEIGHT / 2));

        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(startPosition);

        CircleShape shape = new CircleShape();
        shape.setRadius(radius);

        body = world.createBody(bodyDef);
        body.createFixture(shape, 10);
        shape.dispose();
        body.setFixedRotation(true);// TODO maybe change.
        body.setLinearVelocity(0, -15);
        body.setUserData("meteor");

        sprite = new Sprite(new Texture(Gdx.files.internal("meteor.png")));


    }

    public void update (float delta, SpriteBatch batch) {
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setBounds(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2,
                radius * 2, radius * 2);
        //rotation += delta;
        rotation += delta * rotationSpeed;
        sprite.setRotation(rotation);
        batch.begin();
        sprite.draw(batch);
        batch.end();

        if (body.getPosition().y < -Constants.SCALED_HEIGHT) {
            respawn();
        }
    }

    private void respawn () {
        radius = (float)(Math.random() * 4 + 1);
        rotationSpeed = (float)(Math.random() * 150 + 50);
        startPosition =
                new Vector2((float)(Math.random() * Constants.SCALED_WIDTH - Constants.SCALED_WIDTH / 2),
                (float)(Math.random() * Constants.SCALED_HEIGHT * 2.5f + Constants.SCALED_HEIGHT / 2));
        body.destroyFixture(body.getFixtureList().first());
        CircleShape shape = new CircleShape();
        shape.setRadius(radius);
        body.createFixture(shape, 10);
        shape.dispose();
        body.setTransform(startPosition, rotation);
        body.setLinearVelocity(0, -15);
    }
}
