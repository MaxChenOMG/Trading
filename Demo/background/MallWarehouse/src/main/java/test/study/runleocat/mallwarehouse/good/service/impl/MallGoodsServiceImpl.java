package test.study.runleocat.mallwarehouse.good.service.impl;

import org.springframework.stereotype.Service;
import test.study.runleocat.mallwarehouse.good.dao.entity.MallGood;
import test.study.runleocat.mallwarehouse.good.dao.repo.MallGoodRepository;
import test.study.runleocat.mallwarehouse.good.service.MallGoodsService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MallGoodsServiceImpl implements MallGoodsService {
    @Resource
    private MallGoodRepository mallGoodRepository;
    @Override
    public List<MallGood> findByMallGoodIdIn(List<Long> mallGoodIds) {
        return mallGoodRepository.findByMallGoodIdIn(mallGoodIds);
    }

    @Override
    public List<MallGood> findAllMallGoods() {
        return mallGoodRepository.findAll();
    }
}
