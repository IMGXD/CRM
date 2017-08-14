package com.crm.dao;

import com.crm.dao.provider.SaleChanceProvider;
import com.crm.dto.SaleChanceQuery;
import com.crm.po.SaleChance;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import javax.annotation.Generated;
import java.util.List;

/**
 * Created by IMGXD on 2017/8/10.
 */
public interface SaleChanceDao {
    @SelectProvider(type =SaleChanceProvider.class,method = "queryByParamsForPage")
    List<SaleChance> queryByParamsForPage(SaleChanceQuery saleChanceQuery, PageBounds pageBounds);

    @InsertProvider(type = SaleChanceProvider.class,method = "insertSingle")
    @Options(useGeneratedKeys = true)
    int insertSingle(SaleChance saleChance);
}
