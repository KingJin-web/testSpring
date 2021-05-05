package com.king.spring_rdering.dao;

import com.king.spring_rdering.bean.ResAdmin;
import com.king.spring_rdering.bean.ResFood;
import com.king.spring_rdering.bean.ResOrder;
import com.king.spring_rdering.mapper.ResFoodRepository;
import com.king.spring_rdering.vo.CartItem;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sun.security.ssl.Record;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-05-04 15:01
 */
@Repository
public class ResFoodDaoImpl implements ResFoodDao {
    @Autowired
    private ResFoodRepository foodRepository;

    private Example<ResFood> em;

    @Override
    @Transactional(readOnly = true)
    public List<ResFood> findAll() {
        return foodRepository.findAll();
    }

    @Override
    public List<ResFood> findAll(int page, int size) {
        // 排序方式，这里是以“fid”为标准进行
        Sort sort = Sort.by(Sort.Direction.ASC, "fid");  // 这里的"fid"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = PageRequest.of(page - 1, size, sort); // （当前页， 每页记录数， 排序方式）
        Page<ResFood> list = foodRepository.findAll(pageable);
        //System.out.println(list.getContent());
        System.out.println(list.getTotalPages());
        return list.getContent();
    }

    @Override
    public ResFood findByName(String foodName) {
        ResFood resFood = new ResFood();
        resFood.setFname(foodName);
        em = Example.of(resFood);
        return foodRepository.findAll(em).get(0);
    }

    @Override
    public ResFood findByFid(int fid) {
        ResFood resFood = new ResFood();
        resFood.setFid(fid);
        em = Example.of(resFood);
        Optional<ResFood> opt = foodRepository.findOne(em);
        return opt.get();
    }

    @Override
    public ResFood update(ResFood resFood) {
        return foodRepository.save(resFood);
    }


}
