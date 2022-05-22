package edu.sustech.ssm.domain;

public class Data {

    String date;
    String name;
    String stars;
    int id;

    public Data(String date, String name, String stars, int id) {
        this.date = date;
        this.name = name;
        this.stars = stars;
        this.id = id;
    }

    @Override
    public String toString() {
        return "data{" +
                "date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", stars='" + stars + '\'' +
                ", id=" + id +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getStars() {
        return stars;
    }
}
