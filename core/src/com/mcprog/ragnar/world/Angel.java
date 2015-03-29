package com.mcprog.ragnar.world;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.lib.Assets;
import com.mcprog.ragnar.lib.RagnarConfig;

public class Angel extends Player {

	private Sprite gust;
	private final float angelSpeed = 2;
    private float minBumpSpeed = 10;
    private float maxBumpSpeed = 20;
    private TextureRegion wingsFrame;
    private Sprite wingsSprite;

	public Angel(World world, Vector2 position, OrthographicCamera camera) {
		super(world, position, camera, Ragnar.gameInstance.gameScreen);
        playerSpeed = 8;
        body.getFixtureList().first().setRestitution(.5f);
        body.setLinearDamping(1);

	}

    public void draw (float stateTime, SpriteBatch batch) {
        switch (RagnarConfig.playerType) {
            case 1:

                frame = Assets.playerGirlAnimations[getDirection()].getKeyFrame(stateTime, true);
                break;

            default:
                frame = Assets.playerAnimations[getDirection()].getKeyFrame(stateTime, true);
                break;
        }
        wingsFrame = Assets.wingsAnimation.getKeyFrame(stateTime, true);
        wingsSprite = new Sprite(wingsFrame);
        wingsSprite.setCenter(getPosition().x, getPosition().y);
        wingsSprite.setScale(.125f);

        frameSprite = new Sprite(frame);
        frameSprite.setCenter(getPosition().x, getPosition().y);
        frameSprite.setScale(.125f);

        batch.begin();

        wingsSprite.draw(batch);
        frameSprite.draw(batch);
        if (invincible) {
            getGlow().draw(batch);
        }
        batch.end();
    }

    @Override
    public void update(float delta) {

        if (left) {
            direction = LEFT;
            body.setLinearVelocity(-playerSpeed, getVelocity().y);
        }
        if (right) {
            direction = RIGHT;
            body.setLinearVelocity(playerSpeed, getVelocity().y);
        }
        if (left && right) {
            body.setLinearVelocity(0, 0);
        }
        setToIdle();
        if (Ragnar.isMobile) {
            //setLinearVelocityAndDirection(mVX, mVY);
        } else {
            //setLinearVelocityAndDirection(vX, vY);

        }
        if (getPosition().y < 0) {
            body.applyLinearImpulse(new Vector2(0, (float)(Math.random() * (maxBumpSpeed - minBumpSpeed)) + minBumpSpeed), getPosition(), true);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.A) {

            left = true;
        }
        if (keycode == Keys.D) {

            right = true;

        }
        return true;
    }
}
