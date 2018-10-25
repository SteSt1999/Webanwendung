package Logik.Sessionsteuerung;

import Datenbank.DBKontostand;
import Datenbank.DBUser;
import Logik.Umwandlung;
import Logik.Verwaltung.*;
import Servlet.MainServlet;

import static Datenbank.DBLog.logHinzufuegen;

public class OnlineBankingZugang implements Zugangsweg {
    final private String dbBezeichnung = "2";

    public OnlineBankingZugang() {
    }

    public String getdbBezeichnung() {
        return dbBezeichnung;
    }

    public static void doOnlineBankingUeberweisung(Session session, String empfaenger, String empfaengerBankID, String eingabeBetrag) {
        long betrag = Umwandlung.stringToLong(eingabeBetrag);
        empfaengerBankID = empfaengerBankID.trim().length() == 0 ? MainServlet.getBankID() : empfaengerBankID;
        Bank empfaengerBank = new Bank(empfaengerBankID);

        if (!DBUser.existiertKunde(empfaenger, empfaengerBank.getBankID())) {
            throw new IllegalArgumentException();
        }

        Kunde empfaengerUser = new Kunde(new Konto(DBKontostand.kontostandLesen(empfaenger, empfaengerBankID)), empfaenger, empfaengerBankID);
        Kunde senderUser = (Kunde) session.getUser();

        Konto.ueberweisen(senderUser, empfaengerUser, betrag);

        Transaction transactionUeberweisung = new Transaction(senderUser, empfaengerUser, -betrag, session.getZugangsweg(), 1);
        Transaction transactionUeberweisungErhalten = new Transaction(empfaengerUser, senderUser, betrag, session.getZugangsweg(), 2);
        logHinzufuegen(transactionUeberweisung);
        logHinzufuegen(transactionUeberweisungErhalten);
    }
}