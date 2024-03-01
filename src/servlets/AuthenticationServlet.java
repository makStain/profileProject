package servlets;

import db.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Users;

import java.io.IOException;

@WebServlet (value = "/authentication")
public class AuthenticationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String redirect = "/login?emailError";
        Users user = DBManager.getUser(email);

        if (user != null){
            redirect = "/login?passwordError";
            if(user.getPassword().equals(password)){
                req.getSession().setAttribute("CURRENT_USER", user);
                redirect="/";
            }
        }
        resp.sendRedirect(redirect);
    }
}
