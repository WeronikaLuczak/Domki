package pl.wluczak.projektdomki.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wluczak.projektdomki.api.dto.CustomerDto;
import pl.wluczak.projektdomki.api.dto.OfferDto;
import pl.wluczak.projektdomki.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody CustomerDto customerDto) {
        customerService.createCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<CustomerDto> getCustomers() {
        return customerService.getCustomers();

    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") int id) {
        customerService.deleteCustomer(id);

    }

    @PutMapping("/{id}")
    public void updateCustomer(@RequestBody CustomerDto customerDto, @PathVariable("id") int id) {
        customerService.updateCustomer(customerDto, id);

    }
}
