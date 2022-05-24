package edu.sustech.backend.ssm.services;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface GitHubService {
//    public List<frameData> getFrameData();
    public String sendLineChart();
    public String sendTable();
    public String sendCloud();
    public String sendDynamic();
    public String sendPie();
}
