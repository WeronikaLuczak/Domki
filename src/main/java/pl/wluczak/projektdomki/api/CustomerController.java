package pl.wluczak.projektdomki.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wluczak.projektdomki.api.dto.CustomerDto;
import pl.wluczak.projektdomki.service.CustomerService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody CustomerDto customerDto, HttpServletRequest request) {

        if (request.getSession() == null || request.getSession().getAttribute("admin") == null ||
                (Boolean)request.getSession().getAttribute("admin") == false) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        customerService.createCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping
    public List<CustomerDto> getCustomers(HttpServletRequest request) {

        if (request.getSession() == null || request.getSession().getAttribute("admin") == null
                || (Boolean) request.getSession().getAttribute("admin") == false) {
            return (List<CustomerDto>) ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        customerService.getCustomers();
        return (List<CustomerDto>) ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable("id") int id, HttpServletRequest request) {

        if (request.getSession() == null || request.getSession().getAttribute("admin") == null ||
                (Boolean)request.getSession().getAttribute("admin") == false){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCustomer(@RequestBody CustomerDto customerDto, @PathVariable("id") int id, HttpServletRequest request ) {

        if (request.getSession() == null || request.getSession().getAttribute("admin") == null ||
                (Boolean)request.getSession().getAttribute("admin") == false){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        }
        customerService.updateCustomer(customerDto, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
