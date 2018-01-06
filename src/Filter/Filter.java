package Filter;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Algorithms.Algo1mac;
import Algorithms.Algo2mac;
import Convert.Q2;
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
	private int countfilter=0;
	private int indexOr=0;
	private boolean flag=true;
	private String change="";
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
		else
			DataBase.add(first);
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
	}
	/**
	 * This function upload a file with 46 column  to the DataBase
	 * @param srcPath
	 * @throws ParseException
	 */
	public void read(String srcPath) throws ParseException{

		System.out.println(srcPath);
		DataBase.add(read.ReadFileIntoList3(srcPath));
		change=DataBase.get(0).get(0).getHead().getCount();
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
					DataBase.add(And.CalculateByID1(DataBase.get(countfilter),new ArrayList<Row>(),  s1));
				else
					DataBase.add(Not.CalculateByID1(DataBase.get(countfilter),new ArrayList<Row>(),  s1));
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
		if(DataBase.get(0).get(0).getHead().getCount().equals("no_change" ) ){
			DataBase.get(0).get(0).getHead().setCount(change);
			/**
			 * if it's empty it erase it and not count it as a filter
			 */
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
}
