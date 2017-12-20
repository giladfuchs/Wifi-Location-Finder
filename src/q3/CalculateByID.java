package q3;
import java.util.List;

import object.Row;

public class CalculateByID {
	/**
	 * This function finds all the Mac's that equal to id
	 * @param listInput
	 * @param listOutput
	 * @param id
	 * @return listOutput
	 */
	
	public List<Row> CalculateByID1(List<Row> listInput,List<Row> listOutput,String id)
	{
		boolean find = true;
		for(int i=1;i<listInput.size();i++)
		{
			/**
			 * check each row from listInput if equal to id
			 * if yes, add row to listOutput
			 */
			if(id.equals(listInput.get(i).getHead().getID())) //take the only row with the same id
			{		
				Row row = new Row(listInput.get(i).getElement(),listInput.get(i).getHead());
				listOutput.add(row);
				find = false;
			}
		}
		if(find){
			System.out.println("The filter didnt find this ID");
			return listInput;	
		}
		return listOutput;				
	}
}
