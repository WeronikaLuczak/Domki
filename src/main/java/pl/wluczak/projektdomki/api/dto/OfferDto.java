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
    private String name;
    private String address;
    private String dateFrom;
    private String dateTo;
    private int maximumNumberOfPeople;
    private boolean animals;
    private boolean noSmoking;
    private int pricePln;
}
