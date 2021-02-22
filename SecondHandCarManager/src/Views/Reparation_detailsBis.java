package Views;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import com.toedter.calendar.JDateChooser;

import DAO.ReparationDAO;
import DAO.VehiculeDAO;
import Entities.Database;
import Entities.Reparation;
import Entities.Vehicule;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;
import java.awt.Font;

public class Reparation_detailsBis {

	private JFrame frame;
	private JTextField txtcout;
	private Reparation rep;
	private JTextField txtid_veh;
	private JTextField txtconstructeur_veh;
	private JTextField txtmodel_veh;
	private JTextField txtannee_model_veh;
	private JTextField txtnb_km_veh;
	
	
	
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
					Reparation_detailsBis window = new Reparation_detailsBis();
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
	public Reparation_detailsBis(Reparation reparation) {
		this.rep=reparation;
		initialize();
	}
	
	public Reparation_detailsBis() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaption);
		frame.setBounds(100, 100, 751, 388);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JDateChooser date_rep = new JDateChooser();
		date_rep.setBounds(159, 66, 211, 28);
		date_rep.setDate(rep.getDate_reparation());
		frame.getContentPane().add(date_rep);
		
		txtcout = new JTextField();
		txtcout.setBounds(159, 116, 211, 28);
		txtcout.setText(rep.getCout()+"");
		txtcout.setColumns(10);
		frame.getContentPane().add(txtcout);
		
		JTextArea txtdescriptif = new JTextArea();
		txtdescriptif.setBounds(159, 196, 211, 75);
		txtdescriptif.setText(rep.getDescriptif());
		frame.getContentPane().add(txtdescriptif);
		
		JLabel lbldate_rep = new JLabel("DATE REPARATION");
		lbldate_rep.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lbldate_rep.setBounds(34, 66, 115, 28);
		frame.getContentPane().add(lbldate_rep);
		
		JLabel lblcout_rep = new JLabel("COUT REPARATION");
		lblcout_rep.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblcout_rep.setBounds(34, 116, 115, 28);
		frame.getContentPane().add(lblcout_rep);
		
		JLabel lbldescriptif = new JLabel("DESCRIPTIF");
		lbldescriptif.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lbldescriptif.setBounds(34, 217, 103, 21);
		frame.getContentPane().add(lbldescriptif);
		
		ReparationDAO rdao= new ReparationDAO();
		

		
//-----------------------------------------------------------------------------------------------------------------------------------
		
		JPanel paneldelete = new JPanel();
		paneldelete.setBounds(267, 299, 103, 39);
		paneldelete.setLayout(null);
		paneldelete.setBackground(Color.RED);
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
				int id_test=rep.getId_reparation();
				System.out.println("id test:"+id_test);
				rdao.deleteById(id_test);
				testmpanels7.b4();
			}
		});
		ImageIcon icon_trash2=new ImageIcon(new ImageIcon(this.getClass().getResource("/Ressources/trash2.png")).getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT ));
		lbltrash.setIcon(icon_trash2);
		lbltrash.setHorizontalAlignment(SwingConstants.CENTER);
		lbltrash.setForeground(SystemColor.inactiveCaptionBorder);
		lbltrash.setBackground(SystemColor.textHighlight);
		lbltrash.setBounds(0, 0, 103, 39);
		paneldelete.add(lbltrash);
		
		
		
//---------------------------------------------------------------------------------------------------------------------------------------------		
		
		JPanel panelupdate = new JPanel();
		panelupdate.setBounds(142, 299, 103, 39);
		panelupdate.setLayout(null);
		panelupdate.setBackground(SystemColor.textHighlight);
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
				rep.setId_reparation(rep.getId_reparation());
				rep.setCout(Double.parseDouble(txtcout.getText()));
				rep.setDescriptif(txtdescriptif.getText());				
				Date sqldate = new Date(date_rep.getDate().getTime());
				rep.setDate_reparation(sqldate);				
				rep.setId_vehicule(rep.getId_vehicule());
				Database.Connect();
				rdao.save(rep);				
				//testmpanels7.b4();
				testpanelrep2.RefreshModelReparation();
			}
		});
		ImageIcon icon_pen2=new ImageIcon(new ImageIcon(this.getClass().getResource("/Ressources/pen2.png")).getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT ));
		lblpen.setIcon(icon_pen2);
		lblpen.setHorizontalAlignment(SwingConstants.CENTER);
		lblpen.setForeground(SystemColor.inactiveCaptionBorder);
		lblpen.setBackground(SystemColor.textHighlight);
		lblpen.setBounds(0, 0, 103, 39);
		panelupdate.add(lblpen);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(422, 11, 9, 327);
		frame.getContentPane().add(separator);
		
		
//--------------------------------------------------------------------------------------------------------------------------------------- 		
		VehiculeDAO vdao= new VehiculeDAO();
		Vehicule veh= new Vehicule();
		
		txtid_veh = new JTextField();
		int id_veh=rep.getId_vehicule();
		txtid_veh.setText(id_veh+"");
		txtid_veh.setBounds(544, 66, 161, 28);		
		txtid_veh.setColumns(10);
		frame.getContentPane().add(txtid_veh);
		
		veh=vdao.getById(id_veh);
		
		txtconstructeur_veh = new JTextField();
		txtconstructeur_veh.setText(veh.getConstructeur());
		txtconstructeur_veh.setColumns(10);
		txtconstructeur_veh.setBounds(544, 109, 161, 28);
		frame.getContentPane().add(txtconstructeur_veh);
		
		txtmodel_veh = new JTextField();
		txtmodel_veh.setText(veh.getModel());
		txtmodel_veh.setColumns(10);
		txtmodel_veh.setBounds(544, 152, 161, 28);
		frame.getContentPane().add(txtmodel_veh);
		
		txtannee_model_veh = new JTextField();
		txtannee_model_veh.setText(veh.getAnnee_model()+"");
		txtannee_model_veh.setColumns(10);
		txtannee_model_veh.setBounds(544, 194, 161, 28);
		frame.getContentPane().add(txtannee_model_veh);
		
		txtnb_km_veh = new JTextField();
		txtnb_km_veh.setText(veh.getNb_km()+"");
		txtnb_km_veh.setColumns(10);
		txtnb_km_veh.setBounds(544, 233, 161, 28);
		frame.getContentPane().add(txtnb_km_veh);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblId.setBounds(441, 66, 103, 28);
		frame.getContentPane().add(lblId);
		
		JLabel lbldescriptif_1_1 = new JLabel("CONSTRUCTEUR");
		lbldescriptif_1_1.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lbldescriptif_1_1.setBounds(441, 109, 103, 28);
		frame.getContentPane().add(lbldescriptif_1_1);
		
		JLabel lbldescriptif_1_2 = new JLabel("MODEL");
		lbldescriptif_1_2.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lbldescriptif_1_2.setBounds(441, 152, 103, 28);
		frame.getContentPane().add(lbldescriptif_1_2);
		
		JLabel lbldescriptif_1_3 = new JLabel("ANNEE MODEL");
		lbldescriptif_1_3.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lbldescriptif_1_3.setBounds(441, 194, 103, 28);
		frame.getContentPane().add(lbldescriptif_1_3);
		
		JLabel lbldescriptif_1_4 = new JLabel("KILOMETRAGE");
		lbldescriptif_1_4.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lbldescriptif_1_4.setBounds(441, 233, 103, 28);
		frame.getContentPane().add(lbldescriptif_1_4);
	}
}
