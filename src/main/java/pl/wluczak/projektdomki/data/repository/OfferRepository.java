package pl.wluczak.projektdomki.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wluczak.projektdomki.data.model.OfferEntity;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity,Integer> {
}
