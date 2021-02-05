package game.object;

public class GameObject {

    private String name;
    boolean moveAble;

    public GameObject(String name, boolean moveAble) {
        this.name = name;
        this.moveAble = moveAble;


    }

    public boolean isMoveAble() {
        return this.moveAble;
    }

    public String getName() {
        return this.name;
    }


    public String toString() {
        return this.name;
    }


}
