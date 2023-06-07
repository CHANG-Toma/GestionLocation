package app.MVC.view;

import app.MVC.controller.Appartement_controller;
import app.MVC.controller.demandes_controller;
import app.MVC.modele.appartement_DAO;
import app.MVC.modele.demandes_DAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Client_view extends JPanel{

    private JButton b1,b2,btnNewDemd;
    private demandes_DAO m;
    private appartement_DAO appartement_dao;
    private JPanel panelButtons;
    private JPanel panelLabel;
    private JPanel global;
    private demandes_controller demandes_controller;
    private Appartement_controller appartement_controller;
    private JTextField prixMax;
    private JTextField prixMin;
    private JTextField taille;
    private JList<String> Ville;
    private JList<String> type;
    private JTextField dateDem;
    private int numCli;
    private JList<String> arrondissement, departement;

    public Client_view(menu_view menu) throws Exception {
        //////////////////Instance///////////////
        this.panelButtons = new JPanel();
        this.panelLabel = new JPanel();
        this.global = new JPanel();

        this.m = new demandes_DAO();
        this.appartement_dao = new appartement_DAO();
        this.demandes_controller = new demandes_controller(m, menu);
        this.appartement_controller = new Appartement_controller(this.appartement_dao, menu);

        StringBuilder villeTab = this.getVille(this.demandes_controller.getVilleDispo()); //appelle la fonction private de cette classe
        StringBuilder typeTab = this.getTypesAppart(this.demandes_controller.getType());
        StringBuilder arrondissement = this.getArrondissement(this.demandes_controller.getAllArrondissment());
        StringBuilder departement = this.getDepartement(this.demandes_controller.getAlldepartement());

        this.prixMin = new JTextField(10);
        this.prixMax = new JTextField(10);
        this.taille = new JTextField(10);
        this.dateDem = new JTextField(10);

        this.Ville = new JList<>(villeTab.toString().split("\n"));
        this.type = new JList<>(typeTab.toString().split("\n"));
        this.arrondissement = new JList<>(arrondissement.toString().split("\n"));
        this.departement = new JList<>(departement.toString().split("\n"));

        this.b1 = new JButton("Afficher les appartements disponibles");
        this.b2 = new JButton("Afficher mes demandes");
        this.btnNewDemd = new JButton("Ajouter");

        ////////////appel des controllers////////
        this.b1.addActionListener(this.appartement_controller);
        this.b2.addActionListener(this.demandes_controller);
        this.btnNewDemd.addActionListener(this.demandes_controller);

        this.panelLabel.add(new JLabel("Taille :"));
        this.panelLabel.add(this.taille);

        this.panelLabel.add(new JLabel("Prix min :"));
        this.panelLabel.add(this.prixMin);

        this.panelLabel.add(new JLabel("Prix max :"));
        this.panelLabel.add(this.prixMax);

        this.panelLabel.add(new JLabel("Ville :"));
        this.panelLabel.add(this.Ville);

        this.panelLabel.add(new JLabel("Type: "));  //champ de séléction
        this.panelLabel.add(this.type);

        this.panelLabel.add(new JLabel("Arrondissement : "));  //champ de séléction
        this.panelLabel.add(this.arrondissement);

        this.panelLabel.add(new JLabel("Département : "));  //champ de séléction
        this.panelLabel.add(this.departement);

        this.panelLabel.add(new JLabel("Date limite (YYYY-MM-DD) : "));
        this.panelLabel.add(this.dateDem);

        this.prixMax.setText("0");
        this.taille.setText("0");
        this.prixMin.setText("0");

        this.b1.addActionListener(e -> {
            this.appartement_controller.setNum_cli(this.numCli);
        });
        this.b2.addActionListener(e -> {
            this.demandes_controller.setNum_Cli(this.numCli);
        });
        this.btnNewDemd.addActionListener(e -> {
            this.demandes_controller.setNum_Cli(this.numCli);

            String Arrondissement = this.arrondissement.getSelectedValue();
            demandes_controller.setArrondissement(Arrondissement);

            String dprt = this.departement.getSelectedValue();
            demandes_controller.setDepartement(dprt);

            String ville = this.Ville.getSelectedValue(); //donne la valeur sélectionnée | null sinon
            demandes_controller.setVilleAppart(ville); //setters de la ville, pour actualiser la variable

            String type = this.type.getSelectedValue(); //donne la valeur sélectionnée | null sinon
            this.demandes_controller.setTypeAppart(type);   //setters du type

            int prixmin = Integer.parseInt(this.prixMin.getText());    //passe de string à int et attribue le prix
            this.demandes_controller.setPrix_min(prixmin); //setters

            int prix = Integer.parseInt(this.prixMax.getText());    //passe de string à int et attribue le prix
            this.demandes_controller.setPrix_max(prix); //setters

            int taille = Integer.parseInt(this.taille.getText());   //passe du string a int et attribue la taille
            this.demandes_controller.setTailleAppart(taille); //setters

            String date = this.dateDem.getText();
            this.demandes_controller.setDateDem(date);

        });

        //////////////////ajout//////////////////
        this.global.setLayout(new BorderLayout());
        this.panelButtons.setBackground(Color.DARK_GRAY);   //couleur du panel button
        this.panelLabel.setBackground(Color.GRAY);  //couleur du panel label

        this.panelButtons.setSize(1000, 1000); //setter de la taille

        this.global.add(this.panelButtons, BorderLayout.PAGE_START); //affichage du haut, ca permettra de pas supprimer la vue
        this.global.add(this.panelLabel, BorderLayout.PAGE_END);    //affichage du bas, ca permettra de pas supprimer la vue

        this.panelButtons.add(this.b1); //ajout des button à leurs panels
        this.panelButtons.add(this.b2); //ajout des button à leurs panels
        this.panelLabel.add(this.btnNewDemd);

        this.add(this.global);
    }


    ///////////////////////Affichage proposé///////////////////////
    public void afficheMesDemandes(ResultSet rs) throws SQLException {

        // Création du JPanel qui contiendra tous les panneaux de demandes
        JPanel demandePanel = new JPanel();
        demandePanel.setLayout(new BoxLayout(demandePanel, BoxLayout.PAGE_AXIS));

        do {
            // Création du panneau de demande
            JPanel panel = new JPanel();
            panel.add(new JLabel("\n"));
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

            // Récupération du numéro de demande pour le bouton de recherche
            int numDem = rs.getInt("num_dem");
            int prix_max = rs.getInt("prix_max");
            int taille = rs.getInt("taille");
            String ville = rs.getString("ville");

            // Ajout des informations de la demande au panneau
            panel.add(new JLabel("Numéro demande : " + numDem));
            panel.add(new JLabel("Numéro client : " + rs.getString("num_cli")));
            panel.add(new JLabel("Prix min : " + rs.getInt("prix_min")));
            panel.add(new JLabel("Prix max : " + prix_max));
            panel.add(new JLabel("Taille : " + taille));
            panel.add(new JLabel("Ville : " + ville));
            panel.add(new JLabel("Date : " + rs.getString("date_limite")));

            // Création du bouton de recherche
            JButton deleteButton = new JButton("Supprimer");

            deleteButton.addActionListener(this.demandes_controller);   //appelle du controleur spécifique

            deleteButton.addActionListener((ActionEvent e) -> {
                this.demandes_controller.setNum_dem(numDem);
            });

            // Ajout du panneau de demande au panneau conteneur
            demandePanel.add(panel);
            // Ajout du bouton de recherche au panneau
            panel.add(deleteButton);
        }
        while ((rs.next()));

        // Création du JScrollPane pour le panneau conteneur
        JScrollPane scrollPane = new JScrollPane(demandePanel);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        this.global.add(scrollPane, BorderLayout.CENTER);
        this.global.validate();
        this.global.repaint();
    }
    public void afficheAppartDispo(ResultSet rs) throws SQLException {

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
            panel.add(new JLabel("Ville : " + rs.getString("ville") + "\n"));
            panel.add(new JLabel("taille : " + rs.getString("taille") + "\n"));
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

    /////////////////////////GETTERS//////////////////////////////
    private StringBuilder getTypesAppart(ResultSet rs) throws SQLException{ //pour le champ de sélection
        JTextArea textArea = new JTextArea(); //champ de texte
        textArea.setEditable(false);    //non modifiable
        StringBuilder sb = new StringBuilder(); //création de la chaine

        if(rs != null){
            //System.out.println("affiche type demandé"); //jeu de test
            while (rs.next()) { //parcours du résultat via une boucle
                sb.append(rs.getString("type") + "\n");
            }
            textArea.setText(sb.toString());
        }
        else{
            System.out.println("Aucun type trouvé");    //jeu de test, visuel dans le terminal
            textArea.setText("Aucun type trouvé");
        }
        return sb;
    }
    private StringBuilder getVille(ResultSet rs) throws SQLException{ //pour le champ de sélection
        JTextArea textArea = new JTextArea(); //champ de texte
        textArea.setEditable(false);    //non modifiable
        StringBuilder sb = new StringBuilder(); //création de la chaine

        if(rs != null){
            //System.out.println("affiche ville demandé"); //jeu de test
            while (rs.next()) { //parcours du résultat via une boucle
                sb.append(rs.getString("ville") + "\n");
            }
            textArea.setText(sb.toString());
        }
        else{
            System.out.println("Aucune ville trouvée");    //jeu de test, visuel dans le terminal
            textArea.setText("Aucune ville trouvée");
        }
        return sb;
    }
    private StringBuilder getArrondissement(ResultSet rs) throws SQLException{ //pour le champ de sélection
        JTextArea textArea = new JTextArea(); //champ de texte
        textArea.setEditable(false);    //non modifiable
        StringBuilder sb = new StringBuilder(); //création de la chaine

        if(rs != null){
            //System.out.println("affiche ville demandé"); //jeu de test
            while (rs.next()) { //parcours du résultat via une boucle
                sb.append(rs.getString("libelle") + "\n");
            }
            textArea.setText(sb.toString());
        }
        else{
            System.out.println("Aucun arrondissement trouvé");    //jeu de test, visuel dans le terminal
            textArea.setText("Aucun arrondissement trouvé");
        }
        return sb;
    }
    private StringBuilder getDepartement(ResultSet rs) throws SQLException{ //pour le champ de sélection
        JTextArea textArea = new JTextArea(); //champ de texte
        textArea.setEditable(false);    //non modifiable
        StringBuilder sb = new StringBuilder(); //création de la chaine

        if(rs != null){
            //System.out.println("affiche ville demandé"); //jeu de test
            while (rs.next()) { //parcours du résultat via une boucle
                sb.append(rs.getString("departement") + "\n");
            }
            textArea.setText(sb.toString());
        }
        else{
            System.out.println("Aucun departement trouvé");    //jeu de test, visuel dans le terminal
            textArea.setText("Aucun departement trouvé");
        }
        return sb;
    }


    /////////////////////////SETTERS/////////////////////////////
    public void setNumCli(int num){this.numCli = num;}

}

