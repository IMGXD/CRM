package com.crm.controller;

import com.base.BaseController;
import com.crm.service.UserService;
import com.crm.vo.ServerResponse;
import com.crm.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by IMGXD on 2017/8/10.
 */

@Controller
@RequestMapping("user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    @RequestMapping("login")
    @ResponseBody
    public ServerResponse login(String userName,String password){
        return userService.login(userName,password);
    }

    @RequestMapping("find_customer_manager")
    @ResponseBody
    public List<UserVO> listUserManager(){
        return userService.listUserManager();
    }
}
