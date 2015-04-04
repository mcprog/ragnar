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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.gui.tables.OptionsTable;


/**
 * Created by Michael on 3/21/2015.
 */
public class OptionsScreen extends GUIScreen {


    private Stage stage;
    private OptionsTable optionsTable;

    public OptionsScreen(Ragnar game) {
        super(game, new OptionsTable());
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
            game.setToScreen(Ragnar.MENU_ID);
        }

    }
}
