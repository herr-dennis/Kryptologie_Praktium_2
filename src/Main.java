import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    static BigInteger a = null;
    static BigInteger b = null;
    static int choice_ = 0;
    UserControls userControls = new UserControls();
    public static void main(String[] args) {

       Scanner scanner = new Scanner(System.in);


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
            PrimeUtils primeUtils5 = new PrimeUtils(a,b);

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
            System.out.println("b eingeben....");
            String itString = scanner.nextLine().trim();
            System.out.println("it = "+itString);
            b = userControls.expStringToBigInt(itString);
        }
        a = userControls.expStringToBigInt(nString);

    }
}