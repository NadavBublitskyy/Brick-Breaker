import java.util.List;

/**
 * The Line class represents a line segment in a 2D space.
 */
public class Line {
    private Point start;
    private Point end;

    private static final double THRESHOLD = 0.000001; // Threshold for comparing doubles
    private static final Point NOT_INTERSECT = new Point(-1.001, -1.001);
    private static final Point INFINITE = new Point(-1.00001, -1.00001);

    /**
     * Constructs a Line object with start and end points.
     *
     * @param start the starting point of the line
     * @param end   the ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a Line object with coordinates of start and end points.
     *
     * @param x1 the x-coordinate of the starting point
     * @param y1 the y-coordinate of the starting point
     * @param x2 the x-coordinate of the ending point
     * @param y2 the y-coordinate of the ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Compares two double values with a threshold.
     *
     * @param a the first double value
     * @param b the second double value
     * @return true if the difference between a and b is less than the threshold, false otherwise
     */
    public static boolean compareNumbers(double a, double b) {
        return Math.abs(a - b) < THRESHOLD;
    }

    /**
     * Checks if a line is reduced to a dot.
     *
     * @param line the line to check
     * @return true if the start and end points of the line are the same, false otherwise
     */
    public boolean isLineADot(Line line) {
        return compareNumbers(line.start.getX(), line.end.getX()) && compareNumbers(line.start.getY(), line.end.getY());
    }

    /**
     * Checks if a line is parallel to the Y-axis.
     *
     * @param line the line to check
     * @return true if the start and end points of the line have the same X-coordinate, false otherwise
     */
    public boolean isLineParallelToY(Line line) {
        return compareNumbers(line.start.getX(), line.end.getX());
    }

    /**
     * Checks if a line is parallel to the X-axis.
     *
     * @param line the line to check
     * @return true if the start and end points of the line have the same Y-coordinate, false otherwise
     */
    public boolean isLineParallelToX(Line line) {
        return compareNumbers(line.start.getY(), line.end.getY());
    }

    /**
     * Calculates the slope of a line.
     *
     * @param line the line whose slope is to be calculated
     * @return the slope of the line
     */
    public static double slope(Line line) {
        return (line.end.getY() - line.start.getY()) / (line.end.getX() - line.start.getX());
    }

    /**
     * Calculates the y-intercept of a line.
     *
     * @param line the line whose y-intercept is to be calculated
     * @return the y-intercept of the line
     */
    public static double intercept(Line line) {
        return line.end.getY() - (slope(line) * line.end.getX());
    }

    /**
     * Returns the maximum of two double values.
     *
     * @param a the first double value
     * @param b the second double value
     * @return the maximum of a and b
     */
    public static double max(double a, double b) {
        return a > b ? a : b;
    }

    /**
     * Returns the minimum of two double values.
     *
     * @param a the first double value
     * @param b the second double value
     * @return the minimum of a and b
     */
    public static double min(double a, double b) {
        return a > b ? b : a;
    }

    /**
     * Checks if a number is within a specified range.
     *
     * @param num the number to check
     * @param min the minimum value of the range
     * @param max the maximum value of the range
     * @return true if the number is within the range, false otherwise
     */
    public static boolean inRange(double num, double min, double max) {
        return (compareNumbers(num, min) || num > min) && (compareNumbers(num, max) || num < max);
    }

    /**
     * Determines the intersection point if both lines are points.
     *
     * @param line the other line
     * @return the intersection point if the lines intersect, NOT_INTERSECT otherwise
     */
    public Point pointPoint(Line line) {
        return line.equals(this) ? this.start : NOT_INTERSECT;
    }

    /**
     * Determines the intersection point if one line is parallel to the Y-axis and the other is a point.
     *
     * @param line the other line
     * @return the intersection point if the lines intersect, NOT_INTERSECT otherwise
     */
    public Point pointParallelToY(Line line) {
        return compareNumbers(this.start.getX(), line.start.getX()) ? this.start : NOT_INTERSECT;
    }

    /**
     * Determines the intersection point if one line is parallel to the X-axis and the other is a point.
     *
     * @param line the other line
     * @return the intersection point if the lines intersect, NOT_INTERSECT otherwise
     */
    public Point pointParallelToX(Line line) {
        return compareNumbers(this.start.getY(), line.start.getY()) ? this.start : NOT_INTERSECT;
    }

    /**
     * Determines the intersection point if one line is regular and the other is a point.
     *
     * @param line the other line
     * @return the intersection point if the lines intersect, NOT_INTERSECT otherwise
     */
    public Point pointRegular(Line line) {
        double minRangeX = min(line.start.getX(), line.end.getX());
        double maxRangeX = max(line.start.getX(), line.end.getX());
        double s = slope(line);
        double c = intercept(line);
        if (compareNumbers((s * this.start.getX()) + c, this.start.getY()) && inRange(this.start.getX(), minRangeX,
                maxRangeX)) {
            return this.start;
        }
        return NOT_INTERSECT;
    }

    /**
     * Determines the intersection point if one line is regular and the other is parallel to the X-axis.
     *
     * @param line the other line
     * @return the intersection point if the lines intersect, NOT_INTERSECT otherwise
     */
    public Point regularParallelToX(Line line) {
        double minRangeY = min(this.start.getY(), this.end.getY());
        double maxRangeY = max(this.start.getY(), this.end.getY());
        if (inRange(line.start.getY(), minRangeY, maxRangeY)) {
            double minRangeX = min(this.start.getX(), this.end.getX());
            double maxRangeX = max(this.start.getX(), this.end.getX());
            double minRangeX1 = min(line.start.getX(), line.end.getX());
            double maxRangeX1 = max(line.start.getX(), line.end.getX());
            double s = slope(this);
            double c = intercept(this);
            double xIntersectionPoint = (line.end.getY() - c) / s;
            if (inRange(xIntersectionPoint, minRangeX, maxRangeX) && inRange(xIntersectionPoint, minRangeX1,
                    maxRangeX1)) {
                return new Point(xIntersectionPoint, line.start.getY());
            }
        }
        return NOT_INTERSECT;
    }

    /**
     * Determines the intersection point if one line is regular and the other is parallel to the Y-axis.
     *
     * @param line the other line
     * @return the intersection point if the lines intersect, NOT_INTERSECT otherwise
     */
    public Point regularParallelToY(Line line) {
        double minRangeX = min(this.start.getX(), this.end.getX());
        double maxRangeX = max(this.start.getX(), this.end.getX());
        if (inRange(line.start.getX(), minRangeX, maxRangeX)) {
            double s = slope(this);
            double c = intercept(this);
            double yIntersectionPoint = (line.end.getX() * s) + c;
            double minRangeY = min(line.start.getY(), line.end.getY());
            double maxRangeY = max(line.start.getY(), line.end.getY());
            if (inRange(yIntersectionPoint, minRangeY, maxRangeY)) {
                return new Point(line.end.getX(), yIntersectionPoint);
            }
        }
        return NOT_INTERSECT;
    }

    /**
     * Determines the intersection point if both lines are regular.
     *
     * @param line the other line
     * @return the intersection point if the lines intersect, NOT_INTERSECT otherwise
     */
    public Point regularRegular(Line line) {
        double s1 = slope(this);
        double c1 = intercept(this);
        double s2 = slope(line);
        double c2 = intercept(line);
        double minRangeX1 = min(this.start.getX(), this.end.getX());
        double maxRangeX1 = max(this.start.getX(), this.end.getX());
        double minRangeX2 = min(line.start.getX(), line.end.getX());
        double maxRangeX2 = max(line.start.getX(), line.end.getX());
        double minRangeY1 = min(this.start.getY(), this.end.getY());
        double maxRangeY1 = max(this.start.getY(), this.end.getY());
        double minRangeY2 = min(line.start.getY(), line.end.getY());
        double maxRangeY2 = max(line.start.getY(), line.end.getY());

        if (compareNumbers(s1, s2) && compareNumbers(c1, c2)) {
            if (compareNumbers(minRangeX1, minRangeX2) && compareNumbers(maxRangeX1, maxRangeX2)) {
                return INFINITE;
            }
            if ((minRangeX1 > minRangeX2 && minRangeX1 < maxRangeX2) || (maxRangeX1 > minRangeX2
                    && maxRangeX1 < maxRangeX2)) {
                return INFINITE;
            }
            if ((line.start.getX() > minRangeX1 && line.start.getX() < maxRangeX1) || (line.end.getX() > minRangeX1
                    && line.end.getX() < maxRangeX1)) {
                return INFINITE;
            }
            if (compareNumbers(minRangeX1, minRangeX2) || compareNumbers(minRangeX1, maxRangeX2)) {
                return new Point(minRangeX1, s1 * minRangeX1 + c1);
            }
            if (compareNumbers(maxRangeX1, minRangeX2) || compareNumbers(maxRangeX1, maxRangeX2)) {
                return new Point(maxRangeX1, s1 * maxRangeX1 + c1);
            }
        }
        if (compareNumbers(s1, s2) && !compareNumbers(c1, c2)) {
            return NOT_INTERSECT;
        }
        double s3 = s1 - s2;
        double c3 = -1 * (c1 - c2);
        double xIntersectionPoint = c3 / s3;
        double yIntersectionPoint = (xIntersectionPoint * s2) + c2;
        if (compareNumbers((s1 * xIntersectionPoint + c1), (s2 * xIntersectionPoint) + c2)) {
            if (inRange(xIntersectionPoint, minRangeX1, maxRangeX1)
                    && inRange(xIntersectionPoint, minRangeX2, maxRangeX2)
                    && inRange(yIntersectionPoint, minRangeY1, maxRangeY1)
                    && inRange(yIntersectionPoint, minRangeY2, maxRangeY2)) {
                return new Point(xIntersectionPoint, yIntersectionPoint);
            }
        }
        return NOT_INTERSECT;
    }

    /**
     * Determines the intersection point if one line is parallel to the X-axis and the other is parallel to the Y-axis.
     *
     * @param line the other line
     * @return the intersection point if the lines intersect, NOT_INTERSECT otherwise
     */
    public Point parallelToXParallelToY(Line line) {
        double minRangeX = min(this.start.getX(), this.end.getX());
        double maxRangeX = max(this.start.getX(), this.end.getX());
        if (inRange(line.start.getX(), minRangeX, maxRangeX)) {
            double minRangeY = min(line.start.getY(), line.end.getY());
            double maxRangeY = max(line.start.getY(), line.end.getY());
            if (inRange(line.start.getY(), minRangeY, maxRangeY)) {
                return new Point(line.start.getX(), this.start.getY());
            }
        }
        return NOT_INTERSECT;
    }

    /**
     * Determines the intersection point if both lines are parallel to the X-axis.
     *
     * @param line the other line
     * @return the intersection point if the lines intersect, NOT_INTERSECT otherwise
     */
    public Point parallelToXParallelToX(Line line) {
        double minRangeY = min(this.start.getY(), line.start.getY());
        double maxRangeY = max(this.end.getY(), line.end.getY());
        if (compareNumbers(this.start.getY(), line.start.getY())) {
            double minRangeX1 = min(this.start.getX(), this.end.getX());
            double maxRangeX1 = max(this.end.getX(), this.end.getX());
            double minRangeX2 = min(line.start.getX(), line.start.getX());
            double maxRangeX2 = max(line.end.getX(), line.end.getX());
            if ((line.start.getX() > minRangeX1 && line.start.getX() < maxRangeX1)
                    || (line.end.getX() > minRangeX1 && line.end.getX() < maxRangeX1)) {
                return INFINITE;
            }
            if (compareNumbers(minRangeX1, minRangeX2) || compareNumbers(minRangeX1, maxRangeX2)) {
                return new Point(minRangeX1, this.start.getY());
            }
            if (compareNumbers(maxRangeX1, minRangeX2) || compareNumbers(maxRangeX1, maxRangeX2)) {
                return new Point(maxRangeX1, this.start.getY());
            }
        }
        return NOT_INTERSECT;
    }

    /**
     * Determines the intersection point if both lines are parallel to the Y-axis.
     *
     * @param line the other line
     * @return the intersection point if the lines intersect, NOT_INTERSECT otherwise
     */
    public Point parallelToYParallelToY(Line line) {
        if (this.equals(line)) {
            return INFINITE;
        }
        double minRangeX = min(this.start.getX(), line.start.getX());
        double maxRangeX = max(this.end.getX(), line.end.getX());
        if (compareNumbers(this.start.getX(), line.start.getX())) {
            double minRangeY1 = min(this.start.getY(), this.start.getY());
            double maxRangeY1 = max(this.end.getY(), this.end.getY());
            double minRangeY2 = min(line.start.getY(), line.start.getY());
            double maxRangeY2 = max(line.end.getY(), line.end.getY());
            if ((line.start.getY() > minRangeY1 && line.start.getY() < maxRangeY1)
                    || (line.end.getY() > minRangeY1 && line.end.getY() < maxRangeY1)) {
                return INFINITE;
            }
            if (compareNumbers(minRangeY1, minRangeY2) || compareNumbers(minRangeY1, maxRangeY2)) {
                return new Point(this.start.getX(), minRangeY1);
            }
            if (compareNumbers(maxRangeY1, minRangeY2) || compareNumbers(maxRangeY1, maxRangeY2)) {
                return new Point(this.start.getX(), maxRangeY1);
            }
        }
        return NOT_INTERSECT;
    }

    /**
     * Returns the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return this.end.distance(this.start);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double midX = (start.getX() + end.getX()) / 2;
        double midY = (start.getY() + end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Checks if two lines intersect.
     *
     * @param other the other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        Point result = new Point(0, 0);
        boolean isFirstLineADot = false;
        boolean isOtherLineADot = false;
        boolean isFirstLineParallelToY = false;
        boolean isOtherLineParallelToY = false;
        boolean isFirstLineParallelToX = false;
        boolean isOtherLineParallelToX = false;
        boolean isFirstLineNormal = false;
        boolean isOtherLineNormal = false;

        if (this.start.equals(this.end)) {
            isFirstLineADot = true;
        }
        if (other.start.equals(other.end)) {
            isOtherLineADot = true;
        }
        if (!isFirstLineADot && compareNumbers(this.start.getX(), this.end.getX())) {
            isFirstLineParallelToY = true;
        }
        if (!isOtherLineADot && compareNumbers(other.start.getX(), other.end.getX())) {
            isOtherLineParallelToY = true;
        }
        if (!isFirstLineADot && compareNumbers(this.start.getY(), this.end.getY())) {
            isFirstLineParallelToX = true;
        }
        if (!isOtherLineADot && compareNumbers(other.start.getY(), other.end.getY())) {
            isOtherLineParallelToX = true;
        }
        if (!isFirstLineADot && !isFirstLineParallelToX && !isFirstLineParallelToY) {
            isFirstLineNormal = true;
        }
        if (!isOtherLineADot && !isOtherLineParallelToX && !isOtherLineParallelToY) {
            isOtherLineNormal = true;
        }

        if (isFirstLineADot && isOtherLineADot) {
            result = this.pointPoint(other);
        }
        if (isFirstLineADot && isOtherLineParallelToY) {
            result = this.pointParallelToY(other);
        }
        if (isFirstLineADot && isOtherLineParallelToX) {
            result = this.pointParallelToX(other);
        }
        if (isFirstLineADot && isOtherLineNormal) {
            result = this.pointRegular(other);
        }
        if (isFirstLineParallelToX && isOtherLineADot) {
            result = other.pointParallelToX(this);
        }
        if (isFirstLineParallelToX && isOtherLineParallelToY) {
            result = this.parallelToXParallelToY(other);
        }
        if (isFirstLineParallelToX && isOtherLineParallelToX) {
            result = this.parallelToXParallelToX(other);
        }
        if (isFirstLineParallelToX && isOtherLineNormal) {
            result = other.regularParallelToX(this);
        }
        if (isFirstLineParallelToY && isOtherLineADot) {
            result = other.pointParallelToY(this);
        }
        if (isFirstLineParallelToY && isOtherLineParallelToX) {
            result = other.parallelToXParallelToY(this);
        }
        if (isFirstLineParallelToY && isOtherLineParallelToY) {
            result = this.parallelToYParallelToY(other);
        }
        if (isFirstLineParallelToY && isOtherLineNormal) {
            result = other.regularParallelToY(this);
        }
        if (isFirstLineNormal && isOtherLineADot) {
            result = other.pointRegular(this);
        }
        if (isFirstLineNormal && isOtherLineParallelToX) {
            result = this.regularParallelToX(other);
        }
        if (isFirstLineNormal && isOtherLineParallelToY) {
            result = this.regularParallelToY(other);
        }
        if (isFirstLineNormal && isOtherLineNormal) {
            result = this.regularRegular(other);
        }

        return result != NOT_INTERSECT;
    }

    /**
     * Checks if this line intersects with two other lines.
     *
     * @param other1 the first other line
     * @param other2 the second other line
     * @return true if this line intersects with both other lines, false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return this.isIntersecting(other1) && this.isIntersecting(other2);
    }

    /**
     * Returns the intersection point if the lines intersect, null otherwise.
     *
     * @param other the other line
     * @return the intersection point if the lines intersect, null otherwise
     */
    public Point intersectionWith(Line other) {
        if (this.isIntersecting(other)) {
            Point result = new Point(0, 0);
            boolean isFirstLineADot = false;
            boolean isOtherLineADot = false;
            boolean isFirstLineParallelToY = false;
            boolean isOtherLineParallelToY = false;
            boolean isFirstLineParallelToX = false;
            boolean isOtherLineParallelToX = false;
            boolean isFirstLineNormal = false;
            boolean isOtherLineNormal = false;

            if (this.start.equals(this.end)) {
                isFirstLineADot = true;
            }
            if (other.start.equals(other.end)) {
                isOtherLineADot = true;
            }
            if (compareNumbers(this.start.getX(), this.end.getX())) {
                isFirstLineParallelToY = true;
            }
            if (compareNumbers(other.start.getX(), other.end.getX())) {
                isOtherLineParallelToY = true;
            }
            if (compareNumbers(this.start.getY(), this.end.getY())) {
                isFirstLineParallelToX = true;
            }
            if (compareNumbers(other.start.getY(), other.end.getY())) {
                isOtherLineParallelToX = true;
            }
            if (!isFirstLineADot && !isFirstLineParallelToX && !isFirstLineParallelToY) {
                isFirstLineNormal = true;
            }
            if (!isOtherLineADot && !isOtherLineParallelToX && !isOtherLineParallelToY) {
                isOtherLineNormal = true;
            }

            if (isFirstLineADot && isOtherLineADot) {
                result = this.pointPoint(other);
            }
            if (isFirstLineADot && isOtherLineParallelToY) {
                result = this.pointParallelToY(other);
            }
            if (isFirstLineADot && isOtherLineParallelToX) {
                result = this.pointParallelToX(other);
            }
            if (isFirstLineADot && isOtherLineNormal) {
                result = this.pointRegular(other);
            }
            if (isFirstLineParallelToX && isOtherLineADot) {
                result = other.pointParallelToX(this);
            }
            if (isFirstLineParallelToX && isOtherLineParallelToY) {
                result = this.parallelToXParallelToY(other);
            }
            if (isFirstLineParallelToX && isOtherLineParallelToX) {
                result = this.parallelToXParallelToX(other);
            }
            if (isFirstLineParallelToX && isOtherLineNormal) {
                result = other.regularParallelToX(this);
            }
            if (isFirstLineParallelToY && isOtherLineADot) {
                result = other.pointParallelToY(this);
            }
            if (isFirstLineParallelToY && isOtherLineParallelToX) {
                result = other.parallelToXParallelToY(this);
            }
            if (isFirstLineParallelToY && isOtherLineParallelToY) {
                result = this.parallelToYParallelToY(other);
            }
            if (isFirstLineParallelToY && isOtherLineNormal) {
                result = other.regularParallelToY(this);
            }
            if (isFirstLineNormal && isOtherLineADot) {
                result = other.pointRegular(this);
            }
            if (isFirstLineNormal && isOtherLineParallelToX) {
                result = this.regularParallelToX(other);
            }
            if (isFirstLineNormal && isOtherLineParallelToY) {
                result = this.regularParallelToY(other);
            }
            if (isFirstLineNormal && isOtherLineNormal) {
                result = this.regularRegular(other);
            }
            if (result.equals(INFINITE)) {
                return null;
            }
            return result;
        }
        return null;
    }

    /**
     * Checks if two lines are equal.
     *
     * @param other the other line
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return this.start.equals(other.start) && this.end.equals(other.end)
                || this.start.equals(other.end) && this.end.equals(other.start);
    }

    /**
     * Calculates the closest intersection point to the start of this line within a given rectangle.
     *
     * @param rect The rectangle with which the line may intersect.
     * @return The closest intersection point to the start of the line, or null if there are no intersection points.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> points = rect.intersectionPoints(this);
        if (points == null || points.isEmpty()) {
            return null;
        }

        Point minPoint = points.get(0);
        for (int i = 1; i < points.size(); i++) {
            Point point = points.get(i);
            if (point.distance(this.start()) < minPoint.distance(this.start())) {
                minPoint = point;
            }
        }

        return minPoint;
    }

}
