package edu.sustech.backend.ssm.dao;

import edu.sustech.backend.scraper.jsonObj.JRepo;
import edu.sustech.backend.ssm.pojo.Repo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GithubDao {

    @Select("select * from github_repos")
    public List<Repo> getAllRepos();

    @Select("select * from github_repos where create_year=#{year} and frame_id=#{frame}")
    public List<Repo> getReposByYearAndFrame(Integer year, Integer frame);

    @Select("select * from github_repos where create_year=#{year} and create_month = #{month}" +
            " and frame_id=#{frame}")
    public List<Repo> getReposByTimeAndFrame(Integer year, Integer month, Integer frame);

    @Select("select * from github_repos where create_year=#{year} ")
    public List<Repo> getReposByYear(Integer year);

    @Select("select * from github_repos where frame_id=#{frame} ")
    public List<Repo> getRepoByFrame(Integer frmae);

}
