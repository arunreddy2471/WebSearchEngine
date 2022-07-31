
package com.searchengine;

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

		//iterating and checking last char
		for (int i = 0; i < l1; i++) {
			char c1 = s1.charAt(i);
			for (int j = 0; j < l2; j++) {
				char c2 = s2.charAt(j);

				//check whether if last two char are equal
				if (c1 == c2) {
					//increment value of a by 1
					a[i + 1][j + 1] = a[i][j];
				} 
				else {
					int replaceWord = a[i][j]+1;
					int insertWord = a[i][j+1]+1;
					int deleteWord = a[i+1][j]+1;

					int MinValue = replaceWord > insertWord ? insertWord : replaceWord;
					MinValue = deleteWord > MinValue ? MinValue : deleteWord;
					a[i + 1][j + 1] = MinValue;
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
					SearchEngine.key.add(m.group());
				}
			}
			r.close();
			for(int p = 0; p<SearchEngine.key.size(); p++){
				SearchEngine.numbers.put(SearchEngine.key.get(p), findEditDistance(s1.toLowerCase(),SearchEngine.key.get(p).toLowerCase()));
			}
    	}     
    	catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}