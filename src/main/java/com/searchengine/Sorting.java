package com.searchengine;
/**
 * webpages rank use merge sort for ranking.
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;

public class Sorting {
	
	public static void pageSort(Hashtable<?, Integer> hastTab,int occur)
	{
		 //Transfering web links to List and sorting it
	       ArrayList<Map.Entry<?, Integer>> list1 = new ArrayList(hastTab.entrySet());
	       Collections.sort(list1, new Comparator<Map.Entry<?, Integer>>(){

	         public int compare(Map.Entry<?, Integer> obj1, Map.Entry<?, Integer> obj2) {
	            return obj1.getValue().compareTo(obj2.getValue());
	        }});
	      
	       Collections.reverse(list1);
	       if(occur!=0) {
		       System.out.println("\n-----------------Web Page Ranking----------------\n");
		       
		       int RankCount = 5;
		       int i = 0;
		       System.out.printf( "%-10s %s\n", "Rank", "Web Link and occurance" );
		       System.out.println("-------------------------------------------------");
				while (list1.size() > i && RankCount>0){
					System.out.printf("\n%-10d| %s\n", i+1, list1.get(i));
					i++;
					RankCount--;
				}
				System.out.println("\n-------------------------------------------------\n");
	       }
	}

}
