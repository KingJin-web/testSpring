package com.yc.controller;

import com.yc.bean.VObean;
import com.yc.biz.OrderBiz;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@Api(description = "这是下单相关功能接口")
public class OrderController {

    @Autowired
    private OrderBiz orderBiz;
    @Autowired
    private VObean vObean;

    @PostMapping("/o/t1")
    @ApiOperation(value = "这是确认订单的controller",notes = "生成订单 订单明细 以及删除购物车中对应选中的记录")
    @ApiImplicitParams({@ApiImplicitParam(name = "address",value = "订单地址",required = true,dataType = "String"),
                        @ApiImplicitParam(name = "tel",value = "用户电话",required = true,dataType = "String"),
                        @ApiImplicitParam(name = "ps",value = "用户备注信息",required = true,dataType = "String"),
                        @ApiImplicitParam(name = "roitid",value = "用户选中菜品的id 要求给我的是xx,xx 例如1,2",required = true,dataType = "String")})
    public VObean Test1(HttpSession session,
                        @RequestParam("address") String address,
                        @RequestParam("tel") String tel,
                        @RequestParam("ps") String ps,
                        @RequestParam("roitid") String roitid){
        String uid = (String) session.getAttribute("uid");
        if(uid==null ||uid.trim().isEmpty()){
            vObean.setCode(500);
            vObean.setMsg("请先登录");
            return vObean;
        }
        Boolean aBoolean = orderBiz.order(uid, address, tel, ps, roitid);
        if(aBoolean){
            vObean.setCode(200);
            vObean.setMsg("下单成功");
            return vObean;
        }else {
            vObean.setCode(500);
            vObean.setMsg("下单失败 请重试");
            return vObean;
        }

    }

    @PostMapping("o/t2")
    @ApiOperation(value = "得到某个用户购物车的信息" ,notes = "根据用户登录完后的id 进而得到他的购物车信息")
    public VObean Test2(HttpSession session){
        String uid = (String) session.getAttribute("uid");
        if(uid==null ||uid.trim().isEmpty()){
            vObean.setCode(500);
            vObean.setMsg("请先登录");
            return vObean;
        }
        List<Map<String, Object>> maps = orderBiz.getcartinfo(uid);

        vObean.setCode(200);
        vObean.setData(maps);
        vObean.setMsg("ok");
        return vObean;
    }

    @PostMapping("o/t3")
    @ApiOperation(value = "这是修改购车中菜品数量的controller",notes = "根据当前id和菜品的id 修改它的数量 当数量为1不能修改")
    @ApiImplicitParam(name = "roitid",value = "菜品id ",required = true,type = "String")
    public VObean Test3(HttpSession session,
                        @RequestParam("roitid") String roitid){
        String uid = (String) session.getAttribute("uid");

        Boolean aBoolean = orderBiz.updnowcartnum(uid, roitid);
        if(aBoolean){
            vObean.setCode(200);
            vObean.setMsg("修改成功");
            return vObean;
        }else{
            vObean.setCode(500);
            vObean.setMsg("修改失败");
            return vObean;
        }
    }

    @PostMapping("o/t4")
    @ApiOperation(value = "这是修改购车中菜品数量的controller",notes = "根据当前id和菜品的id 修改它的数量 当数量为1不能修改")
    @ApiImplicitParam(name = "roitid",value = "菜品id ",required = true,type = "String")
    public VObean Test4(HttpSession session,
                        @RequestParam("roitid") String roitid){
        String uid = (String) session.getAttribute("uid");

        Boolean aBoolean = orderBiz.updnowcartnum2(uid, roitid);
        if(aBoolean){
            vObean.setCode(200);
            vObean.setMsg("修改成功");
            return vObean;
        }else{
            vObean.setCode(500);
            vObean.setMsg("修改失败");
            return vObean;
        }
    }

    @PostMapping("o/t5")
    @ApiOperation(value = "这是修改购车中菜的controller",notes = "根据菜品的id 删除菜品")
    @ApiImplicitParam(name = "roitid",value = "菜品id ",required = true,type = "String")
    public VObean Test5(@RequestParam("roitid") String roitid,VObean vObean){
        Boolean aBoolean = orderBiz.delcartfoods(roitid);
        if(aBoolean){
            vObean.setCode(200);
            vObean.setMsg("删除成功");
            return vObean;
        }else{
            vObean.setCode(500);
            vObean.setMsg("删除失败");
            return vObean;
        }
    }

}
