package Servlet;

import Datenbank.DBUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/OnlineBankingServlet")
public class OnlineBankingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Login
        if (request.getParameter("Login") != null) {
            if (DBUser.checkPasswortKunde(request.getParameter("LogInID"), request.getParameter("LogInPasswort"))) {
                request.getRequestDispatcher("OnlineBanking/Auswahl.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("OnlineBanking/LoginFehlgeschlagen.jsp").forward(request, response);
            }
        } else if (request.getParameter("LoginFehlgeschlagenZurueck") != null) {
            request.getRequestDispatcher("OnlineBanking/Login.jsp").forward(request, response);
        }

        // Überweisung
        else if (request.getParameter("Ueberweisung") != null) {
            request.getRequestDispatcher("OnlineBanking/Ueberweisung.jsp").forward(request, response);
        } else if (request.getParameter("Ueberweisen") != null) {
            request.getRequestDispatcher("OnlineBanking/UeberweisungErfolgt.jsp").forward(request, response);
        }

        // Transactions
        else if (request.getParameter("Transactions") != null) {
            request.getRequestDispatcher("OnlineBanking/Transactions.jsp").forward(request, response);
        }

        // Sonstiges
        else if (request.getParameter("Hauptmenu") != null) {
            request.getRequestDispatcher("OnlineBanking/Auswahl.jsp").forward(request, response);
        } else if (request.getParameter("Abmelden") != null) {
            request.getRequestDispatcher("OnlineBanking/Login.jsp").forward(request, response);
        }
    }
}