import java.math.BigInteger;
import java.util.Random;

public class PrimeUtils {

    private BigInteger n = null;
    private int it = 0;
    private BigInteger nextPrime = null;
    private int Zeugen = 0;
    private boolean modus = true;

    PrimeUtils(BigInteger n, BigInteger it) {

        try {
            this.n = n;

            this.it = it.intValue();
            this.nextPrime = n;
        } catch (Exception e) {
            System.out.println("Fehler im BigInteger-Konstruktor PrimeUtils");
        }

    }

    PrimeUtils(int n, int it) {

        try {
            this.n = BigInteger.valueOf(n);
            this.it = it;
            this.nextPrime = BigInteger.valueOf(n);
        } catch (Exception e) {
            System.out.println("Fehler im Integer-Konstruktor PrimeUtils");
        }

    }

    public int checkPrime() {
        for (int i = 0; i < it; i++) {
            int tmp = millerRabinTest();
            if (tmp == 1) {
                return 1; // Zahl ist sicher keine Primzahl
            }
        }
        return 0; // Zahl ist wahrscheinlich prim
    }

    public int millerRabinTest() {
        BigInteger exponent = n.subtract(BigInteger.ONE);
        BigInteger a = getRandomNumber();

        // Sonderfall: n <= 2
        if (n.compareTo(BigInteger.valueOf(2)) <= 0) {
            throw new IllegalArgumentException("n muss größer als 2 sein.");
        }

        // Geraden Zahlen sind keine Primzahlen
        if (n.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            return 1; // Zahl ist keine Primzahl
        }

        // Schrittweise Exponent-Halbierung
        while (exponent.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            BigInteger tmp = a.modPow(exponent, n);

            // Wenn tmp kongruent -1 modulo n ist, ist die Zahl wahrscheinlich prim
            if (tmp.equals(n.subtract(BigInteger.ONE))) {
                return 0;
            }
            // Wenn tmp ungleich 1 ist, ist die Zahl wahrscheinlich keine Primzahl
            if (!tmp.equals(BigInteger.ONE)) {
                return 1;
            }
            exponent = exponent.divide(BigInteger.TWO);
        }
        return 0; // Zahl ist wahrscheinlich prim
    }

    private BigInteger getRandomNumber() {

        BigInteger a = new BigInteger("2");
        int rnd_ = 0;
        if (modus) {
            Random rand = new Random();
            do {
                a = new BigInteger(n.bitLength() - 1, rand);
            } while (a.compareTo(BigInteger.TWO) < 0 || a.compareTo(n.subtract(BigInteger.ONE)) >= 0);
            return a;
        }
        if (!modus) {
            a = BigInteger.valueOf(Zeugen);
            a = a.add(BigInteger.ONE);
            Zeugen++;
        }

        return a;
    }

    public BigInteger nextPrime() {
        while (checkPrime() == 1) {
            n = n.add(BigInteger.ONE);
        }
        return n;
    }

    public int anzahlZeugen() {
        modus = false;
        int maxZeuge = n.intValue()-1;
        int anzahlZeuge = 0;

        for (int i = 0; i < maxZeuge; i++) {
            if (millerRabinTest() == 1) {
                anzahlZeuge++;
            }
        }
        return anzahlZeuge;

    }

    public void reInit(BigInteger n, BigInteger it) {
        try {
            this.n = n;
            this.it = it.intValue();
            this.nextPrime = n;
        } catch (NumberFormatException e) {
            System.out.println("Fehler im BigInteger-Reinit PrimeUtils");
        }

    }

    public void reInit(int n, int it) {
        try {
            this.n = BigInteger.valueOf(n);
            this.it = it;
            this.nextPrime = BigInteger.valueOf(n);
        } catch (NumberFormatException e) {
            System.out.println("Fehler im Integer-Reinit PrimeUtils");
        }

    }


}

