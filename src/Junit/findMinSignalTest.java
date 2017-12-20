package Junit;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import object.Wifi;
import q2.findMinSignal;

public class findMinSignalTest {
    /**
     * This test create a list with 10 wifi and give for the last wifi worst signal,
     * then we check if the function really return the last wifi
     */
	@Test
	public void test() {
		findMinSignal f=new findMinSignal();
		
		int k=-10;
		
			List<Wifi> element = new ArrayList<Wifi>();
			for (int j = 0; j < 10; j++) {	
		Wifi insert=new Wifi("fssf","gfdd","gfdd",""+k);
		element.add(insert);
		k-=10;
			}
		int minSignalIndex =f.findMinSignal1(element);
		assertEquals(9, minSignalIndex);
	}

}
