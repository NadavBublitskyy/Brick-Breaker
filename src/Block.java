import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a block which can collide and be drawn as a sprite.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle r;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * Constructs a block with specified rectangle and color.
     *
     * @param r     the rectangle defining the block's shape and position
     * @param color the color of the block
     */
    public Block(Rectangle r, Color color) {
        this.r = r;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Default constructor, creates a default rectangle.
     */
    public Block() {
        Rectangle a = new Rectangle(new Point(0, 0), 0, 0);
        this.r = a;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Gets the collision rectangle.
     *
     * @return the collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.r;
    }

    /**
     * Updates the velocity upon hit at a collision point.
     *
     * @param hitter          the ball that hits the block
     * @param collisionPoint  the point at which the collision occurs
     * @param currentVelocity the current velocity which will be changed upon collision
     * @return new velocity after the hit
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getX();
        double dy = currentVelocity.getY();
        Line line = new Line(collisionPoint, collisionPoint);

        if (line.isIntersecting(r.getTop()) || line.isIntersecting(r.getBottom())) {
            dy = -dy;
        }
        if (line.isIntersecting(r.getRight()) || line.isIntersecting(r.getLeft())) {
            dx = -dx;
        }

        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        if (!ballColorMatch(hitter) && color != Color.GRAY) {
            hitter.setColor(color);
        }

        return new Velocity(dx, dy);
    }

    /**
     * Draws the block on the given DrawSurface.
     *
     * @param d the surface to draw on
     */
    public void drawOn(DrawSurface d) {
        this.r.drawOn(d, this.color);
    }

    /**
     * Notifies the block that time has passed, currently does nothing.
     */
    public void timePassed() {
    }

    /**
     * Adds the block to the game, both as a sprite and a collidable object.
     *
     * @param g the game to which the block will be added
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Checks if the ball's color matches the block's color.
     *
     * @param ball the ball to check color match
     * @return true if the colors match, false otherwise
     */
    public Boolean ballColorMatch(Ball ball) {
        return this.color.equals(ball.getColor());
    }

    /**
     * Removes the block from the game.
     *
     * @param game the game from which the block will be removed
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * Adds a hit listener to the block.
     *
     * @param hl the hit listener to add
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Removes a hit listener from the block.
     *
     * @param hl the hit listener to remove
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Removes all hit listeners from the block.
     */
    public void removeHitListener() {
        for (int i = 0; i < hitListeners.toArray().length; i++) {
            hitListeners.remove(i);
        }
    }

    /**
     * Notifies all hit listeners about a hit event.
     *
     * @param hitter the ball that hits the block
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).hitEvent(this, hitter);
        }
    }

    /**
     * Returns the color of the block.
     *
     * @return the color of the block
     */
    public Color getColor() {
        return this.color;
    }
}
