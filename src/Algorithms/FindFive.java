package Algorithms;

public class FindFive {
	/**
	 * This function receive arrays with double between 0-1 and return the 5 max
	 * @param arr
	 * @return best
	 * 
	 */
	public  int[] find5best(double[] arr,int imagine){
		int[] best=new int[imagine];
		for (int i = 0; i < best.length; i++) {
			best[i]=i;
		}
		best=min(arr,best);
		for (int i = imagine-1; i < arr.length; i++) {
			/**
			 * Always we keep the most low in index 0.
			 * when it's find one with more value than the min it's put it in index 0.
			 * Send it to function min to set up index 0 with the most low.
			 */
			if(arr[best[0]]<arr[i]){
				best[0]=i;
				best=min(arr,best);
			}

		}
		return best;

	}
	public static int[] min(double[] arr,int[] best){
		/**
		 * Put the most low value in index 0;
		 */
		double min=arr[best[0]];
		for (int i = 1; i < best.length; i++) {
			if(min>arr[best[i]]){
				min=arr[best[i]];
				int swap=best[i];
				best[i]=best[0];
				best[0]=swap;
			}

		}
		return best;
	}
}
