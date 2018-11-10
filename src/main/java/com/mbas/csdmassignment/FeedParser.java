package com.mbas.csdmassignment;

import com.mbas.csdmassignment.entities.Feed;
import com.mbas.csdmassignment.parsers.XMLParser;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FeedParser extends DefaultHandler {

    final URL url;

    public FeedParser(String feedUrl) {
        try {
            this.url = new URL(feedUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Feed> readFeed() {
        List<Feed> feedList = new ArrayList<>();
        try {
            String contentType = "";
            try {
                contentType = getContentType();
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (contentType) {
                case "xml":
                    XMLParser xmlParser = new XMLParser();
                    feedList = xmlParser.readXmlFeeds(url);
                    break;
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return feedList;
    }



    private String getContentType() throws IOException {
        String contentType = "";
        String contentTypeFull = url.openConnection().getContentType();
        if (contentTypeFull.contains("xml")) {
            contentType = "xml";
        }
        return contentType;
    }

}