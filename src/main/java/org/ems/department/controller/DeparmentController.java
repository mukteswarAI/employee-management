package org.ems.department.controller;

import lombok.extern.slf4j.Slf4j;
import org.ems.department.model.Department;
import org.ems.department.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class DeparmentController {

    private DepartmentService departmentService;
    public DeparmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/deptlist")
    public String departmentList(Model model) {
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "department-table";

    }


    @RequestMapping(value = "/addDepartment", method = { RequestMethod.POST })
    public String departmentAdd( @RequestParam("departmentName") String departmentName,
                                 @RequestParam("description") String description) {

//        System.out.println(departmentName + " " + description);

        Department newDept = new Department();
        newDept.setDepartmentName(departmentName);
        newDept.setDescription(description);

        departmentService.addDepartment(newDept);

        return "redirect:/deptlist";


    }

    @GetMapping("/saveDept")
    public String addDepartment() {
        return "AddDept";
    }

    @GetMapping("/updateDept")
    public String updateDepartment(Model model,@RequestParam("deptid") String id) {
        Department department=departmentService.getDepartmentById(id);
        model.addAttribute("department", department);
        return "EditDepartmentForm";
    }

    @PostMapping("/updateDept")
    public String updateDepartmentDetail(@ModelAttribute Department department) {
        departmentService.updateDepartment(department);
        return "redirect:/deptlist";
    }

    @GetMapping("/deleteDept")
    public String deleteDepartment(@RequestParam("deptid") String id) {
        departmentService.deleteDepartment(id);
        return "redirect:/deptlist";
    }





}
