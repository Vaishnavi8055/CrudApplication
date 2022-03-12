package com.incs.crudapplication.Request;/*
 * Created by Vaishnavi Chaurasia
 * 03-Mar-22
 * 10:34 PM
 * crudapplication
 */

// User Input from postman

public class DepartmentRequest {

    private int deptId;
    private String deptName;
    private String deptCity;

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCity() {
        return deptCity;
    }

    public void setDeptCity(String deptCity) {
        this.deptCity = deptCity;
    }
}
