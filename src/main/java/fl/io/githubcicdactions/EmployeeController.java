package fl.io.githubcicdactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
