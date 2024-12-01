import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Rectangle class represents a rectangular object with a width, height, and top-left corner position.
 * It provides methods to draw the rectangle, get its properties, and check if a ball can spawn within its bounds.
 */
public class Rectangle {

    private double width;
    private double height;
    private Point upperLeft;

    private static final double THRESHOLD = 0.000001; // Threshold for comparing doubles
    private static final Point NOT_INTERSECT = new Point(-1.001, -1.001);
    private static final Point INFINITE = new Point(-1.00001, -1.00001);

    /**
     * Constructs a Rectangle object with the given width, height, and top-left corner position.
     *
     * @param upperLeft the top-left corner position of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Constructs a Rectangle object with the given width, height, and top-left corner position.
     *
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     * @param upperLeft the top-left corner position of the rectangle
     */
    public Rectangle(double width, double height, Point upperLeft) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the top-left corner position of the rectangle.
     *
     * @return the top-left corner position of the rectangle
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * Draws the rectangle on the given DrawSurface with the specified color.
     *
     * @param surface the DrawSurface to draw the rectangle on
     * @param color   the color to draw the rectangle
     */
    public void drawOn(DrawSurface surface, Color color) {
        surface.setColor(color);
        surface.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY() - (int) height,
                (int) width, (int) height);


        //new part
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY() - (int) height,
                (int) width, (int) height);
    }

    /**
     * Checks if a ball with the given radius can spawn within the bounds of the rectangle.
     *
     * @param radius the radius of the ball
     * @return true if a ball can spawn within the rectangle, false otherwise
     */
    public boolean canBallSpawnInside(double radius) {
        return radius * 2 < width && radius * 2 < height;
    }

    /**
     * Returns a random spawn point for a ball within the bounds of the rectangle.
     *
     * @param radius the radius of the ball
     * @return a random spawn point for the ball within the rectangle
     */
    public Point getRandomSpawnPoint(double radius) {
        double x = this.upperLeft.getX() + radius + (Math.random() * (this.width - 2 * radius));
        double y = this.upperLeft.getY() - this.height + radius + (Math.random() * (this.height - 2 * radius));
        return new Point(x, y);
    }

    /**
     * Calculates the intersection points between the rectangle and a given line.
     *
     * @param line the line to calculate intersection points with
     * @return a list of intersection points, or null if the line is parallel to the rectangle's sides
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> points = new ArrayList<>();
        Line left = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY() - this.height);
        Line right = new Line(upperLeft.getX() + this.width, upperLeft.getY(),
                upperLeft.getX() + this.width, upperLeft.getY() - this.height);
        Line top = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + this.width, upperLeft.getY());
        Line bottom = new Line(upperLeft.getX(), upperLeft.getY() - this.height,
                upperLeft.getX() + this.width, upperLeft.getY() - this.height);

        if (left.isIntersecting(line)) {
            if (left.intersectionWith(line).equals(INFINITE)) {
                return null;
            } else {
                Point intersectionPoint = left.intersectionWith(line);
                points.add(intersectionPoint);
            }
        }
        if (right.isIntersecting(line)) {
            if (right.intersectionWith(line).equals(INFINITE)) {
                return null;
            } else {
                Point intersectionPoint = right.intersectionWith(line);
                points.add(intersectionPoint);
            }
        }

        if (top.isIntersecting(line)) {
            if (top.intersectionWith(line).equals(INFINITE)) {
                return null;
            } else {
                Point intersectionPoint = top.intersectionWith(line);
                points.add(intersectionPoint);
            }
        }

        if (bottom.isIntersecting(line)) {
            if (bottom.intersectionWith(line).equals(INFINITE)) {
                return null;
            } else {
                Point intersectionPoint = bottom.intersectionWith(line);
                points.add(intersectionPoint);
            }
        }
        points = removeDuplicates(points);

        return points;
    }

    /**
     * Removes duplicate points from the given list.
     *
     * @param list the list to remove duplicates from
     * @param <T>  the type of elements in the list
     * @return a new list without duplicate points
     */
    public static <T> List<Point> removeDuplicates(List<Point> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j))) {
                    list.remove(j);
                    j--;
                }
            }
        }
        return list;
    }

    /**
     * Returns the top line of the rectangle.
     *
     * @return the top line of the rectangle
     */
    public Line getTop() {
        return new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + this.width, upperLeft.getY());
    }

    /**
     * Returns the bottom line of the rectangle.
     *
     * @return the bottom line of the rectangle
     */
    public Line getBottom() {
        return new Line(upperLeft.getX(), upperLeft.getY() - this.height,
                upperLeft.getX() + this.width, upperLeft.getY() - this.height);
    }

    /**
     * Returns the left line of the rectangle.
     *
     * @return the left line of the rectangle
     */
    public Line getLeft() {
        return new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY() - this.height);
    }

    /**
     * Returns the right line of the rectangle.
     *
     * @return the right line of the rectangle
     */
    public Line getRight() {
        return new Line(upperLeft.getX() + this.width, upperLeft.getY(),
                upperLeft.getX() + this.width, upperLeft.getY() - this.height);
    }

/**
 * Checks if the given ball is inside the rectangle.
 *
 * @param ball the ball to check
 * @return true if the ball is inside the rectangle, false otherwise
 */
public boolean isBallInMe(Ball ball) {
    double ballX = ball.getX();
    double ballY = ball.getY();
    return ballX > this.upperLeft.getX()
            && ballX < this.upperLeft.getX() + this.width
            && ballY < this.upperLeft.getY()
            && ballY > this.upperLeft.getY() - this.height;
}
}