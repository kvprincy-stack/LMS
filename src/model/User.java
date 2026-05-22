package model;

import java.util.ArrayList;
import java.util.List;

import utility.LibraryMessages;

/**
 * Represents a library user who can borrow and return books.
 * Tracks the list of books currently borrowed by this user.
 */
public class User {

    private int userId;
    private String userName;
    private final List<Book> borrowedBooks;

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public boolean borrowBook(Book book) {
        if (book == null) {
            LibraryMessages.invalidBook();
            return false;
        }
        if (borrowedBooks.contains(book)) {
            LibraryMessages.alreadyBorrowedByUser(this, book);
            return false;
        }
        if (!book.isAvailable()) {
            LibraryMessages.bookUnavailable(book);
            return false;
        }
        book.setAvailable(false);
        borrowedBooks.add(book);
        LibraryMessages.userBorrowed(this, book);
        return true;
    }

    public boolean returnBook(Book book) {
        if (book == null) {
            LibraryMessages.invalidBook();
            return false;
        }
        if (!borrowedBooks.contains(book)) {
            LibraryMessages.notBorrowedByUser(this, book);
            return false;
        }
        book.setAvailable(true);
        borrowedBooks.remove(book);
        LibraryMessages.userReturned(this, book);
        return true;
    }

    public boolean hasBorrowed(Book book) {
        return borrowedBooks.contains(book);
    }

    public void displayBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println(userName + " has no borrowed books.");
            return;
        }
        System.out.println("=== Books Borrowed by " + userName + " ===");
        for (Book book : borrowedBooks) {
            book.diplayBookInfo();
            System.out.println("----------------------------");
        }
    }
}
