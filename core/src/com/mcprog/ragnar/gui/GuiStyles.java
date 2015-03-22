package com.mcprog.ragnar.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mcprog.ragnar.lib.Assets;

public class GuiStyles {
	
	public static LabelStyle headerLabelStyle;
	public static LabelStyle normalLabelStyle;
	public static LabelStyle headerLabelStyleWhite;
	public static LabelStyle normalLabelStyleWhite;
	public static LabelStyle tinyLabelStyle;
	public static LabelStyle tinyLabelStyleWhite;
	
	public static TextButtonStyle largeButtonStyle;
	public static TextButtonStyle largeButtonStyleLight;
	public static TextButtonStyle smallButtonStyle;
    public static TextButtonStyle smallButtonStyleLight;
	public static TextButtonStyle tinyButtonStyleLight;
	
	public static void init () {
		if (headerLabelStyle != null) {
			return;
		}
		headerLabelStyle = new LabelStyle(Assets.ragnarFont, Color.WHITE);
		normalLabelStyle = new LabelStyle(Assets.smallFont, Color.WHITE);
		headerLabelStyleWhite = new LabelStyle(Assets.ragnarFont, Color.WHITE);
		normalLabelStyleWhite = new LabelStyle(Assets.smallFont, Color.WHITE);
		tinyLabelStyle = new LabelStyle(Assets.tinyFont, Color.WHITE);
		tinyLabelStyleWhite = new LabelStyle(Assets.tinyFont, Color.WHITE);
		
		largeButtonStyle = makeTextButtonStyle(Assets.scoreFont, Color.LIGHT_GRAY, Color.WHITE);
		largeButtonStyleLight = makeTextButtonStyle(Assets.scoreFont, Color.LIGHT_GRAY, Color.WHITE);
		smallButtonStyle = makeTextButtonStyle(Assets.smallFont, Color.LIGHT_GRAY, Color.WHITE);
        smallButtonStyleLight = makeTextButtonStyle(Assets.smallFont, Color.LIGHT_GRAY, Color.WHITE);
		tinyButtonStyleLight = makeTextButtonStyle(Assets.tinyFont, Color.LIGHT_GRAY, Color.WHITE);


	}
	
	private static TextButtonStyle makeTextButtonStyle (BitmapFont font, Color color, Color active) {
		TextButtonStyle tbs = new TextButtonStyle();
		tbs.font = font;
		tbs.fontColor = color;
		tbs.downFontColor = active;
		tbs.overFontColor = active;
        tbs.pressedOffsetX = 5;
        tbs.pressedOffsetY = -5;
		return tbs;
	}

}
