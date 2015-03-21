package com.mcprog.ragnar.gui.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mcprog.ragnar.Ragnar;

/**
 * Created by Michael on 3/21/2015.
 */
public class LeaderboardsButton extends TextButton implements ICustomButton {

    public LeaderboardsButton(TextButtonStyle style) {
        super("Leaderboards", style);
        addFunctionality();
    }


    @Override
    public void addFunctionality() {
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Ragnar.gameInstance.gpgs.getLeaderBoard();
            }
        });
    }
}
