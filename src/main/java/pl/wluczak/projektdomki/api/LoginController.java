package pl.wluczak.projektdomki.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wluczak.projektdomki.data.model.CustomerEntity;
import pl.wluczak.projektdomki.data.repository.CustomerRepository;
import pl.wluczak.projektdomki.service.CustomerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/")
@AllArgsConstructor
public class LoginController {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    @PostMapping(value = "/login")
    public ResponseEntity<Void> loginUser(@RequestHeader("X-AUTH-LOGIN") String login,
                                          @RequestHeader("X-AUTH-PASSWORD") String password,
                                          HttpServletRequest request) {
        boolean userExistAndHasCorrectPassword = customerService.checkIsUserExistAndHasCorrectPassword(login, password);
        if (userExistAndHasCorrectPassword) {
            Optional<CustomerEntity> user = customerRepository.findByLogin(login);
            HttpSession session = request.getSession();

            session.setAttribute("userId", user.get().getId());
            session.setAttribute("admin", user.get().isAdmin());

            return ResponseEntity.status(HttpStatus.CREATED).build();
        }


        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

    @DeleteMapping (value = "/logout")
    public ResponseEntity<Void> logOutUser(HttpServletRequest request) {

        if (request.getSession() != null) {
            request.getSession().invalidate();
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
