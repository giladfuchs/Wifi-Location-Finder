package object;
import java.util.List;

public class Row 
{
	List<Wifi> element;
	Details head;
	/**
	 * 
	 * @param element
	 * @param head
	 */
	public Row(List<Wifi> element,Details head){
		this.element=element;
		this.head=head;		
	}
	/**
	 *  This variable represent the element in the Row
	 * @return element list of wifi object
	 */
	public List<Wifi> getElement() {
		return this.element;
	}
	/**
	 * This function change the element in the Row
	 * @param element
	 */
	public void setElement(List<Wifi> element) {
		this.element = element;
	}
	/**
	 *  This variable represent the head in the Row
	 * @return head the 5 common variable in the row
	 */
	public Details getHead() {
		return this.head;
	}
	/**
	 * This function change the head in the Row
	 * @param head
	 */
	public void setHead(Details head) {
		this.head = head;
	}
	@Override
	/** 
	 * This function print the object
	 */
	public String toString() {
		return "Row [element=" + element + ", head=" + head + "]";
	}

}
