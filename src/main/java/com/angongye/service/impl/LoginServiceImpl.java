package com.angongye.service.impl;

import com.angongye.entity.Emp;
import com.angongye.entity.Login;
import com.angongye.mapper.EmpMapper;
import com.angongye.mapper.LoginMapper;
import com.angongye.module.MyResponse;
import com.angongye.service.LoginService;
import com.angongye.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginMapper loginMapper;
    @Autowired
    EmpMapper empMapper;

    @Override //登录的业务逻辑
    public MyResponse login(Login loginTemp) {
        log.info("=========LoginServiceImpl==============login===============");
        MyResponse result = new MyResponse();
        //判断用户名与密码.loginTemp(明文的密码)，数据库（加密后的密文）
        // 明文加密后进行比较 md5To32String("123456知道","bbbb-数据库",3-数据库中)
        // (1) 通过用户名获得该账户其他信息，（用户名 唯一的）
        Login searchResult = loginMapper.getLoginByName(loginTemp.getLoginName());
        //先看是否已经查到结果
        if(searchResult==null || searchResult.getLoginId()==null){
            //没有对应的账号信息，
            result.setMsg("用户名密码错误");
        }else{
            //有对应的账号,该账号是否被锁定
            if(searchResult.getLoginStatus()!=0){
                //禁用，删除
                result.setMsg("账户被禁用，请联系管理员解锁");
            }else{
                //加密密码，进行比较
                String newPwd = MD5Util.md5To32String(loginTemp.getLoginPwd(), searchResult.getLoginSalt(), searchResult.getLoginIndex());
                if(newPwd.equalsIgnoreCase(searchResult.getLoginPwd())){
                    //登录成功，错误次数清0，保存到数据库中，
                    result.setMsg("登录成功");
                    searchResult.setLoginError(0);
                    result.setTag(searchResult);
                    result.setSuccess(true);

                }else{
                    //登录失败，错误次数+1，如果连续5次错误，改变状态，锁定账户，保存到数据库中，
                    searchResult.setLoginError(searchResult.getLoginError()+1);
                    result.setMsg("用户名密码错误");
                    if(searchResult.getLoginError()>=5){
                        searchResult.setLoginStatus(1);
                        result.setMsg("连续5次密码错误，该账户被锁定，请联系管理员");
                    }
                }
                // 保存到数据库
                loginMapper.updateStatusAndErrorById(searchResult);

            }

        }

        return result;
    }

    @Override
    public MyResponse register(String userName, String pwd, int empId) {
        log.info("=========LoginServiceImpl==============register===============");
        MyResponse result = new MyResponse();
        // 先判断该账户是否已经存在，通过账号直接查询


        Login isExists = loginMapper.getLoginByName(userName);
        if(isExists==null || isExists.getLoginId()==null){
            //账号不存在，可以注册,
            //（1）获得随机字符串作为“盐”，与对应的随机数索引==>加密密码
            String salt = RandomStringUtils.randomAlphabetic(10);
            int index = (int)(MD5Util.TOTAL*Math.random());
            String newPwd = MD5Util.md5To32String(pwd,salt,index);

            //封装数据
            Login login = new Login();
            login.setLoginName(userName)
                    .setLoginPwd(newPwd)
                    .setLoginSalt(salt)
                    .setLoginIndex(index);
            int count = loginMapper.saveBackId(login);//一行受影响
            if(count==1){
                //添加成功,修改emp
                Emp emp = new Emp();
                emp.setEmpId(empId)
                        .setEmpLoginId(login.getLoginId());
                count = empMapper.updateLoginIdByEmpId(emp);
                if(count==1){
                    //两次的对数据库的操作成功
                    result.setSuccess(true);
                    result.setMsg("注册成功");
                }else {
                    // 修改失败
                    throw  new RuntimeException("员工登录Id信息修改失败");
                }


            }else{
                //添加失败
                throw  new RuntimeException("登录信息添加失败");
            }


        }else {
            //账号存在，不可以注册
            result.setMsg("账号存在，不可以注册");
        }

        return result;
    }
}
