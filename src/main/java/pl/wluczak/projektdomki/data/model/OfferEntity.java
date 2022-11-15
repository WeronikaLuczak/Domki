package pl.wluczak.projektdomki.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "offers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "house_name")
    private String houseName;

    @Column(name = "address")
    private String address;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_from")
    private Date dateFrom;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_to")
    private Date dateTo;

    @Column(name ="maximum_number_of_people")
    private int maximumNumberOfPeople;

    @Column(name = "animals")
    private boolean animals;

    @Column(name ="no_smoking")
    private boolean noSmoking;

    @Column(name = "price_per_day_pln_in_season")
    private double pricePerDayPlnInSeason;

    @Column(name = "price_per_day_pln_out_of_season")
    private double pricePerDayPlnOutOfSeason;

}
