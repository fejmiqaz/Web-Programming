package mk.finki.ukim.wp.lab.web.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import mk.finki.ukim.wp.lab.model.Book;

import java.util.ArrayList;

@WebListener
public class ApplicationContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce){
        ServletContextListener.super.contextInitialized(sce);
        sce.getServletContext().setAttribute("lastBooks", new ArrayList<String>());
    }
    public void contextDestroyed(ServletContextEvent sce){
        ServletContextListener.super.contextDestroyed(sce);
        sce.getServletContext().removeAttribute("lastBooks");
    }
}
