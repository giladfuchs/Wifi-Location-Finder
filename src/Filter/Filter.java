package Filter;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Objects.Row;
import Read_Write.CopyListToList;
import Read_Write.ReadAndWriteCSV;

public class Filter {
	public List<Row> filtermain(List<Row> listInput,List<Row> listOutput, String desPathAfterFilterCSV, String desPathAfterFilterKML) throws ParseException 
	{
		
	
		int filter=0;
	
		boolean firstTime=true;
		CopyListToList copy=new CopyListToList();
		FilterAnd And=new FilterAnd();
		FilterNot Not=new FilterNot();
		FilterOr Or=new FilterOr();
		while(true)
		{
			filter++;
			System.out.println("Filter by: "); 
			
			System.out.println("or 1 , not 2 , and 3 , Exit press -1");	

	
			if(filter == 2)
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
			case 2: {
				listOutput=Or.filter(listInput,listOutput);
				break;
			}
			case 1: {
				listOutput=Not.filter(listInput,listOutput);
				break;
			}
			case 3: {
				listOutput=And.filter(listInput,listOutput);
				break;
				}									
			default:
				System.out.println("please enter valid number");
				break;
			}
			
							
			firstTime = false;									
			System.out.println();
		}			


		return listOutput;	
	}

}
