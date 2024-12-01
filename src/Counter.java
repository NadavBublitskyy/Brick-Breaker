/**
 * The Counter class is used to keep track of a numerical count.
 */
public class Counter {

    private int counter;

    /**
     * Constructs a Counter object with an initial count of 0.
     */
    public Counter() {
        counter = 0;
    }

    /**
     * Constructs a Counter object with the specified initial count.
     *
     * @param num the initial count
     */
    public Counter(int num) {
        counter = num;
    }

    /**
     * Adds the specified number to the current count.
     *
     * @param number the number to add
     */
    void increase(int number) {
        counter += number;
    }

    /**
     * Subtracts the specified number from the current count.
     *
     * @param number the number to subtract
     */
    void decrease(int number) {
        counter -= number;
    }

    /**
     * Gets the current count.
     *
     * @return the current count
     */
    int getValue() {
        return counter;
    }
}
