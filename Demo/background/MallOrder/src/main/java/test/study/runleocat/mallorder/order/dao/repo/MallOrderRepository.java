package test.study.runleocat.mallorder.order.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import test.study.runleocat.mallorder.order.dao.entity.MallOrder;

import java.util.List;

public interface MallOrderRepository extends JpaRepository<MallOrder, Long> {
    List<MallOrder> findByMallUserNameEquals(String mallUserName);

    @Query("select m from MallOrder m where m.mallUserName = :mallUserName")
    MallOrder findByMallUserName(@Param("mallUserName") String mallUserName);
}