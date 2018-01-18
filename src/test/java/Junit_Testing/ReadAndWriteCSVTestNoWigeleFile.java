package test.java.Junit_Testing;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import main.java.Convert.Q2;
import main.java.Objects.Row;

public class ReadAndWriteCSVTestNoWigeleFile {
/**
 * This test get a path.
 * Option A: without wigelewifi file and check if it'snt read anything.
 * Option b:with  wigelewifi file and check if it's read it.
 */
	@Test
	public void test() {
		String dirPath = "C:/Windows.old";
		String desPath = "C:/Users/Gilad Fuchs/workspace/Ex0/output2.csv";

		Q2 q2 = new Q2();          
		/**
		 *  read files from directory and write to file
		 */
		List<Row> l=q2.ReadDir(dirPath);
		boolean b = true;
		if(l==null)
			b=false;
		assertEquals(false, b);
	}

}
