package pl.wluczak.projektdomki.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wluczak.projektdomki.api.dto.OfferDto;
import pl.wluczak.projektdomki.service.OfferService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/offers")
@AllArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @PostMapping
    public ResponseEntity<Void> createOffer(@RequestBody OfferDto offerDto,
                                            HttpServletRequest request) {
        if (request.getSession() == null || request.getSession().getAttribute("admin") == null ||
                (Boolean) request.getSession().getAttribute("admin") == false) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        offerService.createOffer(offerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<OfferDto> getOffers() {
        return offerService.getOffers();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable("id") int id, HttpServletRequest request) {

        if (request.getSession() == null || request.getSession().getAttribute("admin") == null ||
                (Boolean) request.getSession().getAttribute("admin") == false) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        }
        offerService.deleteOffer(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOffer(@RequestBody OfferDto offerDto, @PathVariable("id") int id,
                                              HttpServletRequest request) {

        if (request.getSession() == null || request.getSession().getAttribute("admin") == null ||
                (Boolean) request.getSession().getAttribute("admin") == false) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        offerService.updateOffer(offerDto, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/available")
    public List<OfferDto> getOffersAvailable(@RequestParam("from") String from,
                                             @RequestParam("to") String to) {
        return offerService.getOffersAvailable(from, to);
    }
}
