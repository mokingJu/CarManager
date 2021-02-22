package Entities;

import java.sql.Date;

public class Vehicule {
	private int id_vehicule;
	private double puissance;
	private String model;
	private String constructeur ;
	private double prix;
	private int annee_model;
	private int nb_km;
	private int id_categorie;
	
	
	
	public int getNb_km() {
		return nb_km;
	}
	public void setNb_km(int nb_km) {
		this.nb_km = nb_km;
	}

	public int getAnnee_model() {
		return annee_model;
	}
	public void setAnnee_model(int annee_model) {
		this.annee_model = annee_model;
	}
		
	public int getId_categorie() {
		return id_categorie;
	}
	public void setId_categorie(int id_categorie) {
		this.id_categorie = id_categorie;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	public int getId_vehicule() {
		return id_vehicule;
	}
	public void setId_vehicule(int id_vehicule) {
		this.id_vehicule = id_vehicule;
	}
	public double getPuissance() {
		return puissance;
	}
	public void setPuissance(double puissance) {
		this.puissance = puissance;
	}
	public String getConstructeur() {
		return constructeur;
	}
	public void setConstructeur(String constructeur) {
		this.constructeur = constructeur;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Vehicule(int lid_vehicule, String lconstructeur, String lmodel, int lannee_model, double lpuissance, int lnb_km, double lprix, int lid_categorie ) {		
		this.setId_vehicule(lid_vehicule);
		this.setConstructeur(lconstructeur);
		this.setModel(lmodel);
		this.setAnnee_model(lannee_model);
		this.setPuissance(lpuissance);
		this.setNb_km(lnb_km);		
		this.setPrix(lprix);
		this.setId_categorie(lid_categorie);
	}
	
	public Vehicule(String lconstructeur, String lmodel, int lannee_model, double lpuissance, int lnb_km, double lprix, int lid_categorie ) {		
		this.setConstructeur(lconstructeur);
		this.setModel(lmodel);
		this.setAnnee_model(lannee_model);
		this.setPuissance(lpuissance);
		this.setNb_km(lnb_km);		
		this.setPrix(lprix);
		this.setId_categorie(lid_categorie);
	}
	
	public Vehicule() {}
	
	
//	@Override
//	public String toString() {
//		return "Vehicule --> id_vehicule=" + getId_vehicule() + ", puissance=" + getPuissance()+ ", model=" + getModel()
//				+ ", constructeur=" + getConstructeur() + ", prix=" + getPrix() + ", id_categorie=" + getId_categorie() ;
//				
//	}
	
}
