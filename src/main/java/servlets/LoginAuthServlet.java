package servlets;

import auth.AuthenticationHelper;
import auth.AuthenticationResult;
import auth.AuthenticationResultState;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "loginauth")

public class LoginAuthServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(AuthenticationHelper.isAuthenticated(request)) {
            response.sendRedirect("/sits.jsp");
            return;
        }
        String username = request.getParameter("username");
        if(username != null) username = username.trim();

        String password = request.getParameter("password");

        if(username == null || password == null || username.isEmpty()) {
            redirectError("Please enter login details", request, response);
            return;
        }

        try {
            AuthenticationResult result = AuthenticationHelper.authenticate(username, password);
            if(result.getState() == AuthenticationResultState.SUCCESS) {
                response.sendRedirect("/sits.jsp");
            } else {
                redirectError("Unknown account or invalid details", request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectError("Unknown error. Please contact an admin", request, response);
        }
    }

    private void redirectError(String error, HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("loginerror_message", error);
        String link = "/index.jsp";

        response.getWriter().println("<html><body><p>If you are not redirected click <a href='" + link + "'>this link</a></body></html>");
        response.sendRedirect(link);
    }
}
