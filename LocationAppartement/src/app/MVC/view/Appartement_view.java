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

    public void afficheAppart(ResultSet rs) throws SQLException {

        // Création du JPanel qui contiendra tous les panneaux de demandes
        JPanel appartementsPanel = new JPanel();
        appartementsPanel.setLayout(new BoxLayout(appartementsPanel, BoxLayout.PAGE_AXIS));

        if(rs.next()){
            System.out.println("affiche appartements demandé"); //jeu de test

            while (rs.next()) { //parcours du résultat via une bouvle

                JPanel panel = new JPanel();
                panel.add(new JLabel("\n"));
                panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

                int numAppart = rs.getInt("numappart");

                panel.add(new JLabel("Prix location : " + rs.getString("prix_log") + "\n"));
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
                panel.add(new JLabel("\n"));

                JButton updateButton = new JButton("Modifier appart");
                JButton deleteButton = new JButton("Supprimer");

                deleteButton.addActionListener(this.appartement_controller);
                updateButton.addActionListener(this.appartement_controller);

                deleteButton.addActionListener(e -> {
                    this.appartement_controller.setIdAppart(numAppart);
                });

                updateButton.addActionListener(e -> {
                    this.appartement_controller.setIdAppart(numAppart);
                });

                // Ajout du panneau de demande au panneau conteneur
                appartementsPanel.add(panel);
                // Ajout du bouton de recherche au panneau
                panel.add(deleteButton);
                panel.add(updateButton);
            }
        }
        else{
            System.out.println("Aucun appartements trouvé");    //jeu de test, visuel dans le terminal de
        }

        JScrollPane scrollPane = new JScrollPane(appartementsPanel);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        this.global.add(scrollPane, BorderLayout.CENTER);   //ajout au global
        this.global.validate();
        this.global.repaint();
    }

    public void AjoutAppart(ResultSet rs) throws Exception {
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


        if(rs != null){
            this.rue.setText(String.valueOf(rs.getInt("rue")));
            this.cp.setText(rs.getString("CP"));
            this.etage.setText(String.valueOf(rs.getInt("etage")));  //initialise le champ à 0 (en string)
            this.prix_charg.setText(String.valueOf(rs.getInt("prix_charg")));  //initialise le champ à 0 (en string)
            this.ascenseur.setText(String.valueOf(rs.getInt("ascenseur")));  //initialise le champ à 0 (en string)
            this.preavis.setText(String.valueOf(rs.getInt("preavis")));  //initialise le champ à 0 (en string)
            this.dateLibre.setText(rs.getString("date_libre"));  //initialise le champ à 0 (en string)
            this.tailleTextField.setText(String.valueOf(rs.getInt("taille")));  //initialise le champ à 0 (en string)
            this.prixTextField.setText(String.valueOf(rs.getInt("prix_log"))); //initiallise le champ à 0 (en String)
            // Boutons
            JButton modifier = new JButton("Modifier");

            modifier.addActionListener(this.appartement_controller);
            modifier.addActionListener(e -> {
                try {
                    this.appartement_controller.setIdAppart(rs.getInt("numappart"));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
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
            buttonPanel.add(modifier);
        }
        else{
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
        }

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