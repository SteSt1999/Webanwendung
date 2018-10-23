package Logik.Verwaltung;

import java.util.List;

public class Bank {
    private String name;

    public Bank(String name){
        setName(name);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
