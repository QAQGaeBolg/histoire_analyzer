<<<<<<< HEAD
<<<<<<< HEAD
package edu.sustech.backend.scraper;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.sustech.backend.scraper.jsonObj.JRepo;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class github_scraper {

    DataBaseController controller = new DataBaseController();
    Gson gson = new Gson();
    String token = "gfzum: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";

    public static void main(String[] args) {
        String test = "2021-01-01T00:00:00Z";
        for (int i = 0; i < 10; i++) {
            System.out.println(timeAdd(test, 2) );
            test = timeAdd(test, 2);
            System.out.println(timeCheck(test));
        }
    }

    public void repoScrapeByStars(String framework){

        URL url = null;
        outer : for (int i = 1; i <= 10; i++) {
            String s = String.format("https://api.github.com/search/repositories" +
                    "?q=" + framework + "+language:java&sort=stars" +
                    "&per_page=100&page=" + i);
            try {
                url = new URL(s);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                byte[] encodedAuth = Base64.encodeBase64(token.getBytes(StandardCharsets.UTF_8));
                String authHeaderValue = "Basic " + new String(encodedAuth);
                conn.setRequestProperty("Authorization", authHeaderValue);
                conn.connect();

                StringBuilder content = new StringBuilder("");
                BufferedReader bi = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = null;
                while ((line = bi.readLine()) != null){
                    content.append(line);
                }

                JsonObject result = JsonParser.parseString(content.toString()).getAsJsonObject();
//
            for (JsonElement items : result.get("items").getAsJsonArray()) {
                JRepo repo = gson.fromJson(items, JRepo.class);
                repo.setFramework(framework);
                controller.insertRepo(repo);
            }
            controller.printCnt();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void repoScrapeByTime(String framework) {

        int cnt = 0;
        int total_cnt = 1;
        int limit = (framework.equals("Spring") ? 50000: 20000);
        String created = "2012-01-01T00:00:00Z";
//        String created = "2019-05-08T00:00:00Z";
        String last = null;
        URL url = null;
        do {
            for (int i = 1; i <= 10; i++) {
                String s = String.format("https://api.github.com/search/repositories" +
                        "?q=" + framework + "+language:java+created:>" + created +
                        "&sort=created&order=asc&per_page=100&page=" + i);
                try {
                    url = new URL(s);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
//            conn.setRequestProperty("Accept", "");

                    byte[] encodedAuth = Base64.encodeBase64(token.getBytes(StandardCharsets.UTF_8));
                    String authHeaderValue = "Basic " + new String(encodedAuth);
                    conn.setRequestProperty("Authorization", authHeaderValue);

                    conn.connect();

                    int responseCode = conn.getResponseCode();
                    String responseMessage = conn.getResponseMessage();

//            System.out.println(responseCode);
//            System.out.println(responseMessage);

                    StringBuilder content = new StringBuilder("");
                    BufferedReader bi = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = null;
                    while ((line = bi.readLine()) != null) {
                        content.append(line);
                    }

                    JsonObject result = JsonParser.parseString(content.toString()).getAsJsonObject();
                    total_cnt = result.get("total_count").getAsInt();

//                    int page_nums = 0;
                    if (result.get("items").getAsJsonArray().size() == 0) {
                        created = last;
                        break;
                    }
                    for (JsonElement item : result.get("items").getAsJsonArray()) {
                        JRepo repo = gson.fromJson(item, JRepo.class);
                        repo.setFramework(framework);
                        controller.insertRepo(repo);
                        last = repo.getCreated_at();
                        cnt++;
//                        page_nums++;
                    }
                    controller.printCnt();
                    System.out.println("total: " + total_cnt + " cnt: " + cnt);
                    System.out.println("next time: " + created );
//                    if (page_nums < 100 || i == 10) {
//                        created = last;
//                        break;
//                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            // scrap by time
            if (framework.equals("Spring")) created = timeAdd(created, 1);
            if (framework.equals("Spark")) created = timeAdd(created, 2);
        } while( (cnt < limit) && (timeCheck(created)));
    }

    //type = 1: Spring, type = 2: Spark
    private static String timeAdd(String time, int type){
        String created = "2010-01-01T00:00:00Z";
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = parser.parse(time);
            int year = parse.getYear() + 1900;
            int month = parse.getMonth() + 1;

            if (type == 1) month += 3;
            else month += 6;

            if (month > 12){
                month -= 12;
                year += 1;
            }


            String Month = String.valueOf(month);
            if (Month.length() == 1) Month = "0" + Month;
            return year + "-" + Month + "-" + "01T00:00:00Z";

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean timeCheck(String time){
        int year = Integer.parseInt(time.substring(0, 4));
        int month = Integer.parseInt(time.substring(5,7));

        int date = year * 100 + month;
        return date <= 202204;
    }

=======
package edu.sustech.backend.scraper;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.sustech.backend.scraper.jsonObj.JRepo;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class github_scraper {

    DataBaseController controller = new DataBaseController();
    Gson gson = new Gson();
    String token = "gfzum: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";

    public static void main(String[] args) {
        String test = "2021-01-01T00:00:00Z";
        for (int i = 0; i < 10; i++) {
            System.out.println(timeAdd(test, 2) );
            test = timeAdd(test, 2);
            System.out.println(timeCheck(test));
        }
    }

    public void repoScrapeByStars(String framework){

        URL url = null;
        outer : for (int i = 1; i <= 10; i++) {
            String s = String.format("https://api.github.com/search/repositories" +
                    "?q=" + framework + "+language:java&sort=stars" +
                    "&per_page=100&page=" + i);
            try {
                url = new URL(s);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                byte[] encodedAuth = Base64.encodeBase64(token.getBytes(StandardCharsets.UTF_8));
                String authHeaderValue = "Basic " + new String(encodedAuth);
                conn.setRequestProperty("Authorization", authHeaderValue);
                conn.connect();

                StringBuilder content = new StringBuilder("");
                BufferedReader bi = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = null;
                while ((line = bi.readLine()) != null){
                    content.append(line);
                }

                JsonObject result = JsonParser.parseString(content.toString()).getAsJsonObject();
//
            for (JsonElement items : result.get("items").getAsJsonArray()) {
                JRepo repo = gson.fromJson(items, JRepo.class);
                repo.setFramework(framework);
                controller.insertRepo(repo);
            }
            controller.printCnt();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void repoScrapeByTime(String framework) {

        int cnt = 0;
        int total_cnt = 1;
        int limit = (framework.equals("Spring") ? 50000: 20000);
        String created = "2012-01-01T00:00:00Z";
//        String created = "2019-05-08T00:00:00Z";
        String last = null;
        URL url = null;
        do {
            for (int i = 1; i <= 10; i++) {
                String s = String.format("https://api.github.com/search/repositories" +
                        "?q=" + framework + "+language:java+created:>" + created +
                        "&sort=created&order=asc&per_page=100&page=" + i);
                try {
                    url = new URL(s);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
//            conn.setRequestProperty("Accept", "");

                    byte[] encodedAuth = Base64.encodeBase64(token.getBytes(StandardCharsets.UTF_8));
                    String authHeaderValue = "Basic " + new String(encodedAuth);
                    conn.setRequestProperty("Authorization", authHeaderValue);

                    conn.connect();

                    int responseCode = conn.getResponseCode();
                    String responseMessage = conn.getResponseMessage();

//            System.out.println(responseCode);
//            System.out.println(responseMessage);

                    StringBuilder content = new StringBuilder("");
                    BufferedReader bi = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = null;
                    while ((line = bi.readLine()) != null) {
                        content.append(line);
                    }

                    JsonObject result = JsonParser.parseString(content.toString()).getAsJsonObject();
                    total_cnt = result.get("total_count").getAsInt();

//                    int page_nums = 0;
                    if (result.get("items").getAsJsonArray().size() == 0) {
                        created = last;
                        break;
                    }
                    for (JsonElement item : result.get("items").getAsJsonArray()) {
                        JRepo repo = gson.fromJson(item, JRepo.class);
                        repo.setFramework(framework);
                        controller.insertRepo(repo);
                        last = repo.getCreated_at();
                        cnt++;
//                        page_nums++;
                    }
                    controller.printCnt();
                    System.out.println("total: " + total_cnt + " cnt: " + cnt);
                    System.out.println("next time: " + created );
//                    if (page_nums < 100 || i == 10) {
//                        created = last;
//                        break;
//                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            // scrap by time
            if (framework.equals("Spring")) created = timeAdd(created, 1);
            if (framework.equals("Spark")) created = timeAdd(created, 2);
        } while( (cnt < limit) && (timeCheck(created)));
    }

    //type = 1: Spring, type = 2: Spark
    private static String timeAdd(String time, int type){
        String created = "2010-01-01T00:00:00Z";
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = parser.parse(time);
            int year = parse.getYear() + 1900;
            int month = parse.getMonth() + 1;

            if (type == 1) month += 3;
            else month += 6;

            if (month > 12){
                month -= 12;
                year += 1;
            }


            String Month = String.valueOf(month);
            if (Month.length() == 1) Month = "0" + Month;
            return year + "-" + Month + "-" + "01T00:00:00Z";

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean timeCheck(String time){
        int year = Integer.parseInt(time.substring(0, 4));
        int month = Integer.parseInt(time.substring(5,7));

        int date = year * 100 + month;
        return date <= 202204;
    }

>>>>>>> 5811a512aa1c223d3d64ce69ad483f6032c1036c
=======
package edu.sustech.backend.scraper;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.sustech.backend.scraper.jsonObj.JRepo;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class github_scraper {

    DataBaseController controller = new DataBaseController();
    Gson gson = new Gson();
    String token = "gfzum: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";

    public static void main(String[] args) {
        String test = "2021-01-01T00:00:00Z";
        for (int i = 0; i < 10; i++) {
            System.out.println(timeAdd(test, 2) );
            test = timeAdd(test, 2);
            System.out.println(timeCheck(test));
        }
    }

    public void repoScrapeByStars(String framework){

        URL url = null;
        outer : for (int i = 1; i <= 10; i++) {
            String s = String.format("https://api.github.com/search/repositories" +
                    "?q=" + framework + "+language:java&sort=stars" +
                    "&per_page=100&page=" + i);
            try {
                url = new URL(s);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                byte[] encodedAuth = Base64.encodeBase64(token.getBytes(StandardCharsets.UTF_8));
                String authHeaderValue = "Basic " + new String(encodedAuth);
                conn.setRequestProperty("Authorization", authHeaderValue);
                conn.connect();

                StringBuilder content = new StringBuilder("");
                BufferedReader bi = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = null;
                while ((line = bi.readLine()) != null){
                    content.append(line);
                }

                JsonObject result = JsonParser.parseString(content.toString()).getAsJsonObject();
//
            for (JsonElement items : result.get("items").getAsJsonArray()) {
                JRepo repo = gson.fromJson(items, JRepo.class);
                repo.setFramework(framework);
                controller.insertRepo(repo);
            }
            controller.printCnt();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void repoScrapeByTime(String framework) {

        int cnt = 0;
        int total_cnt = 1;
        int limit = (framework.equals("Spring") ? 50000: 20000);
        String created = "2012-01-01T00:00:00Z";
//        String created = "2019-05-08T00:00:00Z";
        String last = null;
        URL url = null;
        do {
            for (int i = 1; i <= 10; i++) {
                String s = String.format("https://api.github.com/search/repositories" +
                        "?q=" + framework + "+language:java+created:>" + created +
                        "&sort=created&order=asc&per_page=100&page=" + i);
                try {
                    url = new URL(s);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
//            conn.setRequestProperty("Accept", "");

                    byte[] encodedAuth = Base64.encodeBase64(token.getBytes(StandardCharsets.UTF_8));
                    String authHeaderValue = "Basic " + new String(encodedAuth);
                    conn.setRequestProperty("Authorization", authHeaderValue);

                    conn.connect();

                    int responseCode = conn.getResponseCode();
                    String responseMessage = conn.getResponseMessage();

//            System.out.println(responseCode);
//            System.out.println(responseMessage);

                    StringBuilder content = new StringBuilder("");
                    BufferedReader bi = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = null;
                    while ((line = bi.readLine()) != null) {
                        content.append(line);
                    }

                    JsonObject result = JsonParser.parseString(content.toString()).getAsJsonObject();
                    total_cnt = result.get("total_count").getAsInt();

//                    int page_nums = 0;
                    if (result.get("items").getAsJsonArray().size() == 0) {
                        created = last;
                        break;
                    }
                    for (JsonElement item : result.get("items").getAsJsonArray()) {
                        JRepo repo = gson.fromJson(item, JRepo.class);
                        repo.setFramework(framework);
                        controller.insertRepo(repo);
                        last = repo.getCreated_at();
                        cnt++;
//                        page_nums++;
                    }
                    controller.printCnt();
                    System.out.println("total: " + total_cnt + " cnt: " + cnt);
                    System.out.println("next time: " + created );
//                    if (page_nums < 100 || i == 10) {
//                        created = last;
//                        break;
//                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            // scrap by time
            if (framework.equals("Spring")) created = timeAdd(created, 1);
            if (framework.equals("Spark")) created = timeAdd(created, 2);
        } while( (cnt < limit) && (timeCheck(created)));
    }

    //type = 1: Spring, type = 2: Spark
    private static String timeAdd(String time, int type){
        String created = "2010-01-01T00:00:00Z";
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = parser.parse(time);
            int year = parse.getYear() + 1900;
            int month = parse.getMonth() + 1;

            if (type == 1) month += 3;
            else month += 6;

            if (month > 12){
                month -= 12;
                year += 1;
            }

            String Month = String.valueOf(month);
            if (Month.length() == 1) Month = "0" + Month;
            return year + "-" + Month + "-" + "01T00:00:00Z";

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean timeCheck(String time){
        int year = Integer.parseInt(time.substring(0, 4));
        int month = Integer.parseInt(time.substring(5,7));

        int date = year * 100 + month;
        return date <= 202204;
    }

>>>>>>> 0e7496ae801647342f1755b489baa9d096639a53
}