package test.study.runleocat.mallwarehouse.good.controller;

import org.springframework.web.bind.annotation.*;
import test.study.runleocat.mallwarehouse.good.controller.po.MallGoodsParam;
import test.study.runleocat.mallwarehouse.good.dao.entity.MallGood;
import test.study.runleocat.mallwarehouse.good.service.MallGoodsService;
import test.study.runleocat.mallwarehouse.utils.Result;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/mallgoods-manage-api/v1")
public class MallGoodsController {
    @Resource
    MallGoodsService mallGoodsService;

    @RequestMapping(value = "/getMallGoodsByPost", method = RequestMethod.POST)
    @ResponseBody
    public Result getMallGoodsByPost(@RequestBody MallGoodsParam mallGoodsParam){
        System.out.println("getMallGoodsByPost");
        System.out.println(mallGoodsParam.getMallGoodsList());
        List<MallGood> returnMallGoods = mallGoodsService.findByMallGoodIdIn(mallGoodsParam.getMallGoodsList());
        System.out.println(returnMallGoods);
        return Result.success(returnMallGoods);
    }


    @RequestMapping(value = "/getMallGoodsAllByGet", method = RequestMethod.GET)
    @ResponseBody
    public Result getMallGoodsAllByGet(){
        List<MallGood> mallGoods = mallGoodsService.findAllMallGoods();
        return Result.success(mallGoods);
    }

}
