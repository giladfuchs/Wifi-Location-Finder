package q2;
import java.util.ArrayList;
import java.util.List;

import object.Details;
import object.Row;
import object.Spot;
import object.Wifi;

public class CalculateQ2 {
	/**
	 * This function Organize all the the mac's with the same Date and Location
	 * @param listInput
	 * @param output
	 */

	public void Calculate(List<Spot> listInput,List<Row> output)
	{		
		findMinSignal f=new findMinSignal();
		List<Wifi> element = new ArrayList<Wifi>();
		/**
		 * Initiate objects 
		 */
		String locat = listInput.get(0).getLat()+listInput.get(0).getLon()+listInput.get(0).getAlt(); // unique geographic location
		String Time = listInput.get(0).getTime();
		
		int countWifi=0,j=0;  
		String count=""+countWifi;		
		
		for(int i=0;i<listInput.size();i++)
		{	
			/**
			 * Ignore type 'GSM', only 'Wifi'
			 */
			if(listInput.get(i).getType().equals("WIFI")) // only wifi
			{			
				String currentLocat =  listInput.get(i).getLat()+listInput.get(i).getLon()+listInput.get(i).getAlt();			
				/**
				 * if Time and Location of previous row equal to Time and Location of current row
				 */
				if(Time.equals(listInput.get(i).getTime()) && locat.equals(currentLocat)) // same date & locat
				{ 
					/**
					 * if the list of wifi of specific MAC is less than 10
					 * 		add the wifi to the list
					 */
					if(countWifi<10)
					{					
						countWifi++;
						Wifi insert=new Wifi(listInput.get(i).getMac(),listInput.get(i).getSSID(),listInput.get(i).getchanal(),listInput.get(i).getSignal());
						element.add(insert);
					}
					else  // only best 10 wifi
					/**
					 * if the list of Wifi of specific MAC is more than 10
					 * 		call to function findMinSignal1 which return the index of the worst signal from the list
					 * 		if the current signal is better than the worst signal
					 * 			replace them 	
					 */
					{        				
						int minSignalIndex = f.findMinSignal1(element);  //get the index with the worst signal   	      										
						if(Integer.parseInt(listInput.get(i).getSignal()) > Integer.parseInt(element.get(minSignalIndex).getSignal()))
						{
							element.remove(minSignalIndex);
							Wifi insert=new Wifi(listInput.get(i).getMac(),listInput.get(i).getSSID(),listInput.get(i).getchanal(),listInput.get(i).getSignal());
							element.add(insert);      					
						} 
					}
				}
				else
					/**
					 * if Time and Location of previous row different to Time and Location of current row
					 * 		create new row in output with details and wifi's list of the previous row in listInput
					 */
				{					
					List<Wifi> elementTemp = new ArrayList<Wifi>();
					while (!element.isEmpty()) {
						elementTemp.add(element.get(0));
						element.remove(0);
					}
					count=""+countWifi;
					Details general=new Details(listInput.get(j).getTime(),listInput.get(j).getLat(),listInput.get(j).getLon(),
							listInput.get(j).getAlt(),listInput.get(j).getID(),count);//put it in here make the first loop autmatcily					
					Row csv = new Row(elementTemp,general);
					output.add(csv);
					/**
					 * reset variables
					 */
					countWifi = 1; 					
					locat = listInput.get(i).getLat()+listInput.get(i).getLon()+listInput.get(i).getAlt(); // unique geographic location
					Time = listInput.get(i).getTime();
					j=i;
					/**
					 * add the wifi of the current row to the wifi's list
					 */
					Wifi insert=new Wifi(listInput.get(i).getMac(),listInput.get(i).getSSID(),listInput.get(i).getchanal(),listInput.get(i).getSignal());
					element.add(insert);
				}
			}
		}
		/**
		 * for the last row in output add details and wifi's list
		 */
		count=""+countWifi; 
		Details general=new Details(listInput.get(j).getTime(),listInput.get(j).getLat(),listInput.get(j).getLon(),
				listInput.get(j).getAlt(),listInput.get(j).getID(),count);//put it in here insert the last loop 
		
		Row csv = new Row(element,general);
		output.add(csv);	
	}

}
