import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The ScoreIndicator class represents a sprite that displays the current score on the screen.
 */
public class ScoreIndicator implements Sprite {

    private Counter scoreCounter;
    private static final int SCORE_X = 350; // X position for score display
    private static final int SCORE_Y = 20; // Y position for score display

    /**
     * Constructs a ScoreIndicator object with the given score counter.
     *
     * @param scoreCounter the counter that keeps track of the score
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }

    /**
     * Draws the score indicator on the given DrawSurface.
     *
     * @param d the surface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(SCORE_X, SCORE_Y, "Score: " + scoreCounter.getValue(), 16);
    }

    /**
     * This method is intentionally left empty as the score indicator doesn't change over time.
     */
    @Override
    public void timePassed() {
        // Intentionally left empty
    }

    /**
     * Adds the score indicator to the game as a sprite.
     *
     * @param game the game to which the score indicator will be added
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}
