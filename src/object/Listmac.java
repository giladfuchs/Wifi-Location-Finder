package object;
import java.util.List;

public class Listmac {
	private List<Mac> samemac;
	private String time;
	private	String name;
	private	String id;
	private	String ferq;
	/**
	 * 
	 * @param samemac
	 * @param time
	 * @param name
	 * @param id
	 * @param ferq
	 */
	public Listmac(List<Mac> samemac,String time,String name,String id,String ferq){
		this.samemac=samemac;
		this.time=time;
		this.name=name;
		this.id=id;
		this.ferq=ferq;
	}
	public void print(){
		System.out.println(time+" ,"+name+" ,"+id+" ,"+ferq+" ,");

	}
	/**
	 * This variable represent the list of the Mac in the Listmac
	 * @return samemac
	 */
	public List<Mac> getSamemac() {
		return samemac;
	}
	/**
	 *  This function change the list of the Mac in the Listmac
	 * @param samemac
	 */
	public void setSamemac(List<Mac> samemac) {
		this.samemac = samemac;
	}
	/**
	 * This variable represent the time in the Listmac
	 * @return time
	 */
	public String getTime() {
		return time;
	}
	/**
	 *  This function change the time in the Listmac
	 * @param time
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * This variable represent the name in the Listmac
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 *  This function change the name in the Listmac
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * This variable represent the id in the Listmac
	 * @return id
	 */
	public String getId() {
		return id;
	}
	/**
	 *  This function change the id in the Listmac
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * This variable represent the frequency in the Listmac
	 * @return ferq
	 */
	public String getFerq() {
		return ferq;
	}
	/**
	 *  This function change the frequency in the Listmac
	 * @param ferq
	 */
	public void setFerq(String ferq) {
		this.ferq = ferq;
	}
}
