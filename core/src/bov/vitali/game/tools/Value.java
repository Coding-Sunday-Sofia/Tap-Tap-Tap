package bov.vitali.game.tools;

// обверта для флоат переменных
    /* твин энджин ( добавленые библиотеки в папке либс, рабо
    тают с обьектами а не с примитивами ( in float и т. д.)
     */
public class Value {

    private float val = 1;

    public float getVal() {
        return val;
    }

    public void setVal(float val) {
        this.val = val;
    }
}
