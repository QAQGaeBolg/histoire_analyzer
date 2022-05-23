package edu.sustech.backend.scraper;

import com.alibaba.druid.pool.*;
import edu.sustech.backend.scraper.jsonObj.JRepo;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DataBaseController {
    private DataSource dataSource;
    private static final int BATCH_SIZE = 500;
    private int repoCnt = 0;
    private Map<String ,Integer> frameworkMap;

    public DataBaseController() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("backend/src/main/resources/config.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        frameworkMap = new HashMap<>();
        frameworkMap.put("Spring", 1);
        frameworkMap.put("Struts", 2);
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(dateConvert("2020-12-10T01:48:54Z"));
    }

    public static String dateConvert(String date){
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date convertDate = parser.parse(date);
            int year = convertDate.getYear() + 1900;
            int month = convertDate.getMonth() + 1;
            return year + "-" + month;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertRepo(JRepo repo) {
        try (Connection connec = dataSource.getConnection()) {

            String sql = "insert into github_repos (repo_name, full_name, url, stars, forks, create_year, create_month, updated_date, description, frame_id) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstm = connec.prepareStatement(sql);
            pstm.setString(1, repo.getName());
            pstm.setString(2, repo.getFull_name());
            pstm.setString(3, repo.getHtml_url());
            pstm.setInt(4, repo.getStargazers_count());
            pstm.setInt(5, repo.getForks_count());
            String create_date = repo.getCreated_at();
            String update_date = repo.getUpdated_at();
            pstm.setInt(6, Integer.parseInt(dateConvert(create_date).substring(0,4)));
            pstm.setInt(7, Integer.parseInt(dateConvert(create_date).substring(5)));
            pstm.setString(8, dateConvert(update_date));
            String description = repo.getDescription();
            if (description != null && description.length() >= 100)
                description = description.substring(0,100);
            pstm.setString(9, description);
            pstm.setInt(10, frameworkMap.get(repo.getFramework()));

            pstm.execute();
            repoCnt++;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void printCnt(){
        System.out.println("load " + repoCnt + " repos into database");
    }

}
