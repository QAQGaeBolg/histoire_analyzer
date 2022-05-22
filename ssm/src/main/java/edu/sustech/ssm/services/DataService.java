package edu.sustech.ssm.services;

import edu.sustech.ssm.domain.Data;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface DataService {
    public String getAll();
}
