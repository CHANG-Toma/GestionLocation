package app.MVC.modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class demandes_DAO {
    public demandes_DAO() {};

    public ResultSet getALLDemandes () throws SQLException {
        connexionBDD cnt = new connexionBDD(); //connexion à la BDD
        ////////////////////SQL///////////////////////
        ResultSet rs = null;
        try {
            /* Requete SQL à executée */
            String req = "SELECT * FROM demandes, type_appart " +
                    "WHERE demandes.idType = type_appart.id_typeAppart";
            //prepare la requete
            PreparedStatement psReq = cnt.prepareReq(req);
            //retourne la requete exécutée
            rs = psReq.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    public ResultSet getAllByNum_Dem(int num_dem) throws SQLException {
        connexionBDD cnt = new connexionBDD(); // connexion à la BDD
        PreparedStatement psReq = null;
        ResultSet rs = null;

        try {
            // Requete SQL à executer avec un paramètre
            String req = "SELECT * FROM demandes, type_appart " +
                         "WHERE num_dem = ?";
            psReq = cnt.prepareReq(req);

            // Définition du paramètre
            psReq.setInt(1, num_dem);

            // Execution de la requete
            rs = psReq.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet getAllDemnByIdCli(int num_cli) throws SQLException {
        connexionBDD cnt = new connexionBDD(); // connexion à la BDD
        PreparedStatement psReq = null;
        ResultSet rs = null;

        try {
            // Requete SQL à executer avec un paramètre
            String req = "SELECT * FROM demandes WHERE num_cli = ?";
            psReq = cnt.prepareReq(req);

            // Définition du paramètre
            psReq.setInt(1, num_cli);

            // Execution de la requete
            rs = psReq.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet getAllVisiteByIdCli(int num_cli) throws SQLException {
        connexionBDD cnt = new connexionBDD(); // connexion à la BDD
        PreparedStatement psReq = null;
        ResultSet rs = null;

        try {
            // Requete SQL à executer avec un paramètre
            String req = "SELECT * " +
                    "FROM visiter, appartements, type_appart, arrondissement " +
                    "WHERE visiter.idClient = ? " +
                    "AND visiter.numappart = appartements.numappart " +
                    "AND appartements.id_typeAppart = type_appart.id_typeAppart " +
                    "AND appartements.arrondissement_num = arrondissement.arrondissement_num";
            psReq = cnt.prepareReq(req);

            // Définition du paramètre
            psReq.setInt(1, num_cli);

            // Execution de la requete
            rs = psReq.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet getClient() throws SQLException{
        connexionBDD cnt = new connexionBDD(); // connexion à la BDD
        PreparedStatement psReq = null;
        ResultSet rs = null;
        try {
            // Requete SQL à executer avec un paramètre
            String req = "select * from clients";
            psReq = cnt.prepareReq(req);
            // Execution de la requete
            rs = psReq.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet getLibelleArrondi() throws SQLException{
        connexionBDD cnt = new connexionBDD(); // connexion à la BDD
        PreparedStatement psReq = null;
        ResultSet rs = null;
        try {
            // Requete SQL à executer avec un paramètre
            String req = "select libelle from arrondissement";
            psReq = cnt.prepareReq(req);
            // Execution de la requete
            rs = psReq.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet getNumClientByNomPrenom(String nom, String prenom) throws SQLException{
        connexionBDD cnt = new connexionBDD(); // connexion à la BDD
        PreparedStatement psReq = null;
        ResultSet rs = null;
        try {
            // Requete SQL à executer avec un paramètre
            String req = "select id from clients where nom = ? AND Prenom = ?";
            psReq = cnt.prepareReq(req);

            psReq.setString(1, nom);
            psReq.setString(2, prenom);

            // Execution de la requete
            rs = psReq.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet getIdtypeByType(String Type) throws SQLException {
        connexionBDD cnt = new connexionBDD(); // connexion à la BDD
        PreparedStatement psReq = null;
        ResultSet rs = null;
        try {
            // Requete SQL à executer avec un paramètre
            String req = "select id_typeAppart from type_appart where type = ?";
            psReq = cnt.prepareReq(req);

            psReq.setString(1, Type);

            // Execution de la requete
            rs = psReq.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet getIdArronByArron(String arrondi) throws SQLException {
        connexionBDD cnt = new connexionBDD(); // connexion à la BDD
        PreparedStatement psReq = null;
        ResultSet rs = null;
        try {
            // Requete SQL à executer avec un paramètre
            String req = "select arrondissement_num from arrondissement where libelle = ?";
            psReq = cnt.prepareReq(req);

            psReq.setString(1, arrondi);

            // Execution de la requete
            rs = psReq.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet getAllClientsByNum(int numcli) throws SQLException{
        connexionBDD cnt = new connexionBDD(); // connexion à la BDD
        PreparedStatement psReq = null;
        ResultSet rs = null;
        try {
            // Requete SQL à executer avec un paramètre
            String req = "SELECT * FROM clients WHERE id = ?";

            psReq = cnt.prepareReq(req);
            psReq.setInt(1, numcli);

            // Execution de la requete
            rs = psReq.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


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

    public void deleteVisite(int num_cli, int num_Appart, String date) throws SQLException {
        connexionBDD cnt = new connexionBDD(); // Connexion à la BDD
        PreparedStatement psReq = null;
        try {
            // Requete SQL à executer avec un paramètre
            String req = "DELETE FROM `visiter` WHERE numappart = ? AND idClient = ? AND date_visite = ?";

            psReq = cnt.prepareReq(req); // Utilisation de prepareStatement() au lieu de prepareReq()
            psReq.setInt(1, num_Appart);
            psReq.setInt(2, num_cli);
            psReq.setString(3, date);

            // Execution de la requete
            psReq.executeUpdate(); // Utilisation de executeUpdate() au lieu de executeQuery()
        } catch (SQLException e) { // Ajout de la gestion des exceptions SQLException
            e.printStackTrace();
        }
    }

    public void deleteClient(int num_Cli) throws SQLException {
        connexionBDD cnt = new connexionBDD(); // Connexion à la BDD
        PreparedStatement psReq = null;
        try {
            // Requete SQL à executer avec un paramètre
            String req = "DELETE FROM clients where id = ?";
            psReq = cnt.prepareReq(req); // Utilisation de prepareStatement() au lieu de prepareReq()
            psReq.setInt(1, num_Cli);
            // Execution de la requete
            psReq.executeUpdate(); // Utilisation de executeUpdate() au lieu de executeQuery()
        } catch (SQLException e) { // Ajout de la gestion des exceptions SQLException
            e.printStackTrace();
        }
    }

    public void addDemande(int tailleAppart, int prix_min, int prix_max, String villeAppart, int numClient, String date_limite, int numType, int numArrondi, String jardin, int superficie, String piscine, String garage, String region, String departement) throws SQLException {
        connexionBDD cnt = new connexionBDD(); // Connexion à la BDD

        String sql = "INSERT INTO demandes (num_cli, date_limite, prix_min, prix_max, taille, ville, idType, idArrondi, jardin, superficie, piscine, garage, region, departement) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = cnt.prepareReq(sql);

        stmt.setInt(1, numClient);
        stmt.setString(2, date_limite);
        stmt.setInt(3, prix_min);
        stmt.setInt(4, prix_max);
        stmt.setInt(5, tailleAppart);
        stmt.setString(6, villeAppart);
        stmt.setInt(7, numType);
        stmt.setInt(8, numArrondi);

        stmt.setString(9, jardin);
        stmt.setInt(10, superficie);
        stmt.setString(11, piscine);
        stmt.setString(12, garage);
        stmt.setString(13, region);
        stmt.setString(14, departement);

        stmt.executeUpdate();
    }

    public void addLocataire(String nom, String prenom, String dateNaiss, int tel, int rib, String mdp) throws SQLException{
        connexionBDD cnt = new connexionBDD(); // Connexion à la BDD
        PreparedStatement psReq = null;
        try {
            // Requête SQL à exécuter avec des paramètres
            String req = "INSERT INTO locataires (nom, prenom, dateNaissance, tel, RIB, mdp) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            psReq = cnt.prepareReq(req); // Utilisation de prepareStatement() au lieu de prepareReq()
            psReq.setString(1, nom);
            psReq.setString(2, prenom);
            psReq.setString(3, dateNaiss);
            psReq.setInt(4, tel);
            psReq.setInt(5, rib);
            psReq.setString(6, mdp);

            // Execution de la requete
            psReq.executeUpdate(); // Utilisation de executeUpdate() au lieu de executeQuery()
        } catch (SQLException e) { // Ajout de la gestion des exceptions SQLException
            e.printStackTrace();
        }
    }

}
