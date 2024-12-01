/**
 * Holds information about collisions.
 * This includes the collision point and the collidable object involved in the collision.
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable a;
    /**
     * Constructor for CollisionInfo.
     * Initializes a new instance with the specified collision point and collidable object.
     *
     * @param collisionPoint the point at which the collision occurs
     * @param a the object involved in the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable a) {
        this.collisionPoint = collisionPoint;
        this.a = a;
    }


    /**
     * Returns the point at which the collision occurs.
     *
     * @return the collision point
     */
    public Point collisionPoint() {
        return collisionPoint;
    }
    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object
     */
    // the collidable object involved in the collision.
    public Collidable collisionObject() {
        return a;

    }
}
