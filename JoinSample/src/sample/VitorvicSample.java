package sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.google.common.collect.MinMaxPriorityQueue;

import models.Tuple;
import models.TuplePair;
import models.WeightedTuple;
import utilities.Constants;
import utilities.InputReader;
import utilities.OutputWriter;
import utilities.Search;

public class VitorvicSample 
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
		
		MinMaxPriorityQueue<WeightedTuple> weightedWORSample = MinMaxPriorityQueue.maximumSize(Constants.SAMPLE_SIZE).create();
		Random randomForWWOR = new Random();
		for(WeightedTuple weightedTuple: r1Weighted)
		{
			if(weightedTuple.weight != 0)
			{	
				weightedTuple.key = Math.pow(randomForWWOR.nextFloat(), (double) 1/weightedTuple.weight);
				weightedWORSample.offer(weightedTuple);
			}
		}
		List<WeightedTuple> r1WWRSample = ChaudhuriSample.weightedWRSample(weightedWORSample, Constants.SAMPLE_SIZE);
		
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
	
		OutputWriter.writeJoinOutput(joinSample, "C:\\Data\\Study\\Pre-PhD\\Workspace\\Sampling\\JoinSample\\output\\Vitorvic_Sample_Output_" + System.currentTimeMillis() + ".csv");
		
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
	  	System.out.println("Vitorvic_Sample_Running_Time_In_Seconds: " + elapsedTime/1000);
  	}
}
