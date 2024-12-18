import java.math.BigInteger;

public class UserControls {


    public static String decimalToHex(BigInteger da) {

        System.out.println("Der Dezimal-Wert vor der Umwandlung:" +da.toString());
        String result = da.toString(16);
        System.out.println("Der Hash-Wert nach  der Umwandlung von: " + result);

        System.out.println("Zurück Umwandlung zur Kontrolle, durch andere Method:" );
        BigInteger back = hexToDecimal(result);
        System.out.println("Nach der Umwandlung der Kontrolle: " + back.toString());

        if(result.equals(back.toString())) {
            System.out.println("Die Hashwerte sind gleich! Methode funktioniert! " );
        }
        else
        {
            System.out.println("Die Hashwerte sind nicht gleich, Umwandlung funktioniert nicht!");
        }

        return result;
    }



    public void   countBytes(String hex) {
        int nibbles = hex.length();
        nibbles = nibbles / 2;
        System.out.println("Bytes: " + nibbles);
    }


    public static BigInteger hexToDecimal(String hex) {
        String input = hex;
        System.out.println("Der Hashwert vor der Umwandlung zur dezimal-Zahl"+input);
        BigInteger result = new BigInteger(input, 16);
        System.out.println("Die dezimal-Zahl nach der Umwandlung"+result.toString());

        System.out.println("Zurück Umwandlung zur Kontrolle durch andere Method");
        String back_to_hash = decimalToHex(result);
        BigInteger back = new BigInteger(back_to_hash, 16);

        System.out.println("Nach der Umwandlung zur dezimal-Zahl: "+back);

       if(result.equals(back)) {
           System.out.println("Die dezimal-Zahlen sind gleich! Methode funktioniert! " );
       }
       else {
           System.out.println("Die dezimal-Zahlen sind nicht gleich, Methode funktioniert nicht!");
       }



        return new BigInteger(hex, 16);
    }






    public BigInteger expStringToBigInt(String exp) {

        String potenz = exp;
        String expression ="";

        if(!exp.contains("^")&&!proofStringOfOps(exp)){
            return new BigInteger(exp);
        }

        if(proofStringOfOps(exp)) {
            if ((proofStringOfOps(exp))) {
                String[] result = splitExpression(exp);
                potenz = result[0];
                expression = result[1];
            }}

        String[]expParts = potenz.split("\\^");
        // Basis und Exponent verarbeiten
        BigInteger base = new BigInteger(expParts[0]);
        int exponent = Integer.parseInt(expParts[1]);

        // Berechne die Potenz
        BigInteger result = base.pow(exponent);

        if(!expression.isEmpty()){
            char c = expression.charAt(0);
            if(Character.isDigit(c)){
                result = result.add( new BigInteger(expression.trim()));
            }
        }

        return result;
    }
    public String[]  splitExpression(String exp) {
        String potenz = "";      // Für den Potenzteil
        String expression = "";  // Für den restlichen Ausdruck

        // Finde die Position des Potenzoperators "^"
        int indexOfPotenz = exp.indexOf('^');

        if (indexOfPotenz != -1) {
            // Suche die Basis der Potenz
            int baseStart = indexOfPotenz - 1;
            while (baseStart >= 0 && Character.isDigit(exp.charAt(baseStart))) {
                baseStart--;
            }
            // Extrahiere die Basis und den Exponenten für den Potenzterm
            potenz = exp.substring(baseStart + 1, exp.length());

            // Der restliche Ausdruck beginnt nach dem Potenzterm
            int endOfPotenz = indexOfPotenz + 1;
            while (endOfPotenz < exp.length() && Character.isDigit(exp.charAt(endOfPotenz))) {
                endOfPotenz++;
            }
            potenz = exp.substring(0, endOfPotenz);

            // Der Rest des Ausdrucks beginnt direkt nach dem Potenzterm
            expression = exp.substring(endOfPotenz).trim();
        }

        expression = expression.replace("+","");

        System.out.println("Potenzteil: " + potenz);
        System.out.println("Restlicher Ausdruck: " + expression);
        return new String[]{potenz, expression};

    }

    private boolean proofStringOfOps(String exp) {
        if(exp.contains("+") || exp.contains("-") || exp.contains("*") || exp.contains("/")) {
            return true;
        }
        return false;
    }

}
