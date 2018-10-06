package sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Tuple;
import models.WeightedTuple;
import utilities.InputReader;
import utilities.OutputWriter;
import utilities.Search;

public class WeightCalculator 
{
	public static void main(String args[]) throws Exception
	{
		long startTime = System.currentTimeMillis();

		List<Tuple> r1 = InputReader.readBaseRelation("C:\\Data\\Study\\Pre-PhD\\Workspace\\Sampling\\JoinSample\\input\\fromTrain_lat_long_sorted.csv");
		List<Tuple> r2 = InputReader.readBaseRelation("C:\\Data\\Study\\Pre-PhD\\Workspace\\Sampling\\JoinSample\\input\\fromTrain_lat_long_sorted.csv");
		Collections.sort(r2);
		
		List<WeightedTuple> weightedRelation = calculateWeight(r1, r2);
		OutputWriter.writeWeightedRelation(weightedRelation, "C:\\Data\\Study\\Pre-PhD\\Workspace\\Sampling\\JoinSample\\output\\BSWeightCalculation_" + System.currentTimeMillis() + ".csv");
		
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
      	System.out.println("CalculateWeight_Running_Time_In_Seconds: " + elapsedTime/1000);
	}
	
	public static List<WeightedTuple> calculateWeight(List<Tuple> r1, List<Tuple> r2) throws Exception
	{
		List<WeightedTuple> list = new ArrayList<WeightedTuple>();
		
		int i = 0;
		for(Tuple r1Tuple: r1)
		{
			List<Integer> matches = Search.SearchJoinableSet(r1Tuple, r2);
					
			WeightedTuple wt = new WeightedTuple();
			wt.tuple = r1Tuple;
			wt.weight = matches.size();
			list.add(wt);
			
			System.out.println("calculateWeight :" + i);
			i++;
		}

		return list;
	}
}
