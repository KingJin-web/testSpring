package com.king.spring_rdering.dao;

import com.king.spring_rdering.bean.ResAdmin;

public interface ResAdminDao {
    public ResAdmin findById(int id);

    /**
     * 登录
     *
     * @param resAdmin
     * @return
     */
    public boolean login(ResAdmin resAdmin);

    /**
     * * 修改信息 注册 ID在表中没有就是注册 有就是修改信息
     *
     * @param resAdmin
     * @return
     */
    public ResAdmin update(ResAdmin resAdmin);
}
