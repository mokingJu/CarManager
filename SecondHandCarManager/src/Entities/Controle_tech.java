package Entities;

import java.util.Date;

public class Controle_tech {
	private Date date_debut_ct;
	private Date date_fin_ct;
	private double cout_ct;
	private int Id_vehicule;
	private int Id_ct;
	
	public int getId_ct() {
		return Id_ct;
	}
	public void setId_ct(int id_ct) {
		Id_ct = id_ct;
	}
	public Date getDate_debut_ct() {
		return date_debut_ct;
	}
	public void setDate_debut_ct(Date date_debut_ct) {
		this.date_debut_ct = date_debut_ct;
	}
	public Date getDate_fin_ct() {
		return date_fin_ct;
	}
	public void setDate_fin_ct(Date date_fin_ct) {
		this.date_fin_ct = date_fin_ct;
	}
	public double getCout_ct() {
		return cout_ct;
	}
	public void setCout_ct(double cout_ct) {
		this.cout_ct = cout_ct;
	}
	public int getId_vehicule() {
		return Id_vehicule;
	}
	public void setId_vehicule(int id_vehicule) {
		Id_vehicule = id_vehicule;
	}
	
	public Controle_tech(int lId_ct, Date ldate_debut_ct, Date ldate_fin_ct, double lcout_ct, int lId_vehicule ) {
		this.setId_ct(lId_ct);
		this.setDate_debut_ct(ldate_debut_ct);
		this.setDate_fin_ct(ldate_fin_ct);
		this.setCout_ct(lcout_ct);
		this.setId_vehicule(lId_vehicule);		
	}
	
	public Controle_tech(Date ldate_debut_ct, Date ldate_fin_ct, double lcout_ct, int lId_vehicule ) {
		this.setDate_debut_ct(ldate_debut_ct);
		this.setDate_fin_ct(ldate_fin_ct);
		this.setCout_ct(lcout_ct);
		this.setId_vehicule(lId_vehicule);		
	}
	
	public Controle_tech() {}
	
//	public String toString() {
//		return 
//	}

}
