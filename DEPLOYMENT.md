# Library Management System - Deployment Guide

This document provides step-by-step instructions to compile, package, and run the Library Management System Java console application.

---

## Prerequisites
- **Java Development Kit (JDK) 8 or higher**
  - Download from: https://adoptopenjdk.net/ or https://www.oracle.com/java/technologies/downloads/
- **Command-line access** (e.g., PowerShell, Command Prompt, or Terminal)

---

## Compilation Steps
1. **Navigate to the project root directory:**
   ```sh
   cd C:/MyWorkSpace
   ```
2. **Compile all Java source files:**
   ```sh
   javac -d bin src/model/Book.java src/model/User.java src/service/BookRepository.java src/service/Library.java src/service/ReservationService.java src/utility/LibraryMessages.java src/app/Main.java
   ```
   - This command compiles all source files and places the `.class` files in the `bin/` directory, preserving the package structure.

---

## JAR Creation
1. **Ensure the manifest file exists and is correct:**
   - The manifest file should be at `META-INF/MANIFEST.MF` and contain:
     ```
     Manifest-Version: 1.0
     Main-Class: app.Main
     ```
2. **Create the JAR file:**
   ```sh
   jar cfm LibraryManagementSystem.jar META-INF/MANIFEST.MF -C bin .
   ```
   - This packages all compiled classes and the manifest into `LibraryManagementSystem.jar`.

---

## Execution Steps
1. **Run the application:**
   ```sh
   java -jar LibraryManagementSystem.jar
   ```
   - The console menu should appear if everything is set up correctly.

---

## Troubleshooting
- **Error: `no main manifest attribute, in LibraryManagementSystem.jar`**
  - Ensure the manifest file is named `MANIFEST.MF`, is located in the `META-INF/` directory, and contains the correct `Main-Class` entry.
  - Rebuild the JAR using the correct manifest file.
- **Error: `package ... does not exist` during compilation**
  - Make sure all source files are present and the package structure is correct.
  - Use the full list of source files in the `javac` command.
- **ClassNotFoundException or NoClassDefFoundError**
  - Ensure all `.class` files are included in the JAR and the package structure is preserved.
- **Java not recognized**
  - Ensure the JDK is installed and the `java` and `javac` commands are available in your system's PATH.

---

For further assistance, consult the project documentation or contact the maintainer.
