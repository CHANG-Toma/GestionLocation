package app.MVC.modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class appartement_DAO {

    public appartement_DAO() {}

    public ResultSet getALLAppart() throws Exception {
        connexionBDD l = new connexionBDD(); //connexion à la BDD
        ////////////////////SQL///////////////////////
        ResultSet rs = null;
        try {
            /* Requete SQL à executée */
            String req = "SELECT * " +
                    "from appartements, type_appart " +
                    "where appartements.id_typeAppart = type_appart.id_typeAppart";
            //prepare la requete
            PreparedStatement psReq = l.prepareReq(req);
            //stock la requete exécutée
            rs = psReq.executeQuery();
        } catch (Exception e) {
            e.getMessage();
        }

        return rs;
    }

    public ResultSet getTypeAppart() throws Exception {
        connexionBDD l = new connexionBDD(); //connexion à la BDD
        ////////////////////SQL///////////////////////
        ResultSet rs = null;
        try {
            /* Requete SQL à executée */
            String req = "select type from type_appart";
            //prepare la requete
            PreparedStatement psReq = l.prepareReq(req);
            //stock la requete exécutée
            rs = psReq.executeQuery();
        } catch (Exception e) {
            e.getMessage();
        }

        return rs;
    }

    public ResultSet getAppartRqst(String type, int taille, int prix, String ville) throws Exception {
        connexionBDD l = new connexionBDD();
        ResultSet rs = null;
        try {
            String req1 = "SELECT * " +
                    "FROM appartements, type_appart " +
                    "WHERE appartements.id_typeAppart = type_appart.id_typeAppart " +
                    "AND (type_appart.type = ? OR ? IS NULL ) " +
                    "AND (appartements.prix_log <= ? OR ? = 0) " +
                    "AND (appartements.taille <= ? OR ? = 0)" +
                    "AND (appartements.ville = ? OR ? IS NULL)";

                PreparedStatement psReq = l.prepareReq(req1);
                psReq.setString(1, type);
                psReq.setString(2, type);
                psReq.setInt(3, prix);
                psReq.setInt(4, prix);
                psReq.setInt(5, taille);
                psReq.setInt(6, taille);
                psReq.setString(7, ville);
                psReq.setString(8, ville);
                rs = psReq.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getAllVilleAppart() throws Exception {
        connexionBDD l = new connexionBDD(); //connexion à la BDD
        ////////////////////SQL///////////////////////
        ResultSet rs = null;
        try {
            /* Requete SQL à executée */
            String req = "select ville from appartements";
            //prepare la requete
            PreparedStatement psReq = l.prepareReq(req);
            //stock la requete exécutée
            rs = psReq.executeQuery();
        } catch (Exception e) {
            e.getMessage();
        }

        return rs;
    }

    public ResultSet getAllAppartDispo() throws Exception {
        connexionBDD l = new connexionBDD(); //connexion à la BDD
        ////////////////////SQL///////////////////////
        ResultSet rs = null;
        try {
            /* Requete SQL à executée */
            String req = "SELECT * FROM appartements, type_appart " +
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

    public ResultSet getMesAppart(int num_prop) throws Exception {
        connexionBDD l = new connexionBDD(); //connexion à la BDD
        ////////////////////SQL///////////////////////
        ResultSet rs = null;
        try {
            /* Requete SQL à executée */
            String req = "SELECT * FROM appartements, type_appart, posseder, proprietaires, arrondissement " +
                    "WHERE proprietaires.id = ? " +
                    "AND appartements.numappart = posseder.numappart " +
                    "AND proprietaires.id = posseder.id_proprietaire " +
                    "AND appartements.id_typeAppart = type_appart.id_typeAppart " +
                    "AND appartements.arrondissement_num = arrondissement.arrondissement_num";
            //prepare la requete
            PreparedStatement psReq = l.prepareReq(req);

            psReq.setInt(1, num_prop);

            //retourne la requete exécutée
            rs = psReq.executeQuery();
        } catch (Exception e) {
            e.getMessage();
        }

        return rs;
    }

    public ResultSet getAllArrondi() throws Exception {
        connexionBDD l = new connexionBDD(); //connexion à la BDD
        ////////////////////SQL///////////////////////
        ResultSet rs = null;
        try {
            /* Requete SQL à executée */
            String req = "select * from arrondissement";
            //prepare la requete
            PreparedStatement psReq = l.prepareReq(req);
            //stock la requete exécutée
            rs = psReq.executeQuery();
        } catch (Exception e) {
            e.getMessage();
        }

        return rs;
    }
    public ResultSet getAllDepartement() throws Exception {
        connexionBDD l = new connexionBDD(); //connexion à la BDD
        ////////////////////SQL///////////////////////
        ResultSet rs = null;
        try {
            /* Requete SQL à executée */
            String req = "SELECT DISTINCT * from appartements";
            //prepare la requete
            PreparedStatement psReq = l.prepareReq(req);
            //stock la requete exécutée
            rs = psReq.executeQuery();
        } catch (Exception e) {
            e.getMessage();
        }

        return rs;
    }


    public void addAppartement(int rue, String Ville, int cp, int etage, int prix_log, int prix_charg, int ascenseur, int preavis, String date_libre, int idType, int num_arrondi, int taille) throws Exception {
        connexionBDD l = new connexionBDD(); //connexion à la BDD
        ////////////////////SQL///////////////////////
        try {
            /* Requete SQL à executée */
            String req = "INSERT INTO appartements (rue, ville, CP, etage, prix_log, prix_charg, ascenseur, preavis, date_libre, id_typeAppart, arrondissement_num, taille) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            //prepare la requete
            PreparedStatement psReq = l.prepareReq(req);

            psReq.setInt(1, rue);
            psReq.setString(2, Ville);
            psReq.setInt(3, cp);
            psReq.setInt(4, etage);
            psReq.setInt(5, prix_log);
            psReq.setInt(6, prix_charg);
            psReq.setInt(7, ascenseur);
            psReq.setInt(8, preavis);
            psReq.setString(9, date_libre);
            psReq.setInt(10, idType);
            psReq.setInt(11, num_arrondi);
            psReq.setInt(12, taille);
            //stock la requete exécutée
            psReq.executeUpdate();
        } catch (Exception e) {
            e.getMessage();
        }
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

}

