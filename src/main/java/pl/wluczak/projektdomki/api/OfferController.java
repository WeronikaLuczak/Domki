package pl.wluczak.projektdomki.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wluczak.projektdomki.api.dto.OfferDto;
import pl.wluczak.projektdomki.service.OfferService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/offers")
@AllArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @PostMapping
    public ResponseEntity<Void> createOffer(@RequestBody OfferDto offerDto) {
        offerService.createOffer(offerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<OfferDto> getOffers() {
        return offerService.getOffers();

    }

    @DeleteMapping("/{id}")
    public void deleteOffer(@PathVariable("id") int id) {
        offerService.deleteOffer(id);
    }

    @PutMapping("/{id}")
    public void updateOffer(@RequestBody OfferDto offerDto, @PathVariable("id") int id) {
        offerService.updateOffer(offerDto, id);
    }

    @GetMapping("/available")
    public List<OfferDto> getOffersAvailable(@RequestParam("from") String from,
                                             @RequestParam("to") String to) {
        return offerService.getOffersAvailable(from, to);


    }
}
