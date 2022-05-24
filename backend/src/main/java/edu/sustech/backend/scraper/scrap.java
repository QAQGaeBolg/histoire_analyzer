package edu.sustech.backend.scraper;

import edu.sustech.backend.ssm.services.impl.GitHubServiceImpl;

public class scrap {
    public static void main(String[] args) {
        github_scraper gs = new github_scraper();
        GitHubServiceImpl temp = new GitHubServiceImpl();
//        gs.repoScrapeByStars("Spring");
//        gs.repoScrapeByStars("Struts");
//        gs.repoScrapeByStars("GWT");
//        gs.repoScrapeByStars("Spark");
//        gs.repoScrapeByStars("DropWizard");

//        gs.repoScrapeByTime("Spring");
        for (int i = 12; i >= 1; i--) {
            gs.repoScrapeByTime(GitHubServiceImpl.frameworkMap.get(i));
        }

    }
}
