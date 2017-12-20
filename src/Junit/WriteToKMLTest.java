package Junit;
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Read_Write.WriteToKML;
import object.Details;
import object.Row;
import object.Wifi;


public class WriteToKMLTest {
/**
 * This test create a list with one row object.
 * then send it to createKMLFile function and check if it create a kml file
 */
	@Test
	public void testcreate() {
		List<Row> listInput = new ArrayList<Row>();	
			List<Wifi> element = new ArrayList<Wifi>();	
		Wifi insert=new Wifi("","aa","","");
		element.add(insert);		
		Details general=new Details("2017-10-27 16:35:58","34.80987434","32.16767714","","","");
		Row csv = new Row(element,general);
		listInput.add(csv);
		WriteToKML kml=new WriteToKML();
		kml. createKMLFile(listInput);
		File k = new File("final.kml");
		boolean check=false;
		if(k.isFile()){
			check=true;
		
		}
		assertEquals(true, check);
	}
	}

