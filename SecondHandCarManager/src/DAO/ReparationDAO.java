package DAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import Entities.Database;
import Entities.Reparation;
import Entities.Vehicule;

public class ReparationDAO {
	
	public ReparationDAO() {}
	
	public void save(Reparation reparation) {
		try {
			if(reparation.getId_reparation()!=0) {				
				PreparedStatement statement  = (PreparedStatement) Database.connexion.prepareStatement("update reparation set date_reparation=?,descriptif=?,cout=?,Id_vehicule=? where Id_reparation=?");
				statement.setDate(1,(Date) reparation.getDate_reparation());
				statement.setString(2,reparation.getDescriptif());
				statement.setDouble(3,reparation.getCout());
				statement.setInt(4,reparation.getId_vehicule());
				statement.setDouble(5,reparation.getId_reparation());
				statement.executeUpdate();				
			}else {
				PreparedStatement statement  = (PreparedStatement) Database.connexion.prepareStatement
						("insert into reparation (date_reparation,descriptif,cout,Id_vehicule) values (?,?,?,?);");
				statement.setDate(1,(Date) reparation.getDate_reparation());
				statement.setString(2,reparation.getDescriptif());
				statement.setDouble(3,reparation.getCout());
				statement.setInt(4,reparation.getId_vehicule());
				statement.executeUpdate();
			}
			System.out.println("ok");						
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("not ok");
		}
	}
	
	public Reparation getById(int Id_reparation) {		
		try {
			PreparedStatement statement=(PreparedStatement) Database.connexion.prepareStatement
					("select * from reparation where id_reparation=?");
			statement.setInt(1,Id_reparation);
			ResultSet res=statement.executeQuery();
			Reparation reparation=new Reparation();
			while(res.next()) {
				reparation.setId_reparation(res.getInt("Id_reparation"));
				reparation.setDate_reparation(res.getDate("date_reparation"));
				reparation.setDescriptif(res.getString("descriptif"));
				reparation.setCout(res.getDouble("cout"));
				reparation.setId_vehicule(res.getInt("Id_vehicule"));

			}			
			return reparation;			
		} catch (SQLException e) {
			e.printStackTrace();			
			return null;
		}		
	}
	
	public void deleteById(int id) {
		try {
			PreparedStatement statement = (PreparedStatement) Database.connexion.prepareStatement
					("delete from reparation where id_reparation=?");
			statement.setInt(1, id);
			statement.executeUpdate();

			System.out.println("DELETED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("DELETED NO");
		}
	}
	
	public void deleteById_vehicule(int id_vehicule) {
		try {
			PreparedStatement statement = (PreparedStatement) Database.connexion.prepareStatement
					("delete from reparation where id_vehicule=?");
			statement.setInt(1, id_vehicule);
			statement.executeUpdate();

			System.out.println("DELETED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("DELETED NO");
		}
	}
	
	public ArrayList<Reparation> getReparationById_vehicule(int id){
		ArrayList<Reparation> lst_rep= new ArrayList<Reparation>();
		try {
			PreparedStatement statement=(PreparedStatement) Database.connexion.prepareStatement
					("select * from reparation where Id_vehicule=?");
			statement.setInt(1,id);
			ResultSet res=statement.executeQuery();
			while(res.next()) {
				Reparation rep=new Reparation();
				rep.setId_reparation(res.getInt("Id_reparation"));
				rep.setDate_reparation(res.getDate("date_reparation"));
				rep.setDescriptif(res.getString("descriptif"));
				rep.setCout(res.getDouble("cout"));
				rep.setId_vehicule(res.getInt("Id_vehicule"));
				lst_rep.add(rep);
			}			
			return lst_rep;			
		} catch (SQLException e) {
			e.printStackTrace();			
			return null;
		}	
	}
	
	
	public ArrayList<Reparation>getAll() {
		ArrayList<Reparation>lst_rep=new ArrayList<Reparation>();

		try {
			PreparedStatement statement=(PreparedStatement) Database.connexion.prepareStatement
					("select * from reparation");			
			ResultSet res=statement.executeQuery();
			while(res.next()) {
				Reparation reparation=new Reparation();
				reparation.setId_reparation(res.getInt("Id_reparation"));
				reparation.setDate_reparation(res.getDate("date_reparation"));
				reparation.setDescriptif(res.getString("descriptif"));
				reparation.setCout(res.getInt("cout"));
				reparation.setId_vehicule(res.getInt("Id_vehicule"));
				lst_rep.add(reparation);
			}
			return lst_rep;			
		} catch (SQLException e) {
			e.printStackTrace();			
			return null;
		}	
	}
	
	
	public ArrayList<Reparation> getReparationByDate(Date min,Date max) {		
		ArrayList<Reparation>lst_rep=new ArrayList<Reparation>();
		try {
			PreparedStatement statement=(PreparedStatement) Database.connexion.prepareStatement
					("select * from reparation where date_reparation between ? and ?");
			statement.setDate(1,min);
			statement.setDate(2,max);
			ResultSet res=statement.executeQuery();
			while(res.next()) {
				Reparation reparation=new Reparation();
				reparation.setId_reparation(res.getInt("Id_reparation"));
				reparation.setDate_reparation(res.getDate("date_reparation"));
				reparation.setDescriptif(res.getString("descriptif"));
				reparation.setCout(res.getInt("cout"));
				reparation.setId_vehicule(res.getInt("Id_vehicule"));

				
				lst_rep.add(reparation);
			}			
			return lst_rep;			
		} catch (SQLException e) {
			e.printStackTrace();			
			return null;
		}	
	}

}
