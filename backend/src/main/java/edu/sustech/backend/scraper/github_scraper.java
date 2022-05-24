package edu.sustech.backend.scraper;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.sustech.backend.scraper.jsonObj.JRepo;
import org.apache.ibatis.annotations.Case;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class github_scraper {

    DataBaseController controller = new DataBaseController();
    Gson gson = new Gson();
    String token = "gfzum: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";

    public static void main(String[] args) {

    }

    public void repoScrapeByStars(String framework){

        URL url = null;
        for (int i = 1; i <= 10; i++) {
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

    public void repoScrapeBytime(String framework) {

        int cnt = 0;
        int total_cnt = 1;
        int limit = (framework.equals("Spring") ? 50000: 20000);
        String created = "2010-01-01T00:00:00Z";
        String last = null;
        URL url = null;
        do {
            for (int i = 1; i <= 10; i++) {
                String s = String.format("https://api.github.com/search/repositories" +
                        "?q=" + framework + "+language:java&created>" + created +
                        "&sort=create&order=asc&per_page=100&page=" + i);
                try {
                    url = new URL(s);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
//            conn.setRequestProperty("Accept", "");
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

                    int page_nums = 0;
                    for (JsonElement item : result.get("items").getAsJsonArray()) {
                        JRepo repo = gson.fromJson(item, JRepo.class);
                        repo.setFramework(framework);
                        controller.insertRepo(repo);
                        last = repo.getCreated_at();
                        cnt++;
                        page_nums++;
                    }
                    controller.printCnt();
                    if (page_nums < 100 || i == 10) {
                        created = last;
                        break;
                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
//            if (framework == "Spring")
        } while(cnt < total_cnt || cnt >= limit);
    }
}