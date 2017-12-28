package Filter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Objects.Row;
import Read_Write.CopyListToList;
import Read_Write.ReadAndWriteCSV;

public class FilterAnd implements FilterInterFace {
	/**
	 * @param listInput
	 * @param listOutput
	 * @param desPath2 
	 * @return listOutput
	 * @throws ParseException
	 *This function filter the data by the user requirement
	 */
	public List<Row> filter(List<Row> listInput,List<Row> listOutput) throws ParseException 
	{
		Scanner reader = new Scanner(System.in);
		int filter=0;
		boolean firstTime=true;
		CopyListToList copy=new CopyListToList();
		
		while(true)
		{
			System.out.println("Filter by: "); 
			System.out.println("Time press 1 , ID press 2 , Location press 3 , Exit press -1");	

			while (true){
				/**
				 * Ask the user about the method he want to filter
				 */
				try {
					filter = reader.nextInt();
					break;
				} catch (InputMismatchException e) {					
					System.out.println("please enter a number");
					System.out.println(); 
					System.out.println("Filter by: "); 
					System.out.println("Time press 1 , ID press 2 , Location press 3 , Exit press -1");	
					reader.next();
				}
				System.out.println();
			}

			if(filter == -1)
			{
				/**
				 *Break the while loop 
				 */
				System.out.println("exit loop");
				if(firstTime)
					listOutput = copy.CopyListToList1(listInput);				
				break;
			}
			if(!firstTime) 
			{
				/**
				 * if its not first time, input became the output and output became new list
				 */
				if(!listInput.equals(listOutput)) 
					listInput = copy.CopyListToList1(listOutput);
				listOutput = new ArrayList<Row>();	
			}	
			switch (filter)  
			{
			case 1: {
				/**
				 * In this case the user need to write the part of time he wants
				 * @param StartDate 
				 * @param EndDate
				 */
				System.out.println("Enter the start time   (example:  28/10/2017  20:11:00)");
				String StartDate = reader.next();
				StartDate += reader.nextLine();
				System.out.println("Enter the end time     (example:  28/10/2017  20:32:00)");
				String EndDate = reader.next();	
				EndDate += reader.nextLine();	
				
				/**
				 * send the list and the data the user wrote and set it up by CalculateByTime class
				 */
				listOutput = CalculateByTime1(listInput,listOutput,StartDate,EndDate);
				break;
			}
			case 2: {
				/**
				 * In this case the user need to write the name of id he wants
				 * @param ID 
				 */
				System.out.println("Enter the ID");
				String ID = reader.next();					
				ID += reader.nextLine();
			
				/**
				 * send the list and the data the user wrote and set it up by CalculateByID
				 */
				listOutput =CalculateByID1(listInput,listOutput,ID);
				break;
			}
			case 3: {
				double lon,lat,radius;
				/**
				 * In this case the user need to write the coordinate he wants and the radius
				 *  @param lon
				 *   @param lat
				 *    @param radius
				 */
				System.out.println("Enter the lon");
				while (true){
					try {							
						lon = reader.nextDouble();
						break;
					} catch (InputMismatchException e) {
						System.out.println("error ! please enter a number");
						reader.next();
					}						
				}
				System.out.println("Enter the lat");
				while (true){
					try {							
						lat = reader.nextDouble();
						break;
					} catch (InputMismatchException e) {
						System.out.println("error ! please enter a number");
						reader.next();
					}						
				}	
				System.out.println("Enter the radius");
				while (true){
					try {							
						radius = reader.nextDouble();
						break;
					} catch (InputMismatchException e) {
						System.out.println("error ! please enter a number");
						reader.next();
					}						
				}									
				
				/**
				 * send the list and the data the user wrote and set it up by CalculateByLocation
				 */
				listOutput = CalculateByLocation1(listInput,listOutput,lon,lat,radius);
				break;
			}
			default:
				System.out.println("please enter valid number");
			}					
			firstTime = false;									
			System.out.println();
		}				
		reader.close();		
		
		return listOutput;	
	}
	/**
	 * This function finds all the Mac's that equal to id
	 * @param listInput
	 * @param listOutput
	 * @param id
	 * @return listOutput
	 */
	public List<Row> CalculateByID1(List<Row> listInput,List<Row> listOutput,String id)
	{
		boolean find = true;
		for(int i=1;i<listInput.size();i++)
		{
			/**
			 * check each row from listInput if equal to id
			 * if yes, add row to listOutput
			 */
			if(id.equals(listInput.get(i).getHead().getID())) //take the only row with the same id
			{		
				Row row = new Row(listInput.get(i).getElement(),listInput.get(i).getHead());
				listOutput.add(row);
				find = false;
			}
		}
		if(find){
			System.out.println("The filter didnt find this ID");
			return listInput;	
		}
		return listOutput;				
	}
	/**
	 * This function finds all the Mac's that close geographic to Lon,Lat and Radios
	 * lon = x, lat = y
	 * @param listInput
	 * @param listOutput
	 * @param Lon
	 * @param Lat
	 * @param Radius
	 * @return listOutput
	 */
	public  List<Row> CalculateByLocation1(List<Row> listInput, List<Row> listOutput,  double Lon ,double Lat,double Radius) 
	{	
		boolean find = true;
		for(int i=1;i<listInput.size();i++)
		{
			/**
			 * Convert String to Double
			 * Create distance by equation "Sqrt((x1-x2)^2 + (y1-y2)^2)"
			 * if distance is less than Radius, add row to listOutput
			 */
			double currentLat = Double.parseDouble(listInput.get(i).getHead().getLat());  
			double currentLon = Double.parseDouble(listInput.get(i).getHead().getLon());			
			double distance = Math.sqrt(Math.pow(Lon - currentLon,2) + Math.pow(Lat - currentLat,2)); 
			if( distance <=  Radius)
			{				
				Row row = new Row(listInput.get(i).getElement(),listInput.get(i).getHead());
				listOutput.add(row);
				find = false;
			}			
		}	
		if(find){
			System.out.println("The filter didnt find this Location");
			return listInput;	
		}
		return listOutput;		
	}
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
