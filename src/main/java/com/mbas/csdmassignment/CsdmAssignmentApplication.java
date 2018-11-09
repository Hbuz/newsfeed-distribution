package com.mbas.csdmassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.Timer;
import java.util.TimerTask;

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


    public static String fetchNewsFeed(){
        HttpURLConnection conn=null;
        BufferedReader reader=null;
        StringBuilder strBuf = new StringBuilder();

        try{
            URL url = new URL("http://feeds.nos.nl/nosjournaal?format=json");
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                        + conn.getResponseCode());
            }

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String output = null;
            while ((output = reader.readLine()) != null)
                strBuf.append(output);
        }catch(MalformedURLException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        finally
        {
            if(reader!=null)
            {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(conn!=null)
            {
                conn.disconnect();
            }
        }
        System.out.println("CIAO:   "+ strBuf.toString());
        return strBuf.toString();
    }


    public static void storeNews(){
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "secret")) {

            System.out.println("Connected to PostgreSQL database!");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.appuser");
            while (resultSet.next()) {
                System.out.printf("Hey; "+ resultSet.getInt("id")+"  "+ resultSet.getString("login"));
            }
        } /*catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC driver not found.");
            e.printStackTrace();
        }*/ catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }

}
