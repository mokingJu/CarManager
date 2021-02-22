package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.toedter.calendar.JDateChooser;

import DAO.Controle_techDAO;
import DAO.VehiculeDAO;
import Entities.Controle_tech;
import Entities.Database;
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

public class Control_tech_details {

	private JFrame frame;
	private JTextField txtcout_ct;
	private Controle_tech ct;
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
					Control_tech_details window = new Control_tech_details();
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
	public Control_tech_details() {
		initialize();
	}
	
	public Control_tech_details(Controle_tech ct) {
		this.ct=ct;
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
		date_debut_ct.setBounds(138, 44, 173, 27);
		date_debut_ct.setDate(ct.getDate_debut_ct());
		frame.getContentPane().add(date_debut_ct);
		
		JDateChooser date_fin_ct = new JDateChooser();
		date_fin_ct.setBounds(138, 92, 173, 27);
		date_fin_ct.setDate(ct.getDate_fin_ct());
		frame.getContentPane().add(date_fin_ct);
		
		txtcout_ct = new JTextField();
		txtcout_ct.setBounds(138, 139, 173, 27);
		txtcout_ct.setText(ct.getCout_ct()+"");
		frame.getContentPane().add(txtcout_ct);
		txtcout_ct.setColumns(10);
		
		JPanel panelupdate = new JPanel();
		panelupdate.setLayout(null);
		panelupdate.setBackground(SystemColor.textHighlight);
		panelupdate.setBounds(82, 197, 103, 39);
		frame.getContentPane().add(panelupdate);
		
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
				Date sqldate_debut = new Date(date_debut_ct.getDate().getTime());
				ct.setDate_debut_ct(sqldate_debut);
				Date sqldate_fin = new Date(date_fin_ct.getDate().getTime());
				ct.setDate_fin_ct(sqldate_fin);
				ct.setCout_ct(Double.parseDouble(txtcout_ct.getText()));
				ct.setId_vehicule(identity_veh);
				System.out.println(identity_veh);
				ctdao.save(ct);
				testpanelct2.RefreshModel();
			}
		});
		lblpen.setHorizontalAlignment(SwingConstants.CENTER);
		lblpen.setForeground(SystemColor.inactiveCaptionBorder);
		lblpen.setBackground(SystemColor.textHighlight);
		lblpen.setBounds(0, 0, 103, 39);
		panelupdate.add(lblpen);
		
		JPanel paneldelete = new JPanel();		
		paneldelete.setLayout(null);
		paneldelete.setBackground(Color.RED);
		paneldelete.setBounds(208, 197, 103, 39);
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
				Database.Connect();
				int id_test=testpanelct2.identity_ct;
				ctdao.deleteById(id_test);
				testpanelct2.RefreshModel();
			}
		});
		lbltrash.setHorizontalAlignment(SwingConstants.CENTER);
		lbltrash.setForeground(SystemColor.inactiveCaptionBorder);
		lbltrash.setBackground(SystemColor.textHighlight);
		lbltrash.setBounds(0, 0, 103, 39);
		paneldelete.add(lbltrash);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(348, 11, 8, 248);
		frame.getContentPane().add(separator);
		
		

//recupération vehicule		
		VehiculeDAO vdao= new VehiculeDAO();
		Vehicule veh= new Vehicule();
		txtid_veh = new JTextField();
		int id_veh=ct.getId_vehicule();
		identity_veh=id_veh;
		txtid_veh.setText(id_veh+"");
		txtid_veh.setBounds(494, 44, 151, 27);		
		txtid_veh.setColumns(10);
		frame.getContentPane().add(txtid_veh);		
		veh=vdao.getById(id_veh);	
		
		txtconstructeur_veh = new JTextField();
		txtconstructeur_veh.setText(veh.getConstructeur());
		txtconstructeur_veh.setColumns(10);
		txtconstructeur_veh.setBounds(494, 79, 151, 27);
		frame.getContentPane().add(txtconstructeur_veh);
		
		txtmodel_veh = new JTextField();
		txtmodel_veh.setText(veh.getModel());
		txtmodel_veh.setColumns(10);
		txtmodel_veh.setBounds(494, 117, 151, 27);
		frame.getContentPane().add(txtmodel_veh);
		
		txtannee_model_veh = new JTextField();
		txtannee_model_veh.setText(veh.getAnnee_model()+"");
		txtannee_model_veh.setColumns(10);
		txtannee_model_veh.setBounds(494, 155, 151, 27);
		frame.getContentPane().add(txtannee_model_veh);
		
		txtnb_km_veh = new JTextField();
		txtnb_km_veh.setText(veh.getNb_km()+"");
		txtnb_km_veh.setColumns(10);
		txtnb_km_veh.setBounds(494, 189, 151, 27);
		frame.getContentPane().add(txtnb_km_veh);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblNewLabel.setBounds(380, 44, 115, 27);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblConstructeur = new JLabel("CONSTRUCTEUR");
		lblConstructeur.setHorizontalAlignment(SwingConstants.CENTER);
		lblConstructeur.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblConstructeur.setBounds(380, 79, 115, 27);
		frame.getContentPane().add(lblConstructeur);
		
		JLabel lblModel = new JLabel("MODEL");
		lblModel.setHorizontalAlignment(SwingConstants.CENTER);
		lblModel.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblModel.setBounds(380, 117, 104, 27);
		frame.getContentPane().add(lblModel);
		
		JLabel lblAnneeModel = new JLabel("ANNEE MODEL");
		lblAnneeModel.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnneeModel.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblAnneeModel.setBounds(380, 155, 104, 27);
		frame.getContentPane().add(lblAnneeModel);
		
		JLabel lblKilometrage = new JLabel("KILOMETRAGE");
		lblKilometrage.setHorizontalAlignment(SwingConstants.CENTER);
		lblKilometrage.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblKilometrage.setBounds(380, 189, 104, 27);
		frame.getContentPane().add(lblKilometrage);
		
		JLabel lblDateDebutCt = new JLabel("DATE DEBUT C.T");
		lblDateDebutCt.setHorizontalAlignment(SwingConstants.LEFT);
		lblDateDebutCt.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblDateDebutCt.setBounds(20, 44, 104, 27);
		frame.getContentPane().add(lblDateDebutCt);
		
		JLabel lblDateFinCt = new JLabel("DATE FIN C.T");
		lblDateFinCt.setHorizontalAlignment(SwingConstants.LEFT);
		lblDateFinCt.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblDateFinCt.setBounds(20, 92, 108, 27);
		frame.getContentPane().add(lblDateFinCt);
		
		JLabel lblCout = new JLabel("COUT");
		lblCout.setHorizontalAlignment(SwingConstants.LEFT);
		lblCout.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblCout.setBounds(25, 141, 103, 27);
		frame.getContentPane().add(lblCout);
	}
}
