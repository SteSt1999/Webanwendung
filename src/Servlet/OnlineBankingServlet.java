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
                request.getRequestDispatcher("OnlineBanking/ATMAuswahl.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("OnlineBanking/ATMLoginFehlgeschlagen.jsp").forward(request, response);
            }
        } else if (request.getParameter("LoginFehlgeschlagenZurueck") != null) {
            request.getRequestDispatcher("OnlineBanking/ATMLogin.jsp").forward(request, response);
        }

        // Überweisung
        else if (request.getParameter("Ueberweisung") != null) {
            request.getRequestDispatcher("OnlineBanking/ATMUeberweisung.jsp").forward(request, response);
        } else if (request.getParameter("Ueberweisen") != null) {
            request.getRequestDispatcher("OnlineBanking/ATMUeberweisungErfolgt.jsp").forward(request, response);
        }

        // Transactions
        else if (request.getParameter("Transactions") != null) {
            request.getRequestDispatcher("OnlineBanking/ATMTransactions.jsp").forward(request, response);
        }

        // Sonstiges
        else if (request.getParameter("Hauptmenu") != null) {
            request.getRequestDispatcher("OnlineBanking/ATMAuswahl.jsp").forward(request, response);
        } else if (request.getParameter("Abmelden") != null) {
            request.getRequestDispatcher("OnlineBanking/ATMLogin.jsp").forward(request, response);
        }
    }
}