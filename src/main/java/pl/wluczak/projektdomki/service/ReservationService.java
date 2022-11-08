package pl.wluczak.projektdomki.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wluczak.projektdomki.api.dto.ReservationDto;
import pl.wluczak.projektdomki.data.model.OfferEntity;
import pl.wluczak.projektdomki.data.model.ReservationEntity;
import pl.wluczak.projektdomki.data.repository.OfferRepository;
import pl.wluczak.projektdomki.data.repository.ReservationRepository;
import pl.wluczak.projektdomki.utils.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final OfferRepository offerRepository;

    public void createReservation(ReservationDto reservationDto) {
        Date from = DateUtils.convertDate(reservationDto.getDateFrom());
        Date to = DateUtils.convertDate(reservationDto.getDateTo());

        if (from.compareTo(to) >= 0) {
            throw new RuntimeException("Data from >= dacie to");
        }
        Optional<OfferEntity> offerOpt = offerRepository.findById(reservationDto.getHouseId());
        if (offerOpt.isEmpty()) {
            throw new RuntimeException("Oferta o podanym ID nie istnieje");
        }

        OfferEntity offer = offerOpt.get();

        int offerCounts = reservationRepository.countReservationBetweenDates(from,to, offer.getId());

        if (offerCounts > 0){
            throw new RuntimeException("Istnieją rezerwacje w tym czasie");
        }

        int diffDays = DateUtils.countDifferenceDaysBetween(from, to);

        double price = diffDays * offer.getPricePerDayPln();

        System.out.println(diffDays);

        ReservationEntity reservationEntity = new ReservationEntity(null, reservationDto.getCustomerId(), from, to,
                reservationDto.getHouseId(), reservationDto.getPaymentMethod(), price);

        reservationRepository.save(reservationEntity);

    }

    public List<ReservationDto> getReservation() {
        List<ReservationEntity> entities = reservationRepository.findAll();
        return entities.stream()
                .map(reservationEntity -> new ReservationDto(reservationEntity.getCustomerId(),
                        DateUtils.convertDate(reservationEntity.getDateFrom()),
                        DateUtils.convertDate(reservationEntity.getDateTo()),
                        reservationEntity.getHouseId(), reservationEntity.getPaymentMethod()))
                .collect(Collectors.toList());
    }

    public void deleteReservation(int id) {
        boolean exist = reservationRepository.existsById(id);
        if (exist) {
            reservationRepository.deleteById(id);
        }

    }

}
