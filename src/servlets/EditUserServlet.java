package servlets;

import db.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Users;

import java.io.IOException;

@WebServlet (value = "/editUser")
public class EditUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = 0L;
        Users usersLogin = (Users) req.getSession().getAttribute("CURRENT_USER");
        if (usersLogin!=null) {

            try {
                id = Long.parseLong(req.getParameter("id"));
            }
            catch (Exception e) {
            }
            Users users = DBManager.getUserID(id);

            if (users != null) {
                req.setAttribute("user", users);
                req.getRequestDispatcher("/editUser.jsp").forward(req, resp);
            } else{
                req.getRequestDispatcher("/404.jsp").forward(req, resp);
            }
        }else {
            req.getRequestDispatcher("/404.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        req.setCharacterEncoding("utf8");

        String redirect = "/editUser?id=" + id + "&error";
        Users user = DBManager.getUserID(id);
        if (user != null){

            user.setFullName(name);
            user.setPassword(password);

            if (DBManager.editUser(user)) {
                changeDataInSession(user, req);
                redirect="/editUser?id=" + id + "&success";
            } else {
                redirect="/editUser?id=" + id + "&editError";
            }
        }
        resp.sendRedirect(redirect);
    }

    private void changeDataInSession(Users editUsers, HttpServletRequest req){
        Users userLogin = (Users) req.getSession().getAttribute("CURRENT_USER");
        userLogin.setEmail(editUsers.getEmail());
        userLogin.setPassword(editUsers.getPassword());
        userLogin.setFullName(editUsers.getFullName());
    }
}
