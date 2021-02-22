package Views;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import com.toedter.calendar.JDateChooser;

import DAO.Controle_techDAO;
import DAO.ReparationDAO;
import DAO.VehiculeDAO;
import Entities.Controle_tech;
import Entities.Database;
import Entities.Reparation;
import Entities.Vehicule;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.Font;

public class testpanelct2 {

	private JLayeredPane layeredPane = new JLayeredPane();
	private JFrame frame;
	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	private static JTable table;	
	private static DefaultTableModel model;		
	private JScrollPane scrollPane;
	public static int identity_ct;
	public static int identity_veh;
	private static JTable table_ct;
	private static DefaultTableModel model_test;	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testpanelct2 window = new testpanelct2();
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
	public testpanelct2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 770, 489);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		layeredPane.setBounds(10, 36, 734, 403);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		frame.getContentPane().add(layeredPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(0, 0, 734, 403);
		layeredPane.add(panel);
		panel.setLayout(null);
		
		JDateChooser dateChooser_min = new JDateChooser();
		dateChooser_min.setBounds(217, 22, 173, 29);
		panel.add(dateChooser_min);
		
		JDateChooser dateChooser_max = new JDateChooser();
		dateChooser_max.setBounds(217, 62, 173, 29);
		panel.add(dateChooser_max);
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(SystemColor.inactiveCaption);
		panel1.setBounds(0, 0, 734, 403);
		layeredPane.add(panel1);
		panel1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(101, 135, 521, 203);
		panel1.add(scrollPane_1);
		
		table_ct = new JTable();
		scrollPane_1.setViewportView(table_ct);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id_veh=Integer.parseInt(model.getValueAt(table_ct.getSelectedRow(), 0).toString());
				identity_veh=id_veh;
				System.out.println("identity_veh: "+identity_veh);//test
				Database.Connect();
				Vehicule veh= new Vehicule();
				VehiculeDAO vdao= new VehiculeDAO();
				veh=vdao.getById(identity_veh);
				Control_tech_add win_ctadd= new Control_tech_add(veh);
				win_ctadd.getFrame().setVisible(true);				
			}
		});
		btnAdd.setBounds(535, 349, 89, 23);
		panel1.add(btnAdd);
		
		JLabel lblNewLabel_1 = new JLabel("v\u00E9hicules n'ayant pas effectu\u00E9 de contr\u00F4le technique");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(101, 72, 521, 14);
		panel1.add(lblNewLabel_1);
		
		
		
		//affichage dans table
		Controle_techDAO ctdao= new Controle_techDAO();
		ArrayList<Controle_tech>lst_ct= new ArrayList<Controle_tech>();
		Database.Connect();
		lst_ct=ctdao.getAll();
		
		String columns[]= {"ID","PRECEDENT","PROCHAIN","COUT"};
		String data[][]=new String[lst_ct.size()][columns.length];
		int i=0;
		for(Controle_tech ct:lst_ct) {
			data[i][0]=ct.getId_ct()+"";
			data[i][1]=ct.getDate_debut_ct()+"";
			data[i][2]=ct.getDate_fin_ct()+"";
			data[i][3]=ct.getCout_ct()+"";
			i++;
		}
		model_test=new DefaultTableModel(data,columns);
		table = new JTable(model_test);		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(50, 120, 558, 213);
		table.setBounds(39, 57, 323, 143);
		panel.add(scrollPane);
		
		
		//Recherche avec jdatechooser
		JButton btnrecherche = new JButton("Recherche");
		btnrecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database.Connect();
				Controle_techDAO ctdao= new Controle_techDAO();
				ArrayList<Controle_tech>lst_ct=new ArrayList<Controle_tech>();
				Date sqldate_min = new Date(dateChooser_min.getDate().getTime());
				Date sqldate_max = new Date(dateChooser_max.getDate().getTime());							
				lst_ct=ctdao.getControle_techByDate(sqldate_min, sqldate_max );

				String columns[]= {"ID","PRECEDENT","PROCHAIN","COUT"};
				String data[][]=new String[lst_ct.size()][columns.length];
				int i=0;
				for(Controle_tech ct:lst_ct) {
					data[i][0]=ct.getId_ct()+"";
					data[i][1]=ct.getDate_debut_ct()+"";
					data[i][2]=ct.getDate_fin_ct()+"";
					data[i][3]=ct.getCout_ct()+"";
					i++;
				}	
				model=new DefaultTableModel(data,columns);
				table.setModel(model);
				
				//if table empty-clear datechoosers
				if(model.getRowCount()==0) {
				//clear component to fill new 'reparation'
				dateChooser_min.setCalendar(null);
				dateChooser_max.setCalendar(null);
				}				
			}
		});
		btnrecherche.setBounds(482, 52, 126, 23);
		panel.add(btnrecherche);
		
		
		
		//boton information
		JPanel panelinfos_ct = new JPanel();
		panelinfos_ct.setLayout(null);
		panelinfos_ct.setBackground(SystemColor.textHighlight);
		panelinfos_ct.setBounds(505, 353, 103, 39);
		panel.add(panelinfos_ct);
		
		JLabel lblinfos_ct = new JLabel("Infos");
		lblinfos_ct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				panelinfos_ct.setBorder(null);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelinfos_ct.setBorder(new LineBorder(Color.GREEN));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				int id_ct=Integer.parseInt(model_test.getValueAt(table.getSelectedRow(), 0).toString());				
				identity_ct=id_ct;
				System.out.println("recp id:"+identity_ct);//test
				Database.Connect();
				Controle_techDAO ctdao=new Controle_techDAO ();
				Controle_tech ct=ctdao.getById(id_ct);	
				Control_tech_details win_ctdetails= new Control_tech_details(ct);
				win_ctdetails.getFrame().setVisible(true);
			}
		});
		lblinfos_ct.setHorizontalAlignment(SwingConstants.CENTER);
		lblinfos_ct.setForeground(SystemColor.inactiveCaptionBorder);
		lblinfos_ct.setBackground(SystemColor.textHighlight);
		lblinfos_ct.setBounds(0, 0, 103, 39);
		panelinfos_ct.add(lblinfos_ct);
		
		JLabel lblNewLabel = new JLabel("DATE DEBUT RECHERCHE");
		lblNewLabel.setFont(new Font("Sitka Display", Font.BOLD, 11));
		lblNewLabel.setBounds(51, 22, 149, 26);
		panel.add(lblNewLabel);
		
		JLabel lblDateFinRecherche = new JLabel("DATE FIN RECHERCHE");
		lblDateFinRecherche.setFont(new Font("Sitka Display", Font.BOLD, 11));
		lblDateFinRecherche.setBounds(50, 62, 149, 26);
		panel.add(lblDateFinRecherche);
		
		JPanel panelcontrole_tech = new JPanel();
		panelcontrole_tech.setBounds(10, 1, 209, 34);
		panelcontrole_tech.setLayout(null);
		panelcontrole_tech.setBackground(SystemColor.textHighlight);
		frame.getContentPane().add(panelcontrole_tech);
		
		JLabel lblselect_update_delete = new JLabel("Selectionner-Modifier/Supprimer");
		lblselect_update_delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				lblselect_update_delete.setBorder(null);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblselect_update_delete.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.GREEN, null, null, null));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				SwitchPanels(panel);				
			}
		});
		lblselect_update_delete.setHorizontalAlignment(SwingConstants.CENTER);
		lblselect_update_delete.setForeground(SystemColor.inactiveCaptionBorder);
		lblselect_update_delete.setBackground(SystemColor.textHighlight);
		lblselect_update_delete.setBounds(0, 0, 209, 34);
		panelcontrole_tech.add(lblselect_update_delete);
		
		JPanel panelcontrole_tech_1 = new JPanel();
		panelcontrole_tech_1.setBounds(220, 1, 177, 34);
		panelcontrole_tech_1.setLayout(null);
		panelcontrole_tech_1.setBackground(SystemColor.textHighlight);
		frame.getContentPane().add(panelcontrole_tech_1);
		
		JLabel lblajouter_ct = new JLabel("Ajouter/Vehicule");
		lblajouter_ct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				lblajouter_ct.setBorder(null);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblajouter_ct.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.GREEN, null, null, null));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				SwitchPanels(panel1);
				Database.Connect();
				VehiculeDAO vdao=new VehiculeDAO();
				ArrayList<Vehicule>vehicules= new ArrayList<Vehicule>();
				vehicules=vdao.getVehicule_ControleTech_less();
				String columns[]= {"ID","MODEL","PRIX"};
				String data[][]=new String[vehicules.size()][columns.length];
				int i=0;
				for(Vehicule vehicule:vehicules) {
					data[i][0]=vehicule.getId_vehicule()+"";
					data[i][1]=vehicule.getModel();
					data[i][2]=vehicule.getPrix()+"";
					i++;
				}
				model=new DefaultTableModel(data,columns);//****
				table_ct.setModel(model);				
			}
		});
		lblajouter_ct.setHorizontalAlignment(SwingConstants.CENTER);
		lblajouter_ct.setForeground(SystemColor.inactiveCaptionBorder);
		lblajouter_ct.setBackground(SystemColor.textHighlight);
		lblajouter_ct.setBounds(0, 0, 177, 34);
		panelcontrole_tech_1.add(lblajouter_ct);
	}
	
//--------------------------------------------------------------------------------------------------------
	
	public void SwitchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();		
	}
	
	//mise à jour du model
	public static void RefreshModel() {
		Controle_techDAO ctdao=new Controle_techDAO();
		ArrayList<Controle_tech>lst_ct=new ArrayList<Controle_tech>();
		Database.Connect();
		lst_ct= ctdao.getAll();

		String columns[]= {"ID","PRECEDENT","PROCHAIN","COUT"};
		String data[][]=new String[lst_ct.size()][columns.length];
		int i=0;
		for(Controle_tech ct:lst_ct) {
			data[i][0]=ct.getId_ct()+"";
			data[i][1]=ct.getDate_debut_ct()+"";
			data[i][2]=ct.getDate_fin_ct()+"";
			data[i][3]=ct.getCout_ct()+"";
			i++;
		}	
		model=new DefaultTableModel(data,columns);
		table.setModel(model);
	}
	
	public static void RefreshModelVehicule() {
		
		VehiculeDAO vdao=new VehiculeDAO();
		ArrayList<Vehicule>vehicules= new ArrayList<Vehicule>();
		Database.Connect();
		vehicules=vdao.getVehicule_ControleTech_less();
		String columns[]= {"ID","MODEL","PRIX"};
		String data[][]=new String[vehicules.size()][columns.length];
		int i=0;
		for(Vehicule vehicule:vehicules) {
			data[i][0]=vehicule.getId_vehicule()+"";
			data[i][1]=vehicule.getModel();
			data[i][2]=vehicule.getPrix()+"";
			i++;
		}
		model=new DefaultTableModel(data,columns);//****
		table_ct.setModel(model);
	}	
}
