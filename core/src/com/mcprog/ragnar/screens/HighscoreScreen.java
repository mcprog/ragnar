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
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.gui.tables.HighscoreTable;

public class HighscoreScreen extends GUIScreen {
	
	public HighscoreScreen(Ragnar game) {
		super(game, new HighscoreTable());
	}
	
	@Override
	public void show() {
		super.show();
        ((HighscoreTable)table).show();
	}

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
            game.setToScreen(Ragnar.OPTIONS_ID);
        }
    }

}
