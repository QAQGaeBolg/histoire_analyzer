package edu.sustech.backend.ssm.sendData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class columnData {
    private List<String> framework = new ArrayList<>(Arrays.asList("Spring", "Struts", "Spark", "GWT", "DropWizard"));
    private List<Integer> pop = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0));

    public List<Integer> getPop() {
        return pop;
    }
}
