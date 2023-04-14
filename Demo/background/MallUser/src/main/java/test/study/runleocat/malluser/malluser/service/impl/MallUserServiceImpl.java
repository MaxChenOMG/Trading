package test.study.runleocat.malluser.malluser.service.impl;

import org.springframework.stereotype.Service;
import test.study.runleocat.malluser.malluser.dao.entity.MallUser;
import test.study.runleocat.malluser.malluser.dao.repo.MallUserRepository;
import test.study.runleocat.malluser.malluser.service.MallUserService;
import test.study.runleocat.malluser.utils.ServiceResult;

import javax.annotation.Resource;

@Service
public class MallUserServiceImpl implements MallUserService {
    @Resource
    MallUserRepository mallUserRepository;


    @Override
    public ServiceResult doLogin(String mallUserName, String mallUserPassword) {
        System.out.println(mallUserName);
        MallUser mallUser = mallUserRepository.findByMallUserNameEquals(mallUserName);
        if(mallUser == null){
            return  ServiceResult.error("305","wrong password or user name");
        }
        if(!mallUserPassword.equals(mallUser.getMallUserPassword())){
            return  ServiceResult.error("305","wrong password or user name");
        }else{
            return ServiceResult.success(mallUser);
        }
    }

    @Override
    public boolean mallUserRegister(String mallUserName, String mallUserRealName, String mallUserPassword, String mallUserGender) {
        MallUser saveUser = new MallUser();
        saveUser.setMallUserGender(mallUserGender);
        saveUser.setMallUserName(mallUserName);
        saveUser.setMallUserPassword(mallUserPassword);
        saveUser.setMallUserRealName(mallUserRealName);
        mallUserRepository.save(saveUser);

        return true;
    }

    @Override
    public MallUser findUserByUserName(String mallUserName) {
        return mallUserRepository.findByMallUserNameEquals(mallUserName);
    }
}
