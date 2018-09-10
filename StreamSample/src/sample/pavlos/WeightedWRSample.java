package sample.pavlos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

public class WeightedWRSample 
{
	public static List<Double> fastParallelWWR(List<Double> r1, Map<Double, Integer> r1Weight, int sampleSize)
	{
		Random random = new Random();
		
		class Temp implements Comparable<Temp>
		{
			Double tuple;
			Double key;
			
			@Override
			public int compareTo(Temp o) 
			{
				return key.compareTo(o.key) * -1;
			}
		}
		
		PriorityQueue<Temp> queue = new PriorityQueue<Temp>();
		
		for(Double r1Tuple: r1)
		{
			double weightOfR1Tuple = r1Weight.get(r1Tuple);
			double ui = random.nextFloat();
			double key = Math.pow(ui, (double) 1/weightOfR1Tuple);
			Temp tmp = new Temp();
			tmp.tuple = r1Tuple;
			tmp.key = key;
			queue.add(tmp);
		}

		List<Double> sample = new ArrayList<Double>();
		for(int i=0; i<sampleSize; i++)
		{
			sample.add(queue.poll().tuple);
		}
		
		return sample;
	}
}
