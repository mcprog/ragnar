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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.gui.GuiStyles;
import com.mcprog.ragnar.gui.tables.KillTable;
import com.mcprog.ragnar.lib.Constants;
import com.mcprog.ragnar.lib.RagnarConfig;

public class KillScreen extends ScreenDrawable {

	public static final int SHOT = 0;
	public static final int STABBED = 1;
    public static final int METEORED = 2;
	public static final String SHOT_MSG = "You got shot by the bowmen";
	public static final String STABBED_MSG = "You got stabbed by the english";
    public static final String METEORED_MSG = "You got hit by a meteor";

    private boolean newTouchUp;
	private Stage stage;
	private KillTable killTable;
    private boolean newHighscore;
	
	public int deathType = -1;

    /**
     * Constructor initializes gui styles and sets ups stage and table
     * @param gameInstance
     */
	public KillScreen(Ragnar gameInstance) {
		super(gameInstance);
		GuiStyles.init();
		stage = new Stage();
		stage.setViewport(new ExtendViewport(Constants.IDEAL_WIDTH, Constants.IDEAL_HEIGHT));
		killTable = new KillTable();
		stage.addActor(killTable);
	}

    /**
     * Called when screen is shown
     * Unlocks achievements shot, stabbed, and dies 5000 times
     * Submits highscore
     */
	@Override
	public void show() {
        newHighscore = false;
        if (Ragnar.isMobile) {
            game.gpgs.unlockAchievement(5);
        }
        if (deathType == SHOT) {
            if (Ragnar.isMobile) {
                game.gpgs.unlockAchievement(2);
            }
        }
        else if (deathType == STABBED) {
            if (Ragnar.isMobile) {
                game.gpgs.unlockAchievement(3);
            }
        }
		newTouchUp = false;
		Gdx.input.setInputProcessor(stage);
		//Gdx.input.setCatchBackKey(true);
        if (Ragnar.isMobile) {
            game.gpgs.submitHighscore((int) game.gameScreen.timeInGame);
        }

		if (RagnarConfig.highScore < (int) (game.gameScreen.timeInGame)) {
			RagnarConfig.highScore = (int) (game.gameScreen.timeInGame);
            if (Ragnar.isMobile) {
                game.gpgs.submitHighscore(RagnarConfig.highScore);
            }
            newHighscore = true;
		}
		RagnarConfig.updateFile();
		killTable.show(assembleMessage(), "You lasted " + (int)(game.gameScreen.timeInGame) + " seconds", "Highscore: " + RagnarConfig.highScore, deathType, newHighscore);
	}

    /**
     * Handles back key and keyboard input
     * Acts and draws stage
     * @param delta: time from last frame in seconds (used here)
     */
	@Override
	public void render(float delta) {
		super.render(delta);
		Gdx.gl.glClearColor(.1f, .1f, .2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (Gdx.input.isKeyJustPressed(Keys.R)) {
			game.setScreen(game.gameScreen);
		}
        else if (Gdx.input.isKeyJustPressed(Keys.BACK)) {
            game.setToScreen(Ragnar.MENU_ID);
        }
		
		stage.act(delta);
		stage.draw();
	}

    /**
     * Adjusts stage to fit current dimensions
     * @param width: current screen width
     * @param height current screen height
     */
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

    /**
     * Determines death message based on death type
     * @return the String for the appropriate death message
     */
	public String assembleMessage () {
		return deathType == SHOT ? SHOT_MSG : deathType == STABBED ? STABBED_MSG : METEORED_MSG;
	}

}
