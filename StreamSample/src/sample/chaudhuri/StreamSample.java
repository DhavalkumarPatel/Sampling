package sample.chaudhuri;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class StreamSample 
{
	private static int SAMPLE_SIZE = 4;
	public static void streamSample(List<Double> r1, List<Double> r2)
	{
		HashMap<Double, List<Double>> r1JoinableSet = getR1JoinableSet(r1, r2);
		
		List<Double> r1WeightedWRSample = weightedWithReplacementSample(r1, r1JoinableSet);
		
		for(Double r1SampledTuple: r1WeightedWRSample)
		{
			List<Double> r1SampledTupleJoinableSet = r1JoinableSet.get(r1SampledTuple);
			Double r2Tuple = r1SampledTupleJoinableSet.get(new Random().nextInt(r1SampledTupleJoinableSet.size()));
			System.out.println(r1SampledTuple + " <=> " + r2Tuple);
		}
	}
	
	public static HashMap<Double, List<Double>> getR1JoinableSet(List<Double> r1, List<Double> r2)
	{
		HashMap<Double, List<Double>> r1JoinableSet = new HashMap<Double, List<Double>>();
		for(Double r1Tuple: r1)
		{
			List<Double> r2JoinableTuples = new ArrayList<Double>();
			for(Double r2Tuple: r2)
			{
				if(joinCondition(r1Tuple, r2Tuple))
				{
					r2JoinableTuples.add(r2Tuple);
				}
			}
			r1JoinableSet.put(r1Tuple, r2JoinableTuples);
		}
		return r1JoinableSet;
	}
	
	public static List<Double> weightedWithReplacementSample(List<Double> r1, HashMap<Double, List<Double>> r1JoinableSet)
	{
		Double[] r1Sample = new Double[SAMPLE_SIZE];
		Random randObj = new Random();
		int totalWeight = 0;
		for(Double r1Tuple: r1)
		{
			int weightOfR1Tuple = r1JoinableSet.get(r1Tuple).size();
			totalWeight = totalWeight + weightOfR1Tuple;
			for(int j = 0 ; j < SAMPLE_SIZE; j++)
			{
				double prob = (double) weightOfR1Tuple / totalWeight;
				if(new Random().nextFloat() <= prob) 
				{
					r1Sample[j] = r1Tuple;
				}
			}
		}
		return Arrays.asList(r1Sample);
	}
	
	public static boolean joinCondition(Double r1Tuple, Double r2Tuple)
	{
		return Math.abs(r1Tuple - r2Tuple) <= 1;
	}
}
