package com.yc.biz;

import com.yc.dao.OrderMapperImp;
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
public class OrderBiz {

    @Autowired
    private OrderMapperImp orderMapperImp;

    public Boolean order(@NotNull(message = "uid不能为空") String uid,
                         @NotNull(message = "address不能为空") String address,
                         @NotNull(message = "tel不能为空") String tel,
                         @NotNull(message = "ps不能为空") String ps,
                         @NotNull(message = "roitid不能为空") String roitid) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("address", address);
        map.put("tel", tel);
        map.put("ps", ps);
        map.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        try {
            int i = orderMapperImp.createorder(map);
            map.put("roid", i);
            boolean b = roitid.contains(",");
            if (b) {
                String[] str = roitid.split(",");
                for (String s : str) {
                    System.out.println(s);
                    int i1 = orderMapperImp.createorderitem(map, s);
                    int delordercar = orderMapperImp.delordercar(s);
                    if (i1 == 1 && delordercar == 1) {
                        System.out.println("成功");
                    } else {
                        throw new RuntimeException("添加失败");
                    }
                }
                return true;
            } else {
                int i1 = orderMapperImp.createorderitem(map, roitid);
                int i2 = orderMapperImp.delordercar(roitid);
                System.out.println(i1 + "=" + i2);
                if (i1 == 1 && i2 == 1) {
                    return true;
                } else {
                    throw new RuntimeException("添加失败");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 获得某一个用户的购物车信息
     *
     * @param userid
     * @return
     */
    public List<Map<String, Object>> getcartinfo(@NotNull(message = "uid不能为空") String userid) {
        List<Map<String, Object>> maps = orderMapperImp.querycartfoodsinfo(userid);
        return maps;
    }

    public Boolean updnowcartnum(@NotNull(message = "userid不能为空") String userid,
                                 @NotNull(message = "roitid不能为空") String roitid) {
        int i = orderMapperImp.querynownum(userid, roitid);
        if (i == 1) {
            return false;
        }
        int u = orderMapperImp.updcartfoodsnum(userid, roitid);
        return u == 1;
    }

    public Boolean updnowcartnum2(@NotNull(message = "userid不能为空") String userid,
                                  @NotNull(message = "roitid不能为空") String roitid) {

        int u = orderMapperImp.updcartfoodsnum2(userid, roitid);
        return u == 1;
    }

    public Boolean delcartfoods(@NotNull(message = "roitid不能为空") String roitid) {
        return orderMapperImp.delordercar(roitid) == 1 ? true : false;
    }

}
