package Algorithms;
import java.util.ArrayList;
import java.util.List;

import GUI.gui;
import Objects.Listmac;
import Objects.Mac;
import Read_Write.ReadAndWriteCSV;

public class Algo1MacW {
	/**
	 * 	
	 * @param output The List he receive from the Function macbase 
	 * @param dest 
	 * @return 
	 */

	public  Mac MacW(List<Mac> Input){

		
		/**
		 * Create a new list and change the value of lat,lon, alt to lat/signal^2
		 */
		
			List<Mac> listMacOut=new ArrayList<Mac>();
			for (int i = 0; i < Input.size(); i++) {
				double x=1/(Math.pow(Input.get(i).getSignal(),2));
				Mac m=new Mac(Input.get(i).getLat()*x,Input.get(i).getLon()*x,Input.get(i).getAlt()*x,x);
				listMacOut.add(m);
			}
			/**
			 *Create one variable for each lat,lot,alt and Put them together in the same variable.
			 */
			double walt=0,wlon=0,wlat=0,wsig=0;
			for (int j = 0; j < listMacOut.size(); j++) {
				walt+=listMacOut.get(j).getAlt();
				wlon+=listMacOut.get(j).getLon();
				wlat+=listMacOut.get(j).getLat();
				wsig+=listMacOut.get(j).getSignal();
			}
			
			/**
			 * insert the mac after the calculate,and put it in a Mac to prepare to export it.
			 */
			Mac fin=new Mac(wlat/wsig,wlon/wsig,walt/wsig,2);
			return fin;
		
		}

	}
