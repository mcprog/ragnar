package com.mcprog.ragnar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.gui.tables.OptionsTable;
import com.mcprog.ragnar.lib.Constants;


/**
 * Created by Michael on 3/21/2015.
 */
public class OptionsScreen extends ScreenDrawable {


    private Stage stage;
    private OptionsTable optionsTable;

    public OptionsScreen(Ragnar game) {
        super(game);
        optionsTable = new OptionsTable();
        stage = new Stage();
        stage.setViewport(new ExtendViewport(Constants.IDEAL_WIDTH, Constants.IDEAL_HEIGHT));

        stage.addActor(optionsTable);

        if (Ragnar.debugger.on) {
            stage.setDebugAll(true);
        }
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        /*if (Ragnar.isMobile) {
            game.adRefresher.showBanner();
        }*/
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
