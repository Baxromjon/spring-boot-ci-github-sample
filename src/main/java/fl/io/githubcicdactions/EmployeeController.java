package fl.io.githubcicdactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/add")
    public HttpEntity<?> save(@RequestBody Employee employee) {
        Employee save = employeeRepository.save(employee);
        return ResponseEntity.ok(save);
    }

    @GetMapping("/get")
    public HttpEntity<?> getAll() {
        List<Employee> all = employeeRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @PostMapping("/edit/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody EmployeeDTO employee) {
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee1 = optional.get();
        employee1.setDept(employee.getDept());
        employee1.setName(employee.getName());
        employee1.setSalary(employee.getSalary());
        employeeRepository.save(employee1);
        return ResponseEntity.ok(employee1);
    }

    @GetMapping("/get_one/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        Employee employee = employeeRepository.findById(id).orElseGet(Employee::new);
        return ResponseEntity.ok(employee);
    }
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        employeeRepository.deleteById(id);
        return ResponseEntity.ok("Successfully deleted");
    }
}
