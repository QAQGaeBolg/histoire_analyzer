package edu.sustech.backend.ssm.services.impl;

import com.google.gson.Gson;
import edu.sustech.backend.ssm.dao.GithubDao;
import edu.sustech.backend.scraper.jsonObj.JRepo;
import edu.sustech.backend.ssm.pojo.Repo;
import edu.sustech.backend.ssm.pojo.frameData;
import edu.sustech.backend.ssm.sendData.columnData;
import edu.sustech.backend.ssm.sendData.lineChartData;
import edu.sustech.backend.ssm.sendData.tableData;
import edu.sustech.backend.ssm.services.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GitHubServiceImpl implements GitHubService {

    @Autowired
    private GithubDao githubDao;
    Gson gson = new Gson();
    Map<Integer, String> frameworkMap;


    public GitHubServiceImpl(){
        frameworkMap = new HashMap<>();
        frameworkMap.put(1, "Spring");
        frameworkMap.put(2, "Struts");
        frameworkMap.put(3, "Spark");
        frameworkMap.put(4, "GWT");
        frameworkMap.put(5, "DropWizard");

    }


//    @Override
//    public List<frameData> getFrameData(){
//
//
//        return null;
//    }

    @Override
    public String sendLineChart() {

        lineChartData result = new lineChartData();

        for (int i = 1; i <= 5; i++) {
            List<Integer> repoCounts = new ArrayList<>();
            for (int j = 2015; j <= 2021; j++) {
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
    public String sendColumn() {

        columnData result = new columnData();

        List<Integer> pop = result.getPop();
        for (int i = 1; i <= 5; i++) {
            for (Repo repo : githubDao.getRepoByFrame(i)) {
                pop.set(i - 1, pop.get(i - 1) + repo.getStars() + repo.getForks());
            }
        }

        return gson.toJson(result);
    }

    @Override
    public String sendPie() {
        return null;
    }

    @Override
    public String sendCloud() {
        return null;
    }

    @Override
    public String sendDynamic() {
        return null;
    }
}
