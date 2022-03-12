package com.incs.crudapplication.Controller;/*
 * Created by Vaishnavi Chaurasia
 * 03-Mar-22
 * 4:30 PM
 * crudapplication
 */

import com.incs.crudapplication.Common.Employee;
import com.incs.crudapplication.Request.EmployeeRequest;
import com.incs.crudapplication.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// call service here

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public boolean insertIntoEmployeeTable(@RequestBody EmployeeRequest employeeRequest) {
        return employeeService.insertIntoEmployeeTable(employeeRequest);
    }


    @GetMapping("/{empId}")
    public boolean retrieveEmployeeData(@PathVariable long empId) {
        return employeeService.retrieveEmployeeData(empId);
    }

    @DeleteMapping("/{empId}")
    public boolean deleteEmployeeData(@PathVariable long empId) {
        return employeeService.deleteEmployeeData(empId);
    }

    @PutMapping("/{empId}")
    public boolean updateEmployeeTable(@PathVariable long empId) {
        return employeeService.updateEmployeeTable(empId);
    }

    @GetMapping("/maxSalary")
    public List<Employee> getTopFiveEmployeeWithMaxSalary() {
        return employeeService.getTopFiveEmployeeWithMaxSalary();
    }

    @GetMapping("/maxSalaryEmployee")
    public List<Employee> getEmployeeWithMaxSalary() {
        return employeeService.getEmployeeWithMaxSalary();
    }

    @GetMapping("/minSalaryEmployee")
    public List<Employee> getEmployeeWithMinSalary() {
        return employeeService.getEmployeeWithMinSalary();
    }

    @GetMapping("/totalSalaryPaid")
    public List<Employee> getTotalSalaryPaid() {
        return employeeService.getTotalSalaryPaid();
    }

    @GetMapping("/avgSalaryPaid")
    public List<Employee> getAvgSalaryPaidDeptWise() {
        return employeeService.getAvgSalaryPaidDeptWise();
    }

    /************** Error *******************/
    @GetMapping("/salaryGreaterThanAvg")
    public List<Employee> getAvgSalaryGreaterThanAvg() {
        return employeeService.getAvgSalaryGreaterThanAvg();
    }

    /************** Error *******************/
    @GetMapping
    public List<Employee> getEmployeeByCity(@RequestParam String city) {
        return employeeService.getEmployeeByCity(city);
    }

    @GetMapping("empDetailWithDeptName")
    public List<Employee> getEmployeeByCity() {
        return employeeService.getEmployeeDetailWithDeptName();
    }

    @GetMapping("sortBySalaryInAsc")
    public List<Employee> sortSalaryInAsc() {
        return employeeService.sortSalaryInAsc();
    }

    @GetMapping("sortBySalaryInDesc")
    public List<Employee> sortSalaryInDesc() {
        return employeeService.sortSalaryInDesc();
    }

    @GetMapping("empAgeGreaterThanAvg")
    public List<Employee> getEmpWithAgeGreaterThanAvgAge() {
        return employeeService.getEmpWithAgeGreaterThanAvgAge();
    }

}