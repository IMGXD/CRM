package com.crm.controller;

import com.base.BaseController;
import com.crm.dto.SaleChanceDto;
import com.crm.dto.SaleChanceQuery;
import com.crm.po.SaleChance;
import com.crm.service.SaleChanceService;
import com.crm.utils.CookieUtil;
import com.crm.vo.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by IMGXD on 2017/8/10.
 */
@Controller
@RequestMapping("sale_chance")
public class SaleChanceController extends BaseController {
    @Autowired
    private SaleChanceService saleChanceService;

    @RequestMapping("index")
    public String index() {
        return "sale_chance";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryByParamsForPage(SaleChanceQuery saleChanceQuery) {
        return saleChanceService.queryByParamsForPage(saleChanceQuery);
    }

    @RequestMapping("add")
    @ResponseBody
    public ServerResponse insertSingle(SaleChanceDto saleChanceDto, HttpServletRequest request) {
        String userName= CookieUtil.getCookieValue(request,"userName");
        return saleChanceService.insertSingle(saleChanceDto,userName);
    }

    public  ServerResponse updateSingle(){
        return saleChanceService.updateSingle();
    }

    public ServerResponse deleteBatch(){
        return saleChanceService.deleteBath();
    }
}
