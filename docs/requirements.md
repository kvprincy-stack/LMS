# 📋 Project Requirements

This document describes **what the Library Management System must do**, the **environment it runs in**, and the **boundaries** of the project.

---

## 1. Functional Requirements

These are the features the system **must provide**.

| ID | Requirement | Description |
|----|-------------|-------------|
| FR-1 | Add Book | The system shall allow adding a book with id, title, and author. |
| FR-2 | View Books | The system shall display all books currently stored. |
| FR-3 | Search Book | The system shall search a book by its title (case-insensitive). |
| FR-4 | Borrow Book | A user shall borrow an available book using its id. |
| FR-5 | Return Book | A user shall return a book they previously borrowed. |
| FR-6 | Availability Update | A book's availability shall update automatically on borrow/return. |
| FR-7 | Prevent Duplicate Borrow | The same user cannot borrow the same book twice. |
| FR-8 | Validation Messages | The system shall display clear messages for success and failure. |
| FR-9 | Exit | The user shall be able to exit the application cleanly. |

---

## 2. Non-Functional Requirements

These describe **quality attributes** of the system.

| ID | Category | Requirement |
|----|----------|-------------|
| NFR-1 | Usability | Menu must be clear and numbered (1–6). |
| NFR-2 | Reliability | Invalid input must not crash the program. |
| NFR-3 | Performance | Operations must respond in under 1 second for up to 10,000 books. |
| NFR-4 | Maintainability | Code must follow clean code and SOLID principles. |
| NFR-5 | Portability | Must run on any OS that supports Java (Windows, Linux, macOS). |
| NFR-6 | Readability | Code must use meaningful names and consistent formatting. |
| NFR-7 | Extensibility | New features (e.g., file storage) should be easy to add. |

---

## 3. System Requirements

### Minimum Hardware

| Component | Minimum |
|-----------|---------|
| Processor | Dual-core 1 GHz |
| RAM | 1 GB |
| Disk Space | 100 MB |
| Display | Any console/terminal |

### Operating System

- Windows 10 or later
- macOS 10.13+
- Any modern Linux distribution

---

## 4. Software Dependencies

| Dependency | Version | Purpose |
|------------|---------|---------|
| Java Development Kit (JDK) | 17 or higher | Compile and run the Java code |
| Terminal / Shell | Any | Run console commands |
| Text Editor / IDE | VS Code, IntelliJ, Eclipse (any) | Edit source files |

> 💡 The project uses only the **standard Java library** — no external dependencies.

---

## 5. User Roles

The current version supports a **single role**:

| Role | Description | Permissions |
|------|-------------|-------------|
| Guest User | The default user (`id=1`, name `"Guest"`) | Add, view, search, borrow, return books |

> 🔮 In a future version: **Librarian** and **Member** roles can be introduced.

---

## 6. Use Cases

### UC-1: Add a Book

- **Actor:** Guest User
- **Steps:**
  1. User selects option `1`.
  2. User enters id, title, author.
  3. System adds the book and confirms.

### UC-2: View All Books

- **Actor:** Guest User
- **Steps:**
  1. User selects option `2`.
  2. System lists every book with its availability.

### UC-3: Search Book by Title

- **Actor:** Guest User
- **Steps:**
  1. User selects option `3`.
  2. User enters the title.
  3. System prints the matching book or "not found".

### UC-4: Borrow a Book

- **Actor:** Guest User
- **Preconditions:** Book exists and is available.
- **Steps:**
  1. User selects option `4`.
  2. User enters the book id.
  3. System marks the book as borrowed and records it under the user.

### UC-5: Return a Book

- **Actor:** Guest User
- **Preconditions:** The user has previously borrowed the book.
- **Steps:**
  1. User selects option `5`.
  2. User enters the book id.
  3. System marks the book as available and removes it from the user's list.

### UC-6: Exit

- **Actor:** Guest User
- **Steps:**
  1. User selects option `6`.
  2. System prints a goodbye message and terminates.

---

## 7. Project Scope

### In Scope

- Console-based interaction.
- In-memory storage using `ArrayList`.
- Single default user.
- Basic borrow/return logic with validation.
- Clear, beginner-friendly code organization.

### Out of Scope

- Graphical user interface (GUI).
- Database or file persistence.
- Authentication and multiple roles.
- Networking or web interface.
- Reporting / analytics.

---

## 8. Assumptions and Limitations

### Assumptions

- The user runs the program from a terminal that supports basic text I/O.
- The user enters numeric values where numbers are requested.
- Java 17 or higher is installed.
- Only one user uses the system at a time.

### Limitations

- ❌ Data is **not persisted** — closing the app loses all books.
- ❌ Only **one user** is supported per session.
- ❌ No **search by author or id**, only by title.
- ❌ No **due dates**, fines, or overdue tracking.
- ❌ The system is **single-threaded** and not designed for concurrent access.
