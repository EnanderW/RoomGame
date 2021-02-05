package game.entities;

import game.Inventory;

public class Player {

    private Inventory inventory;

    public Player() {
        this.inventory = new Inventory(20);
    }

    public Inventory getInventory() {
        return inventory;
    }
}

