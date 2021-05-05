package com.king.spring_rdering.dao;

import com.king.spring_rdering.bean.ResFood;
import com.king.spring_rdering.bean.ResOrder;
import com.king.spring_rdering.vo.CartItem;

import java.util.List;
import java.util.Map;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-05-04 14:58
 */
public interface ResFoodDao {
    //Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "key");

    /**
     * 查询所有菜
     *
     * @return
     */
    public List<ResFood> findAll();

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每一页数据数
     * @return
     */
    public List<ResFood> findAll(int page, int size);

    public ResFood findByName(String foodName);

    /**
     * 通过id查询
     *
     * @param fid
     * @return
     */
    public ResFood findByFid(int fid);

    /**
     * 修改
     *
     * @param resFood
     * @return
     */
    public ResFood update(ResFood resFood);



}
