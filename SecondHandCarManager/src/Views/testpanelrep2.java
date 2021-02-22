package Views;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import com.toedter.calendar.JDateChooser;

import DAO.ReparationDAO;
import DAO.VehiculeDAO;
import Entities.Database;
import Entities.Reparation;
import Entities.Vehicule;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

public class testpanelrep2 {

	private JLayeredPane layeredPane = new JLayeredPane();
	private JFrame frame;
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	private static JTable table_rep;
	static DefaultTableModel model_rep;
	static DefaultTableModel model_test;
	//private JScrollPane scrollPane;
	private JScrollPane scrollPane_2;
	private ArrayList<Reparation>reparations=new  ArrayList<Reparation>();
	public static int identity_reparation;
	public static int identity_veh;
	private static JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testpanelrep2 window = new testpanelrep2();
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
	public testpanelrep2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 747, 447);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		layeredPane.setBounds(10, 36, 711, 361);
		frame.getContentPane().add(layeredPane);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
				
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(0, 0, 711, 361);
		layeredPane.add(panel);		
		//scrollPane = new JScrollPane();
		//scrollPane.setViewportView(table_rep);
		
		Database.Connect();
		ReparationDAO rdao= new ReparationDAO();
		reparations=rdao.getAll();
				
		String columns[]= {"ID","DATE","DESCRIPTIF","COUT"};
		String data[][]=new String[reparations.size()][columns.length];
		int i=0;
		for(Reparation reparation:reparations) {
			data[i][0]=reparation.getId_reparation()+"";
			data[i][1]=reparation.getDate_reparation()+"";
			data[i][2]=reparation.getDescriptif();
			data[i][3]=reparation.getCout()+"";
			i++;
		}
		model_test=new DefaultTableModel(data,columns);
		panel.setLayout(null);
		table_rep = new JTable(model_test);	
		scrollPane_2 = new JScrollPane(table_rep);
		scrollPane_2.setBounds(91, 116, 528, 182);
		table_rep.setBounds(39, 57, 323, 143);
		panel.add(scrollPane_2);
		
//------------------------------------------------------------------------------------------		
		
		JDateChooser dateChooser_min = new JDateChooser();
		dateChooser_min.setBounds(259, 11, 174, 28);
		panel.add(dateChooser_min);
		
		JDateChooser dateChooser_max = new JDateChooser();
		dateChooser_max.setBounds(259, 61, 174, 28);
		panel.add(dateChooser_max);
		
		JButton btnsearch = new JButton("recherche");
		btnsearch.setBounds(505, 45, 114, 23);
		btnsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				Database.Connect();
				ReparationDAO rdao= new ReparationDAO();
				ArrayList<Reparation>reparations=new ArrayList<Reparation>();
				Date sqldate_min = new Date(dateChooser_min.getDate().getTime());
				Date sqldate_max = new Date(dateChooser_max.getDate().getTime());							
				reparations=rdao.getReparationByDate(sqldate_min, sqldate_max );
				String columns[]= {"ID","DATE","DESCRIPTIF","COUT"};
				String data[][]=new String[reparations.size()][columns.length];
				int i=0;
				for(Reparation reparation:reparations) {
					data[i][0]=reparation.getId_reparation()+"";
					data[i][1]=reparation.getDate_reparation()+"";
					data[i][2]=reparation.getDescriptif();
					data[i][3]=reparation.getCout()+"";
					i++;
				}								
				model_test=new DefaultTableModel(data,columns);
				table_rep.setModel(model_test);	
			}
		});
		panel.add(btnsearch);
		
//----------------------------------------------------------------------------------------------
		
		JPanel panelinfos_rep = new JPanel();
		panelinfos_rep.setBounds(516, 309, 103, 39);
		panelinfos_rep.setLayout(null);
		panelinfos_rep.setBackground(SystemColor.textHighlight);
		panel.add(panelinfos_rep);
		
		JLabel lblinfos_rep = new JLabel("Infos");
		lblinfos_rep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				panelinfos_rep.setBorder(null);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelinfos_rep.setBorder(new LineBorder(Color.GREEN));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("CLICKED");
				int id=Integer.parseInt(model_test.getValueAt(table_rep.getSelectedRow(), 0).toString());
				identity_reparation=id;//to get selected id value by static variable
				System.out.println("id rep: "+identity_reparation);
				Database.Connect();
				ReparationDAO rdao=new ReparationDAO();
				Reparation rep=rdao.getById(id);
				Reparation_detailsBis win_add=new Reparation_detailsBis(rep);
				win_add.getFrame().setVisible(true);
			}
		});
		lblinfos_rep.setHorizontalAlignment(SwingConstants.CENTER);
		lblinfos_rep.setForeground(SystemColor.inactiveCaptionBorder);
		lblinfos_rep.setBackground(SystemColor.textHighlight);
		lblinfos_rep.setBounds(0, 0, 103, 39);
		panelinfos_rep.add(lblinfos_rep);
		
		JLabel lblNewLabel = new JLabel("DATE DEBUT RECHERCHE");
		lblNewLabel.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblNewLabel.setBounds(91, 14, 168, 25);
		panel.add(lblNewLabel);
		
		JLabel lblDateFinRecherche = new JLabel("DATE FIN RECHERCHE");
		lblDateFinRecherche.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblDateFinRecherche.setBounds(91, 64, 168, 25);
		panel.add(lblDateFinRecherche);
		
//-------------------------------------------------------------------------------------------
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(SystemColor.inactiveCaption);
		panel1.setBounds(0, 0, 711, 361);
		layeredPane.add(panel1);
		panel1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(113, 83, 498, 180);
		panel1.add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id_veh=Integer.parseInt(model_rep.getValueAt(table.getSelectedRow(), 0).toString());
				identity_veh=id_veh;
				Database.Connect();
				Vehicule veh= new Vehicule();
				VehiculeDAO vdao= new VehiculeDAO();
				veh=vdao.getById(identity_veh);
				rep_add win_repadd= new rep_add(veh);
				win_repadd.getFrame().setVisible(true);	
			}
		});
		btnNewButton.setBounds(508, 300, 103, 23);
		panel1.add(btnNewButton);
		
		JPanel panelrep = new JPanel();
		panelrep.setBounds(10, 0, 209, 34);
		panelrep.setLayout(null);
		panelrep.setBackground(SystemColor.textHighlight);
		frame.getContentPane().add(panelrep);
		
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
		panelrep.add(lblselect_update_delete);
		
		JPanel panelrep1 = new JPanel();
		panelrep1.setBounds(220, 0, 177, 34);
		panelrep1.setLayout(null);
		panelrep1.setBackground(SystemColor.textHighlight);
		frame.getContentPane().add(panelrep1);
		
		JLabel lblajouter_rep = new JLabel("Ajouter/Vehicule");
		lblajouter_rep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				lblajouter_rep.setBorder(null);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblajouter_rep.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.GREEN, null, null, null));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				SwitchPanels(panel1);
				Database.Connect();
				VehiculeDAO vdao=new VehiculeDAO();
				ArrayList<Vehicule>vehicules= new ArrayList<Vehicule>();
				vehicules=vdao.getVehicule_Reparation_less();
				String columns[]= {"ID","MODEL","PRIX"};
				String data[][]=new String[vehicules.size()][columns.length];
				int i=0;
				for(Vehicule vehicule:vehicules) {
					data[i][0]=vehicule.getId_vehicule()+"";
					data[i][1]=vehicule.getModel();
					data[i][2]=vehicule.getPrix()+"";
					i++;
				}
				model_rep=new DefaultTableModel(data,columns);//****
				table.setModel(model_rep);	
			}
		});
		lblajouter_rep.setHorizontalAlignment(SwingConstants.CENTER);
		lblajouter_rep.setForeground(SystemColor.inactiveCaptionBorder);
		lblajouter_rep.setBackground(SystemColor.textHighlight);
		lblajouter_rep.setBounds(0, 0, 177, 34);
		panelrep1.add(lblajouter_rep);
	}
	
//------------------------------------------------------------------------------------------------------------------	
	
	public void SwitchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();		
	}
	
	
	public static void RefreshModelReparation()
	{
		Database.Connect();
		ReparationDAO rdao= new ReparationDAO();
		ArrayList<Reparation>reparations= new ArrayList<Reparation>();
		reparations=rdao.getAll();				
		String columns[]= {"ID","DATE","DESCRIPTIF","COUT"};
		String data[][]=new String[reparations.size()][columns.length];
		int i=0;
		for(Reparation reparation:reparations) {
			data[i][0]=reparation.getId_reparation()+"";
			data[i][1]=reparation.getDate_reparation()+"";
			data[i][2]=reparation.getDescriptif();
			data[i][3]=reparation.getCout()+"";
			i++;
		}								
		model_test=new DefaultTableModel(data,columns);
		table_rep.setModel(model_test);	
	}	
	
	
	public static void RefreshModelReparationVehicule() {
		Database.Connect();
		VehiculeDAO vdao=new VehiculeDAO();
		ArrayList<Vehicule>vehicules= new ArrayList<Vehicule>();
		vehicules=vdao.getVehicule_Reparation_less();
		String columns[]= {"ID","MODEL","PRIX"};
		String data[][]=new String[vehicules.size()][columns.length];
		int i=0;
		for(Vehicule vehicule:vehicules) {
			data[i][0]=vehicule.getId_vehicule()+"";
			data[i][1]=vehicule.getModel();
			data[i][2]=vehicule.getPrix()+"";
			i++;
		}
		model_rep=new DefaultTableModel(data,columns);//****
		table.setModel(model_rep);
	}
}
