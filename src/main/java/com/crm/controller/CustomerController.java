package com.crm.controller;

import com.crm.service.CustomerService;
import com.crm.vo.CustomerVO;
import com.crm.vo.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by IMGXD on 2017/8/13.
 */
@Controller
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("find_all")
    @ResponseBody
    public List<CustomerVO> lsitAll(){
        return customerService.listAll();
    }
}
