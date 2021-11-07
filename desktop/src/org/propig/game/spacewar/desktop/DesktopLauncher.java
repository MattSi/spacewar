package org.propig.game.spacewar.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.propig.game.spacewar.SpaceWar;
import org.propig.game.spacewar.SpaceWarGame;

public class DesktopLauncher {
//	public static void main (String[] arg) {
//		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		config.height = 700;
//		config.width = 433;
//		new LwjglApplication(new SpaceWarGame(), config);
//	}

	public static void main (String[] arg) {
//		LwjglApplicationConfiguration invaderConfig = new LwjglApplicationConfiguration();
//		invaderConfig.width = 480;
//		invaderConfig.height = 320;
//		invaderConfig.title = "Invaders";
//		new LwjglApplication(new Invaders(), invaderConfig);


		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 700;
		config.width = 433;
		new LwjglApplication(new SpaceWarGame(), config);
	}
}
