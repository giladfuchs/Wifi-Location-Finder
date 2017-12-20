package object;

public class Spot 
{
	String ID;
	String time;
	String type;
	String mac;
	String ssid;
	String chanal;
	String lat;
	String lon;
	String alt;
	String signal;
	/**
	 * This object we read the wigele wifi file
	 * @param line
	 * @param ID
	 * @param  time
	 * @param 	String type
	 * @param String mac
	 * @param 	String ssid
	 * @param String chanal
	 * @param String lat
	 * @param String lon
	 * @param String alt
	 * @param String signal
	 */

	public Spot(String line,String ID) {

		String[] temp = line.split(",");
		this.ID = ID;
		this.mac = temp[0];
		this.ssid = temp[1];
		this.time = temp[3];
		this.chanal=temp[4];
		this.signal = temp[5];
		this.lat = temp[6];
		this.lon = temp[7];
		this.alt = temp[8];
		this.type = temp[10];


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
	 * This variable represent the mac in the Spot
	 * @return mac
	 */
	public String getMac() {
		return this.mac;
	}

	/**
	 * This variable represent the ssid in the Spot
	 * @return SSID
	 */
	public String getSSID() {
		return this.ssid;
	}

	/**
	 * This variable represent the chanal in the Spot
	 * @return chanal
	 */
	public String getchanal() {
		return this.chanal;
	}

	/**
	 * This variable represent the time in the Spot
	 * @return time
	 */
	public String getTime() {
		return this.time;
	}

	/**
	 * This variable represent the signal in the Spot
	 * @return signal
	 */
	public String getSignal() {
		return this.signal;
	}
/**
 * This function print the Spot
 */
	public String toString() {
		return chanal + "," + time + "," + lat + "," + lon + "," + alt + "," + mac + "," + ssid + "," + ID + ","
				+ signal+ ","+ type;
	}
}

