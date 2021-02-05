package game;

import commands.ChangeRoomCommand;
import commands.Command;
import commands.DropItemCommand;
import commands.GrabItemCommand;
import game.entities.NPC;
import game.entities.Person;
import game.entities.Player;
import game.object.GameObject;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Game implements Runnable {

    private GUI gui;
    // Sparar det nuvarande aktiva rummet
    private int activeRoom;
    private Room[] map;

    private Player player;

    private boolean running = false;
    private Map<String, Command> commands;
    private Queue<String> commandQueue;

    /*

    För att ta upp ett objekt

    GameObject obj = room.grab("someName");
    if (obj != null) {
        playerInventory.add(obj);
    }

     */

    public Game() {
        this.player = new Player();
        this.commands = new HashMap<>();
        this.commandQueue = new ConcurrentLinkedQueue<>();

        setupGame();
        setupCommands();
    }

    @Override
    public void run() {
        long tps = 1000 / 60; // Max 60 gånger per sekund (om det ens funkar)
        long last = System.currentTimeMillis();
        while (running) {
            long now = System.currentTimeMillis();
            long diff = now - last;
            if (diff < tps) continue;

            last = now;
            handleCommands();

            // Loopa igenom alla rum
            for (Room room : map) {
                // Loopa igenom alla npcs
                final List<NPC> npcs = room.getNPCs();
                for (int i = npcs.size() - 1; i >= 0; i--) {
                    NPC npc = npcs.get(i);
                    // Kör tick (dens logik) på varje npc
                    npc.tick();
                }
            }
        }
    }

    public void handleCommands() {
        // Kolla om det finns några kommandon att köra
        String cmdString = commandQueue.poll();
        while (cmdString != null) {
            // Det finns ett kommando att köra
            // Dela upp strängen i bitar (separerad med mellanslag)
            String[] args = cmdString.split(" ");

            // Om strängen inte är tom (ie '')
            if (args.length > 0) {

                // Hämtar rätt kommando ifrån dess namn
                Command command = this.commands.get(args[0]);

                // Om kommandot finns
                if (command != null) {
                    // Kör kommandot
                    command.execute(cmdString, args);
                    System.out.println("Command: " + cmdString);
                }
            }

            // Hämta nästa kommando som ska köras (om den finns)
            cmdString = commandQueue.poll();
        }}

    public void addCommand(String cmd) {
        commandQueue.add(cmd);
    }

    // Sätter spelets aktiva rum (det som visas i GUIn)
    public void setActiveRoom(int index) {
        // Hämta ett rum från index
        activeRoom = index;
        updateActiveRoom();
    }

    public void updateActiveRoom() {
        Room room = map[activeRoom];
        String persons = "";
        for (NPC npc : room.getNPCs()) {
            persons += npc.toString();
        }

        String description = room.getDecription();
        // Visar rummet i GUIn
        gui.setShowRoom(description + "\n" + room.getInventory().toString() + "\n" + persons);
    }

    public void updatePlayer() {
        gui.setShowInventory(player.getInventory());
    }

    public void setupGame() {
        Room room1 = new Room("sallad","Ligger saker runt här");
        Room room2 = new Room("farkendallen","Ligger saker runt här och kistor");
        Room room3 = new Room("farkefdafsdndallen","Liggfdsfasder saker runt här och kistor");
        Room room4 = new Room("farkefdsaffdafsdndallen","Liggfdsfasder saker runt här och kistor");

        //Array som innehåller båda rummen
        map = new Room[4];
        map[0] = room1;
        map[1] = room2;
        map[2] = room3;
        map[3] = room4;

        //Gameobjects
        GameObject lampa = new GameObject("Taklampa",false);
        GameObject rock = new GameObject("rock",true);

        //lägger till object i rummen
        room1.addObject(lampa);
        room2.addObject(rock);

        Person jason = new Person(this, "Jason", 0);
        room1.addNpc(jason);
        jason.getInventory().add(rock);

        Person jack = new Person(this, "Jack", 2);
        room3.addNpc(jack);
        jack.getInventory().add(rock);

        Person albert = new Person(this, "Albert", 3);
        room4.addNpc(albert);
        albert.getInventory().add(rock);

        System.out.println(jason);

        Container box = new Container("en blå låda", false, true);

        room1.addObject(box);

        Inventory inventory = new Inventory(5);
        System.out.println(inventory.toString());
        inventory.add(rock);
        inventory.add(lampa);
        System.out.println(inventory.toString());

        this.gui = new GUI(this);

        int position = 0;
        gui.setShowRoom(map[position].toString());
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setupCommands() {
        // Lägg till kommando i lista av kommandon
        Command command = new ChangeRoomCommand(this);
        commands.put("changeroom", command);
        commands.put("cr", command);
        commands.put("changer", command);
        commands.put("room", command);

        command = new DropItemCommand(this);
        commands.put("dropitem", command);

        command = new GrabItemCommand(this);
        commands.put("grabitem", command);
    }

    public Room[] getMap() {
        return this.map;
    }

    public Room getActiveRoom() {
        return map[activeRoom];
    }

    public Player getPlayer() {
        return player;
    }

    public int getActiveRoomIndex() {
        return this.activeRoom;
    }
}
