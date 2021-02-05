package game.entities;

import game.Game;
import game.Inventory;

public abstract class NPC {

    protected final Game game;

    private String name;
    private Inventory inventory;

    public NPC(Game game, String name){
        this.game = game;
        this.name = name;
        this.inventory = new Inventory(1);
    }

    public Inventory getInventory() {
        return this.inventory;
    }
    public String toString (){
        return this.name + "is carrying " + this.inventory;
    }

    public abstract void pickupItem();
    public abstract void tick();
}
