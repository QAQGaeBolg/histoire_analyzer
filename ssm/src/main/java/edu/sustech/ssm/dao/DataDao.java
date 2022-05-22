package edu.sustech.ssm.dao;

import edu.sustech.ssm.domain.Data;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DataDao {

    @Select("select * from data")
    public List<Data> getAll();

    public int selectOne(int i);

    public int sort(String s);

}
