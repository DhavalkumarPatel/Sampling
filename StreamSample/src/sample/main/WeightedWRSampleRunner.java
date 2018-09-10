package sample.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sample.pavlos.WeightedWRSample;

public class WeightedWRSampleRunner 
{
	public static void main(String args[]) throws Exception
	{
		List<Double> r1 = new ArrayList<Double>();
		Map<Double, Integer> r1Weight = new HashMap<Double, Integer>();
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Data\\Study\\Pre-PhD\\Workspace\\Sampling\\StreamSample\\data\\WeightedWRSampleInput_100_High.csv"))) 
		{
		    String line;
		    while ((line = br.readLine()) != null) 
		    {
		       String[] strArray = line.split(",");
		       r1.add(Double.parseDouble(strArray[0]));
		       r1Weight.put(Double.parseDouble(strArray[0]), Integer.parseInt(strArray[1]));
		    }
		}
		
		List<Double>  sample = WeightedWRSample.fastParallelWWR(r1, r1Weight, 10);
		Collections.sort(sample);
		for(Double d: sample)
		{
			System.out.println(d);
		}
	}
}
