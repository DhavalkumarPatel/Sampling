package utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import models.TuplePair;
import models.WeightedTuple;

public class OutputWriter 
{
	public static void writeJoinOutput(List<TuplePair> tuplePairs, String path) throws Exception
	{
		File fout = new File(path);
		FileOutputStream fos = new FileOutputStream(fout);
	 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	 
		for(TuplePair tuplePair: tuplePairs) 
		{
			bw.write(tuplePair.left.latitude.doubleValue() + "," 
					+ tuplePair.left.longitude.doubleValue() + "," 
					+ tuplePair.right.latitude.doubleValue() + "," 
					+ tuplePair.right.longitude.doubleValue());
			bw.newLine();
		}
	 
		bw.close();
	}
	
	public static void writeWeightedRelation(List<WeightedTuple> wts, String path) throws Exception
	{
		File fout = new File(path);
		FileOutputStream fos = new FileOutputStream(fout);
	 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	 
		for(WeightedTuple wt: wts) 
		{
			bw.write(wt.tuple.latitude.doubleValue() + "," 
					+ wt.tuple.longitude.doubleValue() + "," 
					+ wt.weight);
			bw.newLine();
		}
		bw.close();
	}
}
