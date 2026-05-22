# 🧑‍💻 Coding Practices Guide

A beginner-friendly guide describing the **coding standards** used in this Library Management System project. Examples are taken directly from the project's classes (`Book`, `Library`, `User`, etc.).

---

## 1. Naming Conventions

Good names make code self-explanatory. We follow standard Java conventions.

| Element | Convention | Example |
|---------|------------|---------|
| Class | `PascalCase` | `Book`, `BookRepository`, `LibraryMessages` |
| Method | `camelCase` (verb) | `addBook()`, `searchBookByTitle()` |
| Variable | `camelCase` (noun) | `bookRepository`, `userName` |
| Constant | `UPPER_SNAKE_CASE` | `MAX_BOOKS`, `DEFAULT_USER_ID` |
| Boolean | starts with `is/has` | `isAvailable`, `hasBorrowed` |
| Package | all lowercase | `com.library.model` |

✅ **Good**
```java
public Optional<Book> searchBookByTitle(String title) { ... }
```

❌ **Bad**
```java
public Optional<Book> SBT(String s) { ... }   // unclear and ugly
```

---

## 2. Class Design Guidelines

- One class = **one responsibility** (Single Responsibility Principle).
- Keep fields **private**; expose them via getters/setters when needed.
- Prefer **constructor injection** to wire dependencies.
- Use `final` for fields that should not change after construction.

### Example from the project

```java
public class Library {
    private final BookRepository bookRepository;   // injected

    public Library() {
        this(new BookRepository());
    }

    public Library(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
```

`Library` does **not** know how books are stored — it just talks to a `BookRepository`. This makes it easy to swap storage later (e.g., file or database).

---

## 3. Method Writing Best Practices

| Rule | Why |
|------|-----|
| A method should do **one thing** | Easier to read and test |
| Keep methods **short** (≈ 5–20 lines) | Less mental overhead |
| Use **descriptive names** | The name should describe the action |
| Use **guard clauses** to exit early | Reduces nesting |
| Return `Optional` instead of `null` | Avoids `NullPointerException` |

✅ **Good — guard clauses**
```java
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
    return true;
}
```

❌ **Bad — deeply nested**
```java
public boolean returnBook(Book book) {
    if (book != null) {
        if (borrowedBooks.contains(book)) {
            book.setAvailable(true);
            borrowedBooks.remove(book);
            return true;
        }
    }
    return false;
}
```

---

## 4. Exception Handling Rules

- Catch only **specific** exceptions you can handle.
- Never use empty `catch` blocks.
- Validate user input at **input boundaries** (e.g., `Scanner` reads).
- Use return values for expected failures, exceptions for unexpected ones.

### Example from `Main`

```java
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
```

This makes sure the program **never crashes** when the user types text instead of a number.

---

## 5. Code Formatting Standards

| Topic | Standard |
|-------|----------|
| Indentation | 4 spaces (no tabs) |
| Line length | ≤ 120 characters |
| Braces | Opening brace on same line |
| Imports | No wildcard imports (`import java.util.*;`) |
| Blank lines | Use to separate logical blocks |
| One statement per line | Easier to read and debug |

✅ **Example**
```java
public void displayAllBooks() {
    if (bookRepository.isEmpty()) {
        LibraryMessages.libraryEmpty();
        return;
    }
    LibraryMessages.libraryHeader();
    for (Book book : bookRepository.findAll()) {
        book.diplayBookInfo();
        LibraryMessages.bookSeparator();
    }
}
```

---

## 6. Commenting Guidelines

- Comments should explain **why**, not **what** (the code already shows "what").
- Use **Javadoc** (`/** ... */`) for public classes and methods.
- Avoid comments that repeat the code.
- Delete commented-out code — use Git history instead.

✅ **Good**
```java
/**
 * Centralizes all user-facing console messages so that
 * Library logic stays free of formatting concerns.
 */
public final class LibraryMessages { ... }
```

❌ **Bad**
```java
// increase i by 1
i++;
```

---

## 7. SOLID Principles Used

| Principle | How we applied it |
|-----------|-------------------|
| **S** — Single Responsibility | `Book` holds data, `BookRepository` stores, `Library` coordinates, `LibraryMessages` prints. Each class has one job. |
| **O** — Open/Closed | `Library` uses a `BookRepository`; you can extend storage without changing `Library`. |
| **L** — Liskov Substitution | A subclass of `BookRepository` (e.g., `FileBookRepository`) can replace the in-memory one without breaking `Library`. |
| **I** — Interface Segregation | Methods are small and focused. (Future: extract a `Repository` interface to follow this fully.) |
| **D** — Dependency Inversion | `Library` depends on the abstraction of "a repository", injected through its constructor, instead of creating it internally. |

---

## 8. Clean Code Principles

| Principle | Example in project |
|-----------|--------------------|
| Meaningful names | `searchBookByTitle`, `markAsBorrowed` |
| Small functions | Each method in `User` is under ~15 lines |
| No magic numbers | Menu options handled with clear `case` labels |
| Avoid duplication | Borrow/return logic lives in one place (`User`) |
| Fail fast | Guard clauses return early on invalid input |
| Prefer immutability | `final` fields like `borrowedBooks` and `bookRepository` |
| Return `Optional` over `null` | `findById`, `findByTitle`, `searchBookByTitle` |

---

## 9. Git Commit Best Practices

A clean commit history helps everyone understand the project's evolution.

### Commit Message Format

```
<type>: <short summary>

<optional longer description>
```

### Common Types

| Type | When to use |
|------|-------------|
| `feat` | A new feature |
| `fix` | A bug fix |
| `refactor` | Code improvement without changing behavior |
| `docs` | Documentation changes |
| `style` | Formatting only (no logic change) |
| `test` | Adding or updating tests |
| `chore` | Build, config, or tooling updates |

### Examples

✅ **Good**
```
feat: add Library class with add/borrow/return operations
fix: prevent duplicate borrow by the same user
refactor: extract LibraryMessages to centralize console output
docs: add README and requirements
```

❌ **Bad**
```
update
fix stuff
asdf
final version (really)
```

### Other Tips

- Commit **small, focused changes** — one logical change per commit.
- Always write a clear summary in **present tense** (e.g., "add", not "added").
- Never commit secrets, passwords, or large binaries.
- Pull and rebase before pushing to keep history clean.
- Use **branches** for new features: `feature/borrow-book`, `fix/return-validation`.

---

Happy clean coding! ✨
