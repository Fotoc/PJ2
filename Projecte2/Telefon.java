package Projecte2;
import javax.print.attribute.standard.RequestingUserName;   
public class Telefon {
    private String telefon;

    public Telefon(String telefon) {
        if (validarTelefon(telefon)) {
            this.telefon = telefon;
        }
    }

    Telefon() {

    }
    

    public boolean validarTelefon(String telefon) {
        // Validem que el numero tingui 9 digits exactes
        if (telefon.length() != 9) {
            System.out.println("El telÃ¨fon ha de tenir 9 digits");
            return false;
        } else {
            // validem que tots els digits siguin numeros
            for (int i = 0; i < telefon.length(); i++) {
                if (!(telefon.charAt(i) >= '0' && telefon.charAt(i) <= '9')) {
                    System.out.println("Tots els digits han de ser numeros");
                    return false;
                }
            }

            return true;
        }

    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setTelefon() {
    }
}