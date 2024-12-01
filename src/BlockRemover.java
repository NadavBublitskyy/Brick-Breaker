/**
 * A BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {

    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructs a BlockRemover object with the specified game and counter for remaining blocks.
     *
     * @param game            the game from which blocks will be removed
     * @param remainingBlocks the counter for the number of remaining blocks
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Blocks that are hit should be removed from the game.
     * This method removes the block from the game and updates the remaining blocks count.
     * Also removes this listener from the block that is being removed from the game.
     *
     * @param beingHit the block that is being hit
     * @param hitter   the ball that hits the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (!beingHit.ballColorMatch(hitter)) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.game);
            this.remainingBlocks.decrease(1);
        }
    }
}
