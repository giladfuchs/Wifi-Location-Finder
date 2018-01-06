package Objects;

import Filter.Filter;

public class FilterInfo 
{	
	private String kind;
	private String type;
	private String mode;
	private String data;
	private int numoffilter;
		
	
	/**
	 * This object keep the values of the filter 
	 * @param kind - And/Or
	 * @param type - Not/Regular
	 * @param mode - Date/ID/Location (filter mode)
	 * @param data - data of the filter mode
	 */
	public FilterInfo(String kind,boolean type,String mode,String data,int numoffilter){
		this.kind=kind;
		this.mode=mode;
		this.data=data;
		if(!type)
			this.type="Regular";
		else
			this.type="Not";
		this.numoffilter=numoffilter;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public int getNumoffilter() {
		return numoffilter;
	}
	public void setNumoffilter(int numoffilter) {
		this.numoffilter = numoffilter;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "   number of filter: "+numoffilter+" " + kind + " , " + type + " , " + mode + " , " + data +  "\n";
	}
}
