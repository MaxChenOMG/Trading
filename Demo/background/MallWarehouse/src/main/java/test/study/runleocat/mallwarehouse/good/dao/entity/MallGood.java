package test.study.runleocat.mallwarehouse.good.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
//@Table(name = "mall_good_hyx")
@Table(name = "mall_good_locus")
public class MallGood {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mall_good_id", nullable = false)
    private Long mallGoodId;
    @Column(name = "mall_good_name", nullable = false)
    private String mallGoodName;
    @Column(name = "mall_good_price", nullable = false)
    private String mallGoodPrice;
    @Column(name = "mall_good_type", nullable = false)
    private String mallGoodType;
    @Column(name = "mall_good_area", nullable = false)
    private String mallGoodArea;
    @Column(name = "mall_good_status", nullable = false)
    private String mallGoodStatus;

}
