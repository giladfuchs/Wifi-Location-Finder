package Junit_Testing;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Filter.CalculateByID;
import Objects.Row;
import Read_Write.ReadAndWriteCSV;

public class CalculateByIDTest {

	@Test
	public void test() {
		/**
		 * check if it's insert id only by the Id request
		 */
		ReadAndWriteCSV read = new ReadAndWriteCSV();
		/**
		 * input csv
		 */
		List<Row> listInput = new ArrayList<Row>();  
		listInput = read.ReadFileIntoList3("C:/Users/Gilad Fuchs/workspace/Ex0/output2.csv");
		CalculateByID id=new CalculateByID();
		List<Row> listOutput = new ArrayList<Row>();
		/**
		 * Insert manually the Id you want
		 */
		String ID="ONEPLUS A3003";
		/**
		 * call the function and filter it
		 */
		listOutput =id.CalculateByID1(listInput,listOutput,ID);
		/**
		 * if you want to put some other id after the filter
		 */
		//listOutput.get(2).getHead().setID("sdsd");
		int i=0;
		boolean flag=true;
		while(i<listOutput.size()&&flag){
			if(!listOutput.get(i).getHead().getID().equals(ID))
				flag=false;
			i++;
		}
		
	
		assertEquals(true,flag);
		
		
	}

}
