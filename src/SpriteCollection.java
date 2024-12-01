import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * The SpriteCollection class manages a collection of Sprite objects.
 * It supports adding sprites, notifying all sprites to perform a time step, and drawing all sprites onto a DrawSurface.
 */
public class SpriteCollection {

    private List<Sprite> sprites;

    /**
     * Constructs an empty collection of sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to be added
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Calls timePassed() on all sprites in the collection to simulate a time step.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).timePassed();
        }
    }

    /**
     * Draws all the sprites in the collection onto the given DrawSurface.
     *
     * @param d the DrawSurface onto which the sprites are drawn
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }

    /**
     * Removes a sprite from the collection.
     *
     * @param s the sprite to be removed
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * Retrieves the list of sprites.
     *
     * @return the list of sprites
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }
}
