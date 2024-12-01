import java.awt.Color;

/**
 * The ScoreTrackingListener class implements the HitListener interface and is used to track the score
 * in the game. It increments the score whenever a block is hit and changes the color of the hitting ball.
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    /**
     * Constructs a ScoreTrackingListener object with the given score counter.
     *
     * @param scoreCounter the counter that keeps track of the score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
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
        currentScore.increase(5);
        Color c = hitter.randColor();
        hitter.setColor(c);
    }
}
