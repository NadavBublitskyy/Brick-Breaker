/*
 * Point
 *
 * Version 1.0
 *
 * Copyright (c) 2023
 */

/**
 * This class represents a point in a 2D coordinate system.
 */
public class Point {

    /**
     * The x-coordinate of the point.
     */
    private double x;

    /**
     * The y-coordinate of the point.
     */
    private double y;

    /**
     * Constructs a new Point with the given coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */

    private static final double THRESHOLD = 0.000001;
    /**
     * Compares two double values to determine if they are effectively equal within a specified threshold.
     *
     * @param a the first number to compare.
     * @param b the second number to compare.
     * @return true if the absolute difference between a and b is less than the threshold, otherwise false.
     */
    public static boolean compareNumbers(double a, double b) {
        return Math.abs(a - b) < THRESHOLD;
    }

    /**
     * Constructs a new point with the specified x and y coordinates.
     *
     * @param x the x-coordinate of the new point.
     * @param y the y-coordinate of the new point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other The other point.
     * @return The distance between the two points.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    /**
     * Checks if this point is equal to another point.
     *
     * @param other The other point to compare.
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return compareNumbers(this.x, other.x) && compareNumbers(this.y, other.y);
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return The x-coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return The y-coordinate.
     */
    public double getY() {
        return y;
    }
}