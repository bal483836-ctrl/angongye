package com.angongye.mapper;

import com.angongye.entity.Login;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    Login getLoginByName(String loginName);

    int updateStatusAndErrorById(Login login);
    int saveBackId(Login login);
}
