package servlets;

import db.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Users;

import java.io.IOException;

@WebServlet (value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirect = "/login?emailError";
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Users user = DBManager.getUser(email);

        if (user != null){
            redirect = "/login?passwordError";
            if(user.getPassword().equals(password)){
                redirect = "/profile";
                req.getSession().setAttribute("CURRENT_USER", user);
            }
        }
        resp.sendRedirect(redirect);
    }
}
