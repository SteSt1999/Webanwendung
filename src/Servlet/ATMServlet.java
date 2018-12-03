package Servlet;

import Datenbank.DBUser;
import Logik.GeldBewegung;
import Logik.Sessionsteuerung.ZugangATM;
import Logik.Sessionsteuerung.Zugangsweg;
import Logik.Verwaltung.Kunde;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ATMServlet")
public class ATMServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Login
        if (request.getParameter("Login") != null) {
            String loginID = request.getParameter("LogInID");
            if(!DBUser.checkPasswortKunde(loginID, request.getParameter("LogInPasswort"))) {
                request.getRequestDispatcher("ATM/ATMLoginFehlgeschlagen.jsp").forward(request, response);
            }
            Zugangsweg zugangsweg = null;
            try {
                zugangsweg = new ZugangATM(request.getParameter("ATM-ID"));
            } catch (IllegalArgumentException e) {
                request.getRequestDispatcher("ATM/ATMLoginFehlgeschlagen.jsp").forward(request, response);
            }

            HttpSession session = request.getSession();
            //TODO in Session direkt den Kunden und Zugangsweg speichern
            session.setAttribute("kunde", loginID);
            session.setAttribute("zugangsweg", zugangsweg.getdbBezeichnung());

            request.getRequestDispatcher("ATM/ATMAuswahl.jsp").forward(request, response);
        } else if (request.getParameter("LoginFehlgeschlagenZurueck") != null) {
            request.getRequestDispatcher("ATM/ATMLogin.jsp").forward(request, response);
        } else if (request.getParameter("Abmelden") != null) {
            request.getSession().invalidate();
            request.getRequestDispatcher("ATM/ATMLogin.jsp").forward(request, response);
        }


        /*
            Methode die zur Fehlerseite leitet, falls man nicht mehr eingeloggt ist
         */
        //TODO
        /*
        if (session == null) {
            request.getRequestDispatcher("ATM/ATMAusgeloggt.jsp").forward(request, response);
        }*/
        /*


        ALLE NACHFOLGENDEN METHODEN können nur aufgerufen werden, wenn man eingeloggt ist


         */


        // Überweisung
        else if (request.getParameter("Ueberweisung") != null) {
            request.getRequestDispatcher("ATM/ATMUeberweisung.jsp").forward(request, response);
        } else if (request.getParameter("Ueberweisen") != null) {
            HttpSession session = request.getSession();
            Kunde kunde = new Kunde((String) session.getAttribute("kunde"), (String) session.getAttribute("bank"));
            Zugangsweg zugangsweg = new ZugangATM((String) session.getAttribute("zugangsweg"));
            try {
                GeldBewegung.ueberweisen(kunde, zugangsweg, request.getParameter("Empfaenger"),
                        request.getParameter("EmpfaengerBank"), request.getParameter("Betrag"));
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
            HttpSession session = request.getSession();
            Kunde kunde = new Kunde((String) session.getAttribute("kunde"), (String) session.getAttribute("bank"));
            Zugangsweg zugangsweg = new ZugangATM((String) session.getAttribute("zugangsweg"));
            try {
                GeldBewegung.einzahlen(kunde, zugangsweg, request.getParameter("Betrag"));
            } catch (NumberFormatException e) {
                request.getRequestDispatcher("ATM/ATMFehler.jsp").forward(request, response);
            }
            request.getRequestDispatcher("ATM/ATMEinzahlungErfolgt.jsp").forward(request, response);
        }

        // Auszahlung
        else if (request.getParameter("Auszahlung") != null) {
            request.getRequestDispatcher("ATM/ATMAuszahlung.jsp").forward(request, response);
        } else if (request.getParameter("Auszahlen") != null) {
            HttpSession session = request.getSession();
            Kunde kunde = new Kunde((String) session.getAttribute("kunde"), (String) session.getAttribute("bank"));
            Zugangsweg zugangsweg = new ZugangATM((String) session.getAttribute("zugangsweg"));
            try {
                GeldBewegung.abheben(kunde, zugangsweg, request.getParameter("Betrag"));
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
}