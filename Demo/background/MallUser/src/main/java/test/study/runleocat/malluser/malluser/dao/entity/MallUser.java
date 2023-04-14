package test.study.runleocat.malluser.malluser.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "mall_user")
public class MallUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mall_user_id", nullable = false)
    private Long mallUserId;

    @Column(name = "mall_user_name", nullable = false)
    private String mallUserName;

    @Column(name = "mall_user_realname", nullable = false)
    private String mallUserRealName;

    @Column(name = "mall_user_password", nullable = false)
    private String mallUserPassword;

    @Column(name = "mall_user_gender", nullable = false)
    private String mallUserGender;

}
