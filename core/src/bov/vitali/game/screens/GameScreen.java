package bov.vitali.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import bov.vitali.game.game.GameRender;
import bov.vitali.game.game.GameWorld;
import bov.vitali.game.ui.InputHandler;

/**
 экран игры
 */
public class GameScreen implements Screen {

    // подключаем вспомогательные классы
    private GameWorld world;
    private GameRender renderer;

    private float runTime = 0;


    public GameScreen() {

        // определяем ширину и высоту экрана андроид устройства
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        // игра в 136 пикселей в ширину
        float gameWidth = 136;
        // экран может быть до 180 пк. в ширину так что отмаштабируем на 1/8
        float gameHeight = screenHeight / (screenWidth / gameWidth);
        /* что бы отмашатбировать высоту игр мы должны взять высоту экрана и
         отмаштабировать на туже величину
        */
        //возьмем центр экрана
        // эти значения мы  будем передавать игровому миру для размещения
        //некоторых обьектов на экране
        int midPointY = (int) (gameHeight / 2);
        int midPointX = (int) (gameWidth / 2);

        world = new GameWorld(midPointY, midPointX);
        // создаем новый импут хендлер и прикручиваем к нашей игре
        Gdx.input.setInputProcessor(new InputHandler(world, screenWidth / gameWidth, screenHeight / gameHeight));
        renderer = new GameRender(world, (int) gameHeight, midPointY, midPointX);
        world.setRenderer(renderer);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(delta, runTime);

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}