package Entity;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isRented;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isRented = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    @Override
    public String toString() {
        return "Book{ title='" + title + "', author='" + author + "', isbn='" + isbn + "', isRented=" + isRented + "}";
    }
}

