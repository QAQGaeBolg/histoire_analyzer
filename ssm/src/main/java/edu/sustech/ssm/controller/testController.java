package edu.sustech.ssm.controller;


import edu.sustech.ssm.domain.Data;
import edu.sustech.ssm.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data")
public class testController {

    @Autowired
    private DataService dataService;

    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id){
        System.out.println(id);
        return "hello!";
    }

    @GetMapping("/test")
    public String getAllData(){
        return dataService.getAll();
    }

}
