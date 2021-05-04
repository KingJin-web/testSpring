package com.king.spring_rdering.dao;

import com.king.spring_rdering.bean.ResUser;

public interface ResUserDao {
    public boolean login(ResUser resUser);
    public ResUser update(ResUser resUser);

}
