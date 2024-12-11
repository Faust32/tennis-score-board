package servlets;

import controller.MatchScoreController;
import controller.NewMatchController;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    private NewMatchController newMatchController;

    private MatchScoreController matchScoreController;

    @Override
    public void init() {
        ServletContext servletContext = getServletContext();
        matchScoreController = (MatchScoreController) servletContext.getAttribute("matchScoreController");
        newMatchController = (NewMatchController) servletContext.getAttribute("newMatchController");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        matchScoreController.setAttributes(request);
        request.getRequestDispatcher("/match-score.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        matchScoreController.handle(request);
        UUID matchId = newMatchController.getCurrentMatchId();
        response.sendRedirect(request.getContextPath() + "/match-score?uuid=" + matchId);
    }

}
