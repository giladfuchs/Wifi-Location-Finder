package Junit_Testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Filter.MergeList;
import Objects.Details;
import Objects.Row;

public class MergeListTest {

	@Test
	public void test() {
		/**
		 * This test create tow same randomly list.
		 * then merge them to one list and check if there a duplicate row
		 */
		List<Row> list1=new ArrayList<Row>();
		List<Row> list2=new ArrayList<Row>();
		int rand=(int)(Math.random()*100)+2;
		for (int i = 0; i < rand; i++) {
			String time=""+Math.random();
			String id=""+Math.random();
			Row r1=new Row(null,new Details(time,"", id, id, time,""));
			Row r2=new Row(null,new Details(time,"", id, id, time,""));
			list1.add(r1);
			list2.add(r2);
			
		}
		MergeList mr=new MergeList();
		mr.merge(list1,list2);
		boolean duplicate=true;
		/**
		 * if you want to get false
		 */
	
		//list2.get(0).getHead().setID(list2.get(1).getHead().getID());
		//list2.get(0).getHead().setTime(list2.get(1).getHead().getTime());
		for (int i = 0; i < list2.size(); i++) {
			for (int j = i+1; j < list2.size(); j++) {
				if(list2.get(i).getHead().getID().equals(list2.get(j).getHead().getID()) && 
						list2.get(i).getHead().getTime().equals(list2.get(j).getHead().getTime()))
					duplicate=false;
			}
		}

		assertEquals(true, duplicate);
	}

}
