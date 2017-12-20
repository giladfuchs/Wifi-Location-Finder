package q3;
import java.util.List;

import object.Row;

public class MacQ3 {
	public void   Mac(List<Row> listInput){
		/**
		 * @param listInput
		 * this function run like bubble sort it's comparing every wifi and check if the mac equal.
		 * If it's find out the mac is equal it's remove the wifi with the less signal
		 */

		for (int i = 0; i < listInput.size(); i++) {
			for (int j = 0; j < listInput.get(i).getElement().size(); j++) {
				/**
				 * the first and the second loop define the locate of the wifi to compare
				 */
				for (int i2 = i; i2 < listInput.size(); i2++) {
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
					while ( j2 < listInput.get(i2).getElement().size()) {
						/**
						 * compare mac
						 */
						if(listInput.get(i2).getElement().get(j2).getMac().equals(listInput.get(i).getElement().get(j).getMac())){
							/**
							 * compare signal and remove the wifi with the less signal
							 */
							if(Integer.parseInt(listInput.get(i2).getElement().get(j2).getSignal())>
							Integer.parseInt(listInput.get(i).getElement().get(j).getSignal())){
								listInput.get(i).getElement().remove(j);
								/**
								 * update the count and if need it's erase the row
								 */
								if(listInput.get(i).getElement().size()==0){
									listInput.remove(i);
									i--;
								}
								else
									listInput.get(i).getHead().setCount(""+listInput.get(i).getElement().size());
								i2 = listInput.size();
								j--;
								break;
							}
							else
							{
								
								listInput.get(i2).getElement().remove(j2);
								j2--;
								/**
								 * update the count and if need it's erase the row
								 */
								if(listInput.get(i2).getElement().size()==0){
									listInput.remove(i2);
									i2--;
									break;
								}
								else
									listInput.get(i2).getHead().setCount(""+listInput.get(i2).getElement().size());
							}
						}
						j2++;
					}
				}
			}
		}

	}


}
