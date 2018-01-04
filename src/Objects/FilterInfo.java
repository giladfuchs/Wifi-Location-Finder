package Objects;

public class FilterInfo {
	String kind;
	String type;
	String mode;
	String data;
	public FilterInfo(String kind,boolean type,String mode,String data){
		this.kind=kind;
		this.mode=mode;
		this.data=data;
		if(type)
			this.type="Regular";
		else
			this.type="Not";
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "FilterInfo [" + kind + " , " + type + " , " + mode + " data " + data + "]\n";
	}
}
