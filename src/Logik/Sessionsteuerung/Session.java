package Logik.Sessionsteuerung;

import java.util.Random;

public class Session {

    private Zugangsweg zugangsweg;
    private int id;


    public Session (Zugangsweg zugangsweg){

        this.id = new Random().nextInt();
        this.zugangsweg = zugangsweg;

    }

}
