package game.entities;

import game.Game;
import game.Room;

import java.util.concurrent.ThreadLocalRandom;

public final class Person extends NPC {

    private long pickupItem;
    private long changeRoom = System.currentTimeMillis();
    private int room;

    public Person(Game game, String name, int room) {
        super(game, name);
        this.room = room;
    }

    @Override
    public void pickupItem() {

    }

    @Override
    public void tick() {
        long now = System.currentTimeMillis();

        if (now >= pickupItem) {
            // Ta upp ett item
        }

        // Om vi ska byta rum
        if (now >= changeRoom) {

            // Byt rum
            Room[] map = game.getMap();
            // Hämta ett random rum index (0 till så många rum som finns)
            int randomRoom = ThreadLocalRandom.current().nextInt(map.length);

            // Hämta det nuvarande rummet man är i
            Room current = map[room];
            // Hämta det rummet man ska gå till
            Room newRoom = map[randomRoom];

            // Ta bort npcn from det nuvarande rummet
            current.removeNPC(this);

            // Lägg till npc i det nya rummet
            newRoom.addNpc(this);

            // Om vi har gått in till eller gått ifrån det aktiva rummet (det som visas i GUIn) så uppdaterar vi GUIn
            if (room == game.getActiveRoomIndex() || randomRoom == game.getActiveRoomIndex()) {
                game.updateActiveRoom();
            }

            // Säg att vi nu är i det nya rummet
            room = randomRoom;

            // Säg när vi ska byta rum nästa gång. I detta fall om 5-10 sekunder
            changeRoom = now + ThreadLocalRandom.current().nextLong(5000, 10000);
        }
    }
}
