package Utils;

/**
 * Triplet object to transfer 3 pieces of information in a simple form
 * @param first first object being returned
 * @param second second object being returned
 * @param third third object being returned
 * @param <T> type of users choice
 * @param <F> type of users choice
 * @param <K> type of users choice
 */
public record Triplet<T, F, K>(T first, F second, K third) {
}
