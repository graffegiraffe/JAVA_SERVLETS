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

@WebServlet("/about")
public class AboutMeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        String aboutFilePath = PropsHandler.getPropertyFromConfig("ABOUT_FILE_PATH");
        try {
            String content = new String(Files.readAllBytes(Paths.get(aboutFilePath)));
            response.getWriter().write(content);
            CustomLogger.info("Page information was issued(/about)");
        } catch (IOException e) {
            CustomLogger.error("Error when giving a page about yourself", e);
            response.getWriter().write("<h1>Page loading error</h1>");
        }
    }
}
