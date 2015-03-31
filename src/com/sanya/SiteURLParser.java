package com.sanya;

import java.io.BufferedReader;
import java.io.IOException;

//class for getting site URL from Alexa's html page
public class SiteURLParser implements Parser {
    private String uriStr;
    private final String startTag = "<a href=\"/siteinfo/";
    private final String endTag = "</a>";
    private BufferedReader bufferedReader;
    private String htmlStr;
    private String parserStr;
    private int start;
    private int end;
    private int count;

    public void setParserStr(String parserStr) {
        this.parserStr = parserStr;
    }

    @Override
    public String getRankFromHtml(String urlStr) throws IOException {
        return null;
    }

    @Override
    public String getSiteFromHtml(String url) throws IOException {
        this.uriStr = url;
        bufferedReader = new URLConnector(uriStr).urlToBuffer();
        //reading html page's lines, if find string with start tag, we will parse it
        //and get site URLs 25 times
        while ((htmlStr = bufferedReader.readLine()) != null && count < 25) {
            if (htmlStr.startsWith(startTag)) {
                start = startTag.length() + 1 + (htmlStr.length() - endTag.length() - startTag.length()) / 2;
                end = htmlStr.length() - endTag.length();
                parserStr += htmlStr.substring(start, end) + "\n";
                count++;
            }
        }
        count = 0;
        return parserStr;
    }
}