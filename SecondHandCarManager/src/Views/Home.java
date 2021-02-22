package Views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import DAO.ReparationDAO;
import Entities.Database;
import Entities.Reparation;

public class Home {

	private JFrame frame;

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
					Home window = new Home ();
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
	public Home () {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(SystemColor.inactiveCaption);
		frame.setBounds(100, 100, 530, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);	
		
		
		JPanel header = new JPanel();
		header.setBackground(SystemColor.activeCaption);
		header.setBounds(0, 0, 514, 99);
		frame.getContentPane().add(header);
		header.setLayout(null);		
		JLabel lblNewLabel = new JLabel("TABLEAU DE BORD");
		lblNewLabel.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(105, 22, 347, 43);
		header.add(lblNewLabel);		
		JLabel lblhome = new JLabel();
		ImageIcon icon=new ImageIcon(new ImageIcon(this.getClass().getResource("/Ressources/home.png")).getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT ));
		lblhome.setIcon(icon);		
		lblhome.setBounds(26, 11, 46, 54);
		header.add(lblhome);


		JPanel plogo_vehicule = new JPanel();
		plogo_vehicule.setBackground(SystemColor.activeCaption);
		plogo_vehicule.setBounds(33, 130, 91, 81);
		frame.getContentPane().add(plogo_vehicule);
		JLabel lblvehicule = new JLabel("");
		lblvehicule.setBounds(10, 0, 71, 58);
		lblvehicule.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				plogo_vehicule.setBorder(null);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				plogo_vehicule.setBorder(new LineBorder(Color.DARK_GRAY));
			}
			@Override
			public void mouseClicked(MouseEvent e) {				
				testmpanels7 win= new testmpanels7();
				win.getFrame().setVisible(true);	
			}
		});	
		plogo_vehicule.setLayout(null);
		plogo_vehicule.add(lblvehicule);
		lblvehicule.setBackground(Color.BLACK);
		lblvehicule.setHorizontalAlignment(SwingConstants.CENTER);
		Image img_vehicule=new ImageIcon(this.getClass().getResource("/Ressources/car.png")).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		lblvehicule.setIcon(new ImageIcon(img_vehicule));//		
		JLabel lblvehicule_libelle = new JLabel("VEHICULES");
		lblvehicule_libelle.setFont(new Font("Arial Black", Font.PLAIN, 9));
		lblvehicule_libelle.setForeground(Color.WHITE);
		lblvehicule_libelle.setHorizontalAlignment(SwingConstants.CENTER);
		lblvehicule_libelle.setBounds(0, 60, 91, 21);
		plogo_vehicule.add(lblvehicule_libelle);
		
//---------------------------------------------------------------------------------------------------------------		

		JPanel plogo_parc = new JPanel();
		plogo_parc.setBackground(SystemColor.activeCaption);
		plogo_parc.setBounds(204, 130, 91, 81);
		frame.getContentPane().add(plogo_parc);
		JLabel lblparc = new JLabel("");
		lblparc.setBounds(10, 0, 71, 58);
		plogo_parc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				plogo_parc.setBorder(null);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				plogo_parc.setBorder(new LineBorder(Color.DARK_GRAY));
			}
			@Override
			public void mouseClicked(MouseEvent e) {				
				testpanelrep2 win_rep= new testpanelrep2();
				win_rep.getFrame().setVisible(true);	
			}
		});
		plogo_parc.setLayout(null);
		plogo_parc.add(lblparc);
		lblparc.setBackground(Color.BLACK);
		lblparc.setHorizontalAlignment(SwingConstants.CENTER);
		Image img_parc=new ImageIcon(this.getClass().getResource("/Ressources/reparation.png")).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		lblparc.setIcon(new ImageIcon(img_parc));		
		JLabel lblparc_libelle = new JLabel("MAINTENANCE");
		lblparc_libelle.setFont(new Font("Arial Black", Font.PLAIN, 9));
		lblparc_libelle.setForeground(Color.WHITE);
		lblparc_libelle.setHorizontalAlignment(SwingConstants.CENTER);
		lblparc_libelle.setBounds(0, 60, 91, 21);
		plogo_parc.add(lblparc_libelle);
		
		
//--------------------------------------------------------------------------------------------------------------------------				

		JPanel plogo_categorie = new JPanel();
		plogo_categorie.setBackground(SystemColor.activeCaption);
		plogo_categorie.setBounds(374, 130, 91, 81);
		frame.getContentPane().add(plogo_categorie);	
		JLabel lblcategorie = new JLabel("");
		lblcategorie.setBounds(10, 0, 71, 58);
		plogo_categorie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				plogo_categorie.setBorder(null);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				plogo_categorie.setBorder(new LineBorder(Color.DARK_GRAY));
			}
			@Override
			public void mouseClicked(MouseEvent e) {				
				testpanelct2 win_ct= new testpanelct2();
				win_ct.getFrame().setVisible(true);	
			}
		});		
		plogo_categorie.setLayout(null);
		plogo_categorie.add(lblcategorie);
		lblcategorie.setBackground(Color.BLACK);
		lblcategorie.setHorizontalAlignment(SwingConstants.CENTER);
		Image img_categorie=new ImageIcon(this.getClass().getResource("/Ressources/controle_tech.png")).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		lblcategorie.setIcon(new ImageIcon(img_categorie));//	
		JLabel lblcategorie_libelle = new JLabel("CONTROLE TECH");
		lblcategorie_libelle.setFont(new Font("Arial Black", Font.PLAIN, 9));
		lblcategorie_libelle.setForeground(Color.WHITE);
		lblcategorie_libelle.setHorizontalAlignment(SwingConstants.CENTER);
		lblcategorie_libelle.setBounds(0, 60, 91, 21);
		plogo_categorie.add(lblcategorie_libelle);
		
		JPanel footer = new JPanel();
		footer.setBackground(SystemColor.activeCaption);
		footer.setBounds(0, 258, 514, 52);
		frame.getContentPane().add(footer);
		

	}
}
