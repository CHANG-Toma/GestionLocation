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
    private demandes_DAO m; //DAO demandes
    private appartement_DAO appartement_dao;    //DAO appartements
    private demandes_controller demandes_controller;
    private Appartement_controller appartement_controller;
    private JPanel panelButtons, panelLabel, global, panelMaison;   //création des panels
    private JButton appartementsDispo,mesDemandes,btnNewDemd, mesVisites, ajouterMaison; //création des bouttons

    //__________________APPARTEMENTS__________________//
    private JTextField prixMax,prixMin,taille,dateDem, jardin, piscine, superficie, garage; //variable pour appartements
    private JList<String> Ville,type,arrondissement, departement, region;    //Liste pour appartements

    //________________FIN APPARTEMENTS__________________//
    private int numCli;  //variable pour Client


    //_____________MAISON_____________________________//

    private String Jardin;
    private int superficieJardin;
    private String Garage;

    public Client_view(menu_view menu) throws Exception {
        //////////////////Instance///////////////
        this.panelButtons = new JPanel(); //création des panels
        this.panelLabel = new JPanel();   //création des panels
        this.global = new JPanel();       //création des panels

        this.m = new demandes_DAO();   //instanciation de la classe
        this.appartement_dao = new appartement_DAO();   //instanciation de la classe
        this.demandes_controller = new demandes_controller(m, menu);     //instanciation de la classe
        this.appartement_controller = new Appartement_controller(this.appartement_dao, menu);   //instanciation de la classe

        StringBuilder villeTab = this.getVille(this.demandes_controller.getVilleDispo()); //appelle la fonction private de cette classe
        StringBuilder typeTab = this.getTypesAppart(this.demandes_controller.getType());  //appelle la fonction private de cette classe
        StringBuilder arrondissement = this.getArrondissement(this.demandes_controller.getAllArrondissment()); //appelle la fonction private de cette classe

        this.prixMin = new JTextField(10);  //textfield
        this.prixMax = new JTextField(10);  //textfield
        this.taille = new JTextField(10);   //textfield
        this.dateDem = new JTextField(10);  //textfield


        this.Ville = new JList<>(villeTab.toString().split("\n"));      //liste pour la séléction
        this.type = new JList<>(typeTab.toString().split("\n"));        //liste pour la séléction
        this.arrondissement = new JList<>(arrondissement.toString().split("\n"));   //liste pour la séléction

        this.departement = new JList<>(departement.toString().split("\n"));   //liste pour la séléction
        this.region = new JList<>(region.toString().split("\n"));   //liste pour la séléction

        this.appartementsDispo = new JButton("Afficher les appartements disponibles"); //initialisation des bouttons
        this.mesDemandes = new JButton("Afficher mes demandes");    //initialisation des bouttons
        this.btnNewDemd = new JButton("Ajouter demandes");                   //initialisation des bouttons
        this.mesVisites = new JButton("Mes visites");               //initialisation des bouttons
        this.ajouterMaison = new JButton("Ajouter maison");               //initialisation des bouttons

        this.appartementsDispo.addActionListener(this.appartement_controller); //appel de son controller
        this.mesDemandes.addActionListener(this.demandes_controller);   //appel de son controller
        this.btnNewDemd.addActionListener(this.demandes_controller);    //appel de son controller
        this.mesVisites.addActionListener(this.demandes_controller);    //appel de son controller

        this.panelLabel.add(new JLabel("Taille :"));    //champ de saisie
        this.panelLabel.add(this.taille);                   //ajout au panel

        this.panelLabel.add(new JLabel("Prix min :"));  //champ de saisie
        this.panelLabel.add(this.prixMin);                  //ajout au panel

        this.panelLabel.add(new JLabel("Prix max :"));  //champ de saisie
        this.panelLabel.add(this.prixMax);                  //ajout au panel

        this.panelLabel.add(new JLabel("Ville :"));     //champ de saisie
        this.panelLabel.add(this.Ville);                    //ajout au panel

        this.panelLabel.add(new JLabel("Type: "));      //champ de selection
        this.panelLabel.add(this.type);                     //ajout au panel

        this.panelLabel.add(new JLabel("Arrondissement : "));  //champ de selection
        this.panelLabel.add(this.arrondissement);                  //ajout au panel

        this.panelLabel.add(new JLabel("Date limite (YYYY-MM-DD) : ")); //champ de saisie
        this.panelLabel.add(this.dateDem);                                  //ajout au panel

        this.panelLabel.add(new JLabel("Region : "));  //champ de selection
        this.panelLabel.add(this.region);

        this.panelLabel.add(new JLabel("Departement : "));  //champ de selection
        this.panelLabel.add(this.departement);

        this.panelLabel.add(new JLabel("Arrondissement : "));  //champ de selection
        this.panelLabel.add(this.arrondissement);


        this.prixMax.setText("0");  //initialise à 0
        this.taille.setText("0");   //initialise à 0
        this.prixMin.setText("0");  //initialise à 0


        //this.panelMaison.add(new JLabel("Superficie : "));
        //this.panelMaison.add(this.superficie);

        //this.panelMaison.add(new JLabel("Piscine : "));
        //this.panelMaison.add(this.piscine);

        //this.panelMaison.add(new JLabel("Garage : "));
        //this.panelMaison.add(this.garage);


        //this.ajouterMaison.addActionListener(this.demandes_controller);

        //this.ajouterMaison.addActionListener(e -> {
        //this.demandes_controller.setJardin(this.jardin.getText());
        //this.demandes_controller.setGarage(this.garage.getText());
        //this.demandes_controller.setPiscine(this.piscine.getText());
        //this.demandes_controller.setSuperficie(Integer.parseInt(this.superficie.getText()));
        //});


        this.appartementsDispo.addActionListener(e -> {
            this.appartement_controller.setNum_cli(this.numCli);
        }); //setters lors du clic du boutton

        this.mesDemandes.addActionListener(e -> { //setters lors du clic du boutton
            this.demandes_controller.setNum_Cli(this.numCli);
        });

        this.btnNewDemd.addActionListener(e -> {
            this.demandes_controller.setNum_Cli(this.numCli);

            this.demandes_controller.setPiscine(null);
            this.demandes_controller.setSuperficie(0);
            this.demandes_controller.setGarage(null);
            this.demandes_controller.setJardin(null);


            String department = this.departement.getSelectedValue();
            this.demandes_controller.setDepartement(department);

            String Region = this.arrondissement.getSelectedValue();
            this.demandes_controller.setRegion(Region);



            String Arrondissement = this.arrondissement.getSelectedValue();
            demandes_controller.setArrondissement(Arrondissement);

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

        });     //setters lors du clic du boutton

        this.mesVisites.addActionListener(e -> {    //setters lors du clic du boutton
            this.demandes_controller.setNum_Cli(this.numCli);
        });

        //////////////////ajout//////////////////
        this.global.setLayout(new BorderLayout());
        this.panelButtons.setBackground(Color.DARK_GRAY);   //couleur du panel button
        this.panelLabel.setBackground(Color.GRAY);  //couleur du panel label

        this.panelButtons.setSize(1000, 1000); //setter de la taille

        this.global.add(this.panelButtons, BorderLayout.PAGE_START); //affichage du haut, ca permettra de pas supprimer la vue
        this.global.add(this.panelLabel, BorderLayout.PAGE_END);    //affichage du bas, ca permettra de pas supprimer la vue
        //this.global.add(this.panelMaison, BorderLayout.CENTER);    //affichage du bas, ca permettra de pas supprimer la vue


        this.panelButtons.add(this.appartementsDispo); //ajout des button à leurs panels
        this.panelButtons.add(this.mesDemandes); //ajout des button à leurs panels
        this.panelButtons.add(this.mesVisites); //ajout des button à leurs panels
        this.panelLabel.add(this.btnNewDemd);   //ajout des button à leurs panels
        //this.panelMaison.add(this.ajouterMaison);

        this.add(this.global);  //ajout au global
    }

    ///////////////////////Affichage proposé///////////////////////
    public void afficheMesVisites(ResultSet rs) throws SQLException {

        // Création du JPanel qui contiendra tous les panneaux de demandes
        JPanel demandePanel = new JPanel();
        demandePanel.setLayout(new BoxLayout(demandePanel, BoxLayout.PAGE_AXIS));

        do {
            // Création du panneau de demande
            JPanel panel = new JPanel();
            panel.add(new JLabel("\n"));
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

            int idAppart = rs.getInt("numappart");
            int idClient = rs.getInt("idClient");
            String date = rs.getString("date_visite");

            // Ajout des informations de la demande au panneau
            panel.add(new JLabel("Date de visite : " + rs.getString("date_visite")));
            panel.add(new JLabel("Rue : " + rs.getInt("rue")));
            panel.add(new JLabel("Ville : " + rs.getString("ville")));
            panel.add(new JLabel("Code postal : "+ rs.getInt("CP")));
            panel.add(new JLabel("Etage : "+ rs.getInt("etage")));
            panel.add(new JLabel("Prix loyer : " + rs.getInt("prix_log")));
            panel.add(new JLabel("Prix Charge : " + rs.getInt("prix_charg")));
            panel.add(new JLabel("Ascenseur : " + rs.getInt("ascenseur")));
            panel.add(new JLabel("Preavis : " + rs.getInt("preavis")));
            panel.add(new JLabel("Type : " + rs.getString("type")));
            panel.add(new JLabel("Arrondissement : " + rs.getString("libelle")));
            panel.add(new JLabel("Taille : " + rs.getString("taille")));

            JButton SupprimerVisite = new JButton("Supprimer visite");

            SupprimerVisite.addActionListener(this.demandes_controller);   //appelle du controleur spécifique

            SupprimerVisite.addActionListener((ActionEvent e) -> { //set l'id du client pour traiter sa demande
                this.demandes_controller.setIdAppart(idAppart);
                this.demandes_controller.setNum_Cli(idClient);
                this.demandes_controller.setDateVisite(date);
            });

            // Ajout du panneau de demande au panneau conteneur
            demandePanel.add(panel);
            panel.add(SupprimerVisite);
        }
        while (rs.next());

        // Création du JScrollPane pour le panneau conteneur
        JScrollPane scrollPane = new JScrollPane(demandePanel);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        this.global.add(scrollPane, BorderLayout.CENTER);
        this.global.validate();
        this.global.repaint();
    }
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

    ///////////////////////GETTERS Liste//////////////////////////////
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
            System.out.println("Aucun arrondissement trouvé");    //jeu de test, visuel dans le terminal
            textArea.setText("Aucun arrondissement trouvé");
        }
        return sb;
    }
    private StringBuilder getRegion(ResultSet rs) throws SQLException{ //pour le champ de sélection
        JTextArea textArea = new JTextArea(); //champ de texte
        textArea.setEditable(false);    //non modifiable
        StringBuilder sb = new StringBuilder(); //création de la chaine

        if(rs != null){
            //System.out.println("affiche ville demandé"); //jeu de test
            while (rs.next()) { //parcours du résultat via une boucle
                sb.append(rs.getString("region") + "\n");
            }
            textArea.setText(sb.toString());
        }
        else{
            System.out.println("Aucun arrondissement trouvé");    //jeu de test, visuel dans le terminal
            textArea.setText("Aucun arrondissement trouvé");
        }
        return sb;
    }



    /////////////////////////SETTERS/////////////////////////////
    public void setNumCli(int num){this.numCli = num;}

}

