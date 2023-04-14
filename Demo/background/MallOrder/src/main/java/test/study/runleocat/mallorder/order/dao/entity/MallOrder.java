package test.study.runleocat.mallorder.order.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class MallOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mall_order_id", nullable = false)
    private Long mallOrderId;

    @Column(name = "mall_good_id", nullable = false)
    private Long mallGoodId;
    @Column(name = "mall_user_name", nullable = false)
    private String mallUserName;
}
