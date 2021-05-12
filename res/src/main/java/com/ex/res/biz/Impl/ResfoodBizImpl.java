package com.ex.res.biz.Impl;

import com.ex.res.bean.Resfood;
import com.ex.res.biz.ResfoodBiz;
import com.ex.res.repository.ResfoodDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ResfoodBizImpl implements ResfoodBiz {

    @Autowired
    private ResfoodDao resfoodDao;

    @Override
    @Transactional(readOnly = true)
    public List<Resfood> findAll() {
        return resfoodDao.findAll();
    }

    @Override
    public List<Resfood> findAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "fid");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Resfood> list = resfoodDao.findAll(pageable);
        System.out.println(list.getTotalPages());
        return list.getContent();
    }

    @Override
    @Transactional(readOnly = true)
    public Resfood findByFid(Integer fid) {
        Resfood resfood = new Resfood();
        resfood.setFid(fid);
        Example<Resfood> example = Example.of(resfood);
        Optional<Resfood> opt = resfoodDao.findOne(example);
        return opt.get();
    }
}
