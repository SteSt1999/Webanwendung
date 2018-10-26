package Logik.Sessionsteuerung;

import Datenbank.DBKontostand;
import Datenbank.DBUser;
import Logik.Umwandlung;
import Logik.Verwaltung.Bank;
import Logik.Verwaltung.Konto;
import Logik.Verwaltung.Kunde;
import Logik.Verwaltung.Transaction;
import Servlet.MainServlet;

import static Datenbank.DBLog.logHinzufuegen;

public class GeldBewegung {
    public static void ueberweisen(Kunde kunde, Zugangsweg zugangsweg, String empfaenger, String empfaengerBankID, String eingabeBetrag) {
        long betrag = Umwandlung.stringToLong(eingabeBetrag);
        empfaengerBankID = empfaengerBankID.trim().length() == 0 ? MainServlet.getBankID() : empfaengerBankID;
        Bank empfaengerBank = new Bank(empfaengerBankID);

        if (!DBUser.existiertKunde(empfaenger, empfaengerBank.getBankID())) {
            throw new IllegalArgumentException();
        }

        Kunde empfaengerUser = new Kunde(new Konto(DBKontostand.kontostandLesen(empfaenger, empfaengerBankID)), empfaenger, empfaengerBankID);

        Konto.ueberweisen(kunde, empfaengerUser, betrag);

        Transaction transactionUeberweisung = new Transaction(kunde, empfaengerUser, -betrag, zugangsweg, 1);
        Transaction transactionUeberweisungErhalten = new Transaction(empfaengerUser, kunde, betrag, new UeberweisungErhalten(), 2);
        logHinzufuegen(transactionUeberweisung);
        logHinzufuegen(transactionUeberweisungErhalten);
    }

    public static void abheben(Kunde kunde, Zugangsweg zugangsweg, String eingabeBetrag) {
        long betrag = Umwandlung.stringToLong(eingabeBetrag);

        kunde.getKonto().abheben(kunde, betrag);

        Transaction transaction = new Transaction(kunde, null, -betrag, zugangsweg, 3);
        logHinzufuegen(transaction);
    }

    public static void einzahlen(Kunde kunde, Zugangsweg zugangsweg, String eingabeBetrag) {
        long betrag = Umwandlung.stringToLong(eingabeBetrag);

        kunde.getKonto().einzahlen(kunde, betrag);

        Transaction transaction = new Transaction(kunde, null, betrag, zugangsweg, 4);
        logHinzufuegen(transaction);
    }
}