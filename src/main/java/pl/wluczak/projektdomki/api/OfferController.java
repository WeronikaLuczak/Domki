package pl.wluczak.projektdomki.api;

import org.springframework.web.bind.annotation.*;
import pl.wluczak.projektdomki.api.dto.OfferDto;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/offers")
public class OfferController {

    @PostMapping
    public void createOffer(@RequestBody OfferDto offerDto) {

    }

    @GetMapping
    public List<OfferDto> getOffers() {
        return null;

    }

    @DeleteMapping("/{id}")
    public void deleteOffer(@PathVariable("id") int id) {

    }
    @PutMapping("/{id}")
    public void updateOffer(@RequestBody OfferDto offerDto, @PathVariable("id") int id){

    }
}
