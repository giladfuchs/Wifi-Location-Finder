package object;

public class Details {

	String count, time,  lat, lon, alt,  ID;
		/**
		 * This object present the six contest variable in the row
		 * @param time
		 * @param lat
		 * @param lon
		 * @param alt
		 * @param ID
		 * @param count
		 */
	public Details(String time,String lat,String lon,String alt,String ID,String count) {
		this.lat = lat;
		this.lon = lon;
		this.alt = alt;
		this.ID = ID;
		this.time = time;
		this.count = count;
	}
	/**
	 * this variable represent how many wifi has in the row 
	 * @return count
	 */
	public String getCount() {
		return this.count;
	}	
	/**
	 * this variable represent the time for the sample in the row 
	 * @return count
	 */
	public String getTime() {
		return this.time;
	}
	/**
	 * this variable represent the latitude for the sample in the row 
	 * @return count
	 */
	public String getLat() {
		return this.lat;
	}
	/**
	 * this variable represent the longitude for the sample in the row 
	 * @return count
	 */
	public String getLon() {
		return this.lon;
	}
	/**
	 * this variable represent the altitude for the sample in the row 
	 * @return count
	 */
	public String getAlt() {
		return this.alt;
	}
	/**
	 * this variable represent the name of the device for the sample in the row 
	 * @return count
	 */
	public String getID() {
		return this.ID;
	}
	/**
	 * When we want to change the time
	 * @param time
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * When we erase wifi we need to setCount
	 * @param count
	 */
	public void setCount(String count) {
		this.count = count;
	}
	/**
	 * When we want to change the lat
	 * @param lat
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}
	/**
	 * When we want to change the lon
	 * @param lon
	 */
	public void setLon(String lon) {
		this.lon = lon;
	}
	/**
	 * When we want to change the alt
	 * @param alt
	 */
	
	public void setAlt(String alt) {
		this.alt = alt;
	}
	/**
	 * When we want to change the id
	 * @param iD
	 */
	public void setID(String iD) {
		ID = iD;
	}
}
