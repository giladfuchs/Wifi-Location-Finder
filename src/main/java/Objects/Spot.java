package main.java.Objects;

public class Spot extends Wifi
{
	String ID;
	String time;
	String type;
	String lat;
	String lon;
	String alt;
	
	/**
	 * This object we read the wigele wifi file
	 * @param line
	 * @param ID
	 * @param  time
	 * @param String type
	 * @param String mac
	 * @param String ssid
	 * @param String chanal
	 * @param String lat
	 * @param String lon
	 * @param String alt
	 * @param String signal
	 */
	public Spot(String ID,String mac, String ssid, String time, String chanal, String signal,String lat,String lon,String alt,String type) {
		super(mac, ssid, chanal, signal);
		this.ID = ID;
		this.time =time;
		this.lat = chanal;
		this.lon = lon;
		this.alt = alt;
		this.type = type;
		
		// TODO Auto-generated constructor stub
	}



	/**
	 * This variable represent the lat in the Spot
	 * @return lat
	 */
	public String getLat() {
		return this.lat;
	}

	/**
	 * This variable represent the lon in the Spot
	 * @return lon
	 */
	public String getLon() {
		return this.lon;
	}

	/**
	 * This variable represent the alt in the Spot
	 * @return alt
	 */
	public String getAlt() {
		return this.alt;
	}

	/**
	 * @
	 * This variable represent the id in the Spot
	 * @return ID
	 */
	public String getID() {
		return this.ID;
	}

	/**
	 * This variable represent the type of the device the sample took 
	 * @return type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * This variable represent the time in the Spot
	 * @return tim
	 */
	public String getTime() {
		return this.time;
	}
}

