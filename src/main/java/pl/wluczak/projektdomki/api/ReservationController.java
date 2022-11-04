package pl.wluczak.projektdomki.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wluczak.projektdomki.api.dto.OfferDto;
import pl.wluczak.projektdomki.api.dto.ReservationDto;
import pl.wluczak.projektdomki.service.OfferService;
import pl.wluczak.projektdomki.service.ReservationService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/reservations")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Void> createReservation(@RequestBody ReservationDto reservationDto) {
        reservationService.createReservation(reservationDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<ReservationDto> getReservation() {
        return reservationService.getReservation();

    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable("id") int id) {
        reservationService.deleteReservation(id);
    }

//    @PutMapping("/{id}")
//    public void updateReservation(@RequestBody ReservationDto reservationDto, @PathVariable("id") int id) {
//
//    }

}
