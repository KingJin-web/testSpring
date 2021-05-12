package com.ex.res.biz;

import com.ex.res.bean.Resfood;

import java.util.List;

public interface ResfoodBiz {
    /**
     * 查询所有的菜单
     *
     * @return
     */
    public List<Resfood> findAll();

    /**
     * 分页查询
     *
     * @param page
     * @param size
     * @return
     */
    public List<Resfood> findAll(int page, int size);

    /**
     * 根据fid查找某个菜
     */
    public Resfood findByFid(Integer fid);


}
