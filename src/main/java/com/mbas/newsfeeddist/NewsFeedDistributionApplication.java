package com.mbas.newsfeeddist;

import com.mbas.newsfeeddist.entities.Feed;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.*;
import java.util.*;

@SpringBootApplication
public class NewsFeedDistributionApplication {

    public static void main(String[] args) {

        SpringApplication.run(NewsFeedDistributionApplication.class, args);

        TimerTask task = new TimerTask() {
            public void run() {
                List<Feed> feeds = Utils.fetchNewsFeed();
                Utils.storeNews(feeds);
            }
        };
        Timer timer = new Timer();

        long period = 0;
        try {
            period = Long.parseLong(Utils.getPropValues("interval"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        timer.schedule(task, 0, period);
    }

}
