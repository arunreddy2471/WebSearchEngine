package com.searchengine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class SearchEngine {

    static Scanner scanner=new Scanner(System.in);
    static ArrayList<String> key = new ArrayList<String>();
	static Hashtable<String, Integer> numbers = new Hashtable<String, Integer>();
	static int n = 1;
	static int R;
	static int[] right;

    public SearchEngine(){
        System.out.println("*****************************************************");
        System.out.println("**************** WEB SEARCH ENGINE ******************");
        System.out.println("*****************************************************");
    }
    public static int search1(String pat, String txt) {
		BoyerMoore b = new BoyerMoore(pat);
		int offset = b.search(txt);
		return offset;
	}

    public static void searchEngineMain() throws IOException {
        File file=new File(FileLocationValues.location());
        System.out.println(file);
        Scanner scanner1=new Scanner(System.in);
        SearchEngine searchEngine=new SearchEngine();
        System.out.println("**************************** CRAWLING ******************************");
        System.out.println("\nEnter the URL\n");
        String url=scanner1.nextLine();
        //crawling
        WebCrawler.crawlAndConvert(url);

        Hashtable<String, Integer> occurrs = new Hashtable<String, Integer>();
		String choice = "y";

		do {
			System.out.println("\n***************************************************");
			System.out.println("\nENTER THE WORD TO BE SEARCHED: ");
			String p = scanner1.nextLine();
			System.out.println("***************************************************");
			long fileNumber = 0;
			int occur = 0;
			int pg = 0;

			try {
				File[] fileArray = file.listFiles();
				for (int i = 0; i < fileArray.length; i++) {
					// Searching the word given as an input.
					occur = SearchWord.wordSearch(fileArray[i], p);
					occurrs.put(fileArray[i].getName(), occur);
					if (occur != 0)
						pg++;
					fileNumber++;
				}

				if (pg == 0) {
					System.out.println("\n\n\n\n\n\n---------------------------------------------------");
					System.out.println("Word Entered not found!");
					System.out.println("Finding for similar words.....");
					/* using regex to find similar strings to pattern */
					SearchWord.altWord(p);
				} 
				else {
					//Webpages Ranking using merge sort 
					//merge sort is by collections.sort
					SearchEngine.hashing(occurrs, pg);
					Sorting.pageSort(occurrs,pg);
				}	
				System.out.println("\n\n Do you want to Search another word(y/n)??");
				choice = scanner1.nextLine();
			} catch(Exception e) {
				e.printStackTrace();
			}
		} while(choice.equals("y"));

		System.out.println("\n***************************************************\n");
		System.out.println("	:) THANK YOUß :)        ");
		System.out.println("\n***************************************************\n");



    }
    static void hashing(Hashtable<String, Integer> hashtable, Integer page){
		System.out.println("-----------------------------------------------------------------------------");
		System.out.printf("| %10s | %20s", "VALUE", "KEY");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------");
		hashtable.forEach(
				(k, v) -> {
					System.out.format("| %10s | %20s ",  v , k);
					System.out.println();
				});
		System.out.println("-----------------------------------------------------------------------------");
    }


    public static void main(String[] args) throws IOException {
        SearchEngine.searchEngineMain();
    }
}
