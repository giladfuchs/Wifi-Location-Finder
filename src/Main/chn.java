package Main;

import java.util.List;

import Objects.Row;
import Read_Write.ReadAndWriteCSV;

public class chn {

	public static void main(String[] args) {
		ReadAndWriteCSV wr=new ReadAndWriteCSV();
		List<Row> list=wr.ReadFileIntoList3("C:/Users/Gilad Fuchs/Documents/abc.csv");
		while(list.size()>20)
			list.remove(0);
		char d='a';
		for (int i = 0; i < list.size(); i++) {
			list.get(i).getHead().setID(""+d);
			i++;
			list.get(i).getHead().setID(""+d);
			d++;
		}
		wr.WriteListIntoFile(list, "C:/Users/Gilad Fuchs/Documents/aabbcbb.csv");
		System.out.println(d);
		// TODO Auto-generated method stub

	}

}
