package com.yc.dao;

import java.util.List;
import java.util.Map;

public interface OrderMapper {

    public int createorder(Map map);

    public Map<String,Object> querycheckedpro(String roitid);

    public List<Map<String,Object>> querycartfoodsinfo(String userid);

    public int updcartfoodsnum(String userid,String roitid);
    public int querynownum(String userid,String roitid);

    public int delcartinfo(String roitid);

}
