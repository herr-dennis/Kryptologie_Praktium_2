import java.math.BigInteger;
import java.util.Random;

public class PrimeUtils {

    private BigInteger n_intern = null;
    private int it = 0;
    private BigInteger nextPrime = null;
    private int Zeugen = 0;
    private boolean modus = true;

    PrimeUtils(BigInteger n, BigInteger it) {

        try {
            this.n_intern = n;
            this.it = it.intValue();
            this.nextPrime = n;
        } catch (Exception e) {
            System.out.println("Fehler im BigInteger-Konstruktor PrimeUtils");
        }

    }

    PrimeUtils(int n, int it) {

        try {
            this.n_intern = BigInteger.valueOf(n);
            this.it = it;
            this.nextPrime = BigInteger.valueOf(n);
        } catch (Exception e) {
            System.out.println("Fehler im Integer-Konstruktor PrimeUtils");
        }

    }
    PrimeUtils(){}

    public int checkPrime(BigInteger n, int it) {
        if(n.equals(BigInteger.TWO) || n.equals(BigInteger.valueOf(3))){
            return 0;
        }
        for (int i = 0; i < it; i++) {
            int tmp = istZeuge(n,getRandomNumber(n));
            if (tmp == 1) {
                return 1; // Zahl ist sicher keine Primzahl
            }
        }
        return 0; // Zahl ist wahrscheinlich prim
    }

    /**
     * MillerRabin Algorithmus.
     * Verwendet das Verfahren "Top-Down" der Exponent wird immer halbiert.
     *
     * @return 1 für nicht Primzahl, 0 für Primzahl
     */
    public int millerRabinTest() {
        BigInteger exponent = n_intern.subtract(BigInteger.ONE);
        BigInteger a = getRandomNumber(n_intern);

        // Sonst inf. Loop
        if(n_intern.equals(BigInteger.TWO)) {
            return 0;
        }
        // Sonderfall: n <= 2
        if (n_intern.compareTo(BigInteger.valueOf(2)) <= 0) {
            throw new IllegalArgumentException("Muss größer 2 sein");
        }

        // Geraden Zahlen sind keine Primzahlen
        if (n_intern.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            return 1; // Zahl ist keine Primzahl
        }

        // Schrittweise Exponent-Halbierung
        while (exponent.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            BigInteger tmp = a.modPow(exponent, n_intern);

            // Wenn tmp kongruent -1 modulo n ist, ist die Zahl wahrscheinlich prim
            if (tmp.equals(n_intern.subtract(BigInteger.ONE))) {
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

    /**
     * Wird standardmäßig verwendet.
     * Generiert Zufallszahlen zwischen [2 bis n-1]
     * Zufallszahlen werden mit BigInteger generiert, normaler Integer reicht nicht aus,
     * Überlauf dann Exceptions
     * @return zufällige Zahl zwischen [2 bis n-1]
     */
    private BigInteger getRandomNumber(BigInteger n) {

        // Wenn n = 0, gib sofort 0 zurück
        if (n.equals(BigInteger.ZERO)) {
            return BigInteger.ZERO;
        }
        BigInteger a;
        Random rand = new Random();
            // Generiere ein zufälliges a im Bereich [2, n - 1]
            do {
                a = new BigInteger(n.bitLength() - 1, rand);
            } while (a.compareTo(BigInteger.TWO) < 0 || a.compareTo(n.subtract(BigInteger.ONE)) >= 0);

            return a;

    }
    public int istZeugeExpDiv(BigInteger n, BigInteger a) {
        BigInteger exponent = n.subtract(BigInteger.ONE);

        // Sonderfall: n <= 2
        if (n.compareTo(BigInteger.valueOf(2)) <= 0) {
            throw new IllegalArgumentException("Muss größer 2 sein");
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
    private int istZeuge(BigInteger n, BigInteger a) {

        BigInteger nMinus1 = n.subtract(BigInteger.ONE);
        BigInteger d = nMinus1;
        int s = 0;

        // n-1 als d * 2^s
        while (d.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            d = d.divide(BigInteger.TWO);
            s++;
        }

        // Berechne a^d % n
        BigInteger x = a.modPow(d, n);
        if (x.equals(BigInteger.ONE) || x.equals(nMinus1)) {
            return 0; // Kein Zeuge
        }

        // Iteriere über runden: x^2 % n
        for (int r = 0; r < s - 1; r++) {
            x = x.modPow(BigInteger.TWO, n);
            if (x.equals(nMinus1)) {
                return 0; // Kein Zeuge
            }
        }

        return 1; // Zeuge gefunden
    }


    /**
     * Berechnet die nächste Primzahl >=n
     * Wenn n eine Primzahl ist, wird sie direkt zurückgegeben
     * @return die nächste Primzahl
     */
    public BigInteger nextPrime(BigInteger n, int it) {

       /* if(n.intValue()==0 || n.intValue()==1 || n.intValue()==2){
            BigInteger tmp = BigInteger.valueOf(2);
            return tmp;
        }

        */
        while (checkPrime(n, it) == 1) {
            n = n.add(BigInteger.ONE);
        }
        return n;
    }

    /**
     * Der Boolean modus, schaltet die getRandom-Methode um, dass sie inkrementiert
     * Zählt die Anzahl der Zeugen
     */
    public int anzahlZeugen() {
        int maxZeuge = n_intern.intValue()-1;
        int anzahlZeuge = 0;

        for (int i = 0; i < maxZeuge; i++) {
            if (millerRabinTest() == 1) {
                anzahlZeuge++;
            }
        }
        return anzahlZeuge;

    }

    public int anzahlZeugen(BigInteger n) {
        BigInteger a = new BigInteger("1");
        int maxZeuge = n.intValue()-1;
        int anzahlZeuge = 0;

        for (int i = 0; i < maxZeuge; i++) {
            if (istZeuge(n,a) == 1) {
                System.out.println("Hier ist ein Zeuge:"+a.intValue());
                anzahlZeuge++;
            }
            a = a.add(BigInteger.ONE);
        }
        return anzahlZeuge;

    }


    public double nextPrimeAverage(BigInteger n, int it, int anz){

        BigInteger m = null;
        double diff = 0;
        BigInteger obereSchranke = n;

        for (int i = 0; i < anz; i++) {
            m = getRandomNumber(obereSchranke);
            n = m;
            nextPrime =   nextPrime(n, it);
            diff = diff + (nextPrime.doubleValue() - m.doubleValue());
        }
        return diff/anz;
    }

    public void reInit(BigInteger n, BigInteger it) {
        try {
            this.n_intern = n;
            this.it = it.intValue();
            this.nextPrime = n;
        } catch (NumberFormatException e) {
            System.out.println("Fehler im BigInteger-Reinit PrimeUtils");
        }

    }

    public void reInit(int n, int it) {
        try {
            this.n_intern = BigInteger.valueOf(n);
            this.it = it;
            this.nextPrime = BigInteger.valueOf(n);
        } catch (NumberFormatException e) {
            System.out.println("Fehler im Integer-Reinit PrimeUtils");
        }

    }

}

