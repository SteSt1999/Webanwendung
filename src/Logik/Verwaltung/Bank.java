package Logik.Verwaltung;

public class Bank {
    private String bankID;
    private long guthaben;

    public Bank(String bankID) {
        setBankID(bankID);
        guthaben = 0;
    }

    public String getBankID() {
        return bankID;
    }

    public void setBankID(String bankID) {
        this.bankID = bankID;
    }

    public boolean existiertKunde(String kunde) {
        return true;
    }
}
