package com.forezp.shardingjdbcdbmstbl.service.impl;


import com.forezp.shardingjdbcdbmstbl.entity.User;
import com.forezp.shardingjdbcdbmstbl.entity.UserInfo;
import com.forezp.shardingjdbcdbmstbl.repository.UserInfoRepository;
import com.forezp.shardingjdbcdbmstbl.repository.UserRepository;
import com.forezp.shardingjdbcdbmstbl.service.UserService;
import io.shardingsphere.core.constant.transaction.TransactionType;
import io.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl  implements UserService {

    Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    // 两阶段事务（支持夸库事务）
    @Override
    @ShardingTransactionType(TransactionType.XA)
    @Transactional(rollbackFor = Throwable.class)
    public Integer addUser(User user) {

        // 强制路由主库
        //HintManager.getInstance().setMasterRouteOnly();
        return userRepository.addUser(user);
    }

    @Resource
    UserRepository userRepository;

    @Resource
    UserInfoRepository userInfoRepository;



    // 两阶段事务（支持夸库事务）
    @Override
    @ShardingTransactionType(TransactionType.XA)
    @Transactional(rollbackFor = Throwable.class)
    public Integer addUserInfo(UserInfo userInfo) {

        // 强制路由主库
        //HintManager.getInstance().setMasterRouteOnly();
        return userInfoRepository.addUserInfo(userInfo);
    }

    @Override
    public List<User> list() {
        return userRepository.list();
    }
    @Override
    public List<UserInfo> listInfo() {
        return userInfoRepository.list();
    }


    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    //@ShardingTransactionType(TransactionType.XA)
    @Transactional(rollbackFor = Throwable.class)
    public Integer addUserBash() {

        // 强制路由主库
        //HintManager.getInstance().setMasterRouteOnly();
        Integer resutl = 0;
        for(int i=121;i<201;i++) {
            User user = new User();
            user.setId(i);
            user.setUsername("forezp"+(i));
            user.setPassword("1233edwd");
            resutl +=   userRepository.addUser(user);
            if (i == 140) {
                int a = i/0;
            }
            logger.info("insert:"+user.toString()+" result:"+resutl);
        }
        return resutl;

    }

    @Override
    @ShardingTransactionType(TransactionType.XA)
    @Transactional(rollbackFor = Throwable.class)
    public Integer addUserInfoandUser() {
        Integer resutl = 0;
        for (int i=150;i<300;i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(i);
            userInfo.setAge(i);
            userInfo.setName("Tom"+i);
            userInfo.setUserId(i);
            resutl += userInfoRepository.addUserInfo(userInfo);
            User user = new User();
            user.setId(i);
            user.setUsername("forezp"+(i));
            user.setPassword("1233edwd");
            resutl +=   userRepository.addUser(user);

        }

        return resutl;
    }

}
