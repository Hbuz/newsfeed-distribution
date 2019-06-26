package com.mbas.newsfeeddist.parsers;

import com.mbas.newsfeeddist.entities.Feed;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {

    static final String ITEM = "item";
    static final String TITLE = "title";
    static final String DESCRIPTION = "description";
    static final String PUB_DATE = "pubDate";
    static final String ENCLOSURE = "enclosure";

    public List<Feed> readXmlFeeds(URL url) {
        Feed feed;
        List<Feed> feedList = new ArrayList<>();
        try {
            String description = "";
            String title = "";
            String image = "";
            String pubdate = "";

            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream in = url.openStream();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName()
                            .getLocalPart();
                    switch (localPart) {
                        case TITLE:
                            title = getCharacterData(eventReader);
                            break;
                        case DESCRIPTION:
                            description = eventReader.getElementText();
                            break;
                        case PUB_DATE:
                            pubdate = getCharacterData(eventReader);
                            break;
                        case ENCLOSURE:
                            image = event.asStartElement().getAttributeByName(new QName("url")).getValue();
                            break;
                    }
                } else if (event.isEndElement()) {
                    if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
                        feed = new Feed();
                        feed.setTitle(title);
                        feed.setDescription(description);
                        feed.setPubdate(pubdate);
                        feed.setImage(image);
                        feedList.add(feed);
                        continue;
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return feedList;
    }


    private String getCharacterData(XMLEventReader eventReader) throws XMLStreamException {
        String result = "";
        XMLEvent event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }


}
