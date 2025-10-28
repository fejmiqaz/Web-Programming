package mk.finki.ukim.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.service.BookReservationService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BookReservationServlet", urlPatterns = {"/bookReservation"})
public class BookReservationServlet extends HttpServlet {

    private final BookReservationService bookReservationService;
    private final SpringTemplateEngine springTemplateEngine;

    public BookReservationServlet(BookReservationService bookReservationService, SpringTemplateEngine springTemplateEngine) {
        this.bookReservationService = bookReservationService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req,resp);
        WebContext context = new WebContext(webExchange);

        String bookTitle = req.getParameter("bookTitle");
        String readerName = req.getParameter("readerName");
        String ipAddress = req.getParameter("ipAddress");
        int numberOfCopies = Integer.parseInt(req.getParameter("numCopies"));

        context.setVariable("bookTitle", bookTitle);
        context.setVariable("readerName", readerName);
        context.setVariable("ipAddress", ipAddress);
        context.setVariable("numberOfCopies", numberOfCopies);

        springTemplateEngine.process("reservationConfirmation.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req,resp);
        WebContext context = new WebContext(webExchange);

        String bookTitle = req.getParameter("bookTitle");
        String readerName = req.getParameter("readerName");
        String readerAddress = req.getParameter("readerAddress");
        int numberOfCopies = Integer.parseInt(req.getParameter("numCopies"));
        String ipAddress = req.getRemoteAddr();

        bookReservationService.placeReservation(bookTitle, readerName, readerAddress, numberOfCopies);

        List<String> visitedBooks = (List<String>) getServletContext().getAttribute("lastBooks");

        if(visitedBooks.size() == 3){
            visitedBooks.remove(visitedBooks.size() - 1);
        }

        visitedBooks.add(0,bookTitle);

        getServletContext().setAttribute("lastBooks", visitedBooks);

        context.setVariable("bookTitle", bookTitle);
        context.setVariable("readerName", readerName);
        context.setVariable("ipAddress", ipAddress);
        context.setVariable("numCopies", numberOfCopies);

        springTemplateEngine.process("reservationConfirmation.html", context, resp.getWriter());
    }
}
