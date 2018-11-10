package com.mbas.csdmassignment;

import com.mbas.csdmassignment.entities.Feed;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FeedParser extends DefaultHandler {

    static final String ITEM = "item";
    static final String TITLE = "title";
    static final String DESCRIPTION = "description";
    static final String PUB_DATE = "pubDate";
    static final String IMAGE = "image";

    final URL url;

    public FeedParser(String feedUrl) {
        try {
            this.url = new URL(feedUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Feed> readFeed() {
        Feed feed = null;
        List<Feed> feedList = new ArrayList<>();
        try {
            boolean isFeedHeader = true;
            String description = "";
            String title = "";
            String image = "";
            String pubdate = "";

            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream in = read();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName()
                            .getLocalPart();
                    switch (localPart) {
                        case ITEM:
//                            if (isFeedHeader) {
//                                isFeedHeader = false;
//                                feed = new Feed(title, link, description, language,
//                                        copyright, pubdate);
//                            }
                            event = eventReader.nextEvent();
                            break;
                        case TITLE:
                            title = getCharacterData(event, eventReader);
                            break;
                        case DESCRIPTION:
                            description = getCharacterData(event, eventReader);
                            break;
                        case PUB_DATE:
                            pubdate = getCharacterData(event, eventReader);
                            break;
                        case IMAGE:
                            image = getCharacterData(event, eventReader);
                            break;
                    }
                } else if (event.isEndElement()) {
                    if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
                        feed = new Feed();
                        feed.setTitle(title);
                        feed.setDescription(description);
                        feed.setPubDate(pubdate);
                        feed.setImage(image);
                        feedList.add(feed);
                        event = eventReader.nextEvent();
                        continue;
                    }
                }

            }
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
        return feedList;
    }


    private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }


    private InputStream read() {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}