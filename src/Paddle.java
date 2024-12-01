import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * This class represents a paddle in a game, handling its movements and rendering.
 * It implements the Sprite and Collidable interfaces to integrate with the game's engine.
 */
public class Paddle implements Sprite, Collidable {

    public static final double WIDTH = 800;
    public static final double HEIGHT = 600;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private int speed;

    /**
     * Constructs a new Paddle object with specified keyboard sensor, shape, and movement speed.
     *
     * @param keyboard the keyboard sensor for paddle control
     * @param rectangle the geometric representation of the paddle
     * @param speed the speed at which the paddle moves
     */
    public Paddle(KeyboardSensor keyboard, Rectangle rectangle, int speed) {
        this.keyboard = keyboard;
        this.rectangle = rectangle;
        this.speed = speed;
    }

    /**
     * Moves the paddle to the left by its speed. If it moves beyond the left boundary, it wraps around to the right.
     */
    public void moveLeft() {
        double newX = this.getCollisionRectangle().getUpperLeft().getX() - speed;
        if (newX < 0) {
            double excess = 0 - newX;
            newX = WIDTH - excess;
        }
        this.rectangle = new Rectangle(new Point(newX, this.getCollisionRectangle().getUpperLeft().getY()),
                this.getCollisionRectangle().getWidth(), this.getCollisionRectangle().getHeight());
    }

    /**
     * Moves the paddle to the right by its speed. If it moves beyond the right boundary, it wraps around to the left.
     */
    public void moveRight() {
        double newX = this.getCollisionRectangle().getUpperLeft().getX() + speed;
        if (newX + this.getCollisionRectangle().getWidth() > WIDTH) {
            double excess = newX + this.getCollisionRectangle().getWidth() - WIDTH;
            newX = 0 - this.getCollisionRectangle().getWidth() + excess;
        }
        this.rectangle = new Rectangle(new Point(newX, this.getCollisionRectangle().getUpperLeft().getY()),
                this.getCollisionRectangle().getWidth(), this.getCollisionRectangle().getHeight());
    }

    /**
     * Draws the paddle on the given DrawSurface.
     *
     * @param d the draw surface on which the paddle is rendered
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.YELLOW);
        d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    /**
     * Updates the paddle's position based on the current key presses.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Provides the collision rectangle of the paddle.
     *
     * @return the rectangle that defines the paddle's boundaries
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Responds to a collision with the paddle, changing the velocity based on the hit's location.
     *
     * @param hitter the ball that hits the paddle
     * @param collisionPoint the point at which the collision occurred
     * @param currentVelocity the current velocity of the object hitting the paddle
     * @return the new velocity after the hit
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double hitX = collisionPoint.getX() - this.rectangle.getUpperLeft().getX();
        double regionWidth = this.rectangle.getWidth() / 5;
        int area = (int) (hitX / regionWidth) + 1;

        switch (area) {
            case 1:
                return Velocity.fromAngleAndSpeed(210, currentVelocity.getSpeed());
            case 2:
                return Velocity.fromAngleAndSpeed(240, currentVelocity.getSpeed());
            case 4:
                return Velocity.fromAngleAndSpeed(-60, currentVelocity.getSpeed());
            case 5:
                return Velocity.fromAngleAndSpeed(-30, currentVelocity.getSpeed());
            default:
                return new Velocity(currentVelocity.getX(), -currentVelocity.getY());
        }
    }

    /**
     * Adds this paddle to the game, registering it as both a sprite and a collidable.
     *
     * @param g the game to which this paddle is added
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
