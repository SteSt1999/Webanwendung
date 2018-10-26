package Servlet;

import Datenbank.DBUser;
import Logik.Sessionsteuerung.GeldBewegung;
import Logik.Sessionsteuerung.OnlineBankingZugang;
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

@WebServlet("/OBServlet")
public class OBServlet extends HttpServlet {
    private static Session session;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Login
        if (request.getParameter("Login") != null) {
            if (DBUser.checkPasswortKunde(request.getParameter("LogInID"), request.getParameter("LogInPasswort"))) {
                Kunde kunde = new Kunde(new Konto(kontostandLesen(request.getParameter("LogInID"))), request.getParameter("LogInID"));
                session = new Session(kunde, new OnlineBankingZugang());
                request.getRequestDispatcher("OnlineBanking/OBAuswahl.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("OnlineBanking/OBLoginFehlgeschlagen.jsp").forward(request, response);
            }
        } else if (request.getParameter("LoginFehlgeschlagenZurueck") != null) {
            request.getRequestDispatcher("OnlineBanking/OBLogin.jsp").forward(request, response);
        } else if (request.getParameter("Abmelden") != null) {
            session = null;
            request.getRequestDispatcher("OnlineBanking/OBLogin.jsp").forward(request, response);
        }


        /*
            Methode die zur Fehlerseite leitet, falls man nicht mehr eingeloggt ist
         */
        if (session == null) {
            request.getRequestDispatcher("OnlineBanking/OBAusgeloggt.jsp").forward(request, response);
        }
        /*


        ALLE NACHFOLGENDEN METHODEN können nur aufgerufen werden, wenn man eingeloggt ist


         */


        // Überweisung
        else if (request.getParameter("Ueberweisung") != null) {
            request.getRequestDispatcher("OnlineBanking/OBUeberweisung.jsp").forward(request, response);
        } else if (request.getParameter("Ueberweisen") != null) {
            try {
                GeldBewegung.ueberweisen(session, request.getParameter("Empfaenger"), request.getParameter("EmpfaengerBank"), request.getParameter("Summe"));
            } catch (IllegalArgumentException e) {
                request.getRequestDispatcher("OnlineBanking/OBFehler.jsp").forward(request, response);
            }
            request.getRequestDispatcher("OnlineBanking/OBUeberweisungErfolgt.jsp").forward(request, response);
        }

        // Transaktionen
        else if (request.getParameter("Transactions") != null) {
            request.getRequestDispatcher("OnlineBanking/OBTransactions.jsp").forward(request, response);
        }

        // Sonstiges
        else if (request.getParameter("Hauptmenu") != null) {
            request.getRequestDispatcher("OnlineBanking/OBAuswahl.jsp").forward(request, response);
        }
    }

    public static double getKontostandInEuro() {
        return session.getKontostand() / 100.;
    }

    public static String getKontoLog() {
        return session.getKontoLog();
    }
}