package app.MVC.modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login_DAO {
    public login_DAO(){}
    public ResultSet verifLocataire (String login, String mdp) throws SQLException {
        connexionBDD cnt = new connexionBDD(); //connexion à la BDD
        ////////////////////SQL///////////////////////
        ResultSet rs = null;
        try {
            /* Requete SQL à executée */
            String req = "SELECT * FROM locataires " +
                    "WHERE prenom = ? AND mdp = ?";

            //prepare la requete
            PreparedStatement psReq = cnt.prepareReq(req);
            psReq.setString(1, login);
            psReq.setString(2, mdp);

            //retourne la requete exécutée
            rs = psReq.executeQuery();
        } catch (Exception e) {
           return null;
        }

        return rs;
    }
    public ResultSet verifProprio (String login, String mdp) throws SQLException {
        connexionBDD cnt = new connexionBDD(); //connexion à la BDD
        ////////////////////SQL///////////////////////
        ResultSet rs = null;
        try {
            /* Requete SQL à executée */
            String req = "SELECT * FROM proprietaires WHERE proprietaires.Prenom = ? AND proprietaires.mdp = ?";

            //prepare la requete
            PreparedStatement psReq = cnt.prepareReq(req);
            psReq.setString(1, login);
            psReq.setString(2, mdp);

            //retourne la requete exécutée
            rs = psReq.executeQuery();
        } catch (Exception e) {
           e.getCause();
        }

        return rs;
    }
    public ResultSet verifClient (String login, String mdp) throws SQLException {
        connexionBDD cnt = new connexionBDD(); //connexion à la BDD
        ////////////////////SQL///////////////////////
        ResultSet rs = null;
        try {
            /* Requete SQL à executée */
            String req = "SELECT * FROM clients " +
                    "WHERE Prenom = ? AND mdp = ?";

            //prepare la requete
            PreparedStatement psReq = cnt.prepareReq(req);
            psReq.setString(1, login);
            psReq.setString(2, mdp);

            //retourne la requete exécutée
            rs = psReq.executeQuery();
        } catch (Exception e) {
            return null;
        }

        return rs;
    }
    public ResultSet verifAdmin (String login, String mdp) throws SQLException {
        connexionBDD cnt = new connexionBDD(); //connexion à la BDD
        ////////////////////SQL///////////////////////
        ResultSet rs = null;
        try {
            /* Requete SQL à executée */
            String req = "SELECT * FROM admin " +
                    "WHERE login = ? AND mdp = ?";

            //prepare la requete
            PreparedStatement psReq = cnt.prepareReq(req);
            psReq.setString(1, login);
            psReq.setString(2, mdp);

            //retourne la requete exécutée
            rs = psReq.executeQuery();
        } catch (Exception e) {
            return null;
        }

        return rs;
    }

}
