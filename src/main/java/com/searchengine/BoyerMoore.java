package com.searchengine;



public class BoyerMoore {
    private final int r;     // the radix
    private int[] a;     // the bad-character skip array

    private String patstring;     

    // pattern provided as a string
    public BoyerMoore(String patstring) {
        this.r = 100000;
        this.patstring = patstring;

        // position of rightmost occurrence of i in the pattern
        a = new int[r];
        for (int i=0;i<r;i++)
            a[i] = -1;
        for (int j=0;j<patstring.length();j++)
            a[Character.toLowerCase(patstring.charAt(j))]=j;
    }

    // return offset of first match; L2 if no match
    public int search(String s) {
        int L1=patstring.length();
        int L2=s.length();
        int skip;
        for (int i = 0; i <=(L2-L1); i+= skip) {
            skip = 0;
            for (int j = L1-1; j >= 0; j--) {
                if (Character.toLowerCase(patstring.charAt(j))!=Character.toLowerCase(s.charAt(i+j))) {
                    skip=Math.max(1,j-a[Character.toLowerCase(s.charAt(i+j))]);
                    break;
                }
            }
            if (skip == 0) return i;    // found
        }
        return L2;                       // not found
    }

}