package GUI;

/** 
 * This is a very simple example representing how to work with MySQL 
 * using java JDBC interface;
 * The example mainly present how to read a table representing a set of WiFi_Scans
 * Note: for simplicity only two properties are stored (in the DB) for each AP:
 * the MAC address (mac) and the signal strength (rssi), the other properties (ssid and channel)
 * are omitted as the algorithms do not use the additional data.
 * 
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Objects.Details;
import Objects.Row;
import Objects.Wifi;

import java.sql.Statement;
import java.sql.Time;

public class OrgSql{

	private static String _ip;	
	private static String _user ;
	private static String _password ;
	private static String _path;
	private static String _table;
	private static String _url ;
	private static Connection _con = null;
	public static String  TimeUpdate="";
	


	public static List<Row>  ReadSqlData(String ip,String port,String user,String password, String path, String table) {
		_ip = ip;
		_path=path;
		_url ="jdbc:mysql://"+_ip+":"+port+"/"+_path+"?useSSL=false";
		_user = user;
		_password = password;		
		_table=table;
		Statement st = null;
		ResultSet rs = null;
		int max_id = -1;
		List<Row> listInput = new ArrayList<Row>();
		try {     
			_con = DriverManager.getConnection(_url, _user, _password);
			st = _con.createStatement();
			rs = st.executeQuery("SELECT UPDATE_TIME FROM information_schema.tables WHERE TABLE_SCHEMA = '"+_path+"' AND TABLE_NAME = '"+_table+"'");
			if (rs.next()) {
				System.out.println("**** Update: "+rs.getString(1));
				TimeUpdate=rs.getString(1);
			}

			PreparedStatement pst = _con.prepareStatement("SELECT * FROM "+_table);

			rs = pst.executeQuery();


			while (rs.next()) {


				List<Wifi> element = new ArrayList<Wifi>();



				Details general=new Details(rs.getString(2),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(3),rs.getString(7));        


				int j = 8;

				for (int i = 0; i < rs.getInt(7); i++) {
					Wifi insert=new Wifi(rs.getString(j),"","",rs.getString(j+1));
					element.add(insert);
					j += 2;
				}
				Row row=new Row(element,general); 
				listInput.add(row);

			}
		} catch (SQLException ex) {

			Logger lgr = Logger.getLogger(OrgSql.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (rs != null) {rs.close();}
				if (st != null) { st.close(); }
				if (_con != null) { _con.close();  }
			} catch (SQLException ex) {

				Logger lgr = Logger.getLogger(OrgSql.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}

		return listInput;
	}


	public static boolean TimeUpdateCheck() {
		Statement st = null;
		ResultSet rs = null;
		Connection _con = null;

		try {     
			_con = DriverManager.getConnection(_url, _user, _password);
			st = _con.createStatement();
			rs = st.executeQuery("SELECT UPDATE_TIME FROM information_schema.tables WHERE TABLE_SCHEMA = '"+_path+"' AND TABLE_NAME = '"+_table+"'");
			System.out.println(TimeUpdate);
			/*if the last modified time isn't current, then table was modified*/
			if (rs.next()) {
				if(!rs.getString(1).equals(TimeUpdate)) {
					TimeUpdate=rs.getString(1);
					return true;
				}else {
					return false;
				}

			}

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(OrgSql.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (rs != null) {rs.close();}
				if (st != null) { st.close(); }
				if (_con != null) { _con.close();  }
			} catch (SQLException ex) {

				Logger lgr = Logger.getLogger(OrgSql.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		} return false;

	}
}