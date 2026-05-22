# 📚 Library Management System (Console-Based)

A simple, beginner-friendly **console application** built in **Java** that helps you manage books in a library. You can add books, view them, search by title, borrow and return books — all from the command line.

---

## 1. Project Overview

This project is a small **console-based Library Management System** written in Java.
It is designed to teach beginners how to apply:

- Object-Oriented Programming (OOP)
- Clean code practices
- Separation of responsibilities
- Console input handling with `Scanner`

The system stores books in memory (using an `ArrayList`) and lets a single default user borrow and return books.

---

## 2. Features

| # | Feature | Description |
|---|---------|-------------|
| 1 | Add Book | Add a new book with ID, title, and author |
| 2 | View Books | Display all books in the library |
| 3 | Search Book | Search a book by its title (case-insensitive) |
| 4 | Borrow Book | Borrow a book by ID (only if available) |
| 5 | Return Book | Return a borrowed book |
| 6 | Exit | Quit the program safely |

Extra behind-the-scenes features:

- Prevents **duplicate borrowing** by the same user.
- Updates **availability** automatically when borrowed/returned.
- Centralized, friendly **validation messages**.

---

## 3. Folder Structure

```
MyWorkSpace/
├── bin/                       # Compiled .class files (after javac)
├── src/                       # Java source files
│   ├── Book.java
│   ├── BookRepository.java
│   ├── Library.java
│   ├── LibraryMessages.java
│   ├── User.java
│   └── Main.java
├── docs/                      # Project documentation
│   ├── requirements.md
│   └── coding-practices.md
└── README.md
```

---

## 4. Class Explanation

| Class | Responsibility |
|-------|----------------|
| `Book` | Represents a single book (id, title, author, availability). |
| `BookRepository` | Stores the books and provides lookups (by id, by title). |
| `Library` | Coordinates operations: add, view, search, borrow, return. |
| `LibraryMessages` | Centralizes all console messages (so logic is clean). |
| `User` | Represents a user and tracks their borrowed books. |
| `Main` | Console menu loop, reads user input using `Scanner`. |

### Class Relationships (Simple View)

```
Main ──▶ Library ──▶ BookRepository ──▶ Book
   │         │
   │         └──▶ User ──▶ Book
   │
   └──▶ Scanner (System.in)

LibraryMessages ◀── used by Library and User for output
```

---

## 5. How the Project Works

1. The program starts in `Main.main()`.
2. A menu is shown to the user.
3. The user types a number from `1` to `6`.
4. Based on the choice, the matching action runs:
   - **Add Book** → asks for id, title, author and stores it.
   - **View Books** → prints every book.
   - **Search Book** → asks for a title and prints the match.
   - **Borrow Book** → asks for an id, checks availability and duplicates.
   - **Return Book** → asks for an id, checks if the user actually borrowed it.
   - **Exit** → quits the loop.
5. Validation messages are printed by `LibraryMessages` (never by raw logic), so messages stay consistent.

---

## 6. Execution / Setup Steps

### Prerequisites

- **Java JDK 17+** installed (any modern JDK works; `switch` expressions need 14+).
- A terminal (PowerShell, CMD, or any shell).

### Compile and Run

From the project root:

```powershell
cd src
javac *.java
java Main
```

Or compile into the `bin/` folder:

```powershell
javac -d bin src/*.java
java -cp bin Main
```

---

## 7. Example Outputs

### Starting the Program

```
===== Library Menu =====
1. Add Book
2. View Books
3. Search Book
4. Borrow Book
5. Return Book
6. Exit
Enter your choice: 1
Enter book ID: 101
Enter title: Clean Code
Enter author: Robert C. Martin
Book added: Clean Code
```

### Viewing Books

```
Enter your choice: 2
=== All Books in Library ===
Book ID: 101
Title: Clean Code
Author: Robert C. Martin
Available: Yes
----------------------------
```

### Borrowing a Book

```
Enter your choice: 4
Enter book ID to borrow: 101
Guest borrowed: Clean Code
```

### Trying to Borrow the Same Book Again

```
Enter your choice: 4
Enter book ID to borrow: 101
Guest has already borrowed "Clean Code".
```

### Returning a Book

```
Enter your choice: 5
Enter book ID to return: 101
Guest returned: Clean Code
```

### Exiting

```
Enter your choice: 6
Exiting... Goodbye!
```

---

## 8. Technologies Used

| Technology | Purpose |
|------------|---------|
| Java (JDK 17+) | Core language |
| `java.util.ArrayList` | Storing books in memory |
| `java.util.Optional` | Safer lookups (avoid `null`) |
| `java.util.Scanner` | Reading console input |
| Object-Oriented Programming | Class-based design |

---

## 9. Future Improvements

- 🔐 Add **multiple users** with login support.
- 💾 Save books and borrow history to a **file or database**.
- 📅 Track **due dates** and overdue books.
- 🧪 Add **unit tests** with JUnit.
- 🖼️ Build a **GUI version** using JavaFX or Swing.
- 🌐 Expose features through a REST API using Spring Boot.
- 📊 Add reports (most borrowed books, active users, etc.).

---

Happy coding! 🚀
