package pl.wluczak.projektdomki.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wluczak.projektdomki.api.dto.CustomerDto;
import pl.wluczak.projektdomki.service.CustomerService;

@RestController
@RequestMapping(value = "/api/v1/login")
@AllArgsConstructor
public class LoginController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<Void> loginUser(@RequestHeader("X-AUTH-LOGIN") String login,
                                          @RequestHeader("X-AUTH-PASSWORD") String password) {
        customerService.checkIsUserExist(login,password);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
