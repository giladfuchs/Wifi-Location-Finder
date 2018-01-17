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

	  private static String _ip = "5.29.193.52";
	  private static String _url = "jdbc:mysql://"+_ip+":3306/oop_course_ariel";
	  private static String _user = "oop1";
	  private static String _password = "Lambda1();";
	  private static Connection _con = null;
	  public static String  TimeUpdate="";
    public static void main(String[] args) {
    	 test_ex4_db();
    	TimeUpdate();
    	 
    	// test_101();
  	//insert_table(max_id);
    }
    public static int test_101() {
        Statement st = null;
        ResultSet rs = null;
        int max_id = -1;
        //String ip = "localhost";
       // String ip = "192.168.1.18";

        try {     
            _con = DriverManager.getConnection(_url, _user, _password);
            st = _con.createStatement();
            rs = st.executeQuery("SELECT UPDATE_TIME FROM ");
            if (rs.next()) {
                System.out.println(rs.getString(1));
                
            }
           
            PreparedStatement pst = _con.prepareStatement("SELECT * FROM test101");
            rs = pst.executeQuery();
            
            while (rs.next()) {
            	int id = rs.getInt(1);
            	if(id>max_id) {max_id=id;}
                System.out.print(id);
                System.out.print(": ");
                System.out.print(rs.getString(2));
                System.out.print(" (");
                double lat = rs.getDouble(3);
                System.out.print(lat);
                System.out.print(", ");
                double lon = rs.getDouble(4);
                System.out.print(lon);
                System.out.println(") ");
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(MySQL_101.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                if (rs != null) {rs.close();}
                if (st != null) { st.close(); }
                if (_con != null) { _con.close();  }
            } catch (SQLException ex) {
                
                Logger lgr = Logger.getLogger(MySQL_101.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return max_id;
    }
    
    public static List<Row>  test_ex4_db() {
        Statement st = null;
        ResultSet rs = null;
        int max_id = -1;
        List<Row> listInput = new ArrayList<Row>();
        try {     
            _con = DriverManager.getConnection(_url, _user, _password);
            st = _con.createStatement();
            rs = st.executeQuery("SELECT UPDATE_TIME FROM information_schema.tables WHERE TABLE_SCHEMA = 'oop_course_ariel' AND TABLE_NAME = 'ex4_db'");
            if (rs.next()) {
                System.out.println("**** Update: "+rs.getString(1));
                TimeUpdate=rs.getString(1);
            }
           
            PreparedStatement pst = _con.prepareStatement("SELECT * FROM ex4_db");
            rs = pst.executeQuery();
            int ind=0;
            
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
            	ind++;
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(MySQL_101.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                if (rs != null) {rs.close();}
                if (st != null) { st.close(); }
                if (_con != null) { _con.close();  }
            } catch (SQLException ex) {
                
                Logger lgr = Logger.getLogger(MySQL_101.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return listInput;
    }
    

    public static boolean TimeUpdate() {
		Statement st = null;
		ResultSet rs = null;
	    Connection _con = null;

		try {     
			_con = DriverManager.getConnection(_url, _user, _password);
			st = _con.createStatement();
			 rs = st.executeQuery("SELECT UPDATE_TIME FROM information_schema.tables WHERE TABLE_SCHEMA = 'oop_course_ariel' AND TABLE_NAME = 'ex4_db'");
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
			Logger lgr = Logger.getLogger(MySQL_101.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (rs != null) {rs.close();}
				if (st != null) { st.close(); }
				if (_con != null) { _con.close();  }
			} catch (SQLException ex) {

				Logger lgr = Logger.getLogger(MySQL_101.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		} return false;

	}
}
    
 /*   public static void insert_table2(int max_id, WiFi_Scans ws) {
        Statement st = null;
        ResultSet rs = null;
    
        try {     
            _con = DriverManager.getConnection(_url, _user, _password);
            st = _con.createStatement();
            
            int size = ws.size();
            for(int i=0;i<size;i++) {
            	int curr_id = 1+i+max_id;
            	WiFi_Scan c = ws.get(i);
            	String sql = creat_sql(c, curr_id);
            	PreparedStatement pst = _con.prepareStatement(sql);
            	System.out.println(sql);
            	pst.execute();
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(MySQL_101.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                if (rs != null) {rs.close();}
                if (st != null) { st.close(); }
                if (_con != null) { _con.close();  }
            } catch (SQLException ex) {
                
                Logger lgr = Logger.getLogger(MySQL_101.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }
    private static String creat_sql(WiFi_Scan w, int id) {
    	String ans = "INSERT INTO ex4_db (ID,time, device,lat,lon,alt, number_of_ap";
    	String str1 = "", str2="";
    	Point3D pos = w.get_pos();
       	int n = w.size();
    	String in = " VALUES ("+id+",'"+w.get_time()+"','"+w.get_device_id()+"',"+pos.x()+","+pos.y()+","+pos.z()+","+n; 
    	for(int i=0;i<n;i++) {
    		str1+=",mac"+i+",rssi"+i;
    		WiFi_AP a = w.get(i);
    		str2+=",'"+a.get_mac()+"',"+(int)a.get_rssi();
    	}
    	ans +=str1+")"+in+str2+")";    	
    	return ans;
    }
}*/