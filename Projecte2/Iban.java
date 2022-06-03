package Projecte2;

import java.math.BigInteger;

public class Iban {
    private String iban;

    public Iban(String iban) {
    }

    public Iban() {
    }

    public static boolean validarIBAN(String iban) {
        boolean esValid = false;
        int i = 2;
        int caracterASCII = 0;
        int resta = 0;
        int dc = 0;
        String cadenaDc = "";
        BigInteger compteNumero = new BigInteger("0");
        BigInteger modo = new BigInteger("97");

        if (iban.length() == 24 && iban.substring(0, 1).toUpperCase().equals("E")
                && iban.substring(1, 2).toUpperCase().equals("S")) {

            do {
                caracterASCII = iban.codePointAt(i);
                esValid = (caracterASCII > 47 && caracterASCII < 58);
                i++;

            } while (i < iban.length() && esValid);

            if (esValid) {
                compteNumero = new BigInteger(iban.substring(4, 24) + "142800");
                resta = compteNumero.mod(modo).intValue();
                dc = 98 - resta;
                cadenaDc = String.valueOf(dc);
            }

            if (dc < 10) {
                cadenaDc = "0" + cadenaDc;
            }

            // Comparem els caracters 2 i 3 de el compte del IBAN amb la cadenaDc
            if (iban.substring(2, 4).equals(cadenaDc)) {
                esValid = true;
            } else {
                esValid = false;
            }

        }
        return esValid;
    }

    public String getCompte() {
        return iban;
    }

    public void setCompte(String iban) {
        this.iban = iban;
    }

}
