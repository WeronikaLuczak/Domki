package pl.wluczak.projektdomki.api.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wluczak.projektdomki.api.data.model.OfferEntity;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity,Integer> {
}
