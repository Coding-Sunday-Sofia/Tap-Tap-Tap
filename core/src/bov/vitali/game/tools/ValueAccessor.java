package bov.vitali.game.tools;

import aurelienribon.tweenengine.TweenAccessor;

// меняет значение
public class ValueAccessor implements TweenAccessor<Value> {

    //получаем значение которое мы хотим изменить для нашего обьекта
    // и помещаем их в масив с которым
    // твин энджин выполняет преобразование
    @Override
    public int getValues(Value target, int tweenType, float[] returnValues) {
        returnValues[0] = target.getVal();
        return 1;
    }

    //измененное значение прередаем в объект
    @Override
    public void setValues(Value target, int tweenType, float[] newValues) {
        target.setVal(newValues[0]);
    }
    // для каждого класса, который будет менять твин энджин надо
    // создать твин акцесснор
}
