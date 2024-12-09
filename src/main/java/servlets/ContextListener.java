package servlets;

import controller.MatchScoreController;
import controller.MatchesController;
import controller.NewMatchController;
import exceptions.HibernateException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.io.*;
import java.util.stream.Collectors;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        NewMatchController newMatchController = new NewMatchController();
        sce.getServletContext().setAttribute("newMatchController", newMatchController);
        MatchScoreController matchScoreController = new MatchScoreController();
        sce.getServletContext().setAttribute("matchScoreController", matchScoreController);
        MatchesController matchesController = new MatchesController();
        sce.getServletContext().setAttribute("matchesController", matchesController);
        try {
            initializeDatabase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeDatabase() throws IOException {
        try (InputStream inputStream = HibernateUtil.class.getClassLoader().getResourceAsStream("init.sql");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String sqlQuery = reader.lines().collect(Collectors.joining("\n"));
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                session.beginTransaction();
                session.createNativeMutationQuery(sqlQuery).executeUpdate();
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            throw new HibernateException("Could not execute SQL init script.");
        }
    }
}

