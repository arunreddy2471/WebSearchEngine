package com.searchengine;

public class FileLocationValues {
     public static String location(){
         String fileLocation;
         String os= System.getProperty("os.name");
         if(os.contains("Windows"))
         {
             return fileLocation=System.getProperty("user.dir") + "\\textFiles\\";

         }
         else
         {
             return fileLocation=System.getProperty("user.dir") + "/textFiles/";

         }
     }

}
