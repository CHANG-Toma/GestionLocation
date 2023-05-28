package app.MVC.view;

import app.MVC.controller.Appartement_controller;
import app.MVC.controller.demandes_controller;
import app.MVC.modele.appartement_DAO;
import app.MVC.modele.demandes_DAO;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Proprio_view extends JPanel{

    private JButton b1,b2;
    private demandes_DAO m;
    private appartement_DAO appartement_dao;
    private JPanel panelButtons,panelLabel,global;
    private demandes_controller demandes_controller;
    private Appartement_controller appartement_controller;
    private JTextField prixMax,prixMin,taille,dateDem;
    private JList<String> Ville,type,arrondissement;
    private int num_prop;

    public Proprio_view(menu_view menu)   {
        //////////////////Instance///////////////
        this.panelButtons = new JPanel();
        this.panelLabel = new JPanel();
        this.global = new JPanel();

        this.m = new demandes_DAO();
        this.appartement_dao = new appartement_DAO();
        this.appartement_controller = new Appartement_controller(this.appartement_dao, menu);

        this.b1 = new JButton("Afficher mes appartements");

        ////////////appel des controllers////////
        this.b1.addActionListener(this.appartement_controller);

        this.b1.addActionListener(e -> {
            this.appartement_controller.setNum_prop(this.num_prop);
        });

        //////////////////ajout//////////////////
        this.global.setLayout(new BorderLayout());
        this.panelButtons.setBackground(Color.DARK_GRAY);   //couleur du panel button
        this.panelLabel.setBackground(Color.GRAY);  //couleur du panel label

        this.panelButtons.setSize(1000, 1000); //setter de la taille

        this.global.add(this.panelButtons, BorderLayout.PAGE_START); //affichage du haut, ca permettra de pas supprimer la vue
        this.global.add(this.panelLabel, BorderLayout.PAGE_END);    //affichage du bas, ca permettra de pas supprimer la vue

        this.panelButtons.add(this.b1); //ajout des button à leurs panels

        this.add(this.global);
    }

    ///////////////////////Affichage proposé///////////////////////
    public void afficheMesAppart(ResultSet rs) throws SQLException {

        // Création du JPanel qui contiendra tous les panneaux de demandes
        JPanel AppartementPanel = new JPanel();
        AppartementPanel.setLayout(new BoxLayout(AppartementPanel, BoxLayout.PAGE_AXIS));

        do {
            // Création du panneau de demande
            JPanel panel = new JPanel();
            panel.add(new JLabel("\n"));
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

            // Ajout des informations de la demande au panneau
            panel.add(new JLabel("Prix location : " + rs.getString("prix_log") + "\n"));
            panel.add(new JLabel("Rue : " + rs.getString("rue") + "\n"));
            panel.add(new JLabel("Ville : " + rs.getString("ville") + "\n"));
            panel.add(new JLabel("CP : " + rs.getString("CP") + "\n"));
            panel.add(new JLabel("Arrondissement : " + rs.getString("libelle") + "\n"));
            panel.add(new JLabel("Prix charge : " + rs.getString("prix_charg") + "\n"));
            panel.add(new JLabel("taille : " + rs.getString("taille") + "m2 \n"));
            panel.add(new JLabel("Type : " + rs.getString("type") + "\n"));
            panel.add(new JLabel("Ascenseur : " + rs.getString("ascenseur") + "\n"));


            // Ajout du panneau de demande au panneau conteneur
            AppartementPanel.add(panel);
        }
        while ((rs.next()));

        // Création du JScrollPane pour le panneau conteneur
        JScrollPane scrollPane = new JScrollPane(AppartementPanel);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        this.global.add(scrollPane, BorderLayout.CENTER);
        this.global.validate();
        this.global.repaint();
    }

    /////////////////////////SETTERS/////////////////////////////
    public void setNum_prop(int num){this.num_prop = num;}

}

