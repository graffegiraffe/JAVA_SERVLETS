package by.rublevskaya.servlet;

import by.rublevskaya.log.CustomLogger;
import by.rublevskaya.service.PropsHandler;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet("/goals")
public class MyGoalsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        String goalsFilePath = PropsHandler.getPropertyFromConfig("GOALS_FILE_PATH");

        try {
            String content = new String(Files.readAllBytes(Paths.get(goalsFilePath)));
            response.getWriter().write(content);
            CustomLogger.info("Sent information page of goals (/goals)");
        } catch (IOException e) {
            CustomLogger.error("Error when issuing a page page (/goals)", e);
            response.getWriter().write("<h1>Error loading the target page</h1>");
        }
    }
}
