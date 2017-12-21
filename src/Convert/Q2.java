package Convert;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Objects.Row;
import Objects.Spot;
import Read_Write.ReadAndWriteCSV;
public class Q2 
{
	/**
	 * This function read from directory all the correct files 
	 * @param dirPath
	 * @param destination
	 * @return false if directory is empty, else true
	 */
	public boolean ReadDir(String dirPath,String destination) // read files from directory
	{
		File folder = new File(dirPath);
		File[] listOfFiles = folder.listFiles();

		List<Spot> listInput = new ArrayList<Spot>();  //input csv
		List<Row> listOutput = new ArrayList<Row>();   //output csv	
				
		ReadAndWriteCSV read = new ReadAndWriteCSV();
		ReadAndWriteCSV write = new ReadAndWriteCSV();
		CalculateQ2 C = new CalculateQ2();
		/**
		 * scan all the files in directory 
		 * each correct file transfer to Calculate function
		 * after scan all files, write to new file CSV
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
			return false;
		else{
			write.WriteListIntoFile(listOutput,destination);
			return true;
		}

	}
	
	

}