package servlets;

import controller.NewMatchController;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {
    private NewMatchController newMatchController;

    @Override
    public void init() {
        ServletContext servletContext = getServletContext();
        newMatchController = (NewMatchController) servletContext.getAttribute("newMatchController");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("new-match.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        newMatchController.handle(request);
        UUID matchId = newMatchController.getCurrentMatchId();
        String redirectURL = request.getContextPath() + "/match-score?uuid=" + matchId;
        response.sendRedirect(redirectURL);
    }
}
