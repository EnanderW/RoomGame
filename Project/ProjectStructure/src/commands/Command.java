package commands;

import game.Game;

public abstract class Command {

    protected final Game game;

    public Command(Game game) {
        this.game = game;
    }

    public abstract void execute(String cmd, String[] args);

}
