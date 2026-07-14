package com.angongye.service;



import com.angongye.entity.Login;
import com.angongye.module.MyResponse;

import java.util.List;

public interface LoginService {
    MyResponse login(Login loginTemp);
    //注册
    MyResponse register(String userName,String pwd,int empId);

}
