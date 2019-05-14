package by.epam.javatraining.beseda.webproject.servlet;

import by.epam.javatraining.beseda.webproject.model.dao.entitydao.UserDAO;
import by.epam.javatraining.beseda.webproject.model.entity.user.User;
import by.epam.javatraining.beseda.webproject.util.PasswordHash;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse
//            response) throws ServletException, IOException {
//
//
//        String login = request.getParameter("newLoginName");
//        byte[] password = PasswordHash.getHash(request.getParameter("newPassword"));
//        UserDAO daoUser = new UserDAO();
////        int userRole=EnumDAO.getUserRoles(daoUser.getWrapperConnector()).get("customer");
//        User user = new User(login, password, "customer");
//        if (daoUser.add(user)) {
//                                                                                       //соединение не закрыто!!!!
////            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(
////                    request, response);
//        } else {
//            request.setAttribute("errorRegister", "User " + login + " already exists");
//            request.getRequestDispatcher("/WEB-INF/views/register.jsp")
//                    .forward(request, response);
//        }
//    }
//
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        request .getRequestDispatcher("/WEB-INF/views/register.jsp")
//                .forward(request, response);
//
//    }
}


