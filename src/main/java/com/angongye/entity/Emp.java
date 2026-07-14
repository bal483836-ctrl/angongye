package com.angongye.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Emp implements Serializable {
    private Integer empId;
    private String empNo;
    private String empName;
    private Integer empDeptId;
    private String empSex;
    private String empEducation;
    private String empEmail;
    private String empPhone;
    private Date empEntryTime;
    private Date empCreateTime;

    //数据库变化后，新增的字段
    private Integer empLoginId;

    //非数据库字段：关联查询出的部门名称，用于列表/详情页展示（不参与增删改）
    private String deptName;


    public Emp(Integer empId){
        this.empId = empId;
    }
    public Emp(Integer empId,String empNo){
        this.empId = empId;
        this.empNo = empNo;
    }


}
