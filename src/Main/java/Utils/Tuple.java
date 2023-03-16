package Utils;

/**
 * Tuple object to transfer 2 pieces of information in a simple form
 * @param first first object being returned
 * @param second second object being returned
 * @param <T> type of users choice
 * @param <F> type of users choice
 */
public record Tuple<T, F>(T first, F second) {
}
