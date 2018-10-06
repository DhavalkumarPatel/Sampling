package compare;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class WeightCompare 
{
	public static void main(String args[]) throws Exception
	{
		Map<String, Integer> fullJoinWeights = new HashMap<String, Integer>();
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Data\\Study\\Pre-PhD\\Workspace\\Sampling\\JoinSample\\input\\calculated\\Full_Join_Output_1537874167539_39628_E0.01.csv"))) 
		{
		    String line;
		    while ((line = br.readLine()) != null) 
		    {
		       String[] strArray = line.split(",");
		       Double latitude = Double.parseDouble(strArray[0]);
		       Double longitude = Double.parseDouble(strArray[1]);
		       
		       String key = latitude.doubleValue() + "#" + longitude.doubleValue();
		       
		       if(fullJoinWeights.containsKey(key))
		       {
		    	   Integer weight = fullJoinWeights.get(key);
		    	   weight++;
		    	   fullJoinWeights.put(key, weight);
		       }
		       else
		       {
		    	   fullJoinWeights.put(key, 1);
		       }
		    }
		}
		
		Map<String, Integer> bsWeights = new HashMap<String, Integer>();
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Data\\Study\\Pre-PhD\\Workspace\\Sampling\\JoinSample\\input\\calculated\\BSWeightCalculation_1537925868991_86_E0.01.csv"))) 
		{
		    String line;
		    while ((line = br.readLine()) != null) 
		    {
		       String[] strArray = line.split(",");
		       Double latitude = Double.parseDouble(strArray[0]);
		       Double longitude = Double.parseDouble(strArray[1]);
		       Integer weight = Integer.parseInt(strArray[2]);
		       
		       String key = latitude.doubleValue() + "#" + longitude.doubleValue();
		       
		       if(bsWeights.containsKey(key))
		       {
		    	   Integer totalweight = bsWeights.get(key);
		    	   totalweight = totalweight + weight;
		    	   bsWeights.put(key, totalweight);
		       }
		       else
		       {
		    	   bsWeights.put(key, weight);
		       }
		    }
		}

		System.out.println("Done reading the inputs.");
		System.out.println("No of keys in FullJoin: " + fullJoinWeights.size());
		System.out.println("No of keys in BS approach: " + bsWeights.size());
		
	    Iterator<Entry<String, Integer>> it = bsWeights.entrySet().iterator();
	    while (it.hasNext())
	    {
	        Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>) it.next();
	        
	        String bsKey = pair.getKey();
	        Integer bsWeight = pair.getValue();
	        
	        if(fullJoinWeights.containsKey(bsKey))
	        {
	        	Integer fjWeight = fullJoinWeights.get(bsKey);
	        	if(fjWeight.intValue() != bsWeight.intValue())
	        	{
	        		System.err.println("Weight does not match, key: " + bsKey + " bsWeight: " + bsWeight + " fjWeight: " + fjWeight);
	        	}
	        }
	        else
	        {
	        	System.err.println("Key not present: " + bsKey);
	        }
	    }
	}
}
