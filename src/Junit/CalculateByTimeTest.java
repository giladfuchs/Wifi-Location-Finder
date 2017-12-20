package Junit;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Read_Write.ReadAndWriteCSV;
import object.Details;
import object.Row;
import object.Wifi;
import q3.CalculateByTime;

public class CalculateByTimeTest {

	@Test
	public void test() {
		/**
		 * This function check if after a radius filter we call the function again and we 
		 * check if the have the same size
		 */
		ReadAndWriteCSV read = new ReadAndWriteCSV();
		List<Row> listInput = new ArrayList<Row>();  
		/**
		 * input csv
		 */

		listInput = read.ReadFileIntoList3("C:/Users/Gilad Fuchs/workspace/Ex0/output3.csv");

		List<Row> listOutput = new ArrayList<Row>(); 
		/**
		 * write the time  you want
		 *Enter the start time   (example:  27-10-2017 16:15:37)
		 */

		String StartDate = "2017-11-09 17:18:38";
		/**
		 * Enter the end time     (example:  27-10-2017 16:15:37)
		 */
		String EndDate ="2017-11-09 17:18:38";	

		CalculateByTime time=new CalculateByTime();
		/**
		 * send the list and the data the user wrote and set it up
		 */
		listOutput=time.CalculateByTime1(listInput,listOutput,StartDate,EndDate);
		int j=listOutput.size();
		/**
		 * check the size after filter
		 */
		List<Row> temp = new ArrayList<Row>();

		List<Wifi> element = new ArrayList<Wifi>();
		/**
		 * insert first empty line cause the function check from the second line
		 */

		Wifi insert=new Wifi("","","","");
		element.add(insert);
		Details general=new Details("","","","","","");
		Row csv = new Row(element,general);
		listOutput.add(0, csv);
		/**
		 * call the function again
		 */
		temp=time.CalculateByTime1(listOutput,temp,StartDate,EndDate);
		int k=temp.size();
		assertEquals(j,k);
	}

}
