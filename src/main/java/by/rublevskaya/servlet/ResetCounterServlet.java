package by.rublevskaya.servlet;

import by.rublevskaya.log.CustomLogger;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/reset")
public class ResetCounterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute("visitCount", 0);
        CustomLogger.info("The visitor to the visits was dropped through /reset");
        response.sendRedirect("/count");
    }
}