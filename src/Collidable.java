/**
 * Interface for objects that can be collided with in the game.
 */
public interface Collidable {

    /**
     * Gets the collision rectangle of the object. This shape is used to check for
     * collisions with other objects.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that a collision has occurred at a specific point with a given velocity.
     * The method calculates and returns the new velocity after the collision based on
     * the object's properties and the impact.
     *
     * @param hitter          the ball that hits the object
     * @param collisionPoint  the point at which the collision occurs
     * @param currentVelocity the current velocity of the object before the collision
     * @return the new velocity after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
