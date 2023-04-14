package test.study.runleocat.mallorder.order.service.impl;

import org.springframework.stereotype.Service;
import test.study.runleocat.mallorder.order.dao.entity.MallOrder;
import test.study.runleocat.mallorder.order.dao.repo.MallOrderRepository;
import test.study.runleocat.mallorder.order.service.MallOrderService;
import test.study.runleocat.mallorder.utils.ServiceResult;

import javax.annotation.Resource;
import java.util.List;
@Service
public class MallOrderServiceImpl implements MallOrderService {
    @Resource
    MallOrderRepository mallOrderRepository;
    @Override
    public List<MallOrder> findMallOrdersByMallUserName(String mallUserName) {
        return mallOrderRepository.findByMallUserNameEquals(mallUserName);
    }

    @Override
    public ServiceResult saveMallUserOrder(String mallUserName, Long mallGoodId) {
        MallOrder mallOrder = new MallOrder();
        mallOrder.setMallGoodId(mallGoodId);
        mallOrder.setMallUserName(mallUserName);
        MallOrder returnMallOrder = mallOrderRepository.save(mallOrder);
        if((returnMallOrder != null)&(returnMallOrder.getMallOrderId() != null)){
            return ServiceResult.success(returnMallOrder);
        }else{
            return ServiceResult.error("701","order add issue.");
        }
    }
}
