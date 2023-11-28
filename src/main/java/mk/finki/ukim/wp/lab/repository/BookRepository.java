package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Author;
import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.model.BookStore;
import mk.finki.ukim.wp.lab.service.BookStoreService;
import mk.finki.ukim.wp.lab.service.impl.BookStoreServiceImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class BookRepository  {
    private List<Book> books;
    private AuthorRepository authorRepository;


    public BookRepository() {
        authorRepository = new AuthorRepository();
        List<Author> authors = authorRepository.findAll();
        books = new ArrayList<Book>();
        books.add(new Book("HP12345678", "Harry Potter and The Philosopher Stone", "Philosophy", 1991
                , authors.stream().filter(a -> a.matchingID(1000)).collect(Collectors.toList())));
        books.add(new Book("HM84231321", "Hamlet", "Romance, Tragedy", 1603,
                authors.stream().filter(a -> a.matchingID(1001)).collect(Collectors.toList())));
        books.add(new Book("HND24134122", "The Hunchback of Notre-Dame", "Romance, Drama", 1831,
                authors.stream().filter(a -> a.matchingID(1002)).collect(Collectors.toList())));
        books.add(new Book("CP563621", "Crime and Punishment", "Tragedy, Drama", 1866,
                authors.stream().filter(a -> a.matchingID(1003)).collect(Collectors.toList())));
        books.add(new Book("BC2412145", "Beloto Cigance", "Drama", 1969,
                authors.stream().filter(a -> a.matchingID(1004)).collect(Collectors.toList())));

    }
    public List<Book> findAll()
    {
        return books;
    }
    public Book findByIsbn(String isbn)
    {
        return books.stream().filter(book -> book.matchingisbn(isbn)).findFirst().orElse(null);
    }
    public Author addAuthorToBook(Author author, Book book) {
        book.addAuthor(author);
        return author;
    }
    public Book findBookByID(Long id)
    {
        return books.stream().filter(b -> b.matchingID(id)).findFirst().orElse(null);
    }
    public Book deleteBook(Long id)
    {
        for(Book b : books)
        {
            if(Objects.equals(b.getId(), id)) {
                books.remove(b);
                return b;
            }
        }
        return null;
    }
}

