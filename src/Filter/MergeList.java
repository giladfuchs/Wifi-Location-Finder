package Filter;

import java.util.ArrayList;

import java.util.List;


import Objects.Row;

public class MergeList {
	public void merge(List<Row> copy,List<Row> paste){
		for (int i = 0; i < copy.size(); i++) {
			Row row = new Row(copy.get(i).getElement(),copy.get(i).getHead());
			paste.add(row);
		}
	
	
		
		
		
		
	for (int i = 0; i < paste.size()-1; i++) {
		int j=i+1;
		while ( j < paste.size()) {
			if(paste.get(i).isEquals(paste.get(j)))
				paste.remove(j);
			else
				j++;
			}
		}

	}

}
