package Objects;

public class Mac {
	double signal;
	double lat;
	double lon;
	double alt;
	/**
	 *  This object keep the locate of the mac and the signal 
	 * @param lat
	 * @param lon
	 * @param alt
	 * @param signal
	 */
	public Mac(double lat,double lon,double alt,double signal) {
		this.lat = lat;
		this.lon = lon;
		this.alt = alt;
		this.signal = signal;
	
	}
	public void print()
	{
		System.out.println(lat +" "+lon+" "+alt+" "+signal);
	}
	/**
	 * This variable represent the signal in the Mac
	 * @return signal
	 */
	public double getSignal() {
		return signal;
	}
	/**
	 *  This function change the signal in the Mac
	 * @param signal
	 */
	public void setSignal(double signal) {
		this.signal = signal;
	}
	/**
	 * This variable represent the lat in the Mac
	 * @return signal
	 */
	public double getLat() {
		return lat;
	}
	/**
	 *  This function change the Lat in the Mac
	 * @param signal
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}
	/**
	 * This variable represent the lon in the Mac
	 * @return signal
	 */
	public double getLon() {
		return lon;
	}
	/**
	 *  This function change the lon in the Mac
	 * @param signal
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}
	/**
	 * This variable represent the alt in the Mac
	 * @return signal
	 */
	public double getAlt() {
		return alt;
	}
	/**
	 *  This function change the alt in the Mac
	 * @param signal
	 */
	public void setAlt(double alt) {
		this.alt = alt;
	}
}
