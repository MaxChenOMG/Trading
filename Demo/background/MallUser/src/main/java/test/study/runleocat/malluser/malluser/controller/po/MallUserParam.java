package test.study.runleocat.malluser.malluser.controller.po;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MallUserParam {
    @NotBlank(message = "login user name should not be null")
    private String mallUserName;
    private String mallUserRealName;
    @NotBlank(message = "login user password should not be null")
    private String mallUserPassword;
    private String mallUserGender;
}
