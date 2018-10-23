package Logik.Verwaltung;


public class ATM {
    private int id;


    public ATM(int id) {
        this.id = id;
      //@TODO atms in Datenbank schreiben?
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
