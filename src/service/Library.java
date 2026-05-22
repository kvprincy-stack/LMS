package service;

import java.util.List;
import java.util.Optional;

import model.Book;
import model.User;
import utility.LibraryMessages;

/**
 * Library coordinates book operations (add, search, borrow, return, reserve, list).
 * Storage is delegated to BookRepository.
 * Reservation queue is delegated to ReservationService.
 * Console output is delegated to LibraryMessages.
 */
public class Library {

    private final BookRepository bookRepository;
    private final ReservationService reservationService;

    public Library() {
        this(new BookRepository(), new ReservationService());
    }

    public Library(BookRepository bookRepository, ReservationService reservationService) {
        this.bookRepository = bookRepository;
        this.reservationService = reservationService;
    }

    public void addBook(Book book) {
        bookRepository.save(book);
        LibraryMessages.bookAdded(book);
    }

    public Optional<Book> searchBookByTitle(String title) {
        Optional<Book> match = bookRepository.findByTitle(title);
        if (match.isPresent()) {
            LibraryMessages.bookFound(match.get());
        } else {
            LibraryMessages.bookNotFoundByTitle(title);
        }
        return match;
    }

    public void displayAllBooks() {
        if (bookRepository.isEmpty()) {
            LibraryMessages.libraryEmpty();
            return;
        }
        LibraryMessages.libraryHeader();
        List<Book> allBooks = bookRepository.findAll();
        for (Book book : allBooks) {
            book.diplayBookInfo();
            LibraryMessages.bookSeparator();
        }
    }

    public boolean borrowBook(User user, int bookId) {
        Optional<Book> match = bookRepository.findById(bookId);
        if (match.isEmpty()) {
            LibraryMessages.bookNotFoundById(bookId);
            return false;
        }
        return user.borrowBook(match.get());
    }

    public boolean returnBook(User user, int bookId) {
        Optional<Book> match = bookRepository.findById(bookId);
        if (match.isEmpty()) {
            LibraryMessages.bookNotFoundById(bookId);
            return false;
        }
        Book book = match.get();
        boolean returned = user.returnBook(book);
        if (returned) {
            assignToNextReservation(book);
        }
        return returned;
    }

    public boolean reserveBook(User user, int bookId) {
        Optional<Book> match = bookRepository.findById(bookId);
        if (match.isEmpty()) {
            LibraryMessages.bookNotFoundById(bookId);
            return false;
        }
        Book book = match.get();
        if (book.isAvailable()) {
            LibraryMessages.cannotReserveAvailableBook(book);
            return false;
        }
        if (user.hasBorrowed(book)) {
            LibraryMessages.cannotReserveOwnBook(user, book);
            return false;
        }
        boolean added = reservationService.reserve(bookId, user);
        if (!added) {
            LibraryMessages.alreadyInQueue(user, book);
            return false;
        }
        LibraryMessages.bookReserved(user, book, reservationService.queueSize(bookId));
        return true;
    }

    private void assignToNextReservation(Book book) {
        if (!reservationService.hasReservations(book.getId())) {
            return;
        }
        Optional<User> nextUser = reservationService.pollNext(book.getId());
        if (nextUser.isEmpty()) {
            return;
        }
        LibraryMessages.autoAssigningReservation(nextUser.get(), book);
        nextUser.get().borrowBook(book);
    }
}
