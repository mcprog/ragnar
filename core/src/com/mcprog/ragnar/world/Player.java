package com.mcprog.ragnar.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.lib.Assets;
import com.mcprog.ragnar.lib.RagnarConfig;
import com.mcprog.ragnar.screens.GameScreen;

import java.util.ArrayList;

public class Player implements InputProcessor {

	private BodyDef bodyDef;
	private World world;
	private int accelerometerLimit = 15;
	private Sprite glow;
	private ArrayList<Integer> lastTwoDirections = new ArrayList<Integer>();
	private int currentDirection;
	protected boolean left, right, up, down;
	private Vector2 directionFactors = new Vector2();
	private Vector2 touch = new Vector2();
	private float tolerance = 45;
    private float mVX;
    private float mVY;
    public Vector2 dragVector;
    private Vector2 dragRatio;
	
	
	protected Body body;
	protected int direction;
	protected float invincibleTimer;
	protected TextureRegion frame;
	protected Sprite frameSprite;
	protected float playerSpeed = 8;
	private boolean isIdle;
	private OrthographicCamera camera;
	protected float hWidth;
	protected float hHeight;
	
	public float dragAngle;
	public boolean invincible;
	
	public static Vector3 touchCoords = new Vector3();
	
	public static final int LEFT = 			0;
	public static final int RIGHT = 		1;
	public static final int UP = 			2;
	public static final int DOWN = 			3;
	public static final int LEFT_IDLE = 	4;
	public static final int RIGHT_IDLE = 	5;
	public static final int UP_IDLE = 		6;
	public static final int DOWN_IDLE = 	7;
	public static final int LEFT_BLOCK = 	8;
	public static final int RIGHT_BLOCK = 	9;
	public static final int UP_BLOCK = 		10;
	public static final int DOWN_BLOCK = 	11;

    private GameScreen screen;
	
	public Player(World world, Vector2 position, OrthographicCamera camera, GameScreen screen) {
		this.world = world;
		this.camera = camera;
        this.screen = screen;
		
		hWidth = .6f;
		hHeight = 1;

        dragVector = new Vector2();
        dragRatio = new Vector2();
		
		glow = new Sprite(new Texture(Gdx.files.internal("glow.png")));
		
		bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(position);
		
		body = world.createBody(bodyDef);
		body.setUserData(Assets.playerAnimations);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(hWidth, hHeight);
		body.setFixedRotation(true);
		body.createFixture(shape, 1);
		
		shape.dispose();
	}
	
	public void init () {
		direction = Player.DOWN_IDLE;
        isIdle = true;
	}
	
	public void draw (float stateTime, SpriteBatch batch) {
		switch (RagnarConfig.playerType) {
		case 1:
			
			frame = Assets.playerGirlAnimations[getDirection()].getKeyFrame(stateTime, true);
			break;

		default:
			frame = Assets.playerAnimations[getDirection()].getKeyFrame(stateTime, true);
			break;
		}
		frameSprite = new Sprite(frame);
		frameSprite.setCenter(getPosition().x, getPosition().y);
		frameSprite.setScale(.125f);
		
		batch.begin();
		frameSprite.draw(batch);
		if (invincible) {
			getGlow().draw(batch);
		}
		batch.end();
	}
	
	public void update (float delta) {
		checkInvincibility(delta);
		int vX = 0;
		int vY = 0;
		if (left) {
			vX = -1;
			direction = LEFT;
		}
		if (right) {
			vX = 1;
			direction = RIGHT;
		}
		if (up) {
			vY = 1;
			direction = UP;
		}
		if (down) {
			vY = -1;
			direction = DOWN;
		}
        if (Ragnar.isMobile) {
            setLinearVelocityAndDirection(mVX, mVY);
        } else {
            setLinearVelocityAndDirection(vX, vY);
        }

	}
	
	private void checkInvincibility(float delta) {
		if (invincibleTimer <= 0) {
			invincible = false;
		}
		invincibleTimer -= delta;
		
	}
	
	protected void setLinearVelocityAndDirection (int vX, int vY) {
		if (!isIdle) {
			direction = left ? LEFT : right ? RIGHT : up ? UP : DOWN;
		}
		body.setLinearVelocity(vX * playerSpeed, vY * playerSpeed);
	}

    protected void setLinearVelocityAndDirection (float mVX, float mVY) {
        if (!isIdle) {
            direction = left ? LEFT : right ? RIGHT : up ? UP : DOWN;
        }
        body.setLinearVelocity(mVX * playerSpeed, mVY * playerSpeed);
    }
	
	protected void setToIdle () {
		if ((left && (up || down)) || left) {
			direction = Player.LEFT_IDLE;
		}
		else if ((right && (up || down)) || right) {
			direction = Player.RIGHT_IDLE;
		}
		else if (up) {
			direction = Player.UP_IDLE;
		}
		else if (down) {
			direction = Player.DOWN_IDLE;
		}
		isIdle = true;
	}
	
	public float getHalfWidth () {
		return hWidth;
	}
	
	public float getHalfHeight () {
		return hHeight;
	}
	
	public Vector2 getVelocity () {
		return body.getLinearVelocity();
	}
	
	public Vector2 getPosition () {
		return body.getPosition();
	}
	
	public int getDirection () {
		return direction;
	}
	
	public void setDirection (int dir) {
		direction = dir;
	}
	
	public Body getBody () {
		return body;
	}
	
	public Sprite getGlow () {
		final float width = frameSprite.getWidth() * frameSprite.getScaleX();
		glow.setBounds(getPosition().x - width, getPosition().y - width, width * 2, width * 2);
		return glow;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.A) {
			left = true;
			isIdle = false;
		}
		else if (keycode == Keys.D) {
			right = true;
			isIdle = false;
		}
		else if (keycode == Keys.W) {
			up = true;
			isIdle = false;
		}
		else if (keycode == Keys.S) {
			down = true;
			isIdle = false;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		setToIdle();
		if (keycode == Keys.A) {
			left = false;
		}
		else if (keycode == Keys.D) {
			right = false;
		}
		else if (keycode == Keys.W) {
			up = false;
		}
		else if (keycode == Keys.S) {
			down = false;
		}
        if (keycode == Keys.SPACE) {
            screen.setGameToPaused(!screen.isPaused());
            screen.setJustPaused(true);
        }
        else if (keycode == Keys.R) {
            screen.show();
        }
        else if (keycode == Keys.Q) {
            Ragnar.gameInstance.setScreen(Ragnar.gameInstance.menuScreen);
        }
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (!Ragnar.isMobile) {
			return false;
		}
		touch.set(screenX, screenY);
		touchCoords.set(screenX, screenY, 0);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (!Ragnar.isMobile) {
			return false;
		}
		setToIdle();
		left = false;
		right = false;
		up = false;
		down = false;
        mVX = 0;
        mVY = 0;
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (Ragnar.isMobile) {
			dragAngle = MathUtils.atan2(screenY - touch.y, screenX - touch.x);
            dragVector.set(screenX - touch.x, -screenY + touch.y);
            dragRatio.set(dragVector).setLength(1);
            mVX = dragRatio.x;
            mVY = dragRatio.y;
			if (Math.abs(dragAngle) >= MathUtils.PI * 5/8) {
				left = true;
				isIdle = false;
			} else {
				left = false;
			}
			if (Math.abs(dragAngle) <= MathUtils.PI * 3/8) {
				right = true;
				isIdle = false;
			} else {
				right = false;
			}
			if (dragAngle <= -MathUtils.PI / 8 && dragAngle >= -MathUtils.PI * 7/8) {
				up = true;
				isIdle = false;
			} else {
				up = false;
			}
			if (dragAngle >= MathUtils.PI / 8 && dragAngle <= MathUtils.PI * 7/8) {
				down = true;
				isIdle = false;
			} else {
				down = false;
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
