package org.samarBg.repository;


import org.samarBg.models.HorseOfferEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorseOfferRepository extends JpaRepository<HorseOfferEntity, Long> {

    Page<HorseOfferEntity> findByIsActive(int isActive, Pageable pageable);
}