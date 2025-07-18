package Services;
import java.util.*;
import Entity.*;

public class AdminService {
    private static final List<Book> books = StudentService.books;
    private static final Map<String, Student> students = StudentService.students;

    public void adminMenu(Scanner scanner) {
        while (true) {
            System.out.println("1. Add a Book\n2. Block a Student\n3. View Books\n4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    blockStudent(scanner);
                    break;
                case 3:
                    viewBooks();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void addBook(Scanner scanner) {
        System.out.println("Enter book title:");
        String title = scanner.nextLine();
        System.out.println("Enter book author:");
        String author = scanner.nextLine();
        System.out.println("Enter book ISBN:");
        String isbn = scanner.nextLine();

        books.add(new Book(title, author, isbn));

        System.out.println("Book added successfully.");
    }

    // enhance this piece of code below
    private void blockStudent(Scanner scanner) {
        System.out.println("Enter the Student ID to block:");
        String studentId = scanner.nextLine();

        if (students.containsKey(studentId)) {
            students.remove(studentId);
            System.out.println("Student blocked successfully.");
        } else {
            System.out.println("Student not found.");
            blockStudent(scanner);
        }
    }

    private void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("Books in library:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }
}
