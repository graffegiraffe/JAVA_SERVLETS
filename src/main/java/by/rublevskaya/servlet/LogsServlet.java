package by.rublevskaya.servlet;

import by.rublevskaya.log.CustomLogger;
import by.rublevskaya.service.PropsHandler;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet("/logs")
public class LogsServlet extends HttpServlet {
    private static final String INFO_LOG_FILE = PropsHandler.getPropertyFromConfig("INFO_LOG_FILE");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        CustomLogger.info("Test recording Info logs from the method /logs");
        System.out.println("DEBUG: INFO_LOG_FILE = " + PropsHandler.getPropertyFromConfig("INFO_LOG_FILE"));
        System.out.println("DEBUG: ERROR_LOG_FILE = " + PropsHandler.getPropertyFromConfig("ERROR_LOG_FILE"));
        PropsHandler.printAllProperties();

        try (PrintWriter out = response.getWriter()) {
            out.println("<html><head><title>Logs</title></head><body>");
            out.println("<h1>Application logs</h1>");
            out.println("<p>The path to the log file: " + INFO_LOG_FILE + "</p>");

            try {
                if (!Files.exists(Paths.get(INFO_LOG_FILE))) {
                    CustomLogger.error("The log file was not found: " + INFO_LOG_FILE);
                    out.println("<p>The log file was not found: " + INFO_LOG_FILE + "</p>");
                    return;
                }
                try (BufferedReader reader = Files.newBufferedReader(Paths.get(INFO_LOG_FILE))) {
                    String line;
                    out.println("<pre>");
                    while ((line = reader.readLine()) != null) {
                        out.println(line);
                    }
                    out.println("</pre>");
                } catch (IOException e) {
                    CustomLogger.error("Log reading error: " + INFO_LOG_FILE, e);
                    out.println("<p>Log reading error:</p>");
                }
            } catch (Exception ex) {
                out.println("<p>It is impossible to check or read the log file</p>");
                CustomLogger.error("An error inside the servlet /logs: ", ex);
            }
            out.println("</body></html>");
        }
    }
}