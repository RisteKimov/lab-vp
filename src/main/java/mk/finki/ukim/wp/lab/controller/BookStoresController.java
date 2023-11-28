package mk.finki.ukim.wp.lab.controller;

import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.model.BookStore;
import mk.finki.ukim.wp.lab.service.BookStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookStoresController {
    private final BookStoreService bookStoreService;

    public BookStoresController(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    @GetMapping("/bookStores")
    public String getAllBookStores(Model m)
    {
        List<BookStore> bookStores = bookStoreService.findAll();
        m.addAttribute("bookStores", bookStores);
        return "bookStores";
    }

    @PostMapping("/bookStores")
    public String postAllBooksInStore(@RequestParam(name="bookStoreId") Long bookStoreID, Model m)
    {
        BookStore bookStore = bookStoreService.findStore(bookStoreID);
        List<Book> books = bookStore.getBooks();

        m.addAttribute("bookStore", bookStore);
        m.addAttribute("books", books);
        return "/allBooksInStore";
    }
}
