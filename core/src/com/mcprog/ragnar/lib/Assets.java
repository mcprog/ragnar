package com.mcprog.ragnar.lib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	
	public static final AssetManager assetManager = new AssetManager();
	
	private static TextureRegion[][] map;
	public static Animation[] playerAnimations;
	public static Animation[] playerGirlAnimations;
	
	public static BitmapFont ragnarFont;
	public static BitmapFont scoreFont;
	
	public static Sprite arrowSprite;
	public static Sprite deadPlayerSprite;
	public static Sprite deadPlayerStabbedSprite;
	
	public static final String PLAYER_PATH = "player.png";
	public static final String PLAYER_GIRL_PATH = "player-girl.png";
	public static final String ARROW_PATH = "arrow.png";
	public static final String DEAD_PLAYER_PATH = "dead-player.png";
	public static final String DEAD_PLAYER_STABBED_PATH = "dead-player-stabbed.png";
	
	public static final String FONT_DIR = "font/";
	public static final String VIKING_FONT_PATH = FONT_DIR + "viking128.fnt";
	public static final String SCORE_FONT_PATH = FONT_DIR + "viking64.fnt";
	
	public static void queueTextures () {
		assetManager.load(PLAYER_PATH, Texture.class);
		assetManager.load(PLAYER_GIRL_PATH, Texture.class);
		assetManager.load(ARROW_PATH, Texture.class);
		assetManager.load(DEAD_PLAYER_PATH, Texture.class);
		assetManager.load(DEAD_PLAYER_STABBED_PATH, Texture.class);
	}
	
	public static void queueFonts () {
		assetManager.load(VIKING_FONT_PATH, BitmapFont.class);
		assetManager.load(SCORE_FONT_PATH, BitmapFont.class);
	}
	
	public static Texture getLoadedTexture (String path) {
		return assetManager.get(path);
	}
	
	public static BitmapFont getLoadedFont (String path) {
		return assetManager.get(path);
	}
	
	public static Animation[] loadAnimation (String path) {
		map = TextureRegion.split(getLoadedTexture(path), 16, 24);
		
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
		return animations;
	}
	
	public static void assignLoadedAssets () {
		scoreFont = getLoadedFont(SCORE_FONT_PATH);
		arrowSprite = new Sprite(getLoadedTexture(ARROW_PATH));
		deadPlayerSprite = new Sprite(getLoadedTexture(DEAD_PLAYER_PATH));
		deadPlayerStabbedSprite = new Sprite(getLoadedTexture(DEAD_PLAYER_STABBED_PATH));
	}
	
	public static float getLoadingProgress () {
		return assetManager.getProgress() * 100;
	}
	

}
