package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mysql.jdbc.PreparedStatement;

import Entities.Categorie;
import Entities.Database;

public class CategorieDAO {
	
	public CategorieDAO() {}

	public void save(Categorie categorie) {
		try {
			if(categorie.getId_categorie()!=0) {
				PreparedStatement statement=(PreparedStatement) Database.connexion.prepareStatement
						("update categorie set libelle=? where Id_categorie=?;");
				statement.setString(1,categorie.getLibelle());
				statement.setInt(2,categorie.getId_categorie());
				statement.executeUpdate();				
			}else {
				PreparedStatement statement=(PreparedStatement) Database.connexion.prepareStatement
						("insert into categorie (libelle) values (?);");
				statement.setString(1, categorie.getLibelle());
				statement.executeUpdate();	
			}
			System.out.println("ok");			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(" not ok");
		}
	}
	
	public Categorie getById(int Id_categorie) {
		
		try {
			PreparedStatement statement=(PreparedStatement) Database.connexion.prepareStatement
					("select * from categorie where Id_categorie=?");
			statement.setInt(1,Id_categorie);
			ResultSet res=statement.executeQuery();
			
			Categorie categorie=new Categorie();
			while(res.next()) {
				categorie.setId_categorie(res.getInt("Id_categorie"));
				categorie.setLibelle(res.getString("libelle"));				
			}			
			return categorie;			
		} catch (SQLException e) {
			e.printStackTrace();			
			return null;
		}		
	}
	
	public ArrayList<Categorie> getAll() {		
			ArrayList<Categorie>lst_cat=new ArrayList<Categorie>();
		try {
			PreparedStatement statement=(PreparedStatement) Database.connexion.prepareStatement
					("select * from categorie");
			ResultSet res=statement.executeQuery();
			
			while(res.next()) {
				Categorie categorie=new Categorie();
				categorie.setId_categorie(res.getInt("Id_categorie"));
				categorie.setLibelle(res.getString("libelle"));
				lst_cat.add(categorie);				
			}			
			return lst_cat;			
		} catch (SQLException e) {
			e.printStackTrace();			
			return null;
		}		
	}


		//for combobox
		public ArrayList<String> getAllLibelle() {		
			ArrayList<String>lst_cat_lib=new ArrayList<String>();
		try {
			PreparedStatement statement=(PreparedStatement) Database.connexion.prepareStatement
					("select libelle from categorie");
			ResultSet res=statement.executeQuery();
			
			while(res.next()) {
				String categorie;
				categorie=res.getString("libelle");
				lst_cat_lib.add(categorie);				
			}			
			return lst_cat_lib;			
		} catch (SQLException e) {
			e.printStackTrace();			
			return null;
		}		
	}

}
