package Views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import DAO.CategorieDAO;
import DAO.Controle_techDAO;
import DAO.ReparationDAO;
import DAO.VehiculeDAO;
import Entities.Categorie;
import Entities.Database;
import Entities.Vehicule;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Vehicule_details3 {

	private JFrame frame;
	
	private JTextField txtid;
	private JTextField txtconstructeur;
	private JTextField txtmodel;
	private JTextField txtprix;	
	public Vehicule vehicule;
	private JTextField txtpuissance;
	private JLabel lblPuisssance;
	private JTextField txtnb_km;
	private JTextField txtannee;
	
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vehicule_details3 window = new Vehicule_details3();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Vehicule_details3(Vehicule vehicule) {
		this.vehicule=vehicule;
		initialize();
	}
	public Vehicule_details3() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {	
		
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaption);
		frame.setBounds(100, 100, 450, 431);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblid = new JLabel("ID");
		lblid.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblid.setBounds(34, 18, 63, 24);
		frame.getContentPane().add(lblid);
		
		txtid = new JTextField();
		this.txtid.enable(false);//textbox en lecture seule
		int idt=testmpanels7.identity;
		
		txtid.setText(idt+"");
		txtid.setBounds(158, 18, 170, 24);
		frame.getContentPane().add(txtid);
		txtid.setColumns(10);
		
		txtconstructeur = new JTextField();
		txtconstructeur.setText(vehicule.getConstructeur());
		txtconstructeur.setColumns(10);
		txtconstructeur.setBounds(158, 50, 170, 27);
		frame.getContentPane().add(txtconstructeur);
		
		JLabel lblconstructeur = new JLabel("CONSTRUCTEUR");
		lblconstructeur.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblconstructeur.setBounds(34, 53, 114, 24);
		frame.getContentPane().add(lblconstructeur);
		
		txtmodel = new JTextField();
		txtmodel.setText(vehicule.getModel());
		txtmodel.setColumns(10);
		txtmodel.setBounds(158, 88, 170, 26);
		frame.getContentPane().add(txtmodel);
		
		JLabel lblmodel = new JLabel("MODEL");
		lblmodel.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblmodel.setBounds(34, 92, 94, 23);
		frame.getContentPane().add(lblmodel);
		
		txtprix = new JTextField();
		txtprix.setText(vehicule.getPrix()+"");
		txtprix.setColumns(10);
		txtprix.setBounds(158, 125, 170, 26);
		frame.getContentPane().add(txtprix);
		
		JLabel lblprix = new JLabel("PRIX");
		lblprix.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblprix.setBounds(34, 125, 94, 22);
		frame.getContentPane().add(lblprix);
		
		txtpuissance = new JTextField();
		txtpuissance.setText(vehicule.getPuissance()+"");
		txtpuissance.setColumns(10);
		txtpuissance.setBounds(158, 162, 170, 26);
		frame.getContentPane().add(txtpuissance);
		
		lblPuisssance = new JLabel("PUISSSANCE");
		lblPuisssance.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblPuisssance.setBounds(34, 166, 94, 23);
		frame.getContentPane().add(lblPuisssance);
		
		
		VehiculeDAO v_dao= new VehiculeDAO();
						
		Database.Connect();		
		//combobox categorie
		ArrayList<Categorie> arr=new ArrayList<Categorie>();
		CategorieDAO catdao= new CategorieDAO();		
		arr=catdao.getAll();
		Categorie curcat = catdao.getById(vehicule.getId_categorie());		
		JComboBox<Categorie> cbcategorie = new JComboBox<Categorie>();	
		for(Categorie el:arr) {
			cbcategorie.addItem(el);
		}			
		cbcategorie.getModel().setSelectedItem(curcat);
		
		
		
		ImageIcon icon_pen=new ImageIcon(new ImageIcon(this.getClass().getResource("/Ressources/pen.png")).getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT ));		
		JPanel panelupdate = new JPanel();
		panelupdate.setBackground(SystemColor.textHighlight);
		panelupdate.setBounds(127, 342, 103, 39);
		frame.getContentPane().add(panelupdate);
		panelupdate.setLayout(null);		
		JLabel lblpen = new JLabel("Modifier");
		lblpen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				panelupdate.setBorder(null);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelupdate.setBorder(new LineBorder(Color.GREEN));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				vehicule.setId_vehicule(idt);
				vehicule.setPuissance(Double.parseDouble(txtpuissance.getText()));
				vehicule.setModel(txtmodel.getText());
				vehicule.setConstructeur(txtconstructeur.getText());
				vehicule.setPrix(Double.parseDouble(txtprix.getText()));
				vehicule.setNb_km(Integer.parseInt(txtnb_km.getText()));
				vehicule.setAnnee_model(Integer.parseInt(txtannee.getText()));			
				Categorie catttt=(Categorie) cbcategorie.getSelectedItem();
				int newidcat=catttt.getId_categorie();
				vehicule.setId_categorie(newidcat);				
				Database.Connect();
				v_dao.save(vehicule);
				testmpanels7.b1();
			}
		});
		lblpen.setForeground(SystemColor.inactiveCaptionBorder);
		lblpen.setHorizontalAlignment(SwingConstants.CENTER);
		lblpen.setBounds(0, 0, 103, 39);
		panelupdate.add(lblpen);
		lblpen.setBackground(SystemColor.textHighlight);
		lblpen.setIcon(icon_pen);
		
		
		
		ImageIcon icon_trash=new ImageIcon(new ImageIcon(this.getClass().getResource("/Ressources/trash.png")).getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT ));	
		JPanel paneldelete = new JPanel();
		paneldelete.setLayout(null);
		paneldelete.setBackground(Color.RED);
		paneldelete.setBounds(269, 342, 103, 39);
		frame.getContentPane().add(paneldelete);		
		JLabel lbltrash = new JLabel("Supprimer");
		lbltrash.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				paneldelete.setBorder(null);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				paneldelete.setBorder(new LineBorder(Color.GREEN));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				//remove from other tables
				ReparationDAO rdao= new ReparationDAO();
				Controle_techDAO ctdao=new Controle_techDAO();
				rdao.deleteById_vehicule(idt);
				ctdao.deleteById_vehicule(idt);
				v_dao.deleteById(idt);
				testmpanels7.b1();
			}
		});
		lbltrash.setHorizontalAlignment(SwingConstants.CENTER);
		lbltrash.setForeground(SystemColor.inactiveCaptionBorder);
		lbltrash.setBackground(SystemColor.textHighlight);
		lbltrash.setBounds(0, 0, 103, 39);
		paneldelete.add(lbltrash);
		lbltrash.setIcon(icon_trash);		
				
		cbcategorie.setBounds(158, 199, 170, 26);
		frame.getContentPane().add(cbcategorie);
		
		JLabel lblNewLabel = new JLabel("CATEGORIE");
		lblNewLabel.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblNewLabel.setBounds(34, 203, 94, 22);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblnb_km = new JLabel("KILOMETRAGE");
		lblnb_km.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblnb_km.setBounds(34, 240, 114, 23);
		frame.getContentPane().add(lblnb_km);
		
		txtnb_km = new JTextField();
		txtnb_km.setText(vehicule.getNb_km()+"");
		txtnb_km.setBounds(158, 236, 170, 26);
		frame.getContentPane().add(txtnb_km);
		txtnb_km.setColumns(10);
		
		txtannee = new JTextField();
		txtannee.setText(vehicule.getAnnee_model()+"");
		txtannee.setColumns(10);
		txtannee.setBounds(158, 273, 170, 28);
		frame.getContentPane().add(txtannee);
		
		JLabel lblnb_km_1 = new JLabel("ANNEE MODEL");
		lblnb_km_1.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblnb_km_1.setBounds(34, 275, 114, 28);
		frame.getContentPane().add(lblnb_km_1);		

	}
}
