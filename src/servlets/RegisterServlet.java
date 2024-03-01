package servlets;

import db.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Users;

import java.io.IOException;

@WebServlet (value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirect = "/register?emailError";
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("re_password");
        String fullName = req.getParameter("full_name");

        Users checkUser = DBManager.getUser(email);
        if (checkUser == null) {
            redirect = "/register?passworderror";
            if (password.equals(rePassword)){
                Users user = new Users();
                user.setEmail(email);
                user.setPassword(password);
                user.setFullName(fullName);

                if(DBManager.addUser(user)){
                    redirect = "/register?success";
                }
            }
        }
        resp.sendRedirect(redirect);
    }
}
