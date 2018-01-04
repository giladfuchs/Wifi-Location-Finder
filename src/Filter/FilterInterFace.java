package Filter;


import java.util.List;

import Objects.Row;

public interface FilterInterFace {
	public  List<Row> CalculateByLocation1(List<Row> listInput, List<Row> listOutput,  double Lon ,double Lat,double Radius) ;
	public List<Row> CalculateByID1(List<Row> listInput,List<Row> listOutput,String id);
	public List<Row> CalculateByTime1(List<Row> listInput, List<Row> listOutput,String startDate,String endDate);


}
