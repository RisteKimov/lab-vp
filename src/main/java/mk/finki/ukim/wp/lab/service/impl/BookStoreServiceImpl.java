package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.model.BookStore;
import mk.finki.ukim.wp.lab.repository.BookRepository;
import mk.finki.ukim.wp.lab.repository.BookStoreRepository;
import mk.finki.ukim.wp.lab.service.BookService;
import mk.finki.ukim.wp.lab.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStoreServiceImpl implements BookStoreService {


    private final BookStoreRepository bookStoreRepository;


    public BookStoreServiceImpl() {

        this.bookStoreRepository  = new BookStoreRepository();
    }

    @Override
    public List<BookStore> findAll() {
        return bookStoreRepository.findall();
    }


    @Override
    public void addBookToStore(Book b, long IDbs) {
        List<BookStore> bookStores = bookStoreRepository.findall();
        BookStore bookStore =  bookStores.stream().filter(bs -> bs.matchingId(IDbs)).findFirst().orElse(null);
        if(findBookInStore(b.getId()) == null)
            bookStore.addBookToStore(b);
        b.setBookStore(bookStore);
    }

    @Override
    public Book findBookInStore(long bookId) {
        List<BookStore> bookStores = bookStoreRepository.findall();
        for(BookStore bs: bookStores)
        {
            for(Book b: bs.getBooks())
            {
                if(b.getId().equals(bookId))
                    return b;
            }
        }
        return null;
    }

    @Override
    public Book deleteBook(long Id) {
        return bookStoreRepository.deleteBook(Id);
    }

    @Override
    public BookStore findStore(long id) {
        return bookStoreRepository.findall().stream().filter(bs -> bs.matchingId(id)).findFirst().orElse(null);
    }
}
