package com.mbas.csdmassignment;

import com.mbas.csdmassignment.entities.Feed;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.sql.*;
import java.util.List;

@SpringBootApplication
public class CsdmAssignmentApplication {

    public static void main(String[] args) {

        SpringApplication.run(CsdmAssignmentApplication.class, args);

       /* TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("PDM!");
                //api fetch function
                fetchNewsFeed();
            }
        };
        Timer timer = new Timer();

        long delay = 1000L;
        long period = 1000L;
        timer.schedule(task, delay, period);*/

        List<Feed> feeds = fetchNewsFeed();
        storeNews(feeds);
    }


    public static List<Feed> fetchNewsFeed() {
        FeedParser parser = new FeedParser(
                "http://feeds.nos.nl/nosjournaal");
        List<Feed> feedList = parser.readFeed();
        return feedList;
    }


    public static void storeNews(List<Feed> feeds) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/newsfeed", "postgres", "secret")) {

            System.out.println("Connected to PostgreSQL database!");
            PreparedStatement st = null;
            for(Feed feed: feeds) {
                st = connection.prepareStatement("INSERT INTO public.feed (title, description, pubdate, image) VALUES (?, ?, ?, ?)");
                st.setString(1, feed.getTitle());
                st.setString(2, feed.getDescription());
                st.setString(3, feed.getPubdate());
                st.setString(4, feed.getImage());
                st.executeUpdate();
            }
            st.close();

//            Statement statement = connection.createStatement();
//            statement.executeUpdate(
//                    "INSERT INTO public.feed (title, description, pubDate, image) " +
//                            "VALUES (feed.getTitle(), feed.getDescription(), feed.getPubDate(), feed.getImage())");
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }


}
