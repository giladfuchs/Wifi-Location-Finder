package Main;
import java.text.ParseException;

import Convert.Q2;
import Filter.Q3;

public class Main {

	public static void main(String[] args) throws ParseException {
		/**
		 * Main function
		 * @param dirPath get the path to upload wigelewifi file
		 * @param desPath get the path and name for the file he will create by the requirement of q2
		 * @param srcPath get the path to upload the csv file we create 
		 */
		String dirPath = "C:/OOP/WigleWifi_files";
		String desPath = "C:/OOP/List_of_wifi.csv";		          
		/**
		 *  read files from directory and write to file
		 *  @param b get true while the function create output file
		 */
		Q2 q2 = new Q2();
		boolean b = q2.ReadDir(dirPath,desPath);
		/**
		 *  read file -> filter by time&ID&location -> filter by MAC -> write to kml
		 */
		if(b){
			String filePath = "C:/OOP/List_of_wifi.csv";
			String desPathAfterFilterCSV = "C:/OOP/List_of_wifi_after_filter.csv";
			String desPathAfterFilterKML = "C:/OOP/final.kml";
			Q3 q3 = new Q3();
			q3.ReadFile(filePath);//,desPathAfterFilterCSV,desPathAfterFilterKML);     
		}
		
	}
}
