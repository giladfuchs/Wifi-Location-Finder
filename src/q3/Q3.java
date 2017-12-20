package q3;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import Read_Write.ReadAndWriteCSV;
import Read_Write.WriteToKML;
import object.Row;

public class Q3 {


	public void ReadFile(String srcPath) throws ParseException
	{
		/**
		 * This function get a path with the file we created in q2.
		 * then it's upload it to list by the function ReadFileIntoList3
		 *  and then send it to filter,and after create a kml file
		 */
		File file = new File(srcPath);		
		ReadAndWriteCSV read = new ReadAndWriteCSV();

		List<Row> listInput = new ArrayList<Row>();  
		listInput = read.ReadFileIntoList3(file.getPath());
		List<Row> listOutput = new ArrayList<Row>();  	

		if(!listInput.isEmpty()){
			System.out.println("-------");
			Filter f = new Filter();
			listOutput = f.filter(listInput,listOutput);	
		}
		/**
		 * write the kml file		
		 */
		WriteToKML kml=new WriteToKML();
		kml. createKMLFile(listOutput);
	}


}
