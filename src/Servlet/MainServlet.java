package Servlet;

import Logik.Verwaltung.Bank;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Auswahl der Bank
        if (request.getParameter("WahlBank") != null) {
            Bank bank = null;
            try {
                bank = new Bank(request.getParameter("BankID"));
            } catch (IllegalArgumentException e) {
                request.getRequestDispatcher("BankAuswahlFehlgeschlagen.jsp").forward(request, response);
            }

            HttpSession session=request.getSession();
            session.setAttribute("bank", bank);

            request.getRequestDispatcher("Auswahl.jsp").forward(request, response);
        } else if (request.getParameter("Zurueck") != null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

        // Auswahl des Zugangsweg
        else if (request.getParameter("ATM") != null) {
            request.getRequestDispatcher("ATM/ATMLogin.jsp").forward(request, response);
        } else if (request.getParameter("OnlineBanking") != null) {
            request.getRequestDispatcher("OnlineBanking/OBLogin.jsp").forward(request, response);
        } else if (request.getParameter("Mitarbeiter") != null) {
            request.getRequestDispatcher("Mitarbeiter/MALogin.jsp").forward(request, response);
        }
    }

    public static void ausloggen(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Bank bank = (Bank) session.getAttribute("bank");
        session.invalidate();
        session = request.getSession();
        session.setAttribute("bank", bank);
    }
}