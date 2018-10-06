package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import models.Tuple;

public class InputReader 
{
	public static List<Tuple> readBaseRelation(String path) throws Exception
	{
		List<Tuple> relation = new ArrayList<Tuple>();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) 
		{
		    String line;
		    while ((line = br.readLine()) != null) 
		    {
		       String[] strArray = line.split(",");
		       relation.add(new Tuple(Double.parseDouble(strArray[0]), Double.parseDouble(strArray[1])));
		    }
		}
		return relation;
	}
}
