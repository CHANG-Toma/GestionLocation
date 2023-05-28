package app.MVC.controller;

import app.MVC.modele.demandes_DAO;
import app.MVC.modele.login_DAO;
import app.MVC.view.login_view;
import app.MVC.view.menu_view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class login_controller implements ActionListener {

    private login_DAO login_dao;
    private login_view loginView;
    private menu_view menu_view;
    private demandes_DAO demandes;
    private String login;
    private String mdp;
    private String type;
    private demandes_controller demandes_controller;

    public login_controller(menu_view v){
        this.login_dao = new login_DAO();
        this.menu_view = v;
        this.demandes = new demandes_DAO();
    }

    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()){
            case "Login" ->{
                try {

                    System.out.println("LOGIN FORM");
                    System.out.println(this.login);
                    System.out.println(this.mdp);

                    if(this.type == "Propriétaire"){
                        //accés la vue locataires avec ces fonctions et ses appartements
                        ResultSet rs = this.login_dao.verifProprio(this.login, this.mdp);
                        if (rs.next()){
                            this.menu_view = new menu_view();
                            this.menu_view.setView("VueProp",rs, null); //redirection
                        }
                    }

                    else if(this.type == "Client"){
                        //accés aux demandes + faire une nouvelle demande
                            ResultSet rs = this.login_dao.verifClient(this.login, this.mdp);
                            if (rs.next()){
                                this.menu_view = new menu_view();
                                this.menu_view.setView("VueClient",rs, null);
                            }
                    }
                    else if(this.type == "admin"){
                        //accés à tout l'ensemble
                        ResultSet rs = this.login_dao.verifAdmin(this.login, this.mdp);
                        if (rs.next()){
                            this.menu_view = new menu_view();   //vue globale
                        }
                    }

                    System.out.println(this.type + " connecté");

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    public void setLogin(String login){this.login = login;}
    public void setMdp(String mdp){this.mdp = mdp;}

    public void setType(String type){this.type = type;}
}
