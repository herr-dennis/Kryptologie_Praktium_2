import java.math.BigInteger;

public class HexConverter {

    public String decimalToHex(BigInteger da) {
        System.out.println("Der Dezimal-Wert vor der Umwandlung: " + da.toString());

        // Dezimal in Hexadezimal umwandeln
        String result = da.toString(16);
        System.out.println("Der Hex-Wert nach der Umwandlung: " + result);

        // Kontrolle durch Rückumwandlung
        BigInteger back = new BigInteger(result, 16);
        System.out.println("Zurück zur Dezimalzahl zur Kontrolle: " + back.toString());

        if (da.equals(back)) {
            System.out.println("Die Werte sind gleich! Methode funktioniert!");
        } else {
            System.out.println("Die Werte sind nicht gleich! Methode funktioniert NICHT!");
        }

        return result;
    }

    public BigInteger hexToDecimal(String hex) {
        System.out.println("Der Hex-Wert vor der Umwandlung zur Dezimalzahl: " + hex);

        // Hexadezimal in Dezimal umwandeln
        BigInteger result = new BigInteger(hex, 16);
        System.out.println("Die Dezimalzahl nach der Umwandlung: " + result.toString());

        // Kontrolle durch Rückumwandlung
        String backToHex = result.toString(16);
        System.out.println("Zurück zur Hex-Zahl zur Kontrolle: " + backToHex);

        if (hex.equalsIgnoreCase(backToHex)) {
            System.out.println("Die Werte sind gleich! Methode funktioniert!");
        } else {
            System.out.println("Die Werte sind nicht gleich! Methode funktioniert NICHT!");
        }

        return result;
    }
}