package app.MVC.view;

import app.MVC.controller.clients_controller;
import app.MVC.modele.clients_DAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class visite_view extends JPanel {
    private JButton b1,btnNew;

    private JPanel panelButtons,panelLabel,global;
    private clients_controller clients_controller;
    private clients_DAO clients_dao;
    private JTextField date;
    private JList<String> ClientList, AppartList;

    public visite_view(menu_view menuView) {
        this.panelButtons = new JPanel();   //panel buttons
        this.panelLabel = new JPanel();     //panel label
        this.global = new JPanel();         //panel global

        this.clients_dao = new clients_DAO();
        this.clients_controller = new clients_controller(this.clients_dao, menuView);

        this.b1 = new JButton("Retour");
        this.btnNew = new JButton("Ajouter");

        this.b1.addActionListener(this.clients_controller);
        this.btnNew.addActionListener(this.clients_controller);

        //////////////////ajout//////////////////
        this.global.setLayout(new BorderLayout());
        this.panelButtons.setBackground(Color.DARK_GRAY);   //couleur du panel button
        this.panelLabel.setBackground(Color.GRAY);  //couleur du panel label

        this.panelButtons.setSize(1000, 1000); //setter de la taille

        this.global.add(this.panelButtons, BorderLayout.PAGE_START); //affichage du haut, ca permettra de pas supprimer la vue
        this.global.add(this.panelLabel, BorderLayout.PAGE_END);    //affichage du bas, ca permettra de pas supprimer la vue

        this.panelButtons.add(this.b1); //ajout des button à leurs panels
        this.panelLabel.add(this.btnNew);

        this.add(this.global);
    }

    public void afficheAllVisite(ResultSet rs) throws SQLException {

        // Création du JPanel qui contiendra tous les panneaux de demandes
        JPanel visitePanel = new JPanel();
        visitePanel.setLayout(new BoxLayout(visitePanel, BoxLayout.PAGE_AXIS));

        while (rs.next()) {
            // Création du panneau de visite
            JPanel panel = new JPanel();

            panel.add(new JLabel("\n"));
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

            // Récupération du numéro de demande pour le bouton de recherche
            int numappart = rs.getInt("numappart");
            int idClient = rs.getInt("idClient");
            String dateVisite = rs.getString("date_visite");

            // Ajout des informations de la demande au panneau
            panel.add(new JLabel("Numéro Appartement : " + numappart));
            panel.add(new JLabel("Numéro Client : " + idClient));
            panel.add(new JLabel("Date visite : " + dateVisite));

            // Création du bouton de recherche
            JButton modifButton = new JButton("Modifier");
            JButton deleteButton = new JButton("Supprimer");

            modifButton.addActionListener(this.clients_controller);   //appelle du controleur spécifique
            deleteButton.addActionListener(this.clients_controller);   //appelle du controleur spécifique

            ////////Setters pour initialiser en fonction de la demande//////
            modifButton.addActionListener((ActionEvent e) -> {
                //this.clients_controller.setNum_dem(numDem);
            });

            deleteButton.addActionListener((ActionEvent e) -> {
                this.clients_controller.setIdClient(idClient);
                this.clients_controller.setIdAppart(numappart);
                this.clients_controller.setDate(dateVisite);
            });

            // Ajout du panneau de demande au panneau conteneur
            visitePanel.add(panel);
            // Ajout du bouton de recherche au panneau
            panel.add(modifButton);
            panel.add(deleteButton);
        }

        // Création du JScrollPane pour le panneau conteneur
        JScrollPane scrollPane = new JScrollPane(visitePanel);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        this.global.add(scrollPane, BorderLayout.CENTER);
        this.global.validate();
        this.global.repaint();
    }

    public void AjoutVisite() throws Exception {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        // Panneaux
        JPanel panel = new JPanel();
        JPanel buttonPanel = new JPanel();

        // Mise en forme des panneaux
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Alignement vertical
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // marge de 20px pour chaque bordure

        // Listes déroulantes pour les villes, les types d'appartements et les arrondissements
        StringBuilder listeClient = this.getClient(this.clients_controller.getAllClients());
        StringBuilder listeAppart = this.getAppartDispo(this.clients_controller.getAppartDispo());

        // Labels pour les champs de saisie
        JLabel CLientLabel = new JLabel("Clients :");
        JLabel AppartLabel = new JLabel("Appartements :");
        JLabel DateLAbel = new JLabel("Date :");

        // Ajout des champs de saisie
        panel.add(CLientLabel);
        panel.add(this.ClientList = new JList<>(listeClient.toString().split("\n")));
        panel.add(AppartLabel);
        panel.add(this.AppartList = new JList<>(listeAppart.toString().split("\n")));
        panel.add(DateLAbel);
        panel.add(this.date = new JTextField(10));

        this.date.setText("0");  //initialise le champ à 0 (en string)

        // Boutons
        JButton ajouterBtn = new JButton("Ajouter Visite");
        JButton Retour = new JButton("Retour");

        ajouterBtn.addActionListener(this.clients_controller);
        Retour.addActionListener(this.clients_controller);

        ajouterBtn.addActionListener(e -> {
            this.clients_controller.setClient(this.ClientList.getSelectedValue());
            this.clients_controller.setIdAppart(Integer.parseInt(this.AppartList.getSelectedValue()));
            this.clients_controller.setDate(this.date.getText());
        });

        buttonPanel.add(ajouterBtn);
        buttonPanel.add(Retour);

        // Ajout des panneaux au panneau principal
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);

        // Validation et redessin
        this.global.validate();
        this.global.repaint();
    }


    private StringBuilder getClient(ResultSet rs) throws SQLException {
        StringBuilder sb = new StringBuilder();

        if (rs != null) {
            System.out.println("affiche client pour visite"); //jeu de test
            while (rs.next()) {
                sb.append(rs.getString("nom")).append(" ");
                sb.append(rs.getString("Prenom")).append("\n");
            }
        } else {
            System.out.println("Aucun client trouvé"); //jeu de test, visuel dans le terminal
            sb.append("Aucun client trouvé\n");
        }
        return sb;
    }
    private StringBuilder getAppartDispo(ResultSet rs) throws SQLException {
        StringBuilder sb = new StringBuilder();

        if (rs != null) {
            System.out.println("affiche appartement disponible"); //jeu de test
            while (rs.next()) {
                sb.append(rs.getString("numappart")).append("\n");
            }
        } else {
            System.out.println("Aucun appartement disponible"); //jeu de test, visuel dans le terminal
            sb.append("Aucun appartement disponible\n");
        }
        return sb;
    }


}
