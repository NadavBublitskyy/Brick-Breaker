/**
 * The HitNotifier interface should be implemented by any class whose instances can notify
 * other objects about hit events.
 */
public interface HitNotifier {

    /**
     * Adds hl as a listener to hit events.
     *
     * @param hl the HitListener to add
     */
    void addHitListener(HitListener hl);

    /**
     * Removes hl from the list of listeners to hit events.
     *
     * @param hl the HitListener to remove
     */
    void removeHitListener(HitListener hl);
}
