package com.incs.crudapplication.Controller;/*
 * Created by Vaishnavi Chaurasia
 * 03-Mar-22
 * 4:32 PM
 * crudapplication
 */

import com.incs.crudapplication.Request.DepartmentRequest;
import com.incs.crudapplication.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;


    @PostMapping
    public boolean insertIntoDepartmentTable(@Validated @RequestBody DepartmentRequest departmentRequest) {

        return departmentService.insertIntoDepartmentTable(departmentRequest);
    }

    @GetMapping("/{deptId}")
    public boolean retrieveDepartmentData(@PathVariable int deptId) {
        return departmentService.retrieveDepartmentData(deptId);
    }

}
