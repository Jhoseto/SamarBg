package org.samarBg.repository;

import org.samarBg.model.entities.OfferImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferImageRepository extends JpaRepository<OfferImageEntity, Long> {
    List<OfferImageEntity> findByHorseOfferId(Long offerId);
    List<OfferImageEntity> findByAccessoryOfferId(Long offerId);

}
