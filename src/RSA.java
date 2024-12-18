import java.io.*;
import java.math.BigInteger;

public class RSA {

    private BigInteger cipher = null;
    private BigInteger m = BigInteger.ZERO;
    private BigInteger n = BigInteger.ZERO;
    private BigInteger e = BigInteger.ZERO;
    private BigInteger d = BigInteger.ZERO;
    private  String id = "";

   public RSA(String id) {
       this.id = id;
   }

    /**
     *  Eine Methode, die eine Nachricht m, mit einem öffentlichen Schlüssel eines Empfängers
     *  verschlüsselt.
     *  Lädt das n und e aus der Datei keysInput.txt
     *  m die zu verschlüsselnde NNachricht.
     */
    public void encrypt()  {

        try {
            loadKeysRSA();
            System.out.println("Der geladene Schlüssel n :" +n);
            System.out.println("Die geladene Nachricht m :" +m);
            System.out.println("Der geladene Schlüssel e :" +e);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        cipher = m.modPow(e,n);

        try {
            saveKeysRSA("Verschlüsseln");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void createYourKeys(){
        





    }

    public void decryption(){

        BigInteger fakt1 = new BigInteger("265613988875874769338781322035779626829233452653394495974574961739092490901302182994384699044269");
        BigInteger fakt2 = new BigInteger("10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000267");
        BigInteger e = new BigInteger("211");
        BigInteger n = fakt1.multiply(fakt2);
        BigInteger f = fakt1.subtract(BigInteger.ONE).multiply(fakt2.subtract(BigInteger.ONE));
        BigInteger d = e.modInverse(f);
        BigInteger c = new BigInteger("543528524044592310600294707594591953104306188635873745946157236147371512079541628820905934322051010859058782210844015864313057718343362367882948274159393779227089709381577257102403162539634628555");
        BigInteger decrypted_ = c.modPow(d,n);
        HexConverter hex = new HexConverter();
        String hex_text = hex.decimalToHex(decrypted_);
        System.out.println("Entschlüsselte Text lautet: "+ decrypted_.toString()+"\n"+hex_text);


    }



    public static void RSA_VER(){
        HexConverter hex = new HexConverter();
        BigInteger m = hex.hexToDecimal("d5f64bc8b42e409d988f34758d324a06");
        BigInteger e = new BigInteger("23");
        BigInteger n = new BigInteger("28541051791918646864847075615479326408105827028809143996925179779784478894855921169956839950452310629585259309807155799607801796202500147861147113770015684499622926025298944610708116266918280229637549");
        BigInteger c_ = m.modPow(e,n);
        System.out.println("Der versschlüsselte Text :" +c_.toString());

    }


    public boolean saveKeysRSA(String method) throws IOException {
        // Datei, in die die Schlüssel geschrieben werden sollen
        File file = new File("keys.txt");

        // Verwende FileWriter im Append-Modus und BufferedWriter
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            // Schreibe die Schlüssel in die Datei
            bw.write(method + "\n");
            bw.write("ID: " + id + "\n");
            bw.write("Keys N: " + n.toString() + "\n");
            bw.write("E: " + e.toString() + "\n");
            bw.write("Ciphertext: "+cipher.toString() + "\n");
            bw.write("--------------------------\n"); // Trennlinie für Übersicht

            // Bestätigung, dass alles geschrieben wurde
            return true;
        } catch (IOException ex) {
            // Fehler beim Schreiben in die Datei
            ex.printStackTrace();
            return false;
        }
    }


    public boolean loadKeysRSA() throws IOException {
        // Datei, aus der die Schlüssel geladen werden
        File file = new File("keysInput.txt");

        // Verwende BufferedReader für die Dateioperationen
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            // Lies jede Zeile und prüfe auf Schlüsselwerte
            while ((line = br.readLine()) != null) {
                if (line.toLowerCase().contains("n")) { // Prüfen auf 'n:' oder 'N:'
                    String value = line.split(":")[1].trim(); // Wert nach ':' extrahieren
                    n = new BigInteger(value); // In BigInteger umwandeln
                }
                    if (line.toLowerCase().contains("e")) { // Prüfen auf 'e:' oder 'E:'
                    String value = line.split(":")[1].trim();
                    e = new BigInteger(value);
                }

                    if (line.toLowerCase().contains("m")) {
                        String value = line.split(":")[1].trim();
                        m = new BigInteger(value);
                    }
            }
        } catch (IOException ex) {
            // Fehler beim Lesen der Datei
            ex.printStackTrace();
            return false;
        }

        // Gib true zurück, wenn die Schlüssel erfolgreich geladen wurden
        return n != null && e != null;
    }

    public BigInteger getCipher() {
        return cipher;
    }

    public void setCipher(BigInteger cipher) {
        this.cipher = cipher;
    }
    public BigInteger getPlain() {
        return m;
    }

    public void setPlain(BigInteger plain) {
            this.m = plain;
    }

}
