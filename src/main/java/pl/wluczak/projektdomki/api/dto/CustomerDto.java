package pl.wluczak.projektdomki.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDto {

    private Integer id;
    private String name;
    private String surname;
    private AddressDto address;
    private String telephoneNumber;
    private String email;

}
