package Algorithms;
import java.util.ArrayList;
import java.util.List;
import Objects.Mac;
import Objects.Row;

public class Algo1mac {


	/** 
	 * This function read a Csv file in our Format and create List of the object Listmac.
	 * Then for every different mac it's create an object and insert it to the list.
	 * then it's send the new List to calculate w-weight by the class Algo1MacW.	
	 * @param src
	 * @param dest 
	 */
	public static void macbase(List<Row> listInput, String MacStr){		

		List<Mac> output = new ArrayList<Mac>();

		for (int i = 0; i < listInput.size(); i++) 
		{
			for (int j = 0; j < listInput.get(i).getElement().size(); j++) 
			{
				/**
				 * Comparing Mac
				 */
				if(listInput.get(i).getElement().get(j).getMac().equals(MacStr)){
					/**
					 * Insert the mac to the list.
					 */
					Mac n=new Mac(Double.parseDouble(listInput.get(i).getHead().getLat()),Double.parseDouble(listInput.get(i).getHead().getLon()),
							Double.parseDouble(listInput.get(i).getHead().getAlt()),Double.parseDouble(listInput.get(i).getElement().get(j).getSignal()));
					output.add(n);

				}

			}
		}
		/**
		 * Export to calculate the exact location.
		 */


		Algo1MacW W=new Algo1MacW();
		W.MacW(output);

	}
}