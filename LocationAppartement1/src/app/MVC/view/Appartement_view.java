package app.MVC.view;

import app.MVC.controller.Appartement_controller;
import app.MVC.modele.appartement_DAO;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Appartement_view extends JPanel {

    private JButton b1, b2, searchButton, ajouteAppart;
    private appartement_DAO appartementDAO;
    private Appartement_controller appartement_controller;
    private JPanel panelButtons,panelLabel,global;
    private JTextField rue, cp, etage, prixTextField, prix_charg, preavis, dateLibre,tailleTextField, ascenseur;
    private JList<String> TypeListField, villeTextAppart, arrondissement;

    public Appartement_view(menu_view menu) throws Exception {

        ////////////////Instance////////////////////
        this.panelButtons = new JPanel();
        this.panelLabel = new JPanel();
        this.global = new JPanel();

        this.appartementDAO = new appartement_DAO();
        this.appartement_controller = new Appartement_controller(appartementDAO, menu);

        //////////////Button de base////////////////
        this.b1 = new JButton("Retour");
        this.b2 = new JButton("Afficher appartements");
        this.searchButton = new JButton("Rechercher");
        this.ajouteAppart = new JButton("Ajouter");

        //////////////Champ de saisie////////////
        this.prixTextField = new JTextField(10);
        this.tailleTextField = new JTextField(10);

        //////////////Liste type et ville////////
        StringBuilder typeTab = this.getTypesAppart(this.appartement_controller.getType()); //appelle la fonction private de cette classe
        StringBuilder villeTab = this.getVille(this.appartement_controller.getVilleDispo()); //appelle la fonction private de cette classe

        this.TypeListField = new JList<>(typeTab.toString().split("\n"));   //liste de type
        this.villeTextAppart = new JList<>(villeTab.toString().split("\n")); // Liste de villes

        this.panelButtons.add(this.b1); // ajout des button
        this.panelButtons.add(this.b2);

        this.panelLabel.add(new JLabel("Type: "));  //champ de séléction
        this.panelLabel.add(TypeListField); //ajout

        this.panelLabel.add(new JLabel("Prix max :"));  //champ de saisie du prix
        this.panelLabel.add(prixTextField); //ajout

        this.panelLabel.add(new JLabel("Taille :"));    //champ de saisie de la taille
        this.panelLabel.add(tailleTextField);   //ajout

        this.panelLabel.add(new JLabel("Ville : "));    //champ de sélection des villes
        this.panelLabel.add(villeTextAppart);   //ajout

        //attribue les boutons à un controleur précis
        this.b1.addActionListener(appartement_controller);
        this.b2.addActionListener(appartement_controller);
        this.searchButton.addActionListener(appartement_controller);
        this.ajouteAppart.addActionListener(appartement_controller);

        this.tailleTextField.setText("0");  //initialise le champ à 0 (en string)
        this.prixTextField.setText("0"); //initiallise le champ à 0 (en String)

        this.searchButton.addActionListener(e -> {
                String type = TypeListField.getSelectedValue(); //donne la valeur sélectionnée | null sinon
                appartement_controller.setTypeAppart(type); //setters du type, pour actualiser la variable

                int prix = Integer.parseInt(prixTextField.getText());   //converti string vers integer car bdd en int
                appartement_controller.setPrixAppart(prix); //setter du prix appart, pour actualiser la variable en cas de changement

                int taille = Integer.parseInt(tailleTextField.getText());   //converti string vers integer car bdd en int
                appartement_controller.setTailleAppart(taille); //setter de la taille, pour actualiser en cas de changement

                String ville = villeTextAppart.getSelectedValue();
                appartement_controller.setVilleAppart(ville);
        });

        this.global.setLayout(new BorderLayout());
        this.panelButtons.setBackground(Color.DARK_GRAY);   //couleur de fond des button
        this.panelLabel.setBackground(Color.GRAY);  //couleur de fond des panels

        this.panelButtons.setSize(1000, 1000);

        this.global.add(this.panelButtons, BorderLayout.PAGE_START);
        this.global.add(this.panelLabel, BorderLayout.PAGE_END);

        this.panelButtons.add(this.b1);
        this.panelButtons.add(this.b2);
        this.panelButtons.add(this.ajouteAppart);
        this.panelLabel.add(this.searchButton);

        this.add(this.global); //ajout du global au panel général
    }

    public void afficheAppart(ResultSet rs) throws SQLException {    //cette procédure permet l'affiche de deux demandes du controleur -> afficheALl et afficheRqst

        JTextArea textArea = new JTextArea(); //champ de texte
        textArea.setEditable(false);    //non modifiable

        StringBuilder sb = new StringBuilder(); //création de la chaine

        if(rs != null){
            System.out.println("affiche appartements demandé"); //jeu de test
            while (rs.next()) { //parcours du résultat via une bouvle
                sb.append("Prix location : " + rs.getString("prix_log") + "\n");
                sb.append("Prix charge: " + rs.getString("prix_charg") + "\n");
                sb.append("Adresse : " + rs.getString("rue") + "\n");
                sb.append("Ville : " + rs.getString("ville") + "\n");
                sb.append("CP : " + rs.getString("CP") + "\n");
                sb.append("Etage : " + rs.getString("etage") + "\n");
                sb.append("Préavis : " + rs.getString("preavis") + "\n");
                sb.append("Ascenseur : " + rs.getString("ascenseur") + "\n");
                sb.append("Date libre : " + rs.getString("date_libre") + "\n");
                sb.append("Taille : " + rs.getString("taille") + "m2\n");
                sb.append("Type : " + rs.getString("type") + "\n");
                sb.append("\n");
            }
            JScrollPane scrollPane = new JScrollPane(textArea); //ajout d'une liste déroulante
            scrollPane.setPreferredSize(new Dimension(400, 300));   //initialisation de la taille

            this.global.add(scrollPane, BorderLayout.CENTER);   //ajout au global
            textArea.setText(sb.toString());
        }
        else{
            System.out.println("Aucun appartements trouvé");    //jeu de test, visuel dans le terminal de
            textArea.setText("Aucun appartements trouvé");
        }

        this.global.validate();
        this.global.repaint();
    }
    public void AjoutAppart() throws Exception {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        // Panneaux
        JPanel panel = new JPanel();
        JPanel buttonPanel = new JPanel();

        // Mise en forme des panneaux
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Alignement vertical
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // marge de 20px pour chaque bordure

        // Listes déroulantes pour les villes, les types d'appartements et les arrondissements
        StringBuilder listeVille = this.getVille(this.appartement_controller.getVilleDispo());
        StringBuilder listeType = this.getTypesAppart(this.appartement_controller.getType());
        StringBuilder listeArrondi = this.getArrondi(this.appartement_controller.getArrondi());

        // Labels pour les champs de saisie
        JLabel rueLabel = new JLabel("Rue:");
        JLabel villeLabel = new JLabel("Ville:");
        JLabel cpLabel = new JLabel("Code postal:");
        JLabel etageLabel = new JLabel("Etage:");
        JLabel prixLabel = new JLabel("Prix du loyer:");
        JLabel prixChargLabel = new JLabel("Prix des charges:");
        JLabel ascenseurLabel = new JLabel("Ascenseur:");
        JLabel preavisLabel = new JLabel("Préavis (en mois):");
        JLabel dateLibreLabel = new JLabel("Date de disponibilité:");
        JLabel typeLabel = new JLabel("Type:");
        JLabel arrondissementLabel = new JLabel("Arrondissement:");
        JLabel tailleLabel = new JLabel("Taille:");

        // Ajout des champs de saisie
        panel.add(rueLabel);
        panel.add(this.rue = new JTextField(20));
        panel.add(villeLabel);
        panel.add(this.villeTextAppart = new JList<>(listeVille.toString().split("\n")));
        panel.add(cpLabel);
        panel.add(this.cp = new JTextField(10));
        panel.add(etageLabel);
        panel.add(this.etage = new JTextField(5));
        panel.add(prixLabel);
        panel.add(this.prixTextField = new JTextField(10));
        panel.add(prixChargLabel);
        panel.add(this.prix_charg = new JTextField(10));
        panel.add(ascenseurLabel);
        panel.add(this.ascenseur = new JTextField());
        panel.add(preavisLabel);
        panel.add(this.preavis = new JTextField(5));
        panel.add(dateLibreLabel);
        panel.add(this.dateLibre = new JTextField(10));
        panel.add(typeLabel);
        panel.add(this.TypeListField = new JList<>(listeType.toString().split("\n")));
        panel.add(arrondissementLabel);
        panel.add(this.arrondissement = new JList<>(listeArrondi.toString().split("\n")));
        panel.add(tailleLabel);
        panel.add(this.tailleTextField = new JTextField(10));

        this.rue.setText("0");  //initialise le champ à 0 (en string)
        this.cp.setText("0");  //initialise le champ à 0 (en string)
        this.etage.setText("0");  //initialise le champ à 0 (en string)
        this.prix_charg.setText("0");  //initialise le champ à 0 (en string)
        this.ascenseur.setText("0");  //initialise le champ à 0 (en string)
        this.preavis.setText("0");  //initialise le champ à 0 (en string)
        this.dateLibre.setText("00/00/0000");  //initialise le champ à 0 (en string)
        this.tailleTextField.setText("0");  //initialise le champ à 0 (en string)
        this.prixTextField.setText("0"); //initiallise le champ à 0 (en String)

        // Boutons
        JButton ajouterBtn = new JButton("Ajouter appart");
        JButton annulerBtn = new JButton("Annuler");

        ajouterBtn.addActionListener(this.appartement_controller);

        ajouterBtn.addActionListener(e -> {
            this.appartement_controller.setRueAppart(Integer.parseInt(this.rue.getText()));
            this.appartement_controller.setVilleAppart(this.villeTextAppart.getSelectedValue());
            this.appartement_controller.setCpAppart(Integer.parseInt(this.cp.getText()));
            this.appartement_controller.setEtageAppart(Integer.parseInt(this.etage.getText()));
            this.appartement_controller.setPrixAppart(Integer.parseInt(this.prixTextField.getText()));
            this.appartement_controller.setPrix_charg(Integer.parseInt(this.prix_charg.getText()));
            this.appartement_controller.setAscenseur(Integer.parseInt(this.ascenseur.getText()));
            this.appartement_controller.setPreavis(Integer.parseInt(this.preavis.getText()));
            this.appartement_controller.setDateLibre(this.dateLibre.getText());
            this.appartement_controller.setTypeAppart(this.TypeListField.getSelectedValue());
            this.appartement_controller.setArrondisement(this.arrondissement.getSelectedValue());
            this.appartement_controller.setTailleAppart(Integer.parseInt(this.tailleTextField.getText()));
        });

        buttonPanel.add(ajouterBtn);
        buttonPanel.add(annulerBtn);

        // Ajout des panneaux au panneau principal
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);

        // Validation et redessin
        this.global.validate();
        this.global.repaint();
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
            System.out.println("Aucune ville trouvée");    //jeu de test, visuel dans le terminal
            textArea.setText("Aucune ville trouvée");
        }
        return sb;
    }
    private StringBuilder getArrondi(ResultSet rs) throws SQLException{ //pour le champ de sélection
        JTextArea textArea = new JTextArea(); //champ de texte
        textArea.setEditable(false);    //non modifiable
        StringBuilder sb = new StringBuilder(); //création de la chaine

        if(rs != null){
            while (rs.next()) { //parcours du résultat via une boucle
                sb.append(rs.getString("libelle") + "\n");
            }
            textArea.setText(sb.toString());
        }
        else{
            System.out.println("Aucun arrondissement trouvée");    //jeu de test, visuel dans le terminal
            textArea.setText("Aucune arrondissement trouvée");
        }
        return sb;
    }


}