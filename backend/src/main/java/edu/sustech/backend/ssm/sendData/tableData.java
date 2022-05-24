package edu.sustech.backend.ssm.sendData;

public class tableData {
    String framework;
    String repoName;
    int stars;
    int forks;
    String create_date;
    String update_date;
    String url;

    public tableData(String framework, String repoName, int stars, int forks,
                     String create_date, String update_date, String url) {
        this.framework = framework;
        this.repoName = repoName;
        this.stars = stars;
        this.forks = forks;
        this.create_date = create_date;
        this.update_date = update_date;
        this.url = url;
    }
}
