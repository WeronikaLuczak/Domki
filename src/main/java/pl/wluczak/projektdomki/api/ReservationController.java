package pl.wluczak.projektdomki.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wluczak.projektdomki.api.dto.ReservationDto;
import pl.wluczak.projektdomki.service.ReservationService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/reservations")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Void> createReservation(@RequestBody ReservationDto reservationDto,
                                                  HttpServletRequest request) {

        if (request.getSession() == null || request.getSession().getAttribute("admin") == null ||
                (Boolean) request.getSession().getAttribute("admin") == false) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        reservationService.createReservation(reservationDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<ReservationDto> getReservation(HttpServletRequest request) {

        if (request.getSession() == null || request.getSession().getAttribute("admin") == null ||
                (Boolean) request.getSession().getAttribute("admin") == false) {
            return (List<ReservationDto>) ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        reservationService.getReservation();
        return (List<ReservationDto>) ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteReservation(@PathVariable("id") int id, HttpServletRequest request) {

        if (request.getSession() == null || request.getSession().getAttribute("admin") == null ||
                (Boolean) request.getSession().getAttribute("admin") == false) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        reservationService.deleteReservation(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
