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
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.gui.GuiStyles;
import com.mcprog.ragnar.gui.tables.CreditsTable;
import com.mcprog.ragnar.lib.Constants;

public class CreditsScreen extends ScreenDrawable {

	private Stage stage;
	private CreditsTable creditsTable;
	
	public CreditsScreen(Ragnar game) {
		super(game);
		GuiStyles.init();
		creditsTable = new CreditsTable();
		stage = new Stage();
		stage.setViewport(new ExtendViewport(Constants.IDEAL_WIDTH, Constants.IDEAL_HEIGHT));
		
		stage.addActor(creditsTable);
		
		if (Ragnar.debugger.on) {
			stage.setDebugAll(true);
		}
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
            game.setToScreen(Ragnar.MENU_ID);
        }
		Gdx.gl.glClearColor(.5f, .5f, .5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
		
	}
	
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}

}
