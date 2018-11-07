package Servlet;

import Logik.GeldBewegung;
import Logik.Sessionsteuerung.Log;
import Logik.Sessionsteuerung.SessionKunde;
import Logik.Sessionsteuerung.ZugangATM;
import Logik.Sessionsteuerung.Zugangsweg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ATMServlet")
public class ATMServlet extends HttpServlet {
    private static SessionKunde session;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Login
        if (request.getParameter("Login") != null) {
            try {
                Zugangsweg zugangsweg = new ZugangATM(request.getParameter("ATM-ID"));
                session = new SessionKunde(request.getParameter("LogInID"), request.getParameter("LogInPasswort"), zugangsweg);
            } catch (IllegalArgumentException e) {
                request.getRequestDispatcher("ATM/ATMLoginFehlgeschlagen.jsp").forward(request, response);
            }
            request.getRequestDispatcher("ATM/ATMAuswahl.jsp").forward(request, response);
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
                GeldBewegung.ueberweisen(session.getUser(), session.getZugangsweg(),
                        request.getParameter("Empfaenger"), request.getParameter("EmpfaengerBank"), request.getParameter("Betrag"));
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
                GeldBewegung.einzahlen(session.getUser(), session.getZugangsweg(), request.getParameter("Betrag"));
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
                GeldBewegung.abheben(session.getUser(), session.getZugangsweg(), request.getParameter("Betrag"));
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

    public static long getKontostand() {
        return session.getKontostand();
    }

    public static String getKontoLog() {
        return Log.ausgabeKundenLog(session.getUser());
    }
}