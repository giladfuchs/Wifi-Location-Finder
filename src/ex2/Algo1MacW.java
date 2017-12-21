package ex2;
import java.util.ArrayList;
import java.util.List;

import Read_Write.ReadAndWriteCSV;
import object.Listmac;
import object.Mac;

public class Algo1MacW {
	/**
	 * 	
	 * @param output The List he receive from the Function macbase 
	 * @param dest 
	 */

	public  void MacW(List<Listmac> output, String dest){

		List<Listmac> output2 = new ArrayList<Listmac>();
		/**
		 * Create a new list and change the value of lat,lon, alt to lat/signal^2
		 */
		for (int i = 0; i < output.size(); i++) {
			List<Mac> listMacOut=new ArrayList<Mac>();
			for (int j = 0; j < output.get(i).getSamemac().size(); j++) {
				double x=1/(Math.pow(output.get(i).getSamemac().get(j).getSignal(),2));
				Mac m=new Mac(output.get(i).getSamemac().get(j).getLat()*x,output.get(i).getSamemac().get(j).getLon()*x,output.get(i).getSamemac().get(j).getAlt()*x,x);
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
			List<Mac> tmp=new ArrayList<Mac>();
			/**
			 * insert the mac after the calculate,and put it in a List to prepare to export it to csv file.
			 */
			Mac fin=new Mac(wlat/wsig,wlon/wsig,walt/wsig,output.get(i).getSamemac().get(0).getSignal());
			tmp.add(fin);
			Listmac t=new Listmac(tmp,output.get(i).getTime(),output.get(i).getName(),output.get(i).getId(),
					output.get(i).getFerq());
			output2.add(t);
		}
		/**
		 * export it to csv file
		 */
		ReadAndWriteCSV a=new ReadAndWriteCSV();
		a.WriteListmacIntoFile(output2,dest);	

	}}
