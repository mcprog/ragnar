package com.mcprog.ragnar.gui.tables;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.gui.GuiStyles;
import com.mcprog.ragnar.gui.buttons.MenuButton;
import com.mcprog.ragnar.gui.buttons.PlayButton;

/**
 * Created by Michael on 3/21/2015.
 */
public class OptionsTable extends NavigationTable {

    private Label header;
    private MenuButton menuButton;
    private PlayButton playButton;
    private TextButton highscoreButton;
    private TextButton settingsButton;

    public OptionsTable () {
        header = new Label("Options", GuiStyles.headerLabelStyle);
        menuButton = new MenuButton();
        playButton = new PlayButton();
        highscoreButton = new TextButton("Highscore", GuiStyles.largeButtonStyle);
        settingsButton = new TextButton("Settings", GuiStyles.largeButtonStyle);

        addFunctionality();

        add(header).colspan(2);
        row();
        add(highscoreButton).colspan(2);
        row();
        add(settingsButton).colspan(2);
        row();
        add(playButton).pad(20);
        add(menuButton).pad(20);
    }

    private void addFunctionality () {
        highscoreButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Ragnar.gameInstance.setScreen(Ragnar.gameInstance.highscoreScreen);
            }
        });
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Ragnar.gameInstance.setScreen(Ragnar.gameInstance.settingsScreen);
            }
        });
    }
}
