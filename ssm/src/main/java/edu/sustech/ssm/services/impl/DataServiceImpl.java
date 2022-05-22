package edu.sustech.ssm.services.impl;

import edu.sustech.ssm.dao.DataDao;
import edu.sustech.ssm.domain.Data;
import edu.sustech.ssm.services.DataService;
import edu.sustech.ssm.utils.dataToJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataDao dataDao;

    @Override
    public String getAll() {
        List<Data> all = dataDao.getAll();
        return dataToJson.dataToJson(all);
    }
}
