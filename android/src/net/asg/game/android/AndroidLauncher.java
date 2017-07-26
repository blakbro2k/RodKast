package net.asg.game.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import net.asg.game.RodKastApplication;
import net.asg.game.stages.HomeStage;
import net.asg.game.utils.GameEventListener;
import net.asg.game.utils.Utils;

public class AndroidLauncher extends AndroidApplication implements GameEventListener{
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new RodKastApplication(this), config);
	}

	@Override
	public void backButton(RodKastApplication app) {
		Utils.backButtonUtil(app);
	}

	@Override
	public void showBannerAd() {

	}

	@Override
	public void hideBannerAd() {

	}

	@Override
	public void showOrLoadInterstital() {

	}

	@Override
	public void share() {

	}

	@Override
	public void getDebugSetting() {

	}
}
