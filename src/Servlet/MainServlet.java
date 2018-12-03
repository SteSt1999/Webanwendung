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
    private static Bank bank;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Auswahl der Bank
        if (request.getParameter("WahlBank") != null) {
            String bankID = request.getParameter("BankID");
            try {
                setBank(bankID);
            } catch (IllegalArgumentException e) {
                request.getRequestDispatcher("BankAuswahlFehlgeschlagen.jsp").forward(request, response);
            }

            HttpSession session=request.getSession();
            session.setAttribute("bank", bankID);

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

    //TODO
    public static Bank getBank() {
        return bank;
    }

    private static void setBank(String bankID) {
        bank = new Bank(bankID);
    }
}