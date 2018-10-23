package Logik.Sessionsteuerung;

import Logik.Verwaltung.Kunde;
import Logik.Verwaltung.Transaction;
import Logik.Verwaltung.User;

import java.util.Random;



public class Session {
    private int id;


    //aktuell eingeloggter User
    //bei jedem Log
    private User user;


    public Session (User user){
        this.id = new Random().nextInt();
        this.user = user;

    }

    public void doTransaction(Zugangsweg zugangsweg, Kunde empfänger, String betrag){
        Transaction transaction = new Transaction(user,empfänger,betrag);

        if(zugangsweg instanceof OnlineBanking ){
            //zugangsweg.doOnlineBanking(transaction);
        }



    }
}
