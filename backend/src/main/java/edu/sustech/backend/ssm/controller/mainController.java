package edu.sustech.backend.ssm.controller;

import edu.sustech.backend.ssm.services.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/data")
public class mainController {

    @Autowired
    private GitHubService gitHubService;

    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id){
        System.out.println(id);
        return "hello!";
    }

    @GetMapping("/line")
    public String getLineChart(){
        System.out.println("line");
        return gitHubService.sendLineChart();
    }
    @GetMapping("/table")
    public String getTable(){
        System.out.println("table");
        return gitHubService.sendTable();
    }

}
