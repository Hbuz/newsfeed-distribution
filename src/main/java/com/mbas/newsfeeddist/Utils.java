package com.mbas.newsfeeddist;

import com.mbas.newsfeeddist.entities.Feed;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class Utils {


    public static List<Feed> fetchNewsFeed() {
        FeedParser parser = null;
        try {
            parser = new FeedParser(getPropValues("source")+"?"+getPropValues("format"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parser.readFeed();
    }


    public static void storeNews(List<Feed> feeds) {
        try (Connection connection = DriverManager.getConnection(getPropValues("spring.datasource.url"),
                getPropValues("spring.datasource.username"), getPropValues("spring.datasource.password"))) {

            System.out.println("Fetching new feeds");

            truncateTable(connection);

            PreparedStatement st = null;
            for (Feed feed : feeds) {
                st = connection.prepareStatement("INSERT INTO public.feed (title, description, pubdate, image) VALUES (?, ?, ?, ?)");
                st.setString(1, feed.getTitle());
                st.setString(2, feed.getDescription());
                st.setString(3, feed.getPubdate());
                st.setString(4, feed.getImage());
                st.executeUpdate();
            }
            st.close();

        } catch (SQLException | IOException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }


    static void truncateTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE public.feed");
        }
    }



    public static String getPropValues(String property) throws IOException {
        String result = "";
        InputStream inputStream = null;

        try {
            Properties prop = new Properties();
            String propFileName = "application.properties";
            inputStream = NewsFeedDistributionApplication.class.getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            result = prop.getProperty(property);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }

}
