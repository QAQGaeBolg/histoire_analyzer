package edu.sustech.backend.ssm.services.impl;

import com.google.gson.Gson;
import edu.sustech.backend.ssm.dao.GithubDao;
import edu.sustech.backend.scraper.jsonObj.JRepo;
import edu.sustech.backend.ssm.pojo.Repo;
import edu.sustech.backend.ssm.pojo.frameData;
import edu.sustech.backend.ssm.sendData.lineChartData;
import edu.sustech.backend.ssm.sendData.tableData;
import edu.sustech.backend.ssm.services.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GitHubServiceImpl implements GitHubService {

    @Autowired
    private GithubDao githubDao;
    Gson gson = new Gson();
    
//    @Override
//    public List<frameData> getFrameData(){
//
//
//        return null;
//    }

    @Override
    public String sendLineChart() {

        lineChartData result = new lineChartData();

        for (int i = 1; i <= 2; i++) {
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
            result.add(new tableData(repo.getFull_name(),
                    repo.getStars(), repo.getForks(),
                    create_data, repo.getUpdated_date(), repo.getUrl()));
        }
        return gson.toJson(result);
    }
}
