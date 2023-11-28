package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.model.BookStore;

import java.util.ArrayList;
import java.util.List;

public class BookStoreRepository {
    private List<BookStore> bookStores;
    private BookRepository bookRepository;


    public BookStoreRepository() {
        bookStores = new ArrayList<BookStore>();
        bookRepository = new BookRepository();
        List<Book> books = bookRepository.findAll();

        // BookStore initialization
        BookStore literatura = new BookStore("Literatura MK", "Skopje", "XCVJ+J58, Македониjа, Скопје 100");
        BookStore kultura = new BookStore("Kultura MK", "Skopje", "XCVJ+J58, Илинденска, Скопје 100");
        BookStore akademska = new BookStore("Академска Книга", "Skopje", "Porta Bunjakovec, Булевар Партизански Одреди 23, Скопје 1000");
        BookStore sakamknigi = new BookStore("Сакам Книги", "Skopje", "Биста на Васил Главинов, 17, Скопје 1000");
        BookStore kupikniga = new BookStore("Купи книга", "Skopje", "Булевар Партизански Одреди 102/2-1, Скопје 1000");

        // Adding bookstores to the repository
        bookStores.add(literatura);
        bookStores.add(kultura);
        bookStores.add(akademska);
        bookStores.add(sakamknigi);
        bookStores.add(kupikniga);

        // Adding books to bookstores
        bookStores.get(0).addBookToStore(books.get(0));
        books.get(0).setBookStore(bookStores.get(0));

        bookStores.get(1).addBookToStore(books.get(1));
        books.get(1).setBookStore(bookStores.get(1));

        bookStores.get(2).addBookToStore(books.get(2));
        books.get(2).setBookStore(bookStores.get(2));

        bookStores.get(3).addBookToStore(books.get(3));
        books.get(3).setBookStore(bookStores.get(3));

        bookStores.get(4).addBookToStore(books.get(4));
        books.get(4).setBookStore(bookStores.get(4));
    }

    public void addBookStores(BookStore bookStore) {
        bookStores.add(bookStore);
    }

    public List<BookStore> findall() {
        return bookStores;
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public Book deleteBook(long id)
    {
        Book b = null;
        for(BookStore bs : bookStores)
        {
            b = bs.deleteBook(id);
            if(b != null) {
                return b;
            }
        }
        return null;
    }
}
