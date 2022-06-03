package Projecte2;
import Projecte.Activitats;
import Projecte2.ConnexioBD;
import Projecte2.Client;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Gimnas {
    private String nom;
    private String CIF;
    ArrayList<Client> clients;
    ArrayList<Activitats> activitats;
    /*ArrayList<Reserves> reserves;*/
    private String telefon;

    public Gimnas(){
    this.clients = new ArrayList<>();
    this.activitats = new ArrayList<>();
    //this.reserves = new ArrayList<>();

    }

    public void gestionarGimnas() throws SQLException, IOException {
        boolean sortir = false;
        Connection conn = ConnexioBD.getConnection();
        Scanner teclat = new Scanner(System.in);

        do {
            System.out.println("******MENU GIMNAS******");
            System.out.println("1.Gestionar Client");
            System.out.println("2.Visualitzar Client");
            System.out.println("3.Visualitzar Activitats");
            System.out.println("4.Sortir");
            System.out.println("\nTria una de les opcions");

            int opcio = teclat.nextInt();

            switch (opcio) {
                case 1:
                    gestionarClient();
                    break;
                case 2:
                    visualitzarClient();
                    break;
                case 3:
                    visualitzarActivitat();
                    break;
                case 4:
                    sortir = true;
                    break;
                default:
                    System.out.println("L'Opció no és vàlida");
            }

            System.out.println(("opció: ") + opcio);

        } while (!sortir);
        desconnexioBD();

    }

    static void gestionarClient() throws SQLException {
        Scanner teclat = new Scanner(System.in);
        boolean enrere = false;
        do {
            System.out.println("******MENU GESTIONAR CLIENT******");
            System.out.println("1.Consulta Client");
            System.out.println("2.Alta de Client");
            System.out.println("3.Baixa de Client");
            System.out.println("4.Modificar Client");
            System.out.println("5.Enrere");
            System.out.println("\nTria una de les opcions");

            int opcio = teclat.nextInt();
            teclat.nextLine();
            Client c = new Client();
            switch (opcio) {
                case 1:
                    c.consultaClient();
                    break;
                case 2:
                    c.altaClient();
                    break;
                case 3:
                    // baixaClient();
                    break;
                case 4:
                    // modificarClient();
                    break;
                case 5:
                    enrere = true;
                    break;
                default:
                    System.out.println("L'Opció no és vàlida");
            }
        } while (!enrere);

    }

    public static void desconnexioBD() {
        System.out.println("Desconnectat de la BD");
    }


    private void visualitzarClient() throws SQLException {
        Scanner teclat = new Scanner(System.in);
        boolean enrere = false;
        do {
            System.out.println("******MENU VISUALITZAR CLIENTS******");
            System.out.println("1. Visualitzar Clients ordenats per cognoms");
            System.out.println("2. Visualitzar Clients ordenats per edats");
            System.out.println("3. Visualitzar Clients ordenats per reserves");
            System.out.println("4. Sortir");
            System.out.println("\nTria una opció: ");

            while (!teclat.hasNextLine()) {
                System.out.println("Valor no valid");
                teclat.next();
                System.out.println("Introdueix un número enter");
            }
            int opcio = teclat.nextInt();

            Client client = new Client();

            switch (opcio) {
                case 1:
                    this.clients = client.getClientOrdenatsCognoms();
                    visualitzarClients();
                    break;

                case 2:
                    // this.clients = client.getClientOrdenatsEdat();
                    // visualitzarClients();
                    break;
                case 3:
                    this.clients = client.getClientOrdenatsReserves();
                    visualitzarClients();
                    break;
                case 4:
                    enrere = true;
                    break;
                default:
                    System.out.println("L'opció no es vàlida");
            }
        } while (!enrere);

    }

    static void visualitzarClients() {

    }

    static void visualitzarActivitat() {

    }

}