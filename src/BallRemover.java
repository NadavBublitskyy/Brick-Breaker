/**
 * The BallRemover class is a HitListener that is responsible for removing balls from the game
 * when they hit a block and decrementing the counter of available balls.
 */
public class BallRemover implements HitListener {

    private Game game;
    private Counter balls;

    /**
     * Constructs a BallRemover object with the given game and counter of available balls.
     *
     * @param game          the game from which balls will be removed
     * @param availableBalls the counter of available balls
     */
    public BallRemover(Game game, Counter availableBalls) {
        this.game = game;
        this.balls = availableBalls;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the block that is being hit
     * @param hitter   the ball that is hitting the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.balls.decrease(1);
    }
}
