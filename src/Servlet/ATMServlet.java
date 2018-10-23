package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ATMServlet")
public class ATMServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("ATMLogin") != null) {
            if (Datenbank.Datenbank.checkPasswortATM(request.getParameter("LogInID"), request.getParameter("LogInPasswort"))) {
                request.getRequestDispatcher("ATM/ATMAuswahl.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("ATM/ATMLoginFehlgeschlagen.jsp").forward(request, response);
            }
        } else if (request.getParameter("ATMLoginFehlgeschlagenZurueck") != null) {
            request.getRequestDispatcher("ATM/ATMLogin.jsp").forward(request, response);
        }
    }
}