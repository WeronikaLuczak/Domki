package pl.wluczak.projektdomki.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OfferDto {

    private Integer id;
    private String houseName;
    private String address;
    private String dateFrom;
    private String dateTo;
    private int maximumNumberOfPeople;
    private Boolean animals;
    private Boolean noSmoking;
    private double pricePerDayPln;
}
