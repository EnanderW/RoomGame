package game;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.setRunning(true);

        Thread thread = new Thread(game);
        thread.start();
    }
}
