package com.crm.service;

import com.crm.dao.CustomerDao;
import com.crm.vo.CustomerVO;
import com.crm.vo.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IMGXD on 2017/8/13.
 */
@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;

    public List<CustomerVO> listAll(){
        List<CustomerVO> list=customerDao.queryAll();
        return list;
    }
}
