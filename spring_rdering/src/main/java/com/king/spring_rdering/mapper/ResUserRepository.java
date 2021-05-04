package com.king.spring_rdering.mapper;

import com.king.spring_rdering.bean.ResAdmin;
import com.king.spring_rdering.bean.ResUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-05-04 14:53
 */
public interface ResUserRepository extends JpaRepository<ResUser, Integer> {
}
