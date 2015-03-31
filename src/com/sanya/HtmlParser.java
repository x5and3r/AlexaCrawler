package com.sanya;

import java.io.BufferedWriter;
import java.io.IOException;

//class for creating Html document from parsed information that
//Alexa`s page with 25 sites contains
public class HtmlParser {
    private String[] siteURLs;
    private int i;
    private int siteCount = 1;
    private BufferedWriter html;
    private Parser globalRankParser;
    private String parseStr;

    private final String htmlHeader = "<!DOCTYPE html>\n" + "<head>\n" +
            "<style>\n" + "table, th, td {\n" + "    border: 1px solid black;\n" +
            "    border-collapse: collapse;\n" + "}\n" + "th, td {\n" + "    padding: 5px;" +
            "}\n" + "</style>\n" + "</head\n\n" + "<body>\n\n" + "<table style=\"width:100%\">\n\n" +
            "<tr>\n" + "    <th>Country rank</th>\n" + "    <th>Site URL</th>\n" +
            "    <th>Global rank</th>\n" + "    <th>Alexa's page</th>\n";
    private final String alexaTag = "http://www.alexa.com/siteinfo/";

    public HtmlParser(BufferedWriter bufferedWriter){
        this.html = bufferedWriter;
    }

    //method that creates html document
    public void htmlSiteParser(String sURLs, BufferedWriter bufferedWriter) {
        try {
            siteURLs = sURLs.split("\n");
            html = bufferedWriter;
            globalRankParser = new GlobalRankParser();
            if (globalRankParser.getRankFromHtml(alexaTag + siteURLs[0].toLowerCase()) == "-") {
                parseStr = globalRankParser.getRankFromHtml(alexaTag + siteURLs[0].toLowerCase());
                for (i = 0; i < siteURLs.length; i++) {
                    globalRankParser.setParserStr("");
                    create(i);
                }
            }
            else {
                for (i = 0; i < siteURLs.length; i++) {
                    globalRankParser.setParserStr("");
                    //parsing alexa`s pages for getting global rank for each site in the country
                    parseStr = globalRankParser.getRankFromHtml(alexaTag + siteURLs[i].toLowerCase());
                    create(i);
                }
            }
        }
        catch (IOException e) {
            e.getStackTrace();
        }
    }

    //writing gathered information to html document
    private void create(int i) throws IOException{
            html.write("<tr>" + "<td>" + siteCount + " </td>\n" +
                    "<td>" + siteURLs[i] + " </td>\n" +
                    "<td>" + parseStr + " </td>\n" +
                    "<td>" + alexaTag + siteURLs[i].toLowerCase() + " </td>\n");
            siteCount++;
    }

    //method for writing default html header
    public void writeHtmlHeader() throws IOException {
        html.write(htmlHeader);
    }

    //method to close html document after writing
    public void htmlFileClose() throws IOException {
        html.write("</table>\n\n" + "</body>\n" + "</html>");
        html.close();
    }
}