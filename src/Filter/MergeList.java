package Filter;

import java.util.ArrayList;
import java.util.List;

import Objects.Row;

public class MergeList {
	public List<Row> merge(List<List<Row>> Undo,int i){
		List<Row> ListOutput=new ArrayList<Row>();
		for (int j = i; j < Undo.size(); j++) {
			for (int j2 = 0; j2 < Undo.get(j).size(); j2++) {
				Duplicate dup=new Duplicate();
				if(dup.duplicate(ListOutput,Undo.get(j).get(j2).getHead().getID(),  Undo.get(j).get(j2).getHead().getTime())){
					System.out.print(i);
				Row row = new Row(Undo.get(j).get(j2).getElement(),Undo.get(j).get(j2).getHead());
				ListOutput.add(row);
				}
			}
		}
		return ListOutput;
	}

}
