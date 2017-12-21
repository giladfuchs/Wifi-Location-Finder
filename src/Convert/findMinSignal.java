package Convert;
import java.util.List;

import Objects.Wifi;

public class findMinSignal {
	/**
	 * This function get list with 10 wifi.
	 * The function look for the wifi with the worst signal and return wifi's index.
	 * @param element
	 * @return index
	 */
	public int findMinSignal1(List<Wifi>  element){

		String Signal;
		int Signal2,min = 0,index = 0;

		for(int i=0;i<element.size();i++)
		{
			Signal = element.get(i).getSignal();			
			Signal2 = Integer.parseInt(Signal);
			if(min > Signal2)
			{
				min = Signal2;
				index = i;
			}
		}		
		return index;	
	}

}
