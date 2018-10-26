package Servlet;

import Datenbank.DBATM;
import Datenbank.DBUser;
import Logik.Sessionsteuerung.ATMZugang;
import Logik.Sessionsteuerung.GeldBewegung;
import Logik.Sessionsteuerung.Session;
import Logik.Verwaltung.Konto;
import Logik.Verwaltung.Kunde;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static Datenbank.DBKontostand.kontostandLesen;

@WebServlet("/ATMServlet")
public class ATMServlet extends HttpServlet {
    private static Session session;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Login
        if (request.getParameter("Login") != null) {
            if (DBUser.checkPasswortKunde(request.getParameter("LogInID"), request.getParameter("LogInPasswort")) && DBATM.existiertATM(request.getParameter("ATM-ID"))) {
                Kunde kunde = new Kunde(new Konto(kontostandLesen(request.getParameter("LogInID"))), request.getParameter("LogInID"));
                session = new Session(kunde, new ATMZugang(Integer.parseInt(request.getParameter("ATM-ID"))));
                request.getRequestDispatcher("ATM/ATMAuswahl.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("ATM/ATMLoginFehlgeschlagen.jsp").forward(request, response);
            }
        } else if (request.getParameter("LoginFehlgeschlagenZurueck") != null) {
            request.getRequestDispatcher("ATM/ATMLogin.jsp").forward(request, response);
        } else if (request.getParameter("Abmelden") != null) {
            session = null;
            request.getRequestDispatcher("ATM/ATMLogin.jsp").forward(request, response);
        }


        /*
            Methode die zur Fehlerseite leitet, falls man nicht mehr eingeloggt ist
         */
        if (session == null) {
            request.getRequestDispatcher("ATM/ATMAusgeloggt.jsp").forward(request, response);
        }
        /*


        ALLE NACHFOLGENDEN METHODEN können nur aufgerufen werden, wenn man eingeloggt ist


         */


        // Überweisung
        else if (request.getParameter("Ueberweisung") != null) {
            request.getRequestDispatcher("ATM/ATMUeberweisung.jsp").forward(request, response);
        } else if (request.getParameter("Ueberweisen") != null) {
            try {
                GeldBewegung.ueberweisen((Kunde) session.getUser(), session.getZugangsweg(), request.getParameter("Empfaenger"), request.getParameter("EmpfaengerBank"), request.getParameter("Summe"));
            } catch (IllegalArgumentException e) {
                request.getRequestDispatcher("ATM/ATMFehler.jsp").forward(request, response);
            }
            request.getRequestDispatcher("ATM/ATMUeberweisungErfolgt.jsp").forward(request, response);
        }

        // Transaktionen
        else if (request.getParameter("Transactions") != null) {
            request.getRequestDispatcher("ATM/ATMTransactions.jsp").forward(request, response);
        }

        // Einzahlung
        else if (request.getParameter("Einzahlung") != null) {
            request.getRequestDispatcher("ATM/ATMEinzahlung.jsp").forward(request, response);
        } else if (request.getParameter("Einzahlen") != null) {
            try {
                GeldBewegung.einzahlen((Kunde) session.getUser(), session.getZugangsweg(), request.getParameter("Betrag"));
            } catch (NumberFormatException e) {
                request.getRequestDispatcher("ATM/ATMFehler.jsp").forward(request, response);
            }
            request.getRequestDispatcher("ATM/ATMEinzahlungErfolgt.jsp").forward(request, response);
        }

        // Auszahlung
        else if (request.getParameter("Auszahlung") != null) {
            request.getRequestDispatcher("ATM/ATMAuszahlung.jsp").forward(request, response);
        } else if (request.getParameter("Auszahlen") != null) {
            try {
                GeldBewegung.abheben((Kunde) session.getUser(), session.getZugangsweg() ,request.getParameter("Betrag"));
            } catch (NumberFormatException e) {
                request.getRequestDispatcher("ATM/ATMFehler.jsp").forward(request, response);
            }
            request.getRequestDispatcher("ATM/ATMAuszahlungErfolgt.jsp").forward(request, response);
        }

        // Sonstiges
        else if (request.getParameter("Hauptmenu") != null) {
            request.getRequestDispatcher("ATM/ATMAuswahl.jsp").forward(request, response);
        }
    }

    public static double getKontostandInEuro() {
        return session.getKontostand() / 100.;
    }

    public static String getKontoLog() {
        return session.getKontoLog();
    }
}