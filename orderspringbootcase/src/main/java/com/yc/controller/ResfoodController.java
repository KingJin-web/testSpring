package com.yc.controller;

import com.google.gson.Gson;
import com.yc.bean.VObean;
import com.yc.biz.FoodInfoBiz;
import com.yc.dao.ResFoodImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@Api(description = "这是关于菜品操作的接口")
public class ResfoodController {
    @Autowired
    private ResFoodImp resFoodImp;
    @Autowired
    private FoodInfoBiz foodInfoBiz;
    @Autowired
    private VObean vObean;

    @GetMapping("/f/t1")
    @ApiOperation(value = "得到进入所有食物信息", notes = "无需参数 返回json数据")
    public String queryallinfo() {
        List<Map<String, Object>> maps = resFoodImp.queryallfoodinfo();
        Gson gson = new Gson();
        String s = gson.toJson(maps);
        return s;
    }

    @GetMapping("/f/t2")
    @ApiOperation(value = "得到某一条食物信息", notes = "通过哪一种食物的id 获取")
    @ApiImplicitParam(name = "fid", value = "传入的参数为食物的fid", required = true, dataType = "String")
    public VObean queryonefood(@RequestParam("fid") String fid) {
        try {
            Map<String, Object> map = foodInfoBiz.GetOneInfo(fid);
            Gson gson = new Gson();
            String s = gson.toJson(map);
            vObean.setCode(200);
            vObean.setData(s);
            vObean.setMsg("成功");
            return vObean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        vObean.setCode(500);
        vObean.setData("null");
        vObean.setMsg("失败");
        return vObean;
    }

    @PostMapping("f/t3")
    @ApiOperation(value = "将食物添加至购物车", notes = "需要食物fid,num")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fid", value = "食物id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "num", value = "食物数量", required = true, dataType = "String")})
    public VObean CarAdd(String fid, String num,  HttpSession session) {
        String uid1 = (String) session.getAttribute("uid");
        if (uid1 == null || uid1.trim().isEmpty()) {
            vObean.setCode(500);
            vObean.setMsg("请先登录");
            return vObean;
        }
        Boolean aBoolean = foodInfoBiz.AddFood(fid, num, uid1);
        if (aBoolean) {
            vObean.setCode(200);
            vObean.setMsg("添加成功");
            return vObean;
        } else {
            vObean.setCode(500);
            vObean.setMsg("添加失败");
            return vObean;
        }

    }


}
