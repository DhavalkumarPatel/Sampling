package models;

public class Tuple implements Comparable<Tuple>
{
	public Double latitude;
	public Double longitude;
	
	@Override
	public int compareTo(Tuple o) 
	{
		if(latitude.compareTo(o.latitude) == 0)
		{
			return longitude.compareTo(o.longitude);
		}
		return latitude.compareTo(o.latitude);
	}
	
	public Tuple(Double latitude, Double longitude)
	{
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
