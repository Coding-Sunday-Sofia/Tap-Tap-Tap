package bov.vitali.game.objects;


public class Grass extends Moving {

    public Grass(float x, float y, int width, int height, float movSpeed) {
        super(x, y, width, height, movSpeed);
    }

    // возвращаем прямоугольник в ее начальное положение, скорость меняем на мовспид
    public void onRestart(float x, float movSpeed) {
        position.x = x;
        velocity.x = movSpeed;
    }
}