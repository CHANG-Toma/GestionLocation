package app.MVC.view;

import app.MVC.controller.Appartement_controller;
import app.MVC.controller.demandes_controller;
import app.MVC.modele.appartement_DAO;
import app.MVC.modele.demandes_DAO;
import app.MVC.modele.locataires_DAO;
import app.MVC.controller.locataires_controller;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class menu_view extends JFrame {

    private JFrame menu;
    private JPanel global,panelButtons,panelreponse;
    private JButton b1, b2, b3;
    private appartement_DAO appartement_dao;
    private Appartement_controller appartement_controller;
    private demandes_DAO demandes_dao;
    private demandes_controller demandes_controller;
    private locataires_DAO locataires_dao;
    private locataires_controller locataires_controller;

    public menu_view() {
        //////////////////Instance/////////////////
        this.menu = new JFrame("Location appartement"); //fenêtre principale
        this.global = new JPanel();
        this.panelButtons = new JPanel();
        this.panelreponse = new JPanel();

        ////////////////Controller////////////////////
        Appartement_controller appartementController = new Appartement_controller(this.appartement_dao, this);
        demandes_controller demandesController = new demandes_controller(this.demandes_dao, this);
        locataires_controller locatairesController = new locataires_controller(this.locataires_dao, this);

        ////////////Initialise les bouttons/////////////
        this.b1 = new JButton("Menu appartements");     //button appartements
        this.b2 = new JButton("Menu demandes");         //button demandes
        this.b3 = new JButton("Menu locataires / Clients");       //button locataires

        ///////////Ajout du controlleur aux bouttons/////
        this.b1.addActionListener(appartementController);   //attribue un boutton à un controlleur spécifique
        this.b2.addActionListener(demandesController);      //attribue un boutton à un controlleur spécifique
        this.b3.addActionListener(locatairesController);    //attribue le boutton au controlleur locataire

        ////////////////////ajout/////////////////
        this.add(this.global);

        this.panelButtons.add(this.b1);  //ajout button appartements
        this.panelButtons.add(this.b2);  //ajput button demandes
        this.panelButtons.add(this.b3);  //ajout button locataires

        this.global.add(this.panelButtons,BorderLayout.PAGE_START); //interface des bouttons
        this.global.add(this.panelreponse,BorderLayout.PAGE_END);     //interface response

        ////////////////setters///////////////////
        this.panelButtons.setBounds(0,0,1500,50);   //panel des bouttons
        this.panelreponse.setBounds(0,50,1500,950); //panel response

        this.panelButtons.setBackground(Color.DARK_GRAY);    //couleur du background des bouttons
        this.panelreponse.setBackground(Color.GRAY);         //couleur background des responses

        this.global.setLayout(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1500, 1500);
        this.setResizable(true);
        //this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /////////////affiche la vue//////////////
    public void setView(String type, ResultSet rs, Integer num) throws Exception {
        this.global.removeAll();    //supprime tout pour afficher la vue spécifique

        System.out.println("Méthode setview");

        Client_view Cv = new Client_view(this); //instance
        Proprio_view Pv = new Proprio_view(this);   //instance

        switch(type){
            case "demandes":
                demandes_view d = new demandes_view(this);
                this.setContentPane(d);
                break;
            case "afficheAllDemandes":
                demandes_view aAD = new demandes_view(this);
                aAD.afficheAllDemandes(rs);//appel la vue all demandes
                this.setContentPane(aAD);
                break;
            case "afficheMesDemandes":
                Cv.setNumCli(num);
                Cv.afficheMesDemandes(rs);
                this.setContentPane(Cv);
                break;
                //_______________________________________________________________________________
            case "appart":
                Appartement_view a = new Appartement_view(this);    //appel le menu appart
                this.setContentPane(a);
                break;
            case "AjouterAppart":
                Appartement_view a2 = new Appartement_view(this);    //appel le menu appart
                a2.AjoutAppart(rs);
                this.setContentPane(a2);
                break;
            case "afficheAppartRqst":
                Appartement_view aAA = new Appartement_view(this);  //appel la vue all appart
                aAA.afficheAppart(rs);
                this.setContentPane(aAA);
                break;
            case "afficheAppartClient":
                demandes_view dd = new demandes_view(this);  //appel la vue all appart
                dd.afficheAppart(rs, num);
                this.setContentPane(dd);
                break;
            ///NE PAS OUBLIE DE SET L'ID CLIENT POUR CHAQUE UTILISATION --CLIENT
            case "afficheAppartDispo":
                Cv.setNumCli(num);
                Cv.afficheAppartDispo(rs);
                this.setContentPane(Cv);
                break;
            case "afficheMesAppart":
                Pv.setNum_prop(rs.getInt("id"));
                Pv.afficheMesAppart(rs);
                this.setContentPane(Pv);
                break;
                //__________________________________________________________________________
            case "locataires", "VueModifClient":
                locataires_view Lv = new locataires_view(this, rs, num);
                this.setContentPane(Lv);
                break;
            case "afficheAllLocataires":
                locataires_view Lv3 = new locataires_view(this, rs, num);
                Lv3.afficheAllLocataires(rs);
                this.setContentPane(Lv3);
                break;
            case "afficheClients":
                locataires_view Lv4 = new locataires_view(this, rs, num);
                Lv4.afficheAllClients(rs);
                this.setContentPane(Lv4);
                break;
            case "VueClient":
                Cv.setNumCli(rs.getInt("id"));
                this.setContentPane(Cv);
                break;
                //__________________________________________________________________________
            case "VueajouteVisite":
                locataires_view Lv5 = new locataires_view(this, null, num);
                Lv5.afficheNewVisite(rs, num);
                this.setContentPane(Lv5);
                break;
            case "AjouterVisite":
                locataires_view Lv6 = new locataires_view(this, rs, num);
                Lv6.afficheNewVisite(rs, num);
                this.setContentPane(Lv6);
                break;
            case "afficheMesVisites":
                Cv.setNumCli(num);
                Cv.afficheMesVisites(rs);
                this.setContentPane(Cv);
                break;
                ////////////////PROPRIO////////////////
            case "VueProp":
                Pv.setNum_prop(rs.getInt("id"));
                this.setContentPane(Pv);
                break;
        }
        this.validate(); //valide
        this.repaint(); //actualise
    }

    /////////////boutton retour///////////////
    public void retour(){
        this.global.removeAll();
        this.global.add(this.panelreponse);
        this.global.add(this.panelButtons);
        this.setContentPane(this.global);
        this.validate();
        this.repaint();
    }
}
