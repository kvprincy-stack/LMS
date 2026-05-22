import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Manages reservation queues for books.
 * Each book id maps to an ordered queue of waiting users (FIFO).
 */
public class ReservationService {

    private final Map<Integer, Deque<User>> queuesByBookId = new HashMap<>();

    /**
     * Adds a user to the reservation queue for a given book.
     * Returns false if the user is already in the queue.
     */
    public boolean reserve(int bookId, User user) {
        Deque<User> queue = queuesByBookId.computeIfAbsent(bookId, k -> new ArrayDeque<>());
        if (queue.contains(user)) {
            return false;
        }
        queue.addLast(user);
        return true;
    }

    /**
     * Removes and returns the next user waiting for a book, if any.
     */
    public Optional<User> pollNext(int bookId) {
        Deque<User> queue = queuesByBookId.get(bookId);
        if (queue == null || queue.isEmpty()) {
            return Optional.empty();
        }
        User next = queue.pollFirst();
        if (queue.isEmpty()) {
            queuesByBookId.remove(bookId);
        }
        return Optional.of(next);
    }

    public boolean hasReservations(int bookId) {
        Deque<User> queue = queuesByBookId.get(bookId);
        return queue != null && !queue.isEmpty();
    }

    public int queueSize(int bookId) {
        Deque<User> queue = queuesByBookId.get(bookId);
        return queue == null ? 0 : queue.size();
    }

    public List<User> peekQueue(int bookId) {
        Deque<User> queue = queuesByBookId.get(bookId);
        if (queue == null) {
            return Collections.emptyList();
        }
        return List.copyOf(queue);
    }
}
