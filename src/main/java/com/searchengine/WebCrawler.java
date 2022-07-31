package com.searchengine;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Pattern;

public class WebCrawler {
    //using hashset to avoid duplicates
    static HashSet<String> linksCrawled = new HashSet<>();

    public static void crawlAndConvert(String url) throws IOException {
        int max=100;
        crawl(url,max);
        htmlToTxt();
    }

    public static void crawl(String crawlURL, int upperLimit) throws IOException {
        linksCrawled.add(crawlURL);

        Document document = Jsoup.connect(crawlURL).get();
        String patternCrawl = ".*" + crawlURL.replaceAll("^(http|https)://","") + ".*";
        Elements pageLinks= document.select("a[href]");
        String url;
        long duplicateURL=0, notMatch=0;
        for(Element currentPage : pageLinks)
        {
            url = currentPage.attr("abs:href");
            if(linksCrawled.contains(url))
            {
                System.out.println(url+ " | URL previously visited");
                duplicateURL++;
            }
            //else if(!patternCrawl.matches(url))//change this if it does not work
            else if(!Pattern.matches(patternCrawl,url))
            {
                System.out.println(url+ " | URL does not matched the pattern requirement");
                notMatch++;
            }
            else{
                linksCrawled.add(currentPage.attr("abs:href"));
                System.out.println(url+ " | URL will be crawled");
                }
        }
        System.out.println("Duplicate URL's count : "+duplicateURL);
        System.out.println("irrelevant URL's count : "+notMatch);
        System.out.println("unique URL's count : "+linksCrawled.size());
    }



    public static void htmlToTxt() throws IOException {
        String cURL, text, fileLocation, fileNamePattern;
        fileNamePattern="[^a-zA-Z0-9_-]";
        fileLocation=FileLocationValues.location();
        Iterator<String> iterator=linksCrawled.iterator();
        while(iterator.hasNext())
        {
            cURL=iterator.next();
            Document document= Jsoup.connect(cURL).get();
            text=document.text();
            String txtFileName=document.title().replaceAll(fileNamePattern,"")+".txt";
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(fileLocation+txtFileName,true));
            bufferedWriter.write(cURL+" "+text);
            bufferedWriter.close();
        }
    }
}
