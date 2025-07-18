package Entity;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private List<Book> rentedBooks = new ArrayList<>();

    public Student(String name, String id, double wallet) {
        super(name, id);
    }

    public void rentBook(Book book) {
        rentedBooks.add(book);
    }

    public void returnBook(Book book) {
        rentedBooks.remove(book);
    }

    public List<Book> getRentedBooks() {
        return rentedBooks;
    }
}