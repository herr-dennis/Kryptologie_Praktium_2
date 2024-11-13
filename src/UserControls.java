import java.math.BigInteger;

public class UserControls {


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
