package com.king.spring_rdering.dao;

import com.king.spring_rdering.bean.ResFood;

import java.util.List;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-05-04 14:58
 */
public interface ResFoodDao {
    //Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "key");
    public List<ResFood> findAll();
    public List<ResFood> findAll(int page,int size);
    public ResFood findByName(String foodName);

    public ResFood update(ResFood resFood);

}
