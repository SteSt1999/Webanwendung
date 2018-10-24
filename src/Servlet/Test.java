package Servlet;

import Logik.Verwaltung.Konto;
import Logik.Verwaltung.Kunde;

import static Datenbank.DBKontostand.kontostandLesen;

public class Test {

    public static void main(String [] args){

        Kunde kunde = new Kunde(new Konto(kontostandLesen("holzmann.julia")),"stump.stefan");



        System.out.println( kunde.getBenutzername());
        System.out.println(kunde.getKonto().getKontostand());

    }
}
