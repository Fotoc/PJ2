package Projecte2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;

public class Client {

    private String nom;
    private String cognom;
    private Dni DNI;
    private String sexe;
    private Telefon telefon;
    private Email correu;
    private String usuari;
    private String contrasenya;
    private LocalDate data_naix;
    private String condicioFisica;
    private int comercial;
    private Iban compteBancari;
    private int edat;

    public Client(String nom, String cognom, Dni DNI, String sexe, Telefon telefon, Email correu, String usuari, String contrasenya, LocalDate data_naix, String condicioFisica, int comercial, Iban compteBancari, int edat) {
        this.nom = nom;
        this.cognom = cognom;
        this.DNI = DNI;
        this.sexe = sexe;
        this.telefon = telefon;
        this.correu = correu;
        this.usuari = usuari;
        this.contrasenya = contrasenya;
        this.data_naix = data_naix;
        this.condicioFisica = condicioFisica;
        this.comercial = comercial;
        this.compteBancari = compteBancari;
        this.edat = edat;
    }

    public void setnom(String nom) {
        this.nom = nom;
    }

    public void setcognom(String cognom) {
        this.cognom = cognom;
    }

    public void setDNI(Dni dNI) {
        DNI = dNI;
    }

    public void settelefon(Telefon telefon) {
        this.telefon = telefon;
    }

    public void setCorreu(Email correu) {
        this.correu = correu;
    }

    public void setdata_naix(LocalDate data_naix) {
        this.data_naix = data_naix;
    }

    public void setCondicioFisica(String condicioFisica) {
        this.condicioFisica = condicioFisica;
    }

    public void setCompteBancari(Iban compteBancari) {
        this.compteBancari = compteBancari;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setTelefon(Telefon telefon) {
        this.telefon = telefon;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public void setComercial(int comercial) {
        this.comercial = comercial;
    }

    public int getComercial() {
        return comercial;
    }

    public String getNom() {
        return nom;
    }

    public String getCognom() {
        return cognom;
    }

    public Dni getDNI() {
        return DNI;
    }

    public Telefon getTelefon() {
        return telefon;
    }

    public Email getCorreu() {
        return correu;
    }

    public LocalDate getData_naix() {
        return data_naix;
    }

    public String getCondicioFisica() {
        return condicioFisica;
    }

    public Iban getCompteBancari() {
        return compteBancari;
    }

    public String getSexe() {
        return sexe;
    }

    public String getUsuari() {
        return usuari;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public int getEdat() {
        return edat;
    }

    public Client() {
        this.DNI = new Dni();
        this.telefon = new Telefon();
        this.correu = new Email();
        this.compteBancari = new Iban();
        this.data_naix = LocalDate.now();
    }

    private void calcularEdad() {
        LocalDate ara = LocalDate.now();
        this.edat = Period.between(this.data_naix, ara).getYears();
    }

    public Client(String dni) {
        this.DNI = new Dni(dni);
        this.telefon = new Telefon();
        this.correu = new Email();
        this.compteBancari = new Iban();
        this.data_naix = LocalDate.now();
    }

    public void consultaClient() throws SQLException {
        Connection conn = ConnexioBD.getConnection();
        Scanner teclat = new Scanner(System.in);
        System.out.println("Buscar el Client amb el seu DNI: ");
        String dni = teclat.nextLine();
        String consulta = String.format("select * from socis where DNI=" + "\"%s\"" + ";", dni);
        PreparedStatement sql = conn.prepareStatement(consulta);
        sql.executeQuery();
        ResultSet rs = sql.executeQuery();
        if (rs.next()) {
            this.nom = rs.getString("Nom");
            this.cognom = rs.getString("Cognom");
            this.DNI = new Dni(rs.getString("DNI"));
            this.correu = new Email(rs.getString("email"));
            this.telefon = new Telefon(rs.getString("mobil"));
            this.contrasenya = rs.getString("contrasenya");
            this.sexe = rs.getString("sexe");
            this.usuari = rs.getString("usuari");
            this.compteBancari = new Iban(rs.getString("compte_bancari"));
            System.out.println("El client que busca té el Nom: " + this.nom + " \nCognom: " + this.cognom + " \nDNI: " + this.DNI.getDni() + " \nSexe: " + this.sexe + " \nUsuari: " + this.usuari + " \nContrasenya: " + this.contrasenya + " \nCorreu Electrònic: " + this.correu.getEmail() + " \nTelefon: " + this.telefon.getTelefon() + " \nCompte Bancari:" + this.compteBancari.getCompte());
        } else {
            System.out.println("No s'ha trobat al Client");
        }
    }
//
//    public void consultaClient() throws SQLException {
//        Scanner teclat = new Scanner(System.in);
//        System.out.println("*CONSULTA D'UN CLIENT*");
//        System.out.println("Introdueix DNI");
//        String DNI = teclat.next();
//        Client cli = consultaClientBD(DNI);
//        if (cli != null) {
//            System.out.println(cli);
//        } else {
//            System.out.println("No existeix el client");
//        }
//
//    }

    private Client consultaClientBD(String DNI) throws SQLException {
        Connection conn = ConnexioBD.getConnection();

        String consulta = "SELECT * FROM socis WHERE DNI = ?";
        PreparedStatement sentencia = conn.prepareStatement(consulta);
        sentencia.setString(1, DNI);
        System.out.println(sentencia);
        ResultSet rs = sentencia.executeQuery();

        if (rs.next()) {
            cargarDadesDeSentenciaEnClient(rs);
        }

        return null;

    }

    public void altaClient() throws SQLException {
        //Connection conn = ConnexioBD.getConnection();
        Scanner teclat = new Scanner(System.in);
//        PreparedStatement sql = conn.prepareStatement("insert into client " + "(DNI, nom, cognom, sexe, data_naix, mobil, email, usuari, contrasenya, compte_bancari)"
//                + "values "
//                + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        System.out.println("*ALTA D'UN CLIENT*");
        String dni;
        Dni dniObj = new Dni();
        String telef;
        Telefon telobj = new Telefon();
        Email corobj = new Email();
        String emai;
        CompteBancari compobj = new CompteBancari();
        String CCC;
        do {
            // solicitem el DNI a donar d'alta fins que sigui correcte
            System.out.println("Introdueix el DNI del client que vols donar d'alta: ");
            dni = teclat.next();

        } while (!dniObj.validarDNI(dni));
        dniObj.setDni(dni);
        setDni(dniObj);
        this.DNI = dniObj;
        //String consulta = String.format("select * from socis where DNI=" + "\"%s\"" + ";", dni);
        //PreparedStatement sql2 = conn.prepareStatement(consulta);
        //sql2.executeQuery();
        //ResultSet rs = sql2.executeQuery();
        if (consultaClientBD(dniObj.getDni()) != null) {
            System.out.println("El client ja existeix");
            do {
                System.out.println("Introdueix el DNI del client que vols donar d'alta: ");
                dni = teclat.next();

            } while (!dniObj.validarDNI(dni));

        } else {
            System.out.println("Introdueix el nom: ");
            this.nom = teclat.next();
            System.out.println("Introdueix el cognom: ");
            this.cognom = teclat.next();
            System.out.println("Introdueix el teu sexe: ");
            this.sexe = teclat.next();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            boolean dataCorrecta;

            do {
                dataCorrecta = true;
                System.out.println("Introdueix data naixement de forma correcta (AAAA-MM-DD)");
                try {
                    this.data_naix = LocalDate.parse(teclat.next(), formatter);
                } catch (Exception ex) {
                    dataCorrecta = false;
                }
            } while (!dataCorrecta);

            do {
                System.out.println("\nIntrodueix el telefon: ");
                telef = teclat.next();
            } while (!telobj.validarTelefon(telef));
            telobj.setTelefon(telef);

            do {
                System.out.println("Introdueix el correu electronic: ");
                emai = teclat.next();
            } while (!corobj.validaEmail(emai));
            corobj.setEmail(emai);

            System.out.println("Introdueix el teu usuari: ");
            this.usuari = teclat.next();
            System.out.println("Introdueix la teva contrasenya: ");
            this.contrasenya = teclat.next();
            System.out.println("Introdueix si tens una condicio fisica: ");
            this.condicioFisica = teclat.next();
            System.out.println("Introdueix si vols comunicacio comercial: ");
            this.comercial = teclat.nextInt();

            do {
                System.out.println("Introdueix el compte bancari (IBAN complert) ");
                CCC = teclat.next();
            } while (!compobj.validarIBAN(CCC));

            
            compobj.setIBAN(CCC);
            
            altaClientBD();


//            sql.setString(1, this.DNI.getDni());
//            sql.setString(2, this.nom);
//            sql.setString(3, this.cognom);
//            sql.setString(4, this.sexe);
//            sql.setString(5, this.data_naix.toString());
//            //sql.setDate(5, java.sql.Date.valueOf(this.data_naix));
//            sql.setString(6, this.telefon.getTelefon());
//            sql.setString(7, this.correu.getEmail());
//            sql.setString(8, this.usuari);
//            sql.setString(9, this.contrasenya);
//            sql.setString(10, this.condicioFisica);
//            sql.setInt(11, this.comercial);
//            sql.setString(12, this.compteBancari.getCompte());
            System.out.println("Client donat d'alta amb exit");

        }

    }

    private void altaClientBD() throws SQLException {
        Connection conn = ConnexioBD.getConnection();
        String query = "INSERT INTO socis (DNI, nom, cognom, sexe, data_naix, mobil, email, usuari, contrasenya, compte_bancari) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            cargarDadesDeClientEnSentencia(ps);
            ps.executeUpdate();
        } catch (Exception ex) {
            throw new RuntimeException("No s'ha pogut agregar un client\n" + this.DNI.getDni());
        }
    }

    private void cargarDadesDeClientEnSentencia(PreparedStatement ps) throws SQLException {
        ps.setString(1, this.DNI.getDni());
        ps.setString(2, this.nom);
        ps.setString(3, this.cognom);
        ps.setString(4, this.sexe);
        ps.setString(5, this.data_naix.toString());
        //ps.setDate(5, java.sql.Date.valueOf(this.data_naix));
        ps.setString(6, this.telefon.getTelefon());
        ps.setString(7, this.correu.getEmail());
        ps.setString(8, this.usuari);
        ps.setString(9, this.contrasenya);
        ps.setString(10, this.condicioFisica);
        ps.setInt(11, this.comercial);
        ps.setString(12, this.compteBancari.getCompte());
    }

    private void cargarDadesDeSentenciaEnClient(ResultSet rs) throws SQLException {
        this.setDni(new Dni(rs.getString("DNI")));
        this.setNom(rs.getString("nom"));
        this.setCognom(rs.getString("cognom"));
        this.correu = new Email(rs.getString("email"));
        this.compteBancari = new Iban(rs.getString("compte_bancari"));
        this.telefon = new Telefon(rs.getString("mobil"));

        this.setData_naix(rs.getDate("data_naix").toLocalDate());
        calcularEdad();
    }

    private void setData_naix(LocalDate localDate) {
    }

    private void setCCC(CompteBancari compteBancari2) {
    }

    private void setCognom(String string) {
    }

    private void setNom(String string) {
    }

    private void setDni(Dni dni2) {
    }

    public ArrayList<Client> getClientOrdenatsCognoms() throws SQLException {
        ArrayList<Client> clients = new ArrayList<>();
        Connection conn = ConnexioBD.getConnection();

        String consulta = "SELECT * FROM socis ORDER BY cognoms, nom";
        PreparedStatement sentencia = conn.prepareStatement(consulta);

        ResultSet rs = sentencia.executeQuery();

        while (rs.next()) {
            Client c1 = new Client();
            c1.cargarDadesDeSentenciaEnClient(rs);

            clients.add(c1);
        }
        return clients;
    }

    public ArrayList<Client> getClientOrdenatsReserves() throws SQLException {
        ArrayList<Client> clients = new ArrayList<>();

        Connection conn = ConnexioBD.getConnection();

        String consulta = "SELECT COUNT (ID) AS numRes, s. * FROM reserva_lliure, socis s";
        PreparedStatement sentencia = conn.prepareStatement(consulta);

        ResultSet rs = sentencia.executeQuery();

        while (rs.next()) {
            Client c1 = new Client();
            c1.cargarDadesDeSentenciaEnClient(rs);

            clients.add(c1);
        }
        return clients;
    }

}
