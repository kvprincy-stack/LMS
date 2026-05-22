import java.util.List;
import java.util.Optional;

/**
 * Handles storage and low-level lookup of Book entities.
 * Separated from Library so that persistence concerns
 * (in-memory now, DB/file later) can evolve independently.
 */
public class BookRepository {
    private final List<Book> books = new java.util.ArrayList<>();

    public void save(Book book) {
        books.add(book);
    }

    public List<Book> findAll() {
        return java.util.Collections.unmodifiableList(books);
    }

    public Optional<Book> findById(int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst();
    }

    public Optional<Book> findByTitle(String title) {
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst();
    }

    public boolean isEmpty() {
        return books.isEmpty();
    }
}
