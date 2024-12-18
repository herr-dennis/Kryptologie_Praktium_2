
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static BigInteger n = null;
    static int it =0;
    static int choice_ = 0;
    static private BigInteger fak1 = null;
    static private BigInteger fak2 = null;





    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        RSA r = new RSA("Prof. Hoevers Keys");
        //r.encrypt();

        UserControls u = new UserControls();
        u.countBytes("9a0a4157c8e7e1e10d1d68beabafdd83327650d374da180e773eace5441c3490");

        //RSA();

       HexConverter hex1 = new HexConverter();
       BigInteger p = new BigInteger("4689919533358590928275807520471434120397285149467106736906341708110732277495608361425617430689574543082979770593528700894980458474456371186752878160854571068633245361593784929974207939668335967940161");
      hex1.decimalToHex(p);


        int choice = userInput();
        if(choice==1){
            getUserInput();
            PrimeUtils primeUtils2 = new PrimeUtils();
          int result = primeUtils2.checkPrime(n, it);
            if(result==0){
                System.out.println("Prime is a prime number");
            }else {
                System.out.println("Prime is NOT a prime number");
            }
        }
        else if(choice==2){

            getUserInput();
            PrimeUtils primeUtils3 = new PrimeUtils();
            BigInteger prim = primeUtils3.nextPrime(n, it);
            System.out.println("Die nächste Primzahl ist: " + prim.toString());
            System.out.println("Bit-Länge: " +prim.bitLength() );

        }
        else if(choice==3){
            getUserInput();
            PrimeUtils primeUtils4 = new PrimeUtils();
            int result = primeUtils4.anzahlZeugen(n);
            System.out.println("Die Anzahl der Zeugen ist: " + result);
        }
        else if(choice==4){

            while (true){

                getUserInput();
                PrimeUtils primeUtils5 = new PrimeUtils();
                System.out.println("anz eingeben....");
                String anzString = scanner.nextLine().trim();
                int anz = Integer.parseInt(anzString);
                long startTime = System.currentTimeMillis();
                double result = primeUtils5.nextPrimeAverage(n, it, anz);
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;
                System.out.println("Berechnungszeit ist: " + elapsedTime);
                System.out.println("Mittelwert ist : " + result);
            }
        }
    }

    public static void Signa(){

        //Hash-Funktion
        BigInteger nh = new BigInteger("223390229630894823575503");
        BigInteger eh = BigInteger.valueOf(23);
        BigInteger m = new UserControls().expStringToBigInt("10^50");
        m = m.multiply(nh);
        BigInteger h = m.modPow(eh,nh);

        System.out.println("Der Hashwert von m  :"+h + " durch die Hashfunktion");

        BigInteger n_rsa = new BigInteger("922676962174818067510521705931");
        BigInteger e_rsa = new BigInteger("17");
        BigInteger s = new BigInteger("122213354635279262673569254549");

        BigInteger h_strich = s.modPow(e_rsa,n_rsa);
        System.out.println("h(m) = :" +h_strich +" s^e mod n");

        if (h.equals(h_strich)) {
            System.out.println("Die Signatur ist gültig.");
        } else {
            System.out.println("Die Signatur ist ungültig.");
        }
    }
    public static int userInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welche Methode?\n 1) Test auf Primzahl" +
                "\n 2)Nächste Primzahl\n 3)Anzahl der Zeugen " +
                "\n 4)Abstand Primzahlen im Mittel ");
        int result = scanner.nextInt();
        choice_ = result;
        return result;
    }

    public static void getUserInput(){
        Scanner scanner = new Scanner(System.in);
        UserControls userControls = new UserControls();

        System.out.println("n eingeben....");
        String nString = scanner.nextLine().trim();
        System.out.println("n = "+nString);
        if(choice_!=3 ){
            System.out.println("it eingeben....");
            String itString = scanner.nextLine().trim();
            System.out.println("it = "+itString);
            it = Integer.parseInt(itString);
        }
        n = userControls.expStringToBigInt(nString);
    }
}