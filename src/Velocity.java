/*
 * Velocity
 *
 * Version 1.0
 *
 * Copyright (c) 2023
 */

/**
 * This class represents a velocity vector, specifying the change in position on the x and y axes.
 */
public class Velocity {

    /**
     * The change in the x-coordinate.
     */
    private double dx;

    /**
     * The change in the y-coordinate.
     */
    private double dy;

    /**
     * Constructs a new Velocity object with the specified changes in x and y coordinates.
     *
     * @param dx The change in the x-coordinate.
     * @param dy The change in the y-coordinate.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Applies the velocity to a given point and returns a new point with the updated position.
     *
     * @param p The point to which the velocity will be applied.
     * @return A new point with the updated position.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Returns the change in the x-coordinate.
     *
     * @return The change in the x-coordinate.
     */
    public double getX() {
        return this.dx;
    }

    /**
     * Returns the change in the y-coordinate.
     *
     * @return The change in the y-coordinate.
     */
    public double getY() {
        return this.dy;
    }

    /**
     * Creates a new Velocity object from the given angle and speed.
     *
     * @param angle The angle in degrees.
     * @param speed The speed.
     * @return A new Velocity object with the calculated changes in x and y coordinates.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle);
        double dx = speed * Math.cos(radians);
        double dy = speed * Math.sin(radians);
        return new Velocity(dx, dy);
    }
    /**
     * Calculates the speed of an object based on its velocity components.
     *
     * @return the current speed calculated as the Euclidean norm (magnitude) of the velocity vector (dx, dy).
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }
}