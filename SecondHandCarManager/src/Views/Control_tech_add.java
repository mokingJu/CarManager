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
import java.awt.Font;

public class Control_tech_add {

	private JFrame frame;
	private JTextField txtcout_ct;
	private Vehicule veh;
	private JTextField txtid_veh;
	private JTextField txtconstructeur_veh;
	private JTextField txtmodel_veh;
	private JTextField txtannee_model_veh;
	private JTextField txtnb_km_veh;
	private static int identity_veh;
	private JLabel lblNewLabel;
	private JLabel lblConstructeur;
	private JLabel lblModel;
	private JLabel lblAnneeModel;
	private JLabel lblPrix;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;

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
					Control_tech_add window = new Control_tech_add();
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
	public Control_tech_add() {
		initialize();
	}
	
	public Control_tech_add(Vehicule veh) {
		this.veh=veh;
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(SystemColor.inactiveCaption);
		frame.setBounds(100, 100, 706, 321);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Controle_techDAO ctdao= new Controle_techDAO();
		
		JDateChooser date_debut_ct = new JDateChooser();
		date_debut_ct.setBounds(148, 44, 170, 27);
		frame.getContentPane().add(date_debut_ct);
		
		JDateChooser date_fin_ct = new JDateChooser();
		date_fin_ct.setBounds(148, 92, 170, 27);
		frame.getContentPane().add(date_fin_ct);
		
		txtcout_ct = new JTextField();
		txtcout_ct.setBounds(148, 139, 170, 27);
		frame.getContentPane().add(txtcout_ct);
		txtcout_ct.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(348, 11, 8, 248);
		frame.getContentPane().add(separator);
		
		

//recupération vehicule		

		txtid_veh = new JTextField();
		this.txtid_veh.enable(false);
		int id_veh=veh.getId_vehicule();
		identity_veh=id_veh;
		txtid_veh.setText(id_veh+"");
		txtid_veh.setBounds(491, 44, 141, 27);		
		txtid_veh.setColumns(10);
		frame.getContentPane().add(txtid_veh);		

		
		txtconstructeur_veh = new JTextField();
		this.txtconstructeur_veh.enable(false);
		txtconstructeur_veh.setText(veh.getConstructeur());
		txtconstructeur_veh.setColumns(10);
		txtconstructeur_veh.setBounds(491, 79, 141, 27);
		frame.getContentPane().add(txtconstructeur_veh);
		
		txtmodel_veh = new JTextField();
		this.txtmodel_veh.enable(false);
		txtmodel_veh.setText(veh.getModel());
		txtmodel_veh.setColumns(10);
		txtmodel_veh.setBounds(491, 117, 141, 27);
		frame.getContentPane().add(txtmodel_veh);
		
		txtannee_model_veh = new JTextField();
		this.txtannee_model_veh.enable(false);
		txtannee_model_veh.setText(veh.getAnnee_model()+"");
		txtannee_model_veh.setColumns(10);
		txtannee_model_veh.setBounds(491, 155, 141, 27);
		frame.getContentPane().add(txtannee_model_veh);
		
		txtnb_km_veh = new JTextField();
		this.txtnb_km_veh.enable(false);
		txtnb_km_veh.setText(veh.getNb_km()+"");
		txtnb_km_veh.setColumns(10);
		txtnb_km_veh.setBounds(491, 189, 141, 27);
		frame.getContentPane().add(txtnb_km_veh);
		
		JPanel panelvalider_vct = new JPanel();
		panelvalider_vct.setLayout(null);
		panelvalider_vct.setBackground(new Color(50, 205, 50));
		panelvalider_vct.setBounds(175, 192, 103, 39);
		frame.getContentPane().add(panelvalider_vct);
		
		JLabel lblvalider_vct = new JLabel("Valider");
		lblvalider_vct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				lblvalider_vct.setBorder(null);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblvalider_vct.setBorder(new LineBorder(Color.DARK_GRAY));
			}
			@Override
			public void mouseClicked(MouseEvent e) {				
				//add controle tech		
				Date sqldate_debut_ct = new Date(date_debut_ct.getDate().getTime());
				Date sqldate_fin_ct = new Date(date_fin_ct.getDate().getTime());
				double cout_ct=(Double.parseDouble(txtcout_ct.getText()));
				Database.Connect();
				Controle_tech ct= new Controle_tech(sqldate_debut_ct,sqldate_fin_ct,cout_ct,identity_veh);
				ctdao.save(ct);
				testpanelct2.RefreshModelVehicule();//refresh
			}
		});
		lblvalider_vct.setHorizontalAlignment(SwingConstants.CENTER);
		lblvalider_vct.setForeground(SystemColor.inactiveCaptionBorder);
		lblvalider_vct.setBackground(new Color(50, 205, 50));
		ImageIcon icon4=new ImageIcon(new ImageIcon(this.getClass().getResource("/Ressources/validate4.png")).getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT ));
		lblvalider_vct.setIcon(icon4);
		lblvalider_vct.setBounds(0, 0, 103, 39);
		panelvalider_vct.add(lblvalider_vct);
		
		lblNewLabel = new JLabel("ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblNewLabel.setBounds(366, 44, 99, 27);
		frame.getContentPane().add(lblNewLabel);
		
		lblConstructeur = new JLabel("CONSTRUCTEUR");
		lblConstructeur.setHorizontalAlignment(SwingConstants.CENTER);
		lblConstructeur.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblConstructeur.setBounds(366, 79, 110, 27);
		frame.getContentPane().add(lblConstructeur);
		
		lblModel = new JLabel("MODEL");
		lblModel.setHorizontalAlignment(SwingConstants.CENTER);
		lblModel.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblModel.setBounds(366, 117, 99, 27);
		frame.getContentPane().add(lblModel);
		
		lblAnneeModel = new JLabel("ANNEE MODEL");
		lblAnneeModel.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnneeModel.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblAnneeModel.setBounds(366, 155, 99, 27);
		frame.getContentPane().add(lblAnneeModel);
		
		lblPrix = new JLabel("KILOMETRAGE");
		lblPrix.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrix.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblPrix.setBounds(366, 189, 99, 27);
		frame.getContentPane().add(lblPrix);
		
		lblNewLabel_1 = new JLabel("V\u00E9hicule associ\u00E9");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(393, 11, 239, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("DATE DEBUT C.T");
		lblNewLabel_2.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblNewLabel_2.setBounds(32, 44, 106, 27);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("DATE FIN C.T");
		lblNewLabel_3.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblNewLabel_3.setBounds(32, 92, 91, 27);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("COUT");
		lblNewLabel_4.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(32, 139, 91, 27);
		frame.getContentPane().add(lblNewLabel_4);
	}
}
