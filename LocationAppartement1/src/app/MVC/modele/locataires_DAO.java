package app.MVC.modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class locataires_DAO {

    public locataires_DAO(){}
    public ResultSet getAllLocataires () throws Exception {
        connexionBDD l = new connexionBDD(); //connexion à la BDD
        ////////////////////SQL///////////////////////
        ResultSet rs = null;
        try {
            /* Requete SQL à executée */
            String req = "select * from locataires";
            //prepare la requete
            PreparedStatement psReq = l.prepareReq(req);
            //retourne la requete exécutée
            rs = psReq.executeQuery();
        } catch (Exception e) {
            e.getMessage();
        }

        return rs;
    }
    public ResultSet getAllAppartements () throws Exception {
        connexionBDD l = new connexionBDD(); //connexion à la BDD
        ////////////////////SQL///////////////////////
        ResultSet rs = null;
        try {
            /* Requete SQL à executée */
            String req = "SELECT * FROM appartements, type_appart WHERE appartements.numappart NOT IN (SELECT numappart FROM occuper)";
            //prepare la requete
            PreparedStatement psReq = l.prepareReq(req);
            //retourne la requete exécutée
            rs = psReq.executeQuery();
        } catch (Exception e) {
            e.getMessage();
        }

        return rs;
    }
    public ResultSet getAllClients () throws Exception {
        connexionBDD l = new connexionBDD(); //connexion à la BDD
        ////////////////////SQL///////////////////////
        ResultSet rs = null;
        try {
            /* Requete SQL à executée */
            String req = "select * from clients";
            //prepare la requete
            PreparedStatement psReq = l.prepareReq(req);
            //retourne la requete exécutée
            rs = psReq.executeQuery();
        } catch (Exception e) {
            e.getMessage();
        }

        return rs;
    }
    public ResultSet getAllClientById (int id) throws Exception {
        connexionBDD l = new connexionBDD(); //connexion à la BDD
        ////////////////////SQL///////////////////////
        ResultSet rs = null;
        try {
            /* Requete SQL à executée */
            String req = "select * from clients where id = ?";
            //prepare la requete
            PreparedStatement psReq = l.prepareReq(req);
            psReq.setInt(1, id);

            //retourne la requete exécutée
            rs = psReq.executeQuery();
        } catch (Exception e) {
            e.getMessage();
        }

        return rs;
    }
    public void addClient(String nom, String Prenom, String adresse, String Vile, int CP, int tel, String DateNaiss, int rib, String mdp) throws Exception{
        connexionBDD cnt = new connexionBDD(); // Connexion à la BDD
        PreparedStatement psReq = null;
        try {
            // Requête SQL à exécuter avec des paramètres
            String req = "INSERT INTO clients (nom, Prenom, adresse, Ville, CP, tel, DateNaiss, RIB, mdp) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            psReq = cnt.prepareReq(req);
            psReq.setString(1, nom);
            psReq.setString(2, Prenom);
            psReq.setString(3, adresse);
            psReq.setString(4, Vile);
            psReq.setInt(5, CP);
            psReq.setInt(6, tel);
            psReq.setString(7, DateNaiss);
            psReq.setInt(8, rib);
            psReq.setString(9, mdp);

            // Execution de la requete
            psReq.executeUpdate(); // Utilisation de executeUpdate() au lieu de executeQuery()
        } catch (SQLException e) { // Ajout de la gestion des exceptions SQLException
            e.printStackTrace();
        }
    }
    public void updateClient(String nom, String prenom, String adresse, String ville, int cp, int tel, String dateNaiss, int rib, String mdp, int id) throws SQLException {
        connexionBDD cnt = new connexionBDD(); // Connexion à la BDD
        PreparedStatement psReq = null;
        try {
            // Requête SQL à exécuter avec des paramètres
            String req = "UPDATE clients SET nom = ?, Prenom = ?, adresse = ?, Ville = ?, CP = ?, tel = ?, DateNaiss = ?, RIB = ?, mdp = ? WHERE id = ?";

            psReq = cnt.prepareReq(req);
            psReq.setString(1, nom);
            psReq.setString(2, prenom);
            psReq.setString(3, adresse);
            psReq.setString(4, ville);
            psReq.setInt(5, cp);
            psReq.setInt(6, tel);
            psReq.setString(7, dateNaiss);
            psReq.setInt(8, rib);
            psReq.setString(9, mdp);
            psReq.setInt(10, id);

            // Execution de la requete
            psReq.executeUpdate(); // Utilisation de executeUpdate() au lieu de executeQuery()
        } finally {
            // Fermeture des ressources JDBC dans un bloc finally
            if (psReq != null) {
                psReq.close();
            }
        }
    }

    public void DeleteClient(int id) throws SQLException {
        connexionBDD cnt = new connexionBDD(); // Connexion à la BDD
        PreparedStatement psReq = null;
        try {
            // Requête SQL à exécuter avec des paramètres
            String req = "DELETE FROM clients WHERE id = ?";

            psReq = cnt.prepareReq(req);
            psReq.setInt(1, id);

            // Execution de la requete
            psReq.executeUpdate(); // Utilisation de executeUpdate() au lieu de executeQuery()
        } finally {
            // Fermeture des ressources JDBC dans un bloc finally
            if (psReq != null) {
                psReq.close();
            }
        }
    }

}
