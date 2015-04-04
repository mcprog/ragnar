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
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.gui.tables.MenuTable;
import com.mcprog.ragnar.lib.Constants;

public class MenuScreen extends ScreenDrawable {

	private Stage stage;
	private MenuTable menuTable;

    /**
     * Constructor assigns game to protected field
     * Mobile signs into Google Play Game Services
     * Sets up stage and adds table to stage
     * Debugger set to debug lines for table
     * @param game: Ragnar instance
     */
	public MenuScreen(Ragnar game) {
		super(game);
		if (game.isMobile) {
			
			game.gpgs.signIn();
		}
		menuTable = new MenuTable();
		stage = new Stage();
		stage.setViewport(new ExtendViewport(Constants.IDEAL_WIDTH, Constants.IDEAL_HEIGHT));

		stage.addActor(menuTable);
		
		if (Ragnar.debugger.on) {
			stage.setDebugAll(true);
		}
	}

    /**
     * Sets input to the stage
     */
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
        /*if (Ragnar.isMobile) {
            game.adRefresher.showBanner();
        }*/

	}

    /**
     * Acts the stage and then draws it
     * @param delta: change in time from last frame (unused here)
     */
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.5f, .5f, .5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
		
	}

    /**
     * updates stage to screen size
     * @param width: current screen width
     * @param height: current screen height
     */
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}

}
