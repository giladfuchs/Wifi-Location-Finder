package Filter;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Convert.Q2;
import Objects.Row;
import Read_Write.ReadAndWriteCSV;


public class Filter {
	private List<List<Row>> Undo = new ArrayList< List<Row>>(); 
	private int countfilter=0;
	private int indexOr=0;
	private boolean flag=true;
	private String change="";
	ReadAndWriteCSV read = new ReadAndWriteCSV();
	public void readq2(String dirPath) throws ParseException{
		Q2 q2 = new Q2();
		Undo.add( q2.ReadDir(dirPath));

	}
	public void write(String desPath2){
		read.WriteListIntoFile(Undo.get(3),desPath2);
	}
	public void read(String srcPath) throws ParseException{
	
System.out.println(srcPath);
		Undo.add(read.ReadFileIntoList3(srcPath));
		change=Undo.get(0).get(0).getHead().getCount();
	}
	public void filtermain(int mode,String id) 
	{		
		
		
			System.out.println(countfilter);
		
		switch (mode)  
		{
		case 1: {
			flag=true;
			FilterAnd And=new FilterAnd();
			Undo.add(And.CalculateByID1(Undo.get(countfilter),new ArrayList<Row>(),  id));

			break;
		}
		case 2: {
			
			FilterOr Or=new FilterOr();
			if(flag){
			
				indexOr=countfilter;
			
				
			}
			Undo.add(Or.CalculateByID1(Undo.get(indexOr),new ArrayList<Row>(),  id));
			if(!flag){
				MergeList mer=new MergeList();
				mer.merge(Undo.get(countfilter), Undo.get(countfilter+1));
			}
			flag=false;

			break;
		}
		case 3: {
			flag=true;
			FilterNot Not=new FilterNot();
			Undo.add(Not.CalculateByID1(Undo.get(countfilter),new ArrayList<Row>(),  id));
			break;


		}
		}
		System.out.println(Undo.size());
		
		for (int i = 0; i < Undo.size(); i++) {
			System.out.println(Undo.get(i).get(0).getHead().getCount());
		}
		
		if(Undo.get(0).get(0).getHead().getCount().equals("no_change")){
			System.out.println(Undo.get(0).get(0).getHead().getCount()+"  \n"+Undo.get(1).get(0).getHead().getCount());
			Undo.get(0).get(0).getHead().setCount(change);
			Undo.remove(Undo.size()-1);
		}
		else
			countfilter++;
		
		

	}
}
