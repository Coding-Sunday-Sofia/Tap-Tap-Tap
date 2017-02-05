package bov.vitali.game.loader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

// загружает все ресурсы за раз
public class ResourseLoader {

    private static TextureAtlas atlas;
    public static Sprite logo, tapTapTap, background, grass, fly1, fly2, fly3, webUp,webDown,
            playButtonUp, playButtonDown, ready, retry, gameOver, highScore, scoreboard, starOn, starOff;
    public static Animation<TextureRegion> flyAnimation;
    public static Sound dead, flap, coin, fall;
    public static Music fly;
    public static BitmapFont font;
    // класс для хранения значения(ключ, значения), по ключу можно получать значение
    private static Preferences preferences;

    public static void load() {
        // создаем атлас и отдаем ему фаил описания текстур
        atlas = new TextureAtlas(Gdx.files.internal("texture/texture.pack"), true);
        // создаем спрайти по количеству изображений и методом финд регион находим текстуры
        logo = new Sprite(atlas.findRegion("logo"));
        // переворачивает лого т.к. опенГл отрисовывает графику перевернутой
        logo.flip(false, true);
        playButtonUp = new Sprite(atlas.findRegion("buttonOff"));
        playButtonDown = new Sprite(atlas.findRegion("buttonOn"));
        ready = new Sprite(atlas.findRegion("tapToFly"));
        retry = new Sprite(atlas.findRegion("retry"));
        gameOver = new Sprite(atlas.findRegion("gameOver"));
        scoreboard = new Sprite(atlas.findRegion("wood"));
        starOn = new Sprite(atlas.findRegion("starOn"));
        starOff = new Sprite(atlas.findRegion("starOff"));
        highScore = new Sprite(atlas.findRegion("highScore"));
        tapTapTap = new Sprite(atlas.findRegion("flyAndSpyders"));
        background = new Sprite(atlas.findRegion("background"));
        grass = new Sprite(atlas.findRegion("grass"));
        fly1 = new Sprite(atlas.findRegion("fly1"));
        fly2 = new Sprite(atlas.findRegion("fly2"));
        fly3 = new Sprite(atlas.findRegion("fly1"));
        webUp = new Sprite(atlas.findRegion("webUp"));
        webDown = new Sprite(atlas.findRegion("webDown"));

        // анимация из трех текстур
        TextureRegion[] fly = {fly1, fly1, fly1};
        flyAnimation = new Animation<TextureRegion>(0.06f, fly);
        flyAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        // звуки
        dead = Gdx.audio.newSound(Gdx.files.internal("sounds/dead.mp3"));
        flap = Gdx.audio.newSound(Gdx.files.internal("sounds/flap.wav"));
        coin = Gdx.audio.newSound(Gdx.files.internal("sounds/coin.wav"));

        ResourseLoader.fly = Gdx.audio.newMusic(Gdx.files.internal("sounds/fly.ogg"));

        // загружаем шрифты и устанавливаем их размер
        font = new BitmapFont(Gdx.files.internal("fonts/text.fnt"));
        font.getData().setScale(.25f, -.25f);

        // здесь мы создаем и получаем рание созданый фал преференсис
        preferences = Gdx.app.getPreferences("FlyGame");

        // переменная для хранения лучшего результата
        if (!preferences.contains("highScore")) {
            preferences.putInteger("highScore", 0);
        }
    }

    // на вход получает значение highScore и сохраняет в фаил
    public static void setHighScore(int val) {
        preferences.putInteger("highScore", val);
        preferences.flush();
    }


    public static int getHighScore() {
        return preferences.getInteger("highScore");
    }

    public static void dispose(){
        atlas.dispose();

        dead.dispose();
        flap.dispose();
        coin.dispose();
        fly.dispose();

        font.dispose();
    }

}