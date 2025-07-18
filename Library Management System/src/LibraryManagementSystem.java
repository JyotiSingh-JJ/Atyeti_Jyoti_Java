import Services.*;
import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Library Management System");
        System.out.println("Enter 1 for Admin Login or 2 for Student Login:");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            AdminService adminService = new AdminService();
            adminService.adminMenu(scanner);
        } else if (choice == 2) {
            StudentService studentService = new StudentService();
            studentService.studentMenu(scanner);
        } else {
            System.out.println("Invalid choice. Exiting...");
        }
        scanner.close();
    }
}