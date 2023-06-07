package app.MVC.controller;

import app.MVC.modele.appartement_DAO;
import app.MVC.view.Appartement_view;
import app.MVC.view.menu_view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

/*
 * Classe Controleur pour les appartements
 */
public class Appartement_controller implements ActionListener{
    private appartement_DAO appartement_dao;
    private Appartement_view appartement_view;
    private menu_view menuView;
    private int rueNum,cpAppart,etageAppart,prix_charg,ascenseur,preavis,prixAppart,tailleAppart;
    private String dateLibre,arrondisement,typeAppart,villeAppart;
    private int Num_cli,num_prop;

    public Appartement_controller(appartement_DAO m, menu_view v){
        this.appartement_dao = m;
        this.menuView = v;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            this.appartement_view = new Appartement_view(this.menuView);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        switch (e.getActionCommand()) {
            case "Menu appartements" -> {
                try {
                    this.menuView.setView("appart", null, null);  //setView a besoin de deux paramètres
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            case "Afficher appartements" -> {
                try {
                    ResultSet rs = appartement_dao.getALLAppart();
                    this.menuView.setView("afficheAppartRqst", rs, null);  //setView a besoin de deux paramètres
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            case "Rechercher" -> {
                try {
                    ResultSet rs = appartement_dao.getAppartRqst(this.typeAppart, this.tailleAppart, this.prixAppart, this.villeAppart);
                    this.menuView.setView("afficheAppartRqst", rs, null);  //setView a besoin de deux paramètres
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            case "Afficher les appartements disponibles" -> {
                try {
                    ResultSet rs = appartement_dao.getAllAppartDispo();
                    if(rs.next()){
                        this.menuView.setView("afficheAppartDispo", rs, this.Num_cli);  //setView a besoin de deux paramètres
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            case "Afficher mes appartements" -> {
                try {
                    ResultSet rs = appartement_dao.getMesAppart(this.num_prop);
                    if(rs.next()){
                        this.menuView.setView("afficheMesAppart", rs, this.num_prop);  //setView a besoin de deux paramètres
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            case "Ajouter" -> {
                try {
                    this.menuView.setView("AjouterAppart", null, null);  //setView a besoin de deux paramètres
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            case "Ajouter appart" -> {
                try {
                   ResultSet idArrondi = this.appartement_dao.getIdArronByArron(this.arrondisement);
                   ResultSet idType = this.appartement_dao.getIdtypeByType(this.typeAppart);

                   if(idArrondi.next() && idType.next()){
                       this.appartement_dao.addAppartement(this.rueNum, this.villeAppart, this.cpAppart, this.etageAppart, this.prixAppart, this.prix_charg, this.ascenseur, this.preavis, this.dateLibre, idType.getInt("id_typeAppart"), idArrondi.getInt("arrondissement_num"), this.tailleAppart);
                       System.out.println("Ajouté");
                   }

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            case "Retour" -> {
                System.out.println("retour");
                this.menuView.retour();
            }
        }
    }

    //////////////////////////Getters//////////////////////////
    public ResultSet getVilleDispo() throws Exception { //ville pour le champ de sélection
        ResultSet rs = appartement_dao.getAllVilleAppart(); //appelle la fonction du dao
        return rs;
    }

    public ResultSet getType() throws Exception{    //type pour le champ de sélection
        ResultSet rs = appartement_dao.getTypeAppart(); //appelle la fonction du dao
        return rs;
    }
    public ResultSet getArrondi() throws Exception{    //type pour le champ de sélection
        ResultSet rs = appartement_dao.getAllArrondi(); //appelle la fonction du dao
        return rs;
    }

    ////////////////////////////Setters//////////////////////////
    public void setRueAppart(int RueAppart) {this.rueNum = RueAppart;}
    public void setCpAppart(int CP) {this.cpAppart = CP;}
    public void setEtageAppart(int etage) {this.etageAppart = etage;}
    public void setPrix_charg(int prix_charg) {this.prix_charg = prix_charg;}

    public void setAscenseur(int ascenseur) {this.ascenseur = ascenseur;}
    public void setPreavis(int preavis) {this.preavis = preavis;}
    public void setDateLibre(String dateLibre) {this.dateLibre = dateLibre;}
    public void setPrixAppart(int prixAppart) {this.prixAppart = prixAppart;}
    public void setTailleAppart(int tailleAppart) {this.tailleAppart = tailleAppart;}
    public void setTypeAppart(String typeAppart){this.typeAppart = typeAppart;}
    public void setVilleAppart(String ville){this.villeAppart = ville;}
    public void setArrondisement(String arrondisement) {this.arrondisement = arrondisement;}

    public void setNum_cli(int numCli){this.Num_cli = numCli;}
    public void setNum_prop(int num_prop){this.num_prop = num_prop;}

}
