package Servlet;

import Datenbank.DBPasswort;

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
        // Login
        if (request.getParameter("Login") != null) {
            if (DBPasswort.checkPasswortKunde(request.getParameter("LogInID"), request.getParameter("LogInPasswort"))) {
                //TODO generate Session
                //new Session( getUserAusDB("logInID"), new ATM_Zugang(getIDAusJSP))
                request.getRequestDispatcher("ATM/ATMAuswahl.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("ATM/ATMLoginFehlgeschlagen.jsp").forward(request, response);
            }
        } else if (request.getParameter("LoginFehlgeschlagenZurueck") != null) {
            request.getRequestDispatcher("ATM/ATMLogin.jsp").forward(request, response);
        }

        // Überweisung
        else if (request.getParameter("Ueberweisung") != null) {
            request.getRequestDispatcher("ATM/ATMUeberweisung.jsp").forward(request, response);
        } else if (request.getParameter("Ueberweisen") != null) {
            request.getRequestDispatcher("ATM/ATMUeberweisungErfolgt.jsp").forward(request, response);
            //TODO Überweisung
            //doATMüberweisen(Session session, User kunde, long betrag)
        }

        // Transactions
        else if (request.getParameter("Transactions") != null) {
            request.getRequestDispatcher("ATM/ATMTransactions.jsp").forward(request, response);
        }

        // Einzahlung
        else if (request.getParameter("Einzahlung") != null) {
            request.getRequestDispatcher("ATM/ATMEinzahlung.jsp").forward(request, response);
        } else if (request.getParameter("Einzahlen") != null) {
            request.getRequestDispatcher("ATM/ATMEinzahlungErfolgt.jsp").forward(request, response);
            //TODO Einzahlung
            //doATMeinzahlen(Session session, long betrag)

        }

        // Auszahlung
        else if (request.getParameter("Auszahlung") != null) {
            request.getRequestDispatcher("ATM/ATMAuszahlung.jsp").forward(request, response);
        } else if (request.getParameter("Auszahlen") != null) {
            request.getRequestDispatcher("ATM/ATMAuszahlungErfolgt.jsp").forward(request, response);
            //TODO Auszahlung
            //doATMabheben(Session session, long betrag)
        }

        // Sonstiges
        else if (request.getParameter("Hauptmenu") != null) {
            request.getRequestDispatcher("ATM/ATMAuswahl.jsp").forward(request, response);
        } else if (request.getParameter("Abmelden") != null) {
            //TODO destroy Session
            request.getRequestDispatcher("ATM/ATMLogin.jsp").forward(request, response);
        }
    }
}