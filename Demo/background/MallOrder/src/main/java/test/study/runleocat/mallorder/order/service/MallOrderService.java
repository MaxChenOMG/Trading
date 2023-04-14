package test.study.runleocat.mallorder.order.service;

import test.study.runleocat.mallorder.order.dao.entity.MallOrder;
import test.study.runleocat.mallorder.utils.ServiceResult;

import java.util.List;

public interface MallOrderService {
    public List<MallOrder> findMallOrdersByMallUserName(String mallUserName);

    ServiceResult saveMallUserOrder(String mallUserName, Long mallGoodId);
}
