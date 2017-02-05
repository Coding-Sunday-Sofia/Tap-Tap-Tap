package bov.vitali.game.objects;


import com.badlogic.gdx.math.Vector2;

// логика поведений обекта, невидимая колона помещается в конец очереди
// тоже самое с прямоугольником
public class Moving {
    protected Vector2 position;
    protected Vector2 velocity;
    protected int width;
    protected int height;
    protected boolean isMovingLeft;

    // инициализируем и создаем обьекты
    public Moving(float x, float y, int width, int height, float movSpeed) {
        position = new Vector2(x, y);
        velocity = new Vector2(movSpeed, 0);
        this.width = width;
        this.height = height;
        isMovingLeft = false;
    }

    /*
    проверяем если обьект выходит за рамки экрана присваиваем тру
    исмувинг лефт
     */
    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));

        if (position.x + width < 0) {
            isMovingLeft = true;
        }
    }

    // для сброса положения обьекта
    public void reset(float newX) {
        position.x = newX;
        isMovingLeft = false;
    }

    // для возможности останавливать обьекты
    public void stop() {
        velocity.x = 0;
    }

    // определить что обьект больше не виден на экране
    public boolean isScrolledLeft() {
        return isMovingLeft;
    }

    // методы доступа переменным к классам
    public float getTailX() {
        return position.x + width;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}