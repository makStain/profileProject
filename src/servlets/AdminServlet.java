package servlets;

import db.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Users;

import java.io.IOException;

@WebServlet (value = "/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("re_password");
        String fullName = req.getParameter("full_name");
        String role = req.getParameter("role_id");
        req.setCharacterEncoding("utf8");

        String redirect = "/register?passwordError";
        if (password.equals(rePassword)){
            redirect="/register?emailError";
            Users user = DBManager.getUser(email);
            if (user == null){
                if (role.equals("admin")) {
                    role = "1";
                } else {
                    role = "2";
                }
                Users newUser = new Users(null, email, password, fullName, role);
                if(DBManager.addUser(newUser)) {
                    redirect = "/register?success";
                }
            }
        }
        resp.sendRedirect(redirect);
    }
}
