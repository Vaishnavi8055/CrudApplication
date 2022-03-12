package com.incs.crudapplication.Common;/*
 * Created by Vaishnavi Chaurasia
 * 03-Mar-22
 * 3:13 PM
 * crudapplication
 */

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee", schema = "corporation")
public class Employee implements Serializable {

    @Id
    @Column(name = "emp_id")
    private long empId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "emp_age")
    private int age;

    @Column(name = "emp_salary")
    private int salary;

    @Column(name = "emp_city")
    private String city;

    /**
     * name is deptId, as it is the foreign Key in Employee table
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deptId", nullable = false)
    private Department department;


    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
