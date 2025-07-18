package Services;
import Entity.*;
import java.util.*;

public class StudentService {
    static final List<Book> books = new ArrayList<>();
    static final Map<String, Student> students = new HashMap<>();
    private static final Map<Student, Map<Book, Long>> rentalHistory = new HashMap<>();

    static {
        books.add(new Book("Database Management Systems", "Dr.Manisha Bharambe, Dr.A.B.Nimbalkar", "ISBN001"));
        books.add(new Book("Matrix Algebra", "M.D.Bhagat, R.S.Bhambre", "ISBN002"));
        books.add(new Book("Discrete Mathematics", "M.D.Bhagat, R.S.Bhambre", "ISBN003"));
        students.put("S1", new Student("Jyoti Singh", "S1", 0.00 ));
        students.put("S2", new Student("JJ", "S2", 0.00));
    }

    public void studentMenu(Scanner scanner) {
        System.out.println("Enter your Student ID:");
        String studentId = String.valueOf(scanner.next());
        if (!students.containsKey(studentId)) {
            System.out.println("Student not found.");
            return;
        }

        Student student = students.get(studentId);
        System.out.println("Welcome, " + student.getName());

        while (true) {
            System.out.println("1. Rent a Book\n2. Return a Book\n3. View Rented Books\n4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    rentBook(student, scanner);
                    break;
                case 2:
                    returnBook(student, scanner);
                    break;
                case 3:
                    viewRentedBooks(student);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void rentBook(Student student, Scanner scanner) {
        System.out.println("Available books:");
        for (Book book : books) {
            if (!book.isRented()) {
                System.out.println(book);
            }
        }
        System.out.println("Enter the ISBN of the book to rent:");
        String isbn = scanner.nextLine();

        for (Book book : books) {
            if (book.getIsbn().equals(isbn) && !book.isRented()) {
                book.setRented(true);
                student.rentBook(book);
                rentalHistory.putIfAbsent(student, new HashMap<>());
                rentalHistory.get(student).put(book, System.currentTimeMillis());
                System.out.println("Book rented successfully.");
                return;
            }
        }
        System.out.println("Book not available or invalid ISBN.");
    }

    private void returnBook(Student student, Scanner scanner) {
        System.out.println("Your rented books:");
        for (Book book : student.getRentedBooks()) {
            System.out.println(book);
        }
        System.out.println("Enter the ISBN of the book to return:");
        String isbn = scanner.nextLine();

        for (Book book : student.getRentedBooks()) {
            if (book.getIsbn().equals(isbn)) {
                book.setRented(false);
                student.returnBook(book);
                long rentedTime = rentalHistory.get(student).get(book);
                long daysRented = (System.currentTimeMillis() - rentedTime) / (1000 * 60 * 60 * 24);
                rentalHistory.get(student).remove(book);
                if (daysRented > 5) {
                    System.out.println("Late fee: Rs. " + (daysRented - 5) * 5);
                }
                System.out.println("Book returned successfully.");
                return;
            }
        }
        System.out.println("Invalid ISBN.");
    }

    private void viewRentedBooks(Student student) {
        if (student.getRentedBooks().isEmpty()) {
            System.out.println("No books rented.");
        } else {
            System.out.println("Rented books:");
            for (Book book : student.getRentedBooks()) {
                System.out.println(book);
            }
        }
    }
}