package models;

public class WeightedTuple implements Comparable<WeightedTuple>
{
	public Tuple tuple;	
	public int weight;
	public Double key;
	
	@Override
	public int compareTo(WeightedTuple o) 
	{
		return key.compareTo(o.key) * -1;
	}
}
