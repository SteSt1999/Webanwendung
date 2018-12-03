package Servlet;

import Datenbank.DBUser;
import Logik.GeldBewegung;
import Logik.Sessionsteuerung.*;
import Logik.Verwaltung.ATM;
import Logik.Verwaltung.Bank;
import Logik.Verwaltung.Kunde;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/MAServlet")
public class MAServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Login
        if (request.getParameter("Login") != null) {
            if(!DBUser.checkPasswortMitarbeiter(request.getParameter("LogInID"), request.getParameter("LogInPasswort"),
                    (Bank) request.getSession().getAttribute("bank"))) {
                request.getRequestDispatcher("Mitarbeiter/MALoginFehlgeschlagen.jsp").forward(request, response);
            }
            HttpSession session = request.getSession();
            session.setAttribute("mitarbeiter", true);

            request.getRequestDispatcher("Mitarbeiter/MAAuswahl.jsp").forward(request, response);
        } else if (request.getParameter("LoginFehlgeschlagenZurueck") != null) {
            request.getRequestDispatcher("Mitarbeiter/MALogin.jsp").forward(request, response);
        } else if (request.getParameter("Abmelden") != null) {
            MainServlet.ausloggen(request);
            request.getRequestDispatcher("Mitarbeiter/MALogin.jsp").forward(request, response);
        }


        /*
            Methode die zur Fehlerseite leitet, falls man nicht mehr eingeloggt ist
         */
        if (request.getSession().getAttribute("mitarbeiter") == null) {
            request.getRequestDispatcher("Mitarbeiter/MAAusgeloggt.jsp").forward(request, response);
        }
        /*


        ALLE NACHFOLGENDEN METHODEN können nur aufgerufen werden, wenn man eingeloggt ist


         */


        //AllLos
        else if (request.getParameter("AllLogs") != null) {
            request.getRequestDispatcher("Mitarbeiter/MALogAll.jsp").forward(request, response);
        }

        //ATMLogs
        else if (request.getParameter("ATMLogs") != null) {
            request.getRequestDispatcher("Mitarbeiter/MALogATMAuswahl.jsp").forward(request, response);
        } else if (request.getParameter("AnzeigenATM") != null) {
            ATM atm = null;
            try {
                atm = new ATM(request.getParameter("ATM-ID"), (Bank) request.getSession().getAttribute("bank"));
            } catch (IllegalArgumentException e) {
                request.getRequestDispatcher("Mitarbeiter/MAFehler.jsp").forward(request, response);
            }
            HttpSession session = request.getSession();
            session.setAttribute("atmAuswahl", atm);
            request.getRequestDispatcher("Mitarbeiter/MALogATM.jsp").forward(request, response);
        }

        //KundenLog
        else if (request.getParameter("UserLogs") != null) {
            request.getRequestDispatcher("Mitarbeiter/MALogKundenAuswahl.jsp").forward(request, response);
        } else if (request.getParameter("AnzeigenUser") != null) {
            Kunde kunde = null;
            try {
                kunde = new Kunde(request.getParameter("Kunde"), (Bank) request.getSession().getAttribute("bank"));
            } catch (IllegalArgumentException e) {
                request.getRequestDispatcher("Mitarbeiter/MAFehler.jsp").forward(request, response);
            }
            HttpSession session = request.getSession();
            session.setAttribute("userAuswahl", kunde);
            request.getRequestDispatcher("Mitarbeiter/MALogKunden.jsp").forward(request, response);
        }

        // Einzahlung
        else if (request.getParameter("Einzahlung") != null) {
            request.getRequestDispatcher("Mitarbeiter/MAEinzahlung.jsp").forward(request, response);
        } else if (request.getParameter("Einzahlen") != null) {
            try {
                GeldBewegung.einzahlen(new Kunde(request.getParameter("Empfaenger"),
                                (Bank) request.getSession().getAttribute("bank")),
                        new ZugangMitarbeiter(), request.getParameter("Betrag"));
            } catch (IllegalArgumentException e) {
                request.getRequestDispatcher("Mitarbeiter/MAFehler.jsp").forward(request, response);
            }
            request.getRequestDispatcher("Mitarbeiter/MAEinzahlungErfolgt.jsp").forward(request, response);
        }

        // Auszahlung
        else if (request.getParameter("Auszahlung") != null) {
            request.getRequestDispatcher("Mitarbeiter/MAAuszahlung.jsp").forward(request, response);
        } else if (request.getParameter("Abheben") != null) {
            try {
                GeldBewegung.abheben(new Kunde(request.getParameter("Empfaenger"),
                                (Bank) request.getSession().getAttribute("bank")),
                        new ZugangMitarbeiter(), request.getParameter("Betrag"));
            } catch (IllegalArgumentException e) {
                request.getRequestDispatcher("Mitarbeiter/MAFehler.jsp").forward(request, response);
            }
            request.getRequestDispatcher("Mitarbeiter/MAAuszahlungErfolgt.jsp").forward(request, response);
        }

        // Kunden Erstellen
        else if (request.getParameter("KundenErstellung") != null) {
            request.getRequestDispatcher("Mitarbeiter/MAKundenErstellung.jsp").forward(request, response);
        } else if (request.getParameter("KundenErstellen") != null) {
            try {
                Kunde.erstelleKunden(request.getParameter("Vorname"), request.getParameter("Nachname"),
                        request.getParameter("Passwort"), request.getParameter("PasswortWiederholung"),
                        (Bank) request.getSession().getAttribute("bank"));
            } catch (IllegalArgumentException e) {
                request.getRequestDispatcher("Mitarbeiter/MAFehler.jsp").forward(request, response);
            }
            request.getRequestDispatcher("Mitarbeiter/MAKundeErstellt.jsp").forward(request, response);
        }

        // Sonstiges
        else if (request.getParameter("Hauptmenu") != null) {
            request.getRequestDispatcher("Mitarbeiter/MAAuswahl.jsp").forward(request, response);
        }
    }
}