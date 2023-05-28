package app.MVC.view;

import app.MVC.controller.locataires_controller;
import app.MVC.modele.locataires_DAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class locataires_view extends JPanel{

    private JButton b1,b2,b3,b4,b5;
    private locataires_DAO m;
    private locataires_controller locataires_controller;
    private JPanel panelButtons,panelLabel, panelLabel2,global;
    private JList<String> clients,appartements;

    ///////new client////////
    private JTextField nom,prenom,adresse,cp,tel,Datenaiss,RIB,mdp,date;
    private JList<String> Ville;
    private JList appart;


    ////////////////construct//////////////
    public locataires_view(menu_view menu, ResultSet rs, int num) throws Exception {

        //////////////////Instance///////////////
        this.panelButtons = new JPanel();
        this.panelLabel2 = new JPanel();
        this.panelLabel = new JPanel();
        this.global = new JPanel();

        this.nom = new JTextField(5);
        this.prenom = new JTextField(5);
        this.adresse = new JTextField(5);
        this.cp = new JTextField(5);
        this.tel = new JTextField(5);
        this.Datenaiss = new JTextField(5);
        this.RIB = new JTextField(5);
        this.mdp = new JTextField(5);

        this.m = new locataires_DAO();
        this.locataires_controller = new locataires_controller(m, menu);   //instance controller

        StringBuilder listeVille = this.getVille(this.locataires_controller.getVilleDispo()); //appelle la fonction private de cette classe
        StringBuilder clientTab = this.getClients(this.m.getAllClients());
        StringBuilder appartstab = this.afficheAppart(this.m.getAllAppartements());

        this.Ville = new JList<>(listeVille.toString().split("\n"));   //liste de type
        this.clients = new JList<>(clientTab.toString().split("\n"));
        this.appartements = new JList<>(appartstab.toString().split("\n"));

        this.b1 = new JButton("Retour");                    //boutton retour
        this.b2 = new JButton("Afficher les locataires");   //boutton locataires (id = Afficher les locataires)
        this.b3 = new JButton("Ajouter locataire");
        this.b5 = new JButton("Afficher les clients");

        //////////////////setters//////////////////
        this.global.setLayout(new BorderLayout());
        this.panelButtons.setSize(1000, 1000);  //attribution d'une taille précise

        ////////////////ajout client/////////////////

        this.panelLabel2.add(new JLabel("Nom :"));
        this.panelLabel2.add(this.nom);

        this.panelLabel2.add(new JLabel("Prenom :"));
        this.panelLabel2.add(this.prenom);

        this.panelLabel2.add(new JLabel("adresse :"));
        this.panelLabel2.add(this.adresse);

        this.panelLabel2.add(new JLabel("Ville :"));
        this.panelLabel2.add(this.Ville);

        this.panelLabel2.add(new JLabel("CP : "));  //champ de séléction
        this.panelLabel2.add(this.cp);

        this.panelLabel2.add(new JLabel("tel : "));  //champ de séléction
        this.panelLabel2.add(this.tel);

        this.panelLabel2.add(new JLabel("Date de naissance :"));
        this.panelLabel2.add(this.Datenaiss);

        this.panelLabel2.add(new JLabel("RIB : "));
        this.panelLabel2.add(this.RIB);

        this.panelLabel2.add(new JLabel("MDP : "));
        this.panelLabel2.add(this.mdp);

        if(num != 1){
            this.nom.setText("vide");
            this.prenom.setText("vide");
            this.adresse.setText("vide");
            this.cp.setText("0");
            this.tel.setText("0");
            this.Datenaiss.setText("vide");
            this.RIB.setText("0");
            this.mdp.setText("0");
            this.b4 = new JButton("Ajouter Client");
            System.out.println("premier if");
        }
        else {
            this.nom.setText(rs.getString("nom"));
            this.prenom.setText(rs.getString("Prenom"));
            this.adresse.setText(rs.getString("adresse"));
            this.cp.setText(String.valueOf(rs.getInt("CP")));
            this.tel.setText(String.valueOf(rs.getInt("tel")));
            this.Datenaiss.setText(rs.getString("DateNaiss"));
            this.RIB.setText(String.valueOf(rs.getInt("RIB")));
            this.mdp.setText(String.valueOf(rs.getInt("mdp")));
            this.b4 = new JButton("Modifier");
            System.out.println("deuxieme if");
        }

        ////////////appel des controllers/////////
        this.b1.addActionListener(locataires_controller);   //chaque boutton est attribué à un controlleur précis
        this.b2.addActionListener(locataires_controller);   //chaque boutton est attribué à un controlleur précis
        this.b3.addActionListener(locataires_controller);   //chaque boutton est attribué à un controlleur précis
        this.b4.addActionListener(locataires_controller);
        this.b5.addActionListener(locataires_controller);

        this.b3.addActionListener(e -> {
            String client = this.clients.getSelectedValue(); //donne la valeur sélectionnée | null sinon
            locataires_controller.setClient(client); //setters du type, pour actualiser la variable

            int appart = Integer.parseInt(this.appartements.getSelectedValue());
            locataires_controller.setIdAppartements(appart);
        }); //action lors du clic sur button

        this.b4.addActionListener(e -> {
            try {
                if (num == 1){
                    int id = rs.getInt("id");
                    this.locataires_controller.setIdCli(id);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            String nom = this.nom.getText(); //donne la valeur sélectionnée | null sinon
            this.locataires_controller.setNom(nom); //setters de la ville, pour actualiser la variable

            String prenom = this.prenom.getText(); //donne la valeur sélectionnée | null sinon
            this.locataires_controller.setPrenom(prenom);   //setters du type

            String adresse = this.adresse.getText();
            this.locataires_controller.setAdresse(adresse);

            String ville = this.Ville.getSelectedValue();    //passe de string à int et attribue le prix
            this.locataires_controller.setVille(String.valueOf(ville)); //setters

            int cp = Integer.parseInt(this.cp.getText());    //passe de string à int et attribue le prix
            this.locataires_controller.setCp(cp); //setters

            int tel = Integer.parseInt(this.tel.getText());   //passe du string a int et attribue la taille
            this.locataires_controller.setTel(tel); //setters

            String Datenaiss = this.Datenaiss.getText();
            this.locataires_controller.setDatenaiss(Datenaiss);

            int rib = Integer.parseInt(this.RIB.getText());
            this.locataires_controller.setRIB(rib);

            String mdp = this.mdp.getText();
            this.locataires_controller.setMdp(mdp);
        }); //action lors du clic sur button

        //////////////////////////////////////////////////////////////////////////

        this.global.setLayout(new BorderLayout());
        this.panelButtons.setBackground(Color.DARK_GRAY);   //couleur du panel button
        this.panelLabel.setBackground(Color.GRAY);          //couleur du panel label
        this.panelLabel2.setBackground(Color.GRAY);         //couleur du panel label
        this.panelButtons.setSize(1000, 1000);  //setter de la taille

        this.global.add(this.panelButtons, BorderLayout.PAGE_START); //affichage du haut, ca permettra de pas supprimer la vue
        this.global.add(this.panelLabel, BorderLayout.PAGE_END);     //affichage du bas, ca permettra de pas supprimer la vue
        this.global.add(this.panelLabel2, BorderLayout.PAGE_END);    //affichage du bas, ca permettra de pas supprimer la vue

        this.panelButtons.add(this.b1); //ajout des button à leurs panels
        this.panelButtons.add(this.b2); //ajout des button à leurs panels
        this.panelLabel.add(this.b3);
        this.panelLabel2.add(this.b4);
        this.panelButtons.add(this.b5);

        this.add(this.global);
    }

    ////////////////Vue des buttons//////////////
    public void afficheAllLocataires(ResultSet rs) throws SQLException {
        System.out.println("affiche all locataires");   //affichage de test

        JTextArea textArea = new JTextArea();   //zone de texte pour les données
        textArea.setEditable(false);    //edit impossible

        /////////////////constructeur string/////////////
        StringBuilder sbd = new StringBuilder();

        ///////////////////////Boucle pour parcourir les données////////////////////////
        while (rs.next()) {
            sbd.append("Numéro locataire : " + rs.getString("id") + "\n");
            sbd.append("Nom locataire : " + rs.getString("nom") + "\n");
            sbd.append("Prénom locataire : " + rs.getString("prenom") + "\n");
            sbd.append("Date de naissance: " + rs.getString("dateNaissance") + "\n");
            sbd.append("Téléphone : " + rs.getString("tel") + "\n");
            sbd.append("RIB : " + rs.getString("RIB") + "\n");
            sbd.append("\n");
        }
        textArea.setText(sbd.toString());   //setters

        ///////////////////Menu déroulant/////////////////
        JScrollPane scrollPane = new JScrollPane(textArea);                 //instance
        scrollPane.setPreferredSize(new Dimension(400, 300));   //Définition de la taille
        this.global.add(scrollPane, BorderLayout.CENTER);                   //ajout du scrollPane dans global

        this.global.validate();
        this.global.repaint();
    }
    public void afficheAllClients(ResultSet rs) throws SQLException {
        System.out.println("affiche all Clients");   //affichage de test

        // Création du JPanel qui contiendra tous les panneaux de demandes
        JPanel demandePanel = new JPanel();
        demandePanel.setLayout(new BoxLayout(demandePanel, BoxLayout.PAGE_AXIS));

        while (rs.next()) { //parcours du résultat via une boucle

            // Création du panneau de demande
            JPanel panel = new JPanel();

            panel.add(new JLabel("\n"));
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

            int id = rs.getInt("id");

            panel.add(new JLabel("id : " + id));
            panel.add(new JLabel("Nom : " + rs.getString("nom")));
            panel.add(new JLabel("Prenom : " + rs.getString("Prenom") + "\n"));
            panel.add(new JLabel("Adresse : " + rs.getString("adresse") + "\n"));
            panel.add(new JLabel("Ville : " + rs.getString("Ville") + "\n"));
            panel.add(new JLabel("Code postal : " + rs.getInt("CP") + "\n"));
            panel.add(new JLabel("tel : " + rs.getInt("tel") + "\n"));
            panel.add(new JLabel("Date de naissance : " + rs.getString("DateNaiss") + "\n"));
            panel.add(new JLabel("RIB : " + rs.getInt("RIB") + "\n"));
            panel.add(new JLabel("mdp : " + rs.getString("mdp") + "\n"));

            // Création du bouton de recherche
            JButton ajouterVisite = new JButton("Ajouter visite");
            JButton modifierClient = new JButton("Modifier Client");
            JButton SupprimerClient = new JButton("Supprimer");

            ajouterVisite.addActionListener(this.locataires_controller);   //appelle du controleur spécifique
            modifierClient.addActionListener(this.locataires_controller);   //appelle du controleur
            SupprimerClient.addActionListener(this.locataires_controller);   //appelle du controleur spécifique

            ajouterVisite.addActionListener((ActionEvent e) -> { //set l'id du client pour traiter sa demande
                this.locataires_controller.setIdCli(id);
            });

            modifierClient.addActionListener((ActionEvent e) -> { //set l'id du client pour traiter sa demande
                this.locataires_controller.setIdCli(id);
            });

            SupprimerClient.addActionListener((ActionEvent e) -> { //set l'id du client pour traiter sa demande
                this.locataires_controller.setIdCli(id);
            });

            // Ajout du panneau de demande au panneau conteneur
            demandePanel.add(panel);
            // Ajout du bouton de recherche au panneau
            panel.add(modifierClient);
            panel.add(ajouterVisite);
            panel.add(SupprimerClient);
        }

        // Création du JScrollPane pour le panneau conteneur
        JScrollPane scrollPane = new JScrollPane(demandePanel);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        this.global.add(scrollPane, BorderLayout.CENTER);
        this.global.validate();
        this.global.repaint();
    }
    public void afficheNewVisite(ResultSet rs, int numCli) throws Exception {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        // Panneaux
        JPanel panel = new JPanel();
        JPanel buttonPanel = new JPanel();

        // Mise en forme des panneaux
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS)); // Alignement vertical
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // marge de 20px pour chaque bordure

        StringBuilder idAppart = this.afficheAppart(this.locataires_controller.getIdAllAppart());

        // Labels pour les champs de saisie
        JLabel Date = new JLabel("Date :");
        JLabel AppartementsId = new JLabel("Appartements numéro : ");

        panel.add(AppartementsId);
        panel.add(this.appart = new JList<>(idAppart.toString().split("\n")));
        panel.add(Date);
        panel.add(this.date = new JTextField(20));

        // Boutons
        JButton ajouterBtn = new JButton("Ajouter");

        ajouterBtn.addActionListener(this.locataires_controller);
        ajouterBtn.addActionListener(e -> {
            this.locataires_controller.setIdCli(numCli);
            this.locataires_controller.setIdAppartements(Integer.parseInt(String.valueOf(this.appart.getSelectedValue())));
            this.locataires_controller.setDateVisite(this.date.getText());
        });

        // Ajout des panneaux au panneau principal
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);

        buttonPanel.add(ajouterBtn);

        // Validation et redessin
        this.global.validate();
        this.global.repaint();
    }

    ////////////////GETTERS//////////////
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
    public StringBuilder afficheAppart(ResultSet rs) throws SQLException {    //cette procédure permet l'affiche de deux demandes du controleur -> afficheALl et afficheRqst

        JTextArea textArea = new JTextArea();   //zone de texte pour les données
        textArea.setEditable(false);    //edit impossible

        StringBuilder sb = new StringBuilder(); //création de la chaine

        ///////////////////////Boucle pour parcourir les données////////////////////////
        while (rs.next()) {
            sb.append(rs.getInt("numappart") + "\n");
        }
        textArea.setText(sb.toString());   //setters

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

}