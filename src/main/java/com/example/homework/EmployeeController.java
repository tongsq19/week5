package com.example.homework;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employees")
    List<Employee> all() {
        return repository.findAll();
    }

    @PostMapping("/employee")
    Employee addEmployee(@RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }

    @GetMapping("/employee/{id}")
    Employee queryEmployee(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PutMapping("/employee/{id}")
    Employee modifyEmployee(@RequestBody Employee newEmployee, @PathVariable Long id){
        Employee employee = repository.findById(id)
                .orElseThrow(RuntimeException::new);

        employee.setAge(newEmployee.getAge());
        employee.setName(newEmployee.getName());
        employee.setGender(newEmployee.getGender());
        return repository.save(employee);
    }

    @DeleteMapping("/employee/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }

}