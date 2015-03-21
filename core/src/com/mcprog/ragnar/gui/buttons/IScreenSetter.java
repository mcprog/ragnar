package com.mcprog.ragnar.gui.buttons;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.screens.ScreenDrawable;

/**
 * Created by Michael on 3/21/2015.
 */
public interface IScreenSetter {

    public void setScreenToSet (final Ragnar game, final ScreenDrawable screen);
}
