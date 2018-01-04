package Algorithms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Objects.Row;
import Read_Write.ReadAndWriteCSV;

public class Algo2mac {

	/**
	 * 
	 * This Function compare each row in listInput to all the rows in list comb.
	 * Find the  five rows most similar by the requirement of imagine.
	 * Then send them to the class PiAlgo2 to calculate and to insert to the row the missing data.

	 * @param listInput  file with ???? in Lat,Lon,Alt
	 * @param listcomb   file we create
	 * @param imagine  how many similar row we want to find
	 * @return 
	 */
	public static Row alg2(List<Row> listInput,Row line,  int imagine)
	{
		
		
		
		double []arr=new double[listInput.size()];
		double x=1;
		for (int i = 0; i < listInput.size(); i++) 
		{
			for (int j = 0; j < line.getElement().size(); j++) 
			{
					/**
					 * Calculate how much this row imagine
					 */
					if(j<listInput.get(i).getElement().size())
					{
						x*=((Math.abs(Double.parseDouble(line.getElement().get(j).getSignal()))
								+Double.parseDouble(line.getElement().get(j).getSignal())
								-Double.parseDouble(listInput.get(i).getElement().get(j).getSignal()))
								/Math.abs(Double.parseDouble(line.getElement().get(j).getSignal())));
					}
					else
						x*=0.1;
				}
				arr[i]=x;
				x=1;
			}
		imagine=Math.min(imagine, listInput.size());
			int[] best=new int[imagine];
			FindFive F=new FindFive();
			/**
			 * Find the five rows most similar 
			 */
			best=F.find5best(arr,imagine);
			PiAlgo2 P=new PiAlgo2();
			/**
			 * Make the calculate in Pi
			 */
			P.pi(best,listInput,line,imagine);
			System.out.println(Arrays.toString(best));
		
		/**
		 * Export the data
		 */
			return line;
		
	}


}
