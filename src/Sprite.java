import biuoop.DrawSurface;

/**
 * The Sprite interface defines methods for objects that can be drawn on the screen
 * and need to be updated over time. This includes handling of visual representation
 * and time-based changes in state.
 */
public interface Sprite {
    /**
     * Draws the sprite to the screen on the provided DrawSurface.
     *
     * @param d the DrawSurface on which the sprite will be drawn
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that a time unit has passed, allowing it to update its state
     * or position.
     */
    void timePassed();
}
