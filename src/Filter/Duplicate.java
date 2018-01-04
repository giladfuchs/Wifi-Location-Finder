package Filter;

import java.util.List;

import Objects.Row;
//tgefgfgfd
public class Duplicate {
public boolean duplicate (List<Row> listInput,String Id,String Time){
	for (int i = 0; i < listInput.size(); i++) {
		if(listInput.get(i).getHead().getTime().equals(Time) && listInput.get(i).getHead().getTime().equals(Id))
			return false;
	}
	return true;
}
}
