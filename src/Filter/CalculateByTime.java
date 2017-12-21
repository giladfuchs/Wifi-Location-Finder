package Filter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Objects.Row;


public class CalculateByTime {
	/**
	 * This function finds all the Mac's that in the range of startDate to endDate
	 * @param listInput
	 * @param listOutput
	 * @param startDate
	 * @param endDate
	 * @return listOutput
	 */

	public List<Row> CalculateByTime1(List<Row> listInput, List<Row> listOutput,String startDate,String endDate)
	{
		boolean find = true;
		/**
		 * These are the Date formats we use
		 */
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");	 
		DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		/**
		 * Convert from String to Date by dateFormat
		 */
		Date dateStart,dateEnd;	    
		try {
			dateStart = dateFormat.parse(startDate);  //convert string to Date by dateFormat
			dateEnd = dateFormat.parse(endDate);
		} catch (ParseException e) {
			System.out.println("The date you entered isnt correct");			
			return listInput;	
		} 

		try {
			for(int i=1;i<listInput.size();i++)
			{	
				Date dateCurrent;
				/**
				 * If the format of the Date we import from CSV file is without seconds
				 * 		add to the date ':00' which describes seconds 
				 * Convert according to the DateFormat
				 */
				if(listInput.get(i).getHead().getTime().charAt(2) == '/') // check the date format
				{
					String s = listInput.get(i).getHead().getTime()+":00";
					listInput.get(i).getHead().setTime(s);
					dateCurrent = dateFormat2.parse(listInput.get(i).getHead().getTime());		
				}
				else
					dateCurrent = dateFormat3.parse(listInput.get(i).getHead().getTime());			

				/**
				 * if the date in the range, add row to listOutput
				 */
				if(dateStart.before(dateCurrent) && dateEnd.after(dateCurrent))//take the only row in the range of the time
				{				
					Row row = new Row(listInput.get(i).getElement(),listInput.get(i).getHead());
					listOutput.add(row);
					find = false;
				}				
			}
		}
		catch (ParseException e) {			
			e.printStackTrace();			
		}
		if(find){
			System.out.println("The filter didnt find this Date");
			return listInput;	
		}
		return listOutput;		 		
	}
}
