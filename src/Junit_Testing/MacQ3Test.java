package Junit_Testing;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Filter.MacQ3;
import Objects.Row;
import Read_Write.ReadAndWriteCSV;

public class MacQ3Test {

	@Test
	/**
	 * This test read csv file we created.
	 * the test operate the function MacQ3 and the run on all the list,
	 * and check if every mac appear only one time.
	 */

	public void testMac() {
		ReadAndWriteCSV read = new ReadAndWriteCSV();
		List<Row> listInput = new ArrayList<Row>();  //input csv

		listInput = read.ReadFileIntoList3("C:/Users/Gilad Fuchs/workspace/Ex0/output2.csv");
		MacQ3 M=new MacQ3();
		M.Mac(listInput);
		//listInput.get(0).getElement().get(0).setMac(listInput.get(1).getElement().get(1).getMac());
		boolean check=true;
		for (int i = 0; i < listInput.size(); i++) {
			for (int j = 0; j < listInput.get(i).getElement().size(); j++) {
				for (int i2 = i; i2 < listInput.size(); i2++) {
					int j2;
					if(i2==i)
						j2 = j+1;
					else
						j2 = 0;
					while ( j2 < listInput.get(i2).getElement().size()) {
						if(listInput.get(i2).getElement().get(j2).getMac().equals(listInput.get(i).getElement().get(j).getMac())){
							check=false;
							
						}
						j2++;
					}}}}
		assertEquals(true, check);
	}

}
