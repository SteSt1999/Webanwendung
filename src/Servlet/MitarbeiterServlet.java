package Servlet;

import Datenbank.DBATM;
import Datenbank.DBUser;
import Logik.Sessionsteuerung.*;
import Logik.Verwaltung.Kunde;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/MitarbeiterServlet")
public class MitarbeiterServlet extends HttpServlet {
    private static SessionMitarbeiter session;
    private static Kunde userLogauswahl;
    private static String ATMLogauswahl;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Login
        if (request.getParameter("Login") != null) {
            try {
                session = new SessionMitarbeiter(request.getParameter("LogInID"), request.getParameter("LogInPasswort"), new ZugangMitarbeiter());
            } catch (IllegalArgumentException e) {
                request.getRequestDispatcher("Mitarbeiter/MALoginFehlgeschlagen.jsp").forward(request, response);;
            }
            request.getRequestDispatcher("Mitarbeiter/MAAuswahl.jsp").forward(request, response);
        } else if (request.getParameter("LoginFehlgeschlagenZurueck") != null) {
            request.getRequestDispatcher("Mitarbeiter/MALogin.jsp").forward(request, response);
        }


        /*
            Methode die zur Fehlerseite leitet, falls man nicht mehr eingeloggt ist
         */
        if (session == null) {
            request.getRequestDispatcher("Mitarbeiter/MAAusgeloggt.jsp").forward(request, response);
        }
        /*


        ALLE NACHFOLGENDEN METHODEN können nur aufgerufen werden, wenn man eingeloggt ist


         */


        //AllLos
        else if (request.getParameter("AllLogs") != null) {
            request.getRequestDispatcher("Mitarbeiter/MAAllLogs.jsp").forward(request, response);
        }

        //ATMLogs
        else if (request.getParameter("ATMLogs") != null) {
            request.getRequestDispatcher("Mitarbeiter/MAATMLogAuswahl.jsp").forward(request, response);
        } else if (request.getParameter("AnzeigenATM") != null) {
            if (DBATM.existiertATM(request.getParameter("ATM-ID"))) {
                ATMLogauswahl = request.getParameter("ATM-ID");
                request.getRequestDispatcher("Mitarbeiter/MAATMLogs.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("Mitarbeiter/MAFehler.jsp").forward(request, response);
            }
        }

        //UserLog
        else if (request.getParameter("UserLogs") != null) {
            request.getRequestDispatcher("Mitarbeiter/MAUserLogAuswahl.jsp").forward(request, response);
        } else if (request.getParameter("AnzeigenUser") != null) {
            if (DBUser.existiertKunde(request.getParameter("Empfaenger"), MainServlet.getBank().getBankID())) {
                userLogauswahl = new Kunde(request.getParameter("Empfaenger"));
                request.getRequestDispatcher("Mitarbeiter/MAUserLogs.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("Mitarbeiter/MAFehler.jsp").forward(request, response);
            }
        }

        // Einzahlung
        else if (request.getParameter("Einzahlung") != null) {
            request.getRequestDispatcher("Mitarbeiter/MAEinzahlung.jsp").forward(request, response);
        } else if (request.getParameter("Einzahlen") != null) {
            try {
                GeldBewegung.einzahlen(new Kunde(request.getParameter("Empfaenger")), session.getZugangsweg(), request.getParameter("Betrag"));
            } catch (IllegalArgumentException e) {
                request.getRequestDispatcher("Mitarbeiter/MAFehler.jsp").forward(request, response);
            }
            request.getRequestDispatcher("Mitarbeiter/MAEinzahlungErfolgt.jsp").forward(request, response);
        }

        // Auszahlung
        else if (request.getParameter("Abhebung") != null) {
            request.getRequestDispatcher("Mitarbeiter/MAAuszahlung.jsp").forward(request, response);
        } else if (request.getParameter("Abheben") != null) {
            try {
                GeldBewegung.abheben(new Kunde(request.getParameter("Empfaenger")), session.getZugangsweg(), request.getParameter("Betrag"));
            } catch (IllegalArgumentException e) {
                request.getRequestDispatcher("Mitarbeiter/MAFehler.jsp").forward(request, response);
            }
            request.getRequestDispatcher("Mitarbeiter/MAAuszahlungErfolgt.jsp").forward(request, response);
        }

        // Sonstiges
        else if (request.getParameter("Hauptmenu") != null) {
            request.getRequestDispatcher("Mitarbeiter/MAAuswahl.jsp").forward(request, response);
        } else if (request.getParameter("Abmelden") != null) {
            session = null;
            request.getRequestDispatcher("Mitarbeiter/MALogin.jsp").forward(request, response);
        }
    }


    public static String getAllLogs() {
        return ZugangMitarbeiter.ausgabeBankLog();
    }

    public static String getUserLogs() {
        return ZugangMitarbeiter.ausgabeUserLog(userLogauswahl);
    }

    public static String getATMLogs() {
        return ZugangMitarbeiter.ausgabeATMLog(ATMLogauswahl);
    }
}