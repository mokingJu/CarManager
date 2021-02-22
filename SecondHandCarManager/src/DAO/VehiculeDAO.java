package DAO;

//import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

//import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Entities.Database;
import Entities.Vehicule;

public class VehiculeDAO {

	public VehiculeDAO() {}
	
	public void save(Vehicule vehicule) {
		try {
			if(vehicule.getId_vehicule()!=0) {				
				PreparedStatement statement  = (PreparedStatement) Database.connexion.prepareStatement("update vehicule set puissance=?,model=?,annee_model=?,constructeur=?,nb_km=?,prix=?,id_categorie=? where Id_vehicule=?");
				statement.setDouble(1,vehicule.getPuissance());
				statement.setString(2,vehicule.getModel());
				statement.setInt(3,vehicule.getAnnee_model());
				statement.setString(4,vehicule.getConstructeur());
				statement.setInt(5,vehicule.getNb_km() );
				statement.setDouble(6,vehicule.getPrix());
				statement.setInt(7,vehicule.getId_categorie());
				statement.setInt(8, vehicule.getId_vehicule());
				statement.executeUpdate();				
			}else {
				PreparedStatement statement  = (PreparedStatement) Database.connexion.prepareStatement
						("insert into vehicule (puissance,model,annee_model,constructeur,nb_km,prix,id_categorie) values (?,?,?,?,?,?,?);");
				statement.setDouble(1,vehicule.getPuissance());
				statement.setString(2,vehicule.getModel());
				statement.setInt(3,vehicule.getAnnee_model());
				statement.setString(4,vehicule.getConstructeur());
				statement.setInt(5,vehicule.getNb_km() );
				statement.setDouble(6,vehicule.getPrix());
				statement.setInt(7,vehicule.getId_categorie());
				statement.executeUpdate();//***
			}
			System.out.println("ok");						
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(" not ok");
		}
	}
	
	
	public Vehicule getById(int Id_vehicule) {		
		try {
			PreparedStatement statement=(PreparedStatement) Database.connexion.prepareStatement
					("select * from vehicule where id_vehicule=?");
			statement.setInt(1,Id_vehicule);
			ResultSet res=statement.executeQuery();
			
			Vehicule vehicule=new Vehicule();
			while(res.next()) {
				vehicule.setId_vehicule(res.getInt("Id_vehicule"));
				vehicule.setPuissance(res.getInt("puissance"));
				vehicule.setModel(res.getString("model"));
				vehicule.setAnnee_model(res.getInt("annee_model"));
				vehicule.setConstructeur(res.getString("constructeur"));
				vehicule.setNb_km(res.getInt("nb_km"));
				vehicule.setPrix(res.getInt("prix"));	
				vehicule.setId_categorie(res.getInt("Id_categorie"));
			}			
			return vehicule;			
		} catch (SQLException e) {
			e.printStackTrace();			
			return null;
		}		
	}
	
	public ArrayList<Vehicule>getVehicule_ControleTech_less(){
		ArrayList<Vehicule>lst_veh=new ArrayList<Vehicule>();
		try {
			PreparedStatement statement=(PreparedStatement) Database.connexion.prepareStatement
					("select * from vehicule where not exists(select * from controle_tech where controle_tech.id_vehicule=vehicule.id_vehicule)");
			ResultSet res=statement.executeQuery();
			while(res.next()) {
				Vehicule vehicule=new Vehicule();
				vehicule.setId_vehicule(res.getInt("Id_vehicule"));
				vehicule.setPuissance(res.getInt("puissance"));
				vehicule.setModel(res.getString("model"));
				vehicule.setAnnee_model(res.getInt("annee_model"));
				vehicule.setConstructeur(res.getString("constructeur"));
				vehicule.setNb_km(res.getInt("nb_km"));
				vehicule.setPrix(res.getInt("prix"));	
				vehicule.setId_categorie(res.getInt("Id_categorie"));
				lst_veh.add(vehicule);
			}			
			return lst_veh;			
		} catch (SQLException e) {
			e.printStackTrace();			
			return null;
		}			
	}
	
	public ArrayList<Vehicule>getVehicule_Reparation_less(){
		ArrayList<Vehicule>lst_veh=new ArrayList<Vehicule>();
		try {
			PreparedStatement statement=(PreparedStatement) Database.connexion.prepareStatement
					("select * from vehicule where not exists(select * from reparation where reparation.id_vehicule=vehicule.id_vehicule)");
			ResultSet res=statement.executeQuery();
			while(res.next()) {
				Vehicule vehicule=new Vehicule();
				vehicule.setId_vehicule(res.getInt("Id_vehicule"));
				vehicule.setPuissance(res.getInt("puissance"));
				vehicule.setModel(res.getString("model"));
				vehicule.setAnnee_model(res.getInt("annee_model"));
				vehicule.setConstructeur(res.getString("constructeur"));
				vehicule.setNb_km(res.getInt("nb_km"));
				vehicule.setPrix(res.getInt("prix"));	
				vehicule.setId_categorie(res.getInt("Id_categorie"));
				lst_veh.add(vehicule);
			}			
			return lst_veh;			
		} catch (SQLException e) {
			e.printStackTrace();			
			return null;
		}			
	}
	

	public ArrayList<Vehicule> getVehiculeById_categorie(int Id_categorie) {		
		ArrayList<Vehicule>lst_vehicule=new ArrayList<Vehicule>();
		try {
			PreparedStatement statement=(PreparedStatement) Database.connexion.prepareStatement
					("select * from vehicule where Id_categorie=?");
			statement.setInt(1,Id_categorie);
			ResultSet res=statement.executeQuery();
			while(res.next()) {
				Vehicule vehicule=new Vehicule();
				vehicule.setId_vehicule(res.getInt("Id_vehicule"));
				vehicule.setModel(res.getString("model"));
				vehicule.setNb_km(res.getInt("nb_km"));
				vehicule.setPrix(res.getInt("prix"));	
				lst_vehicule.add(vehicule);
			}			
			return lst_vehicule;			
		} catch (SQLException e) {
			e.printStackTrace();			
			return null;
		}		
	}
	
	public ArrayList<Vehicule> getVehiculeByConstructor(String constructeur) {		
		ArrayList<Vehicule>lst_vehicule=new ArrayList<Vehicule>();
		try {
			PreparedStatement statement=(PreparedStatement) Database.connexion.prepareStatement
					("select * from vehicule where constructeur=?");
			statement.setString(1,constructeur);
			ResultSet res=statement.executeQuery();

			while(res.next()) {
				Vehicule vehicule=new Vehicule();
				vehicule.setId_vehicule(res.getInt("Id_vehicule"));
				vehicule.setModel(res.getString("model"));
				vehicule.setConstructeur(res.getString("constructeur"));
				vehicule.setPrix(res.getInt("prix"));
				vehicule.setId_categorie(res.getInt("Id_categorie"));

				lst_vehicule.add(vehicule);
			}			
			return lst_vehicule;			
		} catch (SQLException e) {
			e.printStackTrace();			
			return null;
		}	
	}
	
	public ArrayList<Vehicule> getVehiculeByPrice(double min,double max) {		
		ArrayList<Vehicule>lst_vehicule=new ArrayList<Vehicule>();
		try {
			PreparedStatement statement=(PreparedStatement) Database.connexion.prepareStatement
					("select * from vehicule where prix between ? and ?");
			statement.setDouble(1,min);
			statement.setDouble(2,max);
			ResultSet res=statement.executeQuery();
			while(res.next()) {
				Vehicule vehicule=new Vehicule();
				vehicule.setId_vehicule(res.getInt("Id_vehicule"));
				vehicule.setModel(res.getString("model"));
				vehicule.setConstructeur(res.getString("constructeur"));
				vehicule.setPrix(res.getInt("prix"));
				vehicule.setId_categorie(res.getInt("Id_categorie"));				
				lst_vehicule.add(vehicule);
			}			
			return lst_vehicule;			
		} catch (SQLException e) {
			e.printStackTrace();			
			return null;
		}	
	}
		
	
	
	public ArrayList<Vehicule> getVehiculeByCategorie_Constructor(String constructeur, String libelle) {		
		ArrayList<Vehicule>lst_vehicule=new ArrayList<Vehicule>();
		try {
			PreparedStatement statement=(PreparedStatement) Database.connexion.prepareStatement
					("select * from vehicule where constructeur=? and id_categorie in(select id_categorie from categorie where libelle=?)");			
			statement.setString(1,constructeur);
			statement.setString(2,libelle);
			ResultSet res=statement.executeQuery();
			while(res.next()) {
				Vehicule vehicule=new Vehicule();
				vehicule.setId_vehicule(res.getInt("Id_vehicule"));
				vehicule.setModel(res.getString("model"));
				//vehicule.setConstructeur(res.getString("constructeur"));
				vehicule.setPrix(res.getInt("prix"));
				vehicule.setId_categorie(res.getInt("Id_categorie"));//***

				lst_vehicule.add(vehicule);
			}			
			return lst_vehicule;			
		} catch (SQLException e) {
			e.printStackTrace();			
			return null;
		}	
	}
	
	//********************************************************************************************************************************//

	
	public Vehicule getVehiculeByElemts(double puissance,double nb_km, String model,double prix) {		
		try {
			PreparedStatement statement=(PreparedStatement) Database.connexion.prepareStatement
					("select * from vehicule where puissance=? and model=? and nb_km=? and prix=?");			
			statement.setDouble(1,puissance);
			statement.setString(2,model);
			statement.setDouble(3,nb_km);
			statement.setDouble(4,prix);
			ResultSet res=statement.executeQuery();
			Vehicule vehicule=new Vehicule();
			while(res.next()) {
				vehicule.setId_vehicule(res.getInt("Id_vehicule"));
//				vehicule.setModel(res.getString("model"));
//				//vehicule.setConstructeur(res.getString("constructeur"));
//				vehicule.setPrix(res.getInt("prix"));
//				//vehicule.setId_categorie(res.getInt("Id_categorie"));
//				//vehicule.setId_parc(res.getInt("Id_parc"));
				
			}			
			return vehicule;			
		} catch (SQLException e) {
			e.printStackTrace();			
			return null;
		}	
	}
	
	//don't use it
	public ArrayList<Vehicule> getVehiculeByCategorie_Constructor_Model(String constructeur, String libelle, String model) {		
		ArrayList<Vehicule>lst_vehicule=new ArrayList<Vehicule>();
		try {
			PreparedStatement statement=(PreparedStatement) Database.connexion.prepareStatement
					("select * from vehicule where constructeur=? and model=? and id_categorie in(select id_categorie from categorie where libelle=?)");			
			statement.setString(1,constructeur);
			statement.setString(2,model);
			statement.setString(3,libelle);
			ResultSet res=statement.executeQuery();
			while(res.next()) {
				Vehicule vehicule=new Vehicule();
				vehicule.setId_vehicule(res.getInt("Id_vehicule"));
				vehicule.setModel(res.getString("model"));
				//vehicule.setConstructeur(res.getString("constructeur"));
				vehicule.setPrix(res.getInt("prix"));
				//vehicule.setId_categorie(res.getInt("Id_categorie"));
				//vehicule.setId_parc(res.getInt("Id_parc"));
				lst_vehicule.add(vehicule);
			}			
			return lst_vehicule;			
		} catch (SQLException e) {
			e.printStackTrace();			
			return null;
		}	
	}
	
	
	
	public Set<String> getAllConstructor() {
		ArrayList<String>lst_constructeur=new ArrayList<String>();
		Set<String>set;
		try {
			PreparedStatement statement=(PreparedStatement) Database.connexion.prepareStatement
					("select constructeur from vehicule");			
			ResultSet res=statement.executeQuery();
			while(res.next()) {
				lst_constructeur.add(res.getString("constructeur"));
			}
			set=new LinkedHashSet<String>(lst_constructeur);
			return set;			
		} catch (SQLException e) {
			e.printStackTrace();			
			return null;
		}	
	}
	
	
	public ArrayList<Vehicule>getAll() {
		ArrayList<Vehicule>lst_vehicule=new ArrayList<Vehicule>();

		try {
			PreparedStatement statement=(PreparedStatement) Database.connexion.prepareStatement
					("select * from vehicule");			
			ResultSet res=statement.executeQuery();
			while(res.next()) {
				Vehicule vehicule=new Vehicule();
				vehicule.setId_vehicule(res.getInt("Id_vehicule"));
				vehicule.setPuissance(res.getInt("puissance"));
				vehicule.setModel(res.getString("model"));
				vehicule.setAnnee_model(res.getInt("annee_model"));
				vehicule.setConstructeur(res.getString("constructeur"));
				vehicule.setNb_km(res.getInt("nb_km"));
				vehicule.setPrix(res.getInt("prix"));	
				vehicule.setId_categorie(res.getInt("Id_categorie"));
				lst_vehicule.add(vehicule);
			}
			return lst_vehicule;			
		} catch (SQLException e) {
			e.printStackTrace();			
			return null;
		}	
	}
	
	
	
	public ArrayList<String> getfoo( String libelle) {		
		ArrayList<String>lst=new ArrayList<String>();
		ArrayList<String>lst2=new ArrayList<String>();
		Set<String>set;
		try {
			PreparedStatement statement=(PreparedStatement) Database.connexion.prepareStatement
					("select constructeur from vehicule where id_categorie in(select id_categorie from categorie where libelle=?)");			
			statement.setString(1,libelle);
			ResultSet res=statement.executeQuery();
			while(res.next()) {
				Vehicule vehicule=new Vehicule();
				vehicule.setConstructeur(res.getString("constructeur"));
				lst.add(vehicule.getConstructeur());
			}			
			set=new LinkedHashSet<String>(lst);
			for(String el:set) {
				lst2.add(el);
			}
			return lst2;				
		} catch (SQLException e) {
			e.printStackTrace();			
			return null;
		}	
	}
	
	
	//ne fonctionne pas
	public int getLastId_vehicule()
	{
		int id=0;
		try {
			PreparedStatement statement=(PreparedStatement) Database.connexion.prepareStatement
					("select MAX(Id_vehicule) FROM vehicule");			
			ResultSet res=statement.executeQuery();
			if(res.next()) {				
				id=res.getInt("Id_vehicule");
			}						
			return id;				
		} catch (SQLException e) {
			e.printStackTrace();			
			return (Integer) null;
		}	
	}	
		
	public void deleteById(int id) {
		try {

			PreparedStatement statement = (PreparedStatement) Database.connexion.prepareStatement
					("delete from vehicule where id_vehicule=?");
			statement.setInt(1, id);
			statement.executeUpdate();

			System.out.println("DELETED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("DELETED NO");
		}
	}
	

}
