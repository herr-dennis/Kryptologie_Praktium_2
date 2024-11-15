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
        if(n.intValue()==2 || n.intValue()==3){
            return 0;
        }
        for (int i = 0; i < it; i++) {
            int tmp = millerRabinTest();
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
        BigInteger exponent = n.subtract(BigInteger.ONE);
        BigInteger a = getRandomNumber();

        // Sonst inf. Loop
        if(n.equals(BigInteger.TWO)) {
            return 0;
        }
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

    /**
     * Wird standardmäßig verwendet.
     * Generiert Zufallszahlen zwischen [2 bis n-1]
     * Zufallszahlen werden mit BigInteger generiert, normaler Integer reicht nicht aus,
     * Überlauf dann Exceptions
     * @return zufällige Zahl zwischen [2 bis n-1]
     */
    private BigInteger getRandomNumber() {

        // Wenn n = 0, gib sofort 0 zurück
        if (n.equals(BigInteger.ZERO)) {
            return BigInteger.ZERO;
        }

        BigInteger a;
        Random rand = new Random();

        if (modus) {
            // Generiere ein zufälliges a im Bereich [2, n - 1]
            do {
                a = new BigInteger(n.bitLength() - 1, rand);
            } while (a.compareTo(BigInteger.TWO) < 0 || a.compareTo(n.subtract(BigInteger.ONE)) >= 0);

            return a;
        } else {
            // Wenn nicht im Modus (modus = false), benutze die Zeugen-Variable
            a = BigInteger.valueOf(Zeugen).add(BigInteger.ONE);
            Zeugen++;
            return a;
        }
    }


    /**
     * Wird verwendet bei nextPrime
     * @param n_obereSchranke schränkt die Größe der Zufallszahl nach oben ein.
     * @return eine zufällige Zahl von 0 bis n
     * Zahlen im Interval [0-3] werden in den anderen Methoden korrekt behandelt.
     * Hier darf n_obereSchranke nicht <= 0 sein !
     */
    private BigInteger getRandomNumber(BigInteger n_obereSchranke) {

        if(n_obereSchranke.intValue()==0){
            return BigInteger.ZERO;
        }

        BigInteger a = new BigInteger("2");
        if (modus) {
            Random rand = new Random();
            a = new BigInteger(n_obereSchranke.bitLength() -1, rand);
            return a;
        }
        if (!modus) {
            a = BigInteger.valueOf(Zeugen);
            a = a.add(BigInteger.ONE);
            Zeugen++;
        }

        return a;
    }

    /**
     * Berechnet die nächste Primzahl >=n
     * Wenn n eine Primzahl ist, wird sie direkt zurückgegeben
     * @return die nächste Primzahl
     */
    public BigInteger nextPrime() {

        if(n.intValue()==0 || n.intValue()==1 || n.intValue()==2){
            BigInteger tmp = BigInteger.valueOf(2);
            return tmp;
        }
        while (checkPrime() == 1) {
            n = n.add(BigInteger.ONE);
        }
        return n;
    }

    /**
     * Der Boolean modus, schaltet die getRandom-Methode um, dass sie inkrementiert
     * Zählt die Anzahl der Zeugen
     */
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

    public double nextPrimeAverage(int anz){

        BigInteger m = null;
        double diff = 0;
        BigInteger obereSchranke = n;

        for (int i = 0; i < anz; i++) {
            m = getRandomNumber(obereSchranke);
            n = m;
            nextPrime =   nextPrime();
            diff = diff + (nextPrime.doubleValue() - m.doubleValue());
        }
        return diff/anz;
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

