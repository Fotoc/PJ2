package Projecte2;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CorreuElectronic {
    private String correu;
    private String usuari;
    private String organitzacio;
    private String domini;

    public CorreuElectronic(){

    }
   
    public CorreuElectronic(String correu) {
        this.correu = correu;
    }

    

    public String getCorreu() {
        return correu;
    }

    public boolean setCorreu(String correu) {
        if (validarEmail(correu)) {
            this.correu = correu;
            return true;

        } else {
            return false;
        }
    }

    public static Boolean validarEmail (String correu) {
		Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
		Matcher matcher = pattern.matcher(correu);
		return matcher.matches();
	}

    // private boolean validarEmail(String correu){
    //     boolean emailOK = true;
    //     int pospriarroba = correu.indexOf('@');
    //     int posultarroba = correu.lastIndexOf('@');

    //     if (pospriarroba == -1){
    //         return false;
    //     } else {
    //         if (pospriarroba != posultarroba) {
    //             return false;
    //         } else {
    //              String local = correu.substring(0, pospriarroba);
    //              String domExt = correu.substring(pospriarroba + 1, correu.length());

    //             if (local.length() == 0) {
    //                 return false;
    //         }

    //         }
           
    //     }
    //     return emailOK;
    // }

}