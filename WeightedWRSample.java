package sampling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WeightedWRSample 
{
	private static int SAMPLE_SIZE = 5;
	public static List<Double> blakBloxWR2(List<Double> r1, Map<Double, Integer> r1Weight)
	{
		Double[] r1Sample = new Double[SAMPLE_SIZE];
		int W = 0;
		for(Double r1Tuple: r1)
		{
			int weightOfR1Tuple = r1Weight.get(r1Tuple);
			W = W + weightOfR1Tuple;
			for(int j = 0 ; j < SAMPLE_SIZE; j++)
			{
				double prob = (double) weightOfR1Tuple / W;
				if(new Random().nextFloat() <= prob) 
				{
					r1Sample[j] = r1Tuple;
				}
			}
		}
		return Arrays.asList(r1Sample);
	}
	
	public static void main(String[] args)
	{
		List<Double> r1 = new ArrayList<Double>();
		r1.add(1.0);
		r1.add(2.0);
		r1.add(3.0);
		r1.add(4.0);
		r1.add(5.0);
		r1.add(6.0);
		r1.add(7.0);
		r1.add(8.0);
		r1.add(9.0);
		r1.add(10.0);
		
		Map<Double, Integer> r1Weight = new HashMap<Double, Integer>();
		r1Weight.put(1.0, 1);
		r1Weight.put(2.0, 1);
		r1Weight.put(3.0, 1);
		r1Weight.put(4.0, 1);
		r1Weight.put(5.0, 1);
		r1Weight.put(6.0, 1);
		r1Weight.put(7.0, 1);
		r1Weight.put(8.0, 1);
		r1Weight.put(9.0, 1);
		r1Weight.put(10.0, 1);
		
		List<Double> r1Sample = blakBloxWR2(r1, r1Weight);
		for(Double s: r1Sample)
		{
			System.out.println(s);
		}
	}
}

