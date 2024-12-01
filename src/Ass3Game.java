/**
 * Main class for running the game.
 */
public class Ass3Game {

    /**
     * The main method to start the game.
     * It initializes and runs the game.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
