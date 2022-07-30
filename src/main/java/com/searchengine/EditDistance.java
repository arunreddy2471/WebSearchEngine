package com.searchengine.WebSearchEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;


public class EditDistance {

	public static int findEditDistance(String s1, String s2)
	{
		int l1 = s1.length();
		int l2 = s2.length();
	 
		int[][] a = new int[l1+1][l2+1];
 
		for (int i=0;i<=l1;i++) {
			a[i][0] = i;
		}
	 
		for (int j=0;j<=l2;j++) {
			a[0][j] = j;
		}
	 
		//iterate though, and check last char
		for (int i = 0; i < l1; i++) {
			char c1 = s1.charAt(i);
			for (int j = 0; j < l2; j++) {
				char c2 = s2.charAt(j);
	 
				//if last two chars equal
				if (c1 == c2) {
					//update a value for +1 length
					a[i + 1][j + 1] = a[i][j];
				} 
				else {
					int replace = a[i][j]+1;
					int insert = a[i][j+1]+1;
					int delete = a[i+1][j]+1;
	 
					int min = replace > insert ? insert : replace;
					min = delete > min ? min : delete;
					a[i + 1][j + 1] = min;
				}
			}
		}
		return a[l1][l2];		
	}
	

	public static void findWord(File source,int fileNumber,Matcher m,String s1)
	{
		try
    	{
			BufferedReader r = new BufferedReader(new FileReader(source));
			String l=null;
			
			while ((l = r.readLine()) != null){
				m.reset(l);
				while (m.find()) {
					WebSearchEngine.key.add(m.group());
				}
			}
			r.close();
			for(int p = 0; p<WebSearchEngine.key.size(); p++){
				WebSearchEngine.numbers.put(WebSearchEngine.key.get(p), findEditDistance(s1.toLowerCase(),WebSearchEngine.key.get(p).toLowerCase()));
			}
    	}     
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
	}
}
