/**
 * The HitListener interface should be implemented by any class whose instances are intended
 * to be notified whenever a block is hit by a ball.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the block that is being hit
     * @param hitter   the ball that is hitting the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
