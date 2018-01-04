package Filter;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Convert.Q2;
import Objects.Row;
import Read_Write.ReadAndWriteCSV;


public class Filter {
	private List<List<Row>> DataBase = new ArrayList< List<Row>>(); 
	private int countfilter=0;
	private int indexOr=0;
	private boolean flag=true;
	private String change="";
	ReadAndWriteCSV read = new ReadAndWriteCSV();
	public boolean readq2(String dirPath) throws ParseException{
		Q2 q2 = new Q2();
		List<Row> first = q2.ReadDir(dirPath);
		if(first == null)
			return false;
		else
			DataBase.add(first);
		return true;
		
	}
	public void write(String desPath2){
		read.WriteListIntoFile(DataBase.get(countfilter),desPath2);
	}
	public void read(String srcPath) throws ParseException{

		System.out.println(srcPath);
		DataBase.add(read.ReadFileIntoList3(srcPath));
		change=DataBase.get(0).get(0).getHead().getCount();
	}
	public void filtermain(boolean andor,boolean not, int filterType, String s1,String s2,String s3) 
	{		
		FilterNot Not=new FilterNot();
		FilterAnd And=new FilterAnd();
		System.out.println(countfilter);

		switch (filterType)  
		{
		case 1: {

			if(andor){
				if(flag)
					indexOr=countfilter;
				if(!not)
					DataBase.add(And.CalculateByID1(DataBase.get(indexOr),new ArrayList<Row>(),  s1));
				else
					DataBase.add(Not.CalculateByID1(DataBase.get(indexOr),new ArrayList<Row>(),  s1));
				if(!flag){
					MergeList mer=new MergeList();
					mer.merge(DataBase.get(countfilter), DataBase.get(countfilter+1));
				}
				flag=false;
			}
			else{
				flag=true;
				if(!not)
					DataBase.add(And.CalculateByID1(DataBase.get(countfilter),new ArrayList<Row>(),  s1));
				else
					DataBase.add(Not.CalculateByID1(DataBase.get(countfilter),new ArrayList<Row>(),  s1));
			}

			break;
		}
		case 2: {

			if(andor){
				if(flag)
					indexOr=countfilter;
				if(!not)
					DataBase.add(And.CalculateByTime1(DataBase.get(indexOr),new ArrayList<Row>(), s1,s2));
				else
					DataBase.add(Not.CalculateByTime1(DataBase.get(indexOr),new ArrayList<Row>(),s1,s2));
				if(!flag){
					MergeList mer=new MergeList();
					mer.merge(DataBase.get(countfilter), DataBase.get(countfilter+1));
				}
				flag=false;
			}
			else{
				flag=true;
				if(!not)
					DataBase.add(Not.CalculateByTime1(DataBase.get(countfilter),new ArrayList<Row>(), s1,s2));
				else
					DataBase.add(Not.CalculateByTime1(DataBase.get(countfilter),new ArrayList<Row>(), s1,s2));
			}

			break;
		
		}
		case 3: {
			if(andor){
				if(flag)
					indexOr=countfilter;
				if(!not)
					DataBase.add(And.CalculateByLocation1(DataBase.get(indexOr),new ArrayList<Row>(), Double.parseDouble(s1),Double.parseDouble(s2),Double.parseDouble(s3)));
				else
					DataBase.add(Not.CalculateByLocation1(DataBase.get(indexOr),new ArrayList<Row>(),Double.parseDouble(s1),Double.parseDouble(s2),Double.parseDouble(s3)));
				if(!flag){
					MergeList mer=new MergeList();
					mer.merge(DataBase.get(countfilter), DataBase.get(countfilter+1));
				}
				flag=false;
			}
			else{
				flag=true;
				if(!not)
					DataBase.add(Not.CalculateByLocation1(DataBase.get(countfilter),new ArrayList<Row>(),Double.parseDouble(s1),Double.parseDouble(s2),Double.parseDouble(s3)));
				else
					DataBase.add(Not.CalculateByLocation1(DataBase.get(countfilter),new ArrayList<Row>(),Double.parseDouble(s1),Double.parseDouble(s2),Double.parseDouble(s3)));
			}


			break;


		}
		}
		System.out.println(DataBase.size());

		for (int i = 0; i < DataBase.size(); i++) {
			//System.out.println(DataBase.get(i).get(0).getHead().getCount());
		}

		if(DataBase.get(0).get(0).getHead().getCount().equals("no_change" ) ){
			DataBase.get(0).get(0).getHead().setCount(change);
			DataBase.remove(DataBase.size()-1);
		}
		else
			countfilter++;



	}
}
