/*
 * Copyright 2015 Michael Curtis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mcprog.ragnar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.lib.Assets;
import com.mcprog.ragnar.lib.Constants;
import com.mcprog.ragnar.world.Angel;
import com.mcprog.ragnar.world.Meteor;

public class WinScreen extends ScreenDrawable {

    /*
    Physics and World
     */
	private World world;
	private Angel angel;
    private Meteor[] meteors;

    /*
    Timing and Score
     */
	private float stateTime;

    /*
    Resources
     */
	private Sprite leftSidebar;
	private Sprite rightSidebar;
	private SpriteBatch fontBatch;

    /**
     * Constructor instantiates the meteors array
     * @param game
     */
	public WinScreen(Ragnar game) {
		super(game);

        meteors = new Meteor[10];
	}

    /**
     * Called when screen is shown<br>
     * Instantiates each meteor<br>
     * Renews world and physics<br>
     * Unlocks win achievement and submits highscore
     */
    @Override
    public void show() {
        world = new World(new Vector2(0, -9.81f), true);
        for (int i = 0; i < meteors.length; ++i) {
            meteors[i] = new Meteor(world);
        }
        angel = new Angel(world, Vector2.Zero, camera);
        leftSidebar = new Sprite(new Texture(Gdx.files.internal("heaven_sidebar.png")));
        rightSidebar = new Sprite(new Texture(Gdx.files.internal("heaven_sidebar.png")));
        fontBatch = new SpriteBatch();
        Gdx.input.setCatchBackKey(true);
        if (Ragnar.isMobile) {
            game.gpgs.unlockAchievement(4);
        }
        if (Ragnar.isMobile) {
            game.gpgs.submitHighscore((int) game.gameScreen.timeInGame);
        }
        Gdx.input.setInputProcessor(angel);
    }

    /**
     * Called every frame<br>
     * Steps physics and draws to bodies
     * @param delta time from last frame in seconds (used here)
     */
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

    /**
     * Called in render<br>
     *     Kills player if player goes off the screen
     */
    private void handleBarrierDeath () {
        if (angel.getBody().getPosition().x/* - angel.getHalfWidth()*/ < -Constants.SCALED_WIDTH / 2
                || angel.getBody().getPosition().x + angel.getHalfWidth() > Constants.SCALED_WIDTH / 2
                || angel.getBody().getWorldCenter().y + angel.getHalfHeight() > camera.viewportHeight / 2
                || angel.getBody().getWorldCenter().y - angel.getHalfHeight() < -camera.viewportHeight / 2) {


                game.setToKillScreen(KillScreen.METEORED);

        }
    }
}
