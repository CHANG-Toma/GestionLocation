package app.MVC.controller;

import app.MVC.modele.clients_DAO;
import app.MVC.view.demandes_view;
import app.MVC.view.menu_view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class clients_controller implements ActionListener {

    private clients_DAO clients_dao;
    private menu_view menu_view;
    private int idAppart, idClient;
    private String Client,date;

    public clients_controller(clients_DAO m, menu_view v){
        this.clients_dao = m;
        this.menu_view = v;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case "Afficher les visites" ->{
                try {
                    this.clients_dao = new clients_DAO();
                    ResultSet rs = this.clients_dao.getAllVisites();
                    if(rs.next()){
                        this.menu_view.setView("afficheLesVisites", rs, null);
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            case "Ajouter" ->{
                try {
                    this.menu_view.setView("Ajouter", null, null);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            case "Ajouter Visite" ->{
                try {
                    String[] nomPrenom = this.Client.split(" ");
                    ResultSet idClient = this.clients_dao.getIdByNameClient(nomPrenom[0], nomPrenom[1]);

                    if(idClient.next()){
                        this.clients_dao.addVisite(this.idAppart, idClient.getInt("id"), this.date);
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            case "Supprimer" ->{
                try {
                    this.clients_dao.deleteVisite(this.idClient , this.idAppart, this.date);
                } catch (Exception ex) {
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


    ///////////////////////GETTERS////////////////////////////
    public ResultSet getAllClients() throws SQLException {return this.clients_dao.getAllClient();}
    public ResultSet getAppartDispo() throws Exception {return this.clients_dao.getAllAppartDispo();}


    ///////////////////////SETTERS//////////////////////////////

    public void setClient(String Client){this.Client = Client;}
    public void setIdAppart(int idAppart){this.idAppart = idAppart;}

    public void setDate(String date){this.date = date;}
    public void setIdClient(int idClient){this.idClient = idClient;}

}
