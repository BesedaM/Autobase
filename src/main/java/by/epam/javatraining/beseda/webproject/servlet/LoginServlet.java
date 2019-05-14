package by.epam.javatraining.beseda.webproject.servlet;

import by.epam.javatraining.beseda.webproject.model.logic.LoginLogic;
import by.epam.javatraining.beseda.webproject.util.PasswordHash;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private Logger log = Logger.getLogger(LoginServlet.class.getSimpleName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //       request.setCharacterEncoding("UTF-8");          //change!!!!

        String login = request.getParameter("login");
        byte[] password = PasswordHash.getHash(request.getParameter("password"));
        RequestDispatcher requestDispatcher = null;

        if (LoginLogic.isValidUser(login, password)) {
            request.setAttribute("login", login);
            request.setAttribute("isValidUser", "true");
            requestDispatcher = request.getRequestDispatcher("view/welcome.jsp");
        } else {
            request.setAttribute("errorMessage", "Invalid login and password");
            requestDispatcher = request.getRequestDispatcher("view/login.jsp");
        }
        requestDispatcher.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("login", "login");
        request.setAttribute("password", "password");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/login.jsp");
        requestDispatcher.forward(request, response);

    }
}
