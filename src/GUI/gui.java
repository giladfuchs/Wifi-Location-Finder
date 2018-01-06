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
import Objects.Details;
import Objects.FilterInfo;
import Objects.Mac;
import Objects.Row;
import Objects.Wifi;
import Read_Write.ReadAndWriteCSV;
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
	static List<FilterInfo> listInfo = new ArrayList<FilterInfo>(); 
	private String dirPath; 	
	private JTextField nameTxt;
	private JTextField LocaionAltTxt;
	private JTextField LocaionLonTxt;
	private JTextField LocaionRadiosTxt;
	private Filter filter=new Filter();
	
	
	private boolean DataStructureEmpty=false;
	private String destination = "";
	private ReadAndWriteCSV read = new ReadAndWriteCSV();
	private JRadioButton dateRadioBut;
	private JRadioButton nameRadioBut;
	private JRadioButton locationRadioBut;
	private JCheckBox NotCheckBox;
	private JTextField MACTxt;
	private JTextField algo1LocaionLatTxt;
	private JTextField algo1LocaionLonTxt;
	private JTextField algo1LocaionAltTxt;
	private String wlat;
	private String wlon;
	private String walt;
	private JTextField amountMACTxt;
	private JTextField amountListsTxt;
	private JTextArea informationTxt;
	private JLabel informationLbl;
	private JTextField algo2LocaionLatTxt;
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
	static boolean threadSignal = false;

	public gui() {		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 1700, 629);
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
		JButton inputDirBut = new JButton("Read files from directory");
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
				dirPath = pathGetDir;
				try {
					if(filter.readq2(dirPath) == true)
						DataStructureEmpty = true;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(read.getCountwigele());
				amountListsTxt.setText(""+read.getCountwigele());
				amountMACTxt.setText(""+filter.NumOfMac());
				startListen(dirPath);
			}
		});
		inputDirBut.setBounds(52, 109, 180, 25);
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
							amountMACTxt.setText(""+filter.NumOfMac());
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnReadFile.setBounds(52, 164, 180, 25);
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
				else if (filter.getDataBase().size()<1)
					JOptionPane.showMessageDialog(frame,"Cant save ! Data Structure is empty !");
				else{
					String desPath2 = desPath+".csv";
					filter.write(desPath2);
				}
			}
		});
		saveCSVBut.setBounds(52, 283, 180, 25);
		frame.getContentPane().add(saveCSVBut);
		/**
		 * Button of Save as KML
		 */	
		JButton saveKMLBut = new JButton("Save as KML");
		saveKMLBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println(read.getCountwigele());
				/** 
				 * This button the user needs to write a path+name_file to save it as KML		
				 */						
				String desPath = getFilePath();				
				if(desPath == "")
					JOptionPane.showMessageDialog(frame,"You didnt choose a path !");
				else if (filter.getDataBase().size()<1)
					JOptionPane.showMessageDialog(frame,"Cant save ! Data Structure is empty !");
				else{
					String desPath2 = desPath+".kml";
					filter.writeKML(desPath2);
				}
			}
		});
		saveKMLBut.setBounds(52, 341, 180, 25);
		frame.getContentPane().add(saveKMLBut);

		/**
		 * Button of Delete
		 */	
		JButton deleteBut = new JButton("Delete");
		deleteBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				/**
				 * Empty the DataBase except the file we read
				 */
				while(filter.getDataBase().size()>0)
					filter.getDataBase().remove(0);
				
				filter.setCountfilter(0);
				/**
				 * Empty the informtionList how we filterd
				 */
				while(listInfo.size()>0)
					listInfo.remove(0);
				informationTxt.setText(listInfo.toString());				
				JOptionPane.showMessageDialog(frame,"Data Structure is now empty !");
				DataStructureEmpty = false;
				amountMACTxt.setText("0");
				amountListsTxt.setText("0");
			}
		});
		deleteBut.setBounds(52, 223, 180, 25);
		frame.getContentPane().add(deleteBut);

		

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
							//listInfo.add(new FilterInfo("And",not, "Name", strName,"","",filter.getCountfilter()));
							amountMACTxt.setText(""+filter.NumOfMac());
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
							listInfo.add(new FilterInfo("And",not, "Date", time,filter.getCountfilter()));
							amountMACTxt.setText(""+filter.NumOfMac());
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
							listInfo.add(new FilterInfo("And",not, "Location", location,filter.getCountfilter()));
							amountMACTxt.setText(""+filter.NumOfMac());
							flagAnd = true;
						}					
					}
					else
						JOptionPane.showMessageDialog(frame,"You didnt choose a type of filter !");
				}
				if(flagAnd == true && n+1!=filter.getCountfilter()){
					listInfo.remove(listInfo.size()-1);
					JOptionPane.showMessageDialog(frame,"The filter didnt find this value !");
				}
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
						listInfo.add(new FilterInfo("Or",not, "Name", strName,filter.getCountfilter()));
						flagOr = true;
						amountMACTxt.setText(""+filter.NumOfMac());
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
						listInfo.add(new FilterInfo("Or",not, "Date", time,filter.getCountfilter()));
						flagOr = true;
						amountMACTxt.setText(""+filter.NumOfMac());
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
						listInfo.add(new FilterInfo("Or",not, "location", location,filter.getCountfilter()));
						flagOr = true;
						amountMACTxt.setText(""+filter.NumOfMac());
					}
					else
						JOptionPane.showMessageDialog(frame,"You didnt choose a type of filter !");
				}				
				if(flagOr == true && n+1!=filter.getCountfilter()){
					listInfo.remove(listInfo.size()-1);
				JOptionPane.showMessageDialog(frame,"The filter didnt find this value !");
				}
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
					amountMACTxt.setText(""+filter.NumOfMac());
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
		algorithmLbl.setBounds(852, 33, 112, 45);
		frame.getContentPane().add(algorithmLbl);

		/**
		 * algorithm 1
		 */
		JLabel algo1Lbl = new JLabel("Algorithm 1");
		algo1Lbl.setHorizontalAlignment(SwingConstants.CENTER);
		algo1Lbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo1Lbl.setBounds(876, 91, 95, 36);
		frame.getContentPane().add(algo1Lbl);

		JLabel algo1MACLbl = new JLabel("MAC");
		algo1MACLbl.setHorizontalAlignment(SwingConstants.CENTER);
		algo1MACLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo1MACLbl.setBounds(1009, 130, 57, 36);
		frame.getContentPane().add(algo1MACLbl);

		MACTxt = new JTextField();
		MACTxt.setColumns(10);
		MACTxt.setBounds(730, 139, 258, 22);
		frame.getContentPane().add(MACTxt);

		JLabel algo1LocationLbl = new JLabel("Location");
		algo1LocationLbl.setHorizontalAlignment(SwingConstants.CENTER);
		algo1LocationLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo1LocationLbl.setBounds(1112, 196, 57, 36);
		frame.getContentPane().add(algo1LocationLbl);

		JLabel algo1AltLbl = new JLabel("Alt");
		algo1AltLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo1AltLbl.setBounds(717, 166, 38, 36);
		frame.getContentPane().add(algo1AltLbl);

		JLabel algo1LonLbl = new JLabel("Lon");
		algo1LonLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo1LonLbl.setBounds(866, 166, 38, 36);
		frame.getContentPane().add(algo1LonLbl);

		JLabel algo1LatLbl = new JLabel("Lat");
		algo1LatLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo1LatLbl.setBounds(1007, 166, 59, 36);
		frame.getContentPane().add(algo1LatLbl);

		algo1LocaionLatTxt = new JTextField();
		algo1LocaionLatTxt.setColumns(10);
		algo1LocaionLatTxt.setBounds(954, 204, 133, 22);
		algo1LocaionLatTxt.setEditable(false);
		frame.getContentPane().add(algo1LocaionLatTxt);

		algo1LocaionLonTxt = new JTextField();
		algo1LocaionLonTxt.setColumns(10);
		algo1LocaionLonTxt.setBounds(816, 204, 133, 22);
		algo1LocaionLonTxt.setEditable(false);
		frame.getContentPane().add(algo1LocaionLonTxt);

		algo1LocaionAltTxt = new JTextField();
		algo1LocaionAltTxt.setColumns(10);
		algo1LocaionAltTxt.setBounds(674, 204, 133, 22);
		algo1LocaionAltTxt.setEditable(false);
		frame.getContentPane().add(algo1LocaionAltTxt);

		/**
		 * Run of algorithm 1
		 */
		JButton algo1RunBut = new JButton("Run");
		algo1RunBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(DataStructureEmpty == false)
					JOptionPane.showMessageDialog(frame,"Data structure is empty !");
				else{
					String mac = MACTxt.getText();
					System.out.println(mac);
					Mac fin= filter.mac(mac);
					walt=""+fin.getAlt();
					wlat=""+fin.getLat();
					wlon=""+fin.getLon();

					algo1LocaionLatTxt.setText(wlat);
					algo1LocaionLonTxt.setText(wlon);
					algo1LocaionAltTxt.setText(walt);
				}
			}
		});
		algo1RunBut.setBounds(777, 98, 87, 25);
		frame.getContentPane().add(algo1RunBut);

		/**
		 *  algorithm 2
		 */

		JLabel algo2Lbl = new JLabel("Algorithm 2");
		algo2Lbl.setHorizontalAlignment(SwingConstants.CENTER);
		algo2Lbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo2Lbl.setBounds(876, 260, 95, 36);
		frame.getContentPane().add(algo2Lbl);

		JLabel algo2LocationLbl = new JLabel("Location");
		algo2LocationLbl.setHorizontalAlignment(SwingConstants.CENTER);
		algo2LocationLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo2LocationLbl.setBounds(1112, 533, 57, 36);
		frame.getContentPane().add(algo2LocationLbl);

		JLabel algo2LatLbl = new JLabel("Lat");
		algo2LatLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo2LatLbl.setBounds(1001, 509, 35, 34);
		frame.getContentPane().add(algo2LatLbl);

		algo2LocaionLatTxt = new JTextField();
		algo2LocaionLatTxt.setEditable(false);
		algo2LocaionLatTxt.setColumns(10);
		algo2LocaionLatTxt.setBounds(954, 541, 133, 22);
		frame.getContentPane().add(algo2LocaionLatTxt);

		algo2LocaionLonTxt = new JTextField();
		algo2LocaionLonTxt.setEditable(false);
		algo2LocaionLonTxt.setColumns(10);
		algo2LocaionLonTxt.setBounds(816, 541, 133, 22);
		frame.getContentPane().add(algo2LocaionLonTxt);

		JLabel algo2LonLbl = new JLabel("Lon");
		algo2LonLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo2LonLbl.setBounds(876, 508, 38, 36);
		frame.getContentPane().add(algo2LonLbl);

		JLabel algo2AltLbl = new JLabel("Alt");
		algo2AltLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo2AltLbl.setBounds(730, 508, 38, 36);
		frame.getContentPane().add(algo2AltLbl);

		algo2LocaionAltTxt = new JTextField();
		algo2LocaionAltTxt.setEditable(false);
		algo2LocaionAltTxt.setColumns(10);
		algo2LocaionAltTxt.setBounds(674, 541, 133, 22);
		frame.getContentPane().add(algo2LocaionAltTxt);

		algo2MAC1Txt = new JTextField();
		algo2MAC1Txt.setColumns(10);
		algo2MAC1Txt.setBounds(844, 406, 158, 22);
		frame.getContentPane().add(algo2MAC1Txt);

		algo2Signal1Txt = new JTextField();
		algo2Signal1Txt.setColumns(10);
		algo2Signal1Txt.setBounds(749, 406, 59, 22);
		frame.getContentPane().add(algo2Signal1Txt);

		algo2MAC2Txt = new JTextField();
		algo2MAC2Txt.setColumns(10);
		algo2MAC2Txt.setBounds(844, 438, 158, 22);
		frame.getContentPane().add(algo2MAC2Txt);

		algo2Signal2Txt = new JTextField();
		algo2Signal2Txt.setColumns(10);
		algo2Signal2Txt.setBounds(749, 438, 59, 22);
		frame.getContentPane().add(algo2Signal2Txt);

		algo2MAC3Txt = new JTextField();
		algo2MAC3Txt.setColumns(10);
		algo2MAC3Txt.setBounds(844, 469, 158, 22);
		frame.getContentPane().add(algo2MAC3Txt);

		algo2Signal3Txt = new JTextField();
		algo2Signal3Txt.setColumns(10);
		algo2Signal3Txt.setBounds(749, 469, 59, 22);
		frame.getContentPane().add(algo2Signal3Txt);				

		JLabel algo2MACLbl = new JLabel("MAC");
		algo2MACLbl.setHorizontalAlignment(SwingConstants.CENTER);
		algo2MACLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo2MACLbl.setBounds(895, 364, 57, 36);
		frame.getContentPane().add(algo2MACLbl);

		JLabel algo2SiganlLbl = new JLabel("Signal");
		algo2SiganlLbl.setHorizontalAlignment(SwingConstants.CENTER);
		algo2SiganlLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo2SiganlLbl.setBounds(746, 364, 57, 36);
		frame.getContentPane().add(algo2SiganlLbl);

		JLabel algo2MAC1Lbl = new JLabel("1");
		algo2MAC1Lbl.setHorizontalAlignment(SwingConstants.CENTER);
		algo2MAC1Lbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo2MAC1Lbl.setBounds(1009, 406, 57, 25);
		frame.getContentPane().add(algo2MAC1Lbl);

		JLabel algo2MAC2Lbl = new JLabel("2");
		algo2MAC2Lbl.setHorizontalAlignment(SwingConstants.CENTER);
		algo2MAC2Lbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo2MAC2Lbl.setBounds(1009, 438, 57, 25);
		frame.getContentPane().add(algo2MAC2Lbl);

		JLabel algo2MAC3Lbl = new JLabel("3");
		algo2MAC3Lbl.setHorizontalAlignment(SwingConstants.CENTER);
		algo2MAC3Lbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		algo2MAC3Lbl.setBounds(1009, 466, 57, 25);
		frame.getContentPane().add(algo2MAC3Lbl);
		algo2NumberRowTxt = new JTextField();
		algo2NumberRowTxt.setColumns(10);
		algo2NumberRowTxt.setBounds(766, 326, 75, 22);
		frame.getContentPane().add(algo2NumberRowTxt);

		algo2NumberRowLbl = new JLabel("Row Number");
		algo2NumberRowLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		algo2NumberRowLbl.setBounds(766, 305, 75, 25);
		frame.getContentPane().add(algo2NumberRowLbl);


		/**
		 * Run of algorithm 2
		 */

		JButton algo2RunBut = new JButton("Run");
		algo2RunBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(DataStructureEmpty == false)
					JOptionPane.showMessageDialog(frame,"Data structure is empty !");
				else{
					if(algo2FirstRadioBut.isSelected())
					{						
						String numberOfRow = algo2NumberRowTxt.getText();
						if(numberOfRow.isEmpty() || destination.equals(""))
							JOptionPane.showMessageDialog(frame,"Please fill the fields !");	
						else{
							try
							{
								Integer.parseInt(numberOfRow);
								int numberOfRowInt = Integer.parseInt(numberOfRow);								
								
								List<Row> algo2List=read.ReadFileIntoList3(destination);

								if(numberOfRowInt < algo2List.size())
								{							
									Row line=new Row(new ArrayList<Wifi>(algo2List.get(numberOfRowInt).getElement()), new Details("", "", "", "", "", ""));
									line=filter.algo2(line, 5);									
									algo2LocaionLatTxt.setText(line.getHead().getLat());
									algo2LocaionLonTxt.setText(line.getHead().getLon());
									algo2LocaionAltTxt.setText(line.getHead().getAlt());
								}
								else
									JOptionPane.showMessageDialog(frame,"The row number you entered is wrong");	
							}
							catch (NumberFormatException ex)
							{
								JOptionPane.showMessageDialog(frame,"You didnt enter a number in row number !");	
							}
						}

					}else if(algo2SecondRadioBut.isSelected())
					{
						List<Wifi> alg2lst=new ArrayList<Wifi>();

						String mac1 = algo2MAC1Txt.getText();
						String mac2 = algo2MAC2Txt.getText();
						String mac3 = algo2MAC3Txt.getText();
						String signal1 = algo2Signal1Txt.getText();
						String signal2 = algo2Signal2Txt.getText();
						String signal3 = algo2Signal3Txt.getText();

						if((mac1.isEmpty() || signal1.isEmpty())&& (mac2.isEmpty() || signal2.isEmpty()) &&(mac3.isEmpty() || signal3.isEmpty()))
							JOptionPane.showMessageDialog(frame,"Please fill all the fields");
						else{
							if(!mac1.isEmpty() && !signal1.isEmpty())
								alg2lst.add(new Wifi(mac1,"","",signal1));
							if(!mac2.isEmpty() && !signal2.isEmpty())
								alg2lst.add(new Wifi(mac2,"","",signal2));
							if(!mac3.isEmpty() && !signal3.isEmpty())
								alg2lst.add(new Wifi(mac3,"","",signal3));

							Row line=new Row(alg2lst, new Details("", "", "", "", "", ""));							
							line=filter.algo2(line, 5);
							algo2LocaionLatTxt.setText(line.getHead().getLat());							
							algo2LocaionLonTxt.setText(line.getHead().getLon());	 
							algo2LocaionAltTxt.setText(line.getHead().getAlt());
						}
					}
					else
						JOptionPane.showMessageDialog(frame,"You didnt choose a type of filter in algorithm 2!");
				}
			}
		});
		algo2RunBut.setBounds(766, 267, 87, 25);
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
		algo2ChooseFileBut.setBounds(852, 326, 150, 25);
		frame.getContentPane().add(algo2ChooseFileBut);

		/**
		 * Radio buttons of algorithm 2
		 */	

		ButtonGroup buttonGroup2 = new ButtonGroup();

		algo2FirstRadioBut = new JRadioButton("1");
		algo2FirstRadioBut.setBounds(1043, 319, 44, 25);
		algo2SecondRadioBut = new JRadioButton("2");
		algo2SecondRadioBut.setBounds(1043, 372, 38, 25);

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
		informationTxt.setBounds(1196, 89, 445, 408);
		frame.getContentPane().add(informationTxt);

		informationLbl = new JLabel("Information");
		informationLbl.setHorizontalAlignment(SwingConstants.CENTER);
		informationLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		informationLbl.setBounds(1385, 31, 112, 45);
		frame.getContentPane().add(informationLbl);

		JLabel amountListsLbl = new JLabel("Amount of lists");
		amountListsLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		amountListsLbl.setBounds(12, 451, 87, 45);
		frame.getContentPane().add(amountListsLbl);

		JLabel amountMACLbl = new JLabel("Amount of MAC");
		amountMACLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		amountMACLbl.setBounds(12, 411, 87, 45);
		frame.getContentPane().add(amountMACLbl);

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
		
		amountMACTxt.setText("0");
		amountListsTxt.setText("0");
		
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
		exitBut.setBounds(1470, 531, 171, 25);
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
			System.out.println("7777777777777777");
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
