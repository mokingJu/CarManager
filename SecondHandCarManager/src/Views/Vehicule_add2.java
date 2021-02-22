package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import DAO.CategorieDAO;
import DAO.Controle_techDAO;
import DAO.ReparationDAO;
import DAO.VehiculeDAO;
import Entities.Categorie;
import Entities.Controle_tech;
import Entities.Database;
import Entities.Reparation;
import Entities.Vehicule;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.SystemColor;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Vehicule_add2 {

	private JFrame frame;
	
	public JLayeredPane layeredPane = new JLayeredPane();
	private JTextField txtpuissance;
	private JTextField txtmodel;
	private JTextField txtconstructeur;
	private JTextField txtprix;
	private JTextField txtnb_km;
	private JTextField txtannee;
	//private JTextField txtcout_rep;
	private JTextField txtcout_ct;
	
	private double puissance;
	private String model_v;
	private String constructeur;
	private double prix;
	private int annee_model;
	private int nb_km;
	private int newidcat;
	
	//private int identity_vehicule;

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
					Vehicule_add2 window = new Vehicule_add2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void SwitchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();		
	}
	/**
	 * Create the application.
	 */
	public Vehicule_add2() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 591, 420);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		layeredPane.setBounds(10, 36, 554, 317);		
		//permet de switcher sur les differents panel graphiquement
		layeredPane.setLayout(new CardLayout(0, 0));
		layeredPane.setBackground(SystemColor.inactiveCaption);
		frame.getContentPane().add(layeredPane);
		
//--------------------------------------------------------------------------------------------------------------------------------		
		
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(0, 0, 554, 306);
		layeredPane.add(panel);
		panel.setLayout(null);
		
		txtpuissance = new JTextField();
		txtpuissance.setBounds(159, 42, 185, 20);
		panel.add(txtpuissance);
		txtpuissance.setColumns(10);
		
		txtmodel = new JTextField();
		txtmodel.setBounds(159, 73, 185, 20);
		panel.add(txtmodel);
		txtmodel.setColumns(10);
		
		txtconstructeur = new JTextField();
		txtconstructeur.setBounds(159, 104, 185, 20);
		panel.add(txtconstructeur);
		txtconstructeur.setColumns(10);
		
		txtprix = new JTextField();
		txtprix.setBounds(159, 135, 185, 20);
		panel.add(txtprix);
		txtprix.setColumns(10);
		
		//JComboBox cbcategorie = new JComboBox();
		
		Vehicule v= new Vehicule();
		ArrayList<Categorie> arr=new ArrayList<Categorie>();
		CategorieDAO catdao= new CategorieDAO();
		Database.Connect();
		arr=catdao.getAll();
		Categorie curcat = catdao.getById(v.getId_categorie());		
		JComboBox<Categorie> cbcategorie = new JComboBox<Categorie>();	
		for(Categorie el:arr) {
			cbcategorie.addItem(el);
		}			
		cbcategorie.getModel().setSelectedItem(curcat);
		
		cbcategorie.setBounds(159, 170, 185, 22);
		panel.add(cbcategorie);
		
		txtnb_km = new JTextField();
		txtnb_km.setBounds(159, 209, 185, 20);
		panel.add(txtnb_km);
		txtnb_km.setColumns(10);
		
		txtannee = new JTextField();
		txtannee.setBounds(159, 240, 185, 20);
		panel.add(txtannee);
		txtannee.setColumns(10);

		
		
		
		
		JLabel lblpuissance = new JLabel("PUISSANCE");
		lblpuissance.setBounds(59, 45, 90, 14);
		panel.add(lblpuissance);
		
		JLabel lblmodel = new JLabel("MODEL");
		lblmodel.setBounds(59, 76, 90, 14);
		panel.add(lblmodel);
		
		JLabel lblconstructeur = new JLabel("CONSTRUCTEUR");
		lblconstructeur.setBounds(59, 107, 90, 14);
		panel.add(lblconstructeur);
		
		JLabel lblprix = new JLabel("PRIX");
		lblprix.setBounds(59, 138, 90, 14);
		panel.add(lblprix);
		
		JLabel lblcategorie = new JLabel("CATEGORIE");
		lblcategorie.setBounds(59, 174, 90, 14);
		panel.add(lblcategorie);
		
		JLabel lblKilometrage = new JLabel("KILOMETRAGE");
		lblKilometrage.setBounds(59, 212, 90, 14);
		panel.add(lblKilometrage);
		
		JLabel lblannee = new JLabel("ANNEE MODEL");
		lblannee.setBounds(59, 243, 90, 14);
		panel.add(lblannee);
		
		
		
//--------------------------------------------------------------------------------------------------------------------------------------		
		
		JPanel panelvalider_v = new JPanel();
		panelvalider_v.setLayout(null);
		panelvalider_v.setBackground(new Color(50, 205, 50));
		panelvalider_v.setBounds(326, 271, 103, 39);
		panel.add(panelvalider_v);
		
		JLabel lblvalider_v = new JLabel("Valider");
		lblvalider_v.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				panelvalider_v.setBorder(null);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelvalider_v.setBorder(new LineBorder(Color.DARK_GRAY));
			}
			@Override
			public void mouseClicked(MouseEvent e) {				
				puissance=Double.parseDouble(txtpuissance.getText());
				model_v=txtmodel.getText();
				constructeur=txtconstructeur.getText();
				prix=Double.parseDouble(txtprix.getText());
				annee_model=Integer.parseInt(txtannee.getText());
				nb_km=Integer.parseInt(txtnb_km.getText());								
				Categorie catttt=(Categorie) cbcategorie.getSelectedItem();
				newidcat=catttt.getId_categorie();
				System.out.print(newidcat);				
				Vehicule v=new Vehicule(constructeur,model_v,annee_model,puissance,nb_km,prix,newidcat);			
				Database.Connect();
				VehiculeDAO vdao= new VehiculeDAO();
				vdao.save(v);
				testmpanels7.b1();
			}
		});
		lblvalider_v.setHorizontalAlignment(SwingConstants.CENTER);
		lblvalider_v.setForeground(SystemColor.inactiveCaptionBorder);
		lblvalider_v.setBackground(new Color(50, 205, 50));
		ImageIcon icon=new ImageIcon(new ImageIcon(this.getClass().getResource("/Ressources/validate.png")).getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT ));
		lblvalider_v.setIcon(icon);
		lblvalider_v.setBounds(0, 0, 103, 39);
		panelvalider_v.add(lblvalider_v);
		
//**********************************************************************************		
		
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setLayout(null);
		panel_1.setBounds(0, 0, 554, 306);
		layeredPane.add(panel_1);
		
		JDateChooser dateChooser_rep = new JDateChooser();
		dateChooser_rep.setBounds(197, 45, 180, 35);
		panel_1.add(dateChooser_rep);
		
		JTextArea txtdescriptif_rep = new JTextArea();
		txtdescriptif_rep.setBounds(197, 92, 180, 85);
		panel_1.add(txtdescriptif_rep);
		
		JTextField txtcout_rep = new JTextField();
		txtcout_rep.setBounds(197, 188, 180, 35);
		panel_1.add(txtcout_rep);
		txtcout_rep.setColumns(10);
		
		
		JLabel lbldate_rep = new JLabel("Date reparation");
		lbldate_rep.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lbldate_rep.setBounds(39, 40, 148, 30);
		panel_1.add(lbldate_rep);
		
		JLabel lbldescriptif = new JLabel("Descriptif");
		lbldescriptif.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lbldescriptif.setBounds(39, 109, 148, 30);
		panel_1.add(lbldescriptif);
		
		JLabel lblNewLabel_2 = new JLabel("Cout");
		lblNewLabel_2.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblNewLabel_2.setBounds(39, 188, 134, 35);
		panel_1.add(lblNewLabel_2);
		
//------------------------------------------------------------------------------------------------------------------------------------
		
		JPanel panelvalider_rep = new JPanel();
		panelvalider_rep.setLayout(null);
		panelvalider_rep.setBackground(new Color(50, 205, 50));
		panelvalider_rep.setBounds(294, 267, 103, 39);
		panel_1.add(panelvalider_rep);		
		JLabel lblvalider_rep = new JLabel("Valider");
		lblvalider_rep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				lblvalider_rep.setBorder(null);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblvalider_rep.setBorder(new LineBorder(Color.DARK_GRAY));
			}
			@Override
			public void mouseClicked(MouseEvent e) {				
				//enregistrement reparation
				Database.Connect();
				ReparationDAO rdao= new ReparationDAO();
				VehiculeDAO vdao=new VehiculeDAO();
				Date sqldate = new Date(dateChooser_rep.getDate().getTime());
				double cout_rep=(Double.parseDouble(txtcout_rep.getText()));
				String descriptif_rep=(txtdescriptif_rep.getText());  
				Vehicule v= new Vehicule();
				v=vdao.getVehiculeByElemts(puissance, nb_km, model_v, prix);
				int last_id=v.getId_vehicule();
				//identity_vehicule=last_id;
				Reparation rep =new Reparation(sqldate,descriptif_rep,cout_rep,last_id);
				System.out.println("last id:"+last_id);//test
				rdao.save(rep);
			}
		});		
		lblvalider_rep.setHorizontalAlignment(SwingConstants.CENTER);
		lblvalider_rep.setForeground(SystemColor.inactiveCaptionBorder);
		lblvalider_rep.setBackground(new Color(50, 205, 50));
		ImageIcon icon2=new ImageIcon(new ImageIcon(this.getClass().getResource("/Ressources/validate2.png")).getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT ));
		lblvalider_rep.setIcon(icon2);
		lblvalider_rep.setBounds(0, 0, 103, 39);
		panelvalider_rep.add(lblvalider_rep);
		
//-------------------------------------------------------------------------------------------------------------------------------------------------
		
		JPanel panelajouter_rep = new JPanel();
		panelajouter_rep.setLayout(null);
		panelajouter_rep.setBackground(new Color(128, 128, 128));
		panelajouter_rep.setBounds(163, 267, 103, 39);
		panel_1.add(panelajouter_rep);
		
		JLabel lblajouter_rep = new JLabel("Ajouter new");
		panelajouter_rep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				panelajouter_rep.setBorder(null);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelajouter_rep.setBorder(new LineBorder(Color.DARK_GRAY));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				//clear component to fill new 'reparation'
				dateChooser_rep.setCalendar(null);
				txtcout_rep.setText(null);
				txtdescriptif_rep.setText(null);
			}			
		});	
		lblajouter_rep.setHorizontalAlignment(SwingConstants.CENTER);
		lblajouter_rep.setForeground(SystemColor.inactiveCaptionBorder);
		lblajouter_rep.setBackground(new Color(128, 128, 128));
		ImageIcon icon_add=new ImageIcon(new ImageIcon(this.getClass().getResource("/Ressources/add.png")).getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT ));
		lblajouter_rep.setIcon(icon_add);
		lblajouter_rep.setBounds(0, 0, 103, 39);
		panelajouter_rep.add(lblajouter_rep);
		
//-------------------------------------------------------------------------------------------------------------------------------------------------------
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.inactiveCaption);
		panel_2.setLayout(null);
		panel_2.setBounds(0, 0, 554, 306);
		layeredPane.add(panel_2);
		
		JDateChooser dateChooser_debutCT = new JDateChooser();
		dateChooser_debutCT.setBounds(208, 69, 180, 35);
		panel_2.add(dateChooser_debutCT);
		
		JDateChooser dateChooser_finCT = new JDateChooser();
		dateChooser_finCT.setBounds(208, 124, 180, 35);
		panel_2.add(dateChooser_finCT);
		
		txtcout_ct = new JTextField();
		txtcout_ct.setBounds(208, 180, 180, 35);
		panel_2.add(txtcout_ct);
		txtcout_ct.setColumns(10);
		
		JLabel lbldebutCT = new JLabel("Debut controle tech");
		lbldebutCT.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lbldebutCT.setBounds(57, 69, 132, 33);
		panel_2.add(lbldebutCT);
		
		JLabel lblfinCT = new JLabel("Fin controle tech");
		lblfinCT.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblfinCT.setBounds(57, 130, 111, 27);
		panel_2.add(lblfinCT);
		
		JLabel lblcoutCT = new JLabel("Cout");
		lblcoutCT.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblcoutCT.setBounds(57, 185, 111, 27);
		panel_2.add(lblcoutCT);		
		
//---------------------------------------------------------------------------------------------------------------------------------------------
		
		JPanel panelvalider_ct = new JPanel();
		panelvalider_ct.setLayout(null);
		panelvalider_ct.setBackground(new Color(50, 205, 50));
		panelvalider_ct.setBounds(265, 255, 103, 39);
		panel_2.add(panelvalider_ct);		
		JLabel lblvalider_ct = new JLabel("Valider");
		lblvalider_ct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				lblvalider_ct.setBorder(null);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblvalider_ct.setBorder(new LineBorder(Color.DARK_GRAY));
			}
			@Override
			public void mouseClicked(MouseEvent e) {				
				//enregistrement controle tech
				Date sqldate_debut_ct = new Date(dateChooser_debutCT.getDate().getTime());
				Date sqldate_fin_ct = new Date(dateChooser_finCT.getDate().getTime());
				double cout=(Double.parseDouble(txtcout_ct.getText()));
				Database.Connect();
				
				VehiculeDAO vdao=new VehiculeDAO();
				Vehicule v= new Vehicule();
				v=vdao.getVehiculeByElemts(puissance, nb_km, model_v, prix);
				int last_id=v.getId_vehicule();
				System.out.println("identity vehicule:"+last_id); 
				Controle_tech ct= new Controle_tech();
				ct.setId_vehicule(last_id);
				ct.setDate_debut_ct(sqldate_debut_ct);
				ct.setDate_fin_ct(sqldate_fin_ct);
				ct.setCout_ct(cout);				
				Controle_techDAO ctdao=new Controle_techDAO();
				ctdao.save(ct);
			}
		});	
		lblvalider_ct.setHorizontalAlignment(SwingConstants.CENTER);
		lblvalider_ct.setForeground(SystemColor.inactiveCaptionBorder);
		lblvalider_ct.setBackground(new Color(50, 205, 50));
		ImageIcon icon3=new ImageIcon(new ImageIcon(this.getClass().getResource("/Ressources/validate3.png")).getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT ));
		lblvalider_ct.setIcon(icon3);
		lblvalider_ct.setBounds(0, 0, 103, 39);
		panelvalider_ct.add(lblvalider_ct);
		
//---------------------------------------------------------------------------------------------------------------------------------------		
		
		JPanel paneladd = new JPanel();
		paneladd.setBounds(10, 0, 148, 34);
		paneladd.setLayout(null);
		paneladd.setBackground(SystemColor.textHighlight);
		frame.getContentPane().add(paneladd);
		
		JLabel lbladd = new JLabel("Vehicule");
		lbladd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				paneladd.setBorder(null);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				paneladd.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.GREEN, null, null, null));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				SwitchPanels(panel);
			}
		});
		lbladd.setHorizontalAlignment(SwingConstants.CENTER);
		lbladd.setForeground(SystemColor.inactiveCaptionBorder);
		lbladd.setBackground(SystemColor.textHighlight);
		lbladd.setBounds(0, 0, 148, 34);
		paneladd.add(lbladd);
		
		
//------------------------------------------------------------------------------------------------------------------------------		
		
		JPanel paneladd_rep = new JPanel();
		paneladd_rep.setBounds(160, 0, 148, 34);
		paneladd_rep.setLayout(null);
		paneladd_rep.setBackground(SystemColor.textHighlight);
		frame.getContentPane().add(paneladd_rep);
		
		JLabel lbladd_rep = new JLabel("Reparation");
		lbladd_rep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				lbladd_rep.setBorder(null);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lbladd_rep.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.GREEN, null, null, null));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				SwitchPanels(panel_1);
			}
		});
		lbladd_rep.setHorizontalAlignment(SwingConstants.CENTER);
		lbladd_rep.setForeground(SystemColor.inactiveCaptionBorder);
		lbladd_rep.setBackground(SystemColor.textHighlight);
		lbladd_rep.setBounds(0, 0, 148, 34);
		paneladd_rep.add(lbladd_rep);
		
//-----------------------------------------------------------------------------------------------------------------------------------------
		
				
		JPanel paneladd_ct = new JPanel();
		paneladd_ct.setBounds(310, 0, 148, 34);
		paneladd_ct.setLayout(null);
		paneladd_ct.setBackground(SystemColor.textHighlight);
		frame.getContentPane().add(paneladd_ct);
		
		JLabel lbladd_ct = new JLabel("Control tech");
		lbladd_ct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				paneladd_ct.setBorder(null);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				paneladd_ct.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.GREEN, null, null, null));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				SwitchPanels(panel_2);
			}
		});
		lbladd_ct.setHorizontalAlignment(SwingConstants.CENTER);
		lbladd_ct.setForeground(SystemColor.inactiveCaptionBorder);
		lbladd_ct.setBackground(SystemColor.textHighlight);
		lbladd_ct.setBounds(0, 0, 148, 34);
		paneladd_ct.add(lbladd_ct);
			
	}
}
