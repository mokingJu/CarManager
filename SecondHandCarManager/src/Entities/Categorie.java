package Entities;

public class Categorie {
	private int id_categorie;
	private String libelle;
	public int getId_categorie() {
		return id_categorie;
	}
	public void setId_categorie(int id_categorie) {
		this.id_categorie = id_categorie;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
//	public Categorie() {
//		this.setId_categorie(id_categorie);
//		this.setLibelle(libelle);
//	}
//	public Categorie(int lid_categorie,String llibelle) {
//		this.setId_categorie(lid_categorie);
//		this.setLibelle(llibelle);
//	}
	
	public Categorie(String llibelle) {
		this.setLibelle(llibelle);
	}
	public Categorie() {}
	
	
	@Override
	public String toString() {
		return getLibelle();
	}
}
