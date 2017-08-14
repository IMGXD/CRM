package com.crm.dao.provider;

import com.crm.dto.SaleChanceQuery;
import com.crm.po.SaleChance;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


/**
 * Created by IMGXD on 2017/8/10.
 */
public class SaleChanceProvider {
    private static Logger logger = LoggerFactory.getLogger(SaleChanceProvider.class);

    public String queryByParamsForPage(final SaleChanceQuery saleChanceQuery) {
        String string = new SQL() {{
            SELECT("t.id, t.customer_name, t.overview, t.link_man, t.link_phone, t.create_man, "
                    + " t.create_date, t.assign_man,t.assign_time,t.state");
            FROM("t_sale_chance t");
            WHERE("is_valid = 1");
            if (StringUtils.isNoneBlank(saleChanceQuery.getCustomerName())) {
                AND().WHERE("customer_name like  concat ('%',#{customerName},'%')");
            }
            if (StringUtils.isNoneBlank(saleChanceQuery.getOverview())) {
                AND().WHERE("overview like concat ('%',#{overview},'%')");
            }
            if (StringUtils.isNoneBlank(saleChanceQuery.getCreateMan())) {
                AND().WHERE("create_man like concat ('%',#{createMan},'%')");
            }
            if (null != saleChanceQuery.getState()) {
                AND().WHERE("state=#{state}");
            }
        }}.toString();
        logger.debug(string);
        return string;
    }

    public String insertSingle(final SaleChance saleChance) {
        String string = new SQL() {
            {
                INSERT_INTO("t_sale_chance");
                VALUES("chance_source", "#{chanceSource}");
                VALUES("customer_id", "#{customerId}");
                VALUES("customer_name", "#{customerName}");
                VALUES("cgjl", "#{cgjl}");
                VALUES("overview", "#{overview}");
                VALUES("link_man", "#{linkMan}");
                VALUES("link_phone", "#{linkPhone}");
                VALUES("description", "#{description}");
//                VALUES("create_man", "{createMan}");
                VALUES("assign_man", "#{assignMan}");
                VALUES("assign_time", "#{assignTime}");
                VALUES("state", "#{state}");
                VALUES("dev_result", "#{devResult}");
                VALUES("is_valid", "1");
                VALUES("create_date", "now()");
                VALUES("update_date", "now()");
            }
        }.toString();
        logger.debug("插入的SQL:{}",string);
        return string;
    }
}
