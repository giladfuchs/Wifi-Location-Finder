package test.java.Junit_Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.Algorithms.FindFive;

public class FindFiveTest {

	@Test
	public void test() {

	
		double [] arr=new double[100];
		for (int i = 0; i < arr.length; i++) {
			arr[i]=Math.random();	
		}
		FindFive f=new FindFive();
		int []best=new int[4];
		best=f.find5best(arr, 4);
		boolean check=true;
		int count=0;

		for (int i = 0; i < arr.length; i++) {
			if(arr[i]>arr[best[0]])
				count++;
		}
		if(count>3)
			check=false;
		assertEquals(true, check);
	}

}


