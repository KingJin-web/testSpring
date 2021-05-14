package com.example.springmongodb.dao;

import com.example.springmongodb.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * @program: testUser
 * @description:
 * @author: King
 * @create: 2021-05-15 15:35
 */
@Repository
public class UserDao {


    @Autowired
    private MongoTemplate mongoTemplate;

    //添加
    public User insert(User User) {
        User s = mongoTemplate.insert(User, "User");
        return s;
    }

    /**
     * 更新
     *
     * @param filed 字段名
     * @param id    id
     * @param obj   要修改的值
     */
    public void update(String filed, Integer id, Object obj) {
        Query query = new Query(Criteria.where("_id").is(id));//创建查询条件
        Update update = new Update();
        update.set(filed, obj);//添加修改条件
        mongoTemplate.updateFirst(query, update, "User");
    }

    //查询
    public User findByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        User s = mongoTemplate.findOne(query, User.class, "User");
        return s;
    }

    //删除
    public void delete(Integer id) {
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, "User");
    }
}