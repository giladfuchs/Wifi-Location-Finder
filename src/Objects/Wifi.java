package Objects;

public class Wifi {
	private	String  mac;
	private	String ssid;
	private	String chanal;
	private	String signal;
	/**
	 * This object present the four changing variable in the row
	 * @param mac
	 * @param ssid
	 * @param chanal
	 * @param signal
	 */
	public Wifi(String  mac,String ssid,String chanal,String signal){
		this.mac =mac;
		this.ssid = ssid;
		this.chanal=chanal;
		this.signal = signal;
	}
	/**
	 * This variable represent the mac in the wifi
	 * @return mac
	 */
	public String getMac() {
		return this.mac;
	}
	/**
	 * This variable represent the ssid in the wifi
	 * @return ssid
	 */
	public String getSsid() {
		return this.ssid;
	}
	/**
	 * This variable represent the chanal in the wifi
	 * @return chanal
	 */
	public String getChanal() {
		return this.chanal;
	}
	/**
	 * This variable represent the signal in the wifi
	 * @return signal
	 */
	public String getSignal() {
		return this.signal;
	}
	/**
	 * This function change the mac in the wifi
	 * @param mac
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}
	/**
	 *  This function change the ssid in the wifi
	 * @param ssid
	 */
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	/**
	 *  This function change the chanal in the wifi
	 * @param chanal
	 */
	public void setChanal(String chanal) {
		this.chanal = chanal;
	}
	/**
	 *  This function change the signal in the wifi
	 * @param signal
	 */
	public void setSignal(String signal) {
		this.signal = signal;
	}
}
