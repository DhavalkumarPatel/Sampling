package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.commons.math3.distribution.BinomialDistribution;

import com.google.common.collect.MinMaxPriorityQueue;

import models.Tuple;
import models.TuplePair;
import models.WeightedTuple;
import utilities.Constants;
import utilities.InputReader;
import utilities.OutputWriter;
import utilities.Search;

public class ChaudhuriSample 
{
	public static void main(String[] args) throws Exception
	{
		joinSample();
	}
	
	public static void joinSample() throws Exception
	{
		long startTime = System.currentTimeMillis();

		List<Tuple> r1 = InputReader.readBaseRelation("C:\\Data\\Study\\Pre-PhD\\Workspace\\Sampling\\JoinSample\\input\\fromTrain_lat_long_sorted.csv");
		List<Tuple> r2 = InputReader.readBaseRelation("C:\\Data\\Study\\Pre-PhD\\Workspace\\Sampling\\JoinSample\\input\\fromTrain_lat_long_sorted.csv");
		Collections.sort(r2);
		
		List<WeightedTuple> r1Weighted = WeightCalculator.calculateWeight(r1, r2);
		List<WeightedTuple> r1WWRSample = weightedWRSample(r1Weighted, Constants.SAMPLE_SIZE);
		List<TuplePair> joinSample = new ArrayList<TuplePair>();		
		Random random = new Random();
		
		for(WeightedTuple r1WeightedTuple: r1WWRSample)
		{
			TuplePair tp = new TuplePair();
			tp.left = r1WeightedTuple.tuple;
			
			List<Integer> matches = Search.SearchJoinableSet(tp.left, r2);
			if(matches.size() > 0)
			{
				tp.right = r2.get(matches.get(random.nextInt(matches.size())));	
				joinSample.add(tp);
			}
		}

		OutputWriter.writeJoinOutput(joinSample, "C:\\Data\\Study\\Pre-PhD\\Workspace\\Sampling\\JoinSample\\output\\Chaudhuri_Sample_Output_" + System.currentTimeMillis() + ".csv");
		
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
      	System.out.println("Chaudhuri_Sample_Running_Time_In_Seconds: " + elapsedTime/1000);
	}
	
	public static List<WeightedTuple> weightedWRSample(List<WeightedTuple> r1Weighted, int sampleSize)
	{
		List<WeightedTuple> sample = new ArrayList<WeightedTuple>();
		int x = sampleSize;
		int D = 0;
		
		int W = 0;
	    for (WeightedTuple wt : r1Weighted) 
	    {
	    	W = W + wt.weight;
	    }
	    
		for(WeightedTuple r1Tuple: r1Weighted)
		{
			if(x <= 0)
			{
				break;
			}
			
			int weightOfR1Tuple = r1Tuple.weight;
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
	
	public static List<WeightedTuple> weightedWRSample(MinMaxPriorityQueue<WeightedTuple> queue, int sampleSize)
	{
		WeightedTuple[] arr = new WeightedTuple[queue.size()];
		arr = queue.toArray(arr);
		return weightedWRSample(Arrays.asList(arr), sampleSize);
	}	
}
