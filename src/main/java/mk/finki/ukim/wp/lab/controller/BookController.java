package mk.finki.ukim.wp.lab.controller;

import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.service.BookService;
import mk.finki.ukim.wp.lab.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Controller
public class BookController {

    private final SpringTemplateEngine springTemplateEngine;
    private final BookService bookService;
    private final BookStoreService bookStoreService;


    @Autowired
    public BookController(SpringTemplateEngine springTemplateEngine, BookService bookService, BookStoreService bookStoreService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookService = bookService;
        this.bookStoreService = bookStoreService;
    }
    @GetMapping("/books")
    public String getBooksPage(@RequestParam(required = false) String error, Model model)
    {
        if ("success".equals(error)) {
            model.addAttribute("successMessage", "Book successfully added!");
        }
        model.addAttribute("bookStores", bookStoreService.findAll());
        return "listBooks";
    }
    @GetMapping("/books/add")
    public String addBooks(Model model)
    {
        model.addAttribute("bookStores", bookStoreService.findAll());
        return "addBook";
    }

    @PostMapping("/books/add")
    public String saveBook(
            @RequestParam String title,
            @RequestParam String isbn,
            @RequestParam String genre,
            @RequestParam int year,
            @RequestParam Long bookStoreId, RedirectAttributes redirectAttributes) {

        Book b = new Book(isbn, title,genre, year);
        bookService.addBook(b);
        bookStoreService.addBookToStore(b, bookStoreId);
        redirectAttributes.addAttribute("error", "success");

        return "redirect:/books";
    }

    @GetMapping("/books/edit/{bookId}")
    public String getEditBookPage(@PathVariable Long bookId, Model model) {
        // Your logic to retrieve the book for editing goes here

        model.addAttribute("bookStores", bookStoreService.findAll());
        Book book = bookStoreService.findBookInStore(bookId);
        if(book != null){
        model.addAttribute("book", book);
        return "editBook";
        }
        return "redirect:/books";
    }
    @PostMapping("/books/edit/{bookId}")
    public String editBook(@PathVariable String bookId,
            @RequestParam(name="bId") Long bID,
            @RequestParam String title,
            @RequestParam String isbn,
            @RequestParam String genre,
            @RequestParam int year,
            @RequestParam Long bookStoreId, RedirectAttributes redirectAttributes) {


        Book b = bookStoreService.findBookInStore(bID);
        bookService.editBook(b, title, isbn, genre, year);
        bookStoreService.addBookToStore(b, bookStoreId);
        redirectAttributes.addAttribute("error", "success");

        return "redirect:/books";
    }

    @GetMapping("/books/delete/{bookId}")
    public String getDeleteBookPage(@PathVariable Long bookId, Model model) {

        //Book b = bookStoreService.findBookInStore(bookId);
        bookService.deleteBook(bookId);
        bookStoreService.deleteBook(bookId);
        model.addAttribute("bookStores", bookStoreService.findAll());
        return "redirect:/books";
    }
    }

