package commands;

import game.Game;
import game.object.GameObject;
import game.entities.Player;
import game.Room;

public final class DropItemCommand extends Command{

    public DropItemCommand(Game game) {
        super(game);
    }

    @Override
    public void execute(String cmd, String[] args) {
        Room active = game.getActiveRoom();
        Player player = game.getPlayer();

        GameObject item = player.getInventory().grab(args[1]);

        active.getInventory().add(item);
        game.updateActiveRoom();
        game.updatePlayer();
    }
}
