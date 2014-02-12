package misc;

import java.lang.Math;

public class Random
{
	static public int weightedChoice(int[] weights)
	{
		if (weights.length == 1) return 0;
		
		int totalWeight = 0;
		for (int weight : weights) totalWeight += weight;
		
		int rf = (int)(Math.random()*totalWeight)+1;
		
		totalWeight = 0;
		for (int i=0; i<weights.length; i++)
			if (rf <= (totalWeight+=weights[i])) return i;
		
		return -1;
	}
	
	public static void main(String[] args)
	{
		int[] weights = {1241,234121,1230000,11000023,1125,25431};
		long start = System.currentTimeMillis();
		for (int i=0; i<1000; i++)
			Random.weightedChoice(weights);
		
		long elapsedTime = System.currentTimeMillis() - start;
		System.out.println("\n" + Double.toString(elapsedTime / 1000D));
	}
}
