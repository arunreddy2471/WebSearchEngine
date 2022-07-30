package com.searchengine.WebSearchEngine;
/**
 * webpages rank use merge sort for ranking.
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;

public class Sorting {
	
	public static void pageSort(Hashtable<?, Integer> t,int occur)
	{
		 //Transfer as List and sort it
	       ArrayList<Map.Entry<?, Integer>> list1 = new ArrayList(t.entrySet());
	       Collections.sort(list1, new Comparator<Map.Entry<?, Integer>>(){

	         public int compare(Map.Entry<?, Integer> o1, Map.Entry<?, Integer> o2) {
	            return o1.getValue().compareTo(o2.getValue());
	        }});
	      
	       Collections.reverse(list1);
	       if(occur!=0) {
		       System.out.println("\n-----------------Web Page Ranking----------------\n");
		       
		       int n = 5;
		       int j = 0;
		       System.out.printf( "%-10s %s\n", "Rank", "Name and occurance" );
		       System.out.println("-------------------------------------------------");
				while (list1.size() > j && n>0){
					System.out.printf("\n%-10d| %s\n", j+1, list1.get(j));
					j++;
					n--;
				}
				System.out.println("\n-------------------------------------------------\n");
	       }
	}

}
