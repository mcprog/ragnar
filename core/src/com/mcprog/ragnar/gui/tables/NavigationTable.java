package com.mcprog.ragnar.gui.tables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mcprog.ragnar.lib.Constants;

/**
 * Created by Michael on 3/22/2015.
 */
public class NavigationTable extends RagnarTable {

    public NavigationTable() {
        Texture tex = new Texture(Gdx.files.internal("wave.png"));
        tex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        setBackground(new TextureRegionDrawable(new TextureRegion(tex, (int) Constants.WAVE_WIDTH, (int)Constants.WAVE_HEIGHT)));
    }


}
