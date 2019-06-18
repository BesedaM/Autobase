package by.epam.javatraining.beseda.webproject.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

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
//            request.getRequestDispatcher("/WEB-INF/views/customer.jsp")
//                    .forward(request, response);
//        }
//    }
//
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        request .getRequestDispatcher("/WEB-INF/views/customer.jsp")
//                .forward(request, response);
//
//    }
}


