package app.MVC.view;

import app.MVC.controller.demandes_controller;
import app.MVC.modele.demandes_DAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class demandes_view extends JPanel{
    private JButton b1,b2,btnNewDemd;
    private demandes_DAO m;
    private JPanel panelButtons;
    private JPanel panelLabel;
    private JPanel global;
    private demandes_controller demandes_controller;
    private JTextField prixMax;
    private JTextField prixMin;
    private JTextField taille;
    private JList<String> Ville;
    private JList<String> type;
    private JList<String> clients;
    private JTextField dateDem;
    private JList arrondissement;
    public demandes_view(menu_view menu) throws Exception {
        //////////////////Instance///////////////
        this.panelButtons = new JPanel();
        this.panelLabel = new JPanel();
        this.global = new JPanel();

        this.m = new demandes_DAO();
        this.demandes_controller = new demandes_controller(m, menu);

        StringBuilder villeTab = this.getVille(this.demandes_controller.getVilleDispo()); //appelle la fonction private de cette classe
        StringBuilder typeTab = this.getTypesAppart(this.demandes_controller.getType());
        StringBuilder clientTab = this.getClients(this.demandes_controller.getAllClients());
        StringBuilder arrondissement = this.getArrondissement(this.demandes_controller.getAllArrondissment());

        this.prixMin = new JTextField(5);
        this.prixMax = new JTextField(5);
        this.taille = new JTextField(5);
        this.dateDem = new JTextField(10);

        this.Ville = new JList<>(villeTab.toString().split("\n"));
        this.type = new JList<>(typeTab.toString().split("\n"));
        this.clients = new JList<>(clientTab.toString().split("\n"));
        this.arrondissement = new JList<>(arrondissement.toString().split("\n"));

        this.b1 = new JButton("Retour");
        this.b2 = new JButton("Afficher les demandes");
        this.btnNewDemd = new JButton("Ajouter");

        ////////////appel des controllers////////
        this.b1.addActionListener(this.demandes_controller);
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

        this.panelLabel.add(new JLabel("Client :"));
        this.panelLabel.add(this.clients);

        this.panelLabel.add(new JLabel("Date limite (YYYY-MM-DD) : "));
        this.panelLabel.add(this.dateDem);

        this.prixMax.setText("0");
        this.taille.setText("0");
        this.prixMin.setText("0");

        this.btnNewDemd.addActionListener(e -> {
            String ville = this.Ville.getSelectedValue(); //donne la valeur sélectionnée | null sinon
            demandes_controller.setVilleAppart(ville); //setters de la ville, pour actualiser la variable

            String type = this.type.getSelectedValue(); //donne la valeur sélectionnée | null sinon
            this.demandes_controller.setTypeAppart(type);   //setters du type

            String arrondi = this.arrondissement.getSelectedValue().toString();
            this.demandes_controller.setArrondissement(arrondi);

            int prixmin = Integer.parseInt(this.prixMin.getText());    //passe de string à int et attribue le prix
            this.demandes_controller.setPrix_min(prixmin); //setters

            int prix = Integer.parseInt(this.prixMax.getText());    //passe de string à int et attribue le prix
            this.demandes_controller.setPrix_max(prix); //setters

            int taille = Integer.parseInt(this.taille.getText());   //passe du string a int et attribue la taille
            this.demandes_controller.setTailleAppart(taille); //setters

            String client = this.clients.getSelectedValue();
            this.demandes_controller.setClientsDem(client);

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
    public void afficheAllDemandes(ResultSet rs) throws SQLException {

        // Création du JPanel qui contiendra tous les panneaux de demandes
        JPanel demandePanel = new JPanel();
        demandePanel.setLayout(new BoxLayout(demandePanel, BoxLayout.PAGE_AXIS));

        while (rs.next()) {
            // Création du panneau de demande
            JPanel panel = new JPanel();

            panel.add(new JLabel("\n"));
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

            // Récupération du numéro de demande pour le bouton de recherche
            int numDem = rs.getInt("num_dem");
            int prix_max = rs.getInt("prix_max");
            int taille = rs.getInt("taille");
            String type = rs.getString("type");
            String ville = rs.getString("ville");

            // Ajout des informations de la demande au panneau
            panel.add(new JLabel("Numéro demande : " + numDem));
            panel.add(new JLabel("Numéro client : " + rs.getString("num_cli")));
            panel.add(new JLabel("Prix min : " + rs.getInt("prix_min")));
            panel.add(new JLabel("Prix max : " + prix_max));
            panel.add(new JLabel("Taille : " + taille));
            panel.add(new JLabel("Ville : " + ville));
            panel.add(new JLabel("Type : " + type));
            panel.add(new JLabel("Date : " + rs.getString("date_limite")));

            // Création du bouton de recherche
            JButton searchButton = new JButton("Rechercher");
            JButton deleteButton = new JButton("Supprimer");

            searchButton.addActionListener(this.demandes_controller);   //appelle du controleur spécifique
            deleteButton.addActionListener(this.demandes_controller);   //appelle du controleur spécifique


            ////////Setters pour initialiser en fonction de la demande//////
            searchButton.addActionListener((ActionEvent e) -> {
                this.demandes_controller.setNum_dem(numDem);
                this.demandes_controller.setPrix_max(prix_max);
                this.demandes_controller.setTailleAppart(taille);
                this.demandes_controller.setTypeAppart(type);
                this.demandes_controller.setVilleAppart(ville);
            });

            deleteButton.addActionListener((ActionEvent e) -> {
                this.demandes_controller.setNum_dem(numDem);
            });

            // Ajout du panneau de demande au panneau conteneur
            demandePanel.add(panel);
            // Ajout du bouton de recherche au panneau
            panel.add(searchButton);
            panel.add(deleteButton);
        }

        // Création du JScrollPane pour le panneau conteneur
        JScrollPane scrollPane = new JScrollPane(demandePanel);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        this.global.add(scrollPane, BorderLayout.CENTER);
        this.global.validate();
        this.global.repaint();
    }
    public void afficheAppart(ResultSet rs,int numdem) throws SQLException {
        //cette procédure permet l'affiche de deux demandes du controleur -> afficheALl et afficheRqst

        // Création du JPanel qui contiendra tous les panneaux de demandes
        JPanel demandePanel = new JPanel();
        demandePanel.setLayout(new BoxLayout(demandePanel, BoxLayout.PAGE_AXIS));

        JTextArea textArea = new JTextArea(); //champ de texte
        textArea.setEditable(false);    //non modifiable

        StringBuilder sb = new StringBuilder(); //création de la chaine

            System.out.println("affiche appartements demandé"); //jeu de test
            while (rs.next()) { //parcours du résultat via une bouvle

                // Création du panneau de demande
                JPanel panel = new JPanel();

                panel.add(new JLabel("\n"));

                panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

                panel.add(new JLabel("Prix location : " + rs.getString("prix_log")));
                panel.add(new JLabel("Prix charge: " + rs.getString("prix_charg") + "\n"));
                panel.add(new JLabel("Adresse : " + rs.getString("rue") + "\n"));
                panel.add(new JLabel("Ville : " + rs.getString("ville") + "\n"));
                panel.add(new JLabel("CP : " + rs.getString("CP") + "\n"));
                panel.add(new JLabel("Etage : " + rs.getString("etage") + "\n"));
                panel.add(new JLabel("Préavis : " + rs.getString("preavis") + "\n"));
                panel.add(new JLabel("Ascenseur : " + rs.getString("ascenseur") + "\n"));
                panel.add(new JLabel("Date libre : " + rs.getString("date_libre") + "\n"));
                panel.add(new JLabel("Taille : " + rs.getString("taille") + "m2\n"));
                panel.add(new JLabel("Type : " + rs.getString("type") + "\n"));
                sb.append("\n");

                // Création du bouton de recherche
                JButton attribuerButton = new JButton("Attribuer au client");

                attribuerButton.addActionListener(this.demandes_controller);   //appelle du controleur spécifique

                attribuerButton.addActionListener((ActionEvent e) -> {
                    this.demandes_controller.setNum_dem(numdem);
                });

                // Ajout du panneau de demande au panneau conteneur
                demandePanel.add(panel);
                // Ajout du bouton de recherche au panneau
                panel.add(attribuerButton);
            }

            // Création du JScrollPane pour le panneau conteneur
            JScrollPane scrollPane = new JScrollPane(demandePanel);
            scrollPane.setPreferredSize(new Dimension(400, 300));

            this.global.add(scrollPane, BorderLayout.CENTER);
            this.global.validate();
            this.global.repaint();
    }




    private StringBuilder getTypesAppart(ResultSet rs) throws SQLException{ //pour le champ de sélection
        JTextArea textArea = new JTextArea(); //champ de texte
        textArea.setEditable(false);    //non modifiable
        StringBuilder sb = new StringBuilder(); //création de la chaine

        if(rs != null){
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
    private StringBuilder getClients(ResultSet rs) throws SQLException{ //pour le champ de sélection
        JTextArea textArea = new JTextArea(); //champ de texte
        textArea.setEditable(false);    //non modifiable
        StringBuilder sb = new StringBuilder(); //création de la chaine

        if(rs != null){
            System.out.println("affiche clients demandé"); //jeu de test
            while (rs.next()) { //parcours du résultat via une boucle
                sb.append(rs.getString("nom") + " ");
                sb.append(rs.getString("prenom") + "\n");
            }
            textArea.setText(sb.toString());
        }
        else{
            System.out.println("Aucun client trouvé");    //jeu de test, visuel dans le terminal
            textArea.setText("Aucun client trouvé");
        }
        return sb;
    }
    private StringBuilder getVille(ResultSet rs) throws SQLException{ //pour le champ de sélection
        JTextArea textArea = new JTextArea(); //champ de texte
        textArea.setEditable(false);    //non modifiable
        StringBuilder sb = new StringBuilder(); //création de la chaine

        if(rs != null){
            System.out.println("affiche appartements demandé"); //jeu de test
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
}
