package commands;

import game.Game;
import game.Room;

public final class ChangeRoomCommand extends Command {

    // changeroom 2

    public ChangeRoomCommand(Game game) {
        super(game);
    }

    @Override
    public void execute(String cmd, String[] args) {
        // Kolla om kommandot är skrivet som det ska
        if (args.length != 2) {
            System.out.println("Användning: changeroom <index>");
            return;
        }

        int roomIndex;
        try {
            // Hämta kommandos andra argument
            // Hämta nummer ifrån string
            roomIndex = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Du måste skicka in ett nummer som argument");
            return;
        }

        Room[] map = game.getMap();
        // Om vi frågar efter ett rum som inte finns
        if (roomIndex < 0 || roomIndex >= map.length) {
            System.out.println("Det finns inte ett sådant rum");
            return;
        }

        // Sätt spelets aktiva rum
        game.setActiveRoom(roomIndex);
    }
}
