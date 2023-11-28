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
import java.util.stream.Collectors;

@WebServlet(name = "CheckBoxServlet", urlPatterns = {"/authorsCheckbox"})
public class CheckBoxServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public CheckBoxServlet(SpringTemplateEngine springTemplateEngine, BookService bookService, AuthorService authorService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest    req, HttpServletResponse resp) throws ServletException, IOException {
        String bookIsbn = req.getParameter("bookIsbn");
        Book b = bookService.findBookByIsbn(bookIsbn);
        List<Author> authors = authorService.listAuthors();
        List<Author> bookAuthors = b.getAuthors();
        List<Author> otherAuthors = returnDistinctAuthors(bookAuthors, authors);
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("book", b);
        context.setVariable("authors", otherAuthors);
        springTemplateEngine.process(
                "/authorCheckBox.html",
                context,
                resp.getWriter()
        );

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



