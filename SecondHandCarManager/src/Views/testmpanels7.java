package Views;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import DAO.CategorieDAO;
import DAO.Controle_techDAO;
import DAO.ReparationDAO;
import DAO.VehiculeDAO;
import Entities.Controle_tech;
import Entities.Database;
import Entities.Reparation;
import Entities.Vehicule;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Image;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

public class testmpanels7 {

	private JFrame frame;
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}


	public JLayeredPane layeredPane = new JLayeredPane();
		
	private static JTable table;
	private JScrollPane scrollPane;
	private static ArrayList<Vehicule>vehicules=new  ArrayList<Vehicule>();	
	JComboBox<String> cbconstructeur = new JComboBox<String>();
	JComboBox<String> cbcategorie = new JComboBox<String>();	
	private static DefaultTableModel model;
	static DefaultTableModel model_rep;
	public static int identity;
	public static int identity_categorie;
	private JTextField txtcout_ct;
	private JDateChooser datedebut;
	private JDateChooser datefin;
	private Controle_tech ct=new Controle_tech();
	private static JTable table_rep;
	private JScrollPane scrollPane_rep;
	private static ArrayList<Reparation>reparations=new ArrayList<Reparation>();
	private JPanel panel3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testmpanels7 window = new testmpanels7();
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
	public testmpanels7() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 735, 490);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		layeredPane.setBounds(10, 34, 708, 418);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

				
//********************************************************************************************************************		
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(SystemColor.inactiveCaption);
		

		//**********************************************************************//
		
		//to fill combobox categorie
		ArrayList<String>arr=new ArrayList<String>();
		CategorieDAO catdao= new CategorieDAO();
		Database.Connect();
		arr=catdao.getAllLibelle();
		cbcategorie.setBounds(154, 35, 148, 29);
		cbcategorie.addItem("--choisir element--");
		for(String el:arr) {
			cbcategorie.addItem(el);
		}	
		panel1.setLayout(null);
		cbcategorie.setSelectedIndex(0);
		panel1.add(cbcategorie);		
		JLabel lblcategorie = new JLabel("CATEGORIE");
		lblcategorie.setBounds(50, 35, 94, 29);
		lblcategorie.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblcategorie.setHorizontalAlignment(SwingConstants.CENTER);
		panel1.add(lblcategorie);
		//combo constructeur
		VehiculeDAO vdao=new VehiculeDAO();
		cbconstructeur.setBounds(499, 35, 148, 29);
		cbconstructeur.addItem("--choisir element--");
		cbconstructeur.setSelectedIndex(0);
		panel1.add(cbconstructeur);

		
		cbcategorie.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	cbconstructeur.setSelectedIndex(0);//1
				VehiculeDAO vdao=new VehiculeDAO();
				Database.Connect();
				int id=cbcategorie.getSelectedIndex();
				if(id==0) {
					//affichage de tous les vehicules si index=0
					vehicules=vdao.getAll();					
				}else {
				vehicules=vdao.getVehiculeById_categorie(id); 
				}
				String lib=cbcategorie.getSelectedItem().toString();
				ArrayList<String>arr=new ArrayList<String>();
				arr=vdao.getfoo(lib);				
				ArrayList<String>arr2=new ArrayList<String>();
				arr2.add("---choix elements---");
				for(String el:arr) {
					arr2.add(el);
				} 
				cbconstructeur.setModel(new DefaultComboBoxModel(arr2.toArray()));
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
				table.setModel(model);			
		    }
		});

		
		//***********************************************************************************************
	
		
		cbconstructeur.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
				VehiculeDAO vdao=new VehiculeDAO();
				Database.Connect();
				String str=cbconstructeur.getSelectedItem().toString();
				String foo=cbcategorie.getSelectedItem().toString();
				vehicules=vdao.getVehiculeByCategorie_Constructor(str, foo);						
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
				table.setModel(model);
		    }
		});

		//***************************************************************************

		vehicules=vdao.getAll();
		String columns[]= {"ID","MODEL","PRIX"};
		String data[][]=new String[vehicules.size()][columns.length];
		int i=0;
		for(Vehicule vehicule:vehicules) {
			data[i][0]=vehicule.getId_vehicule()+"";
			data[i][1]=vehicule.getModel();
			data[i][2]=vehicule.getPrix()+"";
			i++;
		}
		model =new DefaultTableModel(data,columns);
		table = new JTable(model);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				model=(DefaultTableModel) table.getModel();
				String id=model.getValueAt(table.getSelectedRow(), 0).toString();
				int id_v=Integer.parseInt(id);
				int selectedId=id_v;
				identity=selectedId;
				System.out.println("selectedId : "+identity);//test				
			}
		});
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(50, 120, 597, 233);
		table.setBounds(39, 57, 323, 143);
		panel1.add(scrollPane);
		

		layeredPane.add(panel1, "name_1227052413448000");
		
		JLabel lblconstructeur = new JLabel("CONSTRUCTEUR");
		lblconstructeur.setBounds(387, 35, 102, 29);
		lblconstructeur.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblconstructeur.setHorizontalAlignment(SwingConstants.CENTER);
		panel1.add(lblconstructeur);
		
//-----------------------------------------------------------------------------------------------------------------------
		
		//bouton informations vehicule(modifier/supprimer)
		JPanel panelinfos = new JPanel();
		panelinfos.setLayout(null);
		panelinfos.setBackground(SystemColor.textHighlight);
		panelinfos.setBounds(544, 368, 103, 39);
		panel1.add(panelinfos);		
		JLabel lblpen = new JLabel("Infos");
		lblpen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				panelinfos.setBorder(null);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelinfos.setBorder(new LineBorder(Color.GREEN));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Database.Connect();
				VehiculeDAO vdao=new VehiculeDAO();	
				Vehicule v=vdao.getById(identity);				
				identity_categorie=v.getId_categorie();
				Vehicule_details3 win_d=new Vehicule_details3(v);
				win_d.getFrame().setVisible(true);				
			}
		});
		lblpen.setBounds(0, 0, 103, 39);
		panelinfos.add(lblpen);
		lblpen.setHorizontalAlignment(SwingConstants.CENTER);
		lblpen.setForeground(SystemColor.inactiveCaptionBorder);
		lblpen.setBackground(SystemColor.textHighlight);
		ImageIcon icon=new ImageIcon(new ImageIcon(this.getClass().getResource("/Ressources/info.png")).getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT ));
		lblpen.setIcon(icon);

		
//***********************************************************************************************************************
			
		JPanel panel2 = new JPanel();
		panel2.setBackground(SystemColor.inactiveCaption);
		panel2.setLayout(null);
	
		datedebut = new JDateChooser();
		datedebut.setBounds(299, 77, 222, 30);
		panel2.add(datedebut);						
		JLabel lbldebut_ct = new JLabel("PRECEDENT CONTROLE TECH");
		lbldebut_ct.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lbldebut_ct.setBounds(93, 77, 196, 30);
		panel2.add(lbldebut_ct);
		
		datefin = new JDateChooser();
		datefin.setBounds(299, 132, 222, 30);
		panel2.add(datefin);		
		JLabel lblfin_ct = new JLabel("PROCHAIN CONTROLE TECH");
		lblfin_ct.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblfin_ct.setBounds(93, 132, 196, 30);
		panel2.add(lblfin_ct);
			
		txtcout_ct = new JTextField();
		txtcout_ct.setBounds(299, 189, 222, 30);
		panel2.add(txtcout_ct);
		txtcout_ct.setColumns(10);		
		JLabel lblcout_ct = new JLabel("COUT");
		lblcout_ct.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblcout_ct.setBounds(93, 189, 111, 30);
		panel2.add(lblcout_ct);		
		layeredPane.add(panel2, "name_1227057071855300");
		
//------------------------------------------------------------------------------------------------------------------------
		
		JPanel panelinfos_ct = new JPanel();
		panelinfos_ct.setLayout(null);
		panelinfos_ct.setBackground(SystemColor.textHighlight);
		panelinfos_ct.setBounds(502, 317, 103, 39);
		panel2.add(panelinfos_ct);		
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
				
				//implémenter fonction 
			}
		});
		lblinfos_ct.setHorizontalAlignment(SwingConstants.CENTER);
		lblinfos_ct.setForeground(SystemColor.inactiveCaptionBorder);
		lblinfos_ct.setBackground(SystemColor.textHighlight);
		ImageIcon icon3=new ImageIcon(new ImageIcon(this.getClass().getResource("/Ressources/info3.png")).getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT ));
		lblinfos_ct.setIcon(icon3);
		lblinfos_ct.setBounds(0, 0, 103, 39);
		panelinfos_ct.add(lblinfos_ct);

	
		
//*********************************************************************************************************************		

		panel3 = new JPanel();
		panel3.setBackground(SystemColor.inactiveCaption);
		layeredPane.add(panel3, "name_1227059779226800");

//****************************************************************************************************************************		
		
		JPanel panel4 = new JPanel();
		panel4.setBackground(SystemColor.inactiveCaption);
		panel4.setLayout(null);
		table_rep = new JTable(model_rep);
		
		scrollPane_rep = new JScrollPane(table_rep);
		scrollPane_rep.setBounds(141, 100, 493, 149);
		scrollPane_rep.setViewportView(table_rep);
		
	
		panel4.add(scrollPane_rep);
		layeredPane.add(panel4, "name_1227066970854400");
		
//------------------------------------------------------------------------------------------------------------------------------
		
		JPanel panelinfos_1 = new JPanel();
		panelinfos_1.setLayout(null);
		panelinfos_1.setBackground(SystemColor.textHighlight);
		panelinfos_1.setBounds(531, 317, 103, 39);
		panel4.add(panelinfos_1);
		
		JLabel lblpen_1 = new JLabel("Infos");
		lblpen_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				panelinfos_1.setBorder(null);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelinfos_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.GREEN, null, null, null));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				int id=Integer.parseInt(model_rep.getValueAt(table_rep.getSelectedRow(), 0).toString());
				Database.Connect();
				ReparationDAO rdao=new ReparationDAO();
				Reparation rep=rdao.getById(id);
				System.out.println("id tab rep:"+id);
				Reparation_detailsBis win_rep=new Reparation_detailsBis(rep);
				win_rep.getFrame().setVisible(true);
			}
		});
		lblpen_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblpen_1.setForeground(SystemColor.inactiveCaptionBorder);
		lblpen_1.setBackground(SystemColor.textHighlight);
		ImageIcon icon2=new ImageIcon(new ImageIcon(this.getClass().getResource("/Ressources/info2.png")).getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT ));
		lblpen_1.setIcon(icon2);
		lblpen_1.setBounds(0, 0, 103, 39);
		panelinfos_1.add(lblpen_1);

//---------------------------------------------------------------------------------------------------------------------------
		
		JPanel panelpart1 = new JPanel();
		panelpart1.setLayout(null);
		panelpart1.setBackground(SystemColor.textHighlight);
		panelpart1.setBounds(10, 0, 198, 34);
		frame.getContentPane().add(panelpart1);
		
		JLabel lblpart1 = new JLabel("Selectioner-Supprimer/Modifier");
		lblpart1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				panelpart1.setBorder(null);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelpart1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.GREEN, null, null, null));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				SwitchPanels(panel1);
			}
		});
		lblpart1.setHorizontalAlignment(SwingConstants.CENTER);
		lblpart1.setForeground(SystemColor.inactiveCaptionBorder);
		lblpart1.setBackground(SystemColor.textHighlight);
		lblpart1.setBounds(0, 0, 198, 34);
		panelpart1.add(lblpart1);
				
//--------------------------------------------------------------------------------------------------------------------		
				
		JPanel panelcontrole_tech = new JPanel();
		panelcontrole_tech.setLayout(null);
		panelcontrole_tech.setBackground(SystemColor.textHighlight);
		panelcontrole_tech.setBounds(210, 0, 177, 34);
		frame.getContentPane().add(panelcontrole_tech);		
		JLabel lblcontrole_tech = new JLabel("Infos Controle tech");
		lblcontrole_tech.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				panelcontrole_tech.setBorder(null);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelcontrole_tech.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.GREEN, null, null, null));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				SwitchPanels(panel2);				
				Database.Connect();				
				Controle_techDAO ctdao= new Controle_techDAO();
				ct=ctdao.getById_vehicule(identity);
				datedebut.setDate(ct.getDate_debut_ct());
				datefin.setDate(ct.getDate_fin_ct());
				txtcout_ct.setText(ct.getCout_ct()+"");
			}
		});
		lblcontrole_tech.setHorizontalAlignment(SwingConstants.CENTER);
		lblcontrole_tech.setForeground(SystemColor.inactiveCaptionBorder);
		lblcontrole_tech.setBackground(SystemColor.textHighlight);
		lblcontrole_tech.setBounds(0, 0, 171, 34);
		panelcontrole_tech.add(lblcontrole_tech);
		
//------------------------------------------------------------------------------------------------------------------------------		
		
		JPanel paneladd = new JPanel();
		paneladd.setLayout(null);
		paneladd.setBackground(SystemColor.textHighlight);
		paneladd.setBounds(389, 0, 148, 34);
		frame.getContentPane().add(paneladd);		
		JLabel lbladd= new JLabel("Ajouter");
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
				SwitchPanels(panel3);			
				Vehicule_add2 win_add=new Vehicule_add2();
				win_add.getFrame().setVisible(true);
			}
		});		
		lbladd.setHorizontalAlignment(SwingConstants.CENTER);
		lbladd.setForeground(SystemColor.inactiveCaptionBorder);
		lbladd.setBackground(SystemColor.textHighlight);
		lbladd.setBounds(0, 0, 142, 34);
		paneladd.add(lbladd);
		
		
//--------------------------------------------------------------------------------------------------------------------------------		
		
		JPanel panelrep= new JPanel();
		panelrep.setLayout(null);
		panelrep.setBackground(SystemColor.textHighlight);
		panelrep.setBounds(539, 0, 148, 34);
		frame.getContentPane().add(panelrep);		
		JLabel lblrep = new JLabel("Infos reparations");
		lblrep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				panelrep.setBorder(null);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelrep.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.GREEN, null, null, null));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				SwitchPanels(panel4);				
				b4();
			}
		});
		lblrep.setHorizontalAlignment(SwingConstants.CENTER);
		lblrep.setForeground(SystemColor.inactiveCaptionBorder);
		lblrep.setBackground(SystemColor.textHighlight);
		lblrep.setBounds(0, 0, 142, 34);
		panelrep.add(lblrep);
		
	}
	
	
	
	
//-----------------------------------methodes------------------------------------------------------------------------------------------
	
	public static void b4() {
		ReparationDAO rdao=new ReparationDAO();
		Database.Connect();
		System.out.println("identite:"+identity);//test
		reparations=rdao.getReparationById_vehicule(identity);
		String columns_tab2[]= {"ID","DATE","DESCRIPTIF","COUT"};//****
		String data_tab2[][]=new String[reparations.size()][columns_tab2.length];//****
		int j=0;
		for(Reparation rep:reparations) {
			data_tab2[j][0]=rep.getId_reparation()+"";
			data_tab2[j][1]=rep.getDate_reparation()+"";
			data_tab2[j][2]=rep.getDescriptif();
			data_tab2[j][3]=rep.getCout()+"";
			j++;
		}
		model_rep=new DefaultTableModel(data_tab2,columns_tab2);//****
		table_rep.setModel(model_rep);
	}
	
	
	public static void b1() {
		VehiculeDAO vdao= new VehiculeDAO();
		Database.Connect();
		vehicules=vdao.getAll();
		String columns[]= {"ID","MODEL","PRIX"};
		String data[][]=new String[vehicules.size()][columns.length];
		int i=0;
		for(Vehicule vehicule:vehicules) {
			data[i][0]=vehicule.getId_vehicule()+"";
			data[i][1]=vehicule.getModel();
			data[i][2]=vehicule.getPrix()+"";
			i++;
		}
		model=new DefaultTableModel(data,columns);
		table.setModel(model);
	}
}


