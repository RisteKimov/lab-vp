package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.model.BookStore;

import java.util.List;

public interface BookStoreService {
    public List<BookStore> findAll();
    public void addBookToStore(Book b, long IDbs);
    public Book findBookInStore(long bookId);
    public Book deleteBook(long Id);
    public BookStore findStore(long id);
}
