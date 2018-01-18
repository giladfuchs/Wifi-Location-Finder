package main.java.Objects;

import main.java.Filter.Filter;

public class FilterInfo 
{	
	private String kind;
	private String type;
	private String mode;
	private String s1;
	private String s2;
	private String s3;
	private int numoffilter;
		
	
	/**
	 * This object keep the values of the filter 
	 * @param kind - And/Or
	 * @param type - Not/Regular
	 * @param mode - Date/ID/Location (filter mode)
	 * @param data - data of the filter mode
	 */
	public FilterInfo(String kind,boolean type,String mode,String s1,String s2,String s3,int numoffilter){
		this.kind=kind;
		this.mode=mode;
		this.s1=s1;
		this.s2=s2;
		this.s3=s3;
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

	@Override
	public String toString() {
		return "   number of filter: "+numoffilter+" " + kind + " , " + type + " , " + mode + " , " + s1+"  " +s2+"  "+s3+  "\n";
	}
	public String getS1() {
		return s1;
	}
	public void setS1(String s1) {
		this.s1 = s1;
	}
	public String getS2() {
		return s2;
	}
	public void setS2(String s2) {
		this.s2 = s2;
	}
	public String getS3() {
		return s3;
	}
	public void setS3(String s3) {
		this.s3 = s3;
	}
}
