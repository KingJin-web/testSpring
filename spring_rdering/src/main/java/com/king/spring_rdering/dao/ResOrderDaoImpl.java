package com.king.spring_rdering.dao;

import com.king.spring_rdering.bean.ResOrder;
import com.king.spring_rdering.bean.ResOrderItem;
import com.king.spring_rdering.mapper.ResOrderItemRepository;
import com.king.spring_rdering.mapper.ResOrderRepository;

import com.king.spring_rdering.vo.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-05-04 16:53
 */
@Repository
public class ResOrderDaoImpl implements ResOrderDao {
    @Autowired
    private ResOrderRepository orderRepository;

    @Autowired
    private ResOrderItemRepository orderItemRepository;

    Example<ResOrder> em;

    @Override
    public List<ResOrder> findAll() {
        // System.out.println(orderRepository.findCustom());;
        return null;
    }

    //@Query(value = "select new com.king.spring_rdering.vo.roidVO(o.fid,o.fid,o.num,o.dealprice,f.fname) FROM res_order_item o left JOIN res_food f on o.fid = f.fid")
    @Override
    public ResOrder findById(int roid) {
        return null;
    }

    @Override
    public void deleteById(int roid) {
        ResOrder resOrder = new ResOrder();
        resOrder.setRoid(roid);
        //em = Example.of(resOrder);
        orderRepository.delete(resOrder);
    }

    @Override
    public Integer completeOrder(ResOrder resOrder, Map<Integer, CartItem> shopCart) {
        ResOrder resOrder1 = null;
        if (shopCart != null && shopCart.size() > 0) {
            resOrder1 = orderRepository.save(resOrder);
            for (Map.Entry<Integer, CartItem> entry :
                    shopCart.entrySet()) {

                ResOrderItem ri = new ResOrderItem();
                ri.setRoid(resOrder1.getRoid());
                ri.setNum(entry.getValue().getNum());
                ri.setFid(entry.getKey());
                ri.setDealprice(entry.getValue().getFood().getRealprice());
                orderItemRepository.save(ri);
                System.out.println("ri " + ri );
            }
            System.out.println(resOrder);

            System.out.println(resOrder1);
        }

        assert resOrder1 != null;
        return resOrder1.getRoid();
    }

}
