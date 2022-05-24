package edu.sustech.backend.ssm.sendData;

public class dynamicData{
    String repos; //因为要与第一个名字数组的类型匹配，所以类型转换放到了前端里
    String framework;
    String date;

    public dynamicData(String repos, String framework, String date) {
        this.repos = repos;
        this.framework = framework;
        this.date = date;
    }
}