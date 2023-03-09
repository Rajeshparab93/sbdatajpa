package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/savedata")
    public ResponseEntity<Employee> saveData(@RequestBody Employee employee) {

        return ResponseEntity.ok(employeeServiceImpl.saveData(employee));
    }

    @GetMapping("/getdatabyid/{empId}")
    public ResponseEntity<Optional<Employee>> getDataById(@PathVariable int empId) {

        Employee employee1 = employeeServiceImpl.getDataById(empId).orElseThrow(()-> new RecordNotFoundException("Employee Id Not Exists.."));

        return ResponseEntity.ok(employeeServiceImpl.getDataById(employee1.getEmpId()));
    }

    @GetMapping("/getalldata")
    public ResponseEntity<List<Employee>> getAllData() {

        return ResponseEntity.ok(employeeServiceImpl.getAllData());
    }

    @PutMapping("/updatedata/{empId}")
    public ResponseEntity<Employee> updateData(@RequestBody Employee employee,@PathVariable int empId ) {

        Employee employee1 = employeeServiceImpl.getDataById(empId).orElseThrow(()-> new RecordNotFoundException("Employee Id Not Exists.."));

        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpDOB(employee.getEmpDOB());

        return ResponseEntity.ok(employeeServiceImpl.updateData(employee1));
    }

    @DeleteMapping("/deletedatabyid/{empId}")
    public ResponseEntity<String> deleteDataById(@PathVariable int empId) {

        Employee employee1 = employeeServiceImpl.getDataById(empId).orElseThrow(()-> new RecordNotFoundException("Employee Id Not Exists.."));

        employeeServiceImpl.deleteDataById(employee1.getEmpId());

        return ResponseEntity.ok("Employee Data Delete...");
    }
}
