package servlets;

import auth.AuthenticationHelper;
import data.sessionInformation;
import db.sessionFetcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "loginauth")

public class SafeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!AuthenticationHelper.isAuthenticated(request)) {
            request.getSession().setAttribute("loginerror_message", "Please login to access that page");
            response.sendRedirect("/index.jsp");
            return;
        }

        sessionFetcher sessionFetcher = new sessionFetcher();
        sessionInformation sessionInfo = sessionFetcher.getSessionInfo(AuthenticationHelper.getSession(request).getName());
        if(sessionInfo.getStatus() == 0) {
            String comments = request.getParameter("comment");
            sessionFetcher.storeCommentAndLeftHome(comments, AuthenticationHelper.getSession(request).getName(), sessionInfo.getStartDateTime(), sessionInfo.getEndDateTime());
            request.getSession().setAttribute("message_message", "Thanks for letting us know you finished your sit!");
            response.sendRedirect("/message.jsp");
        } else if(sessionInfo.getStatus() == 1) {
            sessionFetcher.storeSafePlace(AuthenticationHelper.getSession(request).getName(), sessionInfo.getStartDateTime(), sessionInfo.getEndDateTime());
            request.getSession().setAttribute("message_message", "Thanks for letting us know you got back safely! :)");
            response.sendRedirect("/message.jsp");
        } else response.sendRedirect("/waiting.jsp");
    }
}
