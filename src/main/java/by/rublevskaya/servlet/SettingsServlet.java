package by.rublevskaya.servlet;

import by.rublevskaya.log.CustomLogger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/settings")
public class SettingsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        CustomLogger.info("Obtaining configuration parameters in /settings");
        String appName = getServletContext().getInitParameter("appName");
        String appVersion = getServletContext().getInitParameter("appVersion");
        String developerName = getServletContext().getInitParameter("developerName");
        String supportEmail = getServletContext().getInitParameter("supportEmail");
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<html><head><title>Settings</title></head><body>");
            out.println("<h1>App settings</h1>");
            out.println("<ul>");
            out.println("<li><strong>Application name:</strong> " + appName + "</li>");
            out.println("<li><strong>Version:</strong> " + appVersion + "</li>");
            out.println("<li><strong>Developer:</strong> " + developerName + "</li>");
            out.println("<li><strong>Email of support:</strong> " + supportEmail + "</li>");
            out.println("</ul>");
            out.println("</body></html>");
        }
    }
}
