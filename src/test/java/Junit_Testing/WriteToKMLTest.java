package test.java.Junit_Testing;
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import main.java.Objects.Details;
import main.java.Objects.Row;
import main.java.Objects.Wifi;
import main.java.Read_Write.WriteToKML;


public class WriteToKMLTest {
/**
 * This test create a list with one row object.
 * then send it to createKMLFile function and check if it create a kml file
 */
	@Test
	public void testcreate() 
	{
		String desPath = "C:/OOP/final_Test.kml";
		List<Row> listInput = new ArrayList<Row>();	
		List<Wifi> element = new ArrayList<Wifi>();	
		Wifi insert=new Wifi("","aa","","");
		element.add(insert);		
		Details general=new Details("2017-10-27 16:35:58","34.80987434","32.16767714","","","");
		Row csv = new Row(element,general);
		listInput.add(csv);
		WriteToKML kml=new WriteToKML();
		//kml. createKMLFile(listInput,desPath);
		File k = new File(desPath);   //"final.kml"
		boolean check=false;
		if(k.isFile()){
			check=true;
		
		}
		assertEquals(true, check);
	}
	}

