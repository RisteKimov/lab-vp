package mk.finki.ukim.wp.lab.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.wp.lab.model.Author;
import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.service.AuthorService;
import mk.finki.ukim.wp.lab.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AuthorController {
    private final SpringTemplateEngine springTemplateEngine;
    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public AuthorController(SpringTemplateEngine springTemplateEngine, BookService bookService, AuthorService authorService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/author")
    public String getAuthorRequest()
    {
        return "/error.html";
    }

    @PostMapping("/author")
    public String postAuthorRequest(@RequestParam("bookIsbn") String bookIsbn, Model m)
    {
        Book b = bookService.findBookByIsbn(bookIsbn);
        List<Author> authors = authorService.listAuthors();
        List<Author> bookAuthors = b.getAuthors();
        if(bookAuthors != null){
        List<Author> otherAuthors = returnDistinctAuthors(bookAuthors, authors);
            m.addAttribute("authors", otherAuthors); }
        else
        {
            m.addAttribute("authors", authors);
        }
        m.addAttribute("book", b);
               return  "authorList";
    }
    public List<Author> returnDistinctAuthors(List<Author> bookAuthors, List<Author> repositoryAuthors) {
        List<Author> otherAuthors = repositoryAuthors.stream()
                .filter(repositoryAuthor ->
                        bookAuthors.stream().noneMatch(bookAuthor ->
                                repositoryAuthor.fullname().equals(bookAuthor.fullname())
                        )
                )
                .collect(Collectors.toList());
        return otherAuthors;
    }
}
