package com.sanya;

import java.io.BufferedReader;
import java.io.IOException;

//class for getting global rank from Alexa's html page
public class GlobalRankParser implements Parser {
    private String uriStr;
    private final String startTag = "<img class='img-inline ' src='/images/icons/globe-sm.jpg' " +
            "title='Global rank icon' alt='Global rank icon'><strong class=\"metrics-data align-vmiddle\">";
    private final String endTag = "</strong>";
    private BufferedReader bufferedReader;
    private String htmlStr;
    private String parserStr;

    public void setParserStr(String parserStr) {
        this.parserStr = parserStr;
    }

    @Override
    public String getRankFromHtml(String uri) throws IOException {
        this.uriStr = uri;
        bufferedReader = new URLConnector(uriStr).urlToBuffer();
        if (bufferedReader == null) {
            parserStr = "-";
        }
        else {
            //reading html page's lines, if find string with start tag, we'll parse it
            //and get global rank
            while ((htmlStr = bufferedReader.readLine()) != null) {
                if (htmlStr.startsWith(startTag)) {
                    parserStr += htmlStr.substring(startTag.length(), htmlStr.length() - endTag.length()) + "\n";
                    break;
                }
            }
        }
        return parserStr;
    }

    @Override
    public String getSiteFromHtml(String urlStr) throws IOException {
        return null;
    }
}