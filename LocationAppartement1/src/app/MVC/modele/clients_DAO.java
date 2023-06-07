package app.MVC.modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class clients_DAO {


    public clients_DAO(){}

    public ResultSet getAllVisites () throws SQLException {
        connexionBDD cnt = new connexionBDD(); //connexion à la BDD
        ////////////////////SQL///////////////////////
        ResultSet rs = null;
        try {
            /* Requete SQL à executée */
            String req = "SELECT * FROM visiter, clients, appartements " +
                         "WHERE visiter.numappart = appartements.numappart " +
                         "AND clients.id = visiter.idClient ";
            //prepare la requete
            PreparedStatement psReq = cnt.prepareReq(req);
            //retourne la requete exécutée
            rs = psReq.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    public ResultSet getAllClient () throws SQLException {
        connexionBDD cnt = new connexionBDD(); //connexion à la BDD
        ////////////////////SQL///////////////////////
        ResultSet rs = null;
        try {
            /* Requete SQL à executée */
            String req = "SELECT * FROM clients";
            //prepare la requete
            PreparedStatement psReq = cnt.prepareReq(req);
            //retourne la requete exécutée
            rs = psReq.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    public ResultSet getAllAppartDispo() throws Exception {
        connexionBDD l = new connexionBDD(); //connexion à la BDD
        ////////////////////SQL///////////////////////
        ResultSet rs = null;
        try {
            /* Requete SQL à executée */
            String req = "SELECT DISTINCT * FROM appartements, type_appart " +
                    "WHERE appartements.numappart NOT IN (SELECT numappart FROM occuper)";
            //prepare la requete
            PreparedStatement psReq = l.prepareReq(req);
            //retourne la requete exécutée
            rs = psReq.executeQuery();
        } catch (Exception e) {
            e.getMessage();
        }

        return rs;
    }

    public ResultSet getIdByNameClient(String Name, String prenom) throws Exception {
        connexionBDD l = new connexionBDD(); //connexion à la BDD
        ////////////////////SQL///////////////////////
        ResultSet rs = null;
        try {
            /* Requete SQL à executée */
            String req = "SELECT DISTINCT * FROM clients " +
                    "WHERE nom = ? AND Prenom = ?";
            //prepare la requete
            PreparedStatement psReq = l.prepareReq(req);
            psReq.setString(1, Name);
            psReq.setString(2, prenom);

            //retourne la requete exécutée
            rs = psReq.executeQuery();
        } catch (Exception e) {
            e.getMessage();
        }

        return rs;
    }

    public void addVisite(int numappart, int idClient, String date) throws Exception{
        connexionBDD cnt = new connexionBDD(); // Connexion à la BDD
        PreparedStatement psReq = null;
        try {
            // Requête SQL à exécuter avec des paramètres
            String req = "INSERT INTO visiter (numappart, idClient, date_visite) " +
                    "VALUES (?, ?, ?)";

            psReq = cnt.prepareReq(req);
            psReq.setInt(1, numappart);
            psReq.setInt(2, idClient);
            psReq.setString(3, date);

            // Execution de la requete
            psReq.executeUpdate(); // Utilisation de executeUpdate() au lieu de executeQuery()
        } catch (SQLException e) { // Ajout de la gestion des exceptions SQLException
            e.printStackTrace();
        }
    }

    public void deleteVisite(int idClient, int numappart, String date) throws SQLException {
        connexionBDD cnt = new connexionBDD(); // Connexion à la BDD
        PreparedStatement psReq = null;
        try {
            // Requete SQL à executer avec un paramètre
            String req = "DELETE FROM visiter " +
                    "WHERE numappart = ? " +
                    "AND idClient = ? " +
                    "AND date_visite = ?";
            psReq = cnt.prepareReq(req); // Utilisation de prepareStatement() au lieu de prepareReq()
            psReq.setInt(1, numappart);
            psReq.setInt(2, idClient);
            psReq.setString(3, date);

            // Execution de la requete
            psReq.executeUpdate(); // Utilisation de executeUpdate() au lieu de executeQuery()
        } catch (SQLException e) { // Ajout de la gestion des exceptions SQLException
            e.printStackTrace();
        }
    }



}
