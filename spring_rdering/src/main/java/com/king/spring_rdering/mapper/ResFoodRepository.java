package com.king.spring_rdering.mapper;

import com.king.spring_rdering.bean.ResAdmin;
import com.king.spring_rdering.bean.ResFood;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-29 20:52
 */
public interface ResFoodRepository extends JpaRepository<ResFood, Integer> {
}
