package Junit;
import static org.junit.Assert.*;

import org.junit.Test;

import q2.Q2;

public class ReadAndWriteCSVTestNoWigeleFile {
/**
 * This test get a path.
 * Option A: without wigelewifi file and check if it'snt read anything.
 * Option b:with  wigelewifi file and check if it's read it.
 */
	@Test
	public void test() {
		String dirPath = "C:/Users/Gilad Fuchs/workspace/Ex0";
		String desPath = "C:/Users/Gilad Fuchs/workspace/Ex0/output2.csv";

		Q2 q2 = new Q2();          
		/**
		 *  read files from directory and write to file
		 */
		boolean b = q2.ReadDir(dirPath,desPath);
		assertEquals(false, b);
	}

}
