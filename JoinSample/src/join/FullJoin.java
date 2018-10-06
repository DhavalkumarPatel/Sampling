package join;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import models.Tuple;
import utilities.InputReader;
import utilities.JoinCondition;

public class FullJoin 
{
	public static void main(String[] args) throws Exception
	{
		fullJoin();
	}
	
	public static void fullJoin() throws Exception
	{
		long startTime = System.currentTimeMillis();

		List<Tuple> r1 = InputReader.readBaseRelation("C:\\Data\\Study\\Pre-PhD\\Workspace\\Sampling\\JoinSample\\input\\fromTrain_lat_long_sorted.csv");
		List<Tuple> r2 = InputReader.readBaseRelation("C:\\Data\\Study\\Pre-PhD\\Workspace\\Sampling\\JoinSample\\input\\fromTrain_lat_long_sorted.csv");
		File fout = new File("C:\\Data\\Study\\Pre-PhD\\Workspace\\Sampling\\JoinSample\\output\\Full_Join_Output_" + System.currentTimeMillis() + ".csv");
		
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

		int i = 0;
		for(Tuple r1Tuple: r1)
		{
			for(Tuple r2Tuple: r2) 
			{
				if(JoinCondition.isAMatch(r1Tuple, r2Tuple))
				{
					bw.write(r1Tuple.latitude.doubleValue() + "," 
							+ r1Tuple.longitude.doubleValue() + "," 
							+ r2Tuple.latitude.doubleValue() + "," 
							+ r2Tuple.longitude.doubleValue());
					bw.newLine();
				}
			}
			i++;
			System.out.println(i);
		}
		bw.close();
		
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
      	System.out.println("Full_Join_Running_Time_In_Seconds: " + elapsedTime/1000);
	}
}
