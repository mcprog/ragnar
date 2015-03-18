package com.mcprog.ragnar.android;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy;
import com.google.android.gms.games.Games;
import com.google.android.gms.internal.ga;
import com.google.example.games.basegameutils.GameHelper;
import com.google.example.games.basegameutils.GameHelper.GameHelperListener;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.gameservices.IGooglePlayGameServices;
import com.mcprog.ragnar.lib.RagnarConfig;

public class AndroidLauncher extends AndroidApplication implements IGooglePlayGameServices, GameHelperListener {

    private GameHelper gameHelper;

	private static final int REQUEST_CODE_UNUSED = 7;
	
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useWakelock = true;
		initialize(new Ragnar(this), config);
        if (gameHelper == null) {
            gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
            gameHelper.enableDebugLog(true);
        }
        gameHelper.setup(this);
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		gameHelper.onStart(this);
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		gameHelper.onStop();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		gameHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void signIn() {
		try {
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					gameHelper.beginUserInitiatedSignIn();
					
				}
			});
		} catch (final Exception ex) {
			
		}
		
	}

	@Override
	public boolean isSignedIn() {
		// TODO Auto-generated method stub
		return gameHelper.isSignedIn();
	}

//	@Override
//	public void signOut() {
//		try {
//			runOnUiThread(new Runnable() {
//				
//				@Override
//				public void run() {
//					gameHelper.signOut();
//					
//				}
//			});
//		} catch (Exception ex) {
//			// TODO: handle exception
//		}
//		
//	}

	@Override
	public void submitHighscore(int score) {
		if (isSignedIn()) {
			Games.Leaderboards.submitScore(gameHelper.getApiClient(), getString(R.string.leaderboard_id), score);
		} else {
//			signIn();
		}
		
	}

	@Override
	public void getLeaderBoard() {
		if (isSignedIn()) {
			
			startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(), getString(R.string.leaderboard_id)), REQUEST_CODE_UNUSED);
		} else {
//			signIn();
		}
		
	}

	@Override
	public void onSignInFailed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSignInSucceeded() {
		// TODO Auto-generated method stub
		
	}
}