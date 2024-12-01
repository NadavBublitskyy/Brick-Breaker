/**
 * The PrintingHitListener class implements the HitListener interface and is used to print a message
 * whenever a block is hit by a ball.
 */
public class PrintingHitListener implements HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the block that is being hit
     * @param hitter   the ball that is hitting the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}
