package Logik;

public class Umwandlung {
    final private static long MAXIMUM = 10_000_000; //100.000 €

    public static long stringToLong(String betrag) {
        String[] split = betrag.split("\\.");
        if (split.length > 2 || (split.length == 2 && split[1].length() > 2)) {
            throw new NumberFormatException();
        }
        long n = 0;
        n = Long.parseLong(betrag);

        if (n > MAXIMUM || n <= 0) {
            throw new NumberFormatException();
        }

        //Cent --> Euro
        return n * 100;
    }
}