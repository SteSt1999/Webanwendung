package Logik;

import java.text.DecimalFormat;

public class Umwandlung {
    private static final long MAXIMUM = 10_000_000; //100.000 €
    private static final DecimalFormat df = new DecimalFormat("#0.00");

    public static long stringToLong(String betrag) {
        String[] split = betrag.split("\\.");
        if (split.length > 2 || (split.length == 2 && split[1].length() > 2)) {
            throw new NumberFormatException();
        }
        if (split.length == 2) {
            if (split[1].length() == 1) {
                betrag = split[0] + split[1] + "0";
            } else if (split[1].length() == 2) {
                betrag = split[0] + split[1];
            } else {
                betrag = split[0];
            }
        } else {
            betrag += "00";
        }

        long n = Long.parseLong(betrag);
        if (n > MAXIMUM || n <= 0) {
            throw new NumberFormatException();
        }

        return n;
    }

    public static String centToEuroString(long betrag) {
        return df.format(betrag / 100.) + "€";
    }
}