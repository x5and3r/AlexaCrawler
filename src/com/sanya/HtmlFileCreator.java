package com.sanya;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

//class for creating Html document from total parsed information that
//4 Alexa`s pages contain
public class HtmlFileCreator {
    private String countryCode;
    private String url;
    private BufferedWriter bufferedWriter;
    private Parser siteURLParser;
    private HtmlParser htmlParser;
    private String siteURLs;

    public HtmlFileCreator(String countryCode){
        this.countryCode = countryCode;
    }

    //this method creates table with full data in html document
    public void createTable() throws IOException {
        //gathering information from the Alexa's first page
        url = "http://www.alexa.com/topsites/countries;0/" + countryCode;
        bufferedWriter = new BufferedWriter(new FileWriter(countryCode + "_list.html"));

        siteURLParser = new SiteURLParser();
        siteURLParser.setParserStr("");
        siteURLParser.getSiteFromHtml(url);

        htmlParser = new HtmlParser(bufferedWriter);
        htmlParser.writeHtmlHeader();

        //gathering information from the Alexa's second page
        url = "http://www.alexa.com/topsites/countries;1/" + countryCode;
        siteURLParser.getSiteFromHtml(url);

        //gathering information from the Alexa's third page
        url = "http://www.alexa.com/topsites/countries;2/" + countryCode;
        siteURLParser.getSiteFromHtml(url);
        //gathering information from the Alexa's fourth page
        url = "http://www.alexa.com/topsites/countries;3/" + countryCode;
        siteURLs = siteURLParser.getSiteFromHtml(url);

        //using method for HtmlParser class object
        // to write gathered total information
        htmlParser.htmlSiteParser(siteURLs, bufferedWriter);
        htmlParser.htmlFileClose();

        System.out.println("File with name " + countryCode + "_list.html was created!");
    }
}