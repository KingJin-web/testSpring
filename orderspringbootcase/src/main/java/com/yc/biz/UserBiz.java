package com.yc.biz;

import com.yc.bean.ResUser;
import com.yc.dao.ResUserMapperImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Service
@Validated
public class UserBiz {

    @Autowired
    private ResUserMapperImp userMapperImp;

    @Autowired
    private ResUser resUser;

    public Boolean checkuser(@NotNull(message = "用户名不能为空") String username,
                             @NotNull(message = "密码不能空") String pwd
                            ) throws Exception{
            resUser.setUsername(username);
            resUser.setPwd(pwd);
            int i = userMapperImp.QueryUserByUsernameAndPwd(resUser);
            if(i==1){
                return true;
            }
        return false;
    }

    public int getuserid(@NotNull(message = "用户名不能为空") String username,
                         @NotNull(message = "密码不能空") String pwd){
        resUser.setUsername(username);
        resUser.setPwd(pwd);
        int queruserid = userMapperImp.queruserid(resUser);
        return queruserid;

    }
}
