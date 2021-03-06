package edu.sustech.backend.ssm.sendData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lineChartData {
        private List<String> frameworks;
        private List<Integer> years;
        private List<List<Integer>> popularity = new ArrayList<>();

    public lineChartData(){
         frameworks = new ArrayList<>(Arrays.asList(
                "Spring", "Struts", "Spark", "GWT", "DropWizard", "Blade", "Vaadin",
                "JHipster", "Tapestry", "Wicket", "Hibernate", "MyBatis"));
         years = new ArrayList<>(Arrays.asList(2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021));

        }

    public List<List<Integer>> getPopularity() {
        return popularity;
    }
}
