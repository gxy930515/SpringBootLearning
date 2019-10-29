package com.forezp.shardingjdbcdbmstbl.web;


import com.forezp.shardingjdbcdbmstbl.entity.User;
import com.forezp.shardingjdbcdbmstbl.entity.UserInfo;
import com.forezp.shardingjdbcdbmstbl.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class UserController {

    Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Object list() {
        List<User> list= userService.list();
        //Collections.sort(list);
        return list;
    }

    @GetMapping("/userInfos")
    public Object listinfo() {
        List<UserInfo> list= userService.listInfo();
        //Collections.sort(list);
        return list;
    }

    @GetMapping("/aaa")
    public Object aaa() {
        //Collections.sort(list);
        userService.addUserInfoandUser();
        return 0    ;
    }

    @GetMapping("/add")
    public Object add() {

        for(int i=1;i<101;i++) {
            User user = new User();
            user.setId(i);
            user.setUsername("forezp"+(i));
            user.setPassword("1233edwd");
           long resutl=   userService.addUser(user);
            logger.info("insert:"+user.toString()+" result:"+resutl);
        }
        return "ok";
    }


    @GetMapping("/addUserInfo")
    public Object addUserInfo() {
        for (int i=1;i<10;i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(i);
            userInfo.setAge(i);
            userInfo.setName("Tom"+i);
            userService.addUserInfo(userInfo);
        }
        return "ok";
    }


    @GetMapping("/addBash")
    public Object addBash() {
        userService.addUserBash();
        return "ok";
    }
    @GetMapping("/delete")
    public Object delete() {
    userService.deleteAll();
        return "ok";
    }
}
