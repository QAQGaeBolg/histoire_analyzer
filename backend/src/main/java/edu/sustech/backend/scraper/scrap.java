package edu.sustech.backend.scraper;

public class scrap {
    public static void main(String[] args) {
        github_scraper gs = new github_scraper();
//        gs.repoScrapeByStars("Spring");
//        gs.repoScrapeByStars("Struts");
//        gs.repoScrapeByStars("GWT");
        gs.repoScrapeByStars("Spark");
        gs.repoScrapeByStars("DropWizard");

//        gs.repoScrapeByTime("Spring");
    }
}
