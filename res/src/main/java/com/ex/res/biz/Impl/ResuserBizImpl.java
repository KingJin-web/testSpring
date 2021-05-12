package com.ex.res.biz.Impl;

import com.ex.res.bean.Resuser;
import com.ex.res.biz.ResuserBiz;
import com.ex.res.repository.ResuserDao;
import com.ex.res.utils.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Supplier;

@Service
public class ResuserBizImpl implements ResuserBiz {

    @Autowired
    private ResuserDao resuserDao;

    @Override
    @Transactional(readOnly = true)
    public Resuser login(Resuser resuser) {
        resuser.setPwd(Encrypt.md5(resuser.getPwd()));//在业务层完成原始密码的加密
        Example<Resuser> example = Example.of(resuser);
        Optional<Resuser> opt = resuserDao.findOne(example);
        return opt.orElseGet(new Supplier<Resuser>() {
            @Override
            public Resuser get() {
                return null;
            }
        });
    }
}
