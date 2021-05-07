package com.yc.dao;

import java.util.List;
import java.util.Map;

public interface ResFood {

    public List<Map<String,Object>> queryallfoodinfo();
    public List<Map<String,Object>> queryallfoodinfofen(Integer nowpage);

    public Integer querycountfoods();

    public Map<String,Object> queryonrfooddetail(String fid);

    public int addfoodtocar(Map map);

    public int queronefid(Map map);
    public int updatefoodnum(Map map);
    public int queryonefidnum(Map map);
}
