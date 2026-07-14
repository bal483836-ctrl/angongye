package com.angongye.dao;

import com.angongye.entity.Login;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import static com.angongye.util.common.HrmConstants.LOGINTABLE;

/**
 * 登录账号 DAO 组件（支撑登录 / 注册功能，注解式）。
 */
@Mapper
public interface LoginDao {

    // 根据登录名查询账号（登录名唯一）
    @Select("select * from " + LOGINTABLE + " where login_name = #{loginName}")
    Login selectByName(String loginName);

    // 更新账号状态与错误次数
    @Update("update " + LOGINTABLE + " set login_status = #{loginStatus}, login_error = #{loginError} where login_id = #{loginId}")
    int updateStatusAndErrorById(Login login);

    // 新增账号并回填自增主键 login_id
    @Insert("insert into " + LOGINTABLE + " values (null, #{loginName}, #{loginPwd}, #{loginSalt}, #{loginIndex}, 0, 0)")
    @Options(useGeneratedKeys = true, keyColumn = "login_id", keyProperty = "loginId")
    int save(Login login);
}
