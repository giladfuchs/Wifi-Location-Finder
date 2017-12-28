package Filter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Objects.Row;
import Read_Write.CopyListToList;

import Read_Write.ReadAndWriteCSV;

public class FilterOr 
{
	/**
	 * @param listInput
	 * @param listOutput
	 * @param desPath2 
	 * @return listOutput
	 * @throws ParseException
	 *This function filter the data by the user requirement
	 */
	public List<Row> filter(List<Row> listInput,List<Row> listOutput, String desPath) throws ParseException 
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
				CalculateByTime time=new CalculateByTime();
				/**
				 * send the list and the data the user wrote and set it up by CalculateByTime class
				 */
				listOutput = time.CalculateByTime1(listInput,listOutput,StartDate,EndDate);
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
				CalculateByID id=new CalculateByID();
				/**
				 * send the list and the data the user wrote and set it up by CalculateByID
				 */
				listOutput =id.CalculateByID1(listInput,listOutput,ID);
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
				CalculateByLocation loc=new CalculateByLocation();
				/**
				 * send the list and the data the user wrote and set it up by CalculateByLocation
				 */
				listOutput = loc.CalculateByLocation1(listInput,listOutput,lon,lat,radius);
				break;
			}
			default:
				System.out.println("please enter valid number");
			}					
			firstTime = false;									
			System.out.println();
		}				
		reader.close();		
		/**
		 * After the user has finished to filter,
		 * The program use the function MacQ3 and then it's return.
		 */
		
		ReadAndWriteCSV write = new ReadAndWriteCSV();
		write.WriteListIntoFile(listOutput,desPath);	
		return listOutput;	
	}
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


}
