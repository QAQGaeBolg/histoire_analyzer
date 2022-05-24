package edu.sustech.backend.scraper.jsonObj;

public class JRepo {
    String framework;
    String name;
    String full_name;
    String html_url;
    String description;
    String created_at;
    String updated_at;
    int stargazers_count;
    int forks_count;

    public JRepo(String name, String full_name, String html_url, String description,
                 String created_at, String updated_at, int stargazers_count,
                 int forks_count) {
        this.name = name;
        this.full_name = full_name;
        this.html_url = html_url;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.stargazers_count = stargazers_count;
        this.forks_count = forks_count;
    }

    @Override
    public String toString() {
        return "Repo{" +
                "name='" + name + '\'' +
                ", full_name='" + full_name + '\'' +
                ", html_url='" + html_url + '\'' +
                ", description='" + description + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", stargazers_count=" + stargazers_count +
                ", forks_count=" + forks_count +
                '}';
    }

    public String getFramework() {
        return framework;
    }

    public void setFramework(String framework) {
        this.framework = framework;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public int getForks_count() {
        return forks_count;
    }

    public void setForks_count(int forks_count) {
        this.forks_count = forks_count;
    }
}
