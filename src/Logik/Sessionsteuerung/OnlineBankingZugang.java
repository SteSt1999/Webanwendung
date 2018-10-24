package Logik.Sessionsteuerung;

import Datenbank.DBUser;
import Logik.Umwandlung;
import Logik.Verwaltung.Kunde;
import Logik.Verwaltung.Transaction;
import Logik.Verwaltung.User;

import static Datenbank.DBLog.logHinzufuegen;

public class OnlineBankingZugang implements Zugangsweg {
    final private String dbBezeichnung = "2";

    public OnlineBankingZugang() {
    }

    public String getdbBezeichnung() {
        return dbBezeichnung;
    }

    public static void doOnlineBankingUeberweisung(Session session, User kunde, String eingabeBetrag) {
        long betrag = Umwandlung.stringToLong(eingabeBetrag);
        if (!DBUser.existiertKunde(kunde.getBenutzername())) {
            throw new IllegalArgumentException();
        }

        ((Kunde) session.getUser()).getKonto().ueberweisen(session.getUser(), kunde, betrag);

        Transaction transactionUeberweisung = new Transaction(session.getUser(), kunde, -betrag, session.getZugangsweg(), 1);
        Transaction transactionUeberweisungErhalten = new Transaction(kunde, session.getUser(), betrag, session.getZugangsweg(), 2);

        logHinzufuegen(transactionUeberweisung);
        logHinzufuegen(transactionUeberweisungErhalten);
    }
}