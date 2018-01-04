package Algorithms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Objects.Row;
import Read_Write.ReadAndWriteCSV;

public class Algo2mac {
	public static void main(String[] args) 
	{
		String src1 = "C:/OOP/Algorithm/_comb_no_gps_ts2.csv";
		String src2 = "C:/OOP/Algorithm/_comb_all_BM3_.csv"; 
		String des = "C:/OOP/Algorithm/Algo2_BM3_TS2_4.csv";
		int imagine = 3;
		//alg2(src1,src2,des,imagine);
	}
	/**
	 * 
	 * This Function compare each row in listInput to all the rows in list comb.
	 * Find the  five rows most similar by the requirement of imagine.
	 * Then send them to the class PiAlgo2 to calculate and to insert to the row the missing data.
	 * Final it's export this to csv file 
	 * @param des 
	 * @param src2 
	 * @param src1 
	 * @param listInput  file with ???? in Lat,Lon,Alt
	 * @param listcomb   file we create
	 * @param imagine  how many similar row we want to find
	 */
	public static void alg2(String src1, String src2, String des, int imagine,Row line)
	{
		ReadAndWriteCSV a=new ReadAndWriteCSV();
		List<Row> listInput = new ArrayList<Row>();	
		List<Row> listcomb = new ArrayList<Row>();
		
		
		double []arr=new double[listcomb.size()];
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
			P.pi(best,i,listInput,listcomb,imagine);
			System.out.println(Arrays.toString(best));
		
		/**
		 * Export the data
		 */
		a.WriteListIntoFile(listInput,des);	
	}


}
