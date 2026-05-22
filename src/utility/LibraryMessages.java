package utility;

import model.Book;
import model.User;

/**
 * Centralizes all user-facing console messages so that
 * domain classes stay free of formatting concerns.
 */
public final class LibraryMessages {

    private LibraryMessages() {
        // Utility class - prevent instantiation
    }

    public static void bookAdded(Book book) {
        System.out.println("Book added: " + book.getTitle());
    }

    public static void bookFound(Book book) {
        System.out.println("Book found:");
        book.diplayBookInfo();
    }

    public static void bookNotFoundByTitle(String title) {
        System.out.println("No book found with title: " + title);
    }

    public static void bookNotFoundById(int id) {
        System.out.println("No book found with ID: " + id);
    }

    public static void libraryEmpty() {
        System.out.println("No books available in the library.");
    }

    public static void libraryHeader() {
        System.out.println("=== All Books in Library ===");
    }

    public static void bookSeparator() {
        System.out.println("----------------------------");
    }

    public static void bookBorrowed(Book book) {
        System.out.println("You have borrowed: " + book.getTitle());
    }

    public static void bookUnavailable(Book book) {
        System.out.println("Sorry, the book \"" + book.getTitle() + "\" is currently not available.");
    }

    public static void bookReturned(Book book) {
        System.out.println("You have returned: " + book.getTitle());
    }

    public static void bookWasNotBorrowed(Book book) {
        System.out.println("The book \"" + book.getTitle() + "\" was not borrowed.");
    }

    public static void invalidBook() {
        System.out.println("Invalid book.");
    }

    public static void userBorrowed(User user, Book book) {
        System.out.println(user.getUserName() + " borrowed: " + book.getTitle());
    }

    public static void userReturned(User user, Book book) {
        System.out.println(user.getUserName() + " returned: " + book.getTitle());
    }

    public static void alreadyBorrowedByUser(User user, Book book) {
        System.out.println(user.getUserName() + " has already borrowed \"" + book.getTitle() + "\".");
    }

    public static void notBorrowedByUser(User user, Book book) {
        System.out.println(user.getUserName() + " has not borrowed \"" + book.getTitle() + "\".");
    }

    public static void bookReserved(User user, Book book, int position) {
        System.out.println(user.getUserName() + " reserved \"" + book.getTitle()
                + "\". Position in queue: " + position + ".");
    }

    public static void cannotReserveAvailableBook(Book book) {
        System.out.println("The book \"" + book.getTitle() + "\" is available - borrow it directly instead of reserving.");
    }

    public static void cannotReserveOwnBook(User user, Book book) {
        System.out.println(user.getUserName() + " already has \"" + book.getTitle() + "\" and cannot reserve it.");
    }

    public static void alreadyInQueue(User user, Book book) {
        System.out.println(user.getUserName() + " is already in the reservation queue for \"" + book.getTitle() + "\".");
    }

    public static void autoAssigningReservation(User user, Book book) {
        System.out.println("Auto-assigning \"" + book.getTitle() + "\" to next reserved user: " + user.getUserName() + ".");
    }
}
