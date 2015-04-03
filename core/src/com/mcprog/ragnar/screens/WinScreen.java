package com.mcprog.ragnar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.lib.Assets;
import com.mcprog.ragnar.lib.Constants;
import com.mcprog.ragnar.world.Angel;
import com.mcprog.ragnar.world.Bounds;
import com.mcprog.ragnar.world.Meteor;

public class WinScreen extends ScreenDrawable implements ContactListener {
	
	private World world;
	private Angel angel;
	private float stateTime;
	private Bounds bounds;
	private Sprite leftSidebar;
	private Sprite rightSidebar;
	private SpriteBatch fontBatch;
	private ShapeRenderer shapeRenderer;
    //private Meteor testMeteor;
    private Meteor[] meteors;
	
	public WinScreen(Ragnar game) {
		super(game);

        meteors = new Meteor[10];
	}

    @Override
    public void show() {
        world = new World(new Vector2(0, -9.81f), true);
        //testMeteor = new Meteor(world);
        for (int i = 0; i < meteors.length; ++i) {
            meteors[i] = new Meteor(world);
        }
        angel = new Angel(world, Vector2.Zero, camera);
        //bounds = new Bounds(world, Gdx.graphics.getWidth() / 32f, Gdx.graphics.getHeight() / 32f);
        leftSidebar = new Sprite(new Texture(Gdx.files.internal("heaven_sidebar.png")));
        rightSidebar = new Sprite(new Texture(Gdx.files.internal("heaven_sidebar.png")));
        fontBatch = new SpriteBatch();
        Gdx.input.setInputProcessor(angel);
        Gdx.input.setCatchBackKey(true);
        if (Ragnar.isMobile) {
            game.gpgs.unlockAchievement(4);
        }
        if (Ragnar.isMobile) {
            game.gpgs.submitHighscore((int) game.gameScreen.timeInGame);
        }
        Gdx.input.setInputProcessor(angel);
    }

    @Override
	public void render(float delta) {
		super.render(delta);
		Gdx.gl.glClearColor(0, .69f, 1, 1);//Color of Valhalla
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        handleBarrierDeath();
		
		if (Gdx.input.isKeyJustPressed(Keys.BACK)) {
			game.setScreen(game.gameScreen);
		}


		
		stateTime += delta;
		world.step(1/60f, 8, 3);
		angel.update(delta);

        for (Meteor m : meteors) {
            m.update(delta, batch);
        }
		
		fontBatch.setProjectionMatrix(fontCamera.combined);
		fontBatch.begin();
//		Assets.ragnarFont.draw(fontBatch, "You Win Entrance to Valhalla", -fontCamera.viewportWidth / 4, fontCamera.viewportHeight / 4);
		Assets.ragnarFont.drawWrapped(fontBatch, "You Win Entrance to Valhalla", -fontCamera.viewportWidth * .375f, fontCamera.viewportHeight * .375f, fontCamera.viewportWidth * .75f, HAlignment.CENTER);
		Assets.scoreFont.drawWrapped(fontBatch, "Score " + (int)(game.gameScreen.timeInGame) + " seconds", -fontCamera.viewportWidth * .375f, -fontCamera.viewportHeight * .375f, fontCamera.viewportWidth * .75f, HAlignment.CENTER);
		fontBatch.end();

        angel.draw(stateTime, batch);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		leftSidebar.draw(batch);
		rightSidebar.draw(batch);
		batch.end();

        //testMeteor.update(delta, batch);

        Ragnar.debugger.renderDebug(world, camera.combined);
	}

    private void handleBarrierDeath () {
        if (angel.getBody().getPosition().x/* - angel.getHalfWidth()*/ < -Constants.SCALED_WIDTH / 2
                || angel.getBody().getPosition().x + angel.getHalfWidth() > Constants.SCALED_WIDTH / 2
                || angel.getBody().getWorldCenter().y + angel.getHalfHeight() > camera.viewportHeight / 2
                || angel.getBody().getWorldCenter().y - angel.getHalfHeight() < -camera.viewportHeight / 2) {


                game.setToKillScreen(KillScreen.METEORED);

        }
    }


    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
