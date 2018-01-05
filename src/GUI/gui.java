package GUI;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import GUI.NioFileSupport.MyWatchQueueReader;
import Objects.FilterInfo;
import Objects.Mac;
import Objects.Row;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import Filter.Filter;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import javax.swing.JTextArea;


public class gui {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui window = new gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * listOutput is the Data Structure
	 */	
	private List<FilterInfo> listInfo = new ArrayList<FilterInfo>(); 
	private List<Row> listInput = new ArrayList<Row>(); 	
	private JTextField nameTxt;
	private JTextField LocaionAltTxt;
	private JTextField LocaionLonTxt;
	private JTextField LocaionRadiosTxt;
	private Filter filter=new Filter();
	private JComboBox FilterType;
	private String kind;
	private int countfilter=0;
	private int IndexOr=0;
	private boolean flag=true;
	private boolean DataStructureEmpty=false;
	private String destination;
	
	private JRadioButton dateRadioBut;
	private JRadioButton nameRadioBut;
	private JRadioButton locationRadioBut;
	private JCheckBox NotCheckBox;
	private JTextField MACTxt;
	private JTextField algo1LocaionRadiosTxt;
	private JTextField algo1LocaionLonTxt;
	private JTextField algo1LocaionAltTxt;
	private String wlat;
	private String wlon;
	private String walt;
	private JTextField amountMACTxt;
	private JTextField amountListsTxt;
	private JTextField FilterCharTxt;
	private JTextArea informationTxt;
	private JLabel informationLbl;
	private JTextField algo2LocaionRadiosTxt;
	private JTextField algo2LocaionLonTxt;
	private JTextField algo2LocaionAltTxt;
	private JTextField algo2NumberRowTxt;
	private JLabel algo2NumberRowLbl;
	private JTextField algo2MAC1Txt;
	private JTextField algo2Signal1Txt;
	private JTextField algo2MAC2Txt;
	private JTextField algo2Signal2Txt;
	private JTextField algo2MAC3Txt;
	private JTextField algo2Signal3Txt;
	private JRadioButton algo2FirstRadioBut;
	private JRadioButton algo2SecondRadioBut;
		
	
	public gui() {		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 1500, 629);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);


		/**
		 * **************** INPUT - OUTPUT ***************
		 */	

		JLabel InputOutputLbl = new JLabel("Inout Output");
		InputOutputLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		InputOutputLbl.setBounds(92, 31, 95, 45);
		frame.getContentPane().add(InputOutputLbl);
		/**
		 * Button of Get files from directory 
		 */			
		JButton inputDirBut = new JButton("Get files from dir");
		inputDirBut.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				/** 
				 * This button reads a directory that contains multiple Wigle-files
				 * via path the user choose
				 * After it, he needs to write path+name_file to save it as CSV
				 */				
				JFileChooser chooserGetDir = new JFileChooser();
				chooserGetDir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);			
				int returnNameDir = chooserGetDir.showOpenDialog(null);
				String pathGetDir = "";			
				if (returnNameDir == JFileChooser.APPROVE_OPTION) {					 	
					File f = chooserGetDir.getSelectedFile();
					pathGetDir = f.getAbsolutePath();
				}					
				pathGetDir = pathGetDir.replace("\\","/");
																		
				System.out.println("pathGetDir = "+pathGetDir);				
				String dirPath = pathGetDir;
				try {
					if(filter.readq2(dirPath) == true)
						DataStructureEmpty = true;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		});
		inputDirBut.setBounds(52, 109, 171, 25);
		frame.getContentPane().add(inputDirBut);
		
		/**
		 * Button of Read file 
		 */		
		JButton btnReadFile = new JButton("Read File");
		btnReadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser  fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnVal = fc.showOpenDialog(btnReadFile);
				if(returnVal == JFileChooser.APPROVE_OPTION) 
				{										
					File f = fc.getSelectedFile();
					String desPath = f.getAbsolutePath();
					System.out.println("desPath = "+desPath);						
					if (!(f.isFile() && f.getName().endsWith("csv")))
						JOptionPane.showMessageDialog(frame,"Wrong file ! choose only CSV !");			
					else{
						try {
							filter.read(fc.getSelectedFile().getAbsolutePath().replace("\\","/"));
							DataStructureEmpty = true;
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
		
				}
				
				
			}
		});
		btnReadFile.setBounds(52, 164, 171, 25);
		frame.getContentPane().add(btnReadFile);
		
		/**
		 * Button of Save as CSV
		 */	
		JButton saveCSVBut = new JButton("Save as CSV");
		saveCSVBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				/** 
				 * This button the user needs to write a path+name_file to save it as CSV		
				 */						
				String desPath = getFilePath();
				if(desPath == "")
					JOptionPane.showMessageDialog(frame,"You didnt choose a path !");
				else{
					String desPath2 = desPath+".csv";
					filter.write(desPath2);
				}
				
				/*ReadAndWriteCSV write = new ReadAndWriteCSV();
				if(desPath == "")
					JOptionPane.showMessageDialog(frame,"You didnt choose a path !");
				else if(listOutput.isEmpty()){
					JOptionPane.showMessageDialog(frame,"Data Structure is empty !");
					System.out.println(countfilter);
				}
				else{
					write.WriteListIntoFile(Undo.get(countfilter),desPath2);	
				}
				*/
			}
		});
		saveCSVBut.setBounds(52, 283, 171, 25);
		frame.getContentPane().add(saveCSVBut);
		/**
		 * Button of Save as KML
		 */	
		JButton saveKMLBut = new JButton("Save as KML");
		saveKMLBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				/** 
				 * This button the user needs to write a path+name_file to save it as KML		
				 */						
				String desPath = getFilePath();
				String desPath2 = desPath+".kml";

				/*WriteToKML kml=new WriteToKML();
				if(desPath == "")
					JOptionPane.showMessageDialog(frame,"You didnt choose a path !");
				else if(listOutput.isEmpty())
					JOptionPane.showMessageDialog(frame,"Data Structure is empty !");
				else
					kml.createKMLFile(listOutput,desPath2);*/
			}
		});
		saveKMLBut.setBounds(52, 341, 171, 25);
		frame.getContentPane().add(saveKMLBut);

		/**
		 * Button of Delete
		 */	
		JButton deleteBut = new JButton("Delete");
		deleteBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				/*listOutput.clear();
				JOptionPane.showMessageDialog(frame,"Data Structure is now empty !");*/
				DataStructureEmpty = false;
			}
		});
		deleteBut.setBounds(52, 223, 171, 25);
		frame.getContentPane().add(deleteBut);

		JLabel amountListsLbl = new JLabel("amount of lists");
		amountListsLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		amountListsLbl.setBounds(12, 451, 87, 45);
		frame.getContentPane().add(amountListsLbl);

		JLabel amountMACLbl = new JLabel("amount of MAC");
		amountMACLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		amountMACLbl.setBounds(12, 411, 87, 45);
		frame.getContentPane().add(amountMACLbl);

		JLabel FilterCharLbl = new JLabel("Filter characteristics");
		FilterCharLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		FilterCharLbl.setBounds(12, 486, 120, 45);
		frame.getContentPane().add(FilterCharLbl);	
		
		amountMACTxt = new JTextField();
		amountMACTxt.setEditable(false);
		amountMACTxt.setColumns(10);
		amountMACTxt.setBounds(131, 422, 75, 22);
		frame.getContentPane().add(amountMACTxt);
		
		amountListsTxt = new JTextField();
		amountListsTxt.setEditable(false);
		amountListsTxt.setColumns(10);
		amountListsTxt.setBounds(131, 462, 75, 22);
		frame.getContentPane().add(amountListsTxt);
		
		FilterCharTxt = new JTextField();
		FilterCharTxt.setEditable(false);
		FilterCharTxt.setColumns(10);
		FilterCharTxt.setBounds(131, 497, 75, 22);
		frame.getContentPane().add(FilterCharTxt);

		/**
		 * **************** FILTER ***************
		 */	

		JLabel filterLbl = new JLabel("Filter");
		filterLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		filterLbl.setBounds(408, 31, 61, 45);
		frame.getContentPane().add(filterLbl);
		
		/**
		 * Button of start date
		 */	
		JLabel dateChooserMinLbl = new JLabel("Min Date");
		dateChooserMinLbl.setBounds(337, 140, 56, 16);
		frame.getContentPane().add(dateChooserMinLbl);

		JDateChooser dateChooserMin = new JDateChooser();
		dateChooserMin.setBounds(314, 169, 95, 20);
		frame.getContentPane().add(dateChooserMin);

		/**
		 * Button of end date
		 */
		JLabel dateChooserMaxLbl = new JLabel("Max Date");
		dateChooserMaxLbl.setBounds(453, 140, 56, 16);
		frame.getContentPane().add(dateChooserMaxLbl);	

		JDateChooser dateChooserMax = new JDateChooser();
		dateChooserMax.setBounds(434, 169, 95, 20);
		frame.getContentPane().add(dateChooserMax);

		/**
		 * Buttons of start time and end time
		 */			
		Date dateMin = new Date();
		Date dateMax = new Date();
		SpinnerDateModel smhMin = new SpinnerDateModel(dateMin, null, null, Calendar.HOUR_OF_DAY);
		SpinnerDateModel smhMax = new SpinnerDateModel(dateMax, null, null, Calendar.HOUR_OF_DAY);
		JSpinner spinnerMin = new JSpinner(smhMin); 
		JSpinner spinnerMax = new JSpinner(smhMax); 
		JSpinner.DateEditor deMin = new JSpinner.DateEditor(spinnerMin, "hh:mm:ss a");
		JSpinner.DateEditor deMax = new JSpinner.DateEditor(spinnerMax, "hh:mm:ss a");
		spinnerMin.setEditor(deMin);
		spinnerMax.setEditor(deMax);
		spinnerMax.setBounds(434,202,95,30); 
		spinnerMin.setBounds(314,202,95,30); 
		frame.getContentPane().add(spinnerMin);  		   
		frame.getContentPane().add(spinnerMax);

		nameTxt = new JTextField();
		nameTxt.setBounds(353, 312, 144, 22);
		frame.getContentPane().add(nameTxt);
		nameTxt.setColumns(10);

		LocaionAltTxt = new JTextField();
		LocaionAltTxt.setBounds(291, 436, 75, 22);
		frame.getContentPane().add(LocaionAltTxt);
		LocaionAltTxt.setColumns(10);

		LocaionLonTxt = new JTextField();
		LocaionLonTxt.setColumns(10);
		LocaionLonTxt.setBounds(380, 436, 75, 22);
		frame.getContentPane().add(LocaionLonTxt);

		LocaionRadiosTxt = new JTextField();
		LocaionRadiosTxt.setColumns(10);
		LocaionRadiosTxt.setBounds(467, 436, 75, 22);
		frame.getContentPane().add(LocaionRadiosTxt);

		JLabel altLbl = new JLabel("Alt");
		altLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		altLbl.setBounds(314, 399, 38, 36);
		frame.getContentPane().add(altLbl);

		JLabel LonLbl = new JLabel("Lon");
		LonLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LonLbl.setBounds(398, 399, 38, 36);
		frame.getContentPane().add(LonLbl);

		JLabel radiosLbl = new JLabel("Radios");
		radiosLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radiosLbl.setBounds(488, 399, 59, 36);
		frame.getContentPane().add(radiosLbl);

		/**
		 * Button of And
		 */			
		JButton AndBut = new JButton("And");		
		AndBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				boolean flagAnd = false;
				int n = filter.getCountfilter();
				if(DataStructureEmpty == false)
					JOptionPane.showMessageDialog(frame,"Data structure is empty !");
				else{
					boolean not=NotCheckBox.isSelected();
					if(nameRadioBut.isSelected()){

						System.out.println("name");
						String strName = nameTxt.getText();
						System.out.println("strName = "+strName);
						if(strName.equals(""))
							JOptionPane.showMessageDialog(frame,"You didnt enter a name !");
						else{
							filter.filtermain(false,not, 1,strName,"","");
							listInfo.add(new FilterInfo("And",not, "Name", strName));
							flagAnd = true;
						}
					}					
					else if(dateRadioBut.isSelected()){

						System.out.println("date");					
						SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");	
						Date time1 = (Date)spinnerMin.getValue();
						String formattedDate = format.format(time1);					
						Date dateFromDateChooser = dateChooserMin.getDate();
						String dateString = String.format("%1$td-%1$tm-%1$tY", dateFromDateChooser);															
						String startDate = dateString+" "+formattedDate;
											
						format = new SimpleDateFormat("HH:mm:ss");	
						Date time2 = (Date)spinnerMax.getValue();
						formattedDate = format.format(time2);								
						dateFromDateChooser = dateChooserMax.getDate();
						dateString = String.format("%1$td-%1$tm-%1$tY", dateFromDateChooser);						
						String endDate = dateString+" "+formattedDate;

						String time = startDate+" "+endDate;						
						System.out.println("startDate = "+startDate);
						System.out.println("endDate = "+endDate);
						if(startDate.equals(""))
							JOptionPane.showMessageDialog(frame,"You didnt enter a start date !");
						else if(endDate.equals(""))
							JOptionPane.showMessageDialog(frame,"You didnt enter a end date !"); 
						else{
							filter.filtermain(false,not, 2,startDate,endDate,"");
							listInfo.add(new FilterInfo("And",not, "Date", time));
							flagAnd = true;
						}
					}					
					else if(locationRadioBut.isSelected()){

						System.out.println("location");
						String strAlt = LocaionAltTxt.getText();
						System.out.println("strAlt = "+strAlt);
						String strLon = LocaionLonTxt.getText();
						System.out.println("strLon = "+strLon);
						String strRadios = LocaionRadiosTxt.getText();
						System.out.println("strRadios = "+strRadios);
						String location = "alt = "+strAlt+" Lon = "+strLon+" Radios = "+strRadios;
						if(strAlt.equals(""))
							JOptionPane.showMessageDialog(frame,"You didnt enter Alt !");
						else if(strLon.equals(""))
							JOptionPane.showMessageDialog(frame,"You didnt enter Lon !");
						else if(strRadios.equals(""))
							JOptionPane.showMessageDialog(frame,"You didnt enter Radios !");
						else{					
							filter.filtermain(false,not, 3,strAlt,strLon,strRadios);
							listInfo.add(new FilterInfo("And",not, "Location", location));
							flagAnd = true;
						}					
					}
					else
						JOptionPane.showMessageDialog(frame,"You didnt choose a type of filter !");
				}
				if(flagAnd == true && n+1!=filter.getCountfilter())
					listInfo.remove(listInfo.size()-1);
				informationTxt.setText(listInfo.toString());
				flagAnd = false;
		
			}
		});
		
		/**
		 * Button of Or
		 */	
		JButton OrBut = new JButton("Or");
		OrBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				boolean flagOr = false;
				int n = filter.getCountfilter();
				if(DataStructureEmpty == false)
					JOptionPane.showMessageDialog(frame,"Data structure is empty !");
				else{
					boolean not=NotCheckBox.isSelected();
					if(nameRadioBut.isSelected()){

						System.out.println("name");
						String strName = nameTxt.getText();
						System.out.println("strName = "+strName);
						filter.filtermain(true,not, 1,strName,"","");
						listInfo.add(new FilterInfo("Or",not, "Name", strName));
						flagOr = true;
					}					
					else if(dateRadioBut.isSelected()){

						System.out.println("date");					
						SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");	
						Date time1 = (Date)spinnerMin.getValue();
						String formattedDate = format.format(time1);					
						Date dateFromDateChooser = dateChooserMin.getDate();
						String dateString = String.format("%1$td-%1$tm-%1$tY", dateFromDateChooser);															
						String startDate = dateString+" "+formattedDate;
											
						format = new SimpleDateFormat("HH:mm:ss");	
						Date time2 = (Date)spinnerMax.getValue();
						formattedDate = format.format(time2);								
						dateFromDateChooser = dateChooserMax.getDate();
						dateString = String.format("%1$td-%1$tm-%1$tY", dateFromDateChooser);						
						String endDate = dateString+" "+formattedDate;
						
						System.out.println("startDate = "+startDate);
						System.out.println("endDate = "+endDate);
						String time = startDate+" "+endDate;
						filter.filtermain(true,not, 2,startDate,endDate,"");
						listInfo.add(new FilterInfo("Or",not, "Date", time));
						flagOr = true;
					}					
					else if(locationRadioBut.isSelected()){

						System.out.println("location");
						String strAlt = LocaionAltTxt.getText();
						System.out.println("strAlt = "+strAlt);
						String strLon = LocaionLonTxt.getText();
						System.out.println("strLon = "+strLon);
						String strRadios = LocaionRadiosTxt.getText();
						System.out.println("strRadios = "+strRadios);
						String location = "alt = "+strAlt+" Lon = "+strLon+" Radios = "+strRadios;
						filter.filtermain(true,not, 3,strAlt,strLon,strRadios);
						listInfo.add(new FilterInfo("Or",not, "location", location));
						flagOr = true;
					}
					else
						JOptionPane.showMessageDialog(frame,"You didnt choose a type of filter !");
				}				
				if(flagOr == true && n+1!=filter.getCountfilter())
					listInfo.remove(listInfo.size()-1);
				informationTxt.setText(listInfo.toString());
				flagOr = false;
			}
		});
		OrBut.setBounds(488, 508, 87, 25);
		frame.getContentPane().add(OrBut);
		
		/**
		 * Button of Undo
		 */	
		AndBut.setBounds(385, 508, 95, 25);
		frame.getContentPane().add(AndBut);
		
		JButton UndoBut = new JButton("Undo");
		UndoBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(filter.getCountfilter() > 0)
				{
					filter.undo();
					listInfo.remove(listInfo.size()-1);
					informationTxt.setText(listInfo.toString());
				}else
					JOptionPane.showMessageDialog(frame,"Cant do undo !");
				
			}
		});
		UndoBut.setBounds(278, 508, 95, 25);
		frame.getContentPane().add(UndoBut);
		/**
		 * CheckBox of Not
		 */	

		NotCheckBox = new JCheckBox("not");
		NotCheckBox.setBounds(584, 506, 56, 25);
		frame.getContentPane().add(NotCheckBox);	
		
		/**
		 * Radio Button choose filter
		 */
		ButtonGroup buttonGroup = new ButtonGroup();
				
		dateRadioBut = new JRadioButton("Date");
		dateRadioBut.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dateRadioBut.setBounds(545, 95, 61, 25);
		nameRadioBut = new JRadioButton("Name");
		nameRadioBut.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nameRadioBut.setBounds(545, 282, 86, 25);
		locationRadioBut = new JRadioButton("Location");
		locationRadioBut.setFont(new Font("Tahoma", Font.PLAIN, 16));
		locationRadioBut.setBounds(545, 365, 117, 25);
		
		buttonGroup.add(dateRadioBut);
		buttonGroup.add(nameRadioBut);
		buttonGroup.add(locationRadioBut);
						
		frame.getContentPane().add(dateRadioBut);				
		frame.getContentPane().add(nameRadioBut);				
		frame.getContentPane().add(locationRadioBut);
				

		/**
		 * ********************Algorithms************************
		 */	
		
		JLabel algorithmLbl = new JLabel("Algorithm");
		algorithmLbl.setHorizontalAlignment(SwingConstants.CENTER);
		algorithmLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		algorithmLbl.setBounds(816, 31, 112, 45);
		frame.getContentPane().add(algorithmLbl);
		
		/**
		 * algorithm 1
		 */
		JLabel algo1Lbl = new JLabel("Algorithm 1");
		algo1Lbl.setHorizontalAlignment(SwingConstants.CENTER);
		algo1Lbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo1Lbl.setBounds(840, 89, 95, 36);
		frame.getContentPane().add(algo1Lbl);
		
		JLabel algo1MACLbl = new JLabel("MAC");
		algo1MACLbl.setHorizontalAlignment(SwingConstants.CENTER);
		algo1MACLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo1MACLbl.setBounds(973, 128, 57, 36);
		frame.getContentPane().add(algo1MACLbl);
		
		MACTxt = new JTextField();
		MACTxt.setColumns(10);
		MACTxt.setBounds(694, 137, 258, 22);
		frame.getContentPane().add(MACTxt);
		
		JLabel algo1LocationLbl = new JLabel("Location");
		algo1LocationLbl.setHorizontalAlignment(SwingConstants.CENTER);
		algo1LocationLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo1LocationLbl.setBounds(973, 169, 57, 36);
		frame.getContentPane().add(algo1LocationLbl);
		
		JLabel algo1AltLbl = new JLabel("Alt");
		algo1AltLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo1AltLbl.setBounds(730, 169, 38, 36);
		frame.getContentPane().add(algo1AltLbl);
		
		JLabel algo1LonLbl = new JLabel("Lon");
		algo1LonLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo1LonLbl.setBounds(808, 169, 38, 36);
		frame.getContentPane().add(algo1LonLbl);
		
		JLabel algo1LatLbl = new JLabel("Lat");
		algo1LatLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo1LatLbl.setBounds(879, 169, 59, 36);
		frame.getContentPane().add(algo1LatLbl);
		
		algo1LocaionRadiosTxt = new JTextField();
		algo1LocaionRadiosTxt.setColumns(10);
		algo1LocaionRadiosTxt.setBounds(870, 202, 75, 22);
		algo1LocaionRadiosTxt.setEditable(false);
		frame.getContentPane().add(algo1LocaionRadiosTxt);
		
		algo1LocaionLonTxt = new JTextField();
		algo1LocaionLonTxt.setColumns(10);
		algo1LocaionLonTxt.setBounds(783, 202, 75, 22);
		algo1LocaionLonTxt.setEditable(false);
		frame.getContentPane().add(algo1LocaionLonTxt);
		
		algo1LocaionAltTxt = new JTextField();
		algo1LocaionAltTxt.setColumns(10);
		algo1LocaionAltTxt.setBounds(694, 202, 75, 22);
		algo1LocaionAltTxt.setEditable(false);
		frame.getContentPane().add(algo1LocaionAltTxt);
		
		/**
		 * Run of algorithm 1
		 */
		JButton algo1RunBut = new JButton("Run");
		algo1RunBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{	
				String mac = MACTxt.getText();
				System.out.println(mac);
				Mac fin= filter.mac(mac);
				walt=""+fin.getAlt();
				wlat=""+fin.getLat();
				wlon=""+fin.getLon();
			
				algo1LocaionRadiosTxt.setText(wlat);
				algo1LocaionLonTxt.setText(wlon);
				algo1LocaionAltTxt.setText(walt);			
			}
		});
		algo1RunBut.setBounds(741, 96, 87, 25);
		frame.getContentPane().add(algo1RunBut);
		
		/**
		 *  algorithm 2
		 */
		
		JLabel algo2Lbl = new JLabel("Algorithm 2");
		algo2Lbl.setHorizontalAlignment(SwingConstants.CENTER);
		algo2Lbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo2Lbl.setBounds(840, 253, 95, 36);
		frame.getContentPane().add(algo2Lbl);
		
		JLabel algo2LocationLbl = new JLabel("Location");
		algo2LocationLbl.setHorizontalAlignment(SwingConstants.CENTER);
		algo2LocationLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo2LocationLbl.setBounds(973, 501, 57, 36);
		frame.getContentPane().add(algo2LocationLbl);
		
		JLabel algo2LatLbl = new JLabel("Lat");
		algo2LatLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo2LatLbl.setBounds(909, 501, 35, 34);
		frame.getContentPane().add(algo2LatLbl);
		
		algo2LocaionRadiosTxt = new JTextField();
		algo2LocaionRadiosTxt.setEditable(false);
		algo2LocaionRadiosTxt.setColumns(10);
		algo2LocaionRadiosTxt.setBounds(879, 534, 85, 22);
		frame.getContentPane().add(algo2LocaionRadiosTxt);
		
		algo2LocaionLonTxt = new JTextField();
		algo2LocaionLonTxt.setEditable(false);
		algo2LocaionLonTxt.setColumns(10);
		algo2LocaionLonTxt.setBounds(783, 534, 85, 22);
		frame.getContentPane().add(algo2LocaionLonTxt);
		
		JLabel algo2LonLbl = new JLabel("Lon");
		algo2LonLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo2LonLbl.setBounds(808, 501, 38, 36);
		frame.getContentPane().add(algo2LonLbl);
		
		JLabel algo2AltLbl = new JLabel("Alt");
		algo2AltLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo2AltLbl.setBounds(710, 501, 38, 36);
		frame.getContentPane().add(algo2AltLbl);
		
		algo2LocaionAltTxt = new JTextField();
		algo2LocaionAltTxt.setEditable(false);
		algo2LocaionAltTxt.setColumns(10);
		algo2LocaionAltTxt.setBounds(683, 534, 85, 22);
		frame.getContentPane().add(algo2LocaionAltTxt);
		
		algo2MAC1Txt = new JTextField();
		algo2MAC1Txt.setColumns(10);
		algo2MAC1Txt.setBounds(808, 399, 158, 22);
		frame.getContentPane().add(algo2MAC1Txt);
		
		algo2Signal1Txt = new JTextField();
		algo2Signal1Txt.setColumns(10);
		algo2Signal1Txt.setBounds(713, 399, 59, 22);
		frame.getContentPane().add(algo2Signal1Txt);
		
		algo2MAC2Txt = new JTextField();
		algo2MAC2Txt.setColumns(10);
		algo2MAC2Txt.setBounds(808, 431, 158, 22);
		frame.getContentPane().add(algo2MAC2Txt);
		
		algo2Signal2Txt = new JTextField();
		algo2Signal2Txt.setColumns(10);
		algo2Signal2Txt.setBounds(713, 431, 59, 22);
		frame.getContentPane().add(algo2Signal2Txt);
		
		algo2MAC3Txt = new JTextField();
		algo2MAC3Txt.setColumns(10);
		algo2MAC3Txt.setBounds(808, 462, 158, 22);
		frame.getContentPane().add(algo2MAC3Txt);
		
		algo2Signal3Txt = new JTextField();
		algo2Signal3Txt.setColumns(10);
		algo2Signal3Txt.setBounds(713, 462, 59, 22);
		frame.getContentPane().add(algo2Signal3Txt);				
	
		JLabel algo2MACLbl = new JLabel("MAC");
		algo2MACLbl.setHorizontalAlignment(SwingConstants.CENTER);
		algo2MACLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo2MACLbl.setBounds(859, 357, 57, 36);
		frame.getContentPane().add(algo2MACLbl);
		
		JLabel algo2SiganlLbl = new JLabel("Signal");
		algo2SiganlLbl.setHorizontalAlignment(SwingConstants.CENTER);
		algo2SiganlLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo2SiganlLbl.setBounds(710, 357, 57, 36);
		frame.getContentPane().add(algo2SiganlLbl);
		
		JLabel algo2MAC1Lbl = new JLabel("1");
		algo2MAC1Lbl.setHorizontalAlignment(SwingConstants.CENTER);
		algo2MAC1Lbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo2MAC1Lbl.setBounds(973, 399, 57, 25);
		frame.getContentPane().add(algo2MAC1Lbl);
		
		JLabel algo2MAC2Lbl = new JLabel("2");
		algo2MAC2Lbl.setHorizontalAlignment(SwingConstants.CENTER);
		algo2MAC2Lbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo2MAC2Lbl.setBounds(973, 431, 57, 25);
		frame.getContentPane().add(algo2MAC2Lbl);
		
		JLabel algo2MAC3Lbl = new JLabel("3");
		algo2MAC3Lbl.setHorizontalAlignment(SwingConstants.CENTER);
		algo2MAC3Lbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo2MAC3Lbl.setBounds(973, 459, 57, 25);
		frame.getContentPane().add(algo2MAC3Lbl);
		algo2NumberRowTxt = new JTextField();
		algo2NumberRowTxt.setColumns(10);
		algo2NumberRowTxt.setBounds(730, 319, 75, 22);
		frame.getContentPane().add(algo2NumberRowTxt);
		
		algo2NumberRowLbl = new JLabel("Row Number");
		algo2NumberRowLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		algo2NumberRowLbl.setBounds(730, 298, 75, 25);
		frame.getContentPane().add(algo2NumberRowLbl);
		
			
		/**
		 * Run of algorithm 2
		 */
		
		JButton algo2RunBut = new JButton("Run");
		algo2RunBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(algo2FirstRadioBut.isSelected())
				{
					String numberOfRow = algo2NumberRowTxt.getText();
					System.out.println("numberOfRow = "+numberOfRow);
					System.out.println("destination = "+destination);	
				}else if(algo2SecondRadioBut.isSelected())
				{
					String mac1 = algo2MAC1Txt.getText();
					String mac2 = algo2MAC2Txt.getText();
					String mac3 = algo2MAC3Txt.getText();
					String signal1 = algo2Signal1Txt.getText();
					String signal2 = algo2Signal2Txt.getText();
					String signal3 = algo2Signal3Txt.getText();
				}
				else
					JOptionPane.showMessageDialog(frame,"You didnt choose a type of filter in algorithm 2!");
						
			}
		});
		algo2RunBut.setBounds(730, 260, 87, 25);
		frame.getContentPane().add(algo2RunBut);
		
		/**
		 * Choose file of algorithm 2
		 */
		
		JButton algo2ChooseFileBut = new JButton("Choose File");
		algo2ChooseFileBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser  fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnVal = fc.showOpenDialog(btnReadFile);
				if(returnVal == JFileChooser.APPROVE_OPTION) 
				{										
					File f = fc.getSelectedFile();
					String desPath = f.getAbsolutePath();
					System.out.println("desPath = "+desPath);						
					if (!(f.isFile() && f.getName().endsWith("csv")))
						JOptionPane.showMessageDialog(frame,"Wrong file ! choose only CSV !");			
					else{					
						desPath = fc.getSelectedFile().getAbsolutePath().replace("\\","/");
						destination = desPath;
						System.out.println("desPath2 = "+desPath);	
					}
				}
			}
		});
		algo2ChooseFileBut.setBounds(816, 319, 150, 25);
		frame.getContentPane().add(algo2ChooseFileBut);
		
		/**
		 * Radio buttons of algorithm 2
		 */	
		
		ButtonGroup buttonGroup2 = new ButtonGroup();
		
		algo2FirstRadioBut = new JRadioButton("1");
		algo2FirstRadioBut.setBounds(1008, 311, 44, 25);
		algo2SecondRadioBut = new JRadioButton("2");
		algo2SecondRadioBut.setBounds(1008, 364, 38, 25);
		
		frame.getContentPane().add(algo2FirstRadioBut);	
		frame.getContentPane().add(algo2SecondRadioBut);
				
		buttonGroup2.add(algo2FirstRadioBut);
		buttonGroup2.add(algo2SecondRadioBut);
							
		frame.getContentPane().add(algo2FirstRadioBut);				
		frame.getContentPane().add(algo2SecondRadioBut);				
		
		/**
		 * *********************INFORMATION*************
		 */	
		
		informationTxt = new JTextArea();
		informationTxt.setEditable(false);
		informationTxt.setBounds(1075, 91, 365, 408);
		frame.getContentPane().add(informationTxt);
		
		informationLbl = new JLabel("Information");
		informationLbl.setHorizontalAlignment(SwingConstants.CENTER);
		informationLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		informationLbl.setBounds(1186, 31, 112, 45);
		frame.getContentPane().add(informationLbl);
		
		/**
		 * Button of Exit
		 */	
		JButton exitBut = new JButton("Exit");
		exitBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});				
		exitBut.setFont(new Font("Tahoma", Font.BOLD, 18));
		exitBut.setBounds(1299, 531, 171, 25);
		frame.getContentPane().add(exitBut);
		
						
	}
	private String getFilePath()
	{
		/**
		 * This function returns the path the user choose
		 */
		JFileChooser chooserDestFile = new JFileChooser();
		int returnNameFile = chooserDestFile.showOpenDialog(null);
		String pathDestFile = "";

		if (returnNameFile == JFileChooser.APPROVE_OPTION) {					 	
			File f = chooserDestFile.getSelectedFile();
			if (f != null)  // Make sure the user didn't choose a directory.					 
				pathDestFile = f.getAbsolutePath();//get the absolute path to selected file		           		            
		}								
		pathDestFile = pathDestFile.replace("\\","/");
		return pathDestFile;
	}

	/**
	 * *********************THREADS*************
	 */	
	
	private void startListen(String path)
	{
		//String DIRECTORY_TO_WATCH = "c:/OOP/WigleWifi_files";
		// get the directory we want to watch, using the Paths singleton class
        Path toWatch = Paths.get(path);
        if(toWatch == null) 
            throw new UnsupportedOperationException("Directory not found");       

        // make a new watch service that we can register interest in
        // directories and files with.
        WatchService myWatcher = null;
		try {
			myWatcher = toWatch.getFileSystem().newWatchService();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        // start the file watcher thread below
        MyWatchQueueReader fileWatcher = new MyWatchQueueReader(myWatcher);
        Thread th = new Thread(fileWatcher, "FileWatcher");
        th.start();
        
        // register a file
        try {
			toWatch.register(myWatcher, ENTRY_CREATE, ENTRY_MODIFY,ENTRY_DELETE);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        /*try {
			th.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
	}

}
