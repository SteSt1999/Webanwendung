package Logik.Verwaltung;

import Logik.Logs.ATM_Log;

public class ATM {
    private int id;
    private double befüllung;

    //Log der Transaktonen die ein bestimmter ATM geführt hat
    private ATM_Log ATM_Log;



    public ATM(int id) {
        this.id = id;
    }


}
