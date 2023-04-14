package test.study.runleocat.malluser.malluser.service;

import test.study.runleocat.malluser.malluser.controller.vo.MallGood;
import test.study.runleocat.malluser.malluser.dao.entity.MallUser;
import test.study.runleocat.malluser.utils.ServiceResult;

import java.util.List;

public interface MallUserService {
    ServiceResult doLogin(String mallUserName, String mallUserPassword);

    boolean mallUserRegister(String mallUserName, String mallUserRealName, String mallUserPassword, String mallUserGender);

    MallUser findUserByUserName(String mallUserName);

}
