package pl.wluczak.projektdomki.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDto {

    private String street;
    private Integer houseNo;
    private Integer flatNo;
    private String postCode;
    private String city;

}
