package com.sanya;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static String countryCode;
    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            System.out.println("Country code according to ISO, ISO-3166-1 alpha 2:");
            String[]countryCodes = new BufferedReader(new InputStreamReader(System.in)).readLine().split(" ");
            creator(countryCodes);
        }
        else if(args.length > 0)
            creator(args);
    }

    //if we set one and more country code according to ISO country code, ISO­3166­1 alpha 2
    //we'll create object of HtmlFileCreator and call method to create html file with every
    //country code as parameter
    public static void creator (String [] array) throws IOException {
        for (int i = 0; i < array.length; i++) {
            countryCode = array[i];
            HtmlFileCreator file = new HtmlFileCreator(countryCode);
            file.createTable();
        }
    }
}