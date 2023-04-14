package test.study.runleocat.mallorder.order.controller.po;

import lombok.Data;

@Data
public class MallUserParam {
//    @NotBlank(message = "login user name should not be null")
    private String mallUserName;
    private String mallUserRealName;
//    @NotBlank(message = "login user password should not be null")
    private String mallUserPassword;
    private String mallUserGender;
    private Long mallGoodId;
}
