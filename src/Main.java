import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        PrimeUtils primeUtils = new PrimeUtils(14,3);
        BigInteger a = new BigInteger("17");
        BigInteger b = new BigInteger("22");

        PrimeUtils primeUtils1 = new PrimeUtils(a,b);

        int nr = primeUtils1.millerRabinTest();
        System.out.println(nr);


    }
}