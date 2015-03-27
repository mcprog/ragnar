package com.mcprog.ragnar.android;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;
import com.google.example.games.basegameutils.GameHelper.GameHelperListener;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.lib.RagnarConfig;
import com.mcprog.ragnar.services.IAdRefresher;
import com.mcprog.ragnar.services.IGooglePlayGameServices;

public class AndroidLauncher extends AndroidApplication implements IGooglePlayGameServices, GameHelperListener, IAdRefresher {

    private GameHelper gameHelper;
    private AdView adView;

	private static final int REQUEST_CODE_UNUSED = 7;
	
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useAccelerometer = false;
        config.useCompass = false;
        config.useWakelock = false;
        //initialize(new Ragnar(this), config);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        RelativeLayout layout = new RelativeLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(params);



        adView = createAdView();
        layout.addView(adView);
        View gameView = createGameView(config);
        layout.addView(gameView);

        //adMobView.bringToFront();

        setContentView(layout);
        //startTestAdvertising(adView);

        if (gameHelper == null) {
            gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
            gameHelper.enableDebugLog(true);
        }
        /*if (!RagnarConfig.gpgsEnabled) {*/
            gameHelper.setMaxAutoSignInAttempts(0);
        /*} else {
            gameHelper.setMaxAutoSignInAttempts(1);
        }*/


        gameHelper.setup(this);
	}

    private AdView createAdView() {
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId(getString(R.string.ad_banner1));
        adView.setId(12345);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        adView.setLayoutParams(params);
        adView.setBackgroundColor(Color.BLACK);
        return adView;
    }

    private View createGameView (AndroidApplicationConfiguration cfg) {
        View gameView = initializeForView(new Ragnar(this, this), cfg);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ABOVE, adView.getId());
        gameView.setLayoutParams(params);
        return gameView;
    }

    private void startAdvertising (AdView adView) {
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private void startTestAdvertising (AdView adView) {
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("5B8F71A3D3FED6DAB7374305AB82FF34").addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView.loadAd(adRequest);
    }

    @Override
    public void showBanner() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adView.setVisibility(View.VISIBLE);
                adView.loadAd(new AdRequest.Builder().build());
            }
        });
    }

    @Override
    public void showTestBanner() {


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adView.setVisibility(View.VISIBLE);
                adView.loadAd(new AdRequest.Builder().addTestDevice("5B8F71A3D3FED6DAB7374305AB82FF34").addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
            }
        });
    }

    @Override
    public void hideBanner() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adView.setVisibility(View.GONE);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adView != null) {
            adView.pause();
        }
    }

    @Override
    protected void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
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
        ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected && RagnarConfig.gpgsEnabled) {
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
			signIn();
		}
		
	}

	@Override
	public void getLeaderBoard() {
		if (isSignedIn()) {
			submitHighscore(RagnarConfig.highScore);
			startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(), getString(R.string.leaderboard_id)), REQUEST_CODE_UNUSED);
		} else {
			signIn();
		}
		
	}

    @Override
    public void unlockAchievement(int achievement) {
        if (isSignedIn()) {
            switch (achievement) {
                case 1:
                    Games.Achievements.unlock(gameHelper.getApiClient(), getString(R.string.achievement_ambushed_id));
                    break;
                case 2:
                    Games.Achievements.unlock(gameHelper.getApiClient(), getString(R.string.achievement_shot_id));
                    break;
                case 3:
                    Games.Achievements.unlock(gameHelper.getApiClient(), getString(R.string.achievement_stabbed_id));
                    break;
                case 4:
                    Games.Achievements.unlock(gameHelper.getApiClient(), getString(R.string.achievement_win_id));
                    break;
                case 5:
                    Games.Achievements.unlock(gameHelper.getApiClient(), getString(R.string.achievement_die_id));
                    break;
               default:
                   break;
            }

        } else {
            signIn();
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
