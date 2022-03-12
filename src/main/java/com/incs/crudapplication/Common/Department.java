package com.incs.crudapplication.Common;/*
 * Created by Vaishnavi Chaurasia
 * 03-Mar-22
 * 3:13 PM
 * crudapplication
 */


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "department", schema = "corporation")
public class Department implements Serializable {

    @Id
    @Column(name = "dept_id")
    private int deptId;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "dept_city")
    private String deptCity;


    //@JoinColumn(name="deptId")
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Employee> employeeSet;


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

    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }
}
