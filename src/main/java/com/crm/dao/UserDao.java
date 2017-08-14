package com.crm.dao;

import com.crm.po.User;
import com.crm.vo.UserVO;
import org.apache.ibatis.annotations.Select;
import org.w3c.dom.ls.LSException;

import java.util.List;

/**
 * Created by IMGXD on 2017/8/10.
 */
public interface UserDao {
    @Select("select id,user_name, password,true_name, email, "
            + " phone, is_valid, create_date, update_date from t_user ")
    List<User> find();
    @Select("select id,user_name, password,true_name, email, "
            + " phone, is_valid, create_date, update_date from t_user  where user_name = #{userName}")
    User login(String userName);

    @Select("SELECT t1.id, t1.true_name FROM t_user t1 LEFT JOIN t_user_role t2  on t1.id = t2.user_id WHERE t2.role_id = 3")
    List<UserVO> listUserManager();
}
