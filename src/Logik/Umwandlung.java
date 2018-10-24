package Logik;

public class Umwandlung {
    final private static long MAXIMUM = 10_000_000; //100.000 €

    public static long stringToLong(String betrag) {
        System.out.println(betrag);

        String[] split = betrag.split("\\.");
        if (split.length > 2 || (split.length == 2 && split[1].length() > 2)) {
            throw new NumberFormatException();
        }
        if (split.length == 2) {
            betrag = split[0] + split[1];
        } else {
            betrag += "00";
        }

        long n = Long.parseLong(betrag);
        if (n > MAXIMUM || n <= 0) {
            throw new NumberFormatException();
        }

        return n;
    }
}