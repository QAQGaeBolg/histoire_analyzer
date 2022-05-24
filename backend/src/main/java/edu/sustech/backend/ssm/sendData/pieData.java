package edu.sustech.backend.ssm.sendData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class pieData {
    private List<String> framework = new ArrayList<>(Arrays.asList(
            "Spring", "Struts", "Spark", "GWT", "DropWizard", "Blade", "Vaadin",
            "JHipster", "Tapestry", "Wicket", "Hibernate", "MyBatis"));
    private List<Integer> pop = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0));


    public List<Integer> getPop() {
        return pop;
    }
}
