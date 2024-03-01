package servlets;

import db.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Comment;
import model.News;
import model.Users;

import java.io.IOException;

@WebServlet (value = "/addComment")
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirect = "/login";
        req.setCharacterEncoding("utf8");
        Users currentUser = (Users) req.getSession().getAttribute("CURRENT_USER");

        if (currentUser != null) {
            String commentText = req.getParameter("comment");
            Long newsId= Long.parseLong(req.getParameter("news_id"));
            News news= DBManager.getNewsById(newsId);

            if (news != null){
                Comment comment = new Comment();
                comment.setComment(commentText);
                comment.setUserID(currentUser);
                comment.setNewsID(news);

                if (DBManager.addComment(comment)){
                    redirect = "/readNews?id="+news;
                }
            }
        }
        resp.sendRedirect(redirect);
    }
}
