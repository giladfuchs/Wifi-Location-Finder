package Junit;
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Read_Write.WriteToKML;
import object.Row;

public class WriteToKMLTestempty {

	@Test
	public void testempty() {
		/**
		 * This function send to create kml file without a spot and 
		 * check if it's create a kml file
		 */
		List<Row> listInput = new ArrayList<Row>();	
		WriteToKML kml=new WriteToKML();
		kml. createKMLFile(listInput);
		File k = new File("final.kml");
		boolean check=true;
		if(k.isFile()){
			check=false;
		
		}
		assertEquals(true, check);
	}

}
