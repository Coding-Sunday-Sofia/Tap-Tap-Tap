package bov.vitali.game.objects;


import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

// расширяет класс мувинг
public class Web extends Moving{

    // для создания препятствий на разной высоте
    private Random r;

    // прямоугольники столкновения для столбов
    private Rectangle webUp, webDown;

    // константа для указания просвета между прямоугольниками
    public static final int GAP = 45;
    // булеан переменная для очков
    private boolean isScored = false;

    private float groundY;

    public Web(float x, float y, int width, int height, float movSpeed, float groundY) {
        super(x, y, width, height, movSpeed);
        r = new Random();
        // инициализируем прямоугольники
        webUp = new Rectangle();
        webDown = new Rectangle();
        this.groundY = groundY;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        // рисуем прямоугольники
        webUp.set(position.x, position.y, width, height);
        webDown.set(position.x, position.y + height + GAP, width, groundY - (position.x + height + GAP));
    }

    @Override
    public void reset(float newX) {
        super.reset(newX);
        // меняем высоту на случайное значение из указаного диапозона
        // добавляем 15 пик. что б обьекты не улетали за экран
        height = r.nextInt(90) + 15;
        // коггда сбрасываеться положение столбов
        isScored = false;
    }

    public boolean collides(Fly fly) {
        // позиция прямоугольника по Х меньше позиция + ширина круга, значит столкновение
        if (position.x < fly.getX() + fly.getWidth()) {
            // вернет труе, если любой из прямоугольников пересечется с кругом
            return (Intersector.overlaps(fly.getCircle(), webUp))
                    || Intersector.overlaps(fly.getCircle(), webDown);
        }
        return false;
    }

    public boolean isScored() {
        return isScored;
    }

    public void setScored(boolean b) {
        isScored = b;
    }

    public void onRestart(float x, float movSpeed) {
        velocity.x = movSpeed;
        reset(x);
    }
}
