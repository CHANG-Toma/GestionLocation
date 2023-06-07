package app.MVC.modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
Voici la classe à remplir avant de lancer les classes, avec user, mot de passe et ainsi que l'url
 */
public class connexionBDD {

    private String user = "root";
    private String mdp =  "";
    private Connection cnt;
    private String url = "jdbc:mysql://localhost:3306/appartement";

    ///////////////////constructeur/////////////////////////
    public connexionBDD() throws SQLException {
        this.cnt= DriverManager.getConnection(url, user, mdp);
    }

    //////////////////prepare la requete////////////////////
    public PreparedStatement prepareReq(String req) throws SQLException {
        return this.cnt.prepareStatement(req);
    }

}
