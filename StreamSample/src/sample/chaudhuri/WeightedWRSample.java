package sample.chaudhuri;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.apache.commons.math3.distribution.BinomialDistribution;

public class WeightedWRSample 
{
	public static List<Double> blakBloxWR1(List<Double> r1, Map<Double, Integer> r1Weight, int sampleSize)
	{
		List<Double> sample = new ArrayList<Double>();
		int x = sampleSize;
		int D = 0;
		
		int W = 0;
		Iterator<Entry<Double, Integer>> it = r1Weight.entrySet().iterator();
	    while (it.hasNext()) 
	    {
	    	W = W + it.next().getValue();
	    }
		
		for(Double r1Tuple: r1)
		{
			if(x <= 0)
			{
				break;
			}
			
			int weightOfR1Tuple = r1Weight.get(r1Tuple);
			double prob = (double) weightOfR1Tuple / (W-D);
			BinomialDistribution bd = new BinomialDistribution(x, prob);
			int X = bd.sample();
			
			for(int i=0; i < X; i++)
			{
				sample.add(r1Tuple);
			}
			
			x = x - X;
			D = D + weightOfR1Tuple;
		}
		return sample;
	}
	
	public static List<Double> blakBloxWR2(List<Double> r1, Map<Double, Integer> r1Weight, int sampleSize)
	{
		Double[] r1Sample = new Double[sampleSize];
		Random random = new Random();
		int W = 0;
		for(Double r1Tuple: r1)
		{
			int weightOfR1Tuple = r1Weight.get(r1Tuple);
			W = W + weightOfR1Tuple;
			for(int j = 0 ; j < sampleSize; j++)
			{
				double prob = (double) weightOfR1Tuple / W;
				if(random.nextFloat() < prob) 
				{
					r1Sample[j] = r1Tuple;
				}
			}
		}
		return Arrays.asList(r1Sample);
	}
}

