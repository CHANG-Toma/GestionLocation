package app.base;

import app.MVC.view.menu_view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controleur_base implements ActionListener {
    private menu_view menuView;
    //inclure la vue spécifique

    public controleur_base(){
        //initialisé le dao et le menu view
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            //this.appartement_view = new Appartement_view(this.menuView);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        //switch case pour différencier les fonctionnalitées des buttons
        switch (e.getActionCommand()) {
            case "Menu appartements" -> {
                try {
                    this.menuView.setView("nomFonc", null, null);  //setView a besoin de deux paramètres
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            case "Retour" -> {
                System.out.println("retour"); //jeu de test
                this.menuView.retour();
            }
        }
    }

    //peut aussi mettre des getters et setters pour changer / ajouter une valeur

}
