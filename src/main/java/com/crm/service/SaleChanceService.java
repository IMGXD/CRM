package com.crm.service;

import com.crm.dao.SaleChanceDao;
import com.crm.dto.SaleChanceDto;
import com.crm.dto.SaleChanceQuery;
import com.crm.po.SaleChance;
import com.crm.utils.AssertUtil;
import com.crm.vo.ServerResponse;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IMGXD on 2017/8/10.
 */
@Service
public class SaleChanceService {
    @Autowired
    private SaleChanceDao saleChanceDao;
    public static Logger logger = LoggerFactory.getLogger(SaleChanceService.class);

    public Map<String, Object> queryByParamsForPage(SaleChanceQuery saleChanceQuery) {
        Integer page = saleChanceQuery.getPage();
        Integer pageSize = saleChanceQuery.getRows();
        String sort = saleChanceQuery.getSort();
        if (null == page) {
            page = 1;
        }
        if (null == pageSize) {
            pageSize = 10;
        }
        if (StringUtils.isBlank(sort)) {
            sort = "id.desc";
        }
        PageBounds pageBounds = new PageBounds(page, pageSize, Order.formString(sort), true);
        List<SaleChance> saleChances = saleChanceDao.queryByParamsForPage(saleChanceQuery, pageBounds);
        logger.debug("分页参数:{},{},{}", page, pageSize, Order.formString(sort));
        logger.debug("查询结果:{}", saleChances.size());
        PageList<SaleChance> list = (PageList<SaleChance>) saleChances;
        Paginator paginator = list.getPaginator();
        Map<String, Object> result = new HashMap<>();
        result.put("rows", list);
        result.put("total", list.getPaginator().getTotalCount());
        return result;
    }


    public ServerResponse insertSingle(SaleChanceDto saleChanceDto, String loginUserName){
        // 参数验证
        checkParams(saleChanceDto.getCustomerId(), saleChanceDto.getCustomerName(),
                saleChanceDto.getCgjl());
        // 判断分配状态  根据是否有指定人判断
        String assignMan = saleChanceDto.getAssignMan();
        int state = 0; // 未分配
        Date assignTime = null;
        if (StringUtils.isNoneBlank(assignMan)) {
            state = 1; // 已分配
            assignTime = new Date();
        }
        SaleChance saleChance = new SaleChance();
        BeanUtils.copyProperties(saleChanceDto, saleChance); // 属性拷贝
        saleChance.setAssignTime(assignTime);
        saleChance.setState(state);
        saleChance.setCreateMan(loginUserName);
        int count = saleChanceDao.insertSingle(saleChance);
        logger.debug("插入的记录数为：{}, 主键为：", count, saleChance.getId());
        return new ServerResponse(0,"成功",saleChance.getId());

    }

    private void checkParams(Integer customerId,String cutomerName, Integer cgjl) {
        AssertUtil.intIsNotEmpty(customerId, "请选择客户");
        AssertUtil.isNotEmpty(cutomerName, "请选择客户");
        AssertUtil.intIsNotEmpty(cgjl, "请输入成功几率");
    }
}
