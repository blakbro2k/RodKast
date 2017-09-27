package net.asg.game.android;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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

import net.asg.game.R;
import net.asg.game.RodKastApplication;
import net.asg.game.utils.GameEventListener;
import net.asg.game.utils.Utils;

public class AndroidLauncher extends AndroidApplication implements GameEventListener{
	protected AdView mAdView;
	protected View mAppView;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		Log.d("ANDROID", "onCreate() called");
		super.onCreate(savedInstanceState);
		//this.getApplicationInfo().m
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;

		initializeRodkastApp(new RodKastApplication(this), config);
	}

	private void initializeRodkastApp(RodKastApplication rodKastApplication, AndroidApplicationConfiguration config) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

		RelativeLayout layout = new RelativeLayout(this);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		layout.setLayoutParams(params);


		mAppView = createAppView(rodKastApplication,config);
		layout.addView(mAppView);

		mAdView = createAdView();
		layout.addView(mAdView);

		setContentView(layout);
	}

	private AdRequest getAdRequest() {
		//return new AdRequest.Builder().build();
		return new AdRequest.Builder().addTestDevice(getString(R.string.test_s7)).build();
	}

    private View createAppView(RodKastApplication rodKastApplication, AndroidApplicationConfiguration config) {
		mAppView = initializeForView(rodKastApplication, config);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);

		mAppView.setLayoutParams(params);
		return mAppView;
	}

	private AdView createAdView() {
		mAdView = new AdView(this);
		mAdView.setAdSize(AdSize.SMART_BANNER);
		mAdView.setAdUnitId(getAdMobUnitId());
		mAdView.loadAd(getAdRequest());
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		params.addRule(RelativeLayout.BELOW, mAdView.getId());

		mAdView.setLayoutParams(params);
		mAdView.setBackgroundColor(Color.BLACK);
		return mAdView;
	}

	private String getAdMobUnitId() {
		return getString(R.string.ad_unit_id);
	}

	@Override
	public void backButton(RodKastApplication app) {
		Utils.backButton(app);
	}

	@Override
	public void showBannerAd() {
		mAdView.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideBannerAd() {
		mAdView.setVisibility(View.GONE);
	}

	@Override
	public void showOrLoadInterstital() {
	}

	@Override
	public void share() {

	}

	@Override
	public void appLog(String tag, String message){
		Log.d(tag,message);
	}
}
