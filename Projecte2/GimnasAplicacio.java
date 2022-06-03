package Projecte2;

import Projecte2.Gimnas;
import Projecte2.ConnexioBD;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class GimnasAplicacio {

    public static void main(String[] args) throws SQLException, IOException {

        Connection conn = ConnexioBD.getConnection();

        Gimnas g = new Gimnas();

        g.gestionarGimnas();

    }

}
