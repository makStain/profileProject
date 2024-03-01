package servlets;

import db.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Comment;
import model.News;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet (value = "/newsDetails")
public class NewsDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        News news = DBManager.getNewsById(id);
        req.setAttribute("news", news);
        if (news != null){
            ArrayList<Comment> comments = DBManager.getAllComments(news.getId());
            req.setAttribute("comments", comments);
        }
        req.getRequestDispatcher("/newsDetails.jsp").forward(req, resp);
    }
}
