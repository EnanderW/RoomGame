package game;

import game.object.GameObject;

import java.util.Arrays;

public class Inventory {
    private GameObject[] objects;
    private int size;

    public Inventory(int size) {
        this.size = size;
        objects = new GameObject[size];
    }

    // Hämta ett spel objekt ifrån dess namn
    public GameObject grab(String name) {
        return getAndRemove(name, false);
    }

    //lägger till saker till listan//inventory
    public boolean add(GameObject go) {
        int index = getFirstEmptyIndex();
        //om det är fullt
        if (index == -1) {
            System.out.println("inventory is full");
            return false;
        }

        this.objects[index] = go;
        return true;
    }

    public String toString() {
        return Arrays.toString(this.objects);
    }

    private int getFirstEmptyIndex() {
        //Stream sen!! kollar om det finns ledig plats
        for (int i = 0; i < this.objects.length; i++) {
            if (this.objects[i] == null) {
                return i;

                //stream.filter****
            }
        }

        return -1;
    }

    // Hämtar ett objekt ifrån ett visst namn och tar bort det ifrån arrayen om det finns.
    private GameObject getAndRemove(String name, boolean ignoreUnmovable) {
        for (int i = 0; i < objects.length; i++) {
            GameObject object = objects[i];
            if (object == null) continue;
            if (!object.isMoveAble() && !ignoreUnmovable) continue;

            if (object.getName().equalsIgnoreCase(name)) {
                objects[i] = null;
                return object;
            }
        }

        return null;
    }

}
