package Servlet;

import Datenbank.DBATM;
import Datenbank.DBUser;
import Logik.Sessionsteuerung.ATM_Zugang;
import Logik.Sessionsteuerung.Session;
import Logik.Verwaltung.Konto;
import Logik.Verwaltung.Kunde;
import Logik.Verwaltung.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static Datenbank.DBKontostand.kontostandLesen;


@WebServlet("/ATMServlet")
public class ATMServlet extends HttpServlet {
    private Session session;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Login
        if (request.getParameter("Login") != null) {
            if (DBUser.checkPasswortKunde(request.getParameter("LogInID"), request.getParameter("LogInPasswort")) && DBATM.existiertATM(request.getParameter("ATM-ID"))) {
                Kunde kunde = new Kunde(new Konto(kontostandLesen(request.getParameter("LogInID"))), request.getParameter("LogInID"));
                this.session = new Session(kunde, new ATM_Zugang(Integer.parseInt(request.getParameter("ATM-ID"))));
                request.getRequestDispatcher("ATM/Auswahl.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("ATM/LoginFehlgeschlagen.jsp").forward(request, response);
            }
        } else if (request.getParameter("LoginFehlgeschlagenZurueck") != null) {
            request.getRequestDispatcher("ATM/Login.jsp").forward(request, response);
        }

        // Überweisung
        else if (request.getParameter("Ueberweisung") != null) {
            request.getRequestDispatcher("ATM/Ueberweisung.jsp").forward(request, response);
        } else if (request.getParameter("Ueberweisen") != null) {
            User user = new Kunde(new Konto(kontostandLesen(request.getParameter("Empfaenger"))), request.getParameter("Empfaenger"));
            long betrag = Long.parseLong(request.getParameter("Summe"));
            ATM_Zugang.doATMüberweisen(session, user, betrag);
            request.getRequestDispatcher("ATM/UeberweisungErfolgt.jsp").forward(request, response);

        }

        // Transactions
        else if (request.getParameter("Transactions") != null) {
            request.getRequestDispatcher("ATM/Transactions.jsp").forward(request, response);
        }

        // Einzahlung
        else if (request.getParameter("Einzahlung") != null) {
            request.getRequestDispatcher("ATM/Einzahlung.jsp").forward(request, response);
        } else if (request.getParameter("Einzahlen") != null) {
            ATM_Zugang.doATMeinzahlen(session, Long.parseLong(request.getParameter("Betrag")));
            request.getRequestDispatcher("ATM/EinzahlungErfolgt.jsp").forward(request, response);
        }

        // Auszahlung
        else if (request.getParameter("Auszahlung") != null) {
            request.getRequestDispatcher("ATM/Auszahlung.jsp").forward(request, response);
        } else if (request.getParameter("Auszahlen") != null) {
            ATM_Zugang.doATMabheben(session, Long.parseLong(request.getParameter("Betrag")));
            request.getRequestDispatcher("ATM/AuszahlungErfolgt.jsp").forward(request, response);
        }

        // Sonstiges
        else if (request.getParameter("Hauptmenu") != null) {
            request.getRequestDispatcher("ATM/Auswahl.jsp").forward(request, response);
        } else if (request.getParameter("Abmelden") != null) {
            session = null;
            request.getRequestDispatcher("ATM/Login.jsp").forward(request, response);
        }
    }
}