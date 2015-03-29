package com.mcprog.ragnar.world;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Michael on 3/28/2015.
 */
public class Meteor {

    private World world;
    private BodyDef bodyDef;
    private Vector2 position;
    private Sprite sprite;
    private Body body;

    public Meteor (World world) {
        this.world = world;

        position = new Vector2(0, 8);

        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position);

        CircleShape shape = new CircleShape();
        shape.setRadius(2);

        body = world.createBody(bodyDef);
        body.createFixture(shape, 3);
        shape.dispose();
        body.setFixedRotation(true);// TODO maybe change.
        body.setLinearVelocity(0, -15);
        body.setUserData("meteor");
    }
}
