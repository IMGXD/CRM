package com.crm.controller;

import com.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IMGXD on 2017/8/10.
 */
@Controller
public class IndexController extends BaseController{
    @RequestMapping("index")
    public  String index(){
return "index";
    }

    @RequestMapping("main")
    public  String main(HttpServletRequest request, Model model){
        return "main";
    }
}
