package pl.wluczak.projektdomki.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wluczak.projektdomki.api.dto.ReservationDto;
import pl.wluczak.projektdomki.data.model.ReservationEntity;
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

    public void createReservation(ReservationDto reservationDto) {
        Date from = DateUtils.convertDate(reservationDto.getDateFrom());
        Date to = DateUtils.convertDate(reservationDto.getDateTo());
        ReservationEntity reservationEntity = new ReservationEntity(reservationDto.getCustomerId(), from, to,
                reservationDto.getHouseId(), reservationDto.getPaymentMethod());

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

//    public void updateReservation(ReservationDto reservationDto, int id) {
//        Optional<ReservationEntity> entityOptional = reservationRepository.findById(id);
//
//        boolean exist = entityOptional.isPresent();
//
//        if (exist) {
//            ReservationEntity entity = entityOptional.get();
//            entity.setCustomerId(reservationDto.getCustomerId());
//            entity.setDateFrom(DateUtils.convertDate(reservationDto.getDateFrom()));
//            entity.setDateFrom(DateUtils.convertDate(reservationDto.getDateTo()));
//            entity.setHouseId(reservationDto.getHouseId());
//            entity.setPaymentMethod(reservationDto.getPaymentMethod());
//
//            reservationRepository.save(entity);
//        }
//
//    }
}
