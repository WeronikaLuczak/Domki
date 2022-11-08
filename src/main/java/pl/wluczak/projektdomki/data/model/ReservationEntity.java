package pl.wluczak.projektdomki.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "customer_id")
    private Integer customerId;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_from")
    private Date dateFrom;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_to")
    private Date dateTo;

    @Column(name = "house_id ")
    private Integer houseId;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "price")
    private double price;


}
