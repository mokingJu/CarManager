package Entities;

import java.util.Date;

public class Reparation {
	
	private int Id_reparation;
	private Date date_reparation;
	private String descriptif;
	private double cout;
	private int Id_vehicule;
	
	public int getId_reparation() {
		return Id_reparation;
	}
	public void setId_reparation(int id_reparation) {
		Id_reparation = id_reparation;
	}
	public Date getDate_reparation() {
		return date_reparation;
	}
	public void setDate_reparation(Date date_reparation) {
		this.date_reparation = date_reparation;
	}
	public String getDescriptif() {
		return descriptif;
	}
	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	public double getCout() {
		return cout;
	}
	public void setCout(double cout) {
		this.cout = cout;
	}
	public int getId_vehicule() {
		return Id_vehicule;
	}
	public void setId_vehicule(int id_vehicule) {
		Id_vehicule = id_vehicule;
	}

	public Reparation(int lid_reparation, Date ldate_reparation, String ldescriptif, double lcout, int lid_vehicule) {
		this.setId_reparation(lid_reparation);
		this.setDate_reparation(ldate_reparation);
		this.setDescriptif(ldescriptif);
		this.setCout(lcout);
		this.setId_vehicule(lid_vehicule);
	}
	
	public Reparation( Date ldate_reparation, String ldescriptif, double lcout, int lid_vehicule) {
		this.setDate_reparation(ldate_reparation);
		this.setDescriptif(ldescriptif);
		this.setCout(lcout);
		this.setId_vehicule(lid_vehicule);
	}
	
	public Reparation(){}
}
