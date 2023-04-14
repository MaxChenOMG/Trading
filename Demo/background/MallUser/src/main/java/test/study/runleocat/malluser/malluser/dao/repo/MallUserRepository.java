package test.study.runleocat.malluser.malluser.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import test.study.runleocat.malluser.malluser.dao.entity.MallUser;

public interface MallUserRepository extends JpaRepository<MallUser, Integer> {
    MallUser findByMallUserNameEquals(String mallUserName);
}