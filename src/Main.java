import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static BigInteger a = null;
    static BigInteger b = null;
    static int choice_ = 0;
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> primenumbers  = new ArrayList<>(List.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199));

        for (int i = 0; i < primenumbers.size(); i++) {
            PrimeUtils primeUtils = new PrimeUtils(primenumbers.get(i), 500 );
            int iaa =  primeUtils.checkPrime();
            if(iaa == 1){
                System.out.println("Fehler bei : " + primenumbers.get(i));
            }
        }



        int choice = userInput();
        if(choice==1){
            getUserInput();
            PrimeUtils primeUtils2 = new PrimeUtils(a,b);
          int result = primeUtils2.checkPrime();
            if(result==0){
                System.out.println("Prime is a prime number");
            }else {
                System.out.println("Prime is NOT a prime number");
            }
        }
        else if(choice==2){
            getUserInput();
            PrimeUtils primeUtils3 = new PrimeUtils(a,b);
            BigInteger prim = primeUtils3.nextPrime();
            System.out.println("Die nächste Primzahl ist: " + prim.toString());
        }
        else if(choice==3){
            getUserInput();
            PrimeUtils primeUtils4 = new PrimeUtils(a,b);
            int result = primeUtils4.anzahlZeugen();
            System.out.println("Die Anzahl der Zeugen ist: " + result);
        }
        else if(choice==4){


            while (true){

                getUserInput();

                PrimeUtils primeUtils5 = new PrimeUtils(a,b);
                System.out.println("anz eingeben....");
                String anzString = scanner.nextLine().trim();
                int anz = Integer.parseInt(anzString);
                long startTime = System.currentTimeMillis();
                double result = primeUtils5.nextPrimeAverage(anz);
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;
                System.out.println("Brechnungszeit ist: " + elapsedTime);
                System.out.println("Mittelwert ist : " + result);


            }


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
            b = userControls.expStringToBigInt(itString);
        }
        a = userControls.expStringToBigInt(nString);
    }
}