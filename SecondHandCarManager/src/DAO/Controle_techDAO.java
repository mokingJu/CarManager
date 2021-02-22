package DAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import Entities.Controle_tech;
import Entities.Database;
import Entities.Reparation;


public class Controle_techDAO {

	public Controle_techDAO(){}
	
	public void save(Controle_tech ct) {
		try {
			if(ct.getId_ct()!=0) {				
				PreparedStatement statement  = (PreparedStatement) Database.connexion.prepareStatement("update controle_tech set date_debut_ct=?,date_fin_ct=?,cout_ct=?,id_vehicule=? where Id_ct=?");
				statement.setDate(1,(Date) ct.getDate_debut_ct());
				statement.setDate(2,(Date) ct.getDate_fin_ct());
				statement.setDouble(3,ct.getCout_ct());
				statement.setInt(4,ct.getId_vehicule());
				statement.setInt(5,ct.getId_ct());
				statement.executeUpdate();				
			}else {
				PreparedStatement statement  = (PreparedStatement) Database.connexion.prepareStatement
						("insert into controle_tech (date_debut_ct,date_fin_ct,cout_ct,id_vehicule) values (?,?,?,?)");
				statement.setDate(1,(Date) ct.getDate_debut_ct());
				statement.setDate(2,(Date) ct.getDate_fin_ct());
				statement.setDouble(3,ct.getCout_ct());
				statement.setInt(4,ct.getId_vehicule());
				statement.executeUpdate();
			}
			System.out.println("ok");						
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(" not ok");
		}
	}
	
	
	public Controle_tech getById(int Id_ct) {		
		try {
			PreparedStatement statement=(PreparedStatement) Database.connexion.prepareStatement
					("select * from Controle_tech where id_ct=?");
			statement.setInt(1,Id_ct);
			ResultSet res=statement.executeQuery();			
			Controle_tech ct=new Controle_tech();
			while(res.next()) {				
				ct.setId_ct(res.getInt("Id_ct"));
				ct.setDate_debut_ct(res.getDate("date_debut_ct"));
				ct.setDate_fin_ct(res.getDate("date_fin_ct"));
				ct.setCout_ct(res.getDouble("cout_ct"));
				ct.setId_vehicule(res.getInt("Id_vehicule"));				
			}			
			return ct;			
		} catch (SQLException e) {
			e.printStackTrace();			
			return null;
		}		
	}
	
	
	public void deleteById(int Id_ct) {
		try {
			PreparedStatement statement = (PreparedStatement) Database.connexion.prepareStatement
					("delete from Controle_tech where id_ct=?");
			statement.setInt(1, Id_ct);
			statement.executeUpdate();
			System.out.println("DELETED OK");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("DELETED NO");
		}
	}
	
	
	public void deleteById_vehicule(int Id_vehicule) {
		try {
			PreparedStatement statement = (PreparedStatement) Database.connexion.prepareStatement
					("delete from Controle_tech where id_vehiculet=?");
			statement.setInt(1, Id_vehicule);
			statement.executeUpdate();
			System.out.println("DELETED OK");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("DELETED NO");
		}
	}
	
	
	
	public ArrayList<Controle_tech>getAll()
	{
		ArrayList<Controle_tech>lst_ct= new ArrayList<Controle_tech>();
		try {
			PreparedStatement statement=(PreparedStatement) Database.connexion.prepareStatement
					("select * from controle_tech ");			
			ResultSet res=statement.executeQuery();
			while(res.next()) {
				Controle_tech ct=new Controle_tech();
				ct.setId_ct(res.getInt("Id_ct"));
				ct.setDate_debut_ct(res.getDate("date_debut_ct"));
				ct.setDate_fin_ct(res.getDate("date_fin_ct"));
				ct.setCout_ct(res.getInt("cout_ct"));
				ct.setId_vehicule(res.getInt("Id_vehicule"));
				lst_ct.add(ct);
			}
			return lst_ct;			
		} catch (SQLException e) {
			e.printStackTrace();			
			return null;
		}
	}	
	
	
	
	public ArrayList<Controle_tech> getControle_techByDate(Date min,Date max) {		
		ArrayList<Controle_tech>lst_ct=new ArrayList<Controle_tech>();
		try {
			PreparedStatement statement=(PreparedStatement) Database.connexion.prepareStatement
					("select * from controle_tech where date_fin_ct between ? and ?");
			statement.setDate(1,min);
			statement.setDate(2,max);
			ResultSet res=statement.executeQuery();
			while(res.next()) {
				Controle_tech ct=new Controle_tech();
				ct.setId_ct(res.getInt("Id_ct"));
				ct.setDate_debut_ct(res.getDate("date_debut_ct"));
				ct.setDate_fin_ct(res.getDate("date_fin_ct"));
				ct.setCout_ct(res.getInt("cout_ct"));
				ct.setId_vehicule(res.getInt("Id_vehicule"));			
				lst_ct.add(ct);
			}			
			return lst_ct;			
		} catch (SQLException e) {
			e.printStackTrace();			
			return null;
		}	
	}
	

	public Controle_tech getById_vehicule(int Id_vehicule) {		

			try {
				PreparedStatement statement=(PreparedStatement) Database.connexion.prepareStatement
						("select * from Controle_tech where id_vehicule=?");
				statement.setInt(1,Id_vehicule);
				ResultSet res=statement.executeQuery();
				
				Controle_tech ct=new Controle_tech();
				while(res.next()) {					
					ct.setId_ct(res.getInt("Id_ct"));
					ct.setDate_debut_ct(res.getDate("date_debut_ct"));
					ct.setDate_fin_ct(res.getDate("date_fin_ct"));
					ct.setCout_ct(res.getDouble("cout_ct"));
					ct.setId_vehicule(res.getInt("Id_vehicule"));					
				}			
				return ct;			
			} catch (SQLException e) {
				e.printStackTrace();			
				return null;
			}
	}
		
	
}
