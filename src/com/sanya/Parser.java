package com.sanya;

import java.io.IOException;

//interface for being implemented in classes to get global ranks and urls
public interface Parser {
    public void setParserStr(String parserStr);

    public String getRankFromHtml(String urlStr) throws IOException;
    public String getSiteFromHtml(String urlStr) throws IOException;
}