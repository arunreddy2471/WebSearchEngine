package com.searchengine;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SearchEngine {

    static Scanner scanner=new Scanner(System.in);

    public SearchEngine(){
        System.out.println("*****************************************************");
        System.out.println("**************** WEB SEARCH ENGINE ******************");
        System.out.println("*****************************************************");
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



    }

    public static void main(String[] args) throws IOException {
        SearchEngine.searchEngineMain();
    }
}
