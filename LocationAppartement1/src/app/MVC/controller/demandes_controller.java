package app.MVC.controller;

import app.MVC.modele.appartement_DAO;
import app.MVC.modele.demandes_DAO;
import app.MVC.view.demandes_view;
import app.MVC.view.menu_view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class demandes_controller implements ActionListener {

    private demandes_DAO demandes_dao;
    private menu_view menu_view;
    private appartement_DAO appartement_dao;

    ///////Paramètre de demandes////////
    private String typeAppart,villeAppart,ClientsDem,dateDem,arrondissement, departement;
    private int num_dem,prix_max,prix_min,tailleAppart,idCliForDem;

    public demandes_controller(demandes_DAO m, menu_view v){
        this.demandes_dao = m;
        this.menu_view = v;
        this.appartement_dao = new appartement_DAO();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()){
            case "Menu propriétaire" ->{
                try {
                    this.menu_view.setView("Menu propriétaire",null, null);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            case "Menu demandes" ->{
                try {
                    this.menu_view.setView("demandes",null, null);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            case "Afficher les demandes" ->{
                try {
                    ResultSet rs = demandes_dao.getALLDemandes();
                    this.menu_view.setView("afficheAllDemandes", rs, null);

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            case "Afficher mes demandes" ->{
                try {
                    ResultSet rs = demandes_dao.getAllDemnByIdCli(this.idCliForDem);
                    if(rs.next()){
                        this.menu_view.setView("afficheMesDemandes", rs, rs.getInt("num_cli"));
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            case "Rechercher" -> {
                try {
                    //Button de recherche sur les demandes précises
                    ResultSet res = this.appartement_dao.getAppartRqst(this.typeAppart, this.tailleAppart, this.prix_max, this.villeAppart);
                    this.menu_view.setView("afficheAppartClient", res, this.num_dem);   //setview (vue principale)
                    System.out.print(this.num_dem);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            case "Ajouter"->{
                try {
                    String[] nomPrenom = this.ClientsDem.split(" ");
                    int numclient = -1;

                    ResultSet rsNumCli = this.demandes_dao.getNumClientByNomPrenom(nomPrenom[0], nomPrenom[1]);
                    ResultSet rsIdType = this.demandes_dao.getIdtypeByType(this.typeAppart);
                    ResultSet rsIdArrondi = this.demandes_dao.getIdArronByArron(this.arrondissement);

                    if(rsIdType.next() && rsNumCli.next() && rsIdArrondi.next()) {
                        numclient = rsNumCli.getInt(1);

                        this.demandes_dao.addDemande(this.tailleAppart, this.prix_min, this.prix_max, this.villeAppart, numclient, this.dateDem, rsIdType.getInt(1), rsIdArrondi.getInt(1), this.departement);
                        System.out.println("Ajouté");

                    } else {
                        System.out.println("Données manquantes");
                    }

                } catch (Exception ex) {
                    ResultSet rsIdType = null;
                    try {
                        rsIdType = this.demandes_dao.getIdtypeByType(this.typeAppart);
                        ResultSet rsIdArrondi = this.demandes_dao.getIdArronByArron(this.arrondissement);

                        if(rsIdType.next() && rsIdArrondi.next()){
                            this.demandes_dao.addDemande(this.tailleAppart, this.prix_min, this.prix_max, this.villeAppart, this.idCliForDem, this.dateDem, rsIdType.getInt(1), rsIdArrondi.getInt(1),this.departement);
                            System.out.println("Ajouté");
                        }
                    } catch (SQLException exc) {
                        throw new RuntimeException(exc);
                    }
                }
            }

            case "Attribuer au client"->{
                try {
                    ResultSet rsDemandes = this.demandes_dao.getAllByNum_Dem(this.num_dem);

                    if(rsDemandes.next()) {
                        ResultSet rsClient = this.demandes_dao.getAllClientsByNum(rsDemandes.getInt("num_cli"));
                        if(rsClient.next()){
                            //faire un add du client vers locataires
                            this.demandes_dao.addLocataire(rsClient.getString("nom"), rsClient.getString("Prenom"), rsClient.getString("Datenaiss"), rsClient.getInt("tel"), rsClient.getInt("RIB"), rsClient.getString("mdp"));

                            //supprimer la visite / concerner / demande et enfin le client (parent ensuite enfant)
                            this.demandes_dao.deleteVisite(rsDemandes.getInt("num_cli"));
                            this.demandes_dao.deleteDemande(this.num_dem);
                            this.demandes_dao.deleteClient(rsDemandes.getInt("num_cli"));


                        }
                }
                }catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            case "Supprimer"->{
                try {
                    this.demandes_dao.deleteDemande(this.num_dem);  //supprime la demande
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            case "Retour" ->{
                try {
                    // instanciation de la vue demande
                    demandes_view v = new demandes_view(this.menu_view);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                this.menu_view.retour();    //appelle la fonction retour
                System.out.println("Retour");
            }
        }
    }


    ////////////////////////getters///////////////////////
    public ResultSet getVilleDispo() throws Exception { //ville pour le champ de sélection
        ResultSet rs = this.appartement_dao.getAllVilleAppart(); //appelle la fonction du dao
        return rs;
    }

    public ResultSet getType() throws Exception{    //type pour le champ de sélection
        ResultSet rs = appartement_dao.getTypeAppart(); //appelle la fonction du dao
        return rs;
    }
    public ResultSet getAllClients()throws Exception{   //liste de client pour la vue
        ResultSet rs = this.demandes_dao.getClient();   //appelle la fonction du dao
        return rs;
    }
    public ResultSet getAllArrondissment()throws Exception{   //liste de client pour la vue
        ResultSet rs = this.demandes_dao.getLibelleArrondi();   //appelle la fonction du dao
        return rs;
    }

    public ResultSet getAlldepartement()throws Exception{   //liste de client pour la vue
        ResultSet rs = this.appartement_dao.getAllDepartement();   //appelle la fonction du dao
        return rs;
    }
    public int getNum_Cli(){return this.idCliForDem;}

    /////////////////////setter////////////////////////
    public void setNum_dem(int num){this.num_dem = num;}
    public void setNum_Cli(int num){this.idCliForDem = num;}
    public void setPrix_max(int prix){this.prix_max = prix;}
    public void setPrix_min(int prix){this.prix_min = prix;}
    public void setTailleAppart(int taille){this.tailleAppart = taille;}
    public void setTypeAppart(String type){this.typeAppart = type;}
    public void setVilleAppart(String ville){this.villeAppart = ville;}
    public void setClientsDem(String client){this.ClientsDem = client;}
    public void setDateDem(String date){this.dateDem = date;}

    public void setArrondissement(String arrondissement){this.arrondissement = arrondissement;}
    public void setDepartement(String departement){this.departement = departement;}
}
