package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Convert.Q2;
import Filter.Filter;
import Filter.FilterAnd;
import Filter.FilterInterFace;
import Filter.FilterNot;
import Filter.FilterOr;
import Filter.Q3;
import Objects.Row;
import Read_Write.CopyListToList;
import Read_Write.ReadAndWriteCSV;
import Read_Write.WriteToKML;
import javafx.stage.DirectoryChooser;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JDayChooser;
import com.toedter.components.JLocaleChooser;
import com.sun.xml.internal.fastinfoset.sax.Properties;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
	private List<List<Row>> Undo = new ArrayList< List<Row>>(); 
	private List<Row> listOutput = new ArrayList<Row>(); 
	private List<Row> listInput = new ArrayList<Row>(); 	
	private JTextField nameTxt;
	private JTextField LocaionAltTxt;
	private JTextField LocaionLonTxt;
	private JTextField LocaionRadiosTxt;
	private FilterInterFace [] f=new FilterInterFace[5];
	private JComboBox FilterType;
	private String kind;
	private int countfilter=0;
	private boolean flag=true;
	public gui() {		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 1080, 629);
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
				String pathDestFile = "";				
				if (returnNameDir == JFileChooser.APPROVE_OPTION) {					 	
					File f = chooserGetDir.getCurrentDirectory();
					pathGetDir = f.getAbsolutePath();
				}					
				pathGetDir = pathGetDir.replace("\\","/");

				pathDestFile = getFilePath();
				System.out.println("pathGetDir = "+pathGetDir);
				System.out.println("pathDestFile = "+pathDestFile);
				String dirPath = pathGetDir+"/WigleWifi_files";   // לשנות אחרי זה בקיו2
				String desPath = pathDestFile+".csv";		
				Q2 q2 = new Q2();
				boolean b = q2.ReadDir(dirPath,desPath);
				if(!b)
					JOptionPane.showMessageDialog(frame, "The files are empty ! or you didnt choose path !");				
			}
		});
		inputDirBut.setBounds(52, 109, 171, 25);
		frame.getContentPane().add(inputDirBut);
		/**
		 * Button of Get file 
		 */	
		
		JButton inputFileBut = new JButton("Get file");
		inputFileBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{

				JFileChooser  chooserGetDir = new JFileChooser();
				chooserGetDir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnNameDir = chooserGetDir.showOpenDialog(inputDirBut);
				String pathGetDir;
				String pathDestFile;	
				if(returnNameDir == JFileChooser.APPROVE_OPTION) {
					File f = chooserGetDir.getSelectedFile();
					pathGetDir = f.getAbsolutePath();
				}
				/** 
				 * This button reads file from directory path the user choose			
				 */						
				String desPath = getFilePath();
				File f = new File(desPath);
				/**
				 * Check the user choose a CSV file
				 */
				if(desPath == "")
					JOptionPane.showMessageDialog(frame,"You didnt choose a path !");
				else if (!(f.isFile() && f.getName().endsWith("csv")))
					JOptionPane.showMessageDialog(frame,"Wrong file ! choose only CSV !");					
				else{
					Q3 q3 = new Q3();
					try {						 
						listOutput = q3.ReadFile(desPath);

					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
				}

			}
		});
		inputFileBut.setBounds(52, 169, 171, 25);
		frame.getContentPane().add(inputFileBut);
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
				String desPath2 = desPath+".csv";

				ReadAndWriteCSV write = new ReadAndWriteCSV();
				if(desPath == "")
					JOptionPane.showMessageDialog(frame,"You didnt choose a path !");
				else if(listOutput.isEmpty())
					JOptionPane.showMessageDialog(frame,"Data Structure is empty !");
				else
					write.WriteListIntoFile(Undo.get(countfilter),desPath2);					
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

				WriteToKML kml=new WriteToKML();
				if(desPath == "")
					JOptionPane.showMessageDialog(frame,"You didnt choose a path !");
				else if(listOutput.isEmpty())
					JOptionPane.showMessageDialog(frame,"Data Structure is empty !");
				else
					kml.createKMLFile(listOutput,desPath2);
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
				listOutput.clear();
				JOptionPane.showMessageDialog(frame,"Data Structure is now empty !");
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

		/**
		 * **************** FILTER ***************
		 */	

		JLabel filterLbl = new JLabel("Filter");
		filterLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		filterLbl.setBounds(408, 31, 61, 45);
		frame.getContentPane().add(filterLbl);

		/**
		 * Filter by time
		 */

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
		spinnerMax.setBounds(453,202,60,30); 
		spinnerMin.setBounds(337,202,60,30); 
		frame.getContentPane().add(spinnerMin);  		   
		frame.getContentPane().add(spinnerMax);				      

		/**
		 * Filter by name
		 */

		JLabel nameLbl = new JLabel("Name");
		nameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nameLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameLbl.setBounds(398, 276, 57, 36);
		frame.getContentPane().add(nameLbl);

		nameTxt = new JTextField();
		nameTxt.setBounds(353, 312, 144, 22);
		frame.getContentPane().add(nameTxt);
		nameTxt.setColumns(10);


		/**
		 * Filter by location
		 */		
		JLabel locationLbl = new JLabel("Location ");
		locationLbl.setHorizontalAlignment(SwingConstants.CENTER);
		locationLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		locationLbl.setBounds(385, 357, 84, 36);
		frame.getContentPane().add(locationLbl);

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
				if(!flag){
					CopyListToList copy=new CopyListToList();
					Undo.add(copy.CopyListToList1(listInput));
					countfilter++;
					flag=true;
				}
				 kind=(String)FilterType.getSelectedItem();
					FilterAnd fil=new FilterAnd();
					if(kind.equals("ID"));{
						String Id=nameTxt.getText();

						//listOutput=fil.CalculateByLocation1(listOutput, listInput, Lon, Lat, Radius);
						Undo.add(fil.CalculateByID1(Undo.get(countfilter),listOutput,  Id));
						
					}
					 if(kind.equals("Time")){
					
						SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");	
						Date time = (Date)spinnerMin.getValue();
						String formattedDate = format.format(time);
						System.out.println("formattedDate start = "+formattedDate); 
						
						Date dateFromDateChooser = dateChooserMin.getDate();
						String dateString = String.format("%1$td-%1$tm-%1$tY", dateFromDateChooser);
						System.err.println("start date  " + dateString);
						
						String timeStr = dateString+" "+formattedDate;
						System.out.println("timeStr  " + timeStr);
						// Undo.add(fil.CalculateByTime1(Undo.get(countfilter), listOutput, startDate, endDate));
					}
					else if(kind.equals("Location"))
					{
						double Lat = Double.parseDouble(LocaionAltTxt.getText());
						double Lon=Double.parseDouble(LocaionLonTxt.getText());
						double Radius=Double.parseDouble(LocaionRadiosTxt.getText());
						Undo.add(fil.CalculateByLocation1(Undo.get(countfilter), listOutput, Lon, Lat, Radius));

					}
				
				countfilter++;
				
				
				/**
				 * Values of filter time
				 */	
				//dont understood what you tried to do in here
				
				
//				SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");	
//				Date time = (Date)spinnerMin.getValue();
//				String formattedDate = format.format(time);
//				System.out.println("formattedDate start = "+formattedDate);  
//
//				time = (Date)spinnerMax.getValue();
//				formattedDate = format.format(time);
//				System.out.println("formattedDate end = "+formattedDate);  	        		        	
//
//				Date dateFromDateChooser = dateChooserMin.getDate();
//				String dateString = String.format("%1$td-%1$tm-%1$tY", dateFromDateChooser);
//				System.err.println("start date  " + dateString);
//
//				dateFromDateChooser = dateChooserMax.getDate();
//				dateString = String.format("%1$td-%1$tm-%1$tY", dateFromDateChooser);
//				System.err.println("end date  " + dateString);	
//
//				/**
//				 * Values of filter name
//				 */
//				String strName = nameTxt.getText();
//				System.out.println("strName = "+strName);
//
//				/**
//				 * Values of filter location
//				 */
//
//				String strAlt = LocaionAltTxt.getText();
//				System.out.println("strAlt = "+strAlt);
//				String strLon = LocaionLonTxt.getText();
//				System.out.println("strLon = "+strLon);
//				String strRadios = LocaionRadiosTxt.getText();
//				System.out.println("strRadios = "+strRadios);

			}
		});
		AndBut.setBounds(385, 508, 95, 25);
		frame.getContentPane().add(AndBut);

		/**
		 * Button of Or
		 */	

		JButton OrBut = new JButton("Or");
		OrBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(flag){
					flag=false;
				while(listInput.size()>0)
					listInput.remove(0);
					
				}
				
				
				 kind=(String)FilterType.getSelectedItem();
				FilterOr fil=new FilterOr();
				if(kind.equals("ID"));{
					String Id=nameTxt.getText();

					//listOutput=fil.CalculateByLocation1(listOutput, listInput, Lon, Lat, Radius);
					listInput=fil.CalculateByID1(Undo.get(countfilter),listInput,  Id);
					System.out.println(listInput.get(0).getHead().getLat());
				}
				 if(kind.equals("Time")){
				
					//listInput=fil.CalculateByTime1(listInput, listOutput, startDate, endDate);
				}
				else if(kind.equals("Location"))
				{
					double Lat = Double.parseDouble(LocaionAltTxt.getText());
					double Lon=Double.parseDouble(LocaionLonTxt.getText());
					double Radius=Double.parseDouble(LocaionRadiosTxt.getText());
					listInput=fil.CalculateByLocation1(Undo.get(countfilter),listInput, Lon, Lat, Radius);

				}

			}
		});
		OrBut.setBounds(488, 508, 87, 25);
		frame.getContentPane().add(OrBut);

		/**
		 * Button of Not
		 */	
		JButton NotBut = new JButton("Not");
		NotBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 kind=(String)FilterType.getSelectedItem();
				FilterNot fil=new FilterNot();
				if(!flag){
					CopyListToList copy=new CopyListToList();
					Undo.add(copy.CopyListToList1(listInput));
					countfilter++;
					flag=true;
				}
				if(kind.equals("ID"));{
					String Id=nameTxt.getText();
					System.out.println("fsdssd");
					//listOutput=fil.CalculateByLocation1(listOutput, listInput, Lon, Lat, Radius);
					Undo.add(fil.CalculateByID1(Undo.get(countfilter),listOutput,  Id));
					
				}
				 if(kind.equals("Time")){
				System.out.println("fsdssd");
					//listInput=fil.CalculateByTime1(listInput, listOutput, startDate, endDate);
				}
				else if(kind.equals("Location"))
				{
					double Lat = Double.parseDouble(LocaionAltTxt.getText());
					double Lon=Double.parseDouble(LocaionLonTxt.getText());
					double Radius=Double.parseDouble(LocaionRadiosTxt.getText());
					Undo.add(fil.CalculateByLocation1(Undo.get(countfilter), listOutput, Lon, Lat, Radius));

				} 
				 countfilter++;
			}
		});
		NotBut.setBounds(291, 508, 87, 25);
		frame.getContentPane().add(NotBut);

		JLabel dateLbl = new JLabel("Date");
		dateLbl.setHorizontalAlignment(SwingConstants.CENTER);
		dateLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dateLbl.setBounds(398, 89, 57, 36);
		frame.getContentPane().add(dateLbl);

		JButton btnReadFile = new JButton("Read File");
		btnReadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser  fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnVal = fc.showOpenDialog(btnReadFile);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					ReadAndWriteCSV read=new ReadAndWriteCSV();
				
					Undo.add(read.ReadFileIntoList3(fc.getSelectedFile().getAbsolutePath().replace("\\","/")));
					System.out.println(Undo.get(0).get(0).getHead().getAlt());
				}
			}
		});
		btnReadFile.setBounds(577, 260, 97, 25);
		frame.getContentPane().add(btnReadFile);



		String[] options = {"Time", "ID", "Location"};
		FilterType = new JComboBox(options);
		FilterType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println((String)FilterType.getSelectedItem());
			}
		});
		FilterType.setRenderer(new MyComboBoxRenderer("Choose filter"));
		FilterType.setSelectedIndex(-1);
		FilterType.setBounds(577, 206, 95, 22);
		frame.getContentPane().add(FilterType);


	}
	class MyComboBoxRenderer extends JLabel implements ListCellRenderer
	{
		private String _title;

		public MyComboBoxRenderer(String title)
		{
			_title = title;
		}

		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean hasFocus)
		{
			if (index == -1 && value == null) setText(_title);
			else setText(value.toString());
			return this;
		}
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
}
