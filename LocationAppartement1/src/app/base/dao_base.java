package app.base;

import app.MVC.modele.connexionBDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dao_base {

    public dao_base(){}

    /////////////////////getter///////////////////////////
    public ResultSet getALLDemandes () throws SQLException {
        connexionBDD cnt = new connexionBDD(); //connexion à la BDD
        ////////////////////SQL///////////////////////
        ResultSet rs = null;
        try {
            /* Requete SQL à executée */
            String req = "SELECT * FROM demandes, avoir, type_appart " +
                    "WHERE demandes.num_dem = avoir.num_dem " +
                    "AND type_appart.id_typeAppart = avoir.id_typeAppart";
            //prepare la requete
            PreparedStatement psReq = cnt.prepareReq(req);
            //retourne la requete exécutée
            rs = psReq.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    ///////////////////////////Ajout////////////////////////
    public void AddDemande(int tailleAppart,int prix_min ,int prix_max, String villeAppart, int numClient, String date_limite) throws SQLException {
        connexionBDD cnt = new connexionBDD(); // connexion à la BDD

        String sql = "INSERT INTO demandes (num_cli, date_limite, prix_min, prix_max, taille, ville) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = cnt.prepareReq(sql);

        stmt.setInt(1, numClient);
        stmt.setString(2, date_limite);
        stmt.setInt(3, prix_min);
        stmt.setInt(4, prix_max);
        stmt.setInt(5, tailleAppart);
        stmt.setString(6, villeAppart);
        stmt.executeUpdate();
    }

    /////////////////////////DELETE//////////////////////////
    public void deleteDemande(int num_dem) throws SQLException {
        connexionBDD cnt = new connexionBDD(); // Connexion à la BDD
        PreparedStatement psReq = null;
        try {
            // Requete SQL à executer avec un paramètre
            String req = "DELETE FROM demandes where num_dem = ?";
            psReq = cnt.prepareReq(req); // Utilisation de prepareStatement() au lieu de prepareReq()
            psReq.setInt(1, num_dem);
            // Execution de la requete
            psReq.executeUpdate(); // Utilisation de executeUpdate() au lieu de executeQuery()
        } catch (SQLException e) { // Ajout de la gestion des exceptions SQLException
            e.printStackTrace();
        }
    }
}
