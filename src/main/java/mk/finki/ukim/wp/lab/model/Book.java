package mk.finki.ukim.wp.lab.model;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private final Long id;
    private String isbn;
    private String title;
    private String genre;
    private int year;
    private List<Author> authors;
    private BookStore bookStore;

    public Book(String isbn, String title, String genre, int year, List<Author> authors) {
        this.id = (long) (Math.random() * 1000);
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = authors;
        bookStore = null;
    }

    public Book(String isbn, String title, String genre, int year) {
        this.id = (long) (Math.random() * 1000);
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        authors = new ArrayList<>();
        bookStore = null;
    }

    public void setBookStore(BookStore bookStore) {
        this.bookStore = bookStore;
    }

    public BookStore getBookStore() {
        return bookStore;
    }

    public boolean matchingisbn(String isbn)
    {
        return this.isbn.equals(isbn);
    }
    public void addAuthor(Author a)
    {
        authors.add(a);
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public Long getId() {
        return id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public boolean matchingID(Long id)
    {
        return this.id.equals(id);
    }
}

