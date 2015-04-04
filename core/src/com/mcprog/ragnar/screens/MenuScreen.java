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

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.gui.tables.MenuTable;

public class MenuScreen extends GUIScreen {

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
		super(game, new MenuTable());
		if (game.isMobile) {
			game.gpgs.signIn();
		}
	}

    @Override
    protected void handleInput() {
        //do nothing
    }
}
