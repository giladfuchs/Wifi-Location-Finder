package Read_Write;
import java.util.ArrayList;
import java.util.List;

import object.Row;

public class CopyListToList {
	/**
	 * This function creates new List of Row - listInput
	 * copy from listOutput to listInput
	 * @param listOutput
	 * @return listInput
	 */
	public List<Row> CopyListToList1(List<Row> listOutput) {
		List<Row> listInput = new ArrayList<Row>();
		for(int i=0;i<listOutput.size();i++){
			Row row = new Row(listOutput.get(i).getElement(),listOutput.get(i).getHead());
			listInput.add(row);		
		}
		return listInput;
	}

}
