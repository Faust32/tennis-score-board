package servlets;

import controller.MatchesController;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {
    private MatchesController matchesController;

    @Override
    public void init() {
        ServletContext servletContext = getServletContext();
        matchesController = (MatchesController) servletContext.getAttribute("matchesController");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        matchesController.handle(request);
        getServletContext().getRequestDispatcher("/all-matches.jsp").forward(request, response);
    }

}
