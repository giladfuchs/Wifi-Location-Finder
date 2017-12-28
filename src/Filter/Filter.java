package Filter;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Objects.Row;
import Read_Write.CopyListToList;
import Read_Write.ReadAndWriteCSV;

public class Filter {
	public List<Row> filter(List<Row> listInput,List<Row> listOutput) throws ParseException 
	{
		Scanner reader = new Scanner(System.in);
		int filter=0;
		boolean firstTime=true;
		CopyListToList copy=new CopyListToList();
		FilterAnd And=new FilterAnd();
		FilterNot Not=new FilterNot();
		FilterOr Or=new FilterOr();
		while(true)
		{
			System.out.println("Filter by: "); 
			System.out.println("or 1 , not 2 , and 3 , Exit press -1");	

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
					System.out.println("or 1 , not 2 , and 3 , Exit press -1");	
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
				listOutput=Or.filter(listInput,listOutput);
				
			}
			case 2: {
				listOutput=Not.filter(listInput,listOutput);
			}
			case 3: {
				listOutput=And.filter(listInput,listOutput);
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
	}

