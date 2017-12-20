package ex0;
import java.text.ParseException;

import q2.Q2;
import q3.Q3;

public class Main {

	public static void main(String[] args) throws ParseException {
		/**
		 * Main function
		 * @param dirPath get the path to upload wigelewifi file
		 * @param desPath get the path and name for the file he will create by the requirement of q2
		 * @param srcPath get the path to upload the csv file we create 
		 */
		String dirPath = "C:/Users/Gilad Fuchs/workspace/Ex0";
		String desPath = "C:/Users/Gilad Fuchs/workspace/Ex0/output2.csv";
       
		Q2 q2 = new Q2();          
		/**
		 *  read files from directory and write to file
		 *  @param b get true while the function create output file
		 */
		boolean b = q2.ReadDir(dirPath,desPath);
		if(b){
			String srcPath = "C:/Users/Gilad Fuchs/workspace/Ex0/output2.csv";		
			Q3 q3 = new Q3();
			q3.ReadFile(srcPath);     
		}
		/**
		 *  read file -> filter by time&ID&location -> filter by MAC -> write to kml
		 */
	}
}
