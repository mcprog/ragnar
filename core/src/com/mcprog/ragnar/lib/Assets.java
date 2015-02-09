package com.mcprog.ragnar.lib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
//import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Assets {
	
	private static TextureRegion[][] map;
	public static Animation[] playerAnimations;
	
	public static BitmapFont ragnarFont;
	public static BitmapFont scoreFont;
	
	public static Sprite arrowSprite;
	
	
	public static void loadAnimations () {
		map = TextureRegion.split(new Texture(Gdx.files.internal("player.png")), 16, 24);
		
		/*
		 * Pseudo animations (TextureRegions that fit in array)
		 */
		TextureRegion[] holder = new TextureRegion[1];
		holder[0] = map[0][0];
		Animation idleRight = new Animation(1, holder);
		holder = new TextureRegion[1];//Needs to be initialized every time
		holder[0] = map[1][0];
		Animation idleLeft = new Animation(1, holder);
		holder = new TextureRegion[1];//Needs to be initialized every time
		holder[0] = map[2][0];
		Animation idleUp = new Animation(1, holder);
		holder = new TextureRegion[1];//Needs to be initialized every time
		holder[0] = map[3][0];
		Animation idleDown = new Animation(1, holder);
		holder = new TextureRegion[1];
		holder[0] = map[0][5];
		Animation blockingRight = new Animation(1, holder);
		holder = new TextureRegion[1];
		holder[0] = map[1][5];
		Animation blockingLeft = new Animation(1, holder);
		holder = new TextureRegion[1];
		holder[0] = map[2][4];
		Animation blockingUp = new Animation(1, holder);
		holder = new TextureRegion[1];
		holder[0] = map[3][4];
		Animation blockingDown = new Animation(1, holder);
		/*
		 * Now the real animations start
		 */
		holder = new TextureRegion[4];
		holder[0] = map [0][1];
		holder[1] = map [0][2];
		holder[2] = map [0][3];
		holder[3] = map [0][4];
		Animation walkingRight = new Animation(.25f, holder);
		holder = new TextureRegion[4];
		holder[0] = map [1][1];
		holder[1] = map [1][2];
		holder[2] = map [1][3];
		holder[3] = map [1][4];
		Animation walkingLeft = new Animation(.25f, holder);
		holder = new TextureRegion[4];
		holder[0] = map [2][0];
		holder[1] = map [2][1];
		holder[2] = map [2][2];
		holder[3] = map [2][3];
		Animation walkingUp = new Animation(.25f, holder);
		holder = new TextureRegion[4];
		holder[0] = map [3][0];
		holder[1] = map [3][1];
		holder[2] = map [3][2];
		holder[3] = map [3][3];
		Animation walkingDown = new Animation(.25f, holder);
		
		Animation[] animations = {
				walkingLeft,
				walkingRight, 
				walkingUp,
				walkingDown,
				idleLeft,
				idleRight, 
				idleUp,
				idleDown, 
				blockingLeft, 
				blockingRight, 
				blockingUp, 
				blockingDown 
				};
		playerAnimations = animations;
	}
	
	public static void loadFonts () {
//		if (Gdx.app.getType() != ApplicationType.WebGL) {
//			
//			FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("font/VIKING-N.TTF"));
//			FreeTypeFontParameter params = new FreeTypeFontParameter();
//			params.size = 36;
//			ragnarFont = gen.generateFont(params);
//			params.size = 16;
//			scoreFont = gen.generateFont(params);
//			gen.dispose();
//		} else {
			ragnarFont = new BitmapFont();
			scoreFont = new BitmapFont();
//		}
	}
	
	public static void loadSprites () {
		arrowSprite = new Sprite(new Texture(Gdx.files.internal("arrow.png")));
	}
	

}
