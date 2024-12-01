import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * The Game class represents the main game logic and handles the game loop.
 */
public class Game {

    public static final double WIDTH = 800;
    public static final double HEIGHT = 600;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter counter;
    private Counter ballCounter;
    private Counter score;
    private ScoreIndicator scoreIndicator;

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a sprite to the game.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initializes a new game by creating the blocks, balls, and other game objects.
     */
    public void initialize() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        counter = new Counter();
        ballCounter = new Counter(3);
        score = new Counter();

        BlockRemover remover = new BlockRemover(this, counter);
        BallRemover ballRemover = new BallRemover(this, ballCounter);
        ScoreTrackingListener scoreL = new ScoreTrackingListener(score);

        createBlocks(remover, scoreL, counter);

        Ball ball1 = new Ball(new Point(88, 50), 7, Color.BLACK);
        ball1.setVelocity(3, 4);
        ball1.setGame(environment);
        Ball ball2 = new Ball(new Point(134, 70), 7, Color.BLACK);
        ball2.setVelocity(3, 4);
        ball2.setGame(environment);
        Ball ball3 = new Ball(new Point(44, 100), 7, Color.BLACK);
        ball3.setVelocity(5, 4);
        ball3.setGame(environment);

        Rectangle rightRect = new Rectangle(new Point(WIDTH - 30, HEIGHT), 30, HEIGHT);
        Rectangle leftRect = new Rectangle(new Point(0, HEIGHT), 30, HEIGHT);
        Rectangle upRect = new Rectangle(new Point(30, HEIGHT), WIDTH, 30);
        Rectangle downRect = new Rectangle(new Point(0, 30), WIDTH - 30, 30);
        Rectangle deathRegion = new Rectangle(new Point(30, HEIGHT), WIDTH, 30);

        Block rightCorner = new Block(rightRect, Color.GRAY);
        Block leftCorner = new Block(leftRect, Color.GRAY);
        Block upCorner = new Block(upRect, Color.GRAY);
        Block downCorner = new Block(downRect, Color.GRAY);
        Block deathRegionB = new Block(deathRegion, Color.GRAY);

        leftCorner.addToGame(this);
        rightCorner.addToGame(this);
        downCorner.addToGame(this);
        ball1.addToGame(this);
        ball2.addToGame(this);
        ball3.addToGame(this);
        deathRegionB.addToGame(this);

        deathRegionB.addHitListener(ballRemover);

        ScoreIndicator scoreIndicator = new ScoreIndicator(score);
        scoreIndicator.addToGame(this);
    }

    /**
     * Runs the game and starts the animation loop.
     */
    public void run() {
        GUI gui = new GUI("title", (int) WIDTH, (int) HEIGHT);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        Rectangle paddleShape = new Rectangle(new Point(WIDTH / 2 - 50, HEIGHT - 50), 100, 20);
        Paddle paddle = new Paddle(keyboard, paddleShape, 10);

        paddle.addToGame(this);

        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (true) {
            if (counter.getValue() == 0) {
                score.increase(100);
            }
            if (counter.getValue() == 0 || ballCounter.getValue() == 0) {
                gui.close();
            }
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            long usedTime = System.currentTimeMillis() - startTime;
            long millisecondsLeftToSleep = millisecondsPerFrame - usedTime;
            if (millisecondsLeftToSleep > 0) {
                sleeper.sleepFor(millisecondsLeftToSleep);
            }
        }
    }

    /**
     * Creates the blocks for the game.
     */
    public void createBlocks(HitListener a, HitListener b, Counter counter) {
        int blockWidth = 47;
        int blockHeight = 20;
        int startY = 100;
        int startX = 230;
        Color[] colors = {Color.BLUE, Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.PINK};

        for (int i = 0; i < 6; i++) {
            for (int j = 12 - i; j > 0; j--) {
                Point upperLeft = new Point(startX + (12 - j) * blockWidth, startY + i * blockHeight);
                Rectangle blockShape = new Rectangle(upperLeft, blockWidth, blockHeight);
                Block block = new Block(blockShape, colors[i]);
                block.addToGame(this);
                block.addHitListener(a);
                block.addHitListener(b);
                counter.increase(1);
            }
        }
    }

    /**
     * Removes a collidable object from the game environment.
     *
     * @param c the collidable object to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Removes a sprite from the game.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
}
