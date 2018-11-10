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

        fetchNewsFeed();
    }


    public static void fetchNewsFeed(){
        FeedParser parser = new FeedParser(
                "http://www.vogella.com/article.rss");
        List<Feed> feedList = parser.readFeed();
        for (Feed feed : feedList) {
            System.out.println(feed);
        }
    }


//    public static void storeNews(){
//        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "secret")) {
//
//            System.out.println("Connected to PostgreSQL database!");
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.appuser");
//            while (resultSet.next()) {
//                System.out.printf("Hey; "+ resultSet.getInt("id")+"  "+ resultSet.getString("login"));
//            }
//        } /*catch (ClassNotFoundException e) {
//            System.out.println("PostgreSQL JDBC driver not found.");
//            e.printStackTrace();
//        }*/ catch (SQLException e) {
//            System.out.println("Connection failure.");
//            e.printStackTrace();
//        }
//    }


}
