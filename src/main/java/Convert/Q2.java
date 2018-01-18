package main.java.Convert;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import main.java.Objects.Row;
import main.java.Objects.Spot;
import main.java.Read_Write.ReadAndWriteCSV;
public class Q2 
{
	/**
	 * This function read from directory all the correct files 
	 * @param dirPath
	 * @param destination
	 * @return false if directory is empty, else true
	 */
	public List<Row> ReadDir(String dirPath) // read files from directory
	{		
		File folder = new File(dirPath);
		File[] listOfFiles = folder.listFiles();

		List<Spot> listInput = new ArrayList<Spot>();  //input csv
		List<Row> listOutput = new ArrayList<Row>();   //output csv	
		
		System.out.println("dirPath = "+dirPath);
		if(dirPath.equals(""))
			return null;
		
		ReadAndWriteCSV read = new ReadAndWriteCSV();
		//ReadAndWriteCSV write = new ReadAndWriteCSV();
		CalculateQ2 C = new CalculateQ2();
		/**
		 * scan all the files in directory 
		 * each correct file transfer to Calculate function
		 * after scan all files, return list
		 */
		for (File file : listOfFiles) 
		{
			if (file.isFile() && file.getName().endsWith("csv"))
			{
				listInput = read.ReadFileIntoList2(file.getPath()); // read file into List				
				if(!(listInput.isEmpty()))
					C.Calculate(listInput,listOutput);          // creates the output from the input
			}
		}	
		if(listOutput.isEmpty())
			return null;
		else			
			return listOutput;		
	}
	
	

}