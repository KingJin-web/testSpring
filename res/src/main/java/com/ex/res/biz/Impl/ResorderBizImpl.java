package com.ex.res.biz.Impl;

import com.ex.res.bean.Resorder;
import com.ex.res.bean.Resorderitem;
import com.ex.res.biz.ResorderBiz;
import com.ex.res.enums.OrderStatusEnum;
import com.ex.res.repository.ResorderDao;
import com.ex.res.repository.ResorderitemDao;
import com.ex.res.vo.Cartitem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Map;

@Service
@Transactional
public class ResorderBizImpl implements ResorderBiz {

    @Autowired
    private ResorderDao resorderDao;
    @Autowired
    private ResorderitemDao resorderitemDao;

    @Override
    public Integer completeOrder(Resorder resorder, Map<Integer, Cartitem> shopCart) {
        resorder.setStatus(OrderStatusEnum.NEW.getCode());

        resorder.setOrdertime(new Timestamp(System.currentTimeMillis()));
        resorder.setDeliverytime(resorder.getDeliveryTime());

        Resorder orderResult = null;
        if (shopCart != null && shopCart.size() > 0) {
            orderResult = resorderDao.save(resorder);//订单号
            for (Map.Entry<Integer, Cartitem> entry : shopCart.entrySet()) {
                Resorderitem resorderitem = new Resorderitem();

                resorderitem.setRoid(orderResult.getRoid());//订单编号
                resorderitem.setNum(entry.getValue().getNum());
                resorderitem.setFid(entry.getKey());
                resorderitem.setDealprice(entry.getValue().getResfood().getRealprice());
                resorderitemDao.save(resorderitem);
            }
        }
        return orderResult.getRoid();
    }
}
