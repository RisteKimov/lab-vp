package mk.finki.ukim.wp.lab.servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.wp.lab.model.Author;
import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.service.AuthorService;
import mk.finki.ukim.wp.lab.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookDetailsServlet", urlPatterns = {"/bookDetails"})
public class BookDetailsServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final BookService bookService;
    private final AuthorService authorService;
    @Autowired
    public BookDetailsServlet(SpringTemplateEngine springTemplateEngine, BookService bookService, AuthorService authorService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        springTemplateEngine.process(
                "/error.html",
                context,
                resp.getWriter()
        );
    }
    @Override
    protected void doPost(HttpServletRequest    req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("authorId"));
        String bookIsbn = req.getParameter("bookIsbn");
        Book b = bookService.listBooks().stream().filter(f -> f.matchingisbn(bookIsbn)).findFirst().orElse(null);
        List<Author> authors = authorService.listAuthors();
        Author a = authors.stream().filter(f -> f.matchingID(id)).findFirst().orElse(null);
        if(a != null)
            b.addAuthor(a);
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("book", b);
        context.setVariable("author", a);
        springTemplateEngine.process(
                "/bookDetails.html",
                context,
                resp.getWriter()
        );

    }
}



