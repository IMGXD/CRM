package com.crm.service;

import com.crm.dao.UserDao;
import com.crm.po.User;
import com.crm.utils.MD5Util;
import com.crm.vo.ServerResponse;
import com.crm.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IMGXD on 2017/8/10.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public ServerResponse login(String userName, String password){
        if(StringUtils.isBlank(userName)){
            return new ServerResponse(0,"用户名不能为空");
        }
        if(StringUtils.isBlank(password)){
            return new ServerResponse(0,"密码不能为空");
        }

        User user=userDao.login(userName);
        if(null==user){
            return new ServerResponse(0,"用户名或账号错误");
        }
        String md5pwd= MD5Util.md5Method(password);
        if (md5pwd.equals(user.getPassword())){
            return new ServerResponse(1,"登陆成功",user);
        }else {
            return new ServerResponse(0,"登陆失败");
        }
    }

    public List<UserVO> listUserManager(){
        return  userDao.listUserManager();
    }
}
