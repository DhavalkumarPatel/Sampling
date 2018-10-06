package utilities;

import java.util.ArrayList;
import java.util.List;

import models.Tuple;

public class Search 
{
	public static List<Integer> SearchJoinableSet(Tuple tuple, List<Tuple> relation)
	{
		List<Integer> matches = new ArrayList<Integer>();
		
		int matchedIndex = binarySearch(relation, 0, relation.size()-1, tuple);
		if(matchedIndex == -1)
		{
			return matches;
		}
		
		for(int i = matchedIndex; i >= 0 ; i--)
		{
			if(JoinCondition.isLatitudeMatches(tuple, relation.get(i)))
			{
				if(JoinCondition.isLongitudeMatches(tuple, relation.get(i)))
				{
					matches.add(i);
				}
			}
			else
			{
				break;
			}
		}
		
		for(int i = matchedIndex+1; i < relation.size() ; i++)
		{
			if(JoinCondition.isLatitudeMatches(tuple, relation.get(i)))
			{
				if(JoinCondition.isLongitudeMatches(tuple, relation.get(i)))
				{
					matches.add(i);
				}
			}
			else
			{
				break;
			}
		}
		
		return matches;
	}
	
	public static int binarySearch(List<Tuple> arr, int l, int r, Tuple toSearch) 
    { 
        if (r>=l) 
        { 
            int mid = l + (r - l)/2; 
    
            if (arr.get(mid).latitude.doubleValue() == toSearch.latitude.doubleValue() 
            		&& arr.get(mid).longitude.doubleValue() == toSearch.longitude.doubleValue()) 
               return mid; 
  
            if (isGreaterThan(arr.get(mid), toSearch)) 
               return binarySearch(arr, l, mid-1, toSearch); 
  
            return binarySearch(arr, mid+1, r, toSearch); 
        } 
  
        return -1; 
    } 
    
    public static boolean isGreaterThan(Tuple left, Tuple right) {
    	
    	if(left.latitude.doubleValue() == right.latitude.doubleValue())
    	{
    		return left.longitude.doubleValue() > right.longitude.doubleValue();
    	}
    	else
    	{
    		return left.latitude.doubleValue() > right.latitude.doubleValue();
    	}
    }
}
