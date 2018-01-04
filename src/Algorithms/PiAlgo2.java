package Algorithms;
import java.util.List;

import Objects.Row;

public class PiAlgo2 {
	/**
	 * This class calculate the location by the imagine rows he found in the class Algo2mac
	 * then its calculate the pi and the weight for the row.
	 * @param best arrays with index to the rows we need in listcomb
	 * @param i index we work in listInput
	 * @param listInput list we take the data to calculate pi weight
	 * @param listcomb we calculate diff with it and insert the missing data
	 *@param imagine  how many similar row we want to find
	 * 
	 */


	public  void pi(int[] best,List<Row> listInput,Row line,int imgine){
		/**
		 * This function calculate the diff and then calculate the pi and create arrays with pi
		 * tnd send the arrays to the function insertpi
		 */
		double [] datapi=new double[imgine];
		double power=2,norm=10000,sig_diff=0.4,min_diff=3,diff_no_signal=100,pi=0,diff1;
		for (int k = 0; k < best.length; k++) {
			for (int j = 0; j < line.getElement().size(); j++) {
				if(j<listInput.get(best[k]).getElement().size()){
					diff1=Math.max(Math.abs(Double.parseDouble(line.getElement().get(j).getSignal())-
							Double.parseDouble(listInput.get(best[k]).getElement().get(j).getSignal())),min_diff);
				}
				else
					diff1=diff_no_signal;
				pi+=norm/(Math.pow(diff1, sig_diff)*Math.pow(Double.parseDouble(line.getElement().get(j).getSignal()), power));
			}
			datapi[k]=pi;
			pi=0;
		}
		insertpi(best,listInput,line,datapi);
	}
	public  void insertpi(int[] best,List<Row> listInput,Row line,double [] datapi){
		/**
		 * This function calculate the the weight for the location with the pi.
		 * and in the final insert the location to the list. 
		 */
		double walt=0,wlon=0,wlat=0,wsig=0;
		for (int j = 0; j < datapi.length; j++) {
			walt+=Double.parseDouble(listInput.get(best[j]).getHead().getAlt())*datapi[j];
			wlon+=Double.parseDouble(listInput.get(best[j]).getHead().getLon())*datapi[j];
			wlat+=Double.parseDouble(listInput.get(best[j]).getHead().getLat())*datapi[j];
			wsig+=datapi[j];
		}
		walt/=wsig;
		wlat/=wsig;
		wlon/=wsig;
		line.getHead().setAlt(""+walt);
		line.getHead().setLat(""+wlat);
		line.getHead().setLon(""+wlon);
	}

}
