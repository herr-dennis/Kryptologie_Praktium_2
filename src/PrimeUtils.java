import java.math.BigInteger;
import java.util.Random;

public class PrimeUtils {
    private BigInteger n = null;
    private int it = 0;

    PrimeUtils(BigInteger n , BigInteger it){

        try{
            this.n = n;
            this.it = it.intValue();
        }catch (NumberFormatException e){
            System.out.println("Fehler im BigInteger-Konstruktor PrimeUtils");
        }

    }

    PrimeUtils(int n, int it){

        try {
            this.n = BigInteger.valueOf(n);

        }
        catch (NumberFormatException e){
            System.out.println("Fehler im Integer-Konstruktor PrimeUtils");
        }

    }


    public int checkPrime(){

        for(int i = 0; i < it; i++){


        }
    return 0;
    }

    public int millerRabinTest() {
        /**
         *  a^(n-1) mod n
         */
        BigInteger n_ = n.subtract(BigInteger.ONE);
        BigInteger tmp = null;
        BigInteger a = null;

        if(!n_.mod(BigInteger.TWO).equals(BigInteger.ZERO)){
            return 1;
        }

        // n_ % 2 = 0
        while (n_.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            a = getRandomNumber();
            tmp = a.pow(n_.intValue()).mod(n);
            if (!(tmp.intValue() == 1 || tmp.intValue() == -1)) {
                     return 1;
            }
             n_ = n_.divide(BigInteger.TWO);
        }
        return 0;
    }
        public BigInteger getRandomNumber(){

            int rndNumber =0;
            Random rnd = new Random();

            rndNumber = rnd.nextInt(n.intValue()-3) +1;

            BigInteger bigRnd = BigInteger.valueOf(rndNumber);
            return bigRnd;

        }





    }

