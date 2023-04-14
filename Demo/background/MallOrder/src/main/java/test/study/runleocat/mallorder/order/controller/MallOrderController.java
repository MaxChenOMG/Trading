package test.study.runleocat.mallorder.order.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import test.study.runleocat.mallorder.order.controller.po.MallUserParam;
import test.study.runleocat.mallorder.order.dao.entity.MallOrder;
import test.study.runleocat.mallorder.order.service.MallOrderService;
import test.study.runleocat.mallorder.utils.Result;
import test.study.runleocat.mallorder.utils.ServiceResult;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mallorder-manage-api/v1")
public class MallOrderController {
    @Resource
    MallOrderService mallOrderServiceImpl;
    @RequestMapping(value = "/getMallGoodIdsByUserNamePost", method = RequestMethod.POST)
    @ResponseBody
    public Result getMallGoodIdsByUserNamePost(@RequestBody MallUserParam mallUserParam){
        String mallUserName = mallUserParam.getMallUserName();
        List<MallOrder> mallOrders = mallOrderServiceImpl.findMallOrdersByMallUserName(mallUserName);
        List<Long> mallGoodIds = new ArrayList<Long>();
        for (int i = 0; i < mallOrders.size(); i++) {
            MallOrder mallOrder = mallOrders.get(i);
            mallGoodIds.add(mallOrder.getMallGoodId());
        }
        return Result.success(mallGoodIds);

    }
    @RequestMapping(value = "/userAddMallGoodOrderByPut", method = RequestMethod.PUT)
    @ResponseBody
    public Result userAddMallGoodOrderByPut(@RequestBody MallUserParam mallUserParam){

        String mallUserName = mallUserParam.getMallUserName();
        Long mallGoodId = mallUserParam.getMallGoodId();
        ServiceResult sr = mallOrderServiceImpl.saveMallUserOrder(mallUserName,mallGoodId);
        if("0".equals(sr.getCode())){
            return Result.success(sr.getData());
        }else {
            return Result.error(sr.getCode(),sr.getMsg());
        }
    }

}
