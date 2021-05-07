package com.yc.biz;

import com.yc.dao.ResFoodImp;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Validated
@Transactional
public class FoodInfoBiz {

    @Autowired
    private ResFoodImp resFoodImp;

    /**
     * 获取某一个菜品的详细信息  用户进入详情页
     *
     * @param fid
     * @return
     */
    public Map<String, Object> GetOneInfo(@NotNull(message = "fid不能为空") String fid) {
        Map<String, Object> map = resFoodImp.queryonrfooddetail(fid);
        return map;
    }

    /**
     * 对菜品进行分页
     * @param nowpage
     * @return
     */
    public List<Map<String ,Object>> getfoodspageinfo(@NotNull(message = "当前页码不能为空")String nowpage){
        int i = Integer.parseInt(nowpage);
        List<Map<String, Object>> maps = resFoodImp.queryallfoodinfofen(i);
        return maps;
    }


    /**
     * 获取总页数
     * @return
     */
    public Integer getallcount(){
        Integer icount = resFoodImp.querycountfoods();
        int totalPages=0;
        if (icount % 5 == 0) {
            totalPages = icount / 5;
        } else {
            totalPages = (icount / 5) + 1;
        }

        return totalPages;
    }
    /**
     * 添加菜品至购物车  根据前端取到的参数
     * 判断 某人购物车中是否已经存在该菜品 如果有直接修改数量即可  没有的话直接添加
     * @param fid 食物id
     * @param num 食物数量
     * @param uid 用户id
     * @return
     */
    public Boolean AddFood(@NotNull(message = "fid不能为空") String fid,
                           @NotNull(message = "num不能为空") String num,
                           @NotNull(message = "uid不能为空") String uid) {
        int i=0;
        try {

            HashMap<String, Object> map = new HashMap<>();
            map.put("fid", fid);
            map.put("num", num);
            map.put("uid", uid);
            map.put("time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if(resFoodImp.queronefid(map)==1){
                int queryonefidnum = resFoodImp.queryonefidnum(map);//购物车中菜品数量
                map.put("num2",queryonefidnum+1);
                i = resFoodImp.updatefoodnum(map);
            }else{
                i= resFoodImp.addfoodtocar(map);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if (i == 1) {
            return true;
        } else {
            return false;
        }

    }
}
