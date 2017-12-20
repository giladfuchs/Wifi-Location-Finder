package q3;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Read_Write.CopyListToList;
import Read_Write.ReadAndWriteCSV;
import object.Row;

public class Filter {
	/**
	 * 
	 * @param listInput
	 * @param listOutput
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
		MacQ3 M=new MacQ3();
		M.Mac(listOutput);
		ReadAndWriteCSV write = new ReadAndWriteCSV();
		write.WriteListIntoFile(listOutput,"C:/Users/Gilad Fuchs/workspace/Ex0/output3.csv");	
		return listOutput;	
	}


}
