package edu.sustech.backend.ssm.services.impl;

import com.google.gson.Gson;
import edu.sustech.backend.ssm.dao.GithubDao;
import edu.sustech.backend.ssm.pojo.Repo;
import edu.sustech.backend.ssm.sendData.*;
import edu.sustech.backend.ssm.services.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.constant.DynamicCallSiteDesc;
import java.util.*;

@Service
public class GitHubServiceImpl implements GitHubService {

    @Autowired
    private GithubDao githubDao;
    private Gson gson = new Gson();
    public static Map<Integer, String> frameworkMap;


    public GitHubServiceImpl(){
        frameworkMap = new HashMap<>();
        frameworkMap.put(1, "Spring");
        frameworkMap.put(2, "Struts");
        frameworkMap.put(3, "Spark");
        frameworkMap.put(4, "GWT");
        frameworkMap.put(5, "DropWizard");
        frameworkMap.put(6, "Blade");
        frameworkMap.put(7, "Vaadin");
        frameworkMap.put(8, "JHipster");
        frameworkMap.put(9, "Tapestry");
        frameworkMap.put(10,"Wicket");
        frameworkMap.put(11, "Hibernate");
        frameworkMap.put(12, "MyBatis");
    }

    @Override
    public String sendLineChart() {
        lineChartData result = new lineChartData();

        for (int i = 1; i <= 12; i++) {
            List<Integer> repoCounts = new ArrayList<>();
            for (int j = 2012; j <= 2021; j++) {
                repoCounts.add(githubDao.getReposByYearAndFrame(j,i).size());
            }
            result.getPopularity().add(repoCounts);
        }
        return gson.toJson(result);
    }

    @Override
    public String sendTable() {
        List<tableData> result = new ArrayList<>();

        for (Repo repo : githubDao.getAllRepos()) {
            String create_data = repo.getCreate_year() + "-" + repo.getCreate_month();
            result.add(new tableData(frameworkMap.get(repo.getFrame_id()),
                    repo.getFull_name(), repo.getStars(), repo.getForks(),
                    create_data, repo.getUpdated_date(), repo.getUrl()));
        }
        return gson.toJson(result);
    }

    @Override
    public String sendCloud() {

        List<cloudData> result = new ArrayList<>();
        for (int i = 1; i <= frameworkMap.size(); i++) {
            int pop = 0;
            for (Repo repo : githubDao.getRepoByFrame(i)) {
                pop += repo.getForks() + repo.getStars();
            }
            result.add(new cloudData(frameworkMap.get(i), pop));
        }
        return gson.toJson(result);
    }

    @Override
    public String sendDynamic() {
//        List<dynamicData> result = new ArrayList<>();
        List<List<String>> result = new ArrayList<>();
        List<String> title = new ArrayList<>(Arrays.asList("Repositories", "Frameworks", "Time"));
//        result.add(new dynamicData());
        result.add(title);

//        int[] temp = new int[frameworkMap.size()];
        List<Integer> frame_repos = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0));

        for (int y = 2012; y <= 2022; y++) {
            for (int m = 1; m <= 12; m++) {
                if (y == 2022 && m > 4) break;
                for (int i = 1; i <= frameworkMap.size(); i++) {
                    frame_repos.set(i - 1, frame_repos.get(i - 1) + githubDao.getReposByTimeAndFrame(y, m, i).size());
//                    result.add(new dynamicData(String.valueOf(frame_repos.get(i - 1)),
//                            frameworkMap.get(i), y + "-" + m));
                    List<String> temp = new ArrayList<>(Arrays.asList(
                            String.valueOf(frame_repos.get(i - 1)), frameworkMap.get(i), y + "-" + m)
                    );
                    result.add(temp);
                }
            }
        }
        return gson.toJson(result);
    }

    @Override
    public String sendPie() {
        pieData result = new pieData();
        List<Integer> pop = result.getPop();

        for (int i = 1; i <= frameworkMap.size(); i++) {
            for (Repo repo : githubDao.getRepoByFrame(i)) {
                pop.set(i - 1, pop.get(i - 1) + repo.getStars() + repo.getForks());
            }
        }
        return gson.toJson(result);
    }
}
