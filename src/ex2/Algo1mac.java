package ex2;
import java.util.ArrayList;
import java.util.List;

import Read_Write.ReadAndWriteCSV;
import object.Listmac;
import object.Mac;
import object.Row;

public class Algo1mac {

	public static void main(String[] args) {
		String src = "C:/OOP/Algorithm/_comb_all_BM3_.csv";
		String dest = "C:/OOP/Algorithm/Algo1_BM3_4.csv";
		macbase(src,dest);
	}
	/** 
	 * This function read a Csv file in our Format and create List of the object Listmac.
	 * Then for every different mac it's create an object and insert it to the list.
	 * then it's send the new List to calculate w-weight by the class Algo1MacW.	
	 * @param src
	 * @param dest 
	 */
	public static void macbase(String src, String dest){		

		ReadAndWriteCSV a=new ReadAndWriteCSV();
		List<Row> listInput = new ArrayList<Row>();			
		listInput=a.ReadFileIntoList4(src);
		List<Listmac> output = new ArrayList<Listmac>();

		for (int i = 0; i < listInput.size(); i++) 
		{
			for (int j = 0; j < listInput.get(i).getElement().size(); j++) 
			{
				/**
				 * the first and the second loop define the locate of the wifi to compare
				 */
				List<Mac> temp=new ArrayList<Mac>();
				Mac m=new Mac(Double.parseDouble(listInput.get(i).getHead().getLat()),Double.parseDouble(listInput.get(i).getHead().getLon()),
						Double.parseDouble(listInput.get(i).getHead().getAlt()),Double.parseDouble(listInput.get(i).getElement().get(j).getSignal()));
				temp.add(m);
				for (int i2 = i; i2 < listInput.size(); i2++) 
				{
					/**
					 * Define the the another wifi to compare run from the next to the first wifi we define in i and j until the end
					 */
					int j2;
					/**
					 * check if there is room on the same line
					 */
					if(i2==i)
						j2 = j+1;
					else
						j2 = 0;
					while ( j2 < listInput.get(i2).getElement().size()) 
					{
						/**
						 * compare mac
						 */
						if(listInput.get(i2).getElement().get(j2).getMac().equals(listInput.get(i).getElement().get(j).getMac())){
							Mac n=new Mac(Double.parseDouble(listInput.get(i2).getHead().getLat()),Double.parseDouble(listInput.get(i2).getHead().getLon()),
									Double.parseDouble(listInput.get(i2).getHead().getAlt()),Double.parseDouble(listInput.get(i2).getElement().get(j2).getSignal()));
							temp.add(n);
							listInput.get(i2).getElement().remove(j2);
							j2--;
						}
						j2++;
					}
				}
				/**
				 * Insert the mac to the list.
				 */
				Listmac t=new Listmac(temp,listInput.get(i).getHead().getTime(),listInput.get(i).getElement().get(j).getSsid(),listInput.get(i).getElement().get(j).getMac(),
						listInput.get(i).getElement().get(j).getChanal());
				output.add(t);
			}
		}
		Algo1MacW W=new Algo1MacW();
		W.MacW(output,dest);

	}
}