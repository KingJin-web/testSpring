package com.king.spring_rdering.mapper;

import com.king.spring_rdering.bean.ResOrder;

import com.king.spring_rdering.vo.roidVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ResOrderRepository extends JpaRepository<ResOrder,Integer> {
//    @Query(value = "select new com.king.spring_rdering.vo.roidVO" +
//            "(o.fid,o.fid,o.num,o.dealprice,f.fname) " +
//            "FROM res_order_item o left JOIN res_food f on o.fid = f.fid")
//    Page<roidVO> findCustom();

}
