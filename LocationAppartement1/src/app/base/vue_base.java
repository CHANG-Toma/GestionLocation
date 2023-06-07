package app.base;

import javax.swing.*;
import java.awt.*;

public class vue_base extends JFrame {
    private JButton b1,b2,btnNew;

    private JPanel panelButtons;
    private JPanel panelLabel;
    private JPanel global;

    public vue_base() {
        this.panelButtons = new JPanel();   //panel buttons
        this.panelLabel = new JPanel();     //panel label
        this.global = new JPanel();         //panel global

        this.b1 = new JButton("Retour");
        this.b2 = new JButton("Afficher les ...");
        this.btnNew = new JButton("Ajouter");

        //this.b1.addActionListener(this.leCtrlSpe);
        //this.b2.addActionListener(this.leCtrlSpe);
        //this.btnNew.addActionListener(this.leCtrlSpe);

        /*  exemple de si je veux appeler une fonction de la classe du ctrl:
        this.btnNew.addActionListener(e -> {
            String ville = this.Ville.getSelectedValue(); //donne la valeur sélectionnée | null sinon
            demandes_controller.setVilleAppart(ville); //setters de la ville, pour actualiser la variable
        });*/

        //////////////////ajout//////////////////
        this.global.setLayout(new BorderLayout());
        this.panelButtons.setBackground(Color.DARK_GRAY);   //couleur du panel button
        this.panelLabel.setBackground(Color.GRAY);  //couleur du panel label

        this.panelButtons.setSize(1000, 1000); //setter de la taille

        this.global.add(this.panelButtons, BorderLayout.PAGE_START); //affichage du haut, ca permettra de pas supprimer la vue
        this.global.add(this.panelLabel, BorderLayout.PAGE_END);    //affichage du bas, ca permettra de pas supprimer la vue

        this.panelButtons.add(this.b1); //ajout des button à leurs panels
        this.panelButtons.add(this.b2); //ajout des button à leurs panels
        this.panelLabel.add(this.btnNew);
    }

    public void showView() {
        pack();
        setVisible(true);
    }
}
