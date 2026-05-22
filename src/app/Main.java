package app;

import java.util.Optional;
import java.util.Scanner;

import model.Book;
import model.User;
import service.Library;

/**
 * Console entry point for the Library Management System.
 * Provides a menu-driven loop backed by Scanner input.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Library library = new Library();
    private static final User currentUser = new User(1, "Guest");

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readMenuChoice();
            switch (choice) {
                case 1 -> addBookFlow();
                case 2 -> library.displayAllBooks();
                case 3 -> searchBookFlow();
                case 4 -> borrowBookFlow();
                case 5 -> returnBookFlow();
                case 6 -> reserveBookFlow();
                case 7 -> {
                    System.out.println("Exiting... Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please select 1-7.");
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("===== Library Menu =====");
        System.out.println("1. Add Book");
        System.out.println("2. View Books");
        System.out.println("3. Search Book");
        System.out.println("4. Borrow Book");
        System.out.println("5. Return Book");
        System.out.println("6. Reserve Book");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int readMenuChoice() {
        String input = scanner.nextLine().trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static void addBookFlow() {
        int id = readInt("Enter book ID: ");
        String title = readLine("Enter title: ");
        String author = readLine("Enter author: ");
        Book book = new Book(id, title, author, true);
        library.addBook(book);
    }

    private static void searchBookFlow() {
        String title = readLine("Enter title to search: ");
        Optional<Book> found = library.searchBookByTitle(title);
        if (found.isEmpty()) {
            // Library already printed the not-found message.
        }
    }

    private static void borrowBookFlow() {
        int id = readInt("Enter book ID to borrow: ");
        library.borrowBook(currentUser, id);
    }

    private static void returnBookFlow() {
        int id = readInt("Enter book ID to return: ");
        library.returnBook(currentUser, id);
    }

    private static void reserveBookFlow() {
        int id = readInt("Enter book ID to reserve: ");
        library.reserveBook(currentUser, id);
    }
}
