package by.rublevskaya.servlet;

import by.rublevskaya.log.CustomLogger;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/count")
public class VisitCounterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Integer visitCount = (Integer) session.getAttribute("visitCount");

        if (visitCount == null) {
            visitCount = 0;
        }

        visitCount++;
        session.setAttribute("visitCount", visitCount);
        CustomLogger.info("The page /Count was visited " + visitCount + " size");
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<html><head><title>Counter of visits</title></head><body>");
            out.println("<h1>Counter of visits</h1>");
            out.println("<p>The number of visits to the page: " + visitCount + "</p>");
            out.println("</body></html>");
        }
    }
}
