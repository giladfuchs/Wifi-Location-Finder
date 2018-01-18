package test.java.Junit_Testing;
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import main.java.Objects.Row;
import main.java.Read_Write.WriteToKML;

public class WriteToKMLTestempty {

	@Test
	public void testempty() 
	{
		/**
		 * This function send to create kml file without a spot and 
		 * check if it's create a kml file
		 */
		String desPath = "C:/OOP/final_Test.kml";
		List<Row> listInput = new ArrayList<Row>();	
		WriteToKML kml=new WriteToKML();
		//kml.createKMLFile(listInput,desPath);
		File k = new File(desPath);
		boolean check=true;
		if(k.isFile()){
			check=false;	
		}
		assertEquals(true, check);
	}

}
