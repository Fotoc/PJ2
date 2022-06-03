package Projecte2;

public class Dni {
     String dni;
     int numero;
     char lletra;

     public Dni(){

     }

     public Dni(String dni){
            if (setDni(dni)) this.dni=dni;
            else this.dni="incorrecte";
    }

    public String getDni(){
        return dni;
    }

    public boolean setDni(String dni){
        if (validarDNI(dni)){
            this.dni=dni;
            return true;
        } else{
            System.out.println("DNI incorrecte");
        }
        return false;
    }

    public void setDni(){
    }    

    public boolean validarDNI(String dni) {
        String lletraMajuscula = "";
        if (dni.length() != 9 || Character.isLetter(dni.charAt(8)) == false) {
            return false;
        }

        lletraMajuscula = (dni.substring(8)).toUpperCase();
        if (solNumeros(dni) == true && lletraDNI(dni).equals(lletraMajuscula)) {
            return true;
        } else {
            return false;
        }

    }

    private boolean solNumeros(String dni) {
        int i, j = 0;
        String numero = "";
        String DNI = "";
        String[] unNou = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

        for (i = 0; i < dni.length() - 1; i++) {
            numero = dni.substring(i, i + 1);

            for (j = 0; j < unNou.length; j++) {
                if (numero.equals(unNou[j])) {
                    DNI += unNou[j];
                }
            }
        }

        if (DNI.length() != 8) {
            return false;
        } else {
            return true;
        }
    }

    private String lletraDNI(String dni) {
        int DNI = Integer.parseInt(dni.substring(0, 8));
        int rest = 0;
        String lletra = "";
        String[] assignacio = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q",
                "V", "H", "L", "C", "K", "E" };

        rest = DNI % 23;
        lletra = assignacio[rest];

        return lletra;
    }

    

}
