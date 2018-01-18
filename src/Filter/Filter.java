package Filter;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import Algorithms.Algo1mac;
import Algorithms.Algo2mac;
import Convert.Q2;
import GUI.OrgSql;
import GUI.gui;
import Objects.Mac;
import Objects.Row;
import Read_Write.ReadAndWriteCSV;
import Read_Write.WriteToKML;

public class Filter {
	/**
	 * This class connect between all the button in the gui to the function it's need to run.
	 * @param countfilter count how many filter we did
	 * @param indexOr save index for Or filter
	 * @param flag check mode for Or filter
	 *
	 */
	private List<List<Row>> DataBase = new ArrayList< List<Row>>(); 
	private static String _ip ;
	private static String _url ;
	private static String _user;
	private static String _password ;
	private static String path;
	private static String table;
	private int countfilter=0;
	private int indexOr=0;
	private boolean flag=true;
	private String change="";
	private static String dirPaththread="";
	public String getDirPaththread() {
		return dirPaththread;
	}
	public void setDirPaththread(String dirPaththread) {
		this.dirPaththread = dirPaththread;
	}
	ReadAndWriteCSV read = new ReadAndWriteCSV();
	WriteToKML writeKML = new WriteToKML();

	/**
	 * This function get a path to read Wiegele Wifi files.
	 * @param dirPath
	 * @return boolean if it's succeed to read Wigele Wifi File
	 * @throws ParseException
	 */
	public boolean readq2(String dirPath) throws ParseException{
		Q2 q2 = new Q2();
		List<Row> first = q2.ReadDir(dirPath);
		if(first == null)
			return false;
		else{
			delete();
			DataBase.add(first);
		}
		dirPaththread =dirPath;
		return true;

	}
	public void setDataBase(List<List<Row>> dataBase) {
		DataBase = dataBase;
	}
	public void setCountfilter(int countfilter) {
		this.countfilter = countfilter;
	}
	public List<List<Row>> getDataBase() {
		return DataBase;
	}
	/**
	 * This function make the calculate to location by the requirement of Algoritem2 and return the Coordinate
	 * @param line
	 * @param imagine
	 * @return
	 */
	public Row algo2(Row line,int imagine){
		Algo2mac m=new Algo2mac();
		return m.alg2(DataBase.get(0), line, imagine);
	}
	/**
	 * This function make the calculate to location by the requirement of Algoritem1 and return the Coordinate
	 * @param mac
	 * @return
	 */
	public Mac mac(String mac){
		Algo1mac m=new Algo1mac();
		return( m.macbase(DataBase.get(0), mac));

	}
	/**
	 * This function write Csv file from the DataBase
	 * @param desPath2
	 */
	public void write(String desPath2){
		read.WriteListIntoFile(DataBase.get(countfilter),desPath2);
	}
	/**
	 * This function write KML file from the DataBase
	 * @param desPath2
	 */
	public void writeKML(String desPath2){
		writeKML.createKMLFile(DataBase.get(countfilter), desPath2);
	}

	/**
	 * This Function delete Filter from the data base
	 */
	public void undo(){
		DataBase.remove(countfilter);
		countfilter--;
		/**
		 * Set index Or about the last filter inn Or
		 */
		if(indexOr>=countfilter){
			int i=gui.listInfo.size()-1;
			while(i>0){
				if(gui.listInfo.get(i).getKind().equals("And"))
					break;
				else
					i--;
			}
			indexOr=i;
			if(indexOr!=countfilter)
				flag=false;
			else
				flag=true;
		}
	}
	public void readsql() throws ParseException{

		delete();
		DataBase.add(OrgSql.ReadSqlData(_ip, _url, _user, _password, path,table));
		gui.amountListsTxt.setText(""+getDataBase().get(getDataBase().size()-1).size());
		gui.amountMACTxt.setText(""+NumOfMac());
	}

	/**
	 * This function upload a file with 46 column  to the DataBase
	 * @param srcPath
	 * @throws ParseException
	 */
	public void read(String srcPath) throws ParseException{
		delete();
		System.out.println(srcPath);
		DataBase.add(read.ReadFileIntoList3(srcPath));

	}
	/**
	 * This function filter how the user ask in the gui and add a new filter to the DataBase
	 * @param andor check what kind of filter we need to use
	 * @param not check if to filter regular or not
	 * @param filterType check what method we want to use Time or Date or Location
	 * @param s1 String with data how to filter
	 * @param s2 String with data how to filter
	 * @param s3 String with data how to filter
	 */
	public void filtermain(boolean andor,boolean not, int filterType, String s1,String s2,String s3) 
	{		
		FilterNot Not=new FilterNot();
		FilterAnd And=new FilterAnd();

		switch (filterType)  
		{
		case 1: {
			/**
			 * Filter by ID
			 */

			if(andor){
				/**
				 * Filter in Or method
				 */
				if(flag)
					indexOr=countfilter;
				/**
				 * Check if filter Not Or regular
				 */
				if(!not)
					DataBase.add(And.CalculateByID1(DataBase.get(indexOr),new ArrayList<Row>(),  s1));
				else
					DataBase.add(Not.CalculateByID1(DataBase.get(indexOr),new ArrayList<Row>(),  s1));
				if(!flag){
					MergeList mer=new MergeList();
					mer.merge(DataBase.get(countfilter), DataBase.get(countfilter+1));
				}
				flag=false;
			}
			else{
				/**
				 * Filter in And method
				 */
				flag=true;
				/**
				 * Check if filter Not Or regular
				 */
				if(!not)
					this.DataBase.add(And.CalculateByID1(DataBase.get(countfilter),new ArrayList<Row>(),  s1));
				else
					this.DataBase.add(Not.CalculateByID1(DataBase.get(countfilter),new ArrayList<Row>(),  s1));
			}

			break;
		}
		case 2: {
			/**
			 * Filter by Time
			 */

			if(andor){
				/**
				 * Filter in Or method
				 */
				if(flag)
					indexOr=countfilter;
				/**
				 * Check if filter Not Or regular
				 */
				if(!not)
					DataBase.add(And.CalculateByTime1(DataBase.get(indexOr),new ArrayList<Row>(), s1,s2));
				else
					DataBase.add(Not.CalculateByTime1(DataBase.get(indexOr),new ArrayList<Row>(),s1,s2));
				if(!flag){
					MergeList mer=new MergeList();
					mer.merge(DataBase.get(countfilter), DataBase.get(countfilter+1));
				}
				flag=false;
			}
			else{
				/**
				 * Filter in And method
				 */
				flag=true;
				/**
				 * Check if filter Not Or regular
				 */
				if(!not)
					DataBase.add(And.CalculateByTime1(DataBase.get(countfilter),new ArrayList<Row>(), s1,s2));
				else
					DataBase.add(Not.CalculateByTime1(DataBase.get(countfilter),new ArrayList<Row>(), s1,s2));
			}

			break;

		}
		case 3: {
			if(andor){
				/**
				 * Filter in Or method
				 */
				if(flag)
					indexOr=countfilter;
				/**
				 * Check if filter Not Or regular
				 */
				if(!not)
					DataBase.add(And.CalculateByLocation1(DataBase.get(indexOr),new ArrayList<Row>(), Double.parseDouble(s1),Double.parseDouble(s2),Double.parseDouble(s3)));
				else
					DataBase.add(Not.CalculateByLocation1(DataBase.get(indexOr),new ArrayList<Row>(),Double.parseDouble(s1),Double.parseDouble(s2),Double.parseDouble(s3)));
				if(!flag){
					MergeList mer=new MergeList();
					mer.merge(DataBase.get(countfilter), DataBase.get(countfilter+1));
				}
				flag=false;
			}
			else{
				/**
				 * Filter in And method
				 */
				flag=true;
				/**
				 * Check if filter Not Or regular
				 */
				if(!not)
					DataBase.add(And.CalculateByLocation1(DataBase.get(countfilter),new ArrayList<Row>(),Double.parseDouble(s1),Double.parseDouble(s2),Double.parseDouble(s3)));
				else
					DataBase.add(Not.CalculateByLocation1(DataBase.get(countfilter),new ArrayList<Row>(),Double.parseDouble(s1),Double.parseDouble(s2),Double.parseDouble(s3)));
			}
			break;
		}
		}
		/**
		 * Check if the filter return empty list 
		 */

		if(DataBase.get(DataBase.size()-1).get(0).getHead().getAlt().equals("no_change" ) ){
			DataBase.get(0).get(0).getHead().setAlt(change);
			/**
			 * if it's empty it erase it and not count it as a filter
			 */
			System.out.println("fdfdsssss");
			DataBase.remove(DataBase.size()-1);
		}
		else
			countfilter++;



	}
	/**
	 * how many filter we did
	 * @return countfilter
	 */
	public int getCountfilter() {
		return countfilter;
	}
	/**
	 * Counting The Number Of mac
	 * @return mac
	 */
	public int NumOfMac(){
		int mac=0;

		for (int i = 0; i < DataBase.get(countfilter).size(); i++) 
			mac+=Integer.parseInt( DataBase.get(countfilter).get(i).getHead().getCount());

		return mac;

	}

	public void thread() {

		setCountfilter(0);
		if(gui.listen){
			try {
				boolean b=readq2(getDirPaththread());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			try {
				readsql();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if(DataBase.size()>0){
			for (int i = 0; i < gui.listInfo.size(); i++) {
				String s1=gui.listInfo.get(i).getS1();
				String s2=gui.listInfo.get(i).getS2();
				String s3=gui.listInfo.get(i).getS3();
				boolean andor=true;
				if(gui.listInfo.get(i).getKind().equals("And"))
					andor=false;
				boolean not=false;
				if(gui.listInfo.get(i).getType().equals("Not"))
					not=true;
				int filterType = 3;
				if(gui.listInfo.get(i).getMode().equals("Name"))
					filterType = 1;
				else if(gui.listInfo.get(i).getMode().equals("Date"))
					filterType = 2;
				//
				filtermain(andor, not, filterType, s1, s2, s3);
				if(countfilter!=i+1)
				{
					System.out.println("count    "+countfilter+"  i  "+i);
					gui.listInfo.remove(i);
					i--;
				}
			}
			System.out.println("list infovv    "+gui.listInfo .size());
		}
		else
			gui.listInfo.clear();
		
		gui.amountListsTxt.setText(""+getDataBase().get(getDataBase().size()-1).size());
		gui.amountMACTxt.setText(""+NumOfMac());
	
	}
	public void delete() {
		DataBase.clear();
		setCountfilter(0);
		indexOr=0;
		flag=true;
		/**
		 * Empty the informtionList how we filterd
		 */
		if(!gui.listen && !gui.sqlthred)
		gui.listInfo.clear();
		gui.informationTxt.setText(gui.listInfo.toString());
		// TODO Auto-generated method stub

	}
	public static String get_ip() {
		return _ip;
	}
	public static void set_ip(String _ip) {
		Filter._ip = _ip;
	}
	public static String get_url() {
		return _url;
	}
	public static void set_url(String _url) {
		Filter._url = _url;
	}
	public static String get_user() {
		return _user;
	}
	public static void set_user(String _user) {
		Filter._user = _user;
	}
	public static String get_password() {
		return _password;
	}
	public static void set_password(String _password) {
		Filter._password = _password;
	}
	public static String getPath() {
		return path;
	}
	public static void setPath(String path) {
		Filter.path = path;
	}
	public static String getTable() {
		return table;
	}
	public static void setTable(String table) {
		Filter.table = table;
	}
	public String getChange() {
		return change;
	}
	public void setChange(String change) {
		this.change = change;
	}
}
