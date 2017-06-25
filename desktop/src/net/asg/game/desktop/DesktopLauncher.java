package net.asg.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

import net.asg.game.RodKastApplication;
import net.asg.game.utils.Constants;

public class DesktopLauncher {
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		if (args.length == 1 && args[0].equals("texturepacker")) {
			// Create two run configurations
			// 1. For texture packing. Pass 'texturepacker' as argument and use desktop/src
			//    as working directory
			// 2. For playing game with android/assets as working directory
			TexturePacker.Settings settings = new TexturePacker.Settings();
			settings.maxWidth = Constants.MAX_WIDTH;
			settings.maxHeight = Constants.MAX_HEIGHT;
			TexturePacker.process(settings, Constants.SOURCE_ASSETS_FOLDER_PATH + Constants.IMAGES_FOLDER_NAME,
					Constants.TARGET_ASSETS_FOLDER_PATH, Constants.GAME_ATLAS_NAME);

			Gdx.app.exit();
		}

		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = Constants.GAME_TITLE;
		cfg.width = Constants.APP_WIDTH;
		cfg.height = Constants.APP_HEIGHT;

		new LwjglApplication(new RodKastApplication(new DesktopResolver()), config);
	}
}
