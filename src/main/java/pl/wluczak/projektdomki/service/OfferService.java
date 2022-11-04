package pl.wluczak.projektdomki.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wluczak.projektdomki.api.dto.OfferDto;
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
public class OfferService {

    private final OfferRepository offerRepository;

    private final ReservationRepository reservationRepository;

    public void createOffer(OfferDto offerDto) {
        Date from = DateUtils.convertDate(offerDto.getDateFrom());
        Date to = DateUtils.convertDate(offerDto.getDateTo());
        OfferEntity offerEntity = new OfferEntity(null, offerDto.getHouseName(), offerDto.getAddress(),
                from, to, offerDto.getMaximumNumberOfPeople(), offerDto.getAnimals(), offerDto.getNoSmoking(),
                offerDto.getPricePerDayPln());

        offerRepository.save(offerEntity);

    }

    public List<OfferDto> getOffers() {
        List<OfferEntity> entities = offerRepository.findAll();
        return entities.stream()
                .map(offerEntity -> new OfferDto(null, offerEntity.getHouseName(), offerEntity.getAddress(),
                        DateUtils.convertDate(offerEntity.getDateFrom()),
                        DateUtils.convertDate(offerEntity.getDateTo()),
                        offerEntity.getMaximumNumberOfPeople(), offerEntity.isAnimals(), offerEntity.isNoSmoking(),
                        offerEntity.getPricePerDayPln()))
                .collect(Collectors.toList());
    }

    public List<OfferDto> getOffersAvailable(String from, String to) {
        Date dateFrom = DateUtils.convertDate(from);
        Date dateTo = DateUtils.convertDate(to);
        List<OfferDto> dtos = getOffers().stream()
                .filter(offerDto -> {
                    int offerCounts = reservationRepository.countReservationBetweenDates(dateFrom, dateTo,offerDto.getId());
                    return offerCounts == 0;
                })
                .collect(Collectors.toList());
        return dtos;
    }

    public void deleteOffer(int id) {

        boolean exist = offerRepository.existsById(id);

        if (exist) {
            offerRepository.deleteById(id);
        }

    }

    public void updateOffer(OfferDto offerDto, int id) {

        Optional<OfferEntity> offerEntityOptional = offerRepository.findById(id);

        boolean exist = offerEntityOptional.isPresent();

        if (exist) {
            OfferEntity offerEntity = offerEntityOptional.get();
            offerEntity.setHouseName(offerDto.getHouseName());
            offerEntity.setAddress(offerDto.getAddress());
            offerEntity.setDateFrom(DateUtils.convertDate(offerDto.getDateFrom()));
            offerEntity.setDateTo(DateUtils.convertDate(offerDto.getDateTo()));
            offerEntity.setMaximumNumberOfPeople(offerDto.getMaximumNumberOfPeople());
            offerEntity.setAnimals(offerDto.getAnimals());
            offerEntity.setNoSmoking(offerDto.getNoSmoking());
            offerEntity.setPricePerDayPln(offerDto.getPricePerDayPln());

            offerRepository.save(offerEntity);
        }

    }
}




