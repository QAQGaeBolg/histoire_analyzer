package edu.sustech.backend.scraper;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.sustech.backend.scraper.jsonObj.JRepo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class github_scraper {

    DataBaseController controller = new DataBaseController();
    Gson gson = new Gson();

    public static void main(String[] args) {

    }

    public void repoScrape(String framework) {

        String s = String.format("https://api.github.com/search/repositories" +
                "?q=" + framework + "+language:java&sort=stars&per_page=100");
        URL url = null;
        try {
            url = new URL(s);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Accept", "");
            conn.connect();

            int responseCode = conn.getResponseCode();
            String responseMessage = conn.getResponseMessage();

            System.out.println(responseCode);
            System.out.println(responseMessage);

            StringBuilder content = new StringBuilder("");
            BufferedReader bi = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = bi.readLine()) != null){
                content.append(line);
            }

            JsonObject result = JsonParser.parseString(content.toString()).getAsJsonObject();
            System.out.println("nums: " + result.get("total_count").getAsInt());

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