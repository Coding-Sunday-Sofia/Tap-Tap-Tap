package bov.vitali.game.objects;


import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import bov.vitali.game.loader.ResourseLoader;

// Vector2 - контейнер для двух переменных
public class Fly {

    // позиция
    private Vector2 position;
    // скорость
    private Vector2 velocity;
    // ускорение
    private Vector2 acceleration;

    // круг столкновения для круга
    private Circle circle;

    private boolean isAlive;
    // значения угла поворота
    private float rotation;
    // ширина
    private int width;
    // высота
    private float height;
    private float originalY;

    // конструктор - размеры, положения круга, ширина, высота
    public Fly(float x, float y, int width, int height){
        this.width = width;
        this.height = height;
        this.originalY = y;

        // круг
        circle = new Circle();

        isAlive = true;

        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 460);
    }

    // определяет следует ли повернуть вниз
    public boolean isFalling(){
        return velocity.y > 110;
    }

    public boolean notFlap(){
        return velocity.y >70 || !isAlive;
    }

    // нажатие
    public void onClick(){
        // выключаем онклик если круг мертв
        if (isAlive) {
            velocity.y = -140;
            ResourseLoader.flap.play();
        }
    }

    /*
    начало кординат с левого верхнего угла
    положительное значение по оси У птица летит вниз
    DELTA - время, прошедшее с момента последнего запуска метода апдейт
    при обновлении птица движется по сути со скоростью дельта( пока выполняеться обновление)
    */
    public void update(float delta){

        /* добавляем отмаштабирований вектор ускорения
        к вектору скорости так мы получаем
        нашую новую скорость*/
        velocity.add(acceleration.cpy().scl(delta));

        // когда скорость игры достигает 200 мы ее ограничиваем
        if (velocity.y > 200){
            velocity.y = 200;
        }

        // не вылетает за верх
        if (position.y < -13) {
            position.y = -13;
            velocity.y = 0;
        }

        /*
        добавляем обнавленное смаштабирование значение скорости
        к позиции нашего круга, так мы получаем новую позицию
         */
        position.add(velocity.cpy().scl(delta));

        // изменяем кординаты круга когда круг перемещаеться
        circle.set(position.x + 9, position.y + 6, 6.5f);

        // вращаем круг вверх против часовой стрелки
        if (velocity.y < 0){
            // умножаем на дельту для эфекта нормализации
            // что бь круг поворачивался с одинаковой скоростью
            // если игра начнет тормозить или на оборот
            rotation -= 600 * delta;

            if (rotation < -20){
                rotation = -20;
            }
        }

        // ограничиваем поворот на нужном значении
        if(isFalling()){
            // поворачиваем вниз при падении
            rotation += 480 * delta;
            if (rotation > 90){
                rotation = 90;
            }
        }
    }

    // смерть
    public void die() {
        isAlive = false;
        // обнуляем скорость
        velocity.y = 0;
    }

    // гетеры для 3 переменных и для ширины и высоты
    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;

    }
    public float getRotation() {
        return rotation;
    }

    public int getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    // гетр для круга
    public Circle getCircle() {
        return circle;
    }

    public boolean isAlive() {
        return isAlive;
    }

    // возвращает значения переменных по умолчанию
    public void onRestart(int y) {
        rotation = 0;
        position.y = y;
        velocity.x = 0;
        velocity.y = 0;
        acceleration.x = 0;
        acceleration.y = 460;
        isAlive = true;
    }

    // возвращаемся в первоначальное положение и зависание на месте
    public void updateReady(float runTime) {
        position.y = 2 * (float) Math.sin(7 * runTime) + originalY;
    }
}