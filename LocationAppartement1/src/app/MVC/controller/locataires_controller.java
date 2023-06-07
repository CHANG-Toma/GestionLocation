package app.MVC.controller;

import app.MVC.modele.appartement_DAO;
import app.MVC.modele.locataires_DAO;
import app.MVC.view.locataires_view;
import app.MVC.view.menu_view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

/*
 * Classe Controleur pour les appartements
 */
public class locataires_controller implements ActionListener{
    private locataires_DAO locataires_dao;
    private appartement_DAO appartement_dao;
    private locataires_view locataires_view;
    private menu_view menuView;
    private String Client;
    private int Appart;
    //////////CLIENT////////////
    private String nom,prenom,adresse,ville,Datenaiss,mdp;
    private int id,cp,tel,RIB;

    public locataires_controller(locataires_DAO m, menu_view v){    //constructeur
        this.locataires_dao = m;
        this.menuView = v;
        this.appartement_dao = new appartement_DAO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case "Menu locataires / Clients" -> {
                try {
                    this.menuView.setView("locataires", null, 0);  //menu view est une vue principale qui permet
                } catch (Exception ex) {                                //de pointer vers la bonne vue
                    throw new RuntimeException(ex);
                }
            }

            case "Afficher les locataires" -> {
                try {
                    ResultSet rs = locataires_dao.getAllLocataires();   //stock les données des locataires dans rs
                    if(rs.next()){
                        this.menuView.setView("afficheAllLocataires", rs, 0); //menu view est une vue principale qui permet
                    }
                } catch (Exception ex) {   // de pointer vers la bonne vue
                    throw new RuntimeException(ex);
                }
            }

            case "Afficher les clients" -> {
                try {
                    ResultSet rs = locataires_dao.getAllClients();   //stock les données des locataires dans rs
                    if(rs.next()){
                        this.menuView.setView("afficheClients", rs, 0); //menu view est une vue principale qui permet
                    }
                } catch (Exception ex) {                                    // de pointer vers la bonne vue
                    throw new RuntimeException(ex);
                }
            }

            case "Ajouter locataire" ->{
                System.out.println(this.Appart);
                System.out.println(this.Client);
            }

            case "Ajouter Client" ->{
                try {
                    this.locataires_dao.addClient(this.nom, this.prenom, this.adresse, this.ville, this.cp, this.tel, this.Datenaiss, this.RIB, this.mdp);
                    System.out.println("Client ajouté");

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                //System.out.print(this.prenom);
            }

            case "Modifier Client" ->{
                try {
                    System.out.println("Modifier client");
                    ResultSet rs = this.locataires_dao.getAllClientById(this.id);
                    if(rs.next()){
                        this.menuView.setView("VueModifClient", rs, 1);  //menu view est une vue principale qui permet
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                //System.out.print(this.prenom);
            }

            case "Modifier" ->{
                try {
                    this.locataires_dao.updateClient(this.nom, this.prenom, this.adresse, this.ville, this.cp, this.tel, this.Datenaiss, this.RIB, this.mdp, this.id);
                    System.out.println("Modifié");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                //System.out.print(this.prenom);
            }

            case "Supprimer" ->{
                try {
                    this.locataires_dao.DeleteClient(this.id);
                    System.out.println("Supprimé");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                //System.out.print(this.prenom);
            }

            case "Retour" -> {
                System.out.println("retour");
                this.menuView.retour();
            }
        }
    }
    public void setClient(String client){this.Client = client;}
    public void setAppart(int appart){this.Appart = appart;}


    ///////////////////////CLIENT///////////////////////////

    public void setId(int id){this.id = id;}

    public void setNom(String nom){this.nom = nom;}

    public void setPrenom(String prenom){this.prenom = prenom;}
    public void setAdresse(String adresse){this.adresse = adresse;}

    public void setVille(String ville){this.ville = ville;}
    public void setCp(int cp){this.cp = cp;}
    public void setTel(int tel){this.tel = tel;}
    public void setDatenaiss(String dateNaiss){this.Datenaiss = dateNaiss;}
    public void setRIB(int rib){this.RIB = rib;}
    public void setMdp(String mdp){this.mdp = mdp;}



    public ResultSet getVilleDispo() throws Exception { //ville pour le champ de sélection
        ResultSet rs = this.appartement_dao.getAllVilleAppart(); //appelle la fonction du dao
        return rs;
    }
}