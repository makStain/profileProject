package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.News;
import model.NewsCategories;
import model.Users;
import db.DBManager;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet (value = "/addNews")
public class AddNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Users currentUser = (Users) req.getSession().getAttribute("CURRENT_USER");
//        if (currentUser != null) {
//            req.getRequestDispatcher("/addNews.jsp").forward(req, resp);
//        } else {
//            resp.sendRedirect("/login");
//        }
        Users currentUser = (Users) req.getSession().getAttribute("CURRENT_USER");

        if(currentUser!=null && currentUser.getRoleID().equals("1")) {
            ArrayList<NewsCategories> categories = DBManager.getNewsCategories();
            req.setAttribute("categories", categories);

            ArrayList<News> news= DBManager.getAllNews();
            req.setAttribute("news", news);
            req.getRequestDispatcher("/addnews.jsp").forward(req, resp);
        }else{
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirect = "/login";
        req.setCharacterEncoding("utf8");
        Users currentUser = (Users) req.getSession().getAttribute("CURRENT_USER");

        if (currentUser != null) {
            redirect = "/addNews?error";

            String title = req.getParameter("title");
            String content = req.getParameter("content");
            Long categoryID = Long.parseLong(req.getParameter("category"));

            News news = new News();
            news.setTitle(title);
            news.setContent(content);
            news.setCategoryID(categoryID);

            if(DBManager.addNews(news)){
                redirect = "/addNews?success";
            }
        }
        resp.sendRedirect(redirect);
    }
}
