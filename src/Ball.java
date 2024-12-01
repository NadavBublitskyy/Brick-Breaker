/*
 * Ball.java
 *
 * Version 1.0
 *
 * Copyright Notice
 */

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * The Ball class represents a ball object with a center point, radius, color, and velocity.
 * It provides methods to draw the ball, set and get its properties, and handle its movement.
 */
public class Ball implements Sprite {

    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    protected static final int WIDTH = 700;
    protected static final int HEIGHT = 700;
    private GameEnvironment game;

    /**
     * Constructs a Ball object with the given center point, radius, and color.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * Constructs a Ball object with the given x and y coordinates, radius, and color.
     *
     * @param x     the x-coordinate of the center point
     * @param y     the y-coordinate of the center point
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * Sets the game environment for the ball.
     *
     * @param game the game environment
     */
    public void setGame(GameEnvironment game) {
        this.game = game;
    }

    /**
     * Returns the x-coordinate of the center point.
     *
     * @return the x-coordinate of the center point
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Returns the y-coordinate of the center point.
     *
     * @return the y-coordinate of the center point
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Returns the radius of the ball.
     *
     * @return the radius of the ball
     */
    public double getSize() {
        return this.radius;
    }

    /**
     * Returns the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface the DrawSurface to draw the ball on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(getX(), getY(), this.radius);
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the new velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the ball using the given dx and dy values.
     *
     * @param dx the x-component of the new velocity
     * @param dy the y-component of the new velocity
     */
    public void setVelocity(double dx, double dy) {
        setVelocity(new Velocity(dx, dy));
    }

    /**
     * Sets the color of the ball.
     *
     * @param color the new color of the ball
     */
    public void setColor(java.awt.Color color) {
        this.color = color;
    }

    /**
     * Returns the current velocity of the ball.
     *
     * @return the current velocity of the ball
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * Moves the ball one step according to its velocity and the given boundaries.
     * The ball bounces off the boundaries when it hits them.
     *
     * @param width  the width of the boundaries
     * @param height the height of the boundaries
     */
    public void moveOneStep(int width, int height) {
        if (this.center.getX() - radius < 0) {
            setVelocity(-velocity.getX(), velocity.getY());
        }
        if (this.center.getX() + radius > width) {
            setVelocity(-velocity.getX(), velocity.getY());
        }
        if (this.center.getY() - radius < 0) {
            setVelocity(velocity.getX(), -velocity.getY());
        }
        if (this.center.getY() + radius > height) {
            setVelocity(velocity.getX(), -velocity.getY());
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Moves the ball one step according to its velocity and the game environment.
     * The ball bounces off the collidable objects in the game environment.
     */
    public void moveOneStep() {
        double radiousMimicX = this.radius;
        if (this.velocity.getX() < 0) {
            radiousMimicX = -radiousMimicX;
        }
        double radiousMimicY = this.radius;
        if (this.velocity.getY() < 0) {
            radiousMimicY = -radiousMimicY;
        }

        Point p2 = new Point(this.center.getX() + this.velocity.getX() + radiousMimicX,
                this.center.getY() + this.velocity.getY() + radiousMimicY);

        Line trajectory = new Line(this.center, p2);
        CollisionInfo info = game.getClosestCollision(trajectory);
        if (info == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            Velocity vv = info.collisionObject().hit(this, info.collisionPoint(), this.velocity);
            this.center = vv.applyToPoint(this.center);
            this.velocity = vv;
        }
    }

    /**
     * Moves the ball one step according to its velocity and the given gray boundaries.
     * The ball bounces off the gray boundaries when it hits them.
     *
     * @param width  the width of the boundaries
     * @param height the height of the boundaries
     */
    public void moveOneStepGray(int width, int height) {
        if (this.center.getX() - radius < 50) {
            setVelocity(-velocity.getX(), velocity.getY());
        }
        if (this.center.getX() + radius > width) {
            setVelocity(-velocity.getX(), velocity.getY());
        }
        if (this.center.getY() - radius < 50) {
            setVelocity(velocity.getX(), -velocity.getY());
        }
        if (this.center.getY() + radius > height) {
            setVelocity(velocity.getX(), -velocity.getY());
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Moves the ball one step according to its velocity and the given boundaries,
     * handling specific cases for corners and rectangles.
     */
    public void oneStepParams() {
        boolean xFlag = false;
        boolean yFlag = false;
        Velocity vFlag = this.velocity;

        if (checkOverlap(this.radius, this.center.getX(), this.center.getY(), 50, 50, 500, 500)
                && ((this.center.getX() - radius < 50 && this.center.getY() - radius < 50)
                || (this.center.getX() + radius > 500 && this.center.getY() - radius < 50)
                || (this.center.getX() - radius < 50 && this.center.getY() + radius > 500)
                || (this.center.getX() + radius > 500 && this.center.getY() + radius > 500))) {

            setVelocity(-this.velocity.getX(), -this.velocity.getY());
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }

        if (checkOverlap(this.radius, this.center.getX(), this.center.getY(), 450, 450, 600, 600)
                && ((this.center.getX() - radius < 450 && this.center.getY() - radius < 450)
                || (this.center.getX() + radius > 600 && this.center.getY() - radius < 450)
                || (this.center.getX() - radius < 450 && this.center.getY() + radius > 600)
                || (this.center.getX() + radius > 600 && this.center.getY() + radius > 600))) {

            setVelocity(-this.velocity.getX(), -this.velocity.getY());
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }

        if (this.center.getX() + radius >= 50 && this.center.getX() + radius <= 50 + this.velocity.getX()
                && this.center.getY() >= 50 && this.center.getY() <= 500) {
            setVelocity(-this.velocity.getX(), this.velocity.getY());
        }

        if (this.center.getX() - radius >= 500 && this.center.getX() - radius <= 500 - this.velocity.getX()
                && this.center.getY() >= 50 && this.center.getY() <= 450) {
            setVelocity(-this.velocity.getX(), this.velocity.getY());
        }

        if (this.center.getX() - radius >= 600 && this.center.getX() - radius <= 600 - this.velocity.getX()
                && this.center.getY() >= 450 && this.center.getY() <= 600) {
            setVelocity(-this.velocity.getX(), this.velocity.getY());
        }

        if (this.center.getX() + radius >= 450 && this.center.getX() + radius <= 450 + this.velocity.getX()
                && this.center.getY() >= 500 && this.center.getY() <= 600) {
            setVelocity(-this.velocity.getX(), this.velocity.getY());
        }

        if (this.center.getY() + radius >= 50 && this.center.getY() + radius <= 50 + this.velocity.getY()
                && this.center.getX() >= 50 && this.center.getX() <= 500) {
            setVelocity(this.velocity.getX(), -this.velocity.getY());
        }

        if (this.center.getY() + radius >= 450 && this.center.getY() + radius <= 450 + this.velocity.getY()
                && this.center.getX() >= 500 && this.center.getX() <= 600) {
            setVelocity(this.velocity.getX(), -this.velocity.getY());
        }

        if (this.center.getY() - radius <= 500 && this.center.getY() - radius >= 500 + this.velocity.getY()
                && this.center.getX() >= 50 && this.center.getX() <= 450) {
            setVelocity(this.velocity.getX(), -this.velocity.getY());
        }

        if (this.center.getY() - radius <= 500 && this.center.getY() - radius >= 500 + this.velocity.getY()
                && this.center.getX() >= 450 && this.center.getX() <= 600) {
            setVelocity(this.velocity.getX(), -this.velocity.getY());
        }

        if (this.center.getY() - radius <= 600 && this.center.getY() - radius >= 600 + this.velocity.getY()
                && this.center.getX() >= 450 && this.center.getX() <= 600) {
            setVelocity(this.velocity.getX(), -this.velocity.getY());
        }

        if (vFlag.getX() == velocity.getX() && vFlag.getY() == velocity.getY()) {
            moveOneStep(WIDTH, HEIGHT);
        }
    }

    /**
     * Checks if a circle with the given center (Xc, Yc) and radius R overlaps with the
     * rectangle defined by the coordinates (X1, Y1) and (X2, Y2).
     *
     * @param r   the radius of the circle
     * @param xc  the x-coordinate of the center of the circle
     * @param yc  the y-coordinate of the center of the circle
     * @param x1  the x-coordinate of the top-left corner of the rectangle
     * @param y1  the y-coordinate of the top-left corner of the rectangle
     * @param x2  the x-coordinate of the bottom-right corner of the rectangle
     * @param y2  the y-coordinate of the bottom-right corner of the rectangle
     * @return true if the circle overlaps with the rectangle, false otherwise
     */
    private static boolean checkOverlap(double r, double xc, double yc,
                                        double x1, double y1,
                                        double x2, double y2) {

        double xn = Math.max(x1, Math.min(xc, x2));
        double yn = Math.max(y1, Math.min(yc, y2));

        double dx = xn - xc;
        double dy = yn - yc;
        return (dx * dx + dy * dy) <= r * r;
    }

    /**
     * Moves the ball one step according to its velocity and the game environment.
     * This method is called once per game cycle.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Adds the ball to the given game.
     *
     * @param g the game to add the ball to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * Removes the ball from the given game.
     *
     * @param g the game to remove the ball from
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }

    /**
     * Generates a random color from a predefined set of colors.
     *
     * @return a randomly selected color
     */
    public Color randColor() {
        Color[] colors = {Color.BLUE, Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.PINK};
        Random random = new Random();
        int randomNumber = random.nextInt();
        randomNumber = Math.abs(randomNumber % 6);
        return colors[randomNumber];
    }
}
