package pl.wluczak.projektdomki.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wluczak.projektdomki.api.dto.AddressDto;
import pl.wluczak.projektdomki.api.dto.CustomerDto;
import pl.wluczak.projektdomki.data.model.CustomerEntity;
import pl.wluczak.projektdomki.data.repository.CustomerRepository;
import pl.wluczak.projektdomki.exception.OperationNotAllowedException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//ddd
@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public void createCustomer(CustomerDto customerDto) {

        if (customerRepository.findByLogin(customerDto.getLogin()).isPresent()) {
            throw new OperationNotAllowedException("Istnieje użytkownik o podanym loginie");


        }
        AddressDto addressDto = customerDto.getAddress();
        CustomerEntity customerEntity = new CustomerEntity(null, customerDto.getName(), customerDto.getSurname(),
                addressDto.getStreet(), addressDto.getHouseNo(), addressDto.getFlatNo(), addressDto.getPostCode(),
                addressDto.getCity(), customerDto.getTelephoneNumber(), customerDto.getEmail(), customerDto.getLogin(),
                changePasswordToHash(customerDto.getPassword()), false);

        customerRepository.save(customerEntity);

    }

    public List<CustomerDto> getCustomers() {
        List<CustomerEntity> entities = customerRepository.findAll();
        return entities.stream()
                .map(entity -> new CustomerDto(entity.getId(), entity.getName(), entity.getSurname(),
                        new AddressDto(entity.getStreet(), entity.getHouseNumber(), entity.getFlatNumber(), entity.getPostCode(),
                                entity.getCity()), entity.getTelephoneNumber(), entity.getEmail(), entity.getLogin(), null))
                .collect(Collectors.toList());
    }

    public void deleteCustomer(int id) {

        boolean exist = customerRepository.existsById(id);

        if (exist) {
            customerRepository.deleteById(id);
        }

    }

    // 1 pobranie encji z bd o id
    //2 sprawdzenie czy obiekt istnieje
    //3 jeśli istnieje to aktualizujemy jego pola
    //4 zapisujemy zmianmy

    public void updateCustomer(CustomerDto customerDto, int id) {

        Optional<CustomerEntity> entityOptional = customerRepository.findById(id);

        boolean exist = entityOptional.isPresent();

        if (exist) {
            CustomerEntity entity = entityOptional.get();
            entity.setName(customerDto.getName());
            entity.setSurname(customerDto.getSurname());
            entity.setStreet(customerDto.getAddress().getStreet());
            entity.setHouseNumber(customerDto.getAddress().getHouseNo());
            entity.setFlatNumber(customerDto.getAddress().getFlatNo());
            entity.setPostCode(customerDto.getAddress().getPostCode());
            entity.setCity(customerDto.getAddress().getCity());
            entity.setTelephoneNumber(customerDto.getTelephoneNumber());
            entity.setEmail(customerDto.getEmail());
            entity.setLogin(customerDto.getLogin());
            entity.setPasswordHash(changePasswordToHash(customerDto.getPassword()));

            customerRepository.save(entity);
        }
//pomost międziy end pointem a bazą fdanych
    }

    public boolean checkIsUserExistAndHasCorrectPassword(String login, String password) {
        Optional<CustomerEntity> customerOptional = customerRepository.findByLogin(login);
        if (customerOptional.isEmpty()) {
            return false;
        }
        CustomerEntity customer = customerOptional.get();
        boolean isValidPassword = checkPassword(password, customer.getPasswordHash());
        return isValidPassword;

    }

    private String changePasswordToHash(String password) {

        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), new byte[16], 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            byte[] hash = factory.generateSecret(spec).getEncoded();
            return new String(hash, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    public boolean checkPassword(String passwordCandidate, String hash) {
        String hashCandidate = changePasswordToHash(passwordCandidate);
        return hashCandidate.equals(hash);
    }

//16:02:30	UPDATE `domki`.`customers` SET `admin` = '1' WHERE (`id` = '2')	1406: Data too long for column 'admin' at row 1
}
