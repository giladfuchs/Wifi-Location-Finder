package test.java.Junit_Testing;

import static org.junit.Assert.*;


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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.Objects.Details;
import main.java.Objects.Row;
import main.java.Objects.Wifi;

import java.sql.Statement;



import org.junit.Test;

import main.java.Objects.Details;
import main.java.Objects.Row;
import main.java.Objects.Wifi;
/**
 * 
 * @author Gilad Fuchs
 *In this function we connect to my own sql server and check if it's does some change
 *
 */
public class OrgSqlTest {

	static String  TimeUpdate="";
	 static String _ip = "localhost";
	 static String _url = "jdbc:mysql://"+_ip+":3306/sakila?useSSL=false";
	   static String _user = "gilad";
	   static String _password = "gilad";
	 public static boolean TimeUpdateCheck() {
			Statement st = null;
			ResultSet rs = null;
		    Connection _con = null;

			try {     
				_con = DriverManager.getConnection(_url, _user, _password);
				st = _con.createStatement();
				  rs = st.executeQuery("SELECT UPDATE_TIME FROM information_schema.tables WHERE TABLE_SCHEMA = 'sakila' AND TABLE_NAME = 'Scan_list'");
				  PreparedStatement pst = _con.prepareStatement("SELECT * FROM Scan_list");
			
				 
				 
		           
				
				  if (rs.next()) {
				
					/*if the last modified time isn't current, then table was modified*/
					if(!rs.getString(1).equals(TimeUpdate)) {
						TimeUpdate=rs.getString(1);
						/*if there is some change*/
						return true;
					}else {
						return false;
						
					}
				}

			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(OrgSqlTest.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			} finally {
				try {
					if (rs != null) {rs.close();}
					if (st != null) { st.close(); }
					if (_con != null) { _con.close();  }
				} catch (SQLException ex) {

					Logger lgr = Logger.getLogger(OrgSqlTest.class.getName());
					lgr.log(Level.WARNING, ex.getMessage(), ex);
				}
			} return false;

		}
	


		public static void  test_ex4_db(){
	    
		   Connection _con = null;
		  
	        Statement st = null;
	        ResultSet rs = null;
	        int max_id = -1;
	  	  List<Row> listInput = new ArrayList<Row>();
	        try {     
	            _con = DriverManager.getConnection(_url, _user, _password);
	            st = _con.createStatement();
	            rs = st.executeQuery("SELECT UPDATE_TIME FROM information_schema.tables WHERE TABLE_SCHEMA = 'sakila' AND TABLE_NAME = 'Scan_list'");
	            if (rs.next()) {
	                System.out.println("**** Update: "+rs.getString(1));
	                TimeUpdate=rs.getString(1);
	            }
	           
	        } catch (SQLException ex) {
				Logger lgr = Logger.getLogger(OrgSqlTest.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			} finally {
				try {
					if (rs != null) {rs.close();}
					if (st != null) { st.close(); }
					if (_con != null) { _con.close();  }
				} catch (SQLException ex) {

					Logger lgr = Logger.getLogger(OrgSqlTest.class.getName());
					lgr.log(Level.WARNING, ex.getMessage(), ex);
				}
	          
	  
	            	
	    
	    }
	   
	}
		@Test
		public void test() {
			test_ex4_db();
			Scanner sc=new Scanner(System.in);
			System.out.println("press something to conitnue");
			/*Giving time to the user to do change*/
			String s=sc.next();
			sc.close();
			boolean check=TimeUpdateCheck();
		assertEquals(false,check );
		
			
		}

}
