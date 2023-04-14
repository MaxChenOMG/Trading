package test.study.runleocat.mallwarehouse.good.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import test.study.runleocat.mallwarehouse.good.dao.entity.MallGood;

import java.util.Collection;
import java.util.List;

public interface MallGoodRepository extends JpaRepository<MallGood, Long> {
    List<MallGood> findByMallGoodIdIn(List<Long> mallGoodIds);
}