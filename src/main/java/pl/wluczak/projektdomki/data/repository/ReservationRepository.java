package pl.wluczak.projektdomki.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.wluczak.projektdomki.data.model.ReservationEntity;

import java.util.Date;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity,Integer> {

    @Query("select count(r) from ReservationEntity r where r.houseId = :houseId and " +
            "((r.dateFrom < :to and r.dateTo > :from) or (r.dateFrom < :from and r.dateTo > :from) " +
            "or (r.dateFrom < :to and r.dateTo > :to))")
    Integer countReservationBetweenDates (Date from, Date to, Integer houseId);

}
