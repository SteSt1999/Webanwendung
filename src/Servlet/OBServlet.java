package Servlet;

import Logik.GeldBewegung;
import Logik.Sessionsteuerung.*;
import Logik.Verwaltung.Bank;
import Logik.Verwaltung.Kunde;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/OBServlet")
public class OBServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Login
        if (request.getParameter("Login") != null) {
            HttpSession session = request.getSession();
            Kunde kunde = null;
            try {
                kunde = new Kunde(request.getParameter("LogInID"), request.getParameter("LogInPasswort"),
                        ((Bank) session.getAttribute("bank")));
            } catch (IllegalArgumentException e) {
                request.getRequestDispatcher("OnlineBanking/OBLoginFehlgeschlagen.jsp").forward(request, response);
            }
            session.setAttribute("kunde", kunde);

            request.getRequestDispatcher("OnlineBanking/OBAuswahl.jsp").forward(request, response);
        } else if (request.getParameter("LoginFehlgeschlagenZurueck") != null) {
            request.getRequestDispatcher("OnlineBanking/OBLogin.jsp").forward(request, response);
        } else if (request.getParameter("Abmelden") != null) {
            MainServlet.ausloggen(request);
            request.getRequestDispatcher("OnlineBanking/OBLogin.jsp").forward(request, response);
        }


        /*
            Methode die zur Fehlerseite leitet, falls man nicht mehr eingeloggt ist
         */
        if (request.getSession().getAttribute("kunde") == null) {
            request.getRequestDispatcher("OnlineBanking/OBAusgeloggt.jsp").forward(request, response);
        }
        /*


        ALLE NACHFOLGENDEN METHODEN können nur aufgerufen werden, wenn man eingeloggt ist


         */


        // Überweisung
        else if (request.getParameter("Ueberweisung") != null) {
            request.getRequestDispatcher("OnlineBanking/OBUeberweisung.jsp").forward(request, response);
        } else if (request.getParameter("Ueberweisen") != null) {
            HttpSession session = request.getSession();
            Kunde kunde = (Kunde) session.getAttribute("kunde");
            try {
                GeldBewegung.ueberweisen(kunde, new ZugangOnlineBanking(), request.getParameter("Empfaenger"),
                        request.getParameter("EmpfaengerBank"), request.getParameter("Betrag"));
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
}