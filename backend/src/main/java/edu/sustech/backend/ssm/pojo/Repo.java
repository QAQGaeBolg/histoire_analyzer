package edu.sustech.backend.ssm.pojo;

public class Repo {
    int frame_id;
    String repo_name;
    String full_name;
    String url;
    int stars;
    int forks;
    int create_year;
    int create_month;
    String updated_date;
    String description;
    int gh_id;

    public Repo(int frame_id, String repo_name, String full_name, String url,
                int stars, int forks, int create_year, int create_month,
                String updated_date, String description, int gh_id) {
        this.frame_id = frame_id;
        this.repo_name = repo_name;
        this.full_name = full_name;
        this.url = url;
        this.stars = stars;
        this.forks = forks;
        this.create_year = create_year;
        this.create_month = create_month;
        this.updated_date = updated_date;
        this.description = description;
        this.gh_id = gh_id;
    }

    public int getFrame_id() {
        return frame_id;
    }

    public String getRepo_name() {
        return repo_name;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getUrl() {
        return url;
    }

    public int getStars() {
        return stars;
    }

    public int getForks() {
        return forks;
    }

    public int getCreate_year() {
        return create_year;
    }

    public int getCreate_month() {
        return create_month;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public String getDescription() {
        return description;
    }

    public int getGh_id() {
        return gh_id;
    }
}
