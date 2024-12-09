package exceptions;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(value = {"", "/matches", "/match-score", "/new-match"})
public class ServletFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        try {
            filterChain.doFilter(request, servletResponse);
        } catch (HibernateException | NotFoundException | InvalidParametersException |
                 UtilityFilesLoadingException message) {
            request.setAttribute("message", message.getMessage());
            request.getRequestDispatcher("exception.jsp").forward(request, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
