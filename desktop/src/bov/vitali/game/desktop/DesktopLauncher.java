package bov.vitali.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import bov.vitali.game.FlyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new FlyGame(), config);
        config.title = "Fly Game";
        config.width = 360;
        config.height = 640;
	}
}
