package org.samarBg.repository;


import org.samarBg.model.entities.HorseOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HorseOfferRepository extends JpaRepository<HorseOfferEntity, Long> {
    Optional<HorseOfferEntity> findByAuthorName(String authorName);
}