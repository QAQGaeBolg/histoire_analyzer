package edu.sustech.backend.ssm.pojo;

public class frameData {
    String framework;
    int stars;
    int forks;
    int popularity;

    int year;
    int month;
    String date;

    public frameData(String framework, int stars, int forks, int year, int month) {
        this.framework = framework;
        this.stars = stars;
        this.forks = forks;
        this.popularity = stars + forks;

        this.year = year;
        this.month = month;
        this.date = year + "-";
        if (month < 10) date += "0" + month;
        else date += month;
    }
}
