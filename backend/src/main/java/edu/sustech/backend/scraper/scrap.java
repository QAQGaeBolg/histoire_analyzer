package edu.sustech.backend.scraper;

public class scrap {
    public static void main(String[] args) {
        github_scraper gs = new github_scraper();
        gs.repoScrape("Spring");
        gs.repoScrape("Struts");
    }
}