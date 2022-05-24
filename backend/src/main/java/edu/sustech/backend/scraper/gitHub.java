package edu.sustech.backend.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.stream.Collectors;

public class gitHub {

    public static void main(String[] args) throws IOException {
        String url;
        for(int i = 1; i <= 9; i++){
            url = "https://github.com/search?p=" + i + "&q=java+spring&type=Repositories";
            Get(url);
            try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
        }
    }

    public static void Get(String url){
//        Document document = Jsoup.connect(url).ignoreContentType(true).maxBodySize(Integer.MAX_VALUE).get();
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements select = document.select(" #js-pjax-container > div > " +
                "div.col-12.col-md-9.float-left.px-2.pt-3.pt-md-0.codesearch-results > div > ul > li");

        select.stream()
                .map(Info::new)
                .peek(System.out::println)
                .collect(Collectors.groupingBy(it -> it.keyword, Collectors.counting()));
    }

    private static class Info {
        String date;
        String keyword;
        String num;

        Info(Element e){
            date = e.select("relative-time.no-wrap").text();

            keyword = "GitHub";

            num = e.select("a.Link--muted").text();
            if(num.contains(" ")) num = num.substring(0, num.indexOf(" "));
        }
        @Override
        public String toString() {return String.format("%-16s %-10s %s", date, keyword, num);}
    }
}