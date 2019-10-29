package com.forezp.shardingjdbcdbmstbl.service;



import com.forezp.shardingjdbcdbmstbl.entity.User;
import com.forezp.shardingjdbcdbmstbl.entity.UserInfo;



import java.util.List;

public interface UserService {

    Integer addUser(User user);


    Integer addUserInfo(UserInfo userInfo);

    List<User> list();

    List<UserInfo> listInfo();

    void deleteAll();


    Integer addUserBash();


    Integer addUserInfoandUser();
}
