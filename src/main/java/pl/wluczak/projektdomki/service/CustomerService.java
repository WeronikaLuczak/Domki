package pl.wluczak.projektdomki.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wluczak.projektdomki.api.dto.AddressDto;
import pl.wluczak.projektdomki.api.dto.CustomerDto;
import pl.wluczak.projektdomki.data.model.CustomerEntity;
import pl.wluczak.projektdomki.data.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//ddd
@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public void createCustomer(CustomerDto customerDto) {
        AddressDto addressDto = customerDto.getAddress();
        CustomerEntity customerEntity = new CustomerEntity(null, customerDto.getName(), customerDto.getSurname(),
                addressDto.getStreet(), addressDto.getHouseNo(), addressDto.getFlatNo(), addressDto.getPostCode(),
                addressDto.getCity(), customerDto.getTelephoneNumber(), customerDto.getEmail());

        customerRepository.save(customerEntity);

    }

    public List<CustomerDto> getCustomers() {
        List<CustomerEntity> entities = customerRepository.findAll();
        return entities.stream()
                .map(entity -> new CustomerDto(null, entity.getName(), entity.getSurname(),
                        new AddressDto(entity.getStreet(), entity.getHouseNumber(), entity.getFlatNumber(), entity.getPostCode(),
                                entity.getCity()), entity.getTelephoneNumber(), entity.getEmail()))
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

            customerRepository.save(entity);
        }
//utc
    }
}




