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


	public  void pi(int[] best,int i,List<Row> listInput,List<Row> listcomb,int imgine){
		/**
		 * This function calculate the diff and then calculate the pi and create arrays with pi
		 * tnd send the arrays to the function insertpi
		 */
		double [] datapi=new double[imgine];
		double power=2,norm=10000,sig_diff=0.4,min_diff=3,diff_no_signal=100,pi=0,diff1;
		for (int k = 0; k < best.length; k++) {
			for (int j = 0; j < listInput.get(i).getElement().size(); j++) {
				if(j<listcomb.get(best[k]).getElement().size()){
					diff1=Math.max(Math.abs(Double.parseDouble(listInput.get(i).getElement().get(j).getSignal())-
							Double.parseDouble(listcomb.get(best[k]).getElement().get(j).getSignal())),min_diff);
				}
				else
					diff1=diff_no_signal;
				pi+=norm/(Math.pow(diff1, sig_diff)*Math.pow(Double.parseDouble(listInput.get(i).getElement().get(j).getSignal()), power));
			}
			datapi[k]=pi;
			pi=0;
		}
		insertpi(best,i,listInput,listcomb,datapi);
	}
	public  void insertpi(int[] best,int i,List<Row> listInput,List<Row> listcomb,double [] datapi){
		/**
		 * This function calculate the the weight for the location with the pi.
		 * and in the final insert the location to the list. 
		 */
		double walt=0,wlon=0,wlat=0,wsig=0;
		for (int j = 0; j < datapi.length; j++) {
			walt+=Double.parseDouble(listcomb.get(best[j]).getHead().getAlt())*datapi[j];
			wlon+=Double.parseDouble(listcomb.get(best[j]).getHead().getLon())*datapi[j];
			wlat+=Double.parseDouble(listcomb.get(best[j]).getHead().getLat())*datapi[j];
			wsig+=datapi[j];
		}
		walt/=wsig;
		wlat/=wsig;
		wlon/=wsig;
		listInput.get(i).getHead().setAlt(""+walt);
		listInput.get(i).getHead().setLat(""+wlat);
		listInput.get(i).getHead().setLon(""+wlon);
	}

}
