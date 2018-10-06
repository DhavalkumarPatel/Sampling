package compare;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


class Temp
{
	int full = 0;
	int sample = 0;
}

public class SampleCompare 
{
	public static void main(String args[]) throws Exception
	{
		sampleCompare();
	}
	
	public static void sampleCompare() throws Exception
	{
		int i = 0;
		HashMap<String, Temp> map = new HashMap<String, Temp>();
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Data\\\\Study\\Pre-PhD\\Workspace\\Sampling\\JoinSample\\input\\\\calculated\\Full_Join_Output_1537874167539_39628_E0.01.csv"))) 
		{
		    String line;
		    while ((line = br.readLine()) != null) 
		    {
		       String[] strArray = line.split(",");
		       
		       Double lat1 = Double.parseDouble(strArray[0]);
		       Double long1 = Double.parseDouble(strArray[1]);
		       Double lat2 = Double.parseDouble(strArray[2]);
		       Double long2 = Double.parseDouble(strArray[3]);
		       
		       String key = lat1.doubleValue() + "#" + long1.doubleValue() + "#" + lat2.doubleValue() + "#" + long2.doubleValue();
		       i++;
		       
		       if(map.containsKey(key))
		       {
		    	   Temp tmp = map.get(key);
		    	   tmp.full = tmp.full + 1;
		    	   map.put(key, tmp);
		       }
		       else
		       {
		    	   Temp tmp = new Temp();
		    	   tmp.full = 1;
		    	   map.put(key, tmp); 
		       }
		    }
		}
		System.out.println(i);
		
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Data\\Study\\Pre-PhD\\Workspace\\Sampling\\JoinSample\\output\\Vitorvic_Sample_Output_1538132023333.csv"))) 
		{
		    String line;
		    while ((line = br.readLine()) != null) 
		    {
		       String[] strArray = line.split(",");
		       
		       Double lat1 = Double.parseDouble(strArray[0]);
		       Double long1 = Double.parseDouble(strArray[1]);
		       Double lat2 = Double.parseDouble(strArray[2]);
		       Double long2 = Double.parseDouble(strArray[3]);
		       
		       String key = lat1.doubleValue() + "#" + long1.doubleValue() + "#" + lat2.doubleValue() + "#" + long2.doubleValue();
		       
		       if(map.containsKey(key))
		       {
		    	   Temp tmp = map.get(key);
		    	   tmp.sample = tmp.sample + 1;
		    	   map.put(key, tmp);
		       }
		       else
		       {
		    	   System.err.println(key);
		       }
		    }
		}
		
		File fout = new File("C:\\Data\\Study\\Pre-PhD\\Workspace\\Sampling\\JoinSample\\output\\Sample_Compare_Output" + System.currentTimeMillis() + ".csv");
		FileOutputStream fos = new FileOutputStream(fout);
	 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	 
	    Iterator<Entry<String, Temp>> it = map.entrySet().iterator();
	    while (it.hasNext()) 
	    {
		        Map.Entry<String, Temp> pair = (Map.Entry<String, Temp>)it.next();
		        if(pair.getValue().sample > 0)
		        {
		        	bw.write(pair.getKey() + "," + pair.getValue().full + "," + pair.getValue().sample);
		        	bw.newLine();
		        }
		}
	 
		bw.close();
	}
}
