package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Author;
import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.repository.BookRepository;

import java.util.List;

public interface BookService{
    List<Book> listBooks();
    Author addAuthorToBook(Long authorId, String isbn);
    Book findBookByIsbn(String isbn);
    public void addMoreAuthorsToBook(List<String> idString, String bookIsbn);
    public BookRepository getBookRepository();
    public void addBook(Book b);
    public void editBook(Book b, String title, String isbn, String genre, int year);
    public Book findBookById(Long id);
    public Book deleteBook(long Id);
}
