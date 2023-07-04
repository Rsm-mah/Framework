package model;

import etu1877.framework.Url_annotation;
import modelview.ModelView;

import java.util.ArrayList;

public class Emp {
    String nom;
    String prenom;

    public Emp() {

    }

    public Emp(String nom, String prenom) {
        this.setNom(nom);
        this.setPrenom(prenom);
    }

    @Url_annotation(url = "/getAll")
    public ModelView getAll(){
        ModelView modelview = new ModelView();
        ArrayList<String> listString = new ArrayList<String>();
        listString.add("Rasamisoa");
        listString.add("Mahefa");

        modelview.addItem("name", listString);
        modelview.setUrl("Emp.jsp");

        return modelview;
    }

    @Url_annotation(url = "/AffichageEmp")
    public ModelView AffichageEmp(){
        ModelView modelview = new ModelView();     
        modelview.addItem("emp", this);
        modelview.setUrl("AffichageEmp.jsp");

        return modelview;
    }

    @Url_annotation(url = "/AffichageEmp2")
    public ModelView AffichageEmp2(String nom, String prenom) {
        ModelView modelView = new ModelView();

        Emp emp = new Emp();
        emp.setNom(nom);
        emp.setPrenom(prenom);

        modelView.addItem("emp", emp);
        modelView.setUrl("AffichageEmp.jsp");

        return modelView;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
