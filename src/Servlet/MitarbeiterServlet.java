package Servlet;

import Datenbank.DBUser;
import Logik.Sessionsteuerung.Mitarbeiter_Zugang;
import Logik.Sessionsteuerung.Session;
import Logik.Verwaltung.Mitarbeiter;
import Logik.Verwaltung.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/MitarbeiterServlet")
public class MitarbeiterServlet extends HttpServlet {
    private Session session;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Login
        if (request.getParameter("Login") != null) {
            if (DBUser.checkPasswortMitarbeiter(request.getParameter("LogInID"), request.getParameter("LogInPasswort")) ) {


                User mitarbeiter = new Mitarbeiter( request.getParameter("LogInID"));

                this.session = new Session(mitarbeiter, new Mitarbeiter_Zugang());

                request.getRequestDispatcher("Mitarbeiter/MAAuswahl.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("ATM/LoginFehlgeschlagen.jsp").forward(request, response);
            }
        } else if (request.getParameter("LoginFehlgeschlagenZurueck") != null) {
            request.getRequestDispatcher("Mitarbeiter/MALogin.jsp").forward(request, response);
        }



        // Transactions
        else if (request.getParameter("Transactions") != null) {
            request.getRequestDispatcher("ATM/Transactions.jsp").forward(request, response);
        }

        // Einzahlung
        else if (request.getParameter("Einzahlung") != null) {
            request.getRequestDispatcher("Mitarbeiter/MAEinzahlung.jsp").forward(request, response);
        } else if (request.getParameter("Einzahlen") != null) {
            request.getRequestDispatcher("Mitarbeiter/MAEinzahlungErfolgt.jsp").forward(request, response);

          //@TODO Einzahlung verbinden doMitarbeiterEinzahlen(Session session, Kunde kunde, long betrag)

        }

        // Auszahlung
        else if (request.getParameter("Auszahlung") != null) {
            request.getRequestDispatcher("Mitarbeiter/MAAuszahlung.jsp").forward(request, response);
        } else if (request.getParameter("Auszahlen") != null) {
            request.getRequestDispatcher("Mitarbeiter/MAAuszahlungErfolgt.jsp").forward(request, response);

          //@TODO Auszahlung verbinden doMitarbeiterAbheben(Session session, Kunde kunde, long betrag)
        }

        // Sonstiges
        else if (request.getParameter("Hauptmenu") != null) {
            request.getRequestDispatcher("Mitarbeiter/MAAuswahl.jsp").forward(request, response);
        } else if (request.getParameter("Abmelden") != null) {
            session = null;
            request.getRequestDispatcher("Mitarbeiter/MALogin.jsp").forward(request, response);
        }
    }
}

