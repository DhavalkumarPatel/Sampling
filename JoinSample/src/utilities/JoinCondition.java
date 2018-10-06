package utilities;

import models.Tuple;

public class JoinCondition 
{
	private final static double E = 0.01;
	
	public static boolean isAMatch(Tuple left, Tuple right)
	{
		return isLatitudeMatches(left, right) && isLongitudeMatches(left, right);
	}
	
	public static boolean isLatitudeMatches(Tuple left, Tuple right)
	{
		return Math.abs(left.latitude.doubleValue() - right.latitude.doubleValue()) <= E;
	}
	
	public static boolean isLongitudeMatches(Tuple left, Tuple right)
	{
		return Math.abs(left.longitude.doubleValue() - right.longitude.doubleValue()) <= E;
	}
}
