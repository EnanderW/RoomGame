package commands;

import game.Game;
import game.object.GameObject;
import game.entities.Player;
import game.Room;

public final class GrabItemCommand extends Command {

    public GrabItemCommand(Game game) {
        super(game);
    }

    // a b c d

    @Override
    public void execute(String cmd, String[] args) {
        Room active = game.getActiveRoom();

        GameObject item = active.getInventory().grab(args[1]);
        Player player = game.getPlayer();

        player.getInventory().add(item);
        game.updateActiveRoom();
        game.updatePlayer();
    }
}
