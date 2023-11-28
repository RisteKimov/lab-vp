package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Author;
import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.repository.AuthorRepository;
import mk.finki.ukim.wp.lab.repository.BookRepository;
import mk.finki.ukim.wp.lab.service.AuthorService;
import mk.finki.ukim.wp.lab.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    private final AuthorRepository authorRepository;
    private final AuthorService authorService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Author addAuthorToBook(Long authorId, String isbn) {
        Book book =  bookRepository.findAll().stream().filter(b -> b.matchingisbn(isbn)).findFirst().orElse(null);
        if(book == null)
            return null;
        Author author = authorRepository.findAll().stream().filter(b -> b.matchingID(authorId)).findFirst().orElse(null);
        if(author == null)
            return null;
        book.addAuthor(author);
        return author;
    }
    public void addMoreAuthorsToBook(List<String> idString, String bookIsbn)
    {
        List<Long> ids = new ArrayList<Long>();
        for(String s: idString)
            ids.add(Long.parseLong(s));
        Book b = listBooks().stream().filter(f -> f.matchingisbn(bookIsbn)).findFirst().orElse(null);
        List<Author> authors = authorService.listAuthors();
        List<Author> selectedAuthors = findAllAuthors(authors, ids);
        for(Author a : selectedAuthors)
        {
            b.addAuthor(a);
        }
    }
    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public Book findBookById(Long id){ return bookRepository.findBookByID(id);}
    public List<Author> findAllAuthors(List<Author> ats, List<Long> ids)
    {
        List<Author> temp = new ArrayList<Author>();
        for(Author a : ats)
        {
            for(Long id : ids)
                if(a.matchingID(id))
                    temp.add(a);
        }
        return temp;
    }
    public void addBook(Book b)
    {
        bookRepository.findAll().add(b);
    }

    @Override
    public void editBook(Book b, String title, String isbn, String genre, int year) {
        //Book b = this.findBookById(id);
            b.setTitle(title);
            b.setIsbn(isbn);
            b.setGenre(genre);
            b.setYear(year);
    }

    public Book deleteBook(long Id)
    {
        return bookRepository.deleteBook(Id);
    }
    }



