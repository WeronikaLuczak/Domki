package pl.wluczak.projektdomki.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationDto {

    private Integer customerId;
    private String dateFrom;
    private String dateTo;
    private Integer houseId;
    private String paymentMethod;

}
