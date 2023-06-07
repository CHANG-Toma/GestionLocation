package app.MVC.view;

import app.MVC.controller.Appartement_controller;
import app.MVC.controller.clients_controller;
import app.MVC.controller.demandes_controller;
import app.MVC.controller.locataires_controller;
import app.MVC.modele.appartement_DAO;
import app.MVC.modele.clients_DAO;
import app.MVC.modele.demandes_DAO;
import app.MVC.modele.locataires_DAO;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class menu_view extends JFrame {

    private JFrame menu;
    private JPanel global;
    private JPanel panelButtons;
    private JPanel panelreponse;
    private JButton b1, b2, b3, bClientsVisites;
    private appartement_DAO appartement_dao;
    private Appartement_controller appartement_controller;
    private demandes_DAO demandes_dao;
    private demandes_controller demandes_controller;
    private locataires_DAO locataires_dao;
    private locataires_controller locataires_controller;
    private clients_DAO clients_dao;
    private clients_controller clients_controller;

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
        clients_controller clientsController = new clients_controller(this.clients_dao, this);

        ////////////Initialise les bouttons/////////////
        this.b1 = new JButton("Menu appartements");     //button appartements
        this.b2 = new JButton("Menu demandes");         //button demandes
        this.b3 = new JButton("Menu locataires / Clients");       //button locataires
        this.bClientsVisites = new JButton("Afficher les visites");       //button locataires


        ///////////Ajout du controlleur aux bouttons/////
        this.b1.addActionListener(appartementController);   //attribue un boutton à un controlleur spécifique
        this.b2.addActionListener(demandesController);      //attribue un boutton à un controlleur spécifique
        this.b3.addActionListener(locatairesController);    //attribue le boutton au controlleur locataire
        this.bClientsVisites.addActionListener(clientsController); //attribue le boutton au controlleur client
        ////////////////////ajout/////////////////
        this.add(this.global);

        this.panelButtons.add(this.b1);  //ajout button appartements
        this.panelButtons.add(this.b2);  //ajput button demandes
        this.panelButtons.add(this.b3);  //ajout button locataires
        this.panelButtons.add(this.bClientsVisites);  //ajout button locataires

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

    /////////////affiche la vue des demandes//////////////
    public void setView(String type, ResultSet rs, Integer num) throws Exception {
        this.global.removeAll();    //supprime tout pour afficher la vue spécifique
        System.out.println("Méthode setview");

        Client_view Cv = new Client_view(this);
        Proprio_view Pv = new Proprio_view(this);
        visite_view visite_view = new visite_view(this);

        switch(type){
            case "demandes":
                demandes_view d = new demandes_view(this);          //appel le menu demandes
                this.setContentPane(d);
                break;
            case "afficheAllDemandes":
                demandes_view aAD = new demandes_view(this);        //appel la vue all demandes
                aAD.afficheAllDemandes(rs);
                this.setContentPane(aAD);
                break;
            case "appart":
                Appartement_view a = new Appartement_view(this);    //appel le menu appart
                this.setContentPane(a);
                break;
            case "AjouterAppart":
                Appartement_view a2 = new Appartement_view(this);    //appel le menu appart
                a2.AjoutAppart();
                this.setContentPane(a2);
                break;
            case "afficheAppartRqst":
                Appartement_view aAA = new Appartement_view(this);  //appel la vue all appart
                aAA.afficheAppart(rs);
                this.setContentPane(aAA);
                break;
            case "locataires":
                locataires_view Lv = new locataires_view(this, rs, num);
                Lv.setBoolAffiche("locataires");
                this.setContentPane(Lv);
                break;
            case "VueModifClient":
                locataires_view Lv2 = new locataires_view(this, rs, num);
                this.setContentPane(Lv2);
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
            case "afficheAppartClient":
                demandes_view dd = new demandes_view(this);  //appel la vue all appart
                dd.afficheAppart(rs, num);
                this.setContentPane(dd);
                break;


///////////////////////////VISITE//////////////////////////////
            case "afficheLesVisites":
                System.out.println("test test test");
                visite_view.afficheAllVisite(rs);
                this.setContentPane(visite_view);
                break;
            case "Ajouter":
                visite_view.AjoutVisite();
                this.setContentPane(visite_view);
                break;
////////////////////////////////////////////////////////////////


                ///NE PAS OUBLIE DE SET L'ID CLIENT POUR CHAQUE UTILISATION --CLIENT
            case "VueClient":
                Cv.setNumCli(rs.getInt("id"));
                this.setContentPane(Cv);
                break;
            case "afficheMesDemandes":
                Cv.setNumCli(num);
                Cv.afficheMesDemandes(rs);
                this.setContentPane(Cv);
                break;
            case "afficheAppartDispo":
                Cv.setNumCli(num);
                Cv.afficheAppartDispo(rs);
                this.setContentPane(Cv);
                break;
                ////////////////PROPRIO////////////////
            case "VueProp":
                Pv.setNum_prop(rs.getInt("id"));
                this.setContentPane(Pv);
                break;
            case "afficheMesAppart":
                Pv.setNum_prop(rs.getInt("id"));
                Pv.afficheMesAppart(rs);
                this.setContentPane(Pv);
                break;
                ////////////////////////////////////////
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
