package org.samarBg.repository;


import org.samarBg.model.entities.HorseOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorseOfferRepository extends JpaRepository<HorseOfferEntity, Long> {
}
