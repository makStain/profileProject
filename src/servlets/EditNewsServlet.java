package servlets;

import db.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.News;
import model.NewsCategories;
import model.Users;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet (value = "/editNews")
public class EditNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = 0L;

        Users usersLogin = (Users) req.getSession().getAttribute("CURRENT_USER");

        if (usersLogin != null && usersLogin.getRoleID().equals("1")) {

            try {
                id = Long.parseLong(req.getParameter("id"));
            }
            catch (Exception e) {
            }

            News news = DBManager.getNewsById(id);

            if (news != null){
                req.setAttribute("news", news);
                ArrayList<NewsCategories> categories = DBManager.getNewsCategories();
                req.setAttribute("categories", categories);
                req.getRequestDispatcher("/editNews.jsp").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("/404.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        Long category = Long.parseLong(req.getParameter("category"));

        req.setCharacterEncoding("utf8");

        String redirect = "/editUser?id=" + id + "&error";

        News news= DBManager.getNewsById(id);
        NewsCategories newsCategories = DBManager.getCategoryById(category);

        if (category != null) {
            if (news != null) {

                news.setTitle(title);
                news.setContent(content);
                news.setCategoryID(newsCategories.getId());

                if (DBManager.editNews(news)) {
                    redirect = "/editNews?id=" + id + "&success";
                } else {
                    redirect = "/editNews?id=" + id + "&editError";
                }
            }
        }
        resp.sendRedirect(redirect);
    }
}
