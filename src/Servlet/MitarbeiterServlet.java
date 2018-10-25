package Servlet;

import Datenbank.DBUser;
import Logik.Sessionsteuerung.MitarbeiterZugang;
import Logik.Sessionsteuerung.Session;
import Logik.Verwaltung.Konto;
import Logik.Verwaltung.Kunde;
import Logik.Verwaltung.Mitarbeiter;
import Logik.Verwaltung.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static Datenbank.DBKontostand.kontostandLesen;

@WebServlet("/MitarbeiterServlet")
public class MitarbeiterServlet extends HttpServlet {
    public static Session session;
    public static Kunde userLogauswahl;
    public static String ATMLogauswahl;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Login
        if (request.getParameter("Login") != null) {
            if (DBUser.checkPasswortMitarbeiter(request.getParameter("LogInID"), request.getParameter("LogInPasswort"))) {


                User mitarbeiter = new Mitarbeiter(request.getParameter("LogInID"));

                this.session = new Session(mitarbeiter, new MitarbeiterZugang());

                request.getRequestDispatcher("Mitarbeiter/MAAuswahl.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("ATM/LoginFehlgeschlagen.jsp").forward(request, response);
            }
        } else if (request.getParameter("LoginFehlgeschlagenZurueck") != null) {
            request.getRequestDispatcher("Mitarbeiter/MALogin.jsp").forward(request, response);
        }


        //AllLos
        else if (request.getParameter("AllLogs") != null) {

            request.getRequestDispatcher("Mitarbeiter/MAAllLogs.jsp").forward(request, response);

        }

        //ATMLogs
        else if (request.getParameter("ATMLogs") != null) {
            request.getRequestDispatcher("Mitarbeiter/MAATMLogAuswahl.jsp").forward(request, response);

        } else if (request.getParameter("AnzeigenATM") != null) {


            ATMLogauswahl = request.getParameter("ATM-ID");
            request.getRequestDispatcher("Mitarbeiter/MAATMLogs.jsp").forward(request, response);

        }
        //UserLog
        else if (request.getParameter("UserLogs") != null) {
            request.getRequestDispatcher("Mitarbeiter/MAUserLogAuswahl.jsp").forward(request, response);

        } else if (request.getParameter("AnzeigenUser") != null) {


            userLogauswahl = new Kunde(new Konto(kontostandLesen(request.getParameter("Empfaenger"))), request.getParameter("Empfaenger"));
            request.getRequestDispatcher("Mitarbeiter/MAUserLogs.jsp").forward(request, response);

        }


        // Einzahlung
        else if (request.getParameter("Einzahlung") != null) {
            request.getRequestDispatcher("Mitarbeiter/MAEinzahlung.jsp").forward(request, response);
        } else if (request.getParameter("Einzahlen") != null) {


            try {
                Kunde kunde = new Kunde(new Konto(kontostandLesen(request.getParameter("Empfaenger"))), request.getParameter("Empfaenger"));

                MitarbeiterZugang.doMitarbeiterEinzahlen(session, kunde, (request.getParameter("Betrag")));

            } catch (NumberFormatException e) {
                request.getRequestDispatcher("Mitarbeiter/MAFehler.jsp").forward(request, response);
            }
            request.getRequestDispatcher("Mitarbeiter/MAEinzahlungErfolgt.jsp").forward(request, response);

        }

        // Auszahlung
        else if (request.getParameter("Abhebung") != null) {
            request.getRequestDispatcher("Mitarbeiter/MAAuszahlung.jsp").forward(request, response);
        } else if (request.getParameter("Abheben") != null) {

            try {
                Kunde kunde = new Kunde(new Konto(kontostandLesen(request.getParameter("Empfaenger"))), request.getParameter("Empfaenger"));

                MitarbeiterZugang.doMitarbeiterAbheben(session, kunde, (request.getParameter("Betrag")));

            } catch (NumberFormatException e) {
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
        return MitarbeiterZugang.doAusgabeBankLog();
    }

    public static String getUserLogs() {
        return MitarbeiterZugang.doAusgabeUserLog(userLogauswahl);
    }

    public static String getATMLogs() {
        return MitarbeiterZugang.doAusgabeATMLog(ATMLogauswahl);
    }
}

