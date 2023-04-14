package test.study.runleocat.mallwarehouse.good.service;

import test.study.runleocat.mallwarehouse.good.dao.entity.MallGood;

import java.util.List;

public interface MallGoodsService {
    List<MallGood> findByMallGoodIdIn(List<Long> mallGoodIds);

    List<MallGood> findAllMallGoods();
}
