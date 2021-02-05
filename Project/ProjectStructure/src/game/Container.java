package game;

import game.object.GameObject;

public class Container extends GameObject {
    private Inventory inventory;
    boolean locked;
//Kan skicka in en key och kolla om det är "den nyckeln"
    //kan skicka med inventory för att ändra size eller en int så man kan göra olika containers
    public Container(String name, Boolean moveAble, boolean locked){
        super(name,moveAble);
        this.inventory = new Inventory(3);
        this.locked = locked;
    }


    public boolean isLocked() {
        return this.locked;
    }
    public Inventory getInventory(){
        return this.inventory;
    }
    //tostring som ritar ut om den är låst eller om den är öppet
}
