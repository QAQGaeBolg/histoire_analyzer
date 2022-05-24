package edu.sustech.backend.ssm.services;

import edu.sustech.backend.ssm.pojo.frameData;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface GitHubService {
//    public List<frameData> getFrameData();
    public String sendLineChart();
    public String sendTable();
}
