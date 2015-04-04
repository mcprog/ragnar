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

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.lib.Constants;

public class ScreenDrawable extends ScreenAdapter {
	
	protected Ragnar game;
	protected SpriteBatch batch;
	protected OrthographicCamera camera;
	protected SpriteBatch fontBatch;
	protected OrthographicCamera fontCamera;

    /**
     * Constructor instantiates protected fields
     * @param game: non-static game instance assigned to a protected field
     */
	public ScreenDrawable(Ragnar game) {
		this.game = game;
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		fontBatch = new SpriteBatch();
		fontCamera = new OrthographicCamera();
	}
	
	@Override
	public void resize(int width, int height) {
		fontCamera.viewportWidth = Constants.FONT_WIDTH;
		fontCamera.viewportHeight = Constants.FONT_HEIGHT;
		fontCamera.update();
		camera.viewportWidth = Constants.SCALED_WIDTH;
		camera.viewportHeight = Constants.SCALED_HEIGHT;
		camera.update();
	}
	
	@Override
	public void render(float delta) {
		Ragnar.debugger.handleInput(game);
	}

}
