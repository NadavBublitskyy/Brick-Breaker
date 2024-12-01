import java.util.ArrayList;
import java.util.List;

/**
 * Manages the game environment including all collidables.
 */
public class GameEnvironment {

    private List<Collidable> shapes;

    /**
     * Default constructor that initializes the list of shapes.
     */
    public GameEnvironment() {
        this.shapes = new ArrayList<>();
    }

    /**
     * Constructs a game environment with a pre-defined list of shapes.
     *
     * @param shapes a list of collidables to be managed
     */
    public GameEnvironment(List<Collidable> shapes) {
        this.shapes = shapes;
    }

    /**
     * Retrieves the list of collidables.
     *
     * @return the list of collidable shapes
     */
    public List<Collidable> getShapes() {
        return this.shapes;
    }

    /**
     * Adds a collidable to the environment.
     *
     * @param c the collidable to add
     */
    public void addCollidable(Collidable c) {
        shapes.add(c);
    }

    /**
     * Calculates the closest collision for a moving object following a given trajectory.
     * If no collision is detected, returns null.
     *
     * @param trajectory the path the object is moving along
     * @return information about the closest collision, or null if no collision is detected
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Point> points;
        Point closest = new Point(-1000, -1000);
        Collidable closestShape = null;

        for (Collidable shape : shapes) {
            points = shape.getCollisionRectangle().intersectionPoints(trajectory);
            if (points.isEmpty()) {
                continue;
            } else {
                Point collisionPoint = trajectory.closestIntersectionToStartOfLine(shape.getCollisionRectangle());
                if (collisionPoint.distance(trajectory.start()) < closest.distance(trajectory.start())) {
                    closest = collisionPoint;
                    closestShape = shape;
                }
            }
        }

        if (closestShape == null) {
            return null;
        }

        return new CollisionInfo(closest, closestShape);
    }

    /**
     * Removes a collidable from the environment.
     *
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        shapes.remove(c);
    }
}
