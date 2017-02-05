package bov.vitali.game;

import com.badlogic.gdx.Game;

import bov.vitali.game.loader.ResourseLoader;
import bov.vitali.game.screens.SplashScreen;

public class FlyGame extends Game {

	// загружаем ресурсы
	@Override
	public void create () {
		ResourseLoader.load();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		ResourseLoader.dispose();
	}
}
