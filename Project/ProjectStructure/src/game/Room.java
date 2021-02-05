package game;

import game.entities.NPC;
import game.entities.Person;
import game.object.GameObject;

import java.util.LinkedList;
import java.util.List;

public class Room {
    private String name;
    private String decription;
    private Inventory inventory;
    private List<NPC> npcs;
    //kan göra för att göra exit till så man inte kan gå till alla rum osv
    private String exitto;

    //kan göra så man endast kan gå in i vissa rum med actionlistener/command med
    public Room(String roomname, String roomdecription) {
        this.npcs = new LinkedList<>();
        this.name = roomname;
        this.decription = roomdecription;
        this.inventory = new Inventory(10);

    }

    public void addNpc(Person person) {
        this.npcs.add(person);
    }

    //Stream för att inte skriva ut null!!
    public void addObject(GameObject objects) {
        this.inventory.add(objects);
    }

    public String toString() {
        return name + " : " + decription + "\n" + inventory;
    }

    public String getDecription() {
        return decription;
    }

    public String getName() {
        return name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public List<NPC> getNPCs() {
        return this.npcs;
    }

    public void removeNPC(Person person) {
        this.npcs.remove(person);
    }
}
