package Views;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import com.toedter.calendar.JDateChooser;

import DAO.Controle_techDAO;
import DAO.ReparationDAO;
import DAO.VehiculeDAO;
import Entities.Controle_tech;
import Entities.Database;
import Entities.Reparation;
import Entities.Vehicule;

import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import javax.swing.JTextArea;
import java.awt.Font;

public class rep_add {

	private JFrame frame;
	private JTextField txtcout_rep;
	private Vehicule veh;
	private JTextField txtid_veh;
	private JTextField txtconstructeur_veh;
	private JTextField txtmodel_veh;
	private JTextField txtannee_model_veh;
	private JTextField txtnb_km_veh;
	private static int identity_veh;

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
					rep_add window = new rep_add();
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
	public rep_add() {
		initialize();
	}
	
	public rep_add(Vehicule veh) {
		this.veh=veh;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaption);
		frame.setBounds(100, 100, 706, 321);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Controle_techDAO ctdao= new Controle_techDAO();
		frame.getContentPane().setLayout(null);
		
		JDateChooser date_rep = new JDateChooser();
		date_rep.setBounds(137, 29, 178, 27);
		frame.getContentPane().add(date_rep);
		
		txtcout_rep = new JTextField();
		txtcout_rep.setBounds(137, 155, 178, 27);
		frame.getContentPane().add(txtcout_rep);
		txtcout_rep.setColumns(10);
		
		JTextArea txtdescriptif = new JTextArea();
		txtdescriptif.setBounds(137, 67, 178, 77);
		frame.getContentPane().add(txtdescriptif);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(348, 11, 8, 248);
		separator.setOrientation(SwingConstants.VERTICAL);
		frame.getContentPane().add(separator);
		
		

//recupération vehicule		

		txtid_veh = new JTextField();
		txtid_veh.setBounds(499, 44, 154, 27);
		int id_veh=veh.getId_vehicule();
		identity_veh=id_veh;
		txtid_veh.setText(id_veh+"");
		txtid_veh.setColumns(10);
		frame.getContentPane().add(txtid_veh);		

		
		txtconstructeur_veh = new JTextField();
		txtconstructeur_veh.setBounds(499, 79, 154, 27);
		txtconstructeur_veh.setText(veh.getConstructeur());
		txtconstructeur_veh.setColumns(10);
		frame.getContentPane().add(txtconstructeur_veh);
		
		txtmodel_veh = new JTextField();
		txtmodel_veh.setBounds(499, 117, 154, 27);
		txtmodel_veh.setText(veh.getModel());
		txtmodel_veh.setColumns(10);
		frame.getContentPane().add(txtmodel_veh);
		
		txtannee_model_veh = new JTextField();
		txtannee_model_veh.setBounds(499, 155, 154, 27);
		txtannee_model_veh.setText(veh.getAnnee_model()+"");
		txtannee_model_veh.setColumns(10);
		frame.getContentPane().add(txtannee_model_veh);
		
		txtnb_km_veh = new JTextField();
		txtnb_km_veh.setBounds(499, 189, 154, 27);
		txtnb_km_veh.setText(veh.getNb_km()+"");
		txtnb_km_veh.setColumns(10);
		frame.getContentPane().add(txtnb_km_veh);
		
		JPanel panelvalider_vrep = new JPanel();
		panelvalider_vrep.setBounds(212, 198, 103, 39);
		panelvalider_vrep.setLayout(null);
		panelvalider_vrep.setBackground(new Color(50, 205, 50));
		frame.getContentPane().add(panelvalider_vrep);
		
		JLabel lblvalider_vrep = new JLabel("Valider");
		lblvalider_vrep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				lblvalider_vrep.setBorder(null);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblvalider_vrep.setBorder(new LineBorder(Color.DARK_GRAY));
			}
			@Override
			public void mouseClicked(MouseEvent e) {				
				//add controle tech		
				Date sqldate_rep = new Date(date_rep.getDate().getTime());
				String descriptif_rep= txtdescriptif.getText();
				double cout_rep=(Double.parseDouble(txtcout_rep.getText()));
				Database.Connect();				
				ReparationDAO rdao=new ReparationDAO();
				Reparation rep= new Reparation(sqldate_rep,descriptif_rep,cout_rep,identity_veh);
				rdao.save(rep);
				testpanelrep2.RefreshModelReparationVehicule();
				//testpanelct.RefreshModel();//refresh
			}
		});
		lblvalider_vrep.setHorizontalAlignment(SwingConstants.CENTER);
		lblvalider_vrep.setForeground(SystemColor.inactiveCaptionBorder);
		lblvalider_vrep.setBackground(new Color(50, 205, 50));
		ImageIcon icon4=new ImageIcon(new ImageIcon(this.getClass().getResource("/Ressources/validate4.png")).getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT ));
		lblvalider_vrep.setIcon(icon4);
		lblvalider_vrep.setBounds(0, 0, 103, 39);
		panelvalider_vrep.add(lblvalider_vrep);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblNewLabel.setBounds(377, 44, 88, 27);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblConstructeur = new JLabel("CONSTRUCTEUR");
		lblConstructeur.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblConstructeur.setBounds(377, 79, 112, 27);
		frame.getContentPane().add(lblConstructeur);
		
		JLabel lblNewLabel_1_1 = new JLabel("MODEL");
		lblNewLabel_1_1.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(377, 117, 88, 27);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("ANNEE MODEL");
		lblNewLabel_1_2.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblNewLabel_1_2.setBounds(377, 155, 88, 27);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("KILOMETRAGE");
		lblNewLabel_1_3.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblNewLabel_1_3.setBounds(377, 189, 112, 27);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1 = new JLabel("DATE REPARATION");
		lblNewLabel_1.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 29, 121, 27);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_4 = new JLabel("DESCRIPTIF");
		lblNewLabel_1_4.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblNewLabel_1_4.setBounds(10, 91, 121, 27);
		frame.getContentPane().add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("COUT");
		lblNewLabel_1_4_1.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblNewLabel_1_4_1.setBounds(10, 157, 121, 27);
		frame.getContentPane().add(lblNewLabel_1_4_1);
		
		JPanel panelajouter_rep = new JPanel();
		panelajouter_rep.setLayout(null);
		panelajouter_rep.setBackground(Color.GRAY);
		panelajouter_rep.setBounds(88, 198, 103, 39);
		frame.getContentPane().add(panelajouter_rep);
		
		JLabel lblajouter_rep = new JLabel("Ajouter new");
		ImageIcon icon_add=new ImageIcon(new ImageIcon(this.getClass().getResource("/Ressources/add2.png")).getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT ));
		lblajouter_rep.setIcon(icon_add);
		lblajouter_rep.setHorizontalAlignment(SwingConstants.CENTER);
		lblajouter_rep.setForeground(SystemColor.inactiveCaptionBorder);
		lblajouter_rep.setBackground(Color.GRAY);
		lblajouter_rep.setBounds(0, 0, 103, 39);
		panelajouter_rep.add(lblajouter_rep);
		

	}
}
